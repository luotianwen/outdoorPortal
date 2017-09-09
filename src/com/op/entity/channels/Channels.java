package com.op.entity.channels;

import java.io.Serializable;

/** 
 * 活动频道分类表(channels)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2015-11-30 12:21:18 
 */  
public class Channels  implements Serializable {


 	/**
	 * 
	 */
	private static final long serialVersionUID = -838613429878636591L;
	//频道ID
  	 private int ch_id;
 	//频道级别
  	 private int ch_level;
 	//频道名称
  	 private String ch_name;
 	//频道父ID
  	 private String ch_parentId;
 	//频道描述
  	 private String ch_describe;
 	//频道排序
  	 private int ch_sort;



	 
    /**
    *频道ID
    */ 
	public int getCh_id() {
		return ch_id;
	}
	public void setCh_id(int ch_id) {
		this.ch_id = ch_id;
	}
    /**
    *频道级别
    */ 
	public int getCh_level() {
		return ch_level;
	}
	public void setCh_level(int ch_level) {
		this.ch_level = ch_level;
	}
    /**
    *频道名称
    */ 
	public String getCh_name() {
		return ch_name;
	}
	public void setCh_name(String ch_name) {
		this.ch_name = ch_name;
	}
    /**
    *频道父ID
    */ 
	public String getCh_parentId() {
		return ch_parentId;
	}
	public void setCh_parentId(String ch_parentId) {
		this.ch_parentId = ch_parentId;
	}
    /**
    *频道描述
    */ 
	public String getCh_describe() {
		return ch_describe;
	}
	public void setCh_describe(String ch_describe) {
		this.ch_describe = ch_describe;
	}
    /**
    *频道排序
    */ 
	public int getCh_sort() {
		return ch_sort;
	}
	public void setCh_sort(int ch_sort) {
		this.ch_sort = ch_sort;
	}
}