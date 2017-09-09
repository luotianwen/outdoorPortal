package com.op.entity.suitablecrowd;

import java.io.Serializable;

/** 
  * 适合人群字典表(suitableCrowd)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2015-11-30 12:21:18 
 */  
public class SuitableCrowd implements Serializable{
 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//id(序列自增)
  	 private int sc_id;
 	//名称
  	 private String sc_name;

    /**
    *id(序列自增)
    */ 
	public int getSc_id() {
		return sc_id;
	}
	public void setSc_id(int sc_id) {
		this.sc_id = sc_id;
	}
    /**
    *名称
    */ 
	public String getSc_name() {
		return sc_name;
	}
	public void setSc_name(String sc_name) {
		this.sc_name = sc_name;
	}
}
