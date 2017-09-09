package com.op.dto.activity.insertActivity;

import org.springframework.util.StringUtils;

import com.op.util.HTMLEscape;

/**
 * =============================================================
 * 项目名：outdoorPortal
 * 类描述：添加活动DTO，不包括基本数据，基本数据在com.op.dto.activity.insertActivity.ActivityBaseInfoDTO
 * 创建人：Yan
 * 创建时间： 2016-3-14
 * modification list：
 * =============================================================
 */
public class ActivityRestsInfoDTO {

	// 活动ID
	String activityId;
	
	// 当前UserID
	String uId;
	
	// 用户身份类型（领队OR俱乐部）
	int uType;
	
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
	
	// 因为可能涉及到只修改/保存其中某一个属性，所以根据每个属性的set方法标识该实体类要保存的数据存在
	boolean isNull = true;
	
	// 增删改查标识字段
	String crud;
	
	// 动态获取要修改的列名
	String dc;
	
	// 根据返回操作的数据数量来操作完成度
	int dataNum;
	
	// 支付方式
	Integer payType;
	
	// 预订金额
	Double a_reserve_price;
	
	// 活动金额
	Double activityPrice;
	/*-------------------------------------------	get	&&	set --------------------------*/
	
	

	public boolean isNull() {
		return isNull;
	}


	public int getuType() {
		return uType;
	}


	public void setuType(int uType) {
		this.uType = uType;
	}


	public Double getActivityPrice() {
		return activityPrice;
	}




	public void setActivityPrice(Double activityPrice) {
		this.activityPrice = activityPrice;
	}




	public Double getA_reserve_price() {
		return a_reserve_price;
	}


	public void setA_reserve_price(Double a_reserve_price) {
		if(a_reserve_price != null){
			isNull = false;
		}
		this.a_reserve_price = a_reserve_price;
	}


	public Integer getPayType() {
		return payType;
	}


	public void setPayType(Integer payType) {
		this.payType = payType;
	}


	public int getDataNum() {
		return dataNum;
	}


	public void setDataNum(int dataNum) {
		this.dataNum = dataNum;
	}


	public String getDc() {
		return dc;
	}


	public void setDc(String dc) {
		this.dc = dc;
	}


	public String getCrud() {
		return crud;
	}


	public void setCrud(String crud) {
		this.crud = crud;
	}


	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		if(!StringUtils.isEmpty(equipment)){
			isNull = false;	
		}
		this.equipment = equipment;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}


	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		if(!StringUtils.isEmpty(details)){
			isNull = false;	
		}
		this.details = HTMLEscape.htmlEscapeNoEmoji(details);
	}

	public String getCharacteristic() {
		return characteristic;
	}

	public void setCharacteristic(String characteristic) {
		if(!StringUtils.isEmpty(characteristic)){
			isNull = false;	
		}
		this.characteristic = HTMLEscape.htmlEscapeNoEmoji(characteristic);
	}

	public String getTripWatchFocus() {
		return tripWatchFocus;
	}

	public void setTripWatchFocus(String tripWatchFocus) {
		if(!StringUtils.isEmpty(tripWatchFocus)){
			isNull = false;	
		}
		this.tripWatchFocus = HTMLEscape.htmlEscapeNoEmoji(tripWatchFocus);
	}

	public String getA_price_deatil_off() {
		return a_price_deatil_off;
	}

	public void setA_price_deatil_off(String a_price_deatil_off) {
		if(!StringUtils.isEmpty(a_price_deatil_off)){
			isNull = false;	
		}
		this.a_price_deatil_off = HTMLEscape.htmlEscapeNoEmoji(a_price_deatil_off);
	}

	public String getRefundCondition() {
		return refundCondition;
	}

	public void setRefundCondition(String refundCondition) {
		if(!StringUtils.isEmpty(refundCondition)){
			isNull = false;	
		}
		this.refundCondition = HTMLEscape.htmlEscapeNoEmoji(refundCondition);
	}

	public String getA_careful() {
		return a_careful;
	}

	public void setA_careful(String a_careful) {
		if(!StringUtils.isEmpty(a_careful)){
			isNull = false;	
		}
		
		this.a_careful = HTMLEscape.htmlEscapeNoEmoji(a_careful);
	}

	
}
