package com.op.dto.pointService.insert.fabu;

import java.io.Serializable;
import java.util.Date;

/**
 * 地点项目图片(pointProjectsImg)实体类
 * 
 * @author 世峰户外商城
 * @version Revision: 1.00 Date: 2016-06-23 13:36:41
 */
public class PointProjectsImgDTO implements Serializable {

	/**
	 * 
	 */
	static final long serialVersionUID = 1L;

	// id
	String ppi_id;
	// 场馆项目ID
	String ppi_venue_project_id;
	// 图片路径
	String ppi_url;
	// 图片顺序
	int ppi_order;
	// 上传时间
	Date pi_upload_time;
	// 上传作者id
	String pi_upload_user_id;


	public String getPpi_id() {
		return ppi_id;
	}

	public void setPpi_id(String ppi_id) {
		this.ppi_id = ppi_id;
	}

	public String getPpi_venue_project_id() {
		return ppi_venue_project_id;
	}

	public void setPpi_venue_project_id(String ppi_venue_project_id) {
		this.ppi_venue_project_id = ppi_venue_project_id;
	}

	/**
	 * 图片路径
	 */
	public String getPpi_url() {
		return ppi_url;
	}

	public void setPpi_url(String ppi_url) {
		this.ppi_url = ppi_url;
	}

	/**
	 * 图片顺序
	 */
	public int getPpi_order() {
		return ppi_order;
	}

	public void setPpi_order(int ppi_order) {
		this.ppi_order = ppi_order;
	}

	/**
	 * 上传时间
	 */
	public Date getPi_upload_time() {
		return pi_upload_time;
	}

	public void setPi_upload_time(Date pi_upload_time) {
		this.pi_upload_time = pi_upload_time;
	}

	/**
	 * 上传作者id
	 */
	public String getPi_upload_user_id() {
		return pi_upload_user_id;
	}

	public void setPi_upload_user_id(String pi_upload_user_id) {
		this.pi_upload_user_id = pi_upload_user_id;
	}

}
