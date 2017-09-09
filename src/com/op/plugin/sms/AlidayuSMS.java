package com.op.plugin.sms;

import org.apache.log4j.Logger;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

 
/**
 * 阿里大于短信发送工具
 * @author WinZhong
 *
 */
public class AlidayuSMS {

	protected static Logger logger = Logger.getLogger(AlidayuSMS.class);
	
	//App Key
	public static String appKey = "23589086";
	//App Secret
	public static String appSecret = "d37b52cd2969bf2a5d3fd54673c9c96f";
	//serverUrl
	public static String serverUrl = "http://gw.api.taobao.com/router/rest";

	public static void main(String[] args) throws ApiException {
		//AlidayuSMS.sendNotify("18500430578","432978",SmsTemplateCode.SPOTTICKE);
		AlidayuSMS.sendNotify2("13811835151", "SMS_46405040");
	}

	/**
	 * 发送手机短信通知
	 * @param phone  短信接收号码。支持单个或多个手机号码，传入号码为11位手机号码，不能加0或+86。群发短信需传入多个号码，以英文逗号分隔，一次调用最多传入200个号码。示例：18600000000,13911111111,13322222222
	 * @param templateCode 短信模板ID，传入的模板必须是在阿里大于“管理中心-短信模板管理”中的可用模板。示例：SMS_585014
	 * @return  发送状态码 （0：失败、1：成功）
	 */
	public static int sendNotify2(String phone, String templateCode){
		TaobaoClient client = new DefaultTaobaoClient(serverUrl, appKey, appSecret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		//公共回传参数，在“消息返回”中会透传回该参数；举例：用户可以传入自己下级的会员ID，在消息返回时，该会员ID会包含在内，用户可以根据该会员ID识别是哪位会员使用了你的应用
		//req.setExtend("7777777");
		//短信类型，传入值请填写normal
		req.setSmsType("normal");
		//短信签名
		req.setSmsFreeSignName("世峰户外商城");
		//短信模板变量
		req.setSmsParamString("{\"money\": \"100\",\"name\":\""+phone+"\",\"password\":\"1111\",\"year\":\"2017\"}");
		//短信接收号码
		req.setRecNum(phone);
		req.setSmsTemplateCode(templateCode);
		AlibabaAliqinFcSmsNumSendResponse rsp;

		try {
			rsp = client.execute(req);
			if(rsp.getErrorCode() == null){
				logger.info(rsp.getBody());
				return 0;
			}else{
				logger.error("短信通知发送失败："+rsp.getBody());
				return 1;
			}
		} catch (ApiException e) {
			logger.error("短信通知发送失败：", e);
			return 1;
		}
	}


	/**
	 * 发送手机短信通知
	 * @param phone  短信接收号码。支持单个或多个手机号码，传入号码为11位手机号码，不能加0或+86。群发短信需传入多个号码，以英文逗号分隔，一次调用最多传入200个号码。示例：18600000000,13911111111,13322222222
	 * @param content 短信类容
	 * @param templateCode 短信模板ID，传入的模板必须是在阿里大于“管理中心-短信模板管理”中的可用模板。示例：SMS_585014
	 * @return  发送状态码 （0：失败、1：成功）
	 */
	public static int sendNotify(String phone,String content,String templateCode){
		TaobaoClient client = new DefaultTaobaoClient(serverUrl, appKey, appSecret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		//公共回传参数，在“消息返回”中会透传回该参数；举例：用户可以传入自己下级的会员ID，在消息返回时，该会员ID会包含在内，用户可以根据该会员ID识别是哪位会员使用了你的应用
		//req.setExtend("7777777");
		//短信类型，传入值请填写normal
		req.setSmsType("normal");
		//短信签名
		req.setSmsFreeSignName("玩嘛");
		//短信模板变量
		req.setSmsParamString("{\"code\":\""+content+"\"}");
		//短信接收号码
		req.setRecNum(phone);
		req.setSmsTemplateCode(templateCode);
		AlibabaAliqinFcSmsNumSendResponse rsp;
		logger.info("短信通知接收手机号："+phone+"\t发送内容："+content);
		try {
			rsp = client.execute(req);
			if(rsp.getErrorCode() == null){
				logger.info(rsp.getBody());
				return 0;
			}else{
				logger.error("短信通知发送失败："+rsp.getBody());
				return 1;
			}
		} catch (ApiException e) {
			logger.error("短信通知发送失败：", e);
			return 1;
		}
	}



	public static String getAppKey() {
		return appKey;
	}



	public static void setAppKey(String appKey) {
		AlidayuSMS.appKey = appKey;
	}



	public static String getAppSecret() {
		return appSecret;
	}



	public static void setAppSecret(String appSecret) {
		AlidayuSMS.appSecret = appSecret;
	}



	public static String getServerUrl() {
		return serverUrl;
	}



	public static void setServerUrl(String serverUrl) {
		AlidayuSMS.serverUrl = serverUrl;
	}
	
	
}
