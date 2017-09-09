package com.op.dto.usercenter.keeper;

import java.io.Serializable;
import java.util.List;

public class MyActiveInfoDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 活动ID
	String id;
	// 标题
	String title;
	// 价格
	double price;
	// 景点
	List<String> jds;
	// 活动主图
	String a_active_img;
	// 审核状态
	int state;
	
	//关闭活动
	String a_close;
	//活动模板
	String template;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getA_active_img() {
		return a_active_img;
	}

	public void setA_active_img(String a_active_img) {
		this.a_active_img = a_active_img;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<String> getJds() {
		return jds;
	}

	public void setJds(List<String> jds) {
		this.jds = jds;
	}

	public String getA_close() {
		return a_close;
	}

	public void setA_close(String a_close) {
		this.a_close = a_close;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}
	
}
