package com.op.entity.mmd;

import java.io.Serializable;
import java.util.Date;
/** 
 * 旅游目的地(destination)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-04-24 16:43:34 
 */  
public class Destination implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private String id;
 	//所属国家
  	 private String country_id;
 	//所属省（国内目的地使用）
  	 private String province_id;
 	//目的地中文名称
  	 private String zh_name;
 	//目的地英文名称
  	 private String en_name;
 	//代表性景点图片
  	 private String img;
 	//目的地描述(概况)
  	 private String briefing;
 	//编辑人（最后一次）
  	 private String edit_user;
 	//编辑时间（最后一次）
  	 private Date edit_time;



	 
    /**
    *id
    */ 
	public String getId() {
		return id;
	}
    /**
    *id
    */ 
	public void setId(String id) {
		this.id = id;
	}
    /**
    *所属国家
    */ 
	public String getCountry_id() {
		return country_id;
	}
    /**
    *所属国家
    */ 
	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}
    /**
    *所属省（国内目的地使用）
    */ 
	public String getProvince_id() {
		return province_id;
	}
    /**
    *所属省（国内目的地使用）
    */ 
	public void setProvince_id(String province_id) {
		this.province_id = province_id;
	}
    /**
    *目的地中文名称
    */ 
	public String getZh_name() {
		return zh_name;
	}
    /**
    *目的地中文名称
    */ 
	public void setZh_name(String zh_name) {
		this.zh_name = zh_name;
	}
    /**
    *目的地英文名称
    */ 
	public String getEn_name() {
		return en_name;
	}
    /**
    *目的地英文名称
    */ 
	public void setEn_name(String en_name) {
		this.en_name = en_name;
	}
    /**
    *代表性景点图片
    */ 
	public String getImg() {
		return img;
	}
    /**
    *代表性景点图片
    */ 
	public void setImg(String img) {
		this.img = img;
	}
    /**
    *目的地描述(概况)
    */ 
	public String getBriefing() {
		return briefing;
	}
    /**
    *目的地描述(概况)
    */ 
	public void setBriefing(String briefing) {
		this.briefing = briefing;
	}
    /**
    *编辑人（最后一次）
    */ 
	public String getEdit_user() {
		return edit_user;
	}
    /**
    *编辑人（最后一次）
    */ 
	public void setEdit_user(String edit_user) {
		this.edit_user = edit_user;
	}
    /**
    *编辑时间（最后一次）
    */ 
	public Date getEdit_time() {
		return edit_time;
	}
    /**
    *编辑时间（最后一次）
    */ 
	public void setEdit_time(Date edit_time) {
		this.edit_time = edit_time;
	}
	
}
