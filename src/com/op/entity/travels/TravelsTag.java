package com.op.entity.travels;

import java.io.Serializable;
import java.util.Date;

import org.springframework.util.StringUtils;

/**
 * 游记标签(travelsTag)实体类
 * 
 * @author 世峰户外商城
 * @version Revision: 1.00 Date: 2016-04-27 15:19:27
 */
public class TravelsTag implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// id
	String tt_id;
	// 游记ID
	String tt_travels_id;
	// 标签类型（1：餐厅；2：酒店；3：景点）
	String tt_type;
	// 餐厅、酒店、景点 ID
	String tt_type_id;
	// 添加时间
	Date tt_create_time;
	// 创建用户
	String tt_create_user;
	// 标签名称
	String tagName;
	// 标签名称
	String fj;
	// 标签名称
	String ct;
	// 标签名称
	String jd;
	// 景点标签类型(scenic：景点 city：城市 country：国家)
	String scenicType;

	public String getTagName() {
		return tagName;
	}

	public String getScenicType() {
		return scenicType;
	}

	public void setScenicType(String scenicType) {
		this.scenicType = scenicType;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getFj() {
		return fj;
	}

	public void setFj(String fj) {
		if (!StringUtils.isEmpty(fj)) {
			this.setTagName(fj);
		}
		this.fj = fj;
	}

	public String getCt() {
		return ct;
	}

	public void setCt(String ct) {
		if (!StringUtils.isEmpty(ct)) {
			this.setTagName(ct);
		}
		this.ct = ct;
	}

	public String getJd() {
		return jd;
	}

	public void setJd(String jd) {
		if (!StringUtils.isEmpty(jd)) {
			this.setTagName(jd);
		}
		this.jd = jd;
	}

	/**
	 * id
	 */
	public String getTt_id() {
		return tt_id;
	}

	public void setTt_id(String tt_id) {
		this.tt_id = tt_id;
	}

	/**
	 * 游记ID
	 */
	public String getTt_travels_id() {
		return tt_travels_id;
	}

	public void setTt_travels_id(String tt_travels_id) {
		this.tt_travels_id = tt_travels_id;
	}

	/**
	 * 标签类型（1：餐厅；2：酒店；3：景点）
	 */
	public String getTt_type() {
		return tt_type;
	}

	public void setTt_type(String tt_type) {
		this.tt_type = tt_type;
	}

	/**
	 * 餐厅、酒店、景点 ID
	 */
	public String getTt_type_id() {
		return tt_type_id;
	}

	public void setTt_type_id(String tt_type_id) {
		this.tt_type_id = tt_type_id;
	}

	/**
	 * 添加时间
	 */
	public Date getTt_create_time() {
		return tt_create_time;
	}

	public void setTt_create_time(Date tt_create_time) {
		this.tt_create_time = tt_create_time;
	}

	/**
	 * 创建用户
	 */
	public String getTt_create_user() {
		return tt_create_user;
	}

	public void setTt_create_user(String tt_create_user) {
		this.tt_create_user = tt_create_user;
	}

}
