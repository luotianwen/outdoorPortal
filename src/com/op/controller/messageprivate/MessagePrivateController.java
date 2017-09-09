package com.op.controller.messageprivate;

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
import com.op.entity.messageType.MessageType;
import com.op.entity.messageprivate.MessageAddressee;
import com.op.entity.messageprivate.MessagePrivate;
import com.op.entity.users.Users;
import com.op.plugin.page.Page;
import com.op.service.messageType.MessageTypeService;
import com.op.service.messageprivate.MessageAddresseeService;
import com.op.service.messageprivate.MessagePrivateService;
import com.op.util.SerializationUtil;
import  com.op.util.jedis.RedisUtil;

/** 
 * 站内信消息(PrivateMessage)Controller
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-03-07 17:33:09 
 */ 
@Controller
@RequestMapping(value="/messagePrivate")
public class MessagePrivateController extends BaseController {
	
	@Resource(name="messagePrivateServiceImpl")
	private MessagePrivateService messagePrivateServiceImpl;
	@Resource(name="messageAddresseeServiceImpl")
	private MessageAddresseeService messageAddresseeServiceImpl;
	@Resource(name="messageTypeServiceImpl")
	private MessageTypeService messageTypeServiceImpl;
	
	/**
	 * 方法描述：发送通知
	 * @throws Exception 
	 */
	@RequestMapping(value="saveMessagePrivate")
	public void saveMessagePrivate(){
		Users users = this.getSessionUser();
		String title = "测试站内信";
		String content = "站内信测试信息！！！";
		int type = 1;
		
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("title", title);
			map.put("content", content);
			
			messagePrivateServiceImpl.saveMessagePrivate(map, users,"orderSignup");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 方法描述：群发通知
	 * @throws Exception 
	 */
	@RequestMapping(value="saveMassMessage")
	public void saveMassMessage(){
		String title = "测试站内信群发";
		String content = "站内信群发测试信息！！！";
		int type = 1;
		int identity = 0;
		
		try {
			messagePrivateServiceImpl.saveMassMessage(title, content, identity,"");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 查看详情
	 * @param type （1：发信箱；2： 收信箱）
	 * @param id 消息ID（type=1:信息主表ID；type=2:收信表ID）
	 */
	@RequestMapping(value="readMessage")
	public ModelAndView readMessage(@RequestParam(value="type") Integer type,@RequestParam(value="id") String id){
		ModelAndView mav=new ModelAndView();
		if(null!=id&&!"".equals(id)){
			try {
				if(type==1){
					MessagePrivate messagePrivate = new MessagePrivate();
					messagePrivate = messagePrivateServiceImpl.findForMessagePrivateById(id);
					mav.addObject("messagePrivate",messagePrivate);
					mav.addObject("type",1);
				}else{
					MessageAddressee messageAddressee = new MessageAddressee();
					messageAddressee = messageAddresseeServiceImpl.findForMessageAddresseeById(id);
					mav.addObject("messageAddressee",messageAddressee);
					mav.addObject("type",2);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
	    	mav.setViewName("message/messageDetails");
		}else{
			System.out.println("没有从页面获取信息ID");
		}
		
    	return mav;
	}
	
	/**
	 * 定期自动清理用户删除消息
	 * @param id 信息表ID
	 * @throws Exception 
	 */
	@RequestMapping(value="deleteMessage")
	public void deleteMessage(){
		try {
			messageAddresseeServiceImpl.messageShiftDelete("接收人", 1,1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 彻底删除系统消息
	 * @param id 信息表ID
	 * @throws Exception 
	 */
	@RequestMapping(value="messageShiftDelete")
	public void messageShiftDelete(@RequestParam(value="id") String id){
		try {
			messagePrivateServiceImpl.messageShiftDelete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 匹配系统群发站内信
	 * @throws Exception
	 */
	@RequestMapping("/matchingMessage")
	public void matchingMessage(){
		Users users = new Users();
		users.setuId("接收人");
		users.setuType(0);
		try {
			messageAddresseeServiceImpl.saveMessageAddressee(users);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 未读信息总数
	 * @param Users 用户对象
	 * @throws Exception
	 */
	@RequestMapping("/messageCount")
	public void messageCount(){
		Users users = this.getSessionUser();
		
		try {
			System.out.println(messageAddresseeServiceImpl.messageCount(users));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 方法描述：发信箱
	 * @throws Exception 
	 */
	@RequestMapping("/send")
	public ModelAndView getMessagePageListSend(Page<Map<String,Object>> page){
		ModelAndView mav=new ModelAndView();
		try {
			List<MessagePrivate> list = messagePrivateServiceImpl.getMessagePageListSend(1, 0, page);
			
			mav.addObject("messageList",list);
			mav.addObject("type",1);
			mav.setViewName("message/message");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mav;
	}
	
	
	/* ---------------------------------------------------------站内信最新内容----------------------------------------------------- */
	
	/**
	 * 方法描述：收信箱
	 * @throws Exception 
	 */
	@RequestMapping("/receive")
    public ModelAndView getMessagePageListReceive(Page<Map<String,Object>> page,String type){
		ModelAndView mav=new ModelAndView();
		try {
			Users users = this.getSessionUser();
			

			
			//从缓存中获取消息类型
			Map<String,MessageType> mtMap = messageTypeServiceImpl.findMessageType();
			
			MessageType messageType = mtMap.get(type);
			
			if(messageType==null){
				messageType = mtMap.get("11");
			}else{
				if("0".equals(messageType.getParent_id())){
					messageType = mtMap.get(type+"1");
				}
			}
			
			mav.addObject("messageType",messageType);
			
			//从缓存中获取未读消息数量
			Map<String,String> messageTypeCount = messageTypeCount();
			
			if(Integer.valueOf(messageTypeCount.get(messageType.getTemplate()))>0){
				//该类型的消息全部改为已读
				messageAddresseeServiceImpl.updateReadState(users, messageType.getId());
				
				//修改缓存数据
				messageTypeCount.put(mtMap.get(messageType.getParent_id()).getTemplate(), Integer.valueOf(messageTypeCount.get(mtMap.get(messageType.getParent_id()).getTemplate()))-Integer.valueOf(messageTypeCount.get(messageType.getTemplate()))+"");
				messageTypeCount.put(messageType.getTemplate(), "0");
				
				//将新的消息数量存入缓存
				RedisUtil.set(("MessageTypeCount_"+users.getuId()).getBytes(),SerializationUtil.serialize(messageTypeCount));
				//43200秒过期 即半天后自动删除缓存
				RedisUtil.expire(("MessageTypeCount_"+users.getuId()).getBytes(), (int) (0.5*24*60*60));
			}
			
			mav.addObject("messageTypeCount",messageTypeCount);
			
			List<MessageAddressee> messageList = messageAddresseeServiceImpl.getMessagePageListReceive(users.getuId(), Integer.valueOf(messageType.getId()),page);
			mav.addObject("messageList",messageList);
			
	    	mav.addObject("type",2);
	    	mav.setViewName("message/msg");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return mav;
    }
	
	/**
	 * 从缓存中获取未读消息数量
	 */
	@RequestMapping("/messageTypeCount")
	@ResponseBody
	public Map<String,String> messageTypeCount(){

		Users users = this.getSessionUser();
		byte[] bs = RedisUtil.get(("MessageTypeCount_"+users.getuId()).getBytes());
		Map<String,String> map = (Map<String,String>) SerializationUtil.deserialize(bs);
    	
    	if(StringUtils.isEmpty(map)){
    		try {
				map = messageAddresseeServiceImpl.messageCount(this.getSessionUser());
				RedisUtil.set(("MessageTypeCount_"+users.getuId()).getBytes(),SerializationUtil.serialize(map));
				//43200秒过期 即半天后自动删除缓存
				RedisUtil.expire(("MessageTypeCount_"+users.getuId()).getBytes(), (int) (0.5*24*60*60));
			} catch (Exception e) {
				logger.error("从缓存中获取未读消息数量!!!!!!",e);
			}
    	}
		
		return map;
	}
	
	/**
	 * 删除信息
	 * @throws Exception 
	 */
	@RequestMapping(value="messageDelete")
	@ResponseBody
	public void messageDelete(String id){
		try {
			Users users = this.getSessionUser();
			messageAddresseeServiceImpl.messageDelete(users,id);
		} catch (Exception e) {
			logger.error("删除站内信异常！！！！！！！！！",e);
		}
	}
	
}
