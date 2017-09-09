package com.op.service.messageprivate.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;



import com.op.dao.BaseDao;
import com.op.entity.messageType.MessageType;
import com.op.entity.messageprivate.MessageAddressee;
import com.op.entity.messageprivate.MessagePrivate;
import com.op.entity.users.Users;
import com.op.plugin.page.Page;
import com.op.service.messageType.MessageTypeService;
import com.op.service.messageprivate.MessageAddresseeService;
import com.op.util.SerializationUtil;
import  com.op.util.jedis.RedisUtil;


@Service("messageAddresseeServiceImpl")
public class MessageAddresseeServiceImpl implements MessageAddresseeService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	@Resource(name="messageTypeServiceImpl")
	private MessageTypeService messageTypeServiceImpl;
	
	/**
	 * 查看信息详情(收信人查看时修改阅读状态)
	 * @param id 站内信ID
	 * @throws Exception
	 */
	@Override
	public MessageAddressee findForMessageAddresseeById(String id) throws Exception {
		//根据ID查出本条消息
		MessageAddressee messageAddressee = new MessageAddressee();
		messageAddressee = (MessageAddressee) dao.findForObject("MessageAddresseeMapper.findForMessageAddresseeById", id);
		//阅读状态
		messageAddressee.setMar_readStatus(1);
		//阅读时间
		messageAddressee.setMar_readTime(new Date());
		//修改阅读状态
		dao.update("MessageAddresseeMapper.updateMessageAddressee", messageAddressee);
		return messageAddressee;
	}

	/**
	 * 删除信息(系统定期清理删除数据)
	 * @param userId 用户ID
	 * @param type 删除类型(0:全部；其他待定)
	 * @param isaddresseedelete 是否删除（0：用户未删除数据，1：用户已删除数据）
	 * @throws Exception
	 */
	@Override
	public void messageShiftDelete(String userId,Integer type,Integer isaddresseedelete) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		if(type!=0){
			map.put("type", type);
		}
		map.put("isaddresseedelete", isaddresseedelete);
		dao.delete("MessageAddresseeMapper.messageShiftDelete", map);
	}
	
	/**
	 * 匹配系统群发站内信
	 * @param Users 用户对象
	 * @throws Exception
	 */
	@Override
	public void saveMessageAddressee(Users users) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", users.getuId());
		map.put("identity", users.getuType());
		List<MessagePrivate> list = (List<MessagePrivate>) dao.findForList("MessagePrivateMapper.matchingMessage", map);
		
		for(int i=0;i<list.size();i++){
			MessageAddressee messageAddressee = new MessageAddressee();
		 	//信息表ID
		  	messageAddressee.setMp_id(list.get(i).getMp_id());
		 	//阅读状态(0：未读，1：已读)
		  	messageAddressee.setMar_readStatus(0);
		 	//信息状态
		  	messageAddressee.setMar_type(list.get(i).getMp_type());
		 	//阅读时间
		  	messageAddressee.setMar_readTime(new Date());
		 	//接收人
		  	messageAddressee.setMar_addRessee(users.getuId());
		 	//接收人删除状态（0：正常；1：删除到垃圾箱；2：彻底删除）
		  	messageAddressee.setMar_isaddresseedelete(0);
			
		  	dao.save("MessageAddresseeMapper.saveMessageAddressee",messageAddressee);
		}
	}
	
	
	/* ---------------------------------------------------------站内信最新内容----------------------------------------------------- */
	
	/**
	 * 收信箱
	 * @param userId 登录者ID
	 * @param type 信息类型(0:系统消息；其余待定)
	 * @param readStatus 阅读状态(0：未读；1：已读；2：全部)
	 * @param page 分页
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<MessageAddressee> getMessagePageListReceive(String userId,Integer type,Page<Map<String,Object>> page) throws Exception {
		Users users = new Users();
		users = (Users) dao.findForObject("UsersMapper.selectId", userId);
		saveMessageAddressee(users);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", userId);
		if(type!=0){
			map.put("type", type);
		}
		
		page.setT(map);
		
		return (List<MessageAddressee>) dao.findForList("MessageAddresseeMapper.getMessagePageListReceive", page);
	}
	
	/**
	 * 未读信息总数
	 * @param Users 用户对象
	 */
	@Override
	public Map<String,String> messageCount(Users user) throws Exception{

		
		byte[] bs = RedisUtil.get(("MessageTypeCount_"+user.getuId()).getBytes());
		Map<String,String> map = (Map<String,String>) SerializationUtil.deserialize(bs);
		
		if(StringUtils.isEmpty(map)){
			map = new HashMap<String,String>();
			//收信人表已有数据
			List<Map<Object,Object>> list = (List<Map<Object,Object>>) dao.findForList("MessageAddresseeMapper.messageCount", user.getuId());
			//私信类型
			Map<String,MessageType> mtMap = messageTypeServiceImpl.findMessageType();
			
	    	Set<String> set = mtMap.keySet();
	    	boolean bool = true;
	    	for(String key:set){
	    		bool = true;
	    		for(int i=0,len=list.size();i<len;i++){
	    			Map<Object,Object> mapType = list.get(i);
	    			
	    			if(key.equals(mapType.get("MAR_TYPE").toString())){
	    				map.put(mtMap.get(key).getTemplate(), mapType.get("NUM").toString());
	    				
	    				//父级ID
	    				String mt_name = mtMap.get(mtMap.get(key).getParent_id()).getTemplate();
	    				String count = map.get(mt_name);
	    				if(!StringUtils.isEmpty(count)){
	    					map.put(mt_name, Integer.valueOf(count)+Integer.valueOf(mapType.get("NUM").toString())+"");
	    				}else{
	    					map.put(mt_name, "1");
	    				}
	    				
	    				bool = false;
	    				break;
	    			}
	    		}
	    		if(bool){
	    			if(StringUtils.isEmpty(map.get(mtMap.get(key).getTemplate()))){
	    				map.put(mtMap.get(key).getTemplate(), "0");
	    			}
	    		}
	    	}
	    	RedisUtil.set(("MessageTypeCount_"+user.getuId()).getBytes(),SerializationUtil.serialize(map));
			//43200秒过期 即半天后自动删除缓存
			RedisUtil.expire(("MessageTypeCount_"+user.getuId()).getBytes(), (int) (0.5*24*60*60));
		}
    	
    	return map;
    	
		/* 暂时保留
		Map<String,Object> map = new HashMap<String,Object>();
		//用户ID
		map.put("id", user.getuId());
		//用户身份
		map.put("identity", user.getuType());
		//系统群发尚未匹配数据
		int count2 = (int) dao.findForObject("MessagePrivateMapper.matchingMessageCount", map);*/
	}
	
	/**
	 * 删除信息(收信人隐藏信息，系统会定期清理删除数据)
	 * @param id 站内信ID
	 * @throws Exception
	 */
	@Override
	public void messageDelete(Users users,String id) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mar_id", id);
		map.put("usersId", users.getuId());
		
		dao.update("MessageAddresseeMapper.updateDelState", map);
	}
	
	/**
	 * 修改信息阅读状态为已读
	 */
	@Override
	public void updateReadState(Users users,String type) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("type", type);
		map.put("usersId", users.getuId());
		
		dao.update("MessageAddresseeMapper.updateReadState", map);
	}
	
}
