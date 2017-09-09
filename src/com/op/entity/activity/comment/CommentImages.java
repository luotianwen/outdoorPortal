package com.op.entity.activity.comment;

import java.io.Serializable;

/**
 * 评论图片表(commentImages)实体类
 * 
 * @author 世峰户外商城
 * @version Revision: 1.00 Date: 2016-04-06 17:37:22
 */
public class CommentImages implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// id
	private String ci_id;
	// 评论ID
	private String co_id;
	// 本地地址
	private String ci_location_url;
	// 阿里云地址
	private String ci_cly_url;
	// 图片顺序
	private int sort;
	
	/**
	 * id
	 */
	public String getCi_id() {
		return ci_id;
	}

	public void setCi_id(String ci_id) {
		this.ci_id = ci_id;
	}

	/**
	 * 评论ID
	 */
	public String getCo_id() {
		return co_id;
	}

	public void setCo_id(String co_id) {
		this.co_id = co_id;
	}

	/**
	 * 本地地址
	 */
	public String getCi_location_url() {
		return ci_location_url;
	}

	public void setCi_location_url(String ci_location_url) {
		this.ci_location_url = ci_location_url;
	}

	/**
	 * 阿里云地址
	 */
	public String getCi_cly_url() {
		return ci_cly_url;
	}

	public void setCi_cly_url(String ci_cly_url) {
		this.ci_cly_url = ci_cly_url;
	}

	/**
	 * 图片顺序
	 */
	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

}
