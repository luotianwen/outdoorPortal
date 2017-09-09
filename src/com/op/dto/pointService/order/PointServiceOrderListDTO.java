package com.op.dto.pointService.order;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PointServiceOrderListDTO {
	
	//订单编号
	private String pso_id;
	//项目ID
	private String pso_project_id;
	//项目名称
	private String pso_project_name;
	//有效期start
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date pso_validity_date_start;
	//有效期end
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date pso_validity_date_end;
	//商家信息
	private String pso_point_service_id;
	//场馆中文名
	private String pso_zh_name;
	//数量
	private String pso_project_num;
	// 项目打折价
	private double pso_discount_price;
	//总价
	private String pso_price;
	//订单状态（10：待消费；20：已使用）
	private String pso_order_state;
	//退款状态(0：未申请退款；10：等待玩嘛确认，20：退款中，30：退款成功，40：退款失败，50：取消退款)
	private String pso_refund_state;
	// 退款金额
	private double pso_refund_amount;
	//已退款金额
	private double pso_already_refund;
	//支付状态（10：已支付；20：未支付；）
	private String pso_pay_state;
	//创建人
	private String pso_create_user_id;
 	//创建时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date pso_create_time;
	
	//项目图片
	private String pointProjectsImg;
	
	//退款中数量
	private String refunding;
	//退款成功数量
	private String refundSuccess;
	
	/**
	 * 订单编号
	 */
	public String getPso_id() {
		return pso_id;
	}
	/**
	 * 订单编号
	 */
	public void setPso_id(String pso_id) {
		this.pso_id = pso_id;
	}
	
	/**
	 * 项目ID
	 */
	public String getPso_project_id() {
		return pso_project_id;
	}
	/**
	 * 项目ID
	 */
	public void setPso_project_id(String pso_project_id) {
		this.pso_project_id = pso_project_id;
	}
	
	/**
	 * 项目名称
	 */
	public String getPso_project_name() {
		return pso_project_name;
	}
	/**
	 * 项目名称
	 */
	public void setPso_project_name(String pso_project_name) {
		this.pso_project_name = pso_project_name;
	}
	
	/**
	 * 有效期start
	 */
	public Date getPso_validity_date_start() {
		return pso_validity_date_start;
	}
	/**
	 * 有效期start
	 */
	public void setPso_validity_date_start(Date pso_validity_date_start) {
		this.pso_validity_date_start = pso_validity_date_start;
	}
	
	/**
	 * 有效期end
	 */
	public Date getPso_validity_date_end() {
		return pso_validity_date_end;
	}
	/**
	 * 有效期end
	 */
	public void setPso_validity_date_end(Date pso_validity_date_end) {
		this.pso_validity_date_end = pso_validity_date_end;
	}
	
	/**
	 * 商家ID
	 */
	public String getPso_point_service_id() {
		return pso_point_service_id;
	}
	
	/**
	 * 商家ID
	 */
	public void setPso_point_service_id(String pso_point_service_id) {
		this.pso_point_service_id = pso_point_service_id;
	}
	
	/**
	 * 数量
	 */
	public String getPso_project_num() {
		return pso_project_num;
	}
	/**
	 * 数量
	 */
	public void setPso_project_num(String pso_project_num) {
		this.pso_project_num = pso_project_num;
	}
	
	/**
	 * 项目折扣价
	 * @return
	 */
	public double getPso_discount_price() {
		return pso_discount_price;
	}
	/**
	 * 项目折扣价
	 * @return
	 */
	public void setPso_discount_price(double pso_discount_price) {
		this.pso_discount_price = pso_discount_price;
	}
	
	/**
	 * 价格
	 */
	public String getPso_price() {
		return pso_price;
	}
	/**
	 * 价格
	 */
	public void setPso_price(String pso_price) {
		this.pso_price = pso_price;
	}
	
	/**
	 * 订单状态
	 */
	public String getPso_order_state() {
		return pso_order_state;
	}
	/**
	 * 订单状态
	 */
	public void setPso_order_state(String pso_order_state) {
		this.pso_order_state = pso_order_state;
	}
	
	/**
	 * 退款状态
	 */
	public String getPso_refund_state() {
		return pso_refund_state;
	}
	/**
	 * 退款状态
	 */
	public void setPso_refund_state(String pso_refund_state) {
		this.pso_refund_state = pso_refund_state;
	}
	
	/**
	 * 退款金额
	 * @return
	 */
	public double getPso_refund_amount() {
		return pso_refund_amount;
	}
	/**
	 * 退款金额
	 * @return
	 */
	public void setPso_refund_amount(double pso_refund_amount) {
		this.pso_refund_amount = pso_refund_amount;
	}
	
	/**
	 * 已退款金额
	 * @return
	 */
	public double getPso_already_refund() {
		return pso_already_refund;
	}
	/**
	 * 已退款金额
	 * @return
	 */
	public void setPso_already_refund(double pso_already_refund) {
		this.pso_already_refund = pso_already_refund;
	}
	
	/**
	 * 支付状态（10：已支付；20：未支付；）
	 */
	public String getPso_pay_state() {
		return pso_pay_state;
	}
	/**
	 * 支付状态（10：已支付；20：未支付；）
	 */
	public void setPso_pay_state(String pso_pay_state) {
		this.pso_pay_state = pso_pay_state;
	}
	
	/**
	 * 创建人
	 */
	public String getPso_create_user_id() {
		return pso_create_user_id;
	}
	/**
	 * 创建人
	 */
	public void setPso_create_user_id(String pso_create_user_id) {
		this.pso_create_user_id = pso_create_user_id;
	}
	
	/**
	 * 创建时间
	 */
	public Date getPso_create_time() {
		return pso_create_time;
	}
	/**
	 * 创建时间
	 */
	public void setPso_create_time(Date pso_create_time) {
		this.pso_create_time = pso_create_time;
	}
	
	/**
	 * 场馆中文名
	 * @return
	 */
	public String getPso_zh_name() {
		return pso_zh_name;
	}
	/**
	 * 场馆中文名
	 * @return
	 */
	public void setPso_zh_name(String pso_zh_name) {
		this.pso_zh_name = pso_zh_name;
	}
	
	/**
	 * 项目图片
	 * @return
	 */
	public String getPointProjectsImg() {
		return pointProjectsImg;
	}
	/**
	 * 项目图片
	 * @return
	 */
	public void setPointProjectsImg(String pointProjectsImg) {
		this.pointProjectsImg = pointProjectsImg;
	}
	
	/**
	 * 退款中数量
	 * @param refunding
	 */
	public String getRefunding() {
		return refunding;
	}
	/**
	 * 退款中数量
	 * @param refunding
	 */
	public void setRefunding(String refunding) {
		this.refunding = refunding;
	}
	
	/**
	 * 退款成功数量
	 * @param refunding
	 */
	public String getRefundSuccess() {
		return refundSuccess;
	}
	/**
	 * 退款成功数量
	 * @param refunding
	 */
	public void setRefundSuccess(String refundSuccess) {
		this.refundSuccess = refundSuccess;
	}
	
	
}
