package com.op.service.email;

import java.util.Map;

import com.op.entity.email.EmailTemplate;

public interface EmailTemplateService {

	/**
	 * 初始化邮件发送模板数据
	 * @return
	 * @throws Exception
	 */
	Map<String,EmailTemplate> initEmailTemplate()throws Exception;
	
	
}
