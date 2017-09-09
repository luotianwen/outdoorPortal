package com.op.entity.comments;

import java.io.Serializable;
import java.util.Date;
/** 
 * 活动评论回复(activeCommentBack)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-01-15 15:04:11 
 */  
public class ActiveCommentBack implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//评论回复ID
  	 private String cb_id;
 	//评论ID
  	 private String co_id;
 	//用户ID
  	 private String user_id;
 	//回复内容
  	 private String content;
 	//回复时间
  	 private Date replyTime = new Date();
 	//赞
  	 private int praises;
 	
 	// 用户名称
 	String uName;
 	// 用户头像
 	String uHeadImg;


 	/*-----------------------------------------------	get	&&	set	----------------------------------------------*/
	 
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
	/**
    *评论回复ID
    */ 
	public String getCb_id() {
		return cb_id;
	}
	public void setCb_id(String cb_id) {
		this.cb_id = cb_id;
	}
    /**
    *评论ID
    */ 
	public String getCo_id() {
		return co_id;
	}
	public void setCo_id(String co_id) {
		this.co_id = co_id;
	}
    /**
    *用户ID
    */ 
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
    /**
    *回复内容
    */ 
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
    /**
    *回复时间
    */ 
	public Date getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
    /**
    *赞
    */ 
	public int getPraises() {
		return praises;
	}
	public void setPraises(int praises) {
		this.praises = praises;
	}
	
}
