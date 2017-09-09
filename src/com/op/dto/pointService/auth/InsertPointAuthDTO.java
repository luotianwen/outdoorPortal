package com.op.dto.pointService.auth;

import java.io.Serializable;
import java.util.Date;

/**
 * 地点商户认领认证信息(pointAuth)实体类
 * 
 * @author 世峰户外商城
 * @version Revision: 1.00 Date: 2016-06-27 10:06:24
 */
public class InsertPointAuthDTO implements Serializable {

	/**
	 * 
	 */
	static final long serialVersionUID = 1L;

	// 认证id
	String pa_id;
	
	// 用户id
	String user_id;
	
	// 地点服务id
	String ps_id;
	
	// 真实姓名
	String contactName;
	
	// 身份证号
	String idCard;
	
	// 手机号
	String mobile;
	
	// 邮箱
	String email;
	
	// 身份证扫描(正面)图片地址
	String idCard_p_src;
	
	// 身份证扫描(反面)图片地址
	String idCard_i_src2;
	
	// 营业执照注册号
	String license_number;
	
	// 营业执照扫描件图片地址
	String license_src;
	
	// 申请认证时间
	Date application_time = new Date();
	
	//组织机构代码
	private String organizationCode;
	
	//组织机构代码证图片地址
	private String organizationCode_src;
	
	//纳税人识别号
	private String taxpayer_identity_number;
	
	//税务登记证
	private String tax_registration_certificate;
	
	//三证合一(0：有；1：无)
	private String threeinone;
	
	/*--------------------------------------我是分割线------------------------------------*/

	/**
	 * 认证id
	 */
	public String getPa_id() {
		return pa_id;
	}

	public void setPa_id(String pa_id) {
		this.pa_id = pa_id;
	}

	/**
	 * 地点服务id
	 */
	public String getPs_id() {
		return ps_id;
	}

	public void setPs_id(String ps_id) {
		this.ps_id = ps_id;
	}

	/**
	 * 用户id
	 */
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * 真实姓名
	 */
	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 * 身份证号
	 */
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	/**
	 * 手机号
	 */
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 邮箱
	 */
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 身份证扫描(正面)图片地址
	 */
	public String getIdCard_p_src() {
		return idCard_p_src;
	}

	public void setIdCard_p_src(String idCard_p_src) {
		this.idCard_p_src = idCard_p_src;
	}

	/**
	 * 身份证扫描(反面)图片地址
	 */
	public String getIdCard_i_src2() {
		return idCard_i_src2;
	}

	public void setIdCard_i_src2(String idCard_i_src2) {
		this.idCard_i_src2 = idCard_i_src2;
	}

	/**
	 * 营业执照注册号
	 */
	public String getLicense_number() {
		return license_number;
	}

	public void setLicense_number(String license_number) {
		this.license_number = license_number;
	}

	/**
	 * 营业执照扫描件图片地址
	 */
	public String getLicense_src() {
		return license_src;
	}

	public void setLicense_src(String license_src) {
		this.license_src = license_src;
	}

	/**
	 * 申请认证时间
	 */
	public Date getApplication_time() {
		return application_time;
	}

	public void setApplication_time(Date application_time) {
		this.application_time = application_time;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getOrganizationCode_src() {
		return organizationCode_src;
	}

	public void setOrganizationCode_src(String organizationCode_src) {
		this.organizationCode_src = organizationCode_src;
	}

	public String getTaxpayer_identity_number() {
		return taxpayer_identity_number;
	}

	public void setTaxpayer_identity_number(String taxpayer_identity_number) {
		this.taxpayer_identity_number = taxpayer_identity_number;
	}

	public String getTax_registration_certificate() {
		return tax_registration_certificate;
	}

	public void setTax_registration_certificate(String tax_registration_certificate) {
		this.tax_registration_certificate = tax_registration_certificate;
	}

	public String getThreeinone() {
		return threeinone;
	}

	public void setThreeinone(String threeinone) {
		this.threeinone = threeinone;
	}


}
