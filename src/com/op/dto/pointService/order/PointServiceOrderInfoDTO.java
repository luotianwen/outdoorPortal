package com.op.dto.pointService.order;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.op.entity.pointService.order.PointServiceUniqueness;

public class PointServiceOrderInfoDTO {
	// 订单序号
	private String pso_id;
	// 项目ID
	private String pso_project_id;
	//项目快照ID
	private String pso_snapshot_id;
	// 项目名称
	private String pso_project_name;
	// 项目单价
	private double pso_project_price;
	// 项目打折价
	private double pso_discount_price;
	// 项目数量
	private String pso_project_num;
	//有效期start
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date pso_validity_date_start;
	//有效期end
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date pso_validity_date_end;
	// 订单总金额
	private double pso_price;
	//项目图片
	private String pointProjectsImg;
	// 地点服务ID
	private String pso_point_service_id;
	//场馆中文名
	private String pso_zh_name;
	// 用户预约时间start
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date pso_appointment_time_start;
	// 用户预约时间end
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date pso_appointment_time_end;
	// 用户备注
	private String pso_user_comment;
	// 订单状态（10：待消费；20：已使用）
	private int pso_order_state;
	// 商家未接单备注
	private String pso_no_consent_comment;
	// 支付状态（10：未支付；20：已支付；）
	private int pso_pay_state;
	// 创建人
	private String pso_create_user_id;
	// 创建时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date pso_create_time;
	// 支付时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date pso_pay_time;
	// 支付方式
	private int pso_pay_type;
	// 支付流水号
	private String pso_pay_sequence;
	// 手机号
	private String pso_phone;
	// 姓名
	private String pso_user_name;
	// 是否删除（0：否；1：是）
	private int pso_is_del;
	// 退款时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date pso_refund_time;
	// 退款金额
	private double pso_refund_amount;
	// 退款方式
	private int pso_refund_mode;
	// 退款状态(0：未申请退款；10：等待玩嘛确认，20：退款中，30：退款成功，40：退款失败，50：取消退款)
	private int pso_refund_state;
	// 退款原因
	private String pso_refund_reason;
	// 已退金额
	private double pso_already_refund;
	
	//退款中数量
	private String refunding;
	//退款成功数量
	private String refundSuccess;
	
	
	//识别码
	List<PointServiceUniqueness> pointServiceUniquenessList;

	public String getPso_id() {
		return pso_id;
	}

	public void setPso_id(String pso_id) {
		this.pso_id = pso_id;
	}

	public String getPso_project_id() {
		return pso_project_id;
	}

	public void setPso_project_id(String pso_project_id) {
		this.pso_project_id = pso_project_id;
	}

	public String getPso_project_name() {
		return pso_project_name;
	}

	public void setPso_project_name(String pso_project_name) {
		this.pso_project_name = pso_project_name;
	}

	public double getPso_project_price() {
		return pso_project_price;
	}

	public void setPso_project_price(double pso_project_price) {
		this.pso_project_price = pso_project_price;
	}

	public double getPso_discount_price() {
		return pso_discount_price;
	}

	public void setPso_discount_price(double pso_discount_price) {
		this.pso_discount_price = pso_discount_price;
	}

	public String getPso_project_num() {
		return pso_project_num;
	}

	public void setPso_project_num(String pso_project_num) {
		this.pso_project_num = pso_project_num;
	}

	public Date getPso_validity_date_start() {
		return pso_validity_date_start;
	}

	public void setPso_validity_date_start(Date pso_validity_date_start) {
		this.pso_validity_date_start = pso_validity_date_start;
	}

	public Date getPso_validity_date_end() {
		return pso_validity_date_end;
	}

	public void setPso_validity_date_end(Date pso_validity_date_end) {
		this.pso_validity_date_end = pso_validity_date_end;
	}

	public double getPso_price() {
		return pso_price;
	}

	public void setPso_price(double pso_price) {
		this.pso_price = pso_price;
	}

	public String getPointProjectsImg() {
		return pointProjectsImg;
	}

	public void setPointProjectsImg(String pointProjectsImg) {
		this.pointProjectsImg = pointProjectsImg;
	}

	public String getPso_point_service_id() {
		return pso_point_service_id;
	}

	public void setPso_point_service_id(String pso_point_service_id) {
		this.pso_point_service_id = pso_point_service_id;
	}

	public String getPso_zh_name() {
		return pso_zh_name;
	}

	public void setPso_zh_name(String pso_zh_name) {
		this.pso_zh_name = pso_zh_name;
	}

	public Date getPso_appointment_time_start() {
		return pso_appointment_time_start;
	}

