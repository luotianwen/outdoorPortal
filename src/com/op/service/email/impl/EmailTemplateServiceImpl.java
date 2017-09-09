package com.op.service.email.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;



import com.op.dao.BaseDao;
import com.op.entity.email.EmailTemplate;
import com.op.service.email.EmailTemplateService;
import com.op.util.SerializationUtil;
import  com.op.util.jedis.RedisUtil;
/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：EmailTemplateServiceImpl   
* 类描述：   
* 创建人：Win Zhong   
* 创建时间：2015年12月28日 下午1:23:29   
* 修改人：Win Zhong   
* 修改时间：2015年12月28日 下午1:23:29   
* 修改备注：   
* @version    
*
 */
@Service("emailTemplateServiceImpl")
public class EmailTemplateServiceImpl implements EmailTemplateService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	/**
	 * 初始化邮件发送模板数据
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String,EmailTemplate> initEmailTemplate()throws Exception{
		List<EmailTemplate>  emailTemplateList = (List<EmailTemplate>) dao.findForList("EmailTemplate.initEmailTemplate", null);
		Map<String,EmailTemplate> map = new HashMap<String,EmailTemplate>();
		for(EmailTemplate emailTemplate:emailTemplateList){
				map.put(emailTemplate.getEt_id(), emailTemplate);
		}

	  	  RedisUtil.del("EmailTemplate".getBytes());
	  	  RedisUtil.set("EmailTemplate".getBytes(),SerializationUtil.serialize(map));
		
		return map;
	}
}
