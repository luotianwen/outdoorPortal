package com.op.dto.pointService.insert.ruzhu;

import java.io.Serializable;
import java.util.Date;

/**
 * 地点服务图片(pointServiceImg)实体类
 * 
 * @author 世峰户外商城
 * @version Revision: 1.00 Date: 2016-06-20 16:35:07
 */
public class PointServiceImg implements Serializable {

	/**
	 * 
	 */
	static final long serialVersionUID = 1L;

	// id
	int psi_id;
	// 图片地址
	String psi_url;
	// 上传时间
	Date psi_upload_time = new Date();
	// 上传作者id
	String psi_upload_user_id;
	// 地点服务id
	String psi_point_service_id;

	/**
	 * id
	 */
	public int getPsi_id() {
		return psi_id;
	}

	public void setPsi_id(int psi_id) {
		this.psi_id = psi_id;
	}

	/**
	 * 图片地址
	 */
	public String getPsi_url() {
		return psi_url;
	}

	public void setPsi_url(String psi_url) {
		this.psi_url = psi_url;
	}

	/**
	 * 上传时间
	 */
	public Date getPsi_upload_time() {
		return psi_upload_time;
	}

	public void setPsi_upload_time(Date psi_upload_time) {
		this.psi_upload_time = psi_upload_time;
	}

	/**
	 * 上传作者id
	 */
	public String getPsi_upload_user_id() {
		return psi_upload_user_id;
	}

	public void setPsi_upload_user_id(String psi_upload_user_id) {
		this.psi_upload_user_id = psi_upload_user_id;
	}

	public String getPsi_point_service_id() {
		return psi_point_service_id;
	}

	public void setPsi_point_service_id(String psi_point_service_id) {
		this.psi_point_service_id = psi_point_service_id;
	}

}
