package com.op.dto.travels;

import java.io.Serializable;

public class TravelsSearchDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//搜索关键字
 	 private String keyword; 	
 	 //搜索类型（0：最热(默认)；1：最新；）
  	 private String type = "0";
  	 
  	 /**
  	  * 搜索关键字
  	  * @return
  	  */
	public String getKeyword() {
		return keyword;
	}
	/**
	 * 搜索关键字
	 * @param keyword
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	/**
	 * 搜索类型（0：最热(默认)；1：最新；）
	 * @return
	 */
	public String getType() {
		return type;
	}
	/**
	 * 搜索类型（0：最热(默认)；1：最新；）
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
 	 
  	 
	
}

