package com.op.entity.usercenter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.op.entity.activity.cost.ActiveCorrelationCost;
/** 
 * 活动报名（订单）表(activeSignup)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-03-18 16:36:38 
 */  
public class ActiveSignup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//订单编号
	private String asu_id;
 	//应急联系人
	private String asu_user_emergency;
 	//应急联系人手机号码
	private String asu_user_emergency_phone;
	//应急联系人与本人关系
	private String asu_user_relation;
 	//报名联系人手机号码
	private String asu_link_user_phone;
	//订单备注
	private String asu_order_area;
 	//活动ID
	private String asu_active_id;
 	//支付流水号
	private String asu_user_account_num;
 	//支付宝交易号
	private String asu_trade_no;
 	//付款时间
	private Date asu_pay_time;
 	//付款总额(元)
	private double asu_account_paid;
	//应该付款总额(元)
	private double asu_account_payable;
	//预约费总额
	private double asu_reserve_price;
  	//报名状态（10：等待领队确认；20：领队已确认；30：交易完成；40：报名已取消；50：等待领队确认取消；）
	private int asu_state;
 	//支付状态（1：未支付；2：已支付；）
	private int asu_pay_state;
 	//付款类型（10：全款；20：预付款；30：免费；）
	private int asu_price_type;
 	//报名时间
	private Date asu_create_time;
 	//创建用户ID
	private String asu_user_id;
	//最后修改时间
	private Date asu_last_update_time;
 	//最后修改用户
	private String asu_last_update_user;
 	//是否已经删除(0：默认；1：是；)
	private int asu_is_delete;
	//领队是否已经删除(0：默认；1：是；)
	private int asu_leader_is_delete;
 	//是否评价（0：默认；1：是）
	private int asu_is_comment;
	//装备
	private String asu_equipment;
  	//订单类型
	private int asu_type;
	
  	//报名人集合
   	private List<ActiveApplicant> activeApplicant;
  	 
  	 
   	//常用联系人
   	private List<LinkSignUpUser> linkSignUpUser;
	//可选费用
  	private List<ActiveCorrelationCost> optional;
	
	 
  	 /**
     *订单编号
     */ 
	public String getAsu_id() {
		return asu_id;
	}
	public void setAsu_id(String asu_id) {
		this.asu_id = asu_id;
	}
    /**
    *应急联系人
    */ 
	public String getAsu_user_emergency() {
		return asu_user_emergency;
	}
	public void setAsu_user_emergency(String asu_user_emergency) {
		this.asu_user_emergency = asu_user_emergency;
	}
    /**
    *应急联系人手机号码
    */ 
	public String getAsu_user_emergency_phone() {
		return asu_user_emergency_phone;
	}
	public void setAsu_user_emergency_phone(String asu_user_emergency_phone) {
		this.asu_user_emergency_phone = asu_user_emergency_phone;
	}
	/**
	 * 应急联系人与本人关系
	 */
    public String getAsu_user_relation() {
		return asu_user_relation;
	}
	public void setAsu_user_relation(String asu_user_relation) {
		this.asu_user_relation = asu_user_relation;
	}
	/**
    *报名联系人手机号码
    */ 
	public String getAsu_link_user_phone() {
		return asu_link_user_phone;
	}
	public void setAsu_link_user_phone(String asu_link_user_phone) {
		this.asu_link_user_phone = asu_link_user_phone;
	}
    /**
    *订单备注
    */ 
	public String getAsu_order_area() {
		return asu_order_area;
	}
	public void setAsu_order_area(String asu_order_area) {
		this.asu_order_area = asu_order_area;
	}
    /**
    *活动ID
    */ 
	public String getAsu_active_id() {
		return asu_active_id;
	}
	public void setAsu_active_id(String asu_active_id) {
		this.asu_active_id = asu_active_id;
	}
    /**
    *支付流水号
    */ 
	public String getAsu_user_account_num() {
		return asu_user_account_num;
	}
	public void setAsu_user_account_num(String asu_user_account_num) {
		this.asu_user_account_num = asu_user_account_num;
	}
    /**
    *支付宝交易号
    */ 
	public String getAsu_trade_no() {
		return asu_trade_no;
	}
	public void setAsu_trade_no(String asu_trade_no) {
		this.asu_trade_no = asu_trade_no;
	}
    /**
    *付款时间
    */ 
	public Date getAsu_pay_time() {
		return asu_pay_time;
	}
	public void setAsu_pay_time(Date asu_pay_time) {
		this.asu_pay_time = asu_pay_time;
	}
    /**
    *付款总额(元)
    */ 
	public double getAsu_account_paid() {
		return asu_account_paid;
	}
	public void setAsu_account_paid(double asu_account_paid) {
		this.asu_account_paid = asu_account_paid;
	}
	/**
	 * 应付款总额(元)
	 */ 
	public double getAsu_account_payable() {
		return asu_account_payable;
	}
	public void setAsu_account_payable(double asu_account_payable) {
		this.asu_account_payable = asu_account_payable;
	}
	/**
	 * 预约费总额
	 */
	public double getAsu_reserve_price() {
		return asu_reserve_price;
	}
	public void setAsu_reserve_price(double asu_reserve_price) {
		this.asu_reserve_price = asu_reserve_price;
	}
	/**
	 * 报名状态（10：等待领队确认；20：领队已确认；30：交易完成；40：报名已取消；50：等待领队确认取消；）
	 */
    public int getAsu_state() {
		return asu_state;
	}
	public void setAsu_state(int asu_state) {
		this.asu_state = asu_state;
	}
	/**
     *支付状态（1：未支付；2：已支付；）
     */ 
	public int getAsu_pay_state() {
		return asu_pay_state;
	}
	public void setAsu_pay_state(int asu_pay_state) {
		this.asu_pay_state = asu_pay_state;
	}
    /**
     *付款类型（10：全款；20：预付款；30：免费；）
     */ 
	public int getAsu_price_type() {
		return asu_price_type;
	}
	public void setAsu_price_type(int asu_price_type) {
		this.asu_price_type = asu_price_type;
	}
    /**
     *报名时间
     */ 
	public Date getAsu_create_time() {
		return asu_create_time;
	}
	public void setAsu_create_time(Date asu_create_time) {
		this.asu_create_time = asu_create_time;
	}
    /**
     *创建用户ID
     */ 
	public String getAsu_user_id() {
		return asu_user_id;
	}
	public void setAsu_user_id(String asu_user_id) {
		this.asu_user_id = asu_user_id;
	}
    /**
     *最后修改时间
     */ 
	public Date getAsu_last_update_time() {
		return asu_last_update_time;
	}
	public void setAsu_last_update_time(Date asu_last_update_time) {
		this.asu_last_update_time = asu_last_update_time;
	}
    /**
     *最后修改用户
     */ 
	public String getAsu_last_update_user() {
		return asu_last_update_user;
	}
	public void setAsu_last_update_user(String asu_last_update_user) {
		this.asu_last_update_user = asu_last_update_user;
	}
    /**
     *是否已经删除(0：默认；1：是；)
     */ 
	public int getAsu_is_delete() {
		return asu_is_delete;
	}
	public void setAsu_is_delete(int asu_is_delete) {
		this.asu_is_delete = asu_is_delete;
	}
	/**
     *领队是否已经删除(0：默认；1：是；)
     */ 
	public int getAsu_leader_is_delete() {
		return asu_leader_is_delete;
	}
	public void setAsu_leader_is_delete(int asu_leader_is_delete) {
		this.asu_leader_is_delete = asu_leader_is_delete;
	}
    /**
     *是否评价（0：默认；1：是）
     */ 
	public int getAsu_is_comment() {
		return asu_is_comment;
	}
	public void setAsu_is_comment(int asu_is_comment) {
		this.asu_is_comment = asu_is_comment;
	}
	/**
	 * 常用联系人
	 */
	public List<LinkSignUpUser> getLinkSignUpUser() {
		return linkSignUpUser;
	}
	public void setLinkSignUpUser(List<LinkSignUpUser> linkSignUpUser) {
		this.linkSignUpUser = linkSignUpUser;
	}
	/**
	 * 可选费用
	 */
	public List<ActiveCorrelationCost> getOptional() {
		return optional;
	}
	public void setOptional(List<ActiveCorrelationCost> optional) {
		this.optional = optional;
	}
	/**
	 * 订单类型
	 */
	public int getAsu_type() {
		return asu_type;
	}
	public void setAsu_type(int asu_type) {
		this.asu_type = asu_type;
	}
	/**
	 * 订单报名人
	 */
	public List<ActiveApplicant> getActiveApplicant() {
		return activeApplicant;
	}
	public void setActiveApplicant(List<ActiveApplicant> activeApplicant) {
		this.activeApplicant = activeApplicant;
	}
	/**
	 * 装备
	 */
	public String getAsu_equipment() {
		return asu_equipment;
	}
	public void setAsu_equipment(String asu_equipment) {
		this.asu_equipment = asu_equipment;
	}
	
}
