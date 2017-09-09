package com.op.dto.usercenter.leader.activeManager.activeSignUp;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 项目名：outdoorPortal 类描述：活动报名查询对象 创建人：Yan 创建时间： 2016-1-7 下午2:33:08
 * 最后修改时间：2016-1-7下午2:33:08
 */
public class LeaderSearchActiveSignUpDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 创建用户
	private String createUser;
	// 活动ID
	private int activeId;
	// 订单编号
	private String asu_id;
	// 参加人员姓名
	private String aa_user_name;
	// 报名联系人手机号码
	private Long asu_link_user_phone;
	// 报名状态
	private int asu_state;
	// 支付状态（1：未支付；2：已支付；）
	private int asu_pay_state;
	// 报名查询 开始时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date start_asu_create_time;
	// 报名查询 结束时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date end_asu_create_time;

	/*--------------------------------------------------	get  &&	set--------------------------------*/

	public String getCreateUser() {
		return createUser;
	}

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


	public Long getAsu_link_user_phone() {
		return asu_link_user_phone;
	}

	public void setAsu_link_user_phone(Long asu_link_user_phone) {
		this.asu_link_user_phone = asu_link_user_phone;
	}

	public int getAsu_state() {
		return asu_state;
	}

	public void setAsu_state(int asu_state) {
		this.asu_state = asu_state;
	}

	public int getAsu_pay_state() {
		return asu_pay_state;
	}

	public void setAsu_pay_state(int asu_pay_state) {
		this.asu_pay_state = asu_pay_state;
	}

	public Date getStart_asu_create_time() {
		return start_asu_create_time;
	}

	public void setStart_asu_create_time(Date start_asu_create_time) {
		this.start_asu_create_time = start_asu_create_time;
	}

	public Date getEnd_asu_create_time() {
		return end_asu_create_time;
	}

	public void setEnd_asu_create_time(Date end_asu_create_time) {
		this.end_asu_create_time = end_asu_create_time;
	}

	public int getActiveId() {
		return activeId;
	}

	public void setActiveId(int activeId) {
		this.activeId = activeId;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

}
