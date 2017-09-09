package com.op.dto.pointService.show;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.op.entity.pointService.PointComboCrowdType;
import com.op.entity.pointService.PointServiceContact;
import com.op.entity.pointService.PointServiceFeature;
import com.op.entity.pointService.project.PointProjectsImg;

public class PointServiceProjectShowDTO {
	// 场馆ID
	private String ps_id;
	// 中文名
	private String ps_zh_name;
	// 省id
	private String ps_province_id;
	// 市id
	private String ps_city_id;
	// 区/县id
	private String ps_countys_id;
	// 位置
	private String ps_address;
	// 位置经纬度坐标（保存实例： (纬度,经度））
	private String ps_coordinates;
	// 交通
	private String ps_traffic;
	// 发布状态(10：草稿；20：待审核；30：审核中；40：审核成功；50：审核失败；)
	private int ps_state;

	// 标签名
	private List<PointServiceFeature> pointServiceFeatureList;

	// 商家联系方式
	private List<PointServiceContact> pointServiceContactList;

	// 项目id
	private int psp_id;
	// 项目名称
	private String psp_item_name;
	// 项目原价
	private double psp_item_price;
	// 项目打折价
	private double psp_discount_price;
	// 项目备注
	private String psp_item_comment;
	// 有效期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date psp_validity_date_start;
	// 有效期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date psp_validity_date_end;
	// 是否已经过期（0：否；1：是）
	private int psp_is_efficacy;
	// 能否退款（0：是；1：否）
	private int psp_is_refund;
	// 预约提醒(必填)
	private String psp_appointment;
	// 温馨提示(必填)
	private String psp_reminder;
	// 不可用日期
	private String psp_not_time;
	// 限购限用提醒
	private String psp_limit;
	// 规则提醒
	private String psp_rule;
	// 适用人数
	private String psp_people_number;

	// 项目图片
	private List<PointProjectsImg> pointProjectsImgList;

	// 适合人群
	private List<PointComboCrowdType> pointComboCrowdTypeList;

	// 购买须知
	private String psi_pay_info;
	// 场馆所提供的服务
	private String psi_service;
	// 相关介绍（字段具体名称由该场馆类型决定，参考【场馆类型对应字段表】）
	private String psi_venue_type_column_value;
	// 场馆介绍（编辑器）
	private String psi_introduce;

	// 营业时间
	private List<BusinessTimeDTO> businessTimeDTOList;

	public String getPs_id() {
		return ps_id;
	}

	public void setPs_id(String ps_id) {
		this.ps_id = ps_id;
	}

	public String getPs_zh_name() {
		return ps_zh_name;
	}

	public void setPs_zh_name(String ps_zh_name) {
		this.ps_zh_name = ps_zh_name;
	}

	public String getPs_province_id() {
		return ps_province_id;
	}

	public void setPs_province_id(String ps_province_id) {
		this.ps_province_id = ps_province_id;
	}

	public String getPs_city_id() {
		return ps_city_id;
	}

	public void setPs_city_id(String ps_city_id) {
		this.ps_city_id = ps_city_id;
	}

	public String getPs_countys_id() {
		return ps_countys_id;
	}

	public void setPs_countys_id(String ps_countys_id) {
		this.ps_countys_id = ps_countys_id;
	}

	public String getPs_address() {
		return ps_address;
	}

	public void setPs_address(String ps_address) {
		this.ps_address = ps_address;
	}

	public String getPs_coordinates() {
		return ps_coordinates;
	}

	public void setPs_coordinates(String ps_coordinates) {
		this.ps_coordinates = ps_coordinates;
	}

	public int getPs_state() {
		return ps_state;
	}

	public void setPs_state(int ps_state) {
		this.ps_state = ps_state;
	}

	public List<PointServiceFeature> getPointServiceFeatureList() {
		return pointServiceFeatureList;
	}

	public void setPointServiceFeatureList(
			List<PointServiceFeature> pointServiceFeatureList) {
		this.pointServiceFeatureList = pointServiceFeatureList;
	}

	public List<PointServiceContact> getPointServiceContactList() {
		return pointServiceContactList;
	}

	public void setPointServiceContactList(
			List<PointServiceContact> pointServiceContactList) {
		this.pointServiceContactList = pointServiceContactList;
	}

	public int getPsp_id() {
		return psp_id;
	}

	public void setPsp_id(int psp_id) {
		this.psp_id = psp_id;
	}

	public String getPsp_item_name() {
		return psp_item_name;
	}

	public void setPsp_item_name(String psp_item_name) {
		this.psp_item_name = psp_item_name;
	}

	public double getPsp_item_price() {
		return psp_item_price;
	}

	public void setPsp_item_price(double psp_item_price) {
		this.psp_item_price = psp_item_price;
	}

	public double getPsp_discount_price() {
		return psp_discount_price;
	}

	public void setPsp_discount_price(double psp_discount_price) {
		this.psp_discount_price = psp_discount_price;
	}

