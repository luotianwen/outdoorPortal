package com.op.dto.activity.insertActivity.line;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

import com.op.entity.lines.LineImages;
import com.op.entity.lines.address.LineAddress;
import com.op.util.HTMLEscape;

/**
 * 项目名：outdoorPortal 类描述：添加线路实体类 创建人：Yan 创建时间： 2016-1-27 下午1:57:09
 * 最后修改时间：2016-1-27下午1:57:09
 */
public class InsertLineDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 分段线路ID
	String l_id;
	// 出行方式
	String l_vehicle;
	
	// 数据库存储的开始时间
	Date l_time;
	
	// 开始时间（以天为单位）
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date l_time_day;
	
	// 开始时间（以小时为单位）
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	Date l_time_hour;
	
	// 以小时为单位的结束时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
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
	String activityId;
	
	// 线路坐标集合
	String l_line_coordinate;
	
	// 操作类型(前段标识当前操作为线路，保存后保留下来线路ID，便于修改操作)
	String handletype;
	
	// 线路地点集合
	List<LineAddress> didian;

	// 线路图片集合
	List<LineImages> images;
	
	// 行程类型，{day or hour}以天或者以小时为单位
	String journey_type;
	
	// 根据返回操作的数据数量来操作完成度
	int dataNum;
	
	// 起点坐标
	String start;
	
	// 终点坐标
	String end;
	
	
	
	
	public String getStart() {
		if(l_line_coordinate != null){
			String[] a = l_line_coordinate.split("\\|");
			return a[0];
		}
		return "";
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		if(l_line_coordinate != null){
			String[] a = l_line_coordinate.split("\\|");
			return a[a.length-1];
		}
		return "";
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public int getDataNum() {
		return dataNum;
	}

	public void setDataNum(int dataNum) {
		this.dataNum = dataNum;
	}

	public Date getL_to_time() {
		return l_to_time;
	}

	public Date getL_time() {
		return l_time;
	}

	public void setL_time(Date l_time) {
		this.l_time = l_time;
	}

	public void setL_to_time(Date l_to_time) {
		this.l_to_time = l_to_time;
	}

	public String getJourney_type() {
		return journey_type;
	}

	public void setJourney_type(String journey_type) {
		this.journey_type = journey_type;
	}

	/**
	 * 方法描述：l_line_coordinate
	 * 返回类型：String
	 * @return
	 */
	public String getL_line_coordinate() {
		return l_line_coordinate;
	}

	public void setL_line_coordinate(String l_line_coordinate) {
		this.l_line_coordinate = l_line_coordinate;
	}

	/**
	 * 方法描述：线路图片集合
	 * 返回类型：List<LineImages>
	 * @return
	 */
	public List<LineImages> getImages() {
		return images;
	}

	public void setImages(List<LineImages> images) {
		this.images = images;
	}

	/**
	 * 方法描述：线路地点集合
	 * 返回类型：List<Xianludidian>
	 * @return
	 */
	public List<LineAddress> getDidian() {
		return didian;
	}

	public void setDidian(List<LineAddress> didian) {
		this.didian = didian;
	}

	/**
	 * 操作类型
	 */
	public String getHandletype() {
		return handletype;
	}

	public void setHandletype(String handletype) {
		this.handletype = handletype;
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


	public Date getL_time_day() {
		return l_time_day;
	}

	public void setL_time_day(Date l_time_day) {
		this.l_time_day = l_time_day;
	}

	public Date getL_time_hour() {
		return l_time_hour;
	}

	public void setL_time_hour(Date l_time_hour) {
		this.l_time_hour = l_time_hour;
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
		this.l_active_description = HTMLEscape.htmlEscapeNoEmoji(l_active_description);
	}

	/**
	 * 温馨提示
	 */
	public String getL_prompt() {
		return l_prompt;
	}

	public void setL_prompt(String l_prompt) {
		this.l_prompt = HTMLEscape.htmlEscapeNoEmoji(l_prompt);
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
	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
}
