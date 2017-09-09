package com.op.entity.zd;

import java.io.Serializable;
/** 
 * 人际关系类型(relation)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-03-15 17:07:45 
 */  
public class Relation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private int id;
 	//name
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
    *name
    */ 
	public String getName() {
		return name;
	}
    /**
    *name
    */ 
	public void setName(String name) {
		this.name = name;
	}
	
}
