package com.op.dto.activity.activeSignUpPay;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.op.entity.activity.cost.ActiveCorrelationCost;
import com.op.entity.usercenter.ActiveApplicant;

public class ActiveSignUpPay implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// 活动ID
	private int asu_active_id;
	// 需要人数
	private int needUserNum;
	// 已确定人数
	private int confirmUserNum;
	// 报名截止时间
	private Date a_enroll_end_time;
	// 活动状态（1：草稿；2：发布待审核；3：审核中；4：审核不通过；5：审核成功（发布））
	private int state;
	//活动标题
	private String title;
	//发布者ID
	private String createUser;
	//保险ID
	private String in_id;
	//是否强制保险（1：是；2：否）
	private String isinsurance;
	//活动开始时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date activityTime;
	//活动结束时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endTime;
	
	//订单ID
	private String asu_id;
	// 付款类型（10：全款；20：预付款；30：免费；）
	private int asu_price_type;
	// 支付状态（1：未支付；2：已支付；）
	private int asu_pay_state;
	//报名状态
	private int asu_state;
	// 是否已经删除(0：默认；1：是；)
	private int asu_is_delete;
	//付款总额(元)
	private double asu_account_paid;
	//应付款总额
	private double asu_account_payable;
	//预约款
	private double asu_reserve_price;
	//支付宝流水号
	private String asu_user_account_num;
	//创建人
	private String asu_user_id;
	
	//报名人集合
	private List<ActiveApplicant> activeApplicant;
	
	//保险信息
	private ActiveCorrelationCost insurance;
	
	public String getAsu_id() {
		return asu_id;
	}

	public void setAsu_id(String asu_id) {
		this.asu_id = asu_id;
	}

	public int getAsu_active_id() {
		return asu_active_id;
	}

	public void setAsu_active_id(int asu_active_id) {
		this.asu_active_id = asu_active_id;
	}

	public int getAsu_price_type() {
		return asu_price_type;
	}

	public void setAsu_price_type(int asu_price_type) {
		this.asu_price_type = asu_price_type;
	}

	public int getAsu_pay_state() {
		return asu_pay_state;
	}

	public void setAsu_pay_state(int asu_pay_state) {
		this.asu_pay_state = asu_pay_state;
	}

	public int getAsu_is_delete() {
		return asu_is_delete;
	}

	public void setAsu_is_delete(int asu_is_delete) {
		this.asu_is_delete = asu_is_delete;
	}

	public int getNeedUserNum() {
		return needUserNum;
	}

	public void setNeedUserNum(int needUserNum) {
		this.needUserNum = needUserNum;
	}

	public int getConfirmUserNum() {
		return confirmUserNum;
	}

	public void setConfirmUserNum(int confirmUserNum) {
		this.confirmUserNum = confirmUserNum;
	}

	public Date getA_enroll_end_time() {
		return a_enroll_end_time;
	}

	public void setA_enroll_end_time(Date a_enroll_end_time) {
		this.a_enroll_end_time = a_enroll_end_time;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public double getAsu_account_paid() {
		return asu_account_paid;
	}

	public void setAsu_account_paid(double asu_account_paid) {
		this.asu_account_paid = asu_account_paid;
	}

	public double getAsu_account_payable() {
		return asu_account_payable;
	}

	public void setAsu_account_payable(double asu_account_payable) {
		this.asu_account_payable = asu_account_payable;
	}

	public double getAsu_reserve_price() {
		return asu_reserve_price;
	}

	public void setAsu_reserve_price(double asu_reserve_price) {
		this.asu_reserve_price = asu_reserve_price;
	}

	public String getAsu_user_account_num() {
		return asu_user_account_num;
	}

	public void setAsu_user_account_num(String asu_user_account_num) {
		this.asu_user_account_num = asu_user_account_num;
	}

	public String getAsu_user_id() {
		return asu_user_id;
	}

	public void setAsu_user_id(String asu_user_id) {
		this.asu_user_id = asu_user_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public List<ActiveApplicant> getActiveApplicant() {
		return activeApplicant;
	}

	public void setActiveApplicant(List<ActiveApplicant> activeApplicant) {
		this.activeApplicant = activeApplicant;
	}

	public int getAsu_state() {
		return asu_state;
	}

	public void setAsu_state(int asu_state) {
		this.asu_state = asu_state;
	}

	public String getIn_id() {
		return in_id;
	}

	public void setIn_id(String in_id) {
		this.in_id = in_id;
	}

	public String getIsinsurance() {
		return isinsurance;
	}

	public void setIsinsurance(String isinsurance) {
		this.isinsurance = isinsurance;
	}

	public ActiveCorrelationCost getInsurance() {
		return insurance;
	}

	public void setInsurance(ActiveCorrelationCost insurance) {
		this.insurance = insurance;
	}

	public Date getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(Date activityTime) {
		this.activityTime = activityTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
