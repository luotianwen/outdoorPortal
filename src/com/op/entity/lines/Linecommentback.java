package com.op.entity.lines;

import java.util.Date;
/** 
 * 线路评论回复(linecommentback)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2015-12-10 10:51:35 
 */  
public class Linecommentback {


 	//id
  	 private String lcb_id;
 	//线路评论ID
  	 private String lc_id;
 	//用户ID
  	 private String lcb_create_user;
 	//回复时间
  	 private Date lcb_create_time;
 	//回复内容
  	 private String lcb_back_comment;
 	//是否已经删除(0：默认,1：已删除)
  	 private int lcb_is_delete;



	 
    /**
    *id
    */ 
	public String getLcb_id() {
		return lcb_id;
	}
	public void setLcb_id(String lcb_id) {
		this.lcb_id = lcb_id;
	}
    /**
    *线路评论ID
    */ 
	public String getLc_id() {
		return lc_id;
	}
	public void setLc_id(String lc_id) {
		this.lc_id = lc_id;
	}
    /**
    *用户ID
    */ 
	public String getLcb_create_user() {
		return lcb_create_user;
	}
	public void setLcb_create_user(String lcb_create_user) {
		this.lcb_create_user = lcb_create_user;
	}
    /**
    *回复时间
    */ 
	public Date getLcb_create_time() {
		return lcb_create_time;
	}
	public void setLcb_create_time(Date lcb_create_time) {
		this.lcb_create_time = lcb_create_time;
	}
    /**
    *回复内容
    */ 
	public String getLcb_back_comment() {
		return lcb_back_comment;
	}
	public void setLcb_back_comment(String lcb_back_comment) {
		this.lcb_back_comment = lcb_back_comment;
	}
    /**
    *是否已经删除(0：默认,1：已删除)
    */ 
	public int getLcb_is_delete() {
		return lcb_is_delete;
	}
	public void setLcb_is_delete(int lcb_is_delete) {
		this.lcb_is_delete = lcb_is_delete;
	}
	
}
