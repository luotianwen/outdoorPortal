package com.op.util.mail;

import java.util.Map;

import com.op.util.PropertyFile;
 

/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：EmailProperties   
* 类描述：   邮箱配置信息
* 创建人：Win Zhong   
* 创建时间：2015年12月28日 下午4:07:59   
* 修改人：Win Zhong   
* 修改时间：2015年12月28日 下午4:07:59   
* 修改备注：   
* @version    
*
 */
public class EmailProperties {
    public static String MAILSERVERHOST;    
    public static String MAILSERVERPORT;    
    // 邮件发送者的地址    
    public static String FROMADDRESS;    
    // 登陆邮件发送服务器的用户名和密码    
    public static String USERNAME;    
    public static String PASSWORD; 
    // 是否需要身份验证    
    public static boolean VALIDATE = true;  
    static {
        Map<String, String> map = PropertyFile.getPropertiesMap("email.properties");

        MAILSERVERHOST = map.get("MAILSERVERHOST");
        if (MAILSERVERHOST == null) {
            throw new RuntimeException("MAILSERVERHOST property is not configured in email.properties!!!");
        }
        MAILSERVERPORT = map.get("MAILSERVERPORT");
        if (MAILSERVERPORT == null) {
            throw new RuntimeException("MAILSERVERPORT property is not configured in email.properties!!!");
        }
        FROMADDRESS = map.get("FROMADDRESS");
        if (FROMADDRESS == null) {
            throw new RuntimeException("FROMADDRESS property is not configured in email.properties!!!");
        }
        USERNAME = map.get("USERNAME");
        if (USERNAME == null) {
            throw new RuntimeException("USERNAME property is not configured in email.properties!!!");
        }
        PASSWORD = map.get("PASSWORD");
        if (PASSWORD == null) {
            throw new RuntimeException("PASSWORD property is not configured in email.properties!!!");
        }
        
        if("false".equals(map.get("VALIDATE"))) {
        	VALIDATE = false;
        }

    }
	
	
	
	
	
	
	
	
	
}
