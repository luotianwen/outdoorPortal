package com.op.entity.users;

import java.io.Serializable;
 

public class TestUser implements Serializable {

	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	protected String userId;
	protected String userName;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	 
	 
 
}
