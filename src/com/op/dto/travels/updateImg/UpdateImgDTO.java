package com.op.dto.travels.updateImg;

/**
 * =============================================================
 * 项目名：outdoorPortal
 * 类描述：更改图片标签DTO
 * 创建人：Yan
 * 创建时间： 2016-4-30
 * modification list：
 * =============================================================
 */
public class UpdateImgDTO {

	// 游记内容ID
	String content_id;
	
	// 餐厅、酒店、景点 ID
	String tag_id;
	
	// 关联id类型（1：餐厅；2：酒店；3：景点）
	String tag_type;
	
	// 创建用户
	String user_id;
	
	// 景点数据类型  scenic：景点  city：城市   country：国家
	String scenicType;
	
	

	public String getScenicType() {
		return scenicType;
	}

	public void setScenicType(String scenicType) {
		this.scenicType = scenicType;
	}

	public String getContent_id() {
		return content_id;
	}

	public void setContent_id(String content_id) {
		this.content_id = content_id;
	}

	public String getTag_id() {
		return tag_id;
	}

	public void setTag_id(String tag_id) {
		this.tag_id = tag_id;
	}

	public String getTag_type() {
		return tag_type;
	}

	public void setTag_type(String tag_type) {
		this.tag_type = tag_type;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
}
