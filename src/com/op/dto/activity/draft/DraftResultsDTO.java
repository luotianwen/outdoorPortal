package com.op.dto.activity.draft;

import java.util.Date;

/**
 * =============================================================
 * 项目名：outdoorPortal
 * 类描述：活动草稿箱列表
 * 创建人：Yan
 * 创建时间： 2016-4-6
 * modification list：
 * =============================================================
 */
public class DraftResultsDTO {

	// 活动ID
	String id;
	
	// 活动发布时间
	Date publishesTime;
	
	// 标题
	String title;

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getPublishesTime() {
		return publishesTime;
	}

	public void setPublishesTime(Date publishesTime) {
		this.publishesTime = publishesTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
