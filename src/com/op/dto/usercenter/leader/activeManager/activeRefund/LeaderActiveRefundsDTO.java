package com.op.dto.usercenter.leader.activeManager.activeRefund;

import java.io.Serializable;
import java.util.Date;

import com.op.util.ZdGetValue;

/**
 * 项目名：outdoorPortal
 * 类描述：领队查询退款订单
 * 创建人：Yan
 * 创建时间： 2016-1-6 上午11:21:29
 * 最后修改时间：2016-1-6上午11:21:29
 */
public class LeaderActiveRefundsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 活动ID
	private int asu_active_id;
	// 活动标题
	private String title;
	// 报名订单ID
	private String asu_id;
	// 用户订单ID
	private String asu_order_id;
	// 付款类型（10：全款；20：预付款；30：免费；）
	private String asu_price_type;
	// 已付款总额(元)
	private double asu_account_paid;
	// 退款状态
	private String asu_refund_state;
	// 退款总额
	private double asu_refund_price;
	// 申请退款时间
	private Date asu_refund_time;
	// 报名时间
	private Date asu_create_time;
	// 付款时间
	private Date asu_pay_time;
	// 领队退款备注
	private String asu_leader_refund_area;
	// 退款时间（后台操作退款生成的时间）
	private Date asu_refund_date;
	

	/*-------------------------------------		get	&& set------------------------------*/
	
	
	public int getAsu_active_id() {
		return asu_active_id;
	}

	public Date getAsu_refund_date() {
		return asu_refund_date;
	}

	public void setAsu_refund_date(Date asu_refund_date) {
		this.asu_refund_date = asu_refund_date;
	}

	public String getAsu_leader_refund_area() {
		return asu_leader_refund_area;
	}

	public void setAsu_leader_refund_area(String asu_leader_refund_area) {
		this.asu_leader_refund_area = asu_leader_refund_area;
	}

	public String getAsu_order_id() {
		return asu_order_id;
	}

	public void setAsu_order_id(String asu_order_id) {
		this.asu_order_id = asu_order_id;
	}

	public Date getAsu_pay_time() {
		return asu_pay_time;
	}

	public void setAsu_pay_time(Date asu_pay_time) {
		this.asu_pay_time = asu_pay_time;
	}

	public void setAsu_active_id(int asu_active_id) {
		this.asu_active_id = asu_active_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAsu_id() {
		return asu_id;
	}

	public void setAsu_id(String asu_id) {
		this.asu_id = asu_id;
	}

	public String getAsu_price_type() {
		return asu_price_type;
	}

	public void setAsu_price_type(String asu_price_type) {
		this.asu_price_type = ZdGetValue.getTableColumnKeyVal("activeSignup", "asu_price_type", asu_price_type);
	}

	public double getAsu_account_paid() {
		return asu_account_paid;
	}

	public void setAsu_account_paid(double asu_account_paid) {
		this.asu_account_paid = asu_account_paid;
	}

	public String getAsu_refund_state() {
		return asu_refund_state;
	}

	public void setAsu_refund_state(String asu_refund_state) {
		this.asu_refund_state = ZdGetValue.getTableColumnKeyVal("activeSignup", "asu_refund_state", asu_refund_state);
	}

	public double getAsu_refund_price() {
		return asu_refund_price;
	}

	public void setAsu_refund_price(double asu_refund_price) {
		this.asu_refund_price = asu_refund_price;
	}

	public Date getAsu_refund_time() {
		return asu_refund_time;
	}

	public void setAsu_refund_time(Date asu_refund_time) {
		this.asu_refund_time = asu_refund_time;
	}

	public Date getAsu_create_time() {
		return asu_create_time;
	}

	public void setAsu_create_time(Date asu_create_time) {
		this.asu_create_time = asu_create_time;
	}

}
