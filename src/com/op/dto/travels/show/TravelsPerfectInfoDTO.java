package com.op.dto.travels.show;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class TravelsPerfectInfoDTO {
	private String travelsId;
	// 出发时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date departure_time;
	// 出行天数
	private String travel_days;
	// 人物
	private String travel_person;
	// 人均费用
	private String per_capita_cost;
	//创建人
	private String user_id;
	public String getTravelsId() {
		return travelsId;
	}
	public void setTravelsId(String travelsId) {
		this.travelsId = travelsId;
	}
	public Date getDeparture_time() {
		return departure_time;
	}
	public void setDeparture_time(Date departure_time) {
		this.departure_time = departure_time;
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
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
}
