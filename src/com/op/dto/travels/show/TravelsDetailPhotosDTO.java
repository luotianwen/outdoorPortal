package com.op.dto.travels.show;

/**
 * =============================================================
 * 项目名：outdoorPortal
 * 类描述：游记图片
 * 创建人：Yan
 * 创建时间： 2016-4-25
 * modification list：
 * =============================================================
 */
public class TravelsDetailPhotosDTO {

	// id
	String id;
	// 游记内容ID
	String content_id;
	// 图片src
	String src;
	// 图片标签
	String tag;
	// 点赞次数（顶）
	int like_count;
	// 地点、酒店、景点 ID
	String tag_id;
	// 关联id类型（1：地点；2：酒店；3：景点）
	String tag_type;
	// 游记id
	String travels_id;
	// 父内容ID（默认0为一级）
	String parent_id;
	
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
	 * 图片src
	 */
	public String getSrc() {
		return src;
	}

	/**
	 * 图片src
	 */
	public void setSrc(String src) {
		this.src = src;
	}

	/**
	 * 图片标签
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * 图片标签
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * 点赞次数（顶）
	 */
	public int getLike_count() {
		return like_count;
	}

	/**
	 * 点赞次数（顶）
	 */
	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}

	/**
	 * 地点、酒店、景点 ID
	 */
	public String getTag_id() {
		return tag_id;
	}

	/**
	 * 地点、酒店、景点 ID
	 */
	public void setTag_id(String tag_id) {
		this.tag_id = tag_id;
	}

	/**
	 * 关联id类型（1：地点；2：酒店；3：景点）
	 */
	public String getTag_type() {
		return tag_type;
	}

	/**
	 * 关联id类型（1：地点；2：酒店；3：景点）
	 */
	public void setTag_type(String tag_type) {
		this.tag_type = tag_type;
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
	 * 父内容ID（默认0为一级）
	 */
	public String getParent_id() {
		return parent_id;
	}

	/**
	 * 父内容ID（默认0为一级）
	 */
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

}
