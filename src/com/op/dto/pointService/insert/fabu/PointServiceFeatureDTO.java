package com.op.dto.pointService.insert.fabu;

import java.io.Serializable;
import java.util.Date;

/**
 * 地点服务特色标签(pointServiceFeature)实体类
 * 
 * @author 世峰户外商城
 * @version Revision: 1.00 Date: 2016-06-23 13:36:41
 */
public class PointServiceFeatureDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// id
	String psf_id;
	// 场馆ID
	String psf_venue_id;
	// 标签名称
	String psf_name;
	// 创建时间
	Date psf_create_time;
	// 创建人
	String psf_create_user_id;

	public String getPsf_id() {
		return psf_id;
	}

	public void setPsf_id(String psf_id) {
		this.psf_id = psf_id;
	}

	public String getPsf_venue_id() {
		return psf_venue_id;
	}

	public void setPsf_venue_id(String psf_venue_id) {
		this.psf_venue_id = psf_venue_id;
	}

	/**
	 * 标签名称
	 */
	public String getPsf_name() {
		return psf_name;
	}

	public void setPsf_name(String psf_name) {
		this.psf_name = psf_name;
	}

	/**
	 * 创建时间
	 */
	public Date getPsf_create_time() {
		return psf_create_time;
	}

	public void setPsf_create_time(Date psf_create_time) {
		this.psf_create_time = psf_create_time;
	}

	/**
	 * 创建人
	 */
	public String getPsf_create_user_id() {
		return psf_create_user_id;
	}

	public void setPsf_create_user_id(String psf_create_user_id) {
		this.psf_create_user_id = psf_create_user_id;
	}

}
