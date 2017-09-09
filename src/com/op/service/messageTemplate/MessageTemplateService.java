package com.op.service.messageTemplate;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.op.entity.MessageTemplate.MessageTemplate;

@Service("messageTemplateService")
public interface MessageTemplateService {
	
	/**
	 * 查询所有站内信模板
	 * @return
	 * @throws Exception
	 */
	Map<String,MessageTemplate> findMessageTemplate() throws Exception;
}