	public String getPsp_item_comment() {
		return psp_item_comment;
	}

	public void setPsp_item_comment(String psp_item_comment) {
		this.psp_item_comment = psp_item_comment;
	}

	public Date getPsp_validity_date_start() {
		return psp_validity_date_start;
	}

	public void setPsp_validity_date_start(Date psp_validity_date_start) {
		this.psp_validity_date_start = psp_validity_date_start;
	}

	public Date getPsp_validity_date_end() {
		return psp_validity_date_end;
	}

	public void setPsp_validity_date_end(Date psp_validity_date_end) {
		this.psp_validity_date_end = psp_validity_date_end;
	}

	public int getPsp_is_efficacy() {
		return psp_is_efficacy;
	}

	public void setPsp_is_efficacy(int psp_is_efficacy) {
		this.psp_is_efficacy = psp_is_efficacy;
	}

	public int getPsp_is_refund() {
		return psp_is_refund;
	}

	public void setPsp_is_refund(int psp_is_refund) {
		this.psp_is_refund = psp_is_refund;
	}

	public List<PointProjectsImg> getPointProjectsImgList() {
		return pointProjectsImgList;
	}

	public void setPointProjectsImgList(
			List<PointProjectsImg> pointProjectsImgList) {
		this.pointProjectsImgList = pointProjectsImgList;
	}

	public List<PointComboCrowdType> getPointComboCrowdTypeList() {
		return pointComboCrowdTypeList;
	}

	public void setPointComboCrowdTypeList(
			List<PointComboCrowdType> pointComboCrowdTypeList) {
		this.pointComboCrowdTypeList = pointComboCrowdTypeList;
	}

	public String getPsi_pay_info() {
		return psi_pay_info;
	}

	public void setPsi_pay_info(String psi_pay_info) {
		this.psi_pay_info = psi_pay_info;
	}

	public String getPsi_service() {
		return psi_service;
	}

	public void setPsi_service(String psi_service) {
		this.psi_service = psi_service;
	}

	public String getPsi_venue_type_column_value() {
		return psi_venue_type_column_value;
	}

	public void setPsi_venue_type_column_value(
			String psi_venue_type_column_value) {
		this.psi_venue_type_column_value = psi_venue_type_column_value;
	}

	public String getPsi_introduce() {
		return psi_introduce;
	}

	public void setPsi_introduce(String psi_introduce) {
		this.psi_introduce = psi_introduce;
	}

	
	public String getPs_traffic() {
		return ps_traffic;
	}

	public void setPs_traffic(String ps_traffic) {
		this.ps_traffic = ps_traffic;
	}

	public List<BusinessTimeDTO> getBusinessTimeDTOList() {
		return businessTimeDTOList;
	}

	public void setBusinessTimeDTOList(List<BusinessTimeDTO> businessTimeDTOList) {
		this.businessTimeDTOList = businessTimeDTOList;
	}

	/**
	 * 预约提醒(必填)
	 * 
	 * @return
	 */
	public String getPsp_appointment() {
		return psp_appointment;
	}

	/**
	 * 预约提醒(必填)
	 * 
	 * @param type
	 */
	public void setPsp_appointment(String psp_appointment) {
		this.psp_appointment = psp_appointment;
	}

	/**
	 * 温馨提示(必填)
	 * 
	 * @return
	 */
	public String getPsp_reminder() {
		return psp_reminder;
	}

	/**
	 * 温馨提示(必填)
	 * 
	 * @param type
	 */
	public void setPsp_reminder(String psp_reminder) {
		this.psp_reminder = psp_reminder;
	}

	/**
	 * 不可用日期
	 * 
	 * @return
	 */
	public String getPsp_not_time() {
		return psp_not_time;
	}

	/**
	 * 不可用日期
	 * 
	 * @param type
	 */
	public void setPsp_not_time(String psp_not_time) {
		this.psp_not_time = psp_not_time;
	}

	/**
	 * 限购限用提醒
	 * 
	 * @return
	 */
	public String getPsp_limit() {
		return psp_limit;
	}

	/**
	 * 限购限用提醒
	 * 
	 * @param type
	 */
	public void setPsp_limit(String psp_limit) {
		this.psp_limit = psp_limit;
	}

	/**
	 * 规则提醒
	 * 
	 * @return
	 */
	public String getPsp_rule() {
		return psp_rule;
	}

	/**
	 * 规则提醒
	 * 
	 * @param type
	 */
	public void setPsp_rule(String psp_rule) {
		this.psp_rule = psp_rule;
	}

	/**
	 * 适用人数
	 * 
	 * @return
	 */
	public String getPsp_people_number() {
		return psp_people_number;
	}

	/**
	 * 适用人数
	 * 
	 * @param type
	 */
	public void setPsp_people_number(String psp_people_number) {
		this.psp_people_number = psp_people_number;
	}
}
