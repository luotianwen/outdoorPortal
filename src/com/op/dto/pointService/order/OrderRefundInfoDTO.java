package com.op.dto.pointService.order;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.op.entity.pointService.order.PointServiceRefundReason;
import com.op.entity.pointService.order.PointServiceUniqueness;

public class OrderRefundInfoDTO {
	// 订单序号
	private String pso_id;
	// 项目ID
	private String pso_project_id;
	// 项目名称
	private String pso_project_name;
	// 项目打折价
	private double pso_discount_price;
	// 订单总金额
	private double pso_price;
	
	// 地点服务ID
	private String pso_point_service_id;
	//场馆中文名
	private String pso_zh_name;
	//交易流水号
	private String pso_pay_sequence;
	
	//申请退款ID
	private String psoar_id;
	//识别码ID
	private String psu_id;
	//退款时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date psoar_refund_time;
	//申请退款时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date psoar_refund_apply_time;
	//退款到账时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date psoar_refund_success_time;
	//退款个数
	private int psoar_refund_number;
	//申请退款状态(10：等待玩嘛确认，20：退款中，30：退款成功，40：退款失败，50：取消退款)
	private int psoar_state;
	
	
	//识别码
	private List<PointServiceUniqueness> pointServiceUniquenessList;
	
	//退款原因
	private List<PointServiceRefundReason> pointServiceRefundReasonList;

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

	public double getPso_discount_price() {
		return pso_discount_price;
	}

	public void setPso_discount_price(double pso_discount_price) {
		this.pso_discount_price = pso_discount_price;
	}

	public double getPso_price() {
		return pso_price;
	}

	public void setPso_price(double pso_price) {
		this.pso_price = pso_price;
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

	public List<PointServiceUniqueness> getPointServiceUniquenessList() {
		return pointServiceUniquenessList;
	}

	public void setPointServiceUniquenessList(
			List<PointServiceUniqueness> pointServiceUniquenessList) {
		this.pointServiceUniquenessList = pointServiceUniquenessList;
	}

	public String getPsu_id() {
		return psu_id;
	}

	public void setPsu_id(String psu_id) {
		this.psu_id = psu_id;
	}

	public List<PointServiceRefundReason> getPointServiceRefundReasonList() {
		return pointServiceRefundReasonList;
	}

	public void setPointServiceRefundReasonList(
			List<PointServiceRefundReason> pointServiceRefundReasonList) {
		this.pointServiceRefundReasonList = pointServiceRefundReasonList;
	}

	public Date getPsoar_refund_time() {
		return psoar_refund_time;
	}

	public void setPsoar_refund_time(Date psoar_refund_time) {
		this.psoar_refund_time = psoar_refund_time;
	}

	public Date getPsoar_refund_apply_time() {
		return psoar_refund_apply_time;
	}

	public void setPsoar_refund_apply_time(Date psoar_refund_apply_time) {
		this.psoar_refund_apply_time = psoar_refund_apply_time;
	}

	public Date getPsoar_refund_success_time() {
		return psoar_refund_success_time;
	}

	public void setPsoar_refund_success_time(Date psoar_refund_success_time) {
		this.psoar_refund_success_time = psoar_refund_success_time;
	}

	public String getPso_pay_sequence() {
		return pso_pay_sequence;
	}

	public void setPso_pay_sequence(String pso_pay_sequence) {
		this.pso_pay_sequence = pso_pay_sequence;
	}

	public int getPsoar_refund_number() {
		return psoar_refund_number;
	}

	public void setPsoar_refund_number(int psoar_refund_number) {
		this.psoar_refund_number = psoar_refund_number;
	}

	public int getPsoar_state() {
		return psoar_state;
	}

	public void setPsoar_state(int psoar_state) {
		this.psoar_state = psoar_state;
	}

	public String getPsoar_id() {
		return psoar_id;
	}

	public void setPsoar_id(String psoar_id) {
		this.psoar_id = psoar_id;
	}
	
}