	public void setPso_appointment_time_start(Date pso_appointment_time_start) {
		this.pso_appointment_time_start = pso_appointment_time_start;
	}

	public Date getPso_appointment_time_end() {
		return pso_appointment_time_end;
	}

	public void setPso_appointment_time_end(Date pso_appointment_time_end) {
		this.pso_appointment_time_end = pso_appointment_time_end;
	}

	public String getPso_user_comment() {
		return pso_user_comment;
	}

	public void setPso_user_comment(String pso_user_comment) {
		this.pso_user_comment = pso_user_comment;
	}

	public int getPso_order_state() {
		return pso_order_state;
	}

	public void setPso_order_state(int pso_order_state) {
		this.pso_order_state = pso_order_state;
	}

	public String getPso_no_consent_comment() {
		return pso_no_consent_comment;
	}

	public void setPso_no_consent_comment(String pso_no_consent_comment) {
		this.pso_no_consent_comment = pso_no_consent_comment;
	}

	public int getPso_pay_state() {
		return pso_pay_state;
	}

	public void setPso_pay_state(int pso_pay_state) {
		this.pso_pay_state = pso_pay_state;
	}

	public String getPso_create_user_id() {
		return pso_create_user_id;
	}

	public void setPso_create_user_id(String pso_create_user_id) {
		this.pso_create_user_id = pso_create_user_id;
	}

	public Date getPso_create_time() {
		return pso_create_time;
	}

	public void setPso_create_time(Date pso_create_time) {
		this.pso_create_time = pso_create_time;
	}

	public Date getPso_pay_time() {
		return pso_pay_time;
	}

	public void setPso_pay_time(Date pso_pay_time) {
		this.pso_pay_time = pso_pay_time;
	}

	public int getPso_pay_type() {
		return pso_pay_type;
	}

	public void setPso_pay_type(int pso_pay_type) {
		this.pso_pay_type = pso_pay_type;
	}

	public String getPso_pay_sequence() {
		return pso_pay_sequence;
	}

	public void setPso_pay_sequence(String pso_pay_sequence) {
		this.pso_pay_sequence = pso_pay_sequence;
	}

	public String getPso_phone() {
		return pso_phone;
	}

	public void setPso_phone(String pso_phone) {
		this.pso_phone = pso_phone;
	}

	public String getPso_user_name() {
		return pso_user_name;
	}

	public void setPso_user_name(String pso_user_name) {
		this.pso_user_name = pso_user_name;
	}

	public int getPso_is_del() {
		return pso_is_del;
	}

	public void setPso_is_del(int pso_is_del) {
		this.pso_is_del = pso_is_del;
	}

	public Date getPso_refund_time() {
		return pso_refund_time;
	}

	public void setPso_refund_time(Date pso_refund_time) {
		this.pso_refund_time = pso_refund_time;
	}

	public double getPso_refund_amount() {
		return pso_refund_amount;
	}

	public void setPso_refund_amount(double pso_refund_amount) {
		this.pso_refund_amount = pso_refund_amount;
	}

	public int getPso_refund_mode() {
		return pso_refund_mode;
	}

	public void setPso_refund_mode(int pso_refund_mode) {
		this.pso_refund_mode = pso_refund_mode;
	}

	public int getPso_refund_state() {
		return pso_refund_state;
	}

	public void setPso_refund_state(int pso_refund_state) {
		this.pso_refund_state = pso_refund_state;
	}

	public String getPso_refund_reason() {
		return pso_refund_reason;
	}

	public void setPso_refund_reason(String pso_refund_reason) {
		this.pso_refund_reason = pso_refund_reason;
	}

	public double getPso_already_refund() {
		return pso_already_refund;
	}

	public void setPso_already_refund(double pso_already_refund) {
		this.pso_already_refund = pso_already_refund;
	}

	public List<PointServiceUniqueness> getPointServiceUniquenessList() {
		return pointServiceUniquenessList;
	}

	public void setPointServiceUniquenessList(
			List<PointServiceUniqueness> pointServiceUniquenessList) {
		this.pointServiceUniquenessList = pointServiceUniquenessList;
	}

	public String getRefunding() {
		return refunding;
	}

	public void setRefunding(String refunding) {
		this.refunding = refunding;
	}

	public String getRefundSuccess() {
		return refundSuccess;
	}

	public void setRefundSuccess(String refundSuccess) {
		this.refundSuccess = refundSuccess;
	}

	public String getPso_snapshot_id() {
		return pso_snapshot_id;
	}

	public void setPso_snapshot_id(String pso_snapshot_id) {
		this.pso_snapshot_id = pso_snapshot_id;
	}
	
	
}
