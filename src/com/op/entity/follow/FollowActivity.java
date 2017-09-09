package com.op.entity.follow;

import java.io.Serializable;
/** 
 * 活动关注(followActivity)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2015-12-23 16:16:12 
 */  
public class FollowActivity implements Serializable{

	 

 	/**
	 * 
	 */
	private static final long serialVersionUID = -4105470781993460860L;
	//活动关注ID
  	 private int fa_id;
 	//关注者ID
  	 private String userId;
 	//关注的活动ID
  	 private int activity_id;



	 
    /**
    *活动关注ID
    */ 
	public int getFa_id() {
		return fa_id;
	}
	public void setFa_id(int fa_id) {
		this.fa_id = fa_id;
	}
    /**
    *关注者ID
    */ 
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
    /**
    *关注的活动ID
    */ 
	public int getActivity_id() {
		return activity_id;
	}
	public void setActivity_id(int activity_id) {
		this.activity_id = activity_id;
	}
	
}
