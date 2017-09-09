package com.op.entity.lines;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.op.entity.lines.address.LineAddress;

/**
 * 活动线路分段信息表(lines)实体类
 * 
 * @author 世峰户外商城
 * @version Revision: 1.00 Date: 2016-03-16 14:08:41
 */
public class SubsectionLines implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 分段线路ID
	String l_id;
	// 出行方式
	String l_vehicle;
	// 日期
	Date l_time;
	// 以小时为单位的结束时间
	Date l_to_time;
	// 餐饮
	String l_diet;
	// 住宿
	String l_accommodation;
	// 活动介绍
	String l_active_description;
	// 温馨提示
	String l_prompt;
	// 创建用户
	String l_create_user;
	// 创建时间
	Date l_create_time;
	// 活动ID
	String l_active_id;

	// 线路坐标集合
	String l_line_coordinate;

	// 图片集合
	List<LineImages> lineImages;
	
	// 线路地点集合
	List<LineAddress> lineAddress;
	
	// 行程类型（day or hour）
	String journey_type;
	
	// 坐标起点
	String start;
	// 坐标终点
	String end;
	

	public Date getL_to_time() {
		return l_to_time;
	}

	public void setL_to_time(Date l_to_time) {
		this.l_to_time = l_to_time;
	}

	public String getJourney_type() {
		return journey_type==null?"day":journey_type;
	}

	public void setJourney_type(String journey_type) {
		this.journey_type = journey_type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStart() {
		String[] address = l_line_coordinate.split("\\|");
		this.setStart(address[0]);
		this.setEnd(address[address.length-1]);
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public List<LineImages> getLineImages() {
		return lineImages;
	}

	public void setLineImages(List<LineImages> lineImages) {
		this.lineImages = lineImages;
	}

	public List<LineAddress> getLineAddress() {
		return lineAddress;
	}

	public void setLineAddress(List<LineAddress> lineAddress) {
		this.lineAddress = lineAddress;
	}

	/**
	 * 方法描述：线路坐标集合 返回类型：String
	 * 
	 * @return
	 */
	public String getL_line_coordinate() {
		return l_line_coordinate;
	}

	public void setL_line_coordinate(String l_line_coordinate) {
		String[] address = l_line_coordinate.split("\\|");
		this.setStart(address[0]);
		this.setEnd(address[address.length-1]);
		this.l_line_coordinate = l_line_coordinate;
	}

	/**
	 * 分段线路ID
	 */
	public String getL_id() {
		return l_id;
	}

	public void setL_id(String l_id) {
		this.l_id = l_id;
	}

	/**
	 * 出行方式
	 */
	public String getL_vehicle() {
		return l_vehicle;
	}

	public void setL_vehicle(String l_vehicle) {
		this.l_vehicle = l_vehicle;
	}

	/**
	 * 日期
	 */
	public Date getL_time() {
		return l_time;
	}

	public void setL_time(Date l_time) {
		this.l_time = l_time;
	}

	/**
	 * 餐饮
	 */
	public String getL_diet() {
		return l_diet;
	}

	public void setL_diet(String l_diet) {
		this.l_diet = l_diet;
	}

	/**
	 * 住宿
	 */
	public String getL_accommodation() {
		return l_accommodation;
	}

	public void setL_accommodation(String l_accommodation) {
		this.l_accommodation = l_accommodation;
	}

	/**
	 * 活动介绍
	 */
	public String getL_active_description() {
		return l_active_description;
	}

	public void setL_active_description(String l_active_description) {
		this.l_active_description = l_active_description;
	}

	/**
	 * 温馨提示
	 */
	public String getL_prompt() {
		return l_prompt;
	}

	public void setL_prompt(String l_prompt) {
		this.l_prompt = l_prompt;
	}

	/**
	 * 创建用户
	 */
	public String getL_create_user() {
		return l_create_user;
	}

	public void setL_create_user(String l_create_user) {
		this.l_create_user = l_create_user;
	}

	/**
	 * 创建时间
	 */
	public Date getL_create_time() {
		return l_create_time;
	}

	public void setL_create_time(Date l_create_time) {
		this.l_create_time = l_create_time;
	}

	/**
	 * 活动ID
	 */
	public String getL_active_id() {
		return l_active_id;
	}

	public void setL_active_id(String l_active_id) {
		this.l_active_id = l_active_id;
	}

}
