package com.op.dto.usercenter.leader.activeManager.activeRefund;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * 项目名：outdoorPortal
 * 类描述：退款订单查询对象
 * 创建人：Yan
 * 创建时间： 2016-1-6 上午11:21:21
 * 最后修改时间：2016-1-6上午11:21:21
 */
public class LeaderSearchActiveRefundDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 创建用户
	private String createUser;
	
	// 订单编号
	String asu_order_id;
	
	// 退款状态
	int asu_refund_state;
	
	// 申请退款起始时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	Date asu_refund_time_start;
	
	// 申请退款终止时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	Date asu_refund_time_end;
	
	// 报名起始时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	Date asu_create_time_start;
	
	// 报名终止时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	Date asu_create_time_end;
	
	
	/*-------------------------------------		get	&& set------------------------------*/
	
	public String getCreateUser() {
		return createUser;
	}
	public String getAsu_order_id() {
		return asu_order_id;
	}
	public void setAsu_order_id(String asu_order_id) {
		this.asu_order_id = asu_order_id;
	}
	public int getAsu_refund_state() {
		return asu_refund_state;
	}
	public void setAsu_refund_state(int asu_refund_state) {
		this.asu_refund_state = asu_refund_state;
	}
	public Date getAsu_refund_time_start() {
		return asu_refund_time_start;
	}
	public void setAsu_refund_time_start(Date asu_refund_time_start) {
		this.asu_refund_time_start = asu_refund_time_start;
	}
	public Date getAsu_refund_time_end() {
		return asu_refund_time_end;
	}
	public void setAsu_refund_time_end(Date asu_refund_time_end) {
		this.asu_refund_time_end = asu_refund_time_end;
	}
	public Date getAsu_create_time_start() {
		return asu_create_time_start;
	}
	public void setAsu_create_time_start(Date asu_create_time_start) {
		this.asu_create_time_start = asu_create_time_start;
	}
	public Date getAsu_create_time_end() {
		return asu_create_time_end;
	}
	public void setAsu_create_time_end(Date asu_create_time_end) {
		this.asu_create_time_end = asu_create_time_end;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	
}
