package com.op.entity.lines;

import java.io.Serializable;
import java.util.Date;

import com.op.util.HTMLEscape;
/** 
 * 线路图片表(lineImages)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2015-12-19 11:03:55 
 */  
public class LineImages implements Serializable{


 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//图片ID
  	 private String li_id;
 	//分段线路ID
  	 private String li_line_id;
 	//本地图片地址
  	 private String li_local_url;
 	//阿里云存储图片地址
  	 private String li_aliyun_url;
 	//图片原名称
  	 private String li_name;
 	//上传时间
  	 private Date li_create_time;
 	//上传作者
  	 private String li_create_user;
 	//图片描述
  	 private String li_description;
 	//活动/图片标识(1：线路中的活动图片；2：线路图片)
  	 private int li_active_line_flag;
 	//总体线路ID（用作查询该活动所有图片）
  	 private String li_active_line_id;
 	//图片字幕
  	 private String li_caption;
 	//图片上传阿里云OSS云存储状态（1：未上传阿里云；2：已上传阿里云）
  	 private int uploadOSSState;



	 
    /**
    *图片ID
    */ 
	public String getLi_id() {
		return li_id;
	}
	public void setLi_id(String li_id) {
		this.li_id = li_id;
	}
    /**
    *分段线路ID
    */ 
	public String getLi_line_id() {
		return li_line_id;
	}
	public void setLi_line_id(String li_line_id) {
		this.li_line_id = li_line_id;
	}
    /**
    *本地图片地址
    */ 
	public String getLi_local_url() {
		return li_local_url;
	}
	public void setLi_local_url(String li_local_url) {
		this.li_local_url = li_local_url;
	}
    /**
    *阿里云存储图片地址
    */ 
	public String getLi_aliyun_url() {
		return li_aliyun_url;
	}
	public void setLi_aliyun_url(String li_aliyun_url) {
		this.li_aliyun_url = li_aliyun_url;
	}
    /**
    *图片原名称
    */ 
	public String getLi_name() {
		return li_name;
	}
	public void setLi_name(String li_name) {
		this.li_name = li_name;
	}
    /**
    *上传时间
    */ 
	public Date getLi_create_time() {
		return li_create_time;
	}
	public void setLi_create_time(Date li_create_time) {
		this.li_create_time = li_create_time;
	}
    /**
    *上传作者
    */ 
	public String getLi_create_user() {
		return li_create_user;
	}
	public void setLi_create_user(String li_create_user) {
		this.li_create_user = li_create_user;
	}
    /**
    *图片描述
    */ 
	public String getLi_description() {
		return li_description;
	}
	public void setLi_description(String li_description) {
		this.li_description = HTMLEscape.htmlEscapeNoEmoji(li_description);
	}
    /**
    *活动/图片标识(1：线路中的活动图片；2：线路图片)
    */ 
	public int getLi_active_line_flag() {
		return li_active_line_flag;
	}
	public void setLi_active_line_flag(int li_active_line_flag) {
		this.li_active_line_flag = li_active_line_flag;
	}
    /**
    *总体线路ID（用作查询该活动所有图片）
    */ 
	public String getLi_active_line_id() {
		return li_active_line_id;
	}
	public void setLi_active_line_id(String li_active_line_id) {
		this.li_active_line_id = li_active_line_id;
	}
    /**
    *图片字幕
    */ 
	public String getLi_caption() {
		return li_caption;
	}
	public void setLi_caption(String li_caption) {
		this.li_caption = li_caption;
	}
    /**
    *图片上传阿里云OSS云存储状态（1：未上传阿里云；2：已上传阿里云）
    */ 
	public int getUploadOSSState() {
		return uploadOSSState;
	}
	public void setUploadOSSState(int uploadOSSState) {
		this.uploadOSSState = uploadOSSState;
	}
	
}
