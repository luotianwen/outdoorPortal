package com.op.entity.pointService.auth;

import java.io.Serializable;
import java.util.Date;
/** 
 * 地点商户认领认证信息(pointAuth)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-08-30 14:39:05 
 */  
public class PointAuth implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//认证id
  	 private int pa_id;
 	//用户id
  	 private String user_id;
 	//地点服务id
  	 private int ps_id;
 	//真实姓名
  	 private String contactName;
 	//身份证号
  	 private String idCard;
 	//手机号
  	 private String mobile;
 	//邮箱
  	 private String email;
 	//身份证扫描(正面)图片地址
  	 private String idCard_p_src;
 	//身份证扫描(反面)图片地址
  	 private String idCard_i_src2;
 	//审核结果（1：提交待审核；2：审核中；3：审核成功；4：审核失败；）
  	 private String auditor_state;
 	//组织机构代码
  	 private String organizationCode;
 	//公司名称
  	 private String companyName;
 	//注册所在地
  	 private String location;
 	//详细地址
  	 private String detailedAddress;
 	//成立日期
  	 private Date establish_time;
 	//营业结束时间
  	 private Date business_end_time;
 	//营业开始时间
  	 private Date business_start_time;
 	//注册资本
  	 private String registerCapital;
 	//经营范围
  	 private String businessScope;
 	//审核备注
  	 private String auditor_remark;
 	//组织机构代码证图片地址
  	 private String organizationCode_src;
 	//纳税人识别号
  	 private String taxpayer_identity_number;
 	//税务登记证
  	 private String tax_registration_certificate;
 	//一般纳税人资格证图片地址
  	 private String general_taxpayer_src;
 	//营业执照注册号
  	 private String license_number;
 	//营业执照扫描件图片地址
  	 private String license_src;
  	 //三证合一(0：有；1：无)
  	 private String threeinone;
 	//申请认证时间
  	 private Date application_time;
 	//审核人
  	 private String auditor_id;
 	//审核时间
  	 private Date auditor_time;
  	 


	 
    /**
    *认证id
	* @return
    */ 
	public int getPa_id() {
		return pa_id;
	}
    /**
    *认证id
	* @param type
    */ 
	public void setPa_id(int pa_id) {
		this.pa_id = pa_id;
	}
    /**
    *用户id
	* @return
    */ 
	public String getUser_id() {
		return user_id;
	}
    /**
    *用户id
	* @param type
    */ 
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
    /**
    *地点服务id
	* @return
    */ 
	public int getPs_id() {
		return ps_id;
	}
    /**
    *地点服务id
	* @param type
    */ 
	public void setPs_id(int ps_id) {
		this.ps_id = ps_id;
	}
    /**
    *真实姓名
	* @return
    */ 
	public String getContactName() {
		return contactName;
	}
    /**
    *真实姓名
	* @param type
    */ 
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
    /**
    *身份证号
	* @return
    */ 
	public String getIdCard() {
		return idCard;
	}
    /**
    *身份证号
	* @param type
    */ 
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
    /**
    *手机号
	* @return
    */ 
	public String getMobile() {
		return mobile;
	}
    /**
    *手机号
	* @param type
    */ 
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
    /**
    *邮箱
	* @return
    */ 
	public String getEmail() {
		return email;
	}
    /**
    *邮箱
	* @param type
    */ 
	public void setEmail(String email) {
		this.email = email;
	}
    /**
    *身份证扫描(正面)图片地址
	* @return
    */ 
	public String getIdCard_p_src() {
		return idCard_p_src;
	}
    /**
    *身份证扫描(正面)图片地址
	* @param type
    */ 
	public void setIdCard_p_src(String idCard_p_src) {
		this.idCard_p_src = idCard_p_src;
	}
    /**
    *身份证扫描(反面)图片地址
	* @return
    */ 
	public String getIdCard_i_src2() {
		return idCard_i_src2;
	}
    /**
    *身份证扫描(反面)图片地址
	* @param type
    */ 
	public void setIdCard_i_src2(String idCard_i_src2) {
		this.idCard_i_src2 = idCard_i_src2;
	}
    /**
    *审核结果（1：提交待审核；2：审核中；3：审核成功；4：审核失败；）
	* @return
    */ 
	public String getAuditor_state() {
		return auditor_state;
	}
    /**
    *审核结果（1：提交待审核；2：审核中；3：审核成功；4：审核失败；）
	* @param type
    */ 
	public void setAuditor_state(String auditor_state) {
		this.auditor_state = auditor_state;
	}
    /**
    *组织机构代码
	* @return
    */ 
	public String getOrganizationCode() {
		return organizationCode;
	}
    /**
    *组织机构代码
	* @param type
    */ 
	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}
    /**
    *公司名称
	* @return
    */ 
	public String getCompanyName() {
		return companyName;
	}
    /**
    *公司名称
	* @param type
    */ 
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
    /**
    *注册所在地
	* @return
    */ 
	public String getLocation() {
		return location;
	}
    /**
    *注册所在地
	* @param type
    */ 
	public void setLocation(String location) {
		this.location = location;
	}
    /**
    *详细地址
	* @return
    */ 
	public String getDetailedAddress() {
		return detailedAddress;
	}
    /**
    *详细地址
	* @param type
    */ 
	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}
    /**
    *成立日期
	* @return
    */ 
	public Date getEstablish_time() {
		return establish_time;
	}
    /**
    *成立日期
	* @param type
    */ 
	public void setEstablish_time(Date establish_time) {
		this.establish_time = establish_time;
	}
    /**
    *营业结束时间
	* @return
    */ 
	public Date getBusiness_end_time() {
		return business_end_time;
	}
    /**
    *营业结束时间
	* @param type
    */ 
	public void setBusiness_end_time(Date business_end_time) {
		this.business_end_time = business_end_time;
	}
    /**
    *营业开始时间
	* @return
    */ 
	public Date getBusiness_start_time() {
		return business_start_time;
	}
    /**
    *营业开始时间
	* @param type
    */ 
	public void setBusiness_start_time(Date business_start_time) {
		this.business_start_time = business_start_time;
	}
    /**
    *注册资本
	* @return
    */ 
	public String getRegisterCapital() {
		return registerCapital;
	}
    /**
    *注册资本
	* @param type
    */ 
	public void setRegisterCapital(String registerCapital) {
		this.registerCapital = registerCapital;
	}
    /**
    *经营范围
	* @return
    */ 
	public String getBusinessScope() {
		return businessScope;
	}
    /**
    *经营范围
	* @param type
    */ 
	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}
    /**
    *审核备注
	* @return
    */ 
	public String getAuditor_remark() {
		return auditor_remark;
	}
    /**
    *审核备注
	* @param type
    */ 
	public void setAuditor_remark(String auditor_remark) {
		this.auditor_remark = auditor_remark;
	}
    /**
    *组织机构代码证图片地址
	* @return
    */ 
	public String getOrganizationCode_src() {
		return organizationCode_src;
	}
    /**
    *组织机构代码证图片地址
	* @param type
    */ 
	public void setOrganizationCode_src(String organizationCode_src) {
		this.organizationCode_src = organizationCode_src;
	}
    /**
    *纳税人识别号
	* @return
    */ 
	public String getTaxpayer_identity_number() {
		return taxpayer_identity_number;
	}
    /**
    *纳税人识别号
	* @param type
    */ 
	public void setTaxpayer_identity_number(String taxpayer_identity_number) {
		this.taxpayer_identity_number = taxpayer_identity_number;
	}
    /**
    *税务登记证
	* @return
    */ 
	public String getTax_registration_certificate() {
		return tax_registration_certificate;
	}
    /**
    *税务登记证
	* @param type
    */ 
	public void setTax_registration_certificate(String tax_registration_certificate) {
		this.tax_registration_certificate = tax_registration_certificate;
	}
    /**
    *一般纳税人资格证图片地址
	* @return
    */ 
	public String getGeneral_taxpayer_src() {
		return general_taxpayer_src;
	}
    /**
    *一般纳税人资格证图片地址
	* @param type
    */ 
	public void setGeneral_taxpayer_src(String general_taxpayer_src) {
		this.general_taxpayer_src = general_taxpayer_src;
	}
    /**
    *营业执照注册号
	* @return
    */ 
	public String getLicense_number() {
		return license_number;
	}
    /**
    *营业执照注册号
	* @param type
    */ 
	public void setLicense_number(String license_number) {
		this.license_number = license_number;
	}
    /**
    *营业执照扫描件图片地址
	* @return
    */ 
	public String getLicense_src() {
		return license_src;
	}
    /**
    *营业执照扫描件图片地址
	* @param type
    */ 
	public void setLicense_src(String license_src) {
		this.license_src = license_src;
	}
    /**
    *申请认证时间
	* @return
    */ 
	public Date getApplication_time() {
		return application_time;
	}
    /**
    *申请认证时间
	* @param type
    */ 
	public void setApplication_time(Date application_time) {
		this.application_time = application_time;
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
	 * 三证合一(0：有；1：无)
	 * @return
	 */
	public String getThreeinone() {
		return threeinone;
	}
	/**
	 * 三证合一(0：有；1：无)
	 * @return
	 */
	public void setThreeinone(String threeinone) {
		this.threeinone = threeinone;
	}
	
}
