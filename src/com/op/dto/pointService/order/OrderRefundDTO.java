package com.op.dto.pointService.order;

import java.util.List;

import com.op.entity.pointService.order.PointServiceUniqueness;

public class OrderRefundDTO {
	// 订单序号
	private String pso_id;
	// 项目ID
	private String pso_project_id;
	// 项目名称
	private String pso_project_name;
	// 项目打折价
	private double pso_discount_price;
	// 订单总金额
	private double pso_price;
	// 地点服务ID
	private String pso_point_service_id;
	//场馆中文名
	private String pso_zh_name;
	
	//识别码
	List<PointServiceUniqueness> pointServiceUniquenessList;

	
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

	public double getPso_discount_price() {
		return pso_discount_price;
	}

	public void setPso_discount_price(double pso_discount_price) {
		this.pso_discount_price = pso_discount_price;
	}

	public double getPso_price() {
		return pso_price;
	}

	public void setPso_price(double pso_price) {
		this.pso_price = pso_price;
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

	public List<PointServiceUniqueness> getPointServiceUniquenessList() {
		return pointServiceUniquenessList;
	}

	public void setPointServiceUniquenessList(
			List<PointServiceUniqueness> pointServiceUniquenessList) {
		this.pointServiceUniquenessList = pointServiceUniquenessList;
	}
	
}
