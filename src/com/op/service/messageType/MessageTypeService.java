package com.op.service.messageType;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.op.entity.messageType.MessageType;


@Service("messageTypeService")
public interface MessageTypeService {

	//查询所有消息状态
	Map<String,MessageType> findMessageType() throws Exception;

	
}
