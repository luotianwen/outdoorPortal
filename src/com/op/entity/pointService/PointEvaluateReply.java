package com.op.entity.pointService;

import java.io.Serializable;
import java.util.Date;
/** 
 * 地点服务评价回复(pointEvaluateReply)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-06-23 13:36:41 
 */  
public class PointEvaluateReply implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private int pser_id;
 	//评价ID
  	 private int pser_venue_evaluate_id;
 	//回复内容
  	 private String pser_comment;
 	//回复人
  	 private String pser_reply_user_id;
 	//被回复人
  	 private String pser_by_reply_user_id;
 	//回复时间
  	 private Date pser_reply_time;



	 
    /**
    *id
    */ 
	public int getPser_id() {
		return pser_id;
	}
	public void setPser_id(int pser_id) {
		this.pser_id = pser_id;
	}
    /**
    *评价ID
    */ 
	public int getPser_venue_evaluate_id() {
		return pser_venue_evaluate_id;
	}
	public void setPser_venue_evaluate_id(int pser_venue_evaluate_id) {
		this.pser_venue_evaluate_id = pser_venue_evaluate_id;
	}
    /**
    *回复内容
    */ 
	public String getPser_comment() {
		return pser_comment;
	}
	public void setPser_comment(String pser_comment) {
		this.pser_comment = pser_comment;
	}
    /**
    *回复人
    */ 
	public String getPser_reply_user_id() {
		return pser_reply_user_id;
	}
	public void setPser_reply_user_id(String pser_reply_user_id) {
		this.pser_reply_user_id = pser_reply_user_id;
	}
    /**
    *被回复人
    */ 
	public String getPser_by_reply_user_id() {
		return pser_by_reply_user_id;
	}
	public void setPser_by_reply_user_id(String pser_by_reply_user_id) {
		this.pser_by_reply_user_id = pser_by_reply_user_id;
	}
    /**
    *回复时间
    */ 
	public Date getPser_reply_time() {
		return pser_reply_time;
	}
	public void setPser_reply_time(Date pser_reply_time) {
		this.pser_reply_time = pser_reply_time;
	}
	
}
