package com.op.dto.pointService.update.project;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.op.dto.pointService.insert.fabu.ProjectCrowdTypeDTO;
import com.op.entity.pointService.PointServiceFeature;
import com.op.entity.pointService.project.PointProjectsImg;

public class ProjectUpdateDTO {
	// id
	private String psp_id;
	// 场馆表id
	private String psp_venue_id;
	// 项目名称
	private String psp_item_name;
	// 项目原价
	private double psp_item_price;
	// 项目打折价
	private double psp_discount_price;
	// 项目内容
	private String psp_item_comment;
	// 有效期start
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date psp_validity_date_start;
	// 有效期end
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date psp_validity_date_end;
	// 修改关联ID（标识多条数据为历史备份数据）
	private int psp_update_id;
	// 能否退款（0：是；1：否）
	private int psp_is_refund;
	// 是否支持预约（0：否；1：是）
	private int psp_is_yuyue;
	// 预约时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date psp_yuyue_time;
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
	// 创建人
	private String psp_create;

	// 适合人群
	List<ProjectCrowdTypeDTO> crowds;

	// 项目图片集合
	List<PointProjectsImg> imgs;

	// 标签集合
	List<PointServiceFeature> tabs;

	// 购买须知
	private String psi_pay_info;
	// 场馆所提供的服务
	private String psi_service;
	// 场馆介绍（编辑器）
	private String psi_introduce;
	
	
	
	public String getPsp_id() {
		return psp_id;
	}
	public void setPsp_id(String psp_id) {
		this.psp_id = psp_id;
	}
	public String getPsp_venue_id() {
		return psp_venue_id;
	}
	public void setPsp_venue_id(String psp_venue_id) {
		this.psp_venue_id = psp_venue_id;
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
	public int getPsp_update_id() {
		return psp_update_id;
	}
	public void setPsp_update_id(int psp_update_id) {
		this.psp_update_id = psp_update_id;
	}
	public int getPsp_is_refund() {
		return psp_is_refund;
	}
	public void setPsp_is_refund(int psp_is_refund) {
		this.psp_is_refund = psp_is_refund;
	}
	public int getPsp_is_yuyue() {
		return psp_is_yuyue;
	}
	public void setPsp_is_yuyue(int psp_is_yuyue) {
		this.psp_is_yuyue = psp_is_yuyue;
	}
	public Date getPsp_yuyue_time() {
		return psp_yuyue_time;
	}
	public void setPsp_yuyue_time(Date psp_yuyue_time) {
		this.psp_yuyue_time = psp_yuyue_time;
	}
	public String getPsp_appointment() {
		return psp_appointment;
	}
	public void setPsp_appointment(String psp_appointment) {
		this.psp_appointment = psp_appointment;
	}
	public String getPsp_reminder() {
		return psp_reminder;
	}
	public void setPsp_reminder(String psp_reminder) {
		this.psp_reminder = psp_reminder;
	}
	public String getPsp_not_time() {
		return psp_not_time;
	}
	public void setPsp_not_time(String psp_not_time) {
		this.psp_not_time = psp_not_time;
	}
	public String getPsp_limit() {
		return psp_limit;
	}
	public void setPsp_limit(String psp_limit) {
		this.psp_limit = psp_limit;
	}
	public String getPsp_rule() {
		return psp_rule;
	}
	public void setPsp_rule(String psp_rule) {
		this.psp_rule = psp_rule;
	}
	public String getPsp_people_number() {
		return psp_people_number;
	}
	public void setPsp_people_number(String psp_people_number) {
		this.psp_people_number = psp_people_number;
	}
	public String getPsp_create() {
		return psp_create;
	}
	public void setPsp_create(String psp_create) {
		this.psp_create = psp_create;
	}
	public List<ProjectCrowdTypeDTO> getCrowds() {
		return crowds;
	}
	public void setCrowds(List<ProjectCrowdTypeDTO> crowds) {
		this.crowds = crowds;
	}
	public List<PointProjectsImg> getImgs() {
		return imgs;
	}
	public void setImgs(List<PointProjectsImg> imgs) {
		this.imgs = imgs;
	}
	public List<PointServiceFeature> getTabs() {
		return tabs;
	}
	public void setTabs(List<PointServiceFeature> tabs) {
		this.tabs = tabs;
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
	public String getPsi_introduce() {
		return psi_introduce;
	}
	public void setPsi_introduce(String psi_introduce) {
		this.psi_introduce = psi_introduce;
	}
	
	
	
}
