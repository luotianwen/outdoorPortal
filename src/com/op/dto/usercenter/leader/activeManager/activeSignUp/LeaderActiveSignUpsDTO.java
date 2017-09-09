package com.op.dto.usercenter.leader.activeManager.activeSignUp;

import java.io.Serializable;
import java.util.Date;

import com.op.util.ZdGetValue;

/**
 * 项目名：outdoorPortal
 * 类描述：领队查询活动报名详情DTO
 * 创建人：Yan
 * 创建时间： 2016-1-5 上午10:25:50
 * 最后修改时间：2016-1-5上午10:25:50
 */
public class LeaderActiveSignUpsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 报名订单ID
	private String asu_id;
	// 参加人员姓名
	private String aa_user_name;
	// 参加人员性别
	private String aa_user_sex;
	// 报名联系人手机号码
	private String asu_link_user_phone;
	// 订单备注
	private String asu_order_area;
	// 活动ID
	private int asu_active_id;
	// 报名状态
	private String asu_state;
	// 已付款总额
	private double asu_account_paid;
	// 支付类型
	private String asu_pay_state;
	// 付款类型
	private String asu_price_type;
	// 报名时间
	private Date asu_create_time;
	// 活动名称
	private String title;

	/*--------------------------------------------------	get  &&	set--------------------------------*/
	
	
	public String getAsu_id() {
		return asu_id;
	}

	public void setAsu_id(String asu_id) {
		this.asu_id = asu_id;
	}

	public String getAa_user_name() {
		return aa_user_name;
	}

	public void setAa_user_name(String aa_user_name) {
		this.aa_user_name = aa_user_name;
	}

	public String getAa_user_sex() {
		return aa_user_sex;
	}

	public void setAa_user_sex(int aa_user_sex) {
		this.aa_user_sex = ZdGetValue.getTableColumnKeyVal("activeApplicant", "aa_user_sex", aa_user_sex+"");
	}

	public String getAsu_link_user_phone() {
		return asu_link_user_phone;
	}

	public void setAsu_link_user_phone(String asu_link_user_phone) {
		this.asu_link_user_phone = asu_link_user_phone;
	}

	public String getAsu_order_area() {
		return asu_order_area;
	}

	public void setAsu_order_area(String asu_order_area) {
		this.asu_order_area = asu_order_area;
	}

	public int getAsu_active_id() {
		return asu_active_id;
	}

	public void setAsu_active_id(int asu_active_id) {
		this.asu_active_id = asu_active_id;
	}

	public String getAsu_state() {
		return asu_state;
	}

	public void setAsu_state(int asu_state) {
		
		this.asu_state = ZdGetValue.getTableColumnKeyVal("activeSignup", "asu_state", asu_state+"");
	}

	public double getAsu_account_paid() {
		return asu_account_paid;
	}

	public void setAsu_account_paid(double asu_account_paid) {
		this.asu_account_paid = asu_account_paid;
	}

	public String getAsu_pay_state() {
		return asu_pay_state;
	}

	public void setAsu_pay_state(int asu_pay_state) {
		this.asu_pay_state = ZdGetValue.getTableColumnKeyVal("activeSignup", "asu_pay_state", asu_pay_state+"");
	}

	public String getAsu_price_type() {
		return asu_price_type;
	}

	public void setAsu_price_type(int asu_price_type) {
		this.asu_price_type = ZdGetValue.getTableColumnKeyVal("activeSignup", "asu_price_type", asu_price_type+"");
	}

	public Date getAsu_create_time() {
		return asu_create_time;
	}

	public void setAsu_create_time(Date asu_create_time) {
		this.asu_create_time = asu_create_time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
