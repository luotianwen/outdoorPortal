package com.op.controller.dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



import com.op.controller.BaseController;
import com.op.dto.usercenter.usercenter.UserCenterInfo;
import com.op.entity.dialog.Dialog;
import com.op.entity.dialog.DialogContent;
import com.op.entity.users.Users;
import com.op.service.dialog.DialogContentService;
import com.op.service.dialog.DialogService;
import com.op.service.users.UsersService;
import com.op.util.Const;
import com.op.util.HTMLEscape;
import com.op.util.SerializationUtil;
import  com.op.util.jedis.RedisUtil;

@Controller
@RequestMapping(value="/dialog")
public class DialogController extends BaseController {
	
	//聊天列表
	@Resource(name="dialogServiceImpl")
	private DialogService dialogServiceImpl;
	
	//聊天内容
	@Resource(name="dialogContentServiceImpl")
	private DialogContentService dialogContentServiceImpl;
	
	@Resource(name="usersServiceImpl")
	private UsersService usersServiceImpl;
	
	/**
	 * 发送消息
	 * @param dialogId 对话框ID
	 * @param addressee 收信人
	 * @param content 消息内容
	 * @throws Exception
	 */
	@RequestMapping(value="saveDialogContent")
	@ResponseBody
	public Map<String,String> saveDialogContent(@RequestParam(value="dialogId")String dialogId,@RequestParam(value="addressee")String addressee,String content){
		Users user = this.getSessionUser();
		Map<String,String> map = new HashMap<String,String>();
		try {
			dialogContentServiceImpl.saveDialogContent(dialogId, user, addressee, HTMLEscape.htmlEscape(content));
			map.put(Const.RESPONSE_STATE, "200");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put(Const.RESPONSE_STATE, "500");
		}
		return map;
	}
	
	/**
	 * 私信对话框
	 * @param id
	 * @param mav
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="msg")
	public ModelAndView dialog(String id,ModelAndView mav){
		String userId = this.getSessionUser().getuId();
		//当前用户聊天内容
		List<DialogContent> listDialogContent = new ArrayList<DialogContent>();
		//对话列表
		List<Dialog> dialogList = new ArrayList<Dialog>();
		
		try {
			if(!userId.equals(id)){
				if(!StringUtils.isEmpty(id)){
					//获取与当前用户之间的对话列表
					List<Dialog> list = dialogServiceImpl.findDialog(userId,id);
					
					//获取所有对话列表
					List<Dialog> listAll = dialogServiceImpl.findDialogByUserId(userId,id,"0");
					
					if(list.size()>0){
						dialogList.add(list.get(0));
						listDialogContent = dialogContentServiceImpl.findDialogContentByDialogId(list.get(0).getId(),userId);
						mav.addObject("friendUser",list.get(0).getUser());
					}else{
						Dialog dialog = new Dialog();
						dialog.setId("0");
						dialog.setCreater(userId);
						dialog.setFriend(id);
						
						UserCenterInfo userCenterInfo = usersServiceImpl.findUserCenterInfo(id);
						if(userCenterInfo!=null){
							dialog.setUser(userCenterInfo);
							dialogList.add(dialog);
						}
					}
					
					for(int i=0;i<listAll.size();i++){
						dialogList.add(listAll.get(i));
					}
					
				}else{
					//获取所有对话列表
					List<Dialog> listAll = dialogServiceImpl.findDialogByUserId(userId,id,"0");
					
					if(listAll.size()>0){
						listDialogContent = dialogContentServiceImpl.findDialogContentByDialogId(listAll.get(0).getId(),userId);
						mav.addObject("friendUser",listAll.get(0).getUser());
					}
					dialogList = listAll;
				}
			}else{
				//获取所有对话列表
				List<Dialog> listAll = dialogServiceImpl.findDialogByUserId(userId,"","0");
				
				if(listAll.size()>0){
					listDialogContent = dialogContentServiceImpl.findDialogContentByDialogId(listAll.get(0).getId(),userId);
					mav.addObject("friendUser",listAll.get(0).getUser());
				}
				dialogList = listAll;
				mav.addObject("error","iderror");
			}
		} catch (Exception e) {
			logger.error("获取私信聊天列表异常！！！！！！！！", e);
		}
		
		mav.addObject("creater",userId);
		mav.addObject("dialogList",dialogList);
		mav.addObject("listDialogContent",listDialogContent);
		
		mav.setViewName("dialog/chat");
		return mav;
	}
	
	/**
	 * 切换聊天用户
	 */
	@RequestMapping(value="updateDialog")
	@ResponseBody
	public Map<String,Object> updateDialog(String id,String userId){
		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(userId)){
			try {
				Users users = this.getSessionUser();
				
				Map<String,String> pageMap = new HashMap<String,String>();
				pageMap.put("id", id);
				pageMap.put("userId", userId);
				pageMap.put("login", users.getuId());
				
				List<DialogContent> listDialogContent = dialogContentServiceImpl.findDialogContentByUserId(pageMap);
				map.put("listDialogContent", listDialogContent);
				
				UserCenterInfo createrUser = new UserCenterInfo();
				createrUser.setU_id(users.getuId());
				createrUser.setuHeadImg(users.getuHeadImg());
				createrUser.setuName(users.getuName());
				map.put("createrUser", createrUser);
				
				UserCenterInfo friendUser = usersServiceImpl.findUserCenterInfo(userId);
				map.put("friendUser", friendUser);
				
				map.put(Const.RESPONSE_STATE, "200");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("切换聊天用户异常！！！！！！！！！！！！！", e);
				map.put(Const.RESPONSE_STATE, "500");
			}
		}else{
			map.put(Const.RESPONSE_STATE, "500");
		}
		
