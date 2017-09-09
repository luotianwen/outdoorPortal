package com.op.dto.travels.show;

import java.util.Date;

public class UpdateTravelsNewInfo {

	// 点赞数量
	int praiseNum;
	
	// 收藏数量
	int followNum;
	
	// 被查看次数
	int seeNum;
	
	// 当前用户点赞状态
	boolean currentUserPraiseState;
	
	// 当前用户收藏状态
	boolean currentUserFollowState;
	
	//出发日期
	Date departure_time;
	String departure_time_str;
	//出行天数
	String travel_days;
	//人物
	String travel_person;
	//人均费用
	String per_capita_cost;
	
	// 当前用户信息
	String uId;
	
	String uName;
	
	String uHeadImg;
	
	String uType;
	
	

	public String getuType() {
		return uType;
	}

	public void setuType(String uType) {
		this.uType = uType;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuHeadImg() {
		return uHeadImg;
	}

	public void setuHeadImg(String uHeadImg) {
		this.uHeadImg = uHeadImg;
	}

	public int getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(int praiseNum) {
		this.praiseNum = praiseNum;
	}

	public int getFollowNum() {
		return followNum;
	}

	public void setFollowNum(int followNum) {
		this.followNum = followNum;
	}

	public int getSeeNum() {
		return seeNum;
	}

	public void setSeeNum(int seeNum) {
		this.seeNum = seeNum;
	}

	public boolean isCurrentUserPraiseState() {
		return currentUserPraiseState;
	}

	public void setCurrentUserPraiseState(boolean currentUserPraiseState) {
		this.currentUserPraiseState = currentUserPraiseState;
	}

	public boolean isCurrentUserFollowState() {
		return currentUserFollowState;
	}

	public void setCurrentUserFollowState(boolean currentUserFollowState) {
		this.currentUserFollowState = currentUserFollowState;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public Date getDeparture_time() {
		return departure_time;
	}

	public void setDeparture_time(Date departure_time) {
		this.departure_time = departure_time;
	}

	public String getDeparture_time_str() {
		return departure_time_str;
	}

	public void setDeparture_time_str(String departure_time_str) {
		this.departure_time_str = departure_time_str;
	}

	public String getTravel_days() {
		return travel_days;
	}

	public void setTravel_days(String travel_days) {
		this.travel_days = travel_days;
	}

	public String getTravel_person() {
		return travel_person;
	}

	public void setTravel_person(String travel_person) {
		this.travel_person = travel_person;
	}

	public String getPer_capita_cost() {
		return per_capita_cost;
	}

	public void setPer_capita_cost(String per_capita_cost) {
		this.per_capita_cost = per_capita_cost;
	}
	
	
}
