package com.op.dto.travels;

import java.io.Serializable;
import java.util.Date;

public class TravelsHotDataDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	// id
	String id;
	// 发布者名称
	String userName;
	// 发布者id
	String user_id;
	// 发布者头像
	String uHeadImg;

	// 封面图片
	String cover_image;
	// 点赞次数（顶）
	String like_count;
	// 收藏次数
	String collection_count;
	// 分享次数
	String share_count;
	// 阅读次数
	String read_count;
	// 回复次数
	String reply_count;
	// 发布状态（1：草稿；2：发布审核； 3：发布成功；4：审核失败；5：再次审核）
	String issued_state;
	// 是否删除(0：正常；1：删除)
	String isdelete;

	// 出发日期
	Date departure_time;
	String departure_time_str;
	// 出行天数
	String travel_days;
	// 人物
	String travel_person;
	// 人均费用
	String per_capita_cost;
 	
     /**
      *id
  	* @return
      */ 
  	public String getId() {
  		return id;
  	}
      /**
      *id
  	* @param type
      */ 
  	public void setId(String id) {
  		this.id = id;
  	}
	/**
	 * 发布者名称
	 * @return
	 */
    public String getUserName() {
		return userName;
	}
    /**
     * 发布者名称
     * @param userName
     */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 发布者id
	 * @return
	 */
    public String getUser_id() {
		return user_id;
	}
    /**
     * 发布者id
     * @param user_id
     */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
   
    /**
    *点赞次数（顶）
	* @return
    */ 
	public String getLike_count() {
		return like_count;
	}
    /**
    *点赞次数（顶）
	* @param type
    */ 
	public void setLike_count(String like_count) {
		this.like_count = like_count;
	}
    /**
    *收藏次数
	* @return
    */ 
	public String getCollection_count() {
		return collection_count;
	}
    /**
    *收藏次数
	* @param type
    */ 
	public void setCollection_count(String collection_count) {
		this.collection_count = collection_count;
	}
    /**
    *分享次数
	* @return
    */ 
	public String getShare_count() {
		return share_count;
	}
    /**
    *分享次数
	* @param type
    */ 
	public void setShare_count(String share_count) {
		this.share_count = share_count;
	}
    /**
    *阅读次数
	* @return
    */ 
	public String getRead_count() {
		return read_count;
	}
    /**
    *阅读次数
	* @param type
    */ 
	public void setRead_count(String read_count) {
		this.read_count = read_count;
	}
    /**
    *回复次数
	* @return
    */ 
	public String getReply_count() {
		return reply_count;
	}
    /**
    *回复次数
	* @param type
    */ 
	public void setReply_count(String reply_count) {
		this.reply_count = reply_count;
	}
    /**
    *发布状态（1：草稿；2：发布审核； 3：发布成功；4：审核失败；5：再次审核）
	* @return
    */ 
	public String getIssued_state() {
		return issued_state;
	}
    /**
    *发布状态（1：草稿；2：发布审核； 3：发布成功；4：审核失败；5：再次审核）
	* @param type
    */ 
	public void setIssued_state(String issued_state) {
		this.issued_state = issued_state;
	}
    /**
    *是否删除(0：正常；1：删除)
	* @return
    */ 
	public String getIsdelete() {
		return isdelete;
	}
    /**
    *是否删除(0：正常；1：删除)
	* @param type
    */ 
	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}
	/**
	 * 发布者头像
	 * @return
	 */
	public String getuHeadImg() {
		return uHeadImg;
	}
	/**
	 * 发布者头像
	 * @param uHeadImg
	 */
	public void setuHeadImg(String uHeadImg) {
		this.uHeadImg = uHeadImg;
	}
    /**
    *封面图片
	* @return
    */ 
	public String getCover_image() {
		return cover_image;
	}
    /**
    *封面图片
	* @param type
    */ 
	public void setCover_image(String cover_image) {
		this.cover_image = cover_image;
	}
	/**
	 * 出发日期
	 */
	public Date getDeparture_time() {
		return departure_time;
	}
	/**
	 * 出发日期
	 */
	public void setDeparture_time(Date departure_time) {
		this.departure_time = departure_time;
	}
	public String getDeparture_time_str() {
		return departure_time_str;
	}
	public void setDeparture_time_str(String departure_time_str) {
		this.departure_time_str = departure_time_str;
	}
	/**
	 * 出行天数
	 */
	public String getTravel_days() {
		return travel_days;
	}
	/**
	 * 出行天数
	 */
	public void setTravel_days(String travel_days) {
		this.travel_days = travel_days;
	}
	/**
	 * 人物
	 */
	public String getTravel_person() {
		return travel_person;
	}
	/**
	 * 人物
	 */
	public void setTravel_person(String travel_person) {
		this.travel_person = travel_person;
	}
	/**
	 * 人均费用
	 */
	public String getPer_capita_cost() {
		return per_capita_cost;
	}
	/**
	 * 人均费用
	 */
	public void setPer_capita_cost(String per_capita_cost) {
		this.per_capita_cost = per_capita_cost;
	}
   
}

