package com.op.entity.log.order;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单日志表(orderLog)实体类
 * 
 * @author 世峰户外商城
 * @version Revision: 1.00 Date: 2016-01-07 17:36:37
 */
public class ActiveOrderLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// id
	private int ol_id;
	// 处理时间
	private Date ol_create_time;
	// 处理信息
	private String ol_info;
	// 操作人ID
	private String ol_create_user_id;
	// 操作人姓名
	private String ol_create_user_name;
	// 订单ID
	private String ol_order_id;

	/**
	 * id
	 */
	public int getOl_id() {
		return ol_id;
	}

	public void setOl_id(int ol_id) {
		this.ol_id = ol_id;
	}

	/**
	 * 处理时间
	 */
	public Date getOl_create_time() {
		return ol_create_time;
	}

	public void setOl_create_time(Date ol_create_time) {
		this.ol_create_time = ol_create_time;
	}

	/**
	 * 处理信息
	 */
	public String getOl_info() {
		return ol_info;
	}

	public void setOl_info(String ol_info) {
		this.ol_info = ol_info;
	}

	/**
	 * 操作人ID
	 */
	public String getOl_create_user_id() {
		return ol_create_user_id;
	}

	public void setOl_create_user_id(String ol_create_user_id) {
		this.ol_create_user_id = ol_create_user_id;
	}

	/**
	 * 操作人姓名
	 */
	public String getOl_create_user_name() {
		return ol_create_user_name;
	}

	public void setOl_create_user_name(String ol_create_user_name) {
		this.ol_create_user_name = ol_create_user_name;
	}

	public String getOl_order_id() {
		return ol_order_id;
	}

	public void setOl_order_id(String ol_order_id) {
		this.ol_order_id = ol_order_id;
	}

}
