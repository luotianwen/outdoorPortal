package com.op.dto.pointService.insert.ruzhu;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.op.util.StringUtil;

/**
 * 地点服务基本信息表(pointService)实体类
 * 
 * @author 世峰户外商城
 * @version Revision: 1.00 Date: 2016-06-20 11:55:05
 */
public class InsertPointServiceDTO implements Serializable {

	/**
	 * 
	 */
	static final long serialVersionUID = 1L;

	// id
	String ps_id;
	
	// 地点中文名
	String ps_zh_name;
	
	// 地点
	String ps_en_name;
	
	// 终极类型
	String ps_type;
	
	// 省id
	String ps_province_id;
	
	// 市id
	String ps_city_id;
	
	// 区/县id
	String ps_countys_id;
	
	// 位置
	String ps_address;
	
	// 位置经纬度坐标（保存实例： (纬度,经度））
	String ps_coordinates;
	
	// 交通
	String ps_traffic;
	
	// 创建时间
	Date ps_create_time;
	
	// 创建用户id
	String ps_create_user_id;
	
	// 发布状态(10：草稿；20：待审核；30：审核中；40：审核成功；50：审核失败；)
	int ps_state;

	//修改地点服务ID
	String ps_point_service_id;
	
	//新增标识（0：是：1：否）
	String ps_is_add;
	
	// 联系方式
	List<String> contact;
	
	// 营业日组合
	List<InsertPointServiceDate> dates;
	
	// 商户图片
	List<PointServiceImg> imgSrc;
	
	public String getPs_id() {
		return ps_id;
	}

	public void setPs_id(String ps_id) {
		this.ps_id = ps_id;
	}

	/**
	 * 商户图片
	 */

	public List<PointServiceImg> getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(List<PointServiceImg> imgSrc) {
		this.imgSrc = imgSrc;
	}

	/**
	 * 营业日组合
	 */
	public List<InsertPointServiceDate> getDates() {
		return dates;
	}

	public void setDates(List<InsertPointServiceDate> dates) {
		this.dates = dates;
	}

	/**
	 * 联系方式
	 */
	public List<String> getContact() {
		return contact;
	}

	public void setContact(List<String> contact) {
		this.contact = contact;
	}

	/**
	 * 地点中文名
	 */
	public String getPs_zh_name() {
		return ps_zh_name;
	}

	public void setPs_zh_name(String ps_zh_name) {
		this.ps_zh_name = ps_zh_name;
	}

	/**
	 * 地点
	 */
	public String getPs_en_name() {
		return ps_en_name;
	}

	public void setPs_en_name(String ps_en_name) {
		this.ps_en_name = ps_en_name;
	}

	/**
	 * 终极类型
	 */

	public String getPs_type() {
		return ps_type;
	}

	public void setPs_type(String ps_type) {
		this.ps_type = ps_type;
	}

	/**
	 * 省id
	 */
	public String getPs_province_id() {
		return ps_province_id;
	}

	public void setPs_province_id(String ps_province_id) {
		this.ps_province_id = ps_province_id;
	}

	/**
	 * 市id
	 */
	public String getPs_city_id() {
		return ps_city_id;
	}

	public void setPs_city_id(String ps_city_id) {
		this.ps_city_id = ps_city_id;
	}

	/**
	 * 区/县id
	 */
	public String getPs_countys_id() {
		return ps_countys_id;
	}

	public void setPs_countys_id(String ps_countys_id) {
		this.ps_countys_id = ps_countys_id;
	}

	/**
	 * 位置
	 */
	public String getPs_address() {
		return ps_address;
	}

	public void setPs_address(String ps_address) {
		this.ps_address = ps_address;
	}

	/**
	 * 位置经纬度坐标（保存实例： (纬度,经度））
	 */
	public String getPs_coordinates() {
		return ps_coordinates;
	}

	public void setPs_coordinates(String ps_coordinates) {
		if(!StringUtil.isEmpty(ps_coordinates)){
			String[] array = ps_coordinates.split(","); 
			this.ps_coordinates = array[1]+","+array[0];
		}
	}

	/**
	 * 交通
	 */
	public String getPs_traffic() {
		return ps_traffic;
	}

	public void setPs_traffic(String ps_traffic) {
		this.ps_traffic = ps_traffic;
	}

	/**
	 * 创建时间
	 */
	public Date getPs_create_time() {
		return ps_create_time;
	}

	public void setPs_create_time(Date ps_create_time) {
		this.ps_create_time = ps_create_time;
	}

	/**
	 * 创建用户id
	 */
	public String getPs_create_user_id() {
		return ps_create_user_id;
	}

	public void setPs_create_user_id(String ps_create_user_id) {
		this.ps_create_user_id = ps_create_user_id;
	}

	/**
	 * 发布状态(10：草稿；20：待审核；30：审核中；40：审核成功；50：审核失败；)
	 */
	public int getPs_state() {
		return ps_state;
	}

	public void setPs_state(int ps_state) {
		this.ps_state = ps_state;
	}

	/**
	 * 修改地点服务ID
	 * @return
	 */
	public String getPs_point_service_id() {
		return ps_point_service_id;
	}

	public void setPs_point_service_id(String ps_point_service_id) {
		this.ps_point_service_id = ps_point_service_id;
	}

	/**
	 * 新增标识（0：是：1：否）
	 * @return
	 */
	public String getPs_is_add() {
		return ps_is_add;
	}

	public void setPs_is_add(String ps_is_add) {
		this.ps_is_add = ps_is_add;
	}

}
