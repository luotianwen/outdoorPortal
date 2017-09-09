package com.op.dto.activity.edit;

import java.io.Serializable;
import java.util.Date;

import com.op.util.HTMLEscape;
/**
 * =============================================================
 * 项目名：outdoorPortal
 * 类描述：编辑草稿状态的活动DTO
 * 创建人：Yan
 * 创建时间： 2016-4-6
 * modification list：
 * =============================================================
 */
public class ActivityDraftDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*-------------------------------	baseInfo	start	-----------------------------------------*/
	// 活动ID
	String activityId;

	// 频道ID
	Integer ch_id;

	// 标题
	String title;

	// 适合人群
	Integer sc_id;
	
	// 儿童年龄段
	Integer a_children_age;
	
	// 活动难度等级
	Integer a_difficulty_type;
	
	// 活动需要人数
	Integer needUserNum;
	
	// 保险ID
	String in_id;
	
	// 咨询电话
	Long a_phone;
	
	// 活动费用
	Double price;
	
	// 开始时间
	Date activityTime;
	
	// 活动结束时间
	Date endTime;
	
	// 报名截止时间
	Date a_enroll_end_time;

	// 出发地
	String a_start_location;

	// 目的地
	String a_end_location;

	// 集合时间
	Date a_gather_time;

	// 集合地点
	String a_gather_location;

	// 活动主图
	String a_active_img;

	// 活动类型集合
	String[] types;

	// 交通方式
	String[] a_traffic_id;

	// 活动标签
	String[] activeTag;

	// 活动景点
	String[] activeScenic;
	/*-------------------------------	baseInfo	end	-----------------------------------------*/
	
	

	/*-------------------------------	restsInfo	start	-----------------------------------------*/
	// 活动描述
	String details;
	
	// 路线特色描述（原：活动特色）
	String characteristic;
	
	// 行程看点描述
	String tripWatchFocus;
	
	// 装备要求
	String equipment;
	
	// 费用不包括
	String a_price_deatil_off;
	
	// 支付和退款说明
	String refundCondition;
	
	// 注意事项(报名须知)
	String a_careful;
	
	// 支付方式
	Integer payType;
	
	// 预订金额
	Double a_reserve_price;
	
	// 活动金额
	Double activityPrice;
	
	// 线路对象
	java.util.List<InsertLineDTO> lines; 
	
	// 保险对象
	com.op.dto.activity.insurances.ActivityInsuranceResultDTO insurance;
	
	// 费用
	java.util.List<com.op.entity.activity.cost.ActiveCorrelationCost> accs;
	
	// 装备
	java.util.List<com.op.entity.activity.equipment.ActiveEquipment> aes;
	/*-------------------------------	restsInfo	end	-----------------------------------------*/

	
	public String getActivityId() {
		return activityId;
	}



	public java.util.List<InsertLineDTO> getLines() {
		return lines;
	}



	public void setLines(java.util.List<InsertLineDTO> lines) {
		this.lines = lines;
	}



	public java.util.List<com.op.entity.activity.equipment.ActiveEquipment> getAes() {
		return aes;
	}

	public void setAes(java.util.List<com.op.entity.activity.equipment.ActiveEquipment> aes) {
		this.aes = aes;
	}

	public java.util.List<com.op.entity.activity.cost.ActiveCorrelationCost> getAccs() {
		return accs;
	}

	public void setAccs(java.util.List<com.op.entity.activity.cost.ActiveCorrelationCost> accs) {
		this.accs = accs;
	}

	public com.op.dto.activity.insurances.ActivityInsuranceResultDTO getInsurance() {
		return insurance;
	}

	public void setInsurance(com.op.dto.activity.insurances.ActivityInsuranceResultDTO insurance) {
		this.insurance = insurance;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public Integer getCh_id() {
		return ch_id;
	}

	public void setCh_id(Integer ch_id) {
		this.ch_id = ch_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getSc_id() {
		return sc_id;
	}

	public void setSc_id(Integer sc_id) {
		this.sc_id = sc_id;
	}

	public Integer getA_children_age() {
		return a_children_age;
	}

	public void setA_children_age(Integer a_children_age) {
		this.a_children_age = a_children_age;
	}

	public Integer getA_difficulty_type() {
		return a_difficulty_type;
	}

	public void setA_difficulty_type(Integer a_difficulty_type) {
		this.a_difficulty_type = a_difficulty_type;
	}

	public Integer getNeedUserNum() {
		return needUserNum;
	}

	public void setNeedUserNum(Integer needUserNum) {
		this.needUserNum = needUserNum;
	}

	public Long getA_phone() {
		return a_phone;
	}

	public void setA_phone(Long a_phone) {
		this.a_phone = a_phone;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getIn_id() {
		return in_id;
	}



	public void setIn_id(String in_id) {
		this.in_id = in_id;
	}



	public Date getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(Date activityTime) {
		this.activityTime = activityTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getA_enroll_end_time() {
		return a_enroll_end_time;
	}

	public void setA_enroll_end_time(Date a_enroll_end_time) {
		this.a_enroll_end_time = a_enroll_end_time;
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

	public Date getA_gather_time() {
		return a_gather_time;
	}

	public void setA_gather_time(Date a_gather_time) {
		this.a_gather_time = a_gather_time;
	}

	public String getA_gather_location() {
		return a_gather_location;
	}

	public void setA_gather_location(String a_gather_location) {
		this.a_gather_location = a_gather_location;
	}

	public String getA_active_img() {
		return a_active_img;
	}

	public void setA_active_img(String a_active_img) {
		this.a_active_img = a_active_img;
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	public String[] getA_traffic_id() {
		return a_traffic_id;
	}

	public void setA_traffic_id(String[] a_traffic_id) {
		this.a_traffic_id = a_traffic_id;
	}

	public String[] getActiveTag() {
		return activeTag;
	}

	public void setActiveTag(String[] activeTag) {
		this.activeTag = activeTag;
	}

	public String[] getActiveScenic() {
		return activeScenic;
	}

	public void setActiveScenic(String[] activeScenic) {
		this.activeScenic = activeScenic;
	}

	public String getDetails() {
		return HTMLEscape.htmlReverseEscape(details);
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getCharacteristic() {
		return HTMLEscape.htmlReverseEscape(characteristic);
	}

	public void setCharacteristic(String characteristic) {
		this.characteristic = characteristic;
	}

	public String getTripWatchFocus() {
		return HTMLEscape.htmlReverseEscape(tripWatchFocus);
	}

	public void setTripWatchFocus(String tripWatchFocus) {
		this.tripWatchFocus = tripWatchFocus;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public String getA_price_deatil_off() {
		return HTMLEscape.htmlReverseEscape(a_price_deatil_off);
	}

	public void setA_price_deatil_off(String a_price_deatil_off) {
		this.a_price_deatil_off = a_price_deatil_off;
	}

	public String getRefundCondition() {
		return HTMLEscape.htmlReverseEscape(refundCondition);
	}

	public void setRefundCondition(String refundCondition) {
		this.refundCondition = refundCondition;
	}

	public String getA_careful() {
		return HTMLEscape.htmlReverseEscape(a_careful);
	}

	public void setA_careful(String a_careful) {
		this.a_careful = a_careful;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Double getA_reserve_price() {
		return a_reserve_price;
	}

	public void setA_reserve_price(Double a_reserve_price) {
		this.a_reserve_price = a_reserve_price;
	}

	public Double getActivityPrice() {
		return activityPrice;
	}

	public void setActivityPrice(Double activityPrice) {
		this.activityPrice = activityPrice;
	}
	
	
}
