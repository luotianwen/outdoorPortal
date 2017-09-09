package com.op.entity.activity;

import java.util.Date;


/**
 * 活动发布信息表(activity)实体类
 * 
 * @author Win Zhong
 * @version Revision: 1.00 Date: 2015-11-30 11:16:49
 */
public class ActivityInfo extends Activity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -919016519368884455L;
 
	/*
	 * 发布者名称
	 */
	private String userName;
	// 身份类型（1：个人发布者；2：企业；）
	protected int uType;
 	//审核人
 	 private String auditor;
	//审核时间
 	 private Date auditTime;
	//审核备注
 	 private String auditNotes;
	//活动状态
 	 private int[] states;
 	 private String ch_name;
 	 private String cl_name;
 	 //适合人群
 	 private String sc_name;
 	 //活动最小年龄
 	 private int aca_age_min;
 	 //活动最大年龄
 	 private int aca_age_max;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getuType() {
		return uType;
	}

	public void setuType(int uType) {
		this.uType = uType;
	}
    /**
    *审核人
    */ 
	@Override
	public String getAuditor() {
		return auditor;
	}
	@Override
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
    /**
    *审核时间
    */ 
	@Override
	public Date getAuditTime() {
		return auditTime;
	}
	@Override
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
    /**
    *审核备注
    */ 
	@Override
	public String getAuditNotes() {
		return auditNotes;
	}
	@Override
	public void setAuditNotes(String auditNotes) {
		this.auditNotes = auditNotes;
	}

	public int[] getStates() {
		return states;
	}

	public void setStates(int[] states) {
		this.states = states;
	}

	public String getCh_name() {
		return ch_name;
	}

	public void setCh_name(String ch_name) {
		this.ch_name = ch_name;
	}

	public String getCl_name() {
		return cl_name;
	}

	public void setCl_name(String cl_name) {
		this.cl_name = cl_name;
	}

	/**
	 * 适合人群
	 */
	@Override
	public String getSc_name() {
		return sc_name;
	}
	@Override
	public void setSc_name(String sc_name) {
		this.sc_name = sc_name;
	}

	/**
	 * 活动最小年龄
	 */
	public int getAca_age_min() {
		return aca_age_min;
	}
	public void setAca_age_min(int aca_age_min) {
		this.aca_age_min = aca_age_min;
	}

	/**
	 * 活动最大年龄
	 */
	public int getAca_age_max() {
		return aca_age_max;
	}
	public void setAca_age_max(int aca_age_max) {
		this.aca_age_max = aca_age_max;
	}
	
 
	
}
