package com.op.util.mail;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.op.entity.email.EmailTemplate;
import com.op.service.email.EmailTemplateService;
import com.op.util.SerializationUtil;
import com.op.util.Tools;
import  com.op.util.jedis.RedisUtil;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
@Service("emailUtil")
public class EmailUtil{
	
	@Resource(name="emailTemplateServiceImpl")
	private EmailTemplateService emailTemplateServiceImpl;
		
	   /**
	    * 发送验证邮件
	    * @param toAddress 收件人邮箱地址
	    * @param key 模板ID
	    * @return
	    */
	   public String sendCheckEmail(String toAddress,String key){
		   	String yzm = Tools.getRandomNum()+"";
		   	EmailTemplate template = getEmailTemplate(key);
		   	// context
		   	Map<String,Object> context = new HashMap<String,Object>();
	        context.put("yanzhengma", yzm);
		   	String content = this.processTemplate(template.getEt_template(), context);
		    sendEmail(toAddress,template.getEt_title(),content);
			return yzm;
	   }
 
	   /**
	    * 发送邮件
	    * @param toAddress 收件人邮箱地址
	    * @param key 模板ID
	    * @param parameter 模板参数 上下文 map结构
	    */
	   public void sendEmail(String toAddress,String key,Map<String,Object> parameter){
		   	EmailTemplate template = getEmailTemplate(key);
		   	String content = this.processTemplate(template.getEt_template(), parameter);
		    sendEmail(toAddress,template.getEt_title(),content);
	   }
		/**
		 * 根据Key获取内存中的模板
		 * @param key
		 * @return
		 */
	    public EmailTemplate getEmailTemplate(String key){

	    	EmailTemplate template = null;
	    	// 获取
			byte[] bs = RedisUtil.get("EmailTemplate".getBytes());
			Map<String,EmailTemplate> etMap = (Map<String,EmailTemplate>) SerializationUtil.deserialize(bs);
	    	System.out.println(etMap);  
	    	if(StringUtils.isEmpty(etMap)){
	    		try {
					Map<String,EmailTemplate> map = emailTemplateServiceImpl.initEmailTemplate();
					template = map.get(key);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}else{
	    		template = etMap.get(key);
	    		if(StringUtils.isEmpty(template)){
		    		try {
						Map<String,EmailTemplate> map = emailTemplateServiceImpl.initEmailTemplate();
						template = map.get(key);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
	    	}
	    	
			return template;
	    }
	
	    /**
	     * 发送邮件
	     * @param toAddress 收件地址
	     * @param title 邮件标题
	     * @param content 邮件正文
	     */
	    private void sendEmail(String toAddress,String title,String content){
		    //这个类主要是设置邮件   
		    MailSenderInfo mailInfo = new MailSenderInfo();    
		    mailInfo.setMailServerHost(EmailProperties.MAILSERVERHOST);    
		    mailInfo.setMailServerPort(EmailProperties.MAILSERVERPORT);    
		    mailInfo.setValidate(EmailProperties.VALIDATE);    
		    mailInfo.setUserName(EmailProperties.USERNAME);    
		    mailInfo.setPassword(EmailProperties.PASSWORD);//您的邮箱密码    
		    mailInfo.setFromAddress(EmailProperties.FROMADDRESS);    
		    mailInfo.setToAddress(toAddress);    
		    mailInfo.setSubject(title);    
		    mailInfo.setContent(content);    
		    //这个类主要来发送邮件   
		    SimpleMailSender sms = new SimpleMailSender();   
		    sms.sendHtmlMail(mailInfo);//发送html格式   
	    }
	    /**
	     * 处理模板内容
	     * @param templateContext 模板内容
	     * @param parameter 填充模板的数据上下文
	     * @return
	     */
	    private String processTemplate(String templateContext,Map<String,Object> parameter){
	    	Configuration cfg = new Configuration();
	        StringTemplateLoader stringLoader = new StringTemplateLoader();
	        stringLoader.putTemplate("template",templateContext);
	         cfg.setTemplateLoader(stringLoader);
	    	 StringWriter writer = new StringWriter();
	         try {
				Template template = cfg.getTemplate("template","utf-8");
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
	    
	    
     /*	// 字符串模版
        String template = "${owner}：您的${type} : ${bill} 在  ${date} 日已支付成功";
        // 取得velocity的上下文context
        VelocityContext context = new VelocityContext();
        // 把数据填入上下文
        context.put("owner", "nassir");
        context.put("bill", "201203221000029763");
        context.put("type", "订单");
        context.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        StringWriter writer = new StringWriter();
        velocityEngine.evaluate(context, writer, "", template);
       log.info(writer.toString());*/
	
	
	
	
	
	
	
	
	
	public static void main(String[] args){   
        //这个类主要是设置邮件   
     MailSenderInfo mailInfo = new MailSenderInfo();    
     mailInfo.setMailServerHost("smtp.qq.com");    
     mailInfo.setMailServerPort("465");    
     mailInfo.setValidate(true);    
     mailInfo.setUserName("2355566253@qq.com");    
     mailInfo.setPassword("zc1992912");//您的邮箱密码    
     mailInfo.setFromAddress("2355566253@qq.com");    
     mailInfo.setToAddress("2355566251@qq.com");    
     mailInfo.setSubject("玩嘛");    
     mailInfo.setContent("欢迎注册玩嘛！！！");    
        //这个类主要来发送邮件   
     SimpleMailSender sms = new SimpleMailSender();   
        // sms.sendTextMail(mailInfo);//发送文体格式    
         sms.sendHtmlMail(mailInfo);//发送html格式   
   }  

}
