package com.op.dto.pointService.show;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.op.entity.pointService.PointEvaluateImg;

public class PointEvaluateDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// id
	private int pse_id;
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
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date pse_create_time;
	// 评价项目ID
	private int pse_project_id;
	// 评价项目名
	private String psp_item_name;
	
	// 评价订单ID
	private int pse_venue_order_id;
	// 人均(元)
	private double pse_price;

	// 项目图片
	private List<PointEvaluateImg> pointEvaluateImgList;

	// 点赞数量
	private String likeCount;

	// 评论人ID
	private String uId;
	// 用户昵称 OR 法定代表人姓名
	protected String uName;
	// 用户头像
	protected String uHeadImg;
	
	public int getPse_id() {
		return pse_id;
	}
	public void setPse_id(int pse_id) {
		this.pse_id = pse_id;
	}
	public int getPs_id() {
		return ps_id;
	}
	public void setPs_id(int ps_id) {
		this.ps_id = ps_id;
	}
	public int getPse_star() {
		return pse_star;
	}
	public void setPse_star(int pse_star) {
		this.pse_star = pse_star;
	}
	public String getPse_evaluate_comment() {
		return pse_evaluate_comment;
	}
	public void setPse_evaluate_comment(String pse_evaluate_comment) {
		this.pse_evaluate_comment = pse_evaluate_comment;
	}
	public String getPse_park_info() {
		return pse_park_info;
	}
	public void setPse_park_info(String pse_park_info) {
		this.pse_park_info = pse_park_info;
	}
	public String getPse_create_user_id() {
		return pse_create_user_id;
	}
	public void setPse_create_user_id(String pse_create_user_id) {
		this.pse_create_user_id = pse_create_user_id;
	}
	public Date getPse_create_time() {
		return pse_create_time;
	}
	public void setPse_create_time(Date pse_create_time) {
		this.pse_create_time = pse_create_time;
	}
	public int getPse_project_id() {
		return pse_project_id;
	}
	public void setPse_project_id(int pse_project_id) {
		this.pse_project_id = pse_project_id;
	}
	public int getPse_venue_order_id() {
		return pse_venue_order_id;
	}
	public void setPse_venue_order_id(int pse_venue_order_id) {
		this.pse_venue_order_id = pse_venue_order_id;
	}
	public double getPse_price() {
		return pse_price;
	}
	public void setPse_price(double pse_price) {
		this.pse_price = pse_price;
	}
	public List<PointEvaluateImg> getPointEvaluateImgList() {
		return pointEvaluateImgList;
	}
	public void setPointEvaluateImgList(List<PointEvaluateImg> pointEvaluateImgList) {
		this.pointEvaluateImgList = pointEvaluateImgList;
	}
	public String getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(String likeCount) {
		this.likeCount = likeCount;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuHeadImg() {
		return uHeadImg;
	}
	public void setuHeadImg(String uHeadImg) {
		this.uHeadImg = uHeadImg;
	}
	public String getPsp_item_name() {
		return psp_item_name;
	}
	public void setPsp_item_name(String psp_item_name) {
		this.psp_item_name = psp_item_name;
	}

}
