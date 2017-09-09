package com.op.entity.zd;

import java.io.Serializable;
/** 
 * 证件类型(cardType)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-03-15 17:07:45 
 */  
public class CardType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private int id;
 	//证件类型名字
  	 private String name;



	 
    /**
    *id
    */ 
	public int getId() {
		return id;
	}
    /**
    *id
    */ 
	public void setId(int id) {
		this.id = id;
	}
    /**
    *证件类型名字
    */ 
	public String getName() {
		return name;
	}
    /**
    *证件类型名字
    */ 
	public void setName(String name) {
		this.name = name;
	}
	
}
