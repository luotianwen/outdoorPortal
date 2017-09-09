package com.op.dto.pointService.update.point;

import java.util.Date;
import java.util.List;

import com.op.dto.pointService.show.BusinessTimeDTO;
import com.op.entity.pointService.PointServiceContact;
import com.op.entity.pointService.PointServiceFeature;

public class PointServiceUpdateDTO {
	// id
	private int ps_id;
	// 地点中文名
	private String ps_zh_name;
	// 地点英文名
	private String ps_en_name;
	// 终极类型
	private String ps_type;
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
	// 创建时间
	private Date ps_create_time;
	// 创建用户id
	private String ps_create_user_id;
	// 发布状态(10：草稿；20：待审核；30：审核中；40：审核成功；50：审核失败；)
	private int ps_state;
	
	// 标签名
	private List<PointServiceFeature> pointServiceFeatureList;

	// 商家联系方式
	private List<PointServiceContact> pointServiceContactList;
	
	// 营业时间
	private List<BusinessTimeDTO> businessTimeDTOList;
	
	//场馆类型
	private PointTypeDTO pointTypeDTO;
	
	public int getPs_id() {
		return ps_id;
	}

	public void setPs_id(int ps_id) {
		this.ps_id = ps_id;
	}

	public String getPs_zh_name() {
		return ps_zh_name;
	}

	public void setPs_zh_name(String ps_zh_name) {
		this.ps_zh_name = ps_zh_name;
	}

	public String getPs_en_name() {
		return ps_en_name;
	}

	public void setPs_en_name(String ps_en_name) {
		this.ps_en_name = ps_en_name;
	}

	public String getPs_type() {
		return ps_type;
	}

	public void setPs_type(String ps_type) {
		this.ps_type = ps_type;
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

	public String getPs_traffic() {
		return ps_traffic;
	}

	public void setPs_traffic(String ps_traffic) {
		this.ps_traffic = ps_traffic;
	}

	public Date getPs_create_time() {
		return ps_create_time;
	}

	public void setPs_create_time(Date ps_create_time) {
		this.ps_create_time = ps_create_time;
	}

	public String getPs_create_user_id() {
		return ps_create_user_id;
	}

	public void setPs_create_user_id(String ps_create_user_id) {
		this.ps_create_user_id = ps_create_user_id;
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

	public List<BusinessTimeDTO> getBusinessTimeDTOList() {
		return businessTimeDTOList;
	}

	public void setBusinessTimeDTOList(List<BusinessTimeDTO> businessTimeDTOList) {
		this.businessTimeDTOList = businessTimeDTOList;
	}

	public PointTypeDTO getPointTypeDTO() {
		return pointTypeDTO;
	}

	public void setPointTypeDTO(PointTypeDTO pointTypeDTO) {
		this.pointTypeDTO = pointTypeDTO;
	}

	
}
