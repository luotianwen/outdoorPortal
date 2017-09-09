package com.op.dto.pointService.insert.fabu;

import java.io.Serializable;
import java.util.List;

import com.op.entity.pointService.PointServiceFeature;

/**
 * 地点服务描述信息表(pointServiceInfo)实体类
 * 
 * @author 世峰户外商城
 * @version Revision: 1.00 Date: 2016-06-23 13:36:42
 */
public class PointServiceInfoDTO implements Serializable {

	/**
	 * 
	 */
	static final long serialVersionUID = 1L;

	// id
	String psi_id;
	// 地点服务id
	String ps_id;
	// 购买须知
	String psi_pay_info;
	// 场馆所提供的服务
	String psi_service;
	// 场馆介绍（编辑器）
	String psi_introduce;
	// 相关介绍（字段具体名称由该场馆类型决定，参考【场馆类型对应字段表】）
	String psi_venue_type_column_value;
	
	// 创建用户
	String create_user_id;

	// 标签集合
	List<PointServiceFeature> tabs;
	
	// 项目集合
	List<PointServiceProjectsDTO> projects;
	

	public String getCreate_user_id() {
		return create_user_id;
	}

	public void setCreate_user_id(String create_user_id) {
		this.create_user_id = create_user_id;
	}

	public List<PointServiceProjectsDTO> getProjects() {
		return projects;
	}

	public void setProjects(List<PointServiceProjectsDTO> projects) {
		this.projects = projects;
	}

	public List<PointServiceFeature> getTabs() {
		return tabs;
	}

	public void setTabs(List<PointServiceFeature> tabs) {
		this.tabs = tabs;
	}

	public String getPsi_id() {
		return psi_id;
	}

	public void setPsi_id(String psi_id) {
		this.psi_id = psi_id;
	}

	public String getPs_id() {
		return ps_id;
	}

	public void setPs_id(String ps_id) {
		this.ps_id = ps_id;
	}

	/**
	 * 购买须知
	 */
	public String getPsi_pay_info() {
		return psi_pay_info;
	}

	public void setPsi_pay_info(String psi_pay_info) {
		this.psi_pay_info = psi_pay_info;
	}

	/**
	 * 场馆所提供的服务
	 */
	public String getPsi_service() {
		return psi_service;
	}

	public void setPsi_service(String psi_service) {
		this.psi_service = psi_service;
	}

	/**
	 * 相关介绍（字段具体名称由该场馆类型决定，参考【场馆类型对应字段表】）
	 */
	public String getPsi_venue_type_column_value() {
		return psi_venue_type_column_value;
	}

	public void setPsi_venue_type_column_value(String psi_venue_type_column_value) {
		this.psi_venue_type_column_value = psi_venue_type_column_value;
	}

	/**
	 * 场馆介绍（编辑器）
	 */
	public String getPsi_introduce() {
		return psi_introduce;
	}

	public void setPsi_introduce(String psi_introduce) {
		this.psi_introduce = psi_introduce;
	}

}
