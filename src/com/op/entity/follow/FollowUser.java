package com.op.entity.follow;

import java.io.Serializable;
/** 
 * 用户关注(followUser)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2015-12-23 16:16:12 
 */  
public class FollowUser implements Serializable{

 
 	/**
	 * 
	 */
	private static final long serialVersionUID = 4732110140496415630L;
	//id
  	 private int fu_id;
 	//关注者用户Id
  	 private String userId;
 	//被关注着ID
  	 private String fUserId;



	 
    /**
    *id
    */ 
	public int getFu_id() {
		return fu_id;
	}
	public void setFu_id(int fu_id) {
		this.fu_id = fu_id;
	}
    /**
    *关注者用户Id
    */ 
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
    /**
    *被关注着ID
    */ 
	public String getFUserId() {
		return fUserId;
	}
	public void setFUserId(String fUserId) {
		this.fUserId = fUserId;
	}
	
}
