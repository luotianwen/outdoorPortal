package com.op.entity.pointService;

import java.io.Serializable;
import java.util.Date;

/**
 * 地点服务基本信息表(pointService)实体类
 * 
 * @author 世峰户外商城
 * @version Revision: 1.00 Date: 2016-06-20 11:55:05
 */
public class PointService implements Serializable {

	/**
	 * 
	 */
	static final long serialVersionUID = 1L;

	// id
	int ps_id;
	// 地点中文名
	String ps_zh_name;
	// 地点英文名
	String ps_en_name;
	// 终极类型
	int ps_type;
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
	//最后修改人
	String ps_update;
	//最后修改时间
	Date ps_update_time;
	// 发布状态(10：草稿；20：待审核；30：审核中；40：审核成功；50：审核失败；)
	int ps_state;
	// 审核失败备注
	String ps_error_comment;
	// 审核人
	String ps_check_user_id;
	//认证人ID
	String ps_auth_user_id;
	//认证信息ID
	String ps_auth_id;
	//是否认证（0：否；1：是）
	int ps_is_auth;
	
	
	/**
	 * id
	 */
	public int getPs_id() {
		return ps_id;
	}

	public void setPs_id(int ps_id) {
		this.ps_id = ps_id;
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
	 * 地点英文名
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
	public int getPs_type() {
		return ps_type;
	}

	public void setPs_type(int ps_type) {
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
		this.ps_coordinates = ps_coordinates;
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
	 * 审核失败备注
	 */
	public String getPs_error_comment() {
		return ps_error_comment;
	}

	public void setPs_error_comment(String ps_error_comment) {
		this.ps_error_comment = ps_error_comment;
	}

	/**
	 * 审核人
	 */
	public String getPs_check_user_id() {
		return ps_check_user_id;
	}

	public void setPs_check_user_id(String ps_check_user_id) {
		this.ps_check_user_id = ps_check_user_id;
	}

	/**
	 * 认证人
	 * @return
	 */
	public String getPs_auth_user_id() {
		return ps_auth_user_id;
	}

	public void setPs_auth_user_id(String ps_auth_user_id) {
		this.ps_auth_user_id = ps_auth_user_id;
	}

	/**
	 * 认证信息
	 * @return
	 */
	public String getPs_auth_id() {
		return ps_auth_id;
	}

	public void setPs_auth_id(String ps_auth_id) {
		this.ps_auth_id = ps_auth_id;
	}

	/**
	 * 是否认证
	 * @return
	 */
	public int getPs_is_auth() {
		return ps_is_auth;
	}

	public void setPs_is_auth(int ps_is_auth) {
		this.ps_is_auth = ps_is_auth;
	}

	/**
	 * 最后修改人
	 * @return
	 */
	public String getPs_update() {
		return ps_update;
	}
	/**
	 * 最后修改人
	 * @return
	 */
	public void setPs_update(String ps_update) {
		this.ps_update = ps_update;
	}

	/**
	 * 最后修改时间
	 * @return
	 */
	public Date getPs_update_time() {
		return ps_update_time;
	}
	/**
	 * 最后修改时间
	 * @return
	 */
	public void setPs_update_time(Date ps_update_time) {
		this.ps_update_time = ps_update_time;
	}

}
