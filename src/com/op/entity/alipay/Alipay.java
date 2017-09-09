package com.op.entity.alipay;

import java.util.Date;

/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：Alipay   
* 类描述：   支付宝支付信息实体类
* 创建人：Win Zhong   
* 创建时间：2015年12月25日 下午4:24:03   
* 修改人：Win Zhong   
* 修改时间：2015年12月25日 下午4:24:03   
* 修改备注：   
* @version    
*
 */
public class Alipay {
	
	//支付类型
	private final String payment_type = "1";
	 
/*	//服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数
	private String notify_url = AlipayConfig.notify_url;

	//页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
	private String return_url = AlipayConfig.return_url;*/
	 
	//商户订单号 商户网站订单系统中唯一订单号，必填
	private String out_trade_no;

	//支付宝交易号
	private String trade_no;
	
	//订单名称 必填
	private String subject;

	//付款金额 必填
	private String total_fee;

	//订单描述 可空
	private String body;
	
	//默认支付方式  若要使用纯网关，取值必须是bankPay（网银支付）。 如果不设置，默认为directPay（余额支付）。 可空
	private String paymethod;//  ======必须注意区分大小写。======
	
	//默认网银  当paymethod = "bankPay" 时 必填
	private String defaultbank;
	
	//商品展示地址 需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html 可空
	private String show_url;
	
	//防钓鱼时间戳  可空
	private String anti_phishing_key;
	
	//公用回传参数  可空
	private String extra_common_param;
	
	// 付款时间
	private Date pay_time;
	

	/*-----------------------------------------------	商家业务参数-------------------------------------*/
	
	// 商家支付业务类型(详细见Const支付类型)
	private String outDoorPayType;
	
	
	
	
	
	
	
	/*-----------------------------------------------	get or set-------------------------------------*/
	

	public Date getPay_time() {
		return pay_time;
	}

	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}

	public String getOutDoorPayType() {
		return outDoorPayType;
	}

	public void setOutDoorPayType(String outDoorPayType) {
		this.outDoorPayType = outDoorPayType;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getShow_url() {
		return show_url;
	}

	public void setShow_url(String show_url) {
		this.show_url = show_url;
	}

	public String getAnti_phishing_key() {
		return anti_phishing_key;
	}

	public void setAnti_phishing_key(String anti_phishing_key) {
		this.anti_phishing_key = anti_phishing_key;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public String getPaymethod() {
		return paymethod;
	}

	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}

	public String getDefaultbank() {
		return defaultbank;
	}

	public void setDefaultbank(String defaultbank) {
		this.defaultbank = defaultbank;
	}

	public String getExtra_common_param() {
		return extra_common_param;
	}

	public void setExtra_common_param(String extra_common_param) {
		this.extra_common_param = extra_common_param;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	
	
	
}
