package com.op.dto.activity.consultation;

import java.io.Serializable;

import org.apache.solr.client.solrj.util.ClientUtils;

/**
 * =============================================================
 * 项目名：outdoorPortal
 * 类描述：搜索咨询内容
 * 创建人：Yan
 * 创建时间： 2016-3-5
 * modification list：
 * =============================================================
 */
public class SearchConsultationDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 活动ID
	String activeId;

	// 搜索提问内容
	String keyword;
	
	// 按照时间排序还是按照点赞排序{date  or  praises}
	String type;
	
	// 领队ID
	String leaderId;
	
	// 当前用户ID
	String userId;
	
	
	/*------------------------------	get	&&	set		-----------------------------------*/
	
	
	public String getKeyword() {
		if (keyword == null) {
			return "*";
		} else {
			return ClientUtils.escapeQueryChars(keyword);
		}
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	public String getActiveId() {
		return activeId;
	}

	public void setActiveId(String activeId) {
		this.activeId = activeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}
	
	
}
