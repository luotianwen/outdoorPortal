package com.op.dto.travels;

import java.util.Date;

public class TravelsDraftDTO {
	// id
	private String id;
	// 发布者ID
	private String user_id;
	// 游记标题
	private String title;
	// 创建时间
	private Date create_time;
	// 修改时间
	private Date update_time;
	// 发布状态（1：草稿；2：发布审核；3：发布成功；4：审核失败；5：再次审核）
	private String issued_state;
	//是否删除
	private int isdelete;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public String getIssued_state() {
		return issued_state;
	}
	public void setIssued_state(String issued_state) {
		this.issued_state = issued_state;
	}
	public int getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}
	
}
