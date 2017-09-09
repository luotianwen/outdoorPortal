package com.op.dto.travels.show;

/**
 * =============================================================
 * 项目名：outdoorPortal
 * 类描述：游记标题
 * 创建人：Yan
 * 创建时间： 2016-4-25
 * modification list：
 * =============================================================
 */
public class TravelsDetailTitleDTO {

	// id
	String id;
	// 父标题ID（默认0为一级）
	String parent_id;
	// 游记内容ID
	String content_id;
	// 标题
	String title;
	// 样式ID
	String style_id;
	// 游记ID
	String travels_id;
	
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
	
}
