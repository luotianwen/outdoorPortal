package com.op.dto.travels;

import java.io.Serializable;
import java.util.Date;

public class MyTravelsResultsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private String id;
 	//发布者ID
  	 private String user_id;
  	 //发布者名称
  	 private String userName;
  	 //发布者头像
  	 private String uHeadImg;
 	//游记标题
  	 private String title;
	//封面图片
	private String cover_image;
 	//游记精简内容
  	 private String digest;
 	//关联目的地
  	 private String address;
 	//关联目的地ID
  	 private String address_id;
 	//点赞次数（顶）
  	 private String like_count;
 	//收藏次数
  	 private String collection_count;
 	//分享次数
  	 private String share_count;
 	//阅读次数
  	 private String read_count;
 	//回复次数
  	 private String reply_count;
 	//发布时间
  	 private Date issued_time;
 	//发布状态（1：草稿；2：发布审核； 3：发布成功；4：审核失败；5：再次审核）
  	 private String issued_state;

	 
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
    *发布者ID
	* @return
    */ 
	public String getUser_id() {
		return user_id;
	}
    /**
    *发布者ID
	* @param type
    */ 
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
    *游记标题
	* @return
    */ 
	public String getTitle() {
		return title;
	}
    /**
    *游记标题
	* @param type
    */ 
	public void setTitle(String title) {
		this.title = title;
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
    *关联目的地
	* @return
    */ 
	public String getAddress() {
		return address;
	}
    /**
    *关联目的地
	* @param type
    */ 
	public void setAddress(String address) {
		this.address = address;
	}
    /**
    *关联目的地ID
	* @return
    */ 
	public String getAddress_id() {
		return address_id;
	}
    /**
    *关联目的地ID
	* @param type
    */ 
	public void setAddress_id(String address_id) {
		this.address_id = address_id;
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
    *发布时间
	* @return
    */ 
	public Date getIssued_time() {
		return issued_time;
	}
    /**
    *发布时间
	* @param type
    */ 
	public void setIssued_time(Date issued_time) {
		this.issued_time = issued_time;
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
	 * 游记精简内容
	 * @return
	 */
	public String getDigest() {
		return digest;
	}
	/**
	 * 游记精简内容
	 * @param digest
	 */
	public void setDigest(String digest) {
		this.digest = digest;
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
}

