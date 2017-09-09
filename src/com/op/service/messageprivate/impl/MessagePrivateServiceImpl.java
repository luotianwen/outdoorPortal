package com.op.service.messageprivate.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;





import com.op.dao.BaseDao;
import com.op.entity.MessageTemplate.MessageTemplate;
import com.op.entity.messageType.MessageType;
import com.op.entity.messageprivate.MessageAddressee;
import com.op.entity.messageprivate.MessagePrivate;
import com.op.entity.users.Users;
import com.op.plugin.page.Page;
import com.op.service.messageTemplate.MessageTemplateService;
import com.op.service.messageType.MessageTypeService;
import com.op.service.messageprivate.MessageAddresseeService;
import com.op.service.messageprivate.MessagePrivateService;
import com.op.service.users.UsersService;
import com.op.util.Const;
import com.op.util.SerializationUtil;
import com.op.util.WebSocketUtil;
import  com.op.util.jedis.RedisUtil;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


@Service("messagePrivateServiceImpl")
public class MessagePrivateServiceImpl implements MessagePrivateService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	@Resource(name="messageTemplateServiceImpl")
	private MessageTemplateService messageTemplateServiceImpl;
	
	@Resource(name="usersServiceImpl")
    UsersService usersService;
	
	@Resource(name="messageAddresseeServiceImpl")
	private MessageAddresseeService messageAddresseeServiceImpl;
	
	@Resource(name="messageTypeServiceImpl")
	private MessageTypeService messageTypeServiceImpl;
	
	/**
	 * 查看信息详情
	 * @param id 站内信ID
	 * @throws Exception
	 */
	@Override
	public MessagePrivate findForMessagePrivateById(String id) throws Exception{
		
		return (MessagePrivate) dao.findForObject("MessagePrivateMapper.findForMessagePrivateById", id);
	}
	
	/**
	 * 删除信息(修改信息状态)
	 * @param id 站内信ID
	 * @throws Exception
	 */
	@Override
	public void messageDelete(String id) throws Exception{
		MessagePrivate messagePrivate = new MessagePrivate();
		messagePrivate = findForMessagePrivateById(id);
		messagePrivate.setMp_isaddresserdelete(1);
		dao.update("MessagePrivateMapper.messageDelete", messagePrivate);
	}
	
	/**
	 * 彻底删除信息(删除数据库)
	 * @param id 站内信ID(多选用,隔开)
	 * @throws Exception
	 */
	@Override
	public void messageShiftDelete(String id) throws Exception{
		String[] mpId = id.split(",");
		for(int i=0;i<mpId.length;i++){
			dao.delete("MessageAddresseeMapper.messageAllDelete", mpId[i]);
			
			dao.delete("MessagePrivateMapper.messageShiftDelete", mpId[i]);
		}
	}
	
	/**
	 * 发信箱
	 * @param type 信息类型(0:所有；其他待定)
	 * @param identity 用户身份(0:所有；其他待定)
	 * @param page 分页
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<MessagePrivate> getMessagePageListSend(Integer type,Integer identity,Page<Map<String,Object>> page) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		if(type!=0){
			map.put("type", type);
		}
		if(identity!=0){
			map.put("identity", identity);
		}
		page.setT(map);
		
		return (List<MessagePrivate>) dao.findForList("MessagePrivateMapper.getMessagePageListSend", page);
	}

	/**
	 * 垃圾箱
	 * @param type 信息类型(0:所有；其他待定)
	 * @param page 分页
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<MessagePrivate> getMessagePageListDelete(Integer type,Integer identity,Page<Map<String,Object>> page)
			throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		if(type!=0){
			map.put("type", type);
		}
		if(identity!=0){
			map.put("identity", identity);
		}
		page.setT(map);
		
		return (List<MessagePrivate>) dao.findForList("MessagePrivateMapper.getMessagePageListDelete", page);
	}
	
	
	/* ---------------------------------------------------------站内信最新内容----------------------------------------------------- */
	
	/**
	 * 一对一发送站内信
	 * @param title 标题
	 * @param content 内容
	 * @param login 当前登录用户
	 * @param users 收信人
	 * @param key 消息模板
	 * @throws Exception
	 */
	@Override
	public void saveMessagePrivate(Map<String,Object> objectMap,Users users,String key) throws Exception {
		
		//获取模板
		MessageTemplate messageTemplate = messageTemplate(key);
		
		MessagePrivate messagePrivate = new MessagePrivate();
		//标题
	  	messagePrivate.setMp_title(messageTemplate.getMt_title());
	  	
	  	/**
	  	 * 填充模板数据
	  	 */
	  	Map<String,Object> map = new HashMap<String,Object>();
	  	map.put("objectMap", objectMap);
	  	
	  	String mp_content = "";
	  	if(null!=messageTemplate.getMt_content()&&!"".equals(messageTemplate.getMt_content())){
	  		mp_content = processTemplate(messageTemplate.getMt_content(),map);
	  	}
	  	
	  	//内容
	  	messagePrivate.setMp_content(mp_content);
	  	//发送时间
	  	messagePrivate.setMp_sendTime(new Date());
	  	//信息状态
	  	messagePrivate.setMp_type(Integer.valueOf(messageTemplate.getMt_type()));
	  	//发送人是否删除（0：正常；1：删除到垃圾箱；2：彻底删除）
	  	messagePrivate.setMp_isaddresserdelete(0);
		
		dao.save("MessagePrivateMapper.saveMessagePrivate",messagePrivate);
		
		MessageAddressee messageAddressee = new MessageAddressee();
	 	//信息表ID
	  	messageAddressee.setMp_id(messagePrivate.getMp_id());
	 	//阅读状态(0：未读，1：已读)
	  	messageAddressee.setMar_readStatus(0);
	 	//信息状态
	  	messageAddressee.setMar_type(Integer.valueOf(messageTemplate.getMt_type()));
	 	//接收人
	  	messageAddressee.setMar_addRessee(users.getuId());
	 	//接收人是否删除（0：正常；1：删除到垃圾箱；2：彻底删除）
	  	messageAddressee.setMar_isaddresseedelete(0);
	  	
	  	dao.save("MessageAddresseeMapper.saveMessageAddressee",messageAddressee);
		
	  	
		//收信人获取通知
		try {
			map.clear();
			//未读消息数量
			Map<String,String> countMap = new HashMap<String,String>();
			countMap = messageAddresseeServiceImpl.messageCount(users);
			
			//私信类型
			Map<String,MessageType> mtMap = messageTypeServiceImpl.findMessageType();
			MessageType messageType = mtMap.get(messageTemplate.getMt_type());
			String template = messageType.getType_name();
			
			//新增消息数量
			if(StringUtils.isEmpty(countMap.get(template))){
				countMap.put(template, "1");
			}else{
				countMap.put(template, Integer.valueOf(countMap.get(template))+1+"");
			}
			//父类消息模板
	    	MessageType messageType_Parent = mtMap.get(messageType.getParent_id());
	    	//新增消息大类总数
	    	countMap.put(messageType_Parent.getTemplate(), Integer.valueOf(countMap.get(messageType_Parent.getTemplate()))+1+"");
	    	
			map.put("type", "addMessage");
			map.put("messageTypeCount", countMap);
			
			//更新消息数量缓存
			RedisUtil.set(("MessageTypeCount_"+users.getuId()).getBytes(),SerializationUtil.serialize(countMap));
			//43200秒过期 即半天后自动删除缓存
			RedisUtil.expire(("MessageTypeCount_"+users.getuId()).getBytes(), (int) (0.5*24*60*60));
			//websocket发送消息
			WebSocketUtil.sendMessageToUser(users.getuId(),map);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 群发站内信(选择不同身份的用户群发)
	 * 选择多个不同用户群发功能暂时保留
	 * @param title	标题
	 * @param content 内容
	 * @param identity 用户身份(0:所有；其他待定)
	 * @throws Exception
	 */
	@Override
	public void saveMassMessage(String title, String content,Integer identity,String key) throws Exception {
		//获取模板
		MessageTemplate messageTemplate = messageTemplate(key);
		
		MessagePrivate messagePrivate = new MessagePrivate();
		//标题
	  	messagePrivate.setMp_title(title);
	  	
	  	/**
	  	 * 填充模板数据
	  	 */
	  	Map<String,Object> map = new HashMap<String,Object>();
	  	map.put("title", title);
	  	map.put("content", content);
	  	map.put("uName", "");
	  	
	  	String mp_content = "";
	  	if(null!=messageTemplate.getMt_content()&&!"".equals(messageTemplate.getMt_content())){
	  		mp_content = processTemplate(messageTemplate.getMt_content(),map);
	  	}
	  	
	  	//内容
	  	messagePrivate.setMp_content(mp_content);
	  	
	  	//发送时间
	  	messagePrivate.setMp_sendTime(new Date());
	  	//信息状态
	  	messagePrivate.setMp_type(Integer.valueOf(messageTemplate.getMt_type()));
	  	//发送人
	  	messagePrivate.setMp_identity(identity);
	  	//发送人是否删除（0：正常；1：删除到垃圾箱；2：彻底删除）
	  	messagePrivate.setMp_isaddresserdelete(0);
		
		dao.save("MessagePrivateMapper.saveMessagePrivate",messagePrivate);
		
		MessageAddressee messageAddressee = new MessageAddressee();
		
		String[] userIds = usersService.findUserIdByMessage(identity);
		
		for(int i=0;i<userIds.length;i++){
			messageAddressee = new MessageAddressee();
		 	//信息表ID
		  	messageAddressee.setMp_id(messagePrivate.getMp_id());
		 	//阅读状态(0：未读，1：已读)
		  	messageAddressee.setMar_readStatus(0);
		 	//信息状态
		  	messageAddressee.setMar_type(Integer.valueOf(messageTemplate.getMt_type()));
		 	//接收人
		  	messageAddressee.setMar_addRessee(userIds[i]);
		 	//接收人是否删除（0：正常；1：删除到垃圾箱；2：彻底删除）
		  	messageAddressee.setMar_isaddresseedelete(0);
		  	
		  	dao.save("MessageAddresseeMapper.saveMessageAddressee",messageAddressee);
		}
		
		//map通知内容
		map.clear();
		map.put("type", "message");
		try {
			WebSocketUtil.sendMessageToUsers(userIds,map);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 根据Key获取内存中的模板
	 * @param key
	 * @return
	 */
	public MessageTemplate messageTemplate(String key){

    	MessageTemplate template = null;
    	// 获取
		byte[] bs = RedisUtil.get("MessageTemplate".getBytes());
		Map<String,MessageTemplate> mtMap = null;
    	//(Map<String,MessageTemplate>) SerializationUtil.deserialize(bs)
    	if(StringUtils.isEmpty(mtMap)){
    		try {
				Map<String,MessageTemplate> map = messageTemplateServiceImpl.findMessageTemplate();
				template = map.get(key);
				RedisUtil.set("MessageTemplate".getBytes(),SerializationUtil.serialize(map));
				//一周后自动删除缓存
				RedisUtil.expire("MessageTemplate".getBytes(), (int) (7*24*60*60));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}else{
    		template = mtMap.get(key);
    		if(StringUtils.isEmpty(template)){
	    		try {
					Map<String,MessageTemplate> map = messageTemplateServiceImpl.findMessageTemplate();
					template = map.get(key);
					RedisUtil.set("MessageTemplate".getBytes(),SerializationUtil.serialize(map));
					//一周后自动删除缓存
					RedisUtil.expire("MessageTemplate".getBytes(), (int) (7*24*60*60));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    	
		return template;
    }

	/**
     * 处理模板内容
     * @param templateContext 模板内容
     * @param parameter 填充模板的数据上下文
     * @return
     */
	private String processTemplate(String templateContext,Map<String, Object> parameter) {
		Configuration cfg = new Configuration();
		StringTemplateLoader stringLoader = new StringTemplateLoader();
		stringLoader.putTemplate("template", templateContext);
		cfg.setTemplateLoader(stringLoader);
		StringWriter writer = new StringWriter();
		try {
			Template template = cfg.getTemplate("template", "utf-8");
			template.process(parameter, writer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return writer.toString();
	}
	
}

