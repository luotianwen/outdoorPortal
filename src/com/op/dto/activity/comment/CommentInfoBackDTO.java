package com.op.dto.activity.comment;

/**
 * =============================================================
 * 项目名：outdoorPortal
 * 类描述：活动详情	→	评价信息	→	详情	→	回复
 * 创建人：Yan
 * 创建时间： 2016-4-11
 * modification list：
 * =============================================================
 */
public class CommentInfoBackDTO {

	// 用户名称
	String uName;
	
	// 回复内容
	String content;
	
	// 用户头像
	String headImg;

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	
	
}
