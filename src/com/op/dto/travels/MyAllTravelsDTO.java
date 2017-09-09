package com.op.dto.travels;

import java.io.Serializable;

public class MyAllTravelsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//游记发布总数
  	 private int travels_count;
 	//游记回复总数
  	 private int reply_count;
  	 //游记浏览总数
  	 private int read_count;
	public int getTravels_count() {
		return travels_count;
	}
	public void setTravels_count(int travels_count) {
		this.travels_count = travels_count;
	}
	public int getReply_count() {
		return reply_count;
	}
	public void setReply_count(int reply_count) {
		this.reply_count = reply_count;
	}
	public int getRead_count() {
		return read_count;
	}
	public void setRead_count(int read_count) {
		this.read_count = read_count;
	}
	 
    
	
}

