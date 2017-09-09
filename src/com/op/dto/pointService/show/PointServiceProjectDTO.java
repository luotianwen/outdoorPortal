package com.op.dto.pointService.show;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PointServiceProjectDTO {
	// 项目id
	private int psp_id;
	// 项目名称
	private String psp_item_name;
	// 项目原价
	private double psp_item_price;
	// 项目打折价
	private double psp_discount_price;
	// 有效期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date psp_validity_date_start;
	// 有效期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date psp_validity_date_end;
	// 不可用日期
	private String psp_not_time;
	// 限购限用提醒
	private String psp_limit;
	// 规则提醒
	private String psp_rule;
	//项目状态(0:待审核;1:审核成功;2:审核失败)
	private int psp_state;
	
	
	//项目图片
	private String pointProjectsImg;
	
	//购买人数
	private String purchaseNumber;
	
	//评论人数
	private String commentNumber;

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

	public String getPointProjectsImg() {
		return pointProjectsImg;
	}

	public void setPointProjectsImg(String pointProjectsImg) {
		this.pointProjectsImg = pointProjectsImg;
	}

	public String getPurchaseNumber() {
		return purchaseNumber;
	}

	public void setPurchaseNumber(String purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}

	public String getCommentNumber() {
		return commentNumber;
	}

	public void setCommentNumber(String commentNumber) {
		this.commentNumber = commentNumber;
	}

	public int getPsp_state() {
		return psp_state;
	}

	public void setPsp_state(int psp_state) {
		this.psp_state = psp_state;
	}
	
}