		return map;
	}
	
	/**
	 * 查看更多消息
	 */
	@RequestMapping(value="moreInfo")
	@ResponseBody
	public Map<String,Object> moreInfo(String id,String dialogContentId,String userId){
		Map<String,Object> map = new HashMap<String,Object>();
		
		Users users = this.getSessionUser();
		
		Map<String,String> pageMap = new HashMap<String,String>();
		pageMap.put("id", id);
		pageMap.put("userId", userId);
		pageMap.put("login", users.getuId());
		pageMap.put("dialogContentId",dialogContentId);
		
		try {
			List<DialogContent> listDialogContent = dialogContentServiceImpl.findDialogContentByUserId(pageMap);
			
			map.put("listDialogContent", listDialogContent);
			
			UserCenterInfo createrUser = new UserCenterInfo();
			createrUser.setU_id(users.getuId());
			createrUser.setuHeadImg(users.getuHeadImg());
			createrUser.setuName(users.getuName());
			map.put("createrUser", createrUser);
			
			UserCenterInfo friendUser = usersServiceImpl.findUserCenterInfo(userId);
			map.put("friendUser", friendUser);
			
			map.put(Const.RESPONSE_STATE, "200");
			map.put(Const.RESPONSE_STATE, "200");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("查看更多消息方法异常！！！！！！！！！！！！", e);
			map.put(Const.RESPONSE_STATE, "500");
		}
		
		return map;
	}
	
	/**
	 * 查看更多对话
	 */
	@RequestMapping(value="moreDialog")
	@ResponseBody
	public Map<String,Object> moreDialog(String count,String id){
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
			//获取所有对话列表
			List<Dialog> listDialog = dialogServiceImpl.findDialogByUserId(this.getSessionUser().getuId(),id,count);
			map.put("listDialog", listDialog);
			map.put(Const.RESPONSE_STATE, "200");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("查看更多对话方法异常！！！！！！！！！！！！", e);
			map.put(Const.RESPONSE_STATE, "500");
		}
		
		return map;
	}
	
	/**
	 * 未读消息数量
	 */
	@RequestMapping(value="dialogContentCount")
	@ResponseBody
	public Map<String,String> dialogContentCount(){
		Map<String,String> map = new HashMap<String,String>();
		try {

			Users users = this.getSessionUser();
			byte[] bs = RedisUtil.get(("DialogCount_"+users.getuId()).getBytes());
			map = (Map<String,String>) SerializationUtil.deserialize(bs);
	    	
	    	if(StringUtils.isEmpty(map)){
	    		try {
					map = dialogContentServiceImpl.dialogContentCount(users);
					RedisUtil.set(("DialogCount_"+users.getuId()).getBytes(),SerializationUtil.serialize(map));
					//43200秒过期 即半天后自动删除缓存
					RedisUtil.expire(("DialogCount_"+users.getuId()).getBytes(), (int) (0.5*24*60*60));
				} catch (Exception e) {
					logger.error("从缓存中获取未读私信数量!!!!!!",e);
				}
	    	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		return map;
		
	}
	
	/**
	 * 关闭消息框
	 * @param id
	 */
	@RequestMapping(value="deleteDialog")
	@ResponseBody
	public void deleteDialog(String id){
		try {
			Users user = this.getSessionUser();
			
			dialogServiceImpl.deleteDialog(id,user.getuId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
	
//	@RequestMapping(value="updateDialogContent")
//	@ResponseBody
//	public void updateDialogContent(@RequestParam(value="dialogId") String dialogId){
//		try {
//			dialogContentServiceImpl.updateDialogContent(dialogId,"a");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
}
