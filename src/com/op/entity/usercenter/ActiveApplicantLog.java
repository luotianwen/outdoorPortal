package com.op.entity.usercenter;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 订单操作日志(ActiveApplicantLog)实体类
 * 
 * @author 世峰户外商城
 * @version Revision: 1.00 Date: 2016-04-12 14:06:58
 */
public class ActiveApplicantLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 操作日志ID
	private String aal_id;
	// 报名人ID
	private String aa_id;
	// 订单ID
	private String asu_id;
	// 活动ID
	private String activity_id;
	// 操作时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date aal_create_time;
	// 日志类型(0:付款状态；1：订单状态；2：用户取消原因；3：领队同意原因)
	private int aal_type;
	// 日志备注
	private String aal_remark;
	// 操作人
	private String user_Id;
	//报名状态、退款状态
	private String orderState;
	
	/**
	 * 操作日志ID
	 */
	public String getAal_id() {
		return aal_id;
	}
	/**
	 * 操作日志ID
	 */
	public void setAal_id(String aal_id) {
		this.aal_id = aal_id;
	}

	/**
	 * 报名人ID
	 */
	public String getAa_id() {
		return aa_id;
	}
	/**
	 * 报名人ID
	 */
	public void setAa_id(String aa_id) {
		this.aa_id = aa_id;
	}

	/**
	 * 订单ID
	 */
	public String getAsu_id() {
		return asu_id;
	}
	/**
	 * 订单ID
	 */
	public void setAsu_id(String asu_id) {
		this.asu_id = asu_id;
	}

	/**
	 * 活动ID
	 */
	public String getActivity_id() {
		return activity_id;
	}
	/**
	 * 活动ID
	 */
	public void setActivity_id(String activity_id) {
		this.activity_id = activity_id;
	}

	/**
	 * 记录时间
	 */
	public Date getAal_create_time() {
		return aal_create_time;
	}
	/**
	 * 记录时间
	 */
	public void setAal_create_time(Date aal_create_time) {
		this.aal_create_time = aal_create_time;
	}

	/**
	 * 日志类型(0:付款状态；1：订单状态；2：用户取消原因；3：领队同意原因)
	 */
	public int getAal_type() {
		return aal_type;
	}
	/**
	 * 日志类型(0:付款状态；1：订单状态；2：用户取消原因；3：领队同意原因)
	 */
	public void setAal_type(int aal_type) {
		this.aal_type = aal_type;
	}

	/**
	 * 日志备注
	 */
	public String getAal_remark() {
		return aal_remark;
	}
	/**
	 * 日志备注
	 */
	public void setAal_remark(String aal_remark) {
		this.aal_remark = aal_remark;
	}
	
	/**
	 * 操作人
	 */
	public String getUser_Id() {
		return user_Id;
	}
	/**
	 * 操作人
	 */
	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}

	/**
	 * 报名状态、退款状态
	 */
	public String getOrderState() {
		return orderState;
	}
	/**
	 * 报名状态、退款状态
	 */
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

}
