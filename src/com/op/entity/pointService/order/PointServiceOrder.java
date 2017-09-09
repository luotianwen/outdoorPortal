package com.op.entity.pointService.order;

import java.io.Serializable;
import java.util.Date;
/** 
 * 场馆订单(pointServiceOrder)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-07-11 17:14:06 
 */  
public class PointServiceOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//订单序号
  	 private String pso_id;
 	//项目ID
  	 private String pso_project_id;
  	 //快照项目ID
  	 private String pso_snapshot_id;
 	//项目名称
  	 private String pso_project_name;
 	//项目单价
  	 private double pso_project_price;
  	 //项目打折价
  	 private double pso_discount_price;
 	//项目数量
  	 private String pso_project_num;
 	//有效期start
  	 private Date pso_validity_date_start;
 	//有效期end
  	 private Date pso_validity_date_end;
 	//订单总金额
  	 private double pso_price;
 	//地点服务ID
  	 private String pso_point_service_id;
 	//场馆中文名
  	 private String pso_zh_name;
 	//用户预约时间start
  	 private Date pso_appointment_time_start;
 	//用户预约时间end
  	 private Date pso_appointment_time_end;
 	//用户备注
  	 private String pso_user_comment;
 	//订单状态（10：待消费；20：已使用）
  	 private String pso_order_state;
 	//商家未接单备注
  	 private String pso_no_consent_comment;
 	//支付状态（10：未支付；20：已支付；）
  	 private String pso_pay_state;
 	//创建人
  	 private String pso_create_user_id;
 	//创建时间
  	 private Date pso_create_time;
 	//支付时间
  	 private Date pso_pay_time;
 	//支付方式
  	 private String pso_pay_type;
 	//支付流水号
  	 private String pso_pay_sequence;
 	//手机号
  	 private String pso_phone;
 	//姓名
  	 private String pso_user_name;
 	//是否删除（0：否；1：是）
  	 private String pso_is_del;
 	//退款时间
  	 private Date pso_refund_time;
 	//退款金额
  	 private double pso_refund_amount;
 	//退款方式
  	 private String pso_refund_mode;
 	//退款状态(0：未申请退款；10：等待玩嘛确认，20：退款中，30：退款成功，40：退款失败，50：取消退款)
  	 private String pso_refund_state;
 	//退款原因
  	 private String pso_refund_reason;
 	//已退金额
  	 private double pso_already_refund;
  	 //是否评论（0：未评论；1：已评论）
  	 private int pso_comment;


	 
    /**
    *订单序号
	* @return
    */ 
	public String getPso_id() {
		return pso_id;
	}
    /**
    *订单序号
	* @param type
    */ 
	public void setPso_id(String pso_id) {
		this.pso_id = pso_id;
	}
    /**
    *项目ID
	* @return
    */ 
	public String getPso_project_id() {
		return pso_project_id;
	}
    /**
    *项目ID
	* @param type
    */ 
	public void setPso_project_id(String pso_project_id) {
		this.pso_project_id = pso_project_id;
	}
	/**
	 * 快照项目ID
	 * @return
	 */
    public String getPso_snapshot_id() {
		return pso_snapshot_id;
	}
    /**
	 * 快照项目ID
	 * @return
	 */
	public void setPso_snapshot_id(String pso_snapshot_id) {
		this.pso_snapshot_id = pso_snapshot_id;
	}
	/**
    *项目名称
	* @return
    */ 
	public String getPso_project_name() {
		return pso_project_name;
	}
    /**
    *项目名称
	* @param type
    */ 
	public void setPso_project_name(String pso_project_name) {
		this.pso_project_name = pso_project_name;
	}
    /**
    *项目单价
	* @return
    */ 
	public double getPso_project_price() {
		return pso_project_price;
	}
    /**
    *项目单价
	* @param type
    */ 
	public void setPso_project_price(double pso_project_price) {
		this.pso_project_price = pso_project_price;
	}
	
	/**
	 * 项目打折价
	 * @return
	 */
    public double getPso_discount_price() {
		return pso_discount_price;
	}
    
    /**
	 * 项目打折价
	 * @return
	 */
	public void setPso_discount_price(double pso_discount_price) {
		this.pso_discount_price = pso_discount_price;
	}
	
	/**
    *项目数量
	* @return
    */ 
	public String getPso_project_num() {
		return pso_project_num;
	}
    /**
    *项目数量
	* @param type
    */ 
	public void setPso_project_num(String pso_project_num) {
		this.pso_project_num = pso_project_num;
	}
    /**
    *有效期start
	* @return
    */ 
	public Date getPso_validity_date_start() {
		return pso_validity_date_start;
	}
    /**
    *有效期start
	* @param type
    */ 
	public void setPso_validity_date_start(Date pso_validity_date_start) {
		this.pso_validity_date_start = pso_validity_date_start;
	}
    /**
    *有效期end
	* @return
    */ 
	public Date getPso_validity_date_end() {
		return pso_validity_date_end;
	}
    /**
    *有效期end
	* @param type
    */ 
	public void setPso_validity_date_end(Date pso_validity_date_end) {
		this.pso_validity_date_end = pso_validity_date_end;
	}
    /**
    *订单总金额
	* @return
    */ 
	public double getPso_price() {
		return pso_price;
	}
    /**
    *订单总金额
	* @param type
    */ 
	public void setPso_price(double pso_price) {
		this.pso_price = pso_price;
	}
    /**
    *地点服务ID
	* @return
    */ 
	public String getPso_point_service_id() {
		return pso_point_service_id;
	}
    /**
    *地点服务ID
	* @param type
    */ 
	public void setPso_point_service_id(String pso_point_service_id) {
		this.pso_point_service_id = pso_point_service_id;
	}
    /**
    *场馆中文名
	* @return
    */ 
	public String getPso_zh_name() {
		return pso_zh_name;
	}
    /**
    *场馆中文名
	* @param type
    */ 
	public void setPso_zh_name(String pso_zh_name) {
		this.pso_zh_name = pso_zh_name;
	}
    /**
    *用户预约时间start
	* @return
    */ 
	public Date getPso_appointment_time_start() {
		return pso_appointment_time_start;
	}
    /**
    *用户预约时间start
	* @param type
    */ 
	public void setPso_appointment_time_start(Date pso_appointment_time_start) {
		this.pso_appointment_time_start = pso_appointment_time_start;
	}
    /**
    *用户预约时间end
	* @return
    */ 
	public Date getPso_appointment_time_end() {
		return pso_appointment_time_end;
	}
    /**
    *用户预约时间end
	* @param type
    */ 
	public void setPso_appointment_time_end(Date pso_appointment_time_end) {
		this.pso_appointment_time_end = pso_appointment_time_end;
	}
    /**
    *用户备注
	* @return
    */ 
	public String getPso_user_comment() {
		return pso_user_comment;
	}
    /**
    *用户备注
	* @param type
    */ 
	public void setPso_user_comment(String pso_user_comment) {
		this.pso_user_comment = pso_user_comment;
	}
    /**
    *订单状态（10：待消费；20：已使用）
	* @return
    */ 
	public String getPso_order_state() {
		return pso_order_state;
	}
    /**
    *订单状态（10：待消费；20：已使用）
	* @param type
    */ 
	public void setPso_order_state(String pso_order_state) {
		this.pso_order_state = pso_order_state;
	}
    /**
    *商家未接单备注
	* @return
    */ 
	public String getPso_no_consent_comment() {
		return pso_no_consent_comment;
	}
    /**
    *商家未接单备注
	* @param type
    */ 
	public void setPso_no_consent_comment(String pso_no_consent_comment) {
		this.pso_no_consent_comment = pso_no_consent_comment;
	}
    /**
    *支付状态（10：未支付；20：已支付；）
	* @return
    */ 
	public String getPso_pay_state() {
		return pso_pay_state;
	}
    /**
    *支付状态（10：未支付；20：已支付；）
	* @param type
    */ 
	public void setPso_pay_state(String pso_pay_state) {
		this.pso_pay_state = pso_pay_state;
	}
    /**
    *创建人
	* @return
    */ 
	public String getPso_create_user_id() {
		return pso_create_user_id;
	}
    /**
    *创建人
	* @param type
    */ 
	public void setPso_create_user_id(String pso_create_user_id) {
		this.pso_create_user_id = pso_create_user_id;
	}
    /**
    *创建时间
	* @return
    */ 
	public Date getPso_create_time() {
		return pso_create_time;
	}
    /**
    *创建时间
	* @param type
    */ 
	public void setPso_create_time(Date pso_create_time) {
		this.pso_create_time = pso_create_time;
	}
    /**
    *支付时间
	* @return
    */ 
	public Date getPso_pay_time() {
		return pso_pay_time;
	}
    /**
    *支付时间
	* @param type
    */ 
	public void setPso_pay_time(Date pso_pay_time) {
		this.pso_pay_time = pso_pay_time;
	}
    /**
    *支付方式
	* @return
    */ 
	public String getPso_pay_type() {
		return pso_pay_type;
	}
    /**
    *支付方式
	* @param type
    */ 
	public void setPso_pay_type(String pso_pay_type) {
		this.pso_pay_type = pso_pay_type;
	}
    /**
    *支付流水号
	* @return
    */ 
	public String getPso_pay_sequence() {
		return pso_pay_sequence;
	}
    /**
    *支付流水号
	* @param type
    */ 
	public void setPso_pay_sequence(String pso_pay_sequence) {
		this.pso_pay_sequence = pso_pay_sequence;
	}
    /**
    *手机号
	* @return
    */ 
	public String getPso_phone() {
		return pso_phone;
	}
    /**
    *手机号
	* @param type
    */ 
	public void setPso_phone(String pso_phone) {
		this.pso_phone = pso_phone;
	}
    /**
    *姓名
	* @return
    */ 
	public String getPso_user_name() {
		return pso_user_name;
	}
    /**
    *姓名
	* @param type
    */ 
	public void setPso_user_name(String pso_user_name) {
		this.pso_user_name = pso_user_name;
	}
    /**
    *是否删除（0：否；1：是）
	* @return
    */ 
	public String getPso_is_del() {
		return pso_is_del;
	}
    /**
    *是否删除（0：否；1：是）
	* @param type
    */ 
	public void setPso_is_del(String pso_is_del) {
		this.pso_is_del = pso_is_del;
	}
    /**
    *退款时间
	* @return
    */ 
	public Date getPso_refund_time() {
		return pso_refund_time;
	}
    /**
    *退款时间
	* @param type
    */ 
	public void setPso_refund_time(Date pso_refund_time) {
		this.pso_refund_time = pso_refund_time;
	}
    /**
    *退款金额
	* @return
    */ 
	public double getPso_refund_amount() {
		return pso_refund_amount;
	}
    /**
    *退款金额
	* @param type
    */ 
	public void setPso_refund_amount(double pso_refund_amount) {
		this.pso_refund_amount = pso_refund_amount;
	}
    /**
    *退款方式
	* @return
    */ 
	public String getPso_refund_mode() {
		return pso_refund_mode;
	}
    /**
    *退款方式
	* @param type
    */ 
	public void setPso_refund_mode(String pso_refund_mode) {
		this.pso_refund_mode = pso_refund_mode;
	}
    /**
    *退款状态(0：未申请退款；10：等待玩嘛确认，20：退款中，30：退款成功，40：退款失败，50：取消退款)
	* @return
    */ 
	public String getPso_refund_state() {
		return pso_refund_state;
	}
    /**
    *退款状态(0：未申请退款；10：等待玩嘛确认，20：退款中，30：退款成功，40：退款失败，50：取消退款)
	* @param type
    */ 
	public void setPso_refund_state(String pso_refund_state) {
		this.pso_refund_state = pso_refund_state;
	}
    /**
    *退款原因
	* @return
    */ 
	public String getPso_refund_reason() {
		return pso_refund_reason;
	}
    /**
    *退款原因
	* @param type
    */ 
	public void setPso_refund_reason(String pso_refund_reason) {
		this.pso_refund_reason = pso_refund_reason;
	}
    /**
    *已退金额
	* @return
    */ 
	public double getPso_already_refund() {
		return pso_already_refund;
	}
    /**
    *已退金额
	* @param type
    */ 
	public void setPso_already_refund(double pso_already_refund) {
		this.pso_already_refund = pso_already_refund;
	}
	/**
	 * 是否评论（0：未评论；1：已评论）
	 * @return
	 */
	public int getPso_comment() {
		return pso_comment;
	}
	/**
	 * 是否评论（0：未评论；1：已评论）
	 * @param pso_comment
	 */
	public void setPso_comment(int pso_comment) {
		this.pso_comment = pso_comment;
	}
	
}
