package com.op.entity.user_check;

import java.util.Date;

/**
 * 用户身份验证信息表(user_check)实体类
 * 
 * @author Win Zhong
 * @version Revision: 1.00 Date: 2015-12-10 16:27:25
 */
public class UserCheck {

	// 表ID
	String ucId;
	// 用户ID(uses表外键)
	String u_Id;
	// 真实姓名/法人代表姓名
	String realName;
	// 性别
	int gender;
	// 手机号码/法人代表手机
	String mobile;
	// 固定电话/企业固定电话
	String telephone;
	// 证件类型（1：身份证；2：护照；3：军官证；4：港澳回乡证或台胞证）
	int idcardType;
	// 证件号码/法人代表身份证号
	String idcardNum;
	// 居住城市（省份）
	String provinceId;
	// 居住城市（城市）
	String cityId;
	// 居住城市（区县）
	String countyId;
	// EMAIL/企业邮箱
	String email;
	// QQ/法人代表QQ
	String qq;
	// 应急联系人姓名
	String emergencyName;
	// 应急联系人手机
	String emergencyMobile;
	// 与本人关系
	String relation;
	// 户外履历
	String antecedents;
	// 培训经历
	String training;
	// 证件照-正面/法人代表身份证照片
	String idcartFrontUrl;
	// 证件照-反面/法人代表身份证照片
	String idcartBackUrl;
	// 手持证件照/法人手持证件照
	String idcartHandUrl;
	// 企业注册名称
	String enterpriseName;
	// 企业地址
	String address;
	// 企业首页
	String enterpriseHomepage;
	// 营业执照号码
	String licenseNum;
	// 营业执照
	String licensePictureUrl;
	// 经营范围
	String enterpriseIntro;
	// 申请人（1：企业法人代表；2： 非企业法人代表）
	int proposer;
	// 俱乐部名称
	String clubName;
	// 俱乐部logo
	String clubLogo;
	// 俱乐部介绍
	String clubIntro;
	// 审核失败备注
	String ucFailRemarks;
	// 创建时间
	Date ucCreateTime;
	// 审核人
	String ucAuditUserId;
	// 审核时间
	Date ucAuditTime;
	// 身份类型（1：个人发布者；2：企业；）
	int ucType;
	// 申请进度（1：提交申请，2：人工审核中，3：审核完成，4：审核失败）
	int ucProgress;
	

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCountyId() {
		return countyId;
	}

	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}

	/**
	 * 表ID
	 */
	public String getUcId() {
		return ucId;
	}

	public void setUcId(String ucId) {
		this.ucId = ucId;
	}

	/**
	 * 用户ID(uses表外键)
	 */
	public String getU_Id() {
		return u_Id;
	}

	public void setU_Id(String u_Id) {
		this.u_Id = u_Id;
	}

	/**
	 * 真实姓名/法人代表姓名
	 */
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * 性别
	 */
	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	/**
	 * 手机号码/法人代表手机
	 */
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 固定电话/企业固定电话
	 */
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * 证件类型（1：身份证；2：护照；3：军官证；4：港澳回乡证或台胞证）
	 */
	public int getIdcardType() {
		return idcardType;
	}

	public void setIdcardType(int idcardType) {
		this.idcardType = idcardType;
	}

	/**
	 * 证件号码/法人代表身份证号
	 */
	public String getIdcardNum() {
		return idcardNum;
	}

	public void setIdcardNum(String idcardNum) {
		this.idcardNum = idcardNum;
	}

	/**
	 * 居住城市（企业城市）
	 */
	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	/**
	 * EMAIL/企业邮箱
	 */
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * QQ/法人代表QQ
	 */
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	/**
	 * 应急联系人姓名
	 */
	public String getEmergencyName() {
		return emergencyName;
	}

	public void setEmergencyName(String emergencyName) {
		this.emergencyName = emergencyName;
	}

	/**
	 * 应急联系人手机
	 */
	public String getEmergencyMobile() {
		return emergencyMobile;
	}

	public void setEmergencyMobile(String emergencyMobile) {
		this.emergencyMobile = emergencyMobile;
	}

	/**
	 * 与本人关系
	 */
	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	/**
	 * 户外履历
	 */
	public String getAntecedents() {
		return antecedents;
	}

	public void setAntecedents(String antecedents) {
		this.antecedents = antecedents;
	}

	/**
	 * 培训经历
	 */
	public String getTraining() {
		return training;
	}

	public void setTraining(String training) {
		this.training = training;
	}

	/**
	 * 证件照-正面/法人代表身份证照片
	 */
	public String getIdcartFrontUrl() {
		return idcartFrontUrl;
	}

	public void setIdcartFrontUrl(String idcartFrontUrl) {
		this.idcartFrontUrl = idcartFrontUrl;
	}

	/**
	 * 证件照-反面/法人代表身份证照片
	 */
	public String getIdcartBackUrl() {
		return idcartBackUrl;
	}

	public void setIdcartBackUrl(String idcartBackUrl) {
		this.idcartBackUrl = idcartBackUrl;
	}

	/**
	 * 手持证件照/法人手持证件照
	 */
	public String getIdcartHandUrl() {
		return idcartHandUrl;
	}

	public void setIdcartHandUrl(String idcartHandUrl) {
		this.idcartHandUrl = idcartHandUrl;
	}

	/**
	 * 企业注册名称
	 */
	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	/**
	 * 企业地址
	 */
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 企业首页
	 */
	public String getEnterpriseHomepage() {
		return enterpriseHomepage;
	}

	public void setEnterpriseHomepage(String enterpriseHomepage) {
		this.enterpriseHomepage = enterpriseHomepage;
	}

	/**
	 * 营业执照号码
	 */
	public String getLicenseNum() {
		return licenseNum;
	}

	public void setLicenseNum(String licenseNum) {
		this.licenseNum = licenseNum;
	}

	/**
	 * 营业执照
	 */
	public String getLicensePictureUrl() {
		return licensePictureUrl;
	}

	public void setLicensePictureUrl(String licensePictureUrl) {
		this.licensePictureUrl = licensePictureUrl;
	}

	/**
	 * 经营范围
	 */
	public String getEnterpriseIntro() {
		return enterpriseIntro;
	}

	public void setEnterpriseIntro(String enterpriseIntro) {
		this.enterpriseIntro = enterpriseIntro;
	}

	/**
	 * 申请人（1：企业法人代表；2： 非企业法人代表）
	 */
	public int getProposer() {
		return proposer;
	}

	public void setProposer(int proposer) {
		this.proposer = proposer;
	}

	/**
	 * 俱乐部名称
	 */
	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	/**
	 * 俱乐部logo
	 */
	public String getClubLogo() {
		return clubLogo;
	}

	public void setClubLogo(String clubLogo) {
		this.clubLogo = clubLogo;
	}

	/**
	 * 俱乐部介绍
	 */
	public String getClubIntro() {
		return clubIntro;
	}

	public void setClubIntro(String clubIntro) {
		this.clubIntro = clubIntro;
	}

	/**
	 * 审核失败备注
	 */
	public String getUcFailRemarks() {
		return ucFailRemarks;
	}

	public void setUcFailRemarks(String ucFailRemarks) {
		this.ucFailRemarks = ucFailRemarks;
	}

	/**
	 * 创建时间
	 */
	public Date getUcCreateTime() {
		return ucCreateTime;
	}

	public void setUcCreateTime(Date ucCreateTime) {
		this.ucCreateTime = ucCreateTime;
	}

	/**
	 * 审核人
	 */
	public String getUcAuditUserId() {
		return ucAuditUserId;
	}

	public void setUcAuditUserId(String ucAuditUserId) {
		this.ucAuditUserId = ucAuditUserId;
	}

	/**
	 * 审核时间
	 */
	public Date getUcAuditTime() {
		return ucAuditTime;
	}

	public void setUcAuditTime(Date ucAuditTime) {
		this.ucAuditTime = ucAuditTime;
	}

	/**
	 * 身份类型（1：个人发布者；2：企业；）
	 */
	public int getUcType() {
		return ucType;
	}

	public void setUcType(int ucType) {
		this.ucType = ucType;
	}

	/**
	 * 申请进度（1：提交申请，2：人工审核中，3：审核完成，4：审核失败）
	 */
	public int getUcProgress() {
		return ucProgress;
	}

	public void setUcProgress(int ucProgress) {
		this.ucProgress = ucProgress;
	}
}
