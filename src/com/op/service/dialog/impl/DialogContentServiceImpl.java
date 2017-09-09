package com.op.service.dialog.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;



import com.op.util.DateUtil;
import com.op.dao.BaseDao; 
import com.op.entity.dialog.Dialog;
import com.op.entity.dialog.DialogContent;
import com.op.entity.users.Users;
import com.op.service.dialog.DialogContentService;
import com.op.util.SerializationUtil;
import com.op.util.WebSocketUtil;
import  com.op.util.jedis.RedisUtil;

/** 
 * 对话内容表(DialogContent)接口实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-03-12 13:29:51 
 */  
@Service("dialogContentServiceImpl")
public class DialogContentServiceImpl implements DialogContentService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	/**
	 * 新增消息
	 * @param dialogId 对话ID(ID为0，则新增新对话)
	 * @param addresser 发送人
	 * @param addressee 接收人
	 * @param content 消息内容
	 */
	@Override
	public void saveDialogContent(String dialogId,Users user, String addressee, String content) throws Exception {
		
		String addresser = user.getuId();
		
		DialogContent dialogContent = new DialogContent();
		//判断对话ID是否为0（0为没有创建过对话或对话已删除）
		if("0".equals(dialogId)){
			Map<String,String> map = new HashMap<String,String>();
			//发信人
	    	map.put("addresser", addresser);
	    	//接收人
	    	map.put("addressee", addressee);
	    	
	    	//搜索是否存在对话
	    	List<Dialog> list = new ArrayList<Dialog>();
	    	list = (List<Dialog>) dao.findForList("DialogMapper.findDialog",map);
	    	
	    	Dialog dialog = new Dialog();
	    	if(list.size()>0){
	    		dialog = list.get(0);
	    	}else{
	    		//创建人
				dialog.setCreater(addresser);
				//创建时间
				dialog.setCreated(new Date());
				//被邀请好友
				dialog.setFriend(addressee);
				
				dao.save("DialogMapper.saveDialog", dialog);
	    	}
			//发信人
			dialogContent.setAddresser(addresser);
			//接收人
			dialogContent.setAddressee(addressee);
			//发送内容
			dialogContent.setContent(content);
			//对话ID
			dialogContent.setDialogId(dialog.getId());
			//发送时间
			dialogContent.setSendTime(new Date());
			//阅读状态
			dialogContent.setReadStatus(0);
			//发信人是否删除
			dialogContent.setIsAddresseeDelete(0);
			//接收人是否删除
			dialogContent.setIsAddresserDelete(0);
			
			dao.save("DialogContentMapper.saveDialogContent", dialogContent);
			
			dialogId = dialog.getId();
		}else{
			//发信人
			dialogContent.setAddresser(addresser);
			//接收人
			dialogContent.setAddressee(addressee);
			//发送内容
			dialogContent.setContent(content);
			//对话ID
			dialogContent.setDialogId(dialogId);
			//发送时间
			dialogContent.setSendTime(new Date());
			//阅读状态
			dialogContent.setReadStatus(0);
			//发信人是否删除
			dialogContent.setIsAddresserDelete(0);
			//接收人是否删除
			dialogContent.setIsAddresseeDelete(0);
			
			dao.save("DialogContentMapper.saveDialogContent", dialogContent);
		}
		
		//修改缓存私信消息数量

		byte[] bs = RedisUtil.get(("DialogCount_"+addressee).getBytes());
		Map<String,String> dcmap = (Map<String,String>) SerializationUtil.deserialize(bs);
		
		if(!StringUtils.isEmpty(dcmap)){
			if(!StringUtils.isEmpty(dcmap.get(dialogId))){
				dcmap.put(dialogId,Integer.valueOf(dcmap.get(dialogId))+1+"");
				dcmap.put("dialogAll", Integer.valueOf(dcmap.get("dialogAll"))+1+"");
				
				RedisUtil.set(("DialogCount_"+addressee).getBytes(),SerializationUtil.serialize(dcmap));
				//43200秒过期 即半天后自动删除缓存
				RedisUtil.expire(("DialogCount_"+addressee).getBytes(), (int) (0.5*24*60*60));
			}else{
				dcmap.put(dialogId,"1");
				dcmap.put("dialogAll", Integer.valueOf(dcmap.get("dialogAll"))+1+"");
				
				RedisUtil.set(("DialogCount_"+addressee).getBytes(),SerializationUtil.serialize(dcmap));
				//43200秒过期 即半天后自动删除缓存
				RedisUtil.expire(("DialogCount_"+addressee).getBytes(), (int) (0.5*24*60*60));
			}
		}
		
		//发信人/接收人
		String userId = addressee;
		Map<String,Object> map = new HashMap<String,Object>();
		//websocket页面判断类型
		map.put("type", "dialog");  
		//发信人
		map.put("userId", user.getuId());
		//发信名
		map.put("uName", user.getuName());
		//收信人
		map.put("addressee", addressee);
		//私信页面显示名称
		//map.put("userName", dialogContent.getAddresser());
		//私信用户头像
		map.put("uHeadImg", user.getuHeadImg());
		//私信页面显示内容
		map.put("content", dialogContent.getContent());
		//私信页面显示时间
		map.put("time", DateUtil.getTime(dialogContent.getSendTime()));
		//私信页面更改对话ID
		map.put("dialogId", dialogId);
		
		String[] usersId = new String[]{addressee,addresser};
		
		WebSocketUtil.sendMessageToUsers(usersId,map);
	}
	
	/**
	 * 修改阅读状态
	 * @param dialogContentId 信息ID
	 * @param userId 用户ID
	 * @throws Exception
	 */
	@Override
	public void updateDialogContent(String dialogId,String userId) throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		map.put("dialogId", dialogId);
		map.put("userId", userId);
		dao.update("DialogContentMapper.updateDialogContent", map);
		

		byte[] bs = RedisUtil.get(("DialogCount_"+userId).getBytes());
		map = (Map<String,String>) SerializationUtil.deserialize(bs);
		
		if(!StringUtils.isEmpty(map.get(dialogId))){
			//私信总数减去已读消息
			int count = Integer.valueOf(map.get("dialogAll")) - Integer.valueOf(map.get(dialogId));
			
			map.put("dialogAll", count+"");
			map.remove(dialogId);
			RedisUtil.set(("DialogCount_"+userId).getBytes(),SerializationUtil.serialize(map));
			//43200秒过期 即半天后自动删除缓存
			RedisUtil.expire(("DialogCount_"+userId).getBytes(), (int) (0.5*24*60*60));
			
			map.clear();
			//页面更改私信数量
			map.put("type", "dialogCount");
			map.put("dialogCount", count+"");
			WebSocketUtil.sendMessageToUser(userId,map);
		}
	}
	
	/**
	 * 修改所有对话的阅读状态
	 * @param userId 用户ID
	 * @throws Exception
	 */
	@Override
	public void updateAllDialogContent(String userId) throws Exception {
		dao.update("DialogContentMapper.updateAllDialogContent", userId);
	}

	/**
	 * 删除对话(时间删除)
	 * @param dialogContentId 信息ID
	 * @throws Exception
	 */
	@Override
	public void deleteDialogContent(String dialogContentId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 用户未读数量
	 * @param userId 用户ID
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String,String> dialogContentCount(Users users) throws Exception{

		
		byte[] bs = RedisUtil.get(("DialogCount_"+users.getuId()).getBytes());
		Map<String,String> map = (Map<String,String>) SerializationUtil.deserialize(bs);
		
		if(StringUtils.isEmpty(map)){
			map = new HashMap<String,String>();
			List<Map<Object,Object>> list = (List<Map<Object, Object>>) dao.findForList("DialogContentMapper.dialogContentCount", users.getuId());
			
			int num = 0;
			for(int i=0,len=list.size();i<len;i++){
				map.put(list.get(i).get("DIALOGID")+"", list.get(i).get("NUM")+"");
				if(!StringUtils.isEmpty(list.get(i).get("NUM")+"")){
					num += Integer.valueOf(list.get(i).get("NUM")+"");
				}
			}
			
			map.put("dialogAll", num+"");
			
			RedisUtil.set(("DialogCount_"+users.getuId()).getBytes(),SerializationUtil.serialize(map));
			//43200秒过期 即半天后自动删除缓存
			RedisUtil.expire(("DialogCount_"+users.getuId()).getBytes(), (int) (0.5*24*60*60));
		}
		
		return map;
	}
	
	/**
	 * 根据对话ID查询所有内容
	 * @param dialogId 对话表ID
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<DialogContent> findDialogContentByDialogId(String dialogId,String userId) throws Exception{
		List<DialogContent> list = new ArrayList<DialogContent>();
		
		Map<String,String> map = new HashMap<String,String>();
		
		map.put("dialogId", dialogId);
		
		list = (List<DialogContent>) dao.findForList("DialogContentMapper.findDialogContentByDialogId", map);
		
		List<DialogContent> finalList = new ArrayList<DialogContent>();
		
		for(int i=list.size()-1;i>=0;i--){
			finalList.add(list.get(i));
		}
		
		map.put("userId", userId);
		
		dao.update("DialogContentMapper.updateDialogContent", map);
		

		byte[] bs = RedisUtil.get(("DialogCount_"+userId).getBytes());
		map = (Map<String,String>) SerializationUtil.deserialize(bs);
		
		if(!StringUtils.isEmpty(map.get(dialogId))){
			//私信总数减去已读消息
			int count = Integer.valueOf(map.get("dialogAll")) - Integer.valueOf(map.get(dialogId));
			
			map.put("dialogAll", count+"");
			map.remove(dialogId);
			RedisUtil.set(("DialogCount_"+userId).getBytes(),SerializationUtil.serialize(map));
			//43200秒过期 即半天后自动删除缓存
			RedisUtil.expire(("DialogCount_"+userId).getBytes(), (int) (0.5*24*60*60));
			
			map.clear();
			//页面更改私信数量
			map.put("type", "dialogCount");
			map.put("dialogCount", count+"");
			WebSocketUtil.sendMessageToUser(userId,map);
		}
		
		return finalList;
	}
	
	/**
	 * 切换聊天用户显示与该用户对话内容
	 * 查看更多消息
	 * @param dialogId 对话框Id
	 * @param userId 好友Id
	 * @param login 登录用户Id
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<DialogContent> findDialogContentByUserId(Map<String,String> map) throws Exception{
		if(StringUtils.isEmpty(map.get("dialogContentId"))){
			Map<String,String> upMap = new HashMap<String,String>();
			upMap.put("userId", map.get("login"));
			upMap.put("dialogId", map.get("id"));
			dao.update("DialogContentMapper.updateDialogContent", upMap);
			

			byte[] bs = RedisUtil.get(("DialogCount_"+map.get("login")).getBytes());
			Map<String,String> dcmap = (Map<String,String>) SerializationUtil.deserialize(bs);
			
			if(!StringUtils.isEmpty(dcmap.get(map.get("id")))){
				//私信总数减去已读消息
				int count = Integer.valueOf(dcmap.get("dialogAll"))-Integer.valueOf(dcmap.get(map.get("id")));
				
				dcmap.put("dialogAll", count+"");
				dcmap.remove(map.get("id"));
				RedisUtil.set(("DialogCount_"+map.get("login")).getBytes(),SerializationUtil.serialize(dcmap));
				//43200秒过期 即半天后自动删除缓存
				RedisUtil.expire(("DialogCount_"+map.get("login")).getBytes(), (int) (0.5*24*60*60));
				
				dcmap.clear();
				//页面更改私信数量
				map.put("type", "dialogCount");
				dcmap.put("dialogCount", count+"");
				WebSocketUtil.sendMessageToUser(map.get("login"),dcmap);
			}
		}
		
		return (List<DialogContent>) dao.findForList("DialogContentMapper.findDialogContentByUserId", map);
	}
	
}
