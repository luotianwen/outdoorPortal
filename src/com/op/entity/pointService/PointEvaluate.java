package com.op.entity.pointService;

import java.io.Serializable;
import java.util.Date;

/**
 * 地点服务评价(pointEvaluate)实体类
 * 
 * @author Win Zhong
 * @version Revision: 1.00 Date: 2016-07-08 09:35:08
 */
public class PointEvaluate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// id
	private String pse_id;
	// 地点服务id
	private int ps_id;
	// 总体评价星级（1-5星）
	private int pse_star;
	// 评价内容
	private String pse_evaluate_comment;
	// 停车信息(车停哪里了？收费么？)
	private String pse_park_info;
	// 评价人
	private String pse_create_user_id;
	// 评价时间
	private Date pse_create_time;
	// 评价项目ID
	private int pse_project_id;
	// 评价订单ID
	private int pse_venue_order_id;
	// 人均(元)
	private double pse_price;
	// 是否删除(0:否;1:是)
	private int pse_delete;
	// 是否匿名(0:否;1:是)
	private int pse_anonymous;
	//审核人
	private String auditor_id;
	//审核失败备注
	private String audit_notes;
	//审核状态(0:待审核;10:审核中;20:审核成功;30:审核失败)
	private String audit_state;
	//审核时间
	private Date auditor_time;
	
	
	/**
	 * id
	 * 
	 * @return
	 */
	public String getPse_id() {
		return pse_id;
	}

	/**
	 * id
	 * 
	 * @param type
	 */
	public void setPse_id(String pse_id) {
		this.pse_id = pse_id;
	}

	/**
	 * 地点服务id
	 * 
	 * @return
	 */
	public int getPs_id() {
		return ps_id;
	}

	/**
	 * 地点服务id
	 * 
	 * @param type
	 */
	public void setPs_id(int ps_id) {
		this.ps_id = ps_id;
	}

	/**
	 * 总体评价星级（1-5星）
	 * 
	 * @return
	 */
	public int getPse_star() {
		return pse_star;
	}

	/**
	 * 总体评价星级（1-5星）
	 * 
	 * @param type
	 */
	public void setPse_star(int pse_star) {
		this.pse_star = pse_star;
	}

	/**
	 * 评价内容
	 * 
	 * @return
	 */
	public String getPse_evaluate_comment() {
		return pse_evaluate_comment;
	}

	/**
	 * 评价内容
	 * 
	 * @param type
	 */
	public void setPse_evaluate_comment(String pse_evaluate_comment) {
		this.pse_evaluate_comment = pse_evaluate_comment;
	}

	/**
	 * 停车信息(车停哪里了？收费么？)
	 * 
	 * @return
	 */
	public String getPse_park_info() {
		return pse_park_info;
	}

	/**
	 * 停车信息(车停哪里了？收费么？)
	 * 
	 * @param type
	 */
	public void setPse_park_info(String pse_park_info) {
		this.pse_park_info = pse_park_info;
	}

	/**
	 * 评价人
	 * 
	 * @return
	 */
	public String getPse_create_user_id() {
		return pse_create_user_id;
	}

	/**
	 * 评价人
	 * 
	 * @param type
	 */
	public void setPse_create_user_id(String pse_create_user_id) {
		this.pse_create_user_id = pse_create_user_id;
	}

	/**
	 * 评价时间
	 * 
	 * @return
	 */
	public Date getPse_create_time() {
		return pse_create_time;
	}

	/**
	 * 评价时间
	 * 
	 * @param type
	 */
	public void setPse_create_time(Date pse_create_time) {
		this.pse_create_time = pse_create_time;
	}

	/**
	 * 评价项目ID
	 * 
	 * @return
	 */
	public int getPse_project_id() {
		return pse_project_id;
	}

	/**
	 * 评价项目ID
	 * 
	 * @param type
	 */
	public void setPse_project_id(int pse_project_id) {
		this.pse_project_id = pse_project_id;
	}

	/**
	 * 评价订单ID
	 * 
	 * @return
	 */
	public int getPse_venue_order_id() {
		return pse_venue_order_id;
	}

	/**
	 * 评价订单ID
	 * 
	 * @param type
	 */
	public void setPse_venue_order_id(int pse_venue_order_id) {
		this.pse_venue_order_id = pse_venue_order_id;
	}

	/**
	 * 人均(元)
	 * 
	 * @return
	 */
	public double getPse_price() {
		return pse_price;
	}

	/**
	 * 人均(元)
	 * 
	 * @param type
	 */
	public void setPse_price(double pse_price) {
		this.pse_price = pse_price;
	}

	/**
	 * 是否删除(0:否;1:是)
	 * 
	 * @return
	 */
	public int getPse_delete() {
		return pse_delete;
	}

	/**
	 * 是否删除(0:否;1:是)
	 * 
	 * @param type
	 */
	public void setPse_delete(int pse_delete) {
		this.pse_delete = pse_delete;
	}

	/**
	 * 是否匿名(0:否;1:是)
	 * 
	 * @return
	 */
	public int getPse_anonymous() {
		return pse_anonymous;
	}

	/**
	 * 是否匿名(0:否;1:是)
	 * 
	 * @return
	 */
	public void setPse_anonymous(int pse_anonymous) {
		this.pse_anonymous = pse_anonymous;
	}

	/**
	 * 审核人
	 * @return
	 */
	public String getAuditor_id() {
		return auditor_id;
	}

	/**
	 * 审核人
	 * @return
	 */
	public void setAuditor_id(String auditor_id) {
		this.auditor_id = auditor_id;
	}

	/**
	 * 审核失败备注
	 * @return
	 */
	public String getAudit_notes() {
		return audit_notes;
	}

	/**
	 * 审核失败备注
	 * @return
	 */
	public void setAudit_notes(String audit_notes) {
		this.audit_notes = audit_notes;
	}

	/**
	 * 审核状态(0:待审核;10:审核中;20:审核成功;30:审核失败)
	 * @return
	 */
	public String getAudit_state() {
		return audit_state;
	}

	/**
	 * 审核状态(0:待审核;10:审核中;20:审核成功;30:审核失败)
	 * @return
	 */
	public void setAudit_state(String audit_state) {
		this.audit_state = audit_state;
	}

	/**
	 * 审核时间
	 * @return
	 */
	public Date getAuditor_time() {
		return auditor_time;
	}

	/**
	 * 审核时间
	 * @return
	 */
	public void setAuditor_time(Date auditor_time) {
		this.auditor_time = auditor_time;
	}

}
