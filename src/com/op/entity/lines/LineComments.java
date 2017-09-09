package com.op.entity.lines;

import java.util.Date;
/** 
 * 线路评论表(lineComments)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2015-12-10 10:51:35 
 */  
public class LineComments {


 	//ID
  	 private String lc_id;
 	//线路ID OR 分段ID
  	 private String lc_line_id;
 	//评论内容
  	 private String lc_content;
 	//线路打分（等级：1-5 星）
  	 private int lc_score;
 	//用户ID
  	 private String lc_create_user;
 	//评论时间
  	 private Date lc_create_time;
 	//是否删除(0：默认,1：已删除)
  	 private int lc_isdelete;



	 
    /**
    *ID
    */ 
	public String getLc_id() {
		return lc_id;
	}
	public void setLc_id(String lc_id) {
		this.lc_id = lc_id;
	}
    /**
    *线路ID OR 分段ID
    */ 
	public String getLc_line_id() {
		return lc_line_id;
	}
	public void setLc_line_id(String lc_line_id) {
		this.lc_line_id = lc_line_id;
	}
    /**
    *评论内容
    */ 
	public String getLc_content() {
		return lc_content;
	}
	public void setLc_content(String lc_content) {
		this.lc_content = lc_content;
	}
    /**
    *线路打分（等级：1-5 星）
    */ 
	public int getLc_score() {
		return lc_score;
	}
	public void setLc_score(int lc_score) {
		this.lc_score = lc_score;
	}
    /**
    *用户ID
    */ 
	public String getLc_create_user() {
		return lc_create_user;
	}
	public void setLc_create_user(String lc_create_user) {
		this.lc_create_user = lc_create_user;
	}
    /**
    *评论时间
    */ 
	public Date getLc_create_time() {
		return lc_create_time;
	}
	public void setLc_create_time(Date lc_create_time) {
		this.lc_create_time = lc_create_time;
	}
    /**
    *是否删除(0：默认,1：已删除)
    */ 
	public int getLc_isdelete() {
		return lc_isdelete;
	}
	public void setLc_isdelete(int lc_isdelete) {
		this.lc_isdelete = lc_isdelete;
	}
	
}
