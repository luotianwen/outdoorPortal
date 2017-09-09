package com.newAlipay;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
/** 
 * 支付宝支付流水信息表(s_alipayInfo)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-09-07 09:30:45 
 */  
public class AlipayInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//流水号
  	 private String serial_number;
 	//支付交易订单号（网站）
  	 private String out_trade_no;
 	//商品名称
  	 private String subject;
 	//支付宝交易号
  	 private String trade_no;
 	//卖家支付宝账号，可以是Email或手机号码。
  	 private String seller_email;
 	//买家支付宝账号，可以是Email或手机号码。
  	 private String buyer_email;
 	//卖家支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字.
  	 private String seller_id;
 	//买家支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字。
  	 private String buyer_id;
 	//支付结果
  	 private String is_success;
 	//交易状态
  	 private String trade_status;
 	//交易创建时间
  	 @DateTimeFormat(pattern="yyyy-mm-dd HH:mm:ss")  
  	 private Date gmt_create;
 	//交易付款时间
  	 @DateTimeFormat(pattern="yyyy-mm-dd HH:mm:ss")
  	 private Date gmt_payment;
 	//交易关闭时间
  	 @DateTimeFormat(pattern="yyyy-mm-dd HH:mm:ss")
  	 private Date gmt_close;
 	//退款状态（1：全额退款、2：非全额退款）
  	 private String refund_status;
 	//退款金额
  	 private Date refund_fee;
 	//退款时间
  	 @DateTimeFormat(pattern="yyyy-mm-dd HH:mm:ss")
  	 private Date gmt_refund;
 	//商品单价
  	 private double price;
 	//交易金额
  	 private double total_fee;
 	//购买数量
  	 private int quantity;
 	//商品描述
  	 private String body;
 	//展示网址
  	 private String show_url;
 	//公用回传参数
  	 private String extra_common_param;
 	//是否扫码支付
  	 private String business_scene;
 	//支付订单类型（1：活动订单、2：场馆订单、3：景点门票订单）
  	 private int order_type;



	 
    /**
    *流水号
	* @return
    */ 
	public String getSerial_number() {
		return serial_number;
	}
    /**
    *流水号
	* @param type
    */ 
	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}
    /**
    *支付交易订单号（网站）
	* @return
    */ 
	public String getOut_trade_no() {
		return out_trade_no;
	}
    /**
    *支付交易订单号（网站）
	* @param type
    */ 
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
    /**
    *商品名称
	* @return
    */ 
	public String getSubject() {
		return subject;
	}
    /**
    *商品名称
	* @param type
    */ 
	public void setSubject(String subject) {
		this.subject = subject;
	}
    /**
    *支付宝交易号
	* @return
    */ 
	public String getTrade_no() {
		return trade_no;
	}
    /**
    *支付宝交易号
	* @param type
    */ 
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
    /**
    *卖家支付宝账号，可以是Email或手机号码。
	* @return
    */ 
	public String getSeller_email() {
		return seller_email;
	}
    /**
    *卖家支付宝账号，可以是Email或手机号码。
	* @param type
    */ 
	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}
    /**
    *买家支付宝账号，可以是Email或手机号码。
	* @return
    */ 
	public String getBuyer_email() {
		return buyer_email;
	}
    /**
    *买家支付宝账号，可以是Email或手机号码。
	* @param type
    */ 
	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}
    /**
    *卖家支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字.
	* @return
    */ 
	public String getSeller_id() {
		return seller_id;
	}
    /**
    *卖家支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字.
	* @param type
    */ 
	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}
    /**
    *买家支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字。
	* @return
    */ 
	public String getBuyer_id() {
		return buyer_id;
	}
    /**
    *买家支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字。
	* @param type
    */ 
	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}
    /**
    *支付结果
	* @return
    */ 
	public String getIs_success() {
		return is_success;
	}
    /**
    *支付结果
	* @param type
    */ 
	public void setIs_success(String is_success) {
		this.is_success = is_success;
	}
    /**
    *交易状态
	* @return
    */ 
	public String getTrade_status() {
		return trade_status;
	}
    /**
    *交易状态
	* @param type
    */ 
	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}
    /**
    *交易创建时间
	* @return
    */ 
	public Date getGmt_create() {
		return gmt_create;
	}
    /**
    *交易创建时间
	* @param type
    */ 
	public void setGmt_create(Date gmt_create) {
		this.gmt_create = gmt_create;
	}
    /**
    *交易付款时间
	* @return
    */ 
	public Date getGmt_payment() {
		return gmt_payment;
	}
    /**
    *交易付款时间
	* @param type
    */ 
	public void setGmt_payment(Date gmt_payment) {
		this.gmt_payment = gmt_payment;
	}
    /**
    *交易关闭时间
	* @return
    */ 
	public Date getGmt_close() {
		return gmt_close;
	}
    /**
    *交易关闭时间
	* @param type
    */ 
	public void setGmt_close(Date gmt_close) {
		this.gmt_close = gmt_close;
	}
    /**
    *退款状态
	* @return
    */ 
	public String getRefund_status() {
		return refund_status;
	}
    /**
    *退款状态
	* @param type
    */ 
	public void setRefund_status(String refund_status) {
		this.refund_status = refund_status;
	}
    /**
    *退款金额
	* @return
    */ 
	public Date getRefund_fee() {
		return refund_fee;
	}
    /**
    *退款金额
	* @param type
    */ 
	public void setRefund_fee(Date refund_fee) {
		this.refund_fee = refund_fee;
	}
    /**
    *退款时间
	* @return
    */ 
	public Date getGmt_refund() {
		return gmt_refund;
	}
    /**
    *退款时间
	* @param type
    */ 
	public void setGmt_refund(Date gmt_refund) {
		this.gmt_refund = gmt_refund;
	}
    /**
    *商品单价
	* @return
    */ 
	public double getPrice() {
		return price;
	}
    /**
    *商品单价
	* @param type
    */ 
	public void setPrice(double price) {
		this.price = price;
	}
    /**
    *交易金额
	* @return
    */ 
	public double getTotal_fee() {
		return total_fee;
	}
    /**
    *交易金额
	* @param type
    */ 
	public void setTotal_fee(double total_fee) {
		this.total_fee = total_fee;
	}
    /**
    *购买数量
	* @return
    */ 
	public int getQuantity() {
		return quantity;
	}
    /**
    *购买数量
	* @param type
    */ 
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    /**
    *商品描述
	* @return
    */ 
	public String getBody() {
		return body;
	}
    /**
    *商品描述
	* @param type
    */ 
	public void setBody(String body) {
		this.body = body;
	}
    /**
    *商品展示网址
	* @return
    */ 
	public String getShow_url() {
		return show_url;
	}
    /**
    *商品展示网址
	* @param type
    */ 
	public void setShow_url(String show_url) {
		this.show_url = show_url;
	}
    /**
    *公用回传参数  （格式：service 注解name,返回地址）
	* @return
    */ 
	public String getExtra_common_param() {
		return extra_common_param;
	}
    /**
    *公用回传参数  （格式：service 注解name,返回地址）
	* @param type
    */ 
	public void setExtra_common_param(String extra_common_param) {
		this.extra_common_param = extra_common_param;
	}
    /**
    *是否扫码支付
	* @return
    */ 
	public String getBusiness_scene() {
		return business_scene;
	}
    /**
    *是否扫码支付
	* @param type
    */ 
	public void setBusiness_scene(String business_scene) {
		this.business_scene = business_scene;
	}
    /**
    *订单类型（1：活动订单、2：场馆订单、3：景点门票订单）
	* @return
    */ 
	public int getOrder_type() {
		return order_type;
	}
    /**
    *订单类型（1：活动订单、2：场馆订单、3：景点门票订单）
	* @param type
    */ 
	public void setOrder_type(int order_type) {
		this.order_type = order_type;
	}
	@Override
	public String toString() {
		return "AlipayInfo [serial_number=" + serial_number + ", out_trade_no="
				+ out_trade_no + ", order_type=" + order_type + ", subject="
				+ subject + ", trade_no=" + trade_no + ", seller_email="
				+ seller_email + ", buyer_email=" + buyer_email
				+ ", seller_id=" + seller_id + ", buyer_id=" + buyer_id
				+ ", is_success=" + is_success + ", trade_status="
				+ trade_status + ", gmt_create=" + gmt_create
				+ ", gmt_payment=" + gmt_payment + ", gmt_close=" + gmt_close
				+ ", refund_status=" + refund_status + ", gmt_refund="
				+ gmt_refund + ", price=" + price + ", total_fee=" + total_fee
				+ ", quantity=" + quantity + ", body=" + body + ", show_url="
				+ show_url + ", extra_common_param="
				+ extra_common_param + ", business_scene=" + business_scene
				+ "]";
	}
	 
	
	
	
	
}
