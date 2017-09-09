package com.op.entity.travels;

import java.io.Serializable;

/**
 * 游记标题(travelsTitle)实体类
 * 
 * @author Win Zhong
 * @version Revision: 1.00 Date: 2016-04-18 14:30:32
 */
public class TravelsTitle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// id
	private String id;
	// 父标题ID（默认0为一级）
	private String parent_id;
	// 游记内容ID
	private String content_id;
	// 标题
	private String title;
	// 样式ID
	private String style_id;
	// 游记ID
	private String travels_id;
	//创建用户
	private String user_id;
	
	/**
	 * id
	 */
	public String getId() {
		return id;
	}

	/**
	 * id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 父标题ID（默认0为一级）
	 */
	public String getParent_id() {
		return parent_id;
	}

	/**
	 * 父标题ID（默认0为一级）
	 */
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	/**
	 * 游记内容ID
	 */
	public String getContent_id() {
		return content_id;
	}

	/**
	 * 游记内容ID
	 */
	public void setContent_id(String content_id) {
		this.content_id = content_id;
	}

	/**
	 * 标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 样式ID
	 */
	public String getStyle_id() {
		return style_id;
	}

	/**
	 * 样式ID
	 */
	public void setStyle_id(String style_id) {
		this.style_id = style_id;
	}

	/**
	 * 游记ID
	 */
	public String getTravels_id() {
		return travels_id;
	}
	
	/**
	 * 游记ID
	 */
	public void setTravels_id(String travels_id) {
		this.travels_id = travels_id;
	}

	/**
	 * 创建用户
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * 创建用户
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
}
