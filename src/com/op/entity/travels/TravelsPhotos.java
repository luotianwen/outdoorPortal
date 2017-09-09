package com.op.entity.travels;

import java.io.Serializable;
import org.springframework.util.StringUtils;

/**
 * 游记图片(travelsPhotos)实体类
 * 
 * @author Win Zhong
 * @version Revision: 1.00 Date: 2016-04-20 16:24:01
 */
public class TravelsPhotos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	// 餐厅、酒店、景点 ID
	String tag_id;
	// 关联id类型（1：餐厅；2：酒店；3：景点）
	String tag_type;
	// 标签名称
	String tagName;
	// 标签名称
	String fj;
	// 标签名称
	String ct;
	// 标签名称
	String jd;
	// 游记id
	String travels_id;
	// 父内容ID（默认0为一级）
	String parent_id;
	//模板原图片
	String templateimage;
	//图片宽度
	String width;
	//创建用户
	String user_id;
	
	// 景点标签类型(scenic：景点  city：城市   country：国家)
	String tt_scenic_type;
	
	

	public String getTt_scenic_type() {
		return tt_scenic_type;
	}

	public void setTt_scenic_type(String tt_scenic_type) {
		this.tt_scenic_type = tt_scenic_type;
	}

	public String getFj() {
		return fj;
	}

	public void setFj(String fj) {
		if(!StringUtils.isEmpty(fj)){
			this.setTagName(fj);
		}
		this.fj = fj;
	}

	public String getCt() {
		return ct;
	}

	public void setCt(String ct) {
		if(!StringUtils.isEmpty(ct)){
			this.setTagName(ct);
		}
		this.ct = ct;
	}

	public String getJd() {
		return jd;
	}

	public void setJd(String jd) {
		if(!StringUtils.isEmpty(jd)){
			this.setTagName(jd);
		}
		this.jd = jd;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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

	/**
	 * 模板原图片
	 */
	public String getTemplateimage() {
		return templateimage;
	}

	/**
	 * 模板原图片
	 */
	public void setTemplateimage(String templateimage) {
		this.templateimage = templateimage;
	}

	/**
	 * 图片宽度
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * 图片宽度
	 */
	public void setWidth(String width) {
		this.width = width;
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
