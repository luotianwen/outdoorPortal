package com.op.dto.signUp;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目名：outdoorPortal
 * 类描述：报名订单及关联的活动DTO
 * 创建人：Yan
 * 创建时间： 2015-12-28 下午4:12:32
 * 最后修改时间：2015-12-28下午4:12:32
 */
public class ActiveAndSignUpDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 活动ID
	private int asu_active_id;
	
	// 付款类型（10：全款；20：预付款；30：免费；）
	private int asu_price_type;
	
	// 支付状态（1：未支付；2：已支付；）
	private int asu_pay_state;
	
	// 是否申请退款（0：默认；1：是；）
	private int asu_refund_flag;
	
	// 是否已经删除(0：默认；1：是；)
	private int asu_is_delete;
	
	// 需要人数
	private int needUserNum;
	
	// 已报名人数
	private int alreadyInNum;
	
	// 报名截止时间
	private Date a_enroll_end_time;
	
	// 活动状态（1：草稿；2：发布待审核；3：审核中；4：审核不通过；5：审核成功（发布））
	private int state;
	
	// 活动总额
	private double price;
	
	// 活动预付额
	private double a_reserve_price;

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

	public int getAsu_refund_flag() {
		return asu_refund_flag;
	}

	public void setAsu_refund_flag(int asu_refund_flag) {
		this.asu_refund_flag = asu_refund_flag;
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

	public int getAlreadyInNum() {
		return alreadyInNum;
	}

	public void setAlreadyInNum(int alreadyInNum) {
		this.alreadyInNum = alreadyInNum;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getA_reserve_price() {
		return a_reserve_price;
	}

	public void setA_reserve_price(double a_reserve_price) {
		this.a_reserve_price = a_reserve_price;
	}

}
