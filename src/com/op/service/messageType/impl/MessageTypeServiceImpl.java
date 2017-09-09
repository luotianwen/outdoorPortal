package com.op.service.messageType.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;



import com.op.dao.BaseDao;
import com.op.entity.messageType.MessageType;
import com.op.service.messageType.MessageTypeService;
import com.op.util.SerializationUtil;
import  com.op.util.jedis.RedisUtil;


@Service("messageTypeServiceImpl")
public class MessageTypeServiceImpl implements MessageTypeService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	@Override
	public Map<String,MessageType> findMessageType() throws Exception{

		byte[] bs = RedisUtil.get("MessageType".getBytes());
		Map<String,MessageType> mtMap = (Map<String,MessageType>) SerializationUtil.deserialize(bs);
    	
    	if(StringUtils.isEmpty(mtMap)){
    		mtMap = new HashMap<String,MessageType>();
    		try {
    			List<MessageType> list = (List<MessageType>) dao.findForList("MessageTypeMapper.findMessageType");
    			
    			for(MessageType messageType:list){
    				mtMap.put(messageType.getId(), messageType);
    			}
    			
    			RedisUtil.del("MessageType".getBytes());
				RedisUtil.set("MessageType".getBytes(),SerializationUtil.serialize(mtMap));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return mtMap;
	}
	
}
