package com.op.dto.pointService.order;


public class OrderEvaluateInfo {
	// 订单序号
	private String pso_id;
	// 项目ID
	private String pso_project_id;
	// 项目名称
	private String pso_project_name;
	// 地点服务ID
	private String pso_point_service_id;
	// 场馆中文名
	private String pso_zh_name;
	//评价数量
	private String evaluateNum;
	//项目图片
	private String pointProjectsImg;
	
	public String getPso_id() {
		return pso_id;
	}
	public void setPso_id(String pso_id) {
		this.pso_id = pso_id;
	}
	public String getPso_project_id() {
		return pso_project_id;
	}
	public void setPso_project_id(String pso_project_id) {
		this.pso_project_id = pso_project_id;
	}
	public String getPso_project_name() {
		return pso_project_name;
	}
	public void setPso_project_name(String pso_project_name) {
		this.pso_project_name = pso_project_name;
	}
	public String getPso_point_service_id() {
		return pso_point_service_id;
	}
	public void setPso_point_service_id(String pso_point_service_id) {
		this.pso_point_service_id = pso_point_service_id;
	}
	public String getPso_zh_name() {
		return pso_zh_name;
	}
	public void setPso_zh_name(String pso_zh_name) {
		this.pso_zh_name = pso_zh_name;
	}
	public String getEvaluateNum() {
		return evaluateNum;
	}
	public void setEvaluateNum(String evaluateNum) {
		this.evaluateNum = evaluateNum;
	}
	public String getPointProjectsImg() {
		return pointProjectsImg;
	}
	public void setPointProjectsImg(String pointProjectsImg) {
		this.pointProjectsImg = pointProjectsImg;
	}
	
}
