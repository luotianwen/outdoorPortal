package com.op.entity.mmd;

import java.io.Serializable;
import java.util.Date;
/** 
 * 国家(country)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-04-24 16:43:34 
 */  
public class Country implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private String id;
 	//所属州ID（1：亚洲；2：北美洲；；3：南美洲；4：欧洲；5：非洲；6：大洋洲）
  	 private String continent_id;
 	//中文名称
  	 private String zh_name;
 	//英文名称
  	 private String en_name;
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
    *所属州ID（1：亚洲；2：北美洲；；3：南美洲；4：欧洲；5：非洲；6：大洋洲）
    */ 
	public String getContinent_id() {
		return continent_id;
	}
    /**
    *所属州ID（1：亚洲；2：北美洲；；3：南美洲；4：欧洲；5：非洲；6：大洋洲）
    */ 
	public void setContinent_id(String continent_id) {
		this.continent_id = continent_id;
	}
    /**
    *中文名称
    */ 
	public String getZh_name() {
		return zh_name;
	}
    /**
    *中文名称
    */ 
	public void setZh_name(String zh_name) {
		this.zh_name = zh_name;
	}
    /**
    *英文名称
    */ 
	public String getEn_name() {
		return en_name;
	}
    /**
    *英文名称
    */ 
	public void setEn_name(String en_name) {
		this.en_name = en_name;
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
