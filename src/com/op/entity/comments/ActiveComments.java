package com.op.entity.comments;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 活动评论信息表(comments)实体类
 * 
 * @author 世峰户外商城
 * @version Revision: 1.00 Date: 2016-01-08 10:42:02
 */
public class ActiveComments implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 评论ID
	String co_id;
	
	// 活动ID
	String activity_id;
	
	// 订单ID
	String suId;
	
	// 用户ID
	String user_id;
	
	// 评论内容
	String content;
	
	// 活动打分（等级：1-5 星）
	double activityScore;
	
	// 领队打分（等级：1-5 星）
	double leaderScore;
	
	// 评论时间
	Date commentTime;
	
	// 赞
	int praises;
	
	/*----------------------------------------------------------------------------------------*/
	// 间隔时间（几分钟；几小时；几天；几月；几年）
	String interval;
	// 用户名称
	String uName;
	// 用户头像
	String uHeadImg;

	// 评价回复集合
	List<ActiveCommentBack> acbs;
	
	
	
	/*-----------------------------------------------	get	&&	set	----------------------------------------------*/
	
	
	
	/**
	 * 订单ID
	 */
	public String getSuId() {
		return suId;
	}


	public String getInterval() {
		return interval;
	}


	public void setInterval(String interval) {
		this.interval = interval;
	}


	public List<ActiveCommentBack> getAcbs() {
		return acbs;
	}


	public void setAcbs(List<ActiveCommentBack> acbs) {
		this.acbs = acbs;
	}


	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuHeadImg() {
		return uHeadImg;
	}

	public void setuHeadImg(String uHeadImg) {
		this.uHeadImg = uHeadImg;
	}


	public void setSuId(String suId) {
		this.suId = suId;
	}

	/**
	 * 评论ID
	 */
	public String getCo_id() {
		return co_id;
	}

	public void setCo_id(String co_id) {
		this.co_id = co_id;
	}

	/**
	 * 活动ID
	 */
	public String getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(String activity_id) {
		this.activity_id = activity_id;
	}

	/**
	 * 用户ID
	 */
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * 评论内容
	 */
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public double getActivityScore() {
		return activityScore;
	}

	public void setActivityScore(double activityScore) {
		this.activityScore = activityScore;
	}

	public double getLeaderScore() {
		return leaderScore;
	}

	public void setLeaderScore(double leaderScore) {
		this.leaderScore = leaderScore;
	}

	public void setLeaderScore(int leaderScore) {
		this.leaderScore = leaderScore;
	}

	/**
	 * 评论时间
	 */
	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	/**
	 * 赞
	 */
	public int getPraises() {
		return praises;
	}

	public void setPraises(int praises) {
		this.praises = praises;
	}

}
