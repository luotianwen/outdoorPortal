package com.op.plugin.alipay.config;

import java.util.Map;

import com.op.util.PropertyFile;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088701539723278";
	
	// 收款支付宝账号，一般情况下收款账号就是签约账号
	public static String seller_email = "837978734@qq.com";
	// 商户的私钥
	public static String key = "llzfz3ubf965xlxa4babcdfrw0pe580b";

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "MD5";
	
	// 服务器异步通知页面路径
	public static String pay_notify_url;
	
	// 页面跳转同步通知页面路径
	public static String pay_return_url;
	
	//活动展示地址
	public static String buy_active_url;
	
	//场馆展示地址
	public static String buy_pointService_url;

	
	   static {
	        Map<String, String> map = PropertyFile.getPropertiesMap("AlipayConfig.properties");

	        partner = map.get("partner");
	        if (partner == null) {
	            throw new RuntimeException("partner property is not configured in AlipayConfig.properties!!!");
	        }
	        seller_email = map.get("seller_email");
	        if (seller_email == null) {
	            throw new RuntimeException("seller_email property is not configured in AlipayConfig.properties!!!");
	        }
	        key = map.get("key");
	        if (key == null) {
	            throw new RuntimeException("key property is not configured in AlipayConfig.properties!!!");
	        }
	        pay_notify_url = map.get("pay_notify_url");
	        if (pay_notify_url == null) {
	            throw new RuntimeException("pay_notify_url property is not configured in AlipayConfig.properties!!!");
	        }
	        pay_return_url = map.get("pay_return_url");
	        if (pay_return_url == null) {
	            throw new RuntimeException("pay_return_url property is not configured in AlipayConfig.properties!!!");
	        }
	        buy_active_url = map.get("buy_active_url");
	        if (buy_active_url == null) {
	            throw new RuntimeException("buy_active_url property is not configured in AlipayConfig.properties!!!");
	        }
	        buy_pointService_url = map.get("buy_pointService_url");
	        if (buy_pointService_url == null) {
	            throw new RuntimeException("buy_pointService_url property is not configured in AlipayConfig.properties!!!");
	        }
	        log_path = map.get("log_path");
	        if (log_path == null) {
	            throw new RuntimeException("log_path property is not configured in AlipayConfig.properties!!!");
	        }
	 

	    }
	
	
	
	
	
}
