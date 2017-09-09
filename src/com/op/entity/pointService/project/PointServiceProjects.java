package com.op.entity.pointService.project;

import java.io.Serializable;
import java.util.Date;
/** 
 * 地点服务项目(pointServiceProjects)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-07-26 11:28:23 
 */  
public class PointServiceProjects implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private int psp_id;
 	//场馆表id
  	 private int psp_venue_id;
 	//项目名称
  	 private String psp_item_name;
 	//项目原价
  	 private int psp_item_price;
 	//项目打折价
  	 private int psp_discount_price;
 	//项目内容
  	 private String psp_item_comment;
 	//是否参与用户组合(0：否；1：是)
  	 private int psp_is_group;
 	//有效期start
  	 private Date psp_validity_date_start;
 	//有效期end
  	 private Date psp_validity_date_end;
 	//修改关联ID（标识多条数据为历史备份数据）
  	 private int psp_update_id;
 	//是否已经过期（0：否；1：是）
  	 private int psp_is_efficacy;
 	//能否退款（0：是；1：否）
  	 private int psp_is_refund;
 	//是否支持预约（0：否；1：是）
  	 private int psp_is_yuyue;
 	//预约时间
  	 private Date psp_yuyue_time;
 	//是否删除（0：否；1：是）
  	 private int psp_is_delete;
 	//预约提醒(必填)
  	 private String psp_appointment;
 	//温馨提示(必填)
  	 private String psp_reminder;
 	//不可用日期
  	 private String psp_not_time;
 	//限购限用提醒
  	 private String psp_limit;
 	//规则提醒
  	 private String psp_rule;
 	//适用人数
  	 private String psp_people_number;
 	//创建人
  	 private String psp_create;
 	//创建时间
  	 private Date psp_create_time;
  	//最后修改人
  	 private String psp_update;
  	//最后修改时间
  	 private Date psp_update_time;
 	//项目状态(0:待审核;1:审核成功;2:审核失败)
  	 private int psp_state;
 	//审核人
  	 private String auditor_id;
 	//审核时间
  	 private Date auditor_time;
 	//审核意见
  	 private String auditNotes;



	 
    /**
    *id
	* @return
    */ 
	public int getPsp_id() {
		return psp_id;
	}
    /**
    *id
	* @param type
    */ 
	public void setPsp_id(int psp_id) {
		this.psp_id = psp_id;
	}
    /**
    *场馆表id
	* @return
    */ 
	public int getPsp_venue_id() {
		return psp_venue_id;
	}
    /**
    *场馆表id
	* @param type
    */ 
	public void setPsp_venue_id(int psp_venue_id) {
		this.psp_venue_id = psp_venue_id;
	}
    /**
    *项目名称
	* @return
    */ 
	public String getPsp_item_name() {
		return psp_item_name;
	}
    /**
    *项目名称
	* @param type
    */ 
	public void setPsp_item_name(String psp_item_name) {
		this.psp_item_name = psp_item_name;
	}
    /**
    *项目原价
	* @return
    */ 
	public int getPsp_item_price() {
		return psp_item_price;
	}
    /**
    *项目原价
	* @param type
    */ 
	public void setPsp_item_price(int psp_item_price) {
		this.psp_item_price = psp_item_price;
	}
    /**
    *项目打折价
	* @return
    */ 
	public int getPsp_discount_price() {
		return psp_discount_price;
	}
    /**
    *项目打折价
	* @param type
    */ 
	public void setPsp_discount_price(int psp_discount_price) {
		this.psp_discount_price = psp_discount_price;
	}
    /**
    *项目内容
	* @return
    */ 
	public String getPsp_item_comment() {
		return psp_item_comment;
	}
    /**
    *项目内容
	* @param type
    */ 
	public void setPsp_item_comment(String psp_item_comment) {
		this.psp_item_comment = psp_item_comment;
	}
    /**
    *是否参与用户组合(0：否；1：是)
	* @return
    */ 
	public int getPsp_is_group() {
		return psp_is_group;
	}
    /**
    *是否参与用户组合(0：否；1：是)
	* @param type
    */ 
	public void setPsp_is_group(int psp_is_group) {
		this.psp_is_group = psp_is_group;
	}
    /**
    *有效期start
	* @return
    */ 
	public Date getPsp_validity_date_start() {
		return psp_validity_date_start;
	}
    /**
    *有效期start
	* @param type
    */ 
	public void setPsp_validity_date_start(Date psp_validity_date_start) {
		this.psp_validity_date_start = psp_validity_date_start;
	}
    /**
    *有效期end
	* @return
    */ 
	public Date getPsp_validity_date_end() {
		return psp_validity_date_end;
	}
    /**
    *有效期end
	* @param type
    */ 
	public void setPsp_validity_date_end(Date psp_validity_date_end) {
		this.psp_validity_date_end = psp_validity_date_end;
	}
    /**
    *修改关联ID（标识多条数据为历史备份数据）
	* @return
    */ 
	public int getPsp_update_id() {
		return psp_update_id;
	}
    /**
    *修改关联ID（标识多条数据为历史备份数据）
	* @param type
    */ 
	public void setPsp_update_id(int psp_update_id) {
		this.psp_update_id = psp_update_id;
	}
    /**
    *是否已经过期（0：否；1：是）
	* @return
    */ 
	public int getPsp_is_efficacy() {
		return psp_is_efficacy;
	}
    /**
    *是否已经过期（0：否；1：是）
	* @param type
    */ 
	public void setPsp_is_efficacy(int psp_is_efficacy) {
		this.psp_is_efficacy = psp_is_efficacy;
	}
    /**
    *能否退款（0：是；1：否）
	* @return
    */ 
	public int getPsp_is_refund() {
		return psp_is_refund;
	}
    /**
    *能否退款（0：是；1：否）
	* @param type
    */ 
	public void setPsp_is_refund(int psp_is_refund) {
		this.psp_is_refund = psp_is_refund;
	}
    /**
    *是否支持预约（0：否；1：是）
	* @return
    */ 
	public int getPsp_is_yuyue() {
		return psp_is_yuyue;
	}
    /**
    *是否支持预约（0：否；1：是）
	* @param type
    */ 
	public void setPsp_is_yuyue(int psp_is_yuyue) {
		this.psp_is_yuyue = psp_is_yuyue;
	}
    /**
    *预约时间
	* @return
    */ 
	public Date getPsp_yuyue_time() {
		return psp_yuyue_time;
	}
    /**
    *预约时间
	* @param type
    */ 
	public void setPsp_yuyue_time(Date psp_yuyue_time) {
		this.psp_yuyue_time = psp_yuyue_time;
	}
    /**
    *是否删除（0：否；1：是）
	* @return
    */ 
	public int getPsp_is_delete() {
		return psp_is_delete;
	}
    /**
    *是否删除（0：否；1：是）
	* @param type
    */ 
	public void setPsp_is_delete(int psp_is_delete) {
		this.psp_is_delete = psp_is_delete;
	}
    /**
    *预约提醒(必填)
	* @return
    */ 
	public String getPsp_appointment() {
		return psp_appointment;
	}
    /**
    *预约提醒(必填)
	* @param type
    */ 
	public void setPsp_appointment(String psp_appointment) {
		this.psp_appointment = psp_appointment;
	}
    /**
    *温馨提示(必填)
	* @return
    */ 
	public String getPsp_reminder() {
		return psp_reminder;
	}
    /**
    *温馨提示(必填)
	* @param type
    */ 
	public void setPsp_reminder(String psp_reminder) {
		this.psp_reminder = psp_reminder;
	}
    /**
    *不可用日期
	* @return
    */ 
	public String getPsp_not_time() {
		return psp_not_time;
	}
    /**
    *不可用日期
	* @param type
    */ 
	public void setPsp_not_time(String psp_not_time) {
		this.psp_not_time = psp_not_time;
	}
    /**
    *限购限用提醒
	* @return
    */ 
	public String getPsp_limit() {
		return psp_limit;
	}
    /**
    *限购限用提醒
	* @param type
    */ 
	public void setPsp_limit(String psp_limit) {
		this.psp_limit = psp_limit;
	}
    /**
    *规则提醒
	* @return
    */ 
	public String getPsp_rule() {
		return psp_rule;
	}
    /**
    *规则提醒
	* @param type
    */ 
	public void setPsp_rule(String psp_rule) {
		this.psp_rule = psp_rule;
	}
    /**
    *适用人数
	* @return
    */ 
	public String getPsp_people_number() {
		return psp_people_number;
	}
    /**
    *适用人数
	* @param type
    */ 
	public void setPsp_people_number(String psp_people_number) {
		this.psp_people_number = psp_people_number;
	}
    /**
    *创建人
	* @return
    */ 
	public String getPsp_create() {
		return psp_create;
	}
    /**
    *创建人
	* @param type
    */ 
	public void setPsp_create(String psp_create) {
		this.psp_create = psp_create;
	}
    /**
    *创建时间
	* @return
    */ 
	public Date getPsp_create_time() {
		return psp_create_time;
	}
    /**
    *创建时间
	* @param type
    */ 
	public void setPsp_create_time(Date psp_create_time) {
		this.psp_create_time = psp_create_time;
	}
    /**
    *项目状态(0:待审核;1:审核成功;2:审核失败)
	* @return
    */ 
	public int getPsp_state() {
		return psp_state;
	}
    /**
    *项目状态(0:待审核;1:审核成功;2:审核失败)
	* @param type
    */ 
	public void setPsp_state(int psp_state) {
		this.psp_state = psp_state;
	}
    /**
    *审核人
	* @return
    */ 
	public String getAuditor_id() {
		return auditor_id;
	}
    /**
    *审核人
	* @param type
    */ 
	public void setAuditor_id(String auditor_id) {
		this.auditor_id = auditor_id;
	}
    /**
    *审核时间
	* @return
    */ 
	public Date getAuditor_time() {
		return auditor_time;
	}
    /**
    *审核时间
	* @param type
    */ 
	public void setAuditor_time(Date auditor_time) {
		this.auditor_time = auditor_time;
	}
    /**
    *审核意见
	* @return
    */ 
	public String getAuditNotes() {
		return auditNotes;
	}
    /**
    *审核意见
	* @param type
    */ 
	public void setAuditNotes(String auditNotes) {
		this.auditNotes = auditNotes;
	}
	
	/**
	 * 最后修改人
	 * @return
	 */
	public String getPsp_update() {
		return psp_update;
	}
	/**
	 * 最后修改人
	 * @return
	 */
	public void setPsp_update(String psp_update) {
		this.psp_update = psp_update;
	}
	/**
	 * 最后修改时间
	 * @return
	 */
	public Date getPsp_update_time() {
		return psp_update_time;
	}
	/**
	 * 最后修改时间
	 * @return
	 */
	public void setPsp_update_time(Date psp_update_time) {
		this.psp_update_time = psp_update_time;
	}
	
}
