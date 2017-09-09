package com.op.service.messageTemplate.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;



import com.op.dao.BaseDao;
import com.op.entity.MessageTemplate.MessageTemplate;
import com.op.service.messageTemplate.MessageTemplateService;
import com.op.util.SerializationUtil;
import  com.op.util.jedis.RedisUtil;


@Service("messageTemplateServiceImpl")
public class MessageTemplateServiceImpl implements MessageTemplateService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	/**
	 * 查询所有站内信模板
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String,MessageTemplate> findMessageTemplate() throws Exception{
		List<MessageTemplate> list = (List<MessageTemplate>) dao.findForList("MessageTemplateMapper.findMessageTemplate");
		
		Map<String,MessageTemplate> map = new HashMap<String,MessageTemplate>();
		for(MessageTemplate messageTemplate:list){
			map.put(messageTemplate.getMt_name(), messageTemplate);
		}

		RedisUtil.del("MessageTemplate".getBytes());
		RedisUtil.set("MessageTemplate".getBytes(),SerializationUtil.serialize(map));
		
		return map;
	}
	
}
