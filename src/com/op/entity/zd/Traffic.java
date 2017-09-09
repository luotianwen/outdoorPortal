package com.op.entity.zd;

import java.io.Serializable;
/** 
 * 交通方式字典表(traffic)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-03-12 10:32:01 
 */  
public class Traffic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private String t_id;
 	//文字描述
  	 private String t_content;
 	//图标
  	 private String t_icon;
	/**
    *id
    */ 
	public String getT_id() {
		return t_id;
	}
	public void setT_id(String t_id) {
		this.t_id = t_id;
	}
    /**
    *文字描述
    */ 
	public String getT_content() {
		return t_content;
	}
	public void setT_content(String t_content) {
		this.t_content = t_content;
	}
    /**
    *图标
    */ 
	public String getT_icon() {
		return t_icon;
	}
	public void setT_icon(String t_icon) {
		this.t_icon = t_icon;
	}
	
}
