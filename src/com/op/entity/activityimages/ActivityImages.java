package com.op.entity.activityimages;

public class ActivityImages {
	//活动图片集合id
	String ssi_id;
	//活动id、活动评论id
	String relevance_id;
	//图片类型（1：活动图片；2：活动评论图片；）
	Integer img_type;
	/**
	 * 活动图片集合id
	 */
	public String getSsi_id() {
		return ssi_id;
	}
	public void setSsi_id(String ssi_id) {
		this.ssi_id = ssi_id;
	}
	/**
	 * 活动id、活动评论id
	 */
	public String getRelevance_id() {
		return relevance_id;
	}
	public void setRelevance_id(String relevance_id) {
		this.relevance_id = relevance_id;
	}
	/**
	 * 图片类型（1：活动图片；2：活动评论图片；）
	 */
	public Integer getImg_type() {
		return img_type;
	}
	public void setImg_type(Integer img_type) {
		this.img_type = img_type;
	}
	
}
