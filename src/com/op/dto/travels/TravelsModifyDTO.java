package com.op.dto.travels;

public class TravelsModifyDTO {
	//游记id
	private String id;
	//操作行为  insert update delete
	private String act;
	//序列
	private String content_id;
	//用户ID
	private String user_id;
	//数据类型与内容
	private TravelsModifyData data;
	//最大排序数
	private int sort;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAct() {
		return act;
	}
	public void setAct(String act) {
		this.act = act;
	}
	public String getContent_id() {
		return content_id;
	}
	public void setContent_id(String content_id) {
		this.content_id = content_id;
	}
	public TravelsModifyData getData() {
		return data;
	}
	public void setData(TravelsModifyData data) {
		this.data = data;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	 
	
}
