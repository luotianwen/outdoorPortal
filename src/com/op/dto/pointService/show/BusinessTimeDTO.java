package com.op.dto.pointService.show;

import java.io.Serializable;
import java.util.List;

import com.op.entity.pointService.BusinessDays;

public class BusinessTimeDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// id
	private int psabd_id;
	// 营业开始时间
	private String psabd_start_date;
	// 营业结束时间
	private String psabd_end_date;
	// 地点服务ID
	private int psabd_point_service_id;
	
	//营业日
	private List<BusinessDays> businessDaysList;
	
	
	public int getPsabd_id() {
		return psabd_id;
	}
	public void setPsabd_id(int psabd_id) {
		this.psabd_id = psabd_id;
	}
	public String getPsabd_start_date() {
		return psabd_start_date;
	}
	public void setPsabd_start_date(String psabd_start_date) {
		this.psabd_start_date = psabd_start_date;
	}
	public String getPsabd_end_date() {
		return psabd_end_date;
	}
	public void setPsabd_end_date(String psabd_end_date) {
		this.psabd_end_date = psabd_end_date;
	}
	public int getPsabd_point_service_id() {
		return psabd_point_service_id;
	}
	public void setPsabd_point_service_id(int psabd_point_service_id) {
		this.psabd_point_service_id = psabd_point_service_id;
	}
	public List<BusinessDays> getBusinessDaysList() {
		return businessDaysList;
	}
	public void setBusinessDaysList(List<BusinessDays> businessDaysList) {
		this.businessDaysList = businessDaysList;
	}

	
}
