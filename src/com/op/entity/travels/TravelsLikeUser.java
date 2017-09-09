package com.op.entity.travels;

import java.io.Serializable;
import java.util.Date;

/**
 * 点赞用户(travelsLikeUser)实体类
 * 
 * @author 世峰户外商城
 * @version Revision: 1.00 Date: 2016-04-21 17:01:29
 */
public class TravelsLikeUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// id
	private String id;
	// 用户id
	private String user_id;
	// 游记id
	private String travels_id;
	// 点赞时间
	private Date create_time;

	/**
	 * id
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 用户id
	 */
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * 游记id
	 */
	public String getTravels_id() {
		return travels_id;
	}

	public void setTravels_id(String travels_id) {
		this.travels_id = travels_id;
	}

	/**
	 * 点赞时间
	 */
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

}
