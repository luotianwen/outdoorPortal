package com.op.dto.pointService.insert.ruzhu;


public class InsertPointServiceDate {

	// id
	String psabd_id;
	
	// 营业开始时间
	String startDate;
	
	// 营业结束时间
	String endDate;
	
	// 营业日集合
	String days;
	
	// 地点服务ID
	String psabd_point_service_id;
	
	
	
	
	
	public String getPsabd_point_service_id() {
		return psabd_point_service_id;
	}

	public void setPsabd_point_service_id(String psabd_point_service_id) {
		this.psabd_point_service_id = psabd_point_service_id;
	}

	public String getPsabd_id() {
		return psabd_id;
	}

	public void setPsabd_id(String psabd_id) {
		this.psabd_id = psabd_id;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
