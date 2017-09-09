package com.op.dto.activity.searchResult;

import java.io.Serializable;
import java.util.Date;

import com.op.util.DateUtil;


/**
 * =============================================================
 * 项目名：outdoorPortal
 * 类描述：搜索聚合页面查询返回对象集合
 * 创建人：Yan
 * 创建时间： 2016-3-1
 * modification list：
 * =============================================================
 */
public class SearchResultDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 活动标题
	String title;
	// 活动ID
	int id;
	// 活动主图
	String a_active_img;
	// 活动收费价格
	double price;
	// 活动开始时间
	Date activityTime;
	// 活动开始时间("yyyy-mm-dd")
	String activityTimeStr;
	// 活动结束时间
	Date endTime;
	// 活动结束时间
	String endTimeStr;
	// 报名截止时间
	Date a_enroll_end_time;
	// 报名截止时间
	String a_enroll_end_timeStr;
	// 确认人数
	int confirmUserNum;
	// 需要人数
	int needUserNum;
	// 已报名人数
	int alreadyInNum;
	// 所在省
	String province;
	// 所在市
	String city;
	// 所在区
	String district;
	// 活动终点省
	String l_province;
	// 活动终点市
	String l_city;
	// 活动终点区
	String l_district;
	// 创建用户名称
	String createUserName;
	// 活动特色
	String characteristic;
	// 经纬度坐标（保存实例： (纬度,经度））
	String coordinates;
	// 出发地
	String a_start_location;
	// 目的地
	String a_end_location;
	
	/*----------------------------------	非对应表字段	-----------------------------*/
	
	// 咨询问答数量
	int consultationNum;
	// 满意度(默认100%)
	Double satisfaction=100d;
	
	

	
	

	/*-----------------------------------------------	get	&&	set	----------------------------------------------*/
	
	
	
	public int getId() {
		return id;
	}
	public String getA_start_location() {
		return a_start_location;
	}
	public void setA_start_location(String a_start_location) {
		this.a_start_location = a_start_location;
	}
	public String getA_end_location() {
		return a_end_location;
	}
	public void setA_end_location(String a_end_location) {
		this.a_end_location = a_end_location;
	}
	public String getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getA_active_img() {
		return a_active_img;
	}
	public void setA_active_img(String a_active_img) {
		this.a_active_img = a_active_img;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getConfirmUserNum() {
		return confirmUserNum;
	}
	public void setConfirmUserNum(int confirmUserNum) {
		this.confirmUserNum = confirmUserNum;
	}
	public int getNeedUserNum() {
		return needUserNum;
	}
	public void setNeedUserNum(int needUserNum) {
		this.needUserNum = needUserNum;
	}
	public int getAlreadyInNum() {
		return alreadyInNum;
	}
	public void setAlreadyInNum(int alreadyInNum) {
		this.alreadyInNum = alreadyInNum;
	}
	public String getProvince() {
		if(province == null || province.equals(city)){
			return "";
		}
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		if(city == null){
			return "";
		}
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		if(district == null){
			return "";
		}
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getL_province() {
		if(l_province == null || l_province.equals(l_city)){
			return "";
		}
		return l_province;
	}
	public void setL_province(String l_province) {
		this.l_province = l_province;
	}
	public String getL_city() {
		if(l_city == null){
			return "";
		}
		return l_city;
	}
	public void setL_city(String l_city) {
		this.l_city = l_city;
	}
	public String getL_district() {
		if(l_district == null){
			return "";
		}
		return l_district;
	}
	public void setL_district(String l_district) {
		this.l_district = l_district;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getCharacteristic() {
		return characteristic;
	}
	public void setCharacteristic(String characteristic) {
		this.characteristic = characteristic;
	}
	public int getConsultationNum() {
		return consultationNum;
	}
	public void setConsultationNum(int consultationNum) {
		this.consultationNum = consultationNum;
	}
	
	public Double getSatisfaction() {
		return satisfaction;
	}
	public void setSatisfaction(Double satisfaction) {
		this.satisfaction = satisfaction;
	}
	public Date getActivityTime() {
		return activityTime;
	}
	public void setActivityTime(Date activityTime) {
		this.activityTimeStr = DateUtil.YYYY_MM_DDgetDay(activityTime);
		this.activityTime = activityTime;
	}
	public String getActivityTimeStr() {
		return activityTimeStr;
	}
	
	
	
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTimeStr = DateUtil.YYYY_MM_DDgetDay(endTime);
		this.endTime = endTime;
	}
	public String getEndTimeStr() {
		return endTimeStr;
	}
	
	
	public Date getA_enroll_end_time() {
		return a_enroll_end_time;
	}
	public void setA_enroll_end_time(Date a_enroll_end_time) {
		this.a_enroll_end_timeStr = DateUtil.getTime(a_enroll_end_time);
		this.a_enroll_end_time = a_enroll_end_time;
	}
	public String getA_enroll_end_timeStr() {
		return a_enroll_end_timeStr;
	}
	
}
