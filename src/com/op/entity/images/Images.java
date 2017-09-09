package com.op.entity.images;

import java.util.Date;

/** 
 * 图片信息表(Images)实体类
 * @author 潘永威
 * @version Revision: 1.00 
 *  Date: 2015-12-3 17:57:31
 */  
public class Images {
	//图片id
	private String img_id;
	//关联id（活动图片id、景区图片id）
	private String relevance_id;
	//本地图片地址
	private String local_url;
	//阿里云存储图片地址
	private String aliyun_url;
	//图片原名称
	private String name;
	//上传时间
	private Date uploadtime;
	//上传作者
	private String user_id;
	//图片类型（1：活动图片；2：景区图片；）
	private Integer img_type;
	
	/**
	 *图片id
	 */
	public String getImg_id() {
		return img_id;
	}
	public void setImg_id(String img_id) {
		this.img_id = img_id;
	}
	/**
	 *关联id（活动图片id、景区图片id）
	 */
	public String getRelevance_id() {
		return relevance_id;
	}
	public void setRelevance_id(String relevance_id) {
		this.relevance_id = relevance_id;
	}
	/**
	 *本地图片地址
	 */
	public String getLocal_url() {
		return local_url;
	}
	public void setLocal_url(String local_url) {
		this.local_url = local_url;
	}
	/**
	 *阿里云存储图片地址
	 */
	public String getAliyun_url() {
		return aliyun_url;
	}
	public void setAliyun_url(String aliyun_url) {
		this.aliyun_url = aliyun_url;
	}
	/**
	 *图片原名称
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 *上传时间
	 */
	public Date getUploadtime() {
		return uploadtime;
	}
	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}
	/**
	 *上传作者
	 */
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	/**
	 *图片类型（1：活动图片；2：景区图片；）
	 */
	public Integer getImg_type() {
		return img_type;
	}
	public void setImg_type(Integer img_type) {
		this.img_type = img_type;
	}
}
