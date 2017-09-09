package com.op.dto.activity.comment;

/**
 * =============================================================
 * 项目名：outdoorPortal
 * 类描述：活动详情	→	评价详情
 * 创建人：Yan
 * 创建时间： 2016-4-11
 * modification list：
 * =============================================================
 */
public class CommentInfoDTO{

	// 评价人头像
	String uHeaderImg;
	
	// 评论人名称
	String uName;
	
	// 评价内容
	String content;
	
	// 评论时间
	java.util.Date commentTime;
	
	// 图片集合
	java.util.List<com.op.entity.activity.comment.CommentImages> images;
	
	/*--------------------------------	活动数据	------------------------------*/
	// 活动ID
	String id;
	
	// 标题
	String title;
	
	// 价格
	double price;
	
	// 活动主图
	String a_active_img;
	
	// 景点
	java.util.List<java.lang.String> jds;

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuHeaderImg() {
		return uHeaderImg;
	}

	public void setuHeaderImg(String uHeaderImg) {
		this.uHeaderImg = uHeaderImg;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public java.util.Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(java.util.Date commentTime) {
		this.commentTime = commentTime;
	}

	public java.util.List<com.op.entity.activity.comment.CommentImages> getImages() {
		return images;
	}

	public void setImages(java.util.List<com.op.entity.activity.comment.CommentImages> images) {
		this.images = images;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getA_active_img() {
		return a_active_img;
	}

	public void setA_active_img(String a_active_img) {
		this.a_active_img = a_active_img;
	}

	public java.util.List<java.lang.String> getJds() {
		return jds;
	}

	public void setJds(java.util.List<java.lang.String> jds) {
		this.jds = jds;
	}

}
