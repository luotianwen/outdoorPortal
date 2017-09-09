package com.op.entity.messageType;

import java.io.Serializable;
import java.util.Date;
/** 
 * 消息类型表(MessageType)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-06-01 16:03:43 
 */  
public class MessageType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private String id;
 	//消息类型
  	 private String type_name;
 	//父类型
  	 private String parent_id;
 	//类型描述
  	 private String type_desc;
 	//类型Class
  	 private String type_style;
 	//创建人
  	 private String type_create;
 	//最后修改人
  	 private String type_update;
 	//最后修改时间
  	 private Date update_time;
 	//排序
  	 private int sort;
  	 //模板名称
  	 private String template;


	 
    /**
    *id
    */ 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    /**
    *消息类型
    */ 
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
    /**
    *父类型
    */ 
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
    /**
    *类型描述
    */ 
	public String getType_desc() {
		return type_desc;
	}
	public void setType_desc(String type_desc) {
		this.type_desc = type_desc;
	}
    /**
    *类型Class
    */ 
	public String getType_style() {
		return type_style;
	}
	public void setType_style(String type_style) {
		this.type_style = type_style;
	}
    /**
    *创建人
    */ 
	public String getType_create() {
		return type_create;
	}
	public void setType_create(String type_create) {
		this.type_create = type_create;
	}
    /**
    *最后修改人
    */ 
	public String getType_Update() {
		return type_update;
	}
	public void setType_Update(String type_update) {
		this.type_update = type_update;
	}
    /**
    *最后修改时间
    */ 
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
    /**
    *排序
    */ 
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	/**
	 * 最后修改人
	 */
	public String getType_update() {
		return type_update;
	}
	public void setType_update(String type_update) {
		this.type_update = type_update;
	}
	/**
	 * 模板名称
	 */
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	
}
