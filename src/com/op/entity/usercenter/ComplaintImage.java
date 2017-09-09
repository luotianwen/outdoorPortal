package com.op.entity.usercenter;

import java.io.Serializable;
/** 
 * 投诉领队证据图片(complaintImage)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-01-14 10:52:40 
 */  
public class ComplaintImage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private String cimg_id;
 	//投诉领队id
  	 private String cl_id;
 	//图片地址
  	 private String img_url;



	 
    /**
    *id
    */ 
	public String getCimg_id() {
		return cimg_id;
	}
	public void setCimg_id(String cimg_id) {
		this.cimg_id = cimg_id;
	}
    /**
    *投诉领队id
    */ 
	public String getCl_id() {
		return cl_id;
	}
	public void setCl_id(String cl_id) {
		this.cl_id = cl_id;
	}
    /**
    *图片地址
    */ 
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	
}
