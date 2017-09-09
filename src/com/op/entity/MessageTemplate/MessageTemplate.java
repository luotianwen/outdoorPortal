package com.op.entity.MessageTemplate;

import java.io.Serializable;
import java.util.Date;
/** 
 * 站内信模板(MessageTemplate)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-05-31 10:07:51 
 */  
public class MessageTemplate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private String mt_id;
 	//模板名称
  	 private String mt_name;
 	//消息标题
  	 private String mt_title;
 	//消息内容
  	 private String mt_content;
 	//邮件模板参数备注
  	 private String mt_remark;
 	//模板创建时间
  	 private Date mt_create_time;
 	//模板最后修改时间
  	 private Date mt_last_update_time;
 	//模板创建人
  	 private String mt_create;
  	 //模板最后修改人
  	 private String mt_last_update;
  	 //模板类型
  	 private String mt_type;
  	 
    /**
    *id
    */ 
	public String getMt_id() {
		return mt_id;
	}
	public void setMt_id(String mt_id) {
		this.mt_id = mt_id;
	}
    /**
    *模板名称
    */ 
	public String getMt_name() {
		return mt_name;
	}
	public void setMt_name(String mt_name) {
		this.mt_name = mt_name;
	}
    /**
    *消息标题
    */ 
	public String getMt_title() {
		return mt_title;
	}
	public void setMt_title(String mt_title) {
		this.mt_title = mt_title;
	}
    /**
    *消息内容
    */ 
	public String getMt_content() {
		return mt_content;
	}
	public void setMt_content(String mt_content) {
		this.mt_content = mt_content;
	}
    /**
    *邮件模板参数备注
    */ 
	public String getMt_remark() {
		return mt_remark;
	}
	public void setMt_remark(String mt_remark) {
		this.mt_remark = mt_remark;
	}
    /**
    *模板创建时间
    */ 
	public Date getMt_create_time() {
		return mt_create_time;
	}
	public void setMt_create_time(Date mt_create_time) {
		this.mt_create_time = mt_create_time;
	}
    /**
    *模板最后修改时间
    */ 
	public Date getMt_last_update_time() {
		return mt_last_update_time;
	}
	public void setMt_last_update_time(Date mt_last_update_time) {
		this.mt_last_update_time = mt_last_update_time;
	}
    /**
    *模板创建人
    */ 
	public String getMt_create() {
		return mt_create;
	}
	public void setMt_create(String mt_create) {
		this.mt_create = mt_create;
	}
	/**
	 * 模板最后修改人
	 */
	public String getMt_last_update() {
		return mt_last_update;
	}
	public void setMt_last_update(String mt_last_update) {
		this.mt_last_update = mt_last_update;
	}
	/**
	 * 模板类型
	 */
	public String getMt_type() {
		return mt_type;
	}
	public void setMt_type(String mt_type) {
		this.mt_type = mt_type;
	}
	
}
