package com.op.dto.usercenter.leader.activeManager.activeSignUp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.op.entity.usercenter.ActiveApplicant;

public class MySignUp implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 订单表数据
	 */
	//订单编号
	private String asu_id;
	//付款总额(元)
	private double asu_account_paid;
	//应付款总额
	private double asu_account_payable;
	//预约款
	private double asu_reserve_price;
	//支付状态(1：未付款，2：已付款)
	private int asu_pay_state;
	//报名时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date asu_create_time;
	//报名状态（10：等待领队确认；20：领队已确认；30：交易完成；40：报名已取消；50：等待领队确认取消；）
	private int asu_state;
	//订单状态
	private String asu_stateStr;
	//用户ID
	private String asu_user_id;
	//是否评价
	private int asu_is_comment;
	
	//订单状态
	private String state;
	//支付时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private String paytime;
	//报名联系人
	private String signUpLinkman;
	//报名联系人电话
	private String linkmanPhone;
	//退款总额
	private double refund;
	//订单备注
	private String asu_order_area;
	
	/**
	 * 订单报名人表
	 */
	//所有报名人
	private List<ActiveApplicant> activeApplicants;
	
	/**
	 * 活动表数据
	 */
	//活动ID
	private String activityId;
	//活动标题
	private String title;
	//活动主图
	private String a_active_img;
	//活动开始时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date activitytime;
	//活动结束时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date endtime;
	//报名截止时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date a_enroll_end_time;
	// 已报名人数
	private int alreadyInNum;
	// 确认人数
	private int confirmUserNum;
	//发布者ID
	private String createUser;
	
	//活动状态(1：活动未开始；2：活动已开始；3：活动已结束)
	private String activityState;
	
	//订单条件
	private String asu_type;
	
	
	/** ------------------------------------------订单详情-------------------------------------- */
	//正常交易中报名人
	private List<ActiveApplicant> activeApplicantNormal;
	//退款中报名人
	private List<ActiveApplicant> activeApplicantRefunding;
	//领队已同意退款的报名人
	private List<ActiveApplicant> activeApplicantRefund;
	//退款失败报名人
	private List<ActiveApplicant> activeApplicantRefundfail;
	//交易号
	private String asu_trade_no;
	
	/**
	 * 订单编号
	 */
	public String getAsu_id() {
		return asu_id;
	}
	/**
	 * 订单编号
	 */
	public void setAsu_id(String asu_id) {
		this.asu_id = asu_id;
	}
	
	/**
	 * 实付款总额
	 */
	public double getAsu_account_paid() {
		return asu_account_paid;
	}
	/**
	 * 实付款总额
	 */
	public void setAsu_account_paid(double asu_account_paid) {
		this.asu_account_paid = asu_account_paid;
	}
	
	/**
	 * 应付款总额
	 */
	public double getAsu_account_payable() {
		return asu_account_payable;
	}
	/**
	 * 应付款总额
	 */
	public void setAsu_account_payable(double asu_account_payable) {
		this.asu_account_payable = asu_account_payable;
	}
	
	/**
	 * 预约款总额
	 */
	public double getAsu_reserve_price() {
		return asu_reserve_price;
	}
	/**
	 * 预约款总额
	 */
	public void setAsu_reserve_price(double asu_reserve_price) {
		this.asu_reserve_price = asu_reserve_price;
	}
	
	/**
	 * 支付状态(1：未付款，2：已付款)
	 */
	public int getAsu_pay_state() {
		return asu_pay_state;
	}
	/**
	 * 支付状态(1：未付款，2：已付款)
	 */
	public void setAsu_pay_state(int asu_pay_state) {
		this.asu_pay_state = asu_pay_state;
	}
	
	/**
	 * 报名时间
	 */
	public Date getAsu_create_time() {
		return asu_create_time;
	}
	/**
	 * 报名时间
	 */
	public void setAsu_create_time(Date asu_create_time) {
		this.asu_create_time = asu_create_time;
	}
	
	/**
	 * 报名状态（10：等待领队确认；20：领队已确认；30：交易完成；40：报名已取消；50：等待领队确认取消；）
	 */
	public int getAsu_state() {
		return asu_state;
	}
	/**
	 * 报名状态
	 */
	public void setAsu_state(int asu_state) {
		this.asu_state = asu_state;
	}
	
	/**
	 * 订单状态
	 */
	public String getAsu_stateStr() {
		return asu_stateStr;
	}/**
	 * 订单状态
	 */
	public void setAsu_stateStr(String asu_stateStr) {
		this.asu_stateStr = asu_stateStr;
	}
	
	/**
	 * 用户ID
	 */
	public String getAsu_user_id() {
		return asu_user_id;
	}
	/**
	 * 用户ID
	 */
	public void setAsu_user_id(String asu_user_id) {
		this.asu_user_id = asu_user_id;
	}
	
	/**
	 * 是否评价
	 */
	public int getAsu_is_comment() {
		return asu_is_comment;
	}
	/**
	 * 是否评价
	 */
	public void setAsu_is_comment(int asu_is_comment) {
		this.asu_is_comment = asu_is_comment;
	}
	
	/**
	 * 订单状态
	 */
	public String getState() {
		return state;
	}
	/**
	 * 订单状态
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * 订单报名人表
	 */
	public List<ActiveApplicant> getActiveApplicants() {
		return activeApplicants;
	}
	
	/**
	 * 订单报名人表
	 */
	public void setActiveApplicants(List<ActiveApplicant> activeApplicants) {
		this.activeApplicants = activeApplicants;
	}
	
	/**
	 * 活动ID
	 */
	public String getActivityId() {
		return activityId;
	}
	/**
	 * 活动ID
	 */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	
	/**
	 * 活动标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 活动标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 活动主图
	 */
	public String getA_active_img() {
		return a_active_img;
	}
	/**
	 * 活动主图
	 */
	public void setA_active_img(String a_active_img) {
		this.a_active_img = a_active_img;
	}
	
	/**
	 * 支付时间
	 */
	public String getPaytime() {
		return paytime;
	}
	/**
	 * 支付时间
	 */
	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}
	
	/**
	 * 报名联系人
	 */
	public String getSignUpLinkman() {
		return signUpLinkman;
	}
	/**
	 * 报名联系人
	 */
	public void setSignUpLinkman(String signUpLinkman) {
		this.signUpLinkman = signUpLinkman;
	}
	
	/**
	 * 报名联系人电话
	 */
	public String getLinkmanPhone() {
		return linkmanPhone;
	}
	/**
	 * 报名联系人电话
	 */
	public void setLinkmanPhone(String linkmanPhone) {
		this.linkmanPhone = linkmanPhone;
	}
	
	/**
	 * 退款总额
	 */
	public double getRefund() {
		return refund;
	}
	/**
	 * 退款总额
	 */
	public void setRefund(double refund) {
		this.refund = refund;
	}
	
	/**
	 * 订单备注
	 */
	public String getAsu_order_area() {
		return asu_order_area;
	}
	/**
	 * 订单备注
	 */
	public void setAsu_order_area(String asu_order_area) {
		this.asu_order_area = asu_order_area;
	}
	
	/**
	 * 活动开始时间
	 */
	public Date getActivitytime() {
		return activitytime;
	}
	/**
	 * 活动开始时间
	 */
	public void setActivitytime(Date activitytime) {
		this.activitytime = activitytime;
	}
	
	/**
	 * 活动结束时间
	 */
	public Date getEndtime() {
		return endtime;
	}
	/**
	 * 活动结束时间
	 */
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	
	/**
	 * 报名截止时间
	 */
	public Date getA_enroll_end_time() {
		return a_enroll_end_time;
	}
	/**
	 * 报名截止时间
	 */
	public void setA_enroll_end_time(Date a_enroll_end_time) {
		this.a_enroll_end_time = a_enroll_end_time;
	}
	
	
	/**
	 * 发布者ID
	 */
	public String getCreateUser() {
		return createUser;
	}
	/**
	 * 发布者ID
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	/**
	 * 活动状态(1：活动未开始；2：已截止报名；3：活动已开始；4：活动已结束)
	 */
	public String getActivityState() {
		return activityState;
	}
	/**
	 * 活动状态(1：活动未开始；2：活动已开始；3：活动已结束)
	 */
	public void setActivityState(String activityState) {
		this.activityState = activityState;
	}
	
	/**
	 * 已报名人数
	 */
	public int getAlreadyInNum() {
		return alreadyInNum;
	}
	/**
	 * 已报名人数
	 */
	public void setAlreadyInNum(int alreadyInNum) {
		this.alreadyInNum = alreadyInNum;
	}
	
	/**
	 * 已确认人数
	 */
	public int getConfirmUserNum() {
		return confirmUserNum;
	}
	/**
	 * 已确认人数
	 */
	public void setConfirmUserNum(int confirmUserNum) {
		this.confirmUserNum = confirmUserNum;
	}
	
	/**
	 * 订单条件
	 */
	public String getAsu_type() {
		return asu_type;
	}
	/**
	 * 订单条件
	 */
	public void setAsu_type(String asu_type) {
		this.asu_type = asu_type;
	}
	
	/**
	 * 正常交易中报名人
	 */
	public List<ActiveApplicant> getActiveApplicantNormal() {
		return activeApplicantNormal;
	}
	/**
	 * 正常交易中报名人
	 */
	public void setActiveApplicantNormal(List<ActiveApplicant> activeApplicantNormal) {
		this.activeApplicantNormal = activeApplicantNormal;
	}
	
	/**
	 * 退款中的报名人
	 */
	public List<ActiveApplicant> getActiveApplicantRefunding() {
		return activeApplicantRefunding;
	}
	/**
	 * 领队已同意退款的报名人
	 */
	public void setActiveApplicantRefunding(
			List<ActiveApplicant> activeApplicantRefunding) {
		this.activeApplicantRefunding = activeApplicantRefunding;
	}
	
	/**
	 * 领队已同意退款的报名人
	 */
	public List<ActiveApplicant> getActiveApplicantRefund() {
		return activeApplicantRefund;
	}
	/**
	 * 退款中的报名人
	 */
	public void setActiveApplicantRefund(
			List<ActiveApplicant> activeApplicantRefund) {
		this.activeApplicantRefund = activeApplicantRefund;
	}
	
	/**
	 * 退款失败的报名人
	 */
	public List<ActiveApplicant> getActiveApplicantRefundfail() {
		return activeApplicantRefundfail;
	}
	/**
	 * 退款失败的报名人
	 */
	public void setActiveApplicantRefundfail(
			List<ActiveApplicant> activeApplicantRefundfail) {
		this.activeApplicantRefundfail = activeApplicantRefundfail;
	}
	
	/**
	 * 支付宝交易号
	 */
	public String getAsu_trade_no() {
		return asu_trade_no;
	}
	/**
	 * 支付宝交易号
	 */
	public void setAsu_trade_no(String asu_trade_no) {
		this.asu_trade_no = asu_trade_no;
	}
	
}
