package com.op.dto.pointService.insert.ruzhu;

import java.io.Serializable;

/**
 * 营业日集合(businessDays)实体类
 * 
 * @author 世峰户外商城
 * @version Revision: 1.00 Date: 2016-06-20 16:35:07
 */
public class BusinessDays implements Serializable {

	/**
	 * 
	 */
	static final long serialVersionUID = 1L;

	// id
	String psbd_id;
	// 营业日
	String psbd_day;
	// 地点服务ID
	String psbd_point_service_id;
	// 地点服务营业日ID
	String psabd_id;

	public String getPsbd_id() {
		return psbd_id;
	}

	public void setPsbd_id(String psbd_id) {
		this.psbd_id = psbd_id;
	}

	/**
	 * 营业日
	 */
	public String getPsbd_day() {
		return psbd_day;
	}

	public void setPsbd_day(String psbd_day) {
		this.psbd_day = psbd_day;
	}

	public String getPsbd_point_service_id() {
		return psbd_point_service_id;
	}

	public void setPsbd_point_service_id(String psbd_point_service_id) {
		this.psbd_point_service_id = psbd_point_service_id;
	}

	public String getPsabd_id() {
		return psabd_id;
	}

	public void setPsabd_id(String psabd_id) {
		this.psabd_id = psabd_id;
	}
	
	

}
