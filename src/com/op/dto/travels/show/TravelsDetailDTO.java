package com.op.dto.travels.show;

import java.util.Date;

/**
 * =============================================================
 * 项目名：outdoorPortal
 * 类描述：游记详情DTO
 * 创建人：Yan
 * 创建时间： 2016-4-25
 * modification list：
 * =============================================================
 */
public class TravelsDetailDTO {

	// id
	String id;
	// 发布者ID
	String user_id;
	// 游记主图
	String main_image;
	// 游记主图（截取部分）
	String show_image;
	// 游记主图视频
	String main_video;
	// 游记标题
	String title;
	// 封面图片id
	String cover_image_id;
	// 背景音乐
	String music;
	// 出发时间
	Date departure_time;
	// 出行天数
	int travel_days;
	// 人物
	String travel_person;
	// 人均费用
	int per_capita_cost;
	// 关联目的地
	String address;
	// 点赞次数（顶）
	int like_count;
	// 收藏次数
	int collection_count;
	// 分享次数
	int share_count;
	// 阅读次数
	int read_count;
	// 回复次数
	int reply_count;
	// 发布时间
	Date issued_time;
	// 创建时间
	Date create_time;
	// 修改时间
	Date update_time;
	// 发布状态（1：草稿；2：发布审核；3：发布成功；4：审核失败；5：再次审核）
	String issued_state;
	// 审核人ID
	String auditor_id;
	// 审核时间
	Date auditor_time;
	//是否删除
	int isdelete;
	
	// 游记
	java.util.List<TravelsDetailContentDTO> contents;
	
	
	public java.util.List<TravelsDetailContentDTO> getContents() {
		return contents;
	}

	public void setContents(java.util.List<TravelsDetailContentDTO> contents) {
		this.contents = contents;
	}

	/**
	 * id
	 */
	public String getId() {
		return id;
	}

	/**
	 * id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 发布者ID
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * 发布者ID
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * 游记主图
	 */
	public String getMain_image() {
		return main_image;
	}

	/**
	 * 游记主图
	 */
	public void setMain_image(String main_image) {
		this.main_image = main_image;
	}

	/**
	 * 游记主图（截取部分）
	 */
	public String getShow_image() {
		return show_image;
	}

	/**
	 * 游记主图（截取部分）
	 */
	public void setShow_image(String show_image) {
		this.show_image = show_image;
	}

	/**
	 * 游记主图视频
	 */
	public String getMain_video() {
		return main_video;
	}

	/**
	 * 游记主图视频
	 */
	public void setMain_video(String main_video) {
		this.main_video = main_video;
	}

	/**
	 * 游记标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 游记标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 封面图片id
	 */
	public String getCover_image_id() {
		return cover_image_id;
	}

	/**
	 * 封面图片id
	 */
	public void setCover_image_id(String cover_image_id) {
		this.cover_image_id = cover_image_id;
	}

	/**
	 * 背景音乐
	 */
	public String getMusic() {
		return music;
	}

	/**
	 * 背景音乐
	 */
	public void setMusic(String music) {
		this.music = music;
	}

	/**
	 * 出发时间
	 */
	public Date getDeparture_time() {
		return departure_time;
	}

	/**
	 * 出发时间
	 */
	public void setDeparture_time(Date departure_time) {
		this.departure_time = departure_time;
	}

	/**
	 * 出行天数
	 */
	public int getTravel_days() {
		return travel_days;
	}

	/**
	 * 出行天数
	 */
	public void setTravel_days(int travel_days) {
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
	public int getPer_capita_cost() {
		return per_capita_cost;
	}

	/**
	 * 人均费用
	 */
	public void setPer_capita_cost(int per_capita_cost) {
		this.per_capita_cost = per_capita_cost;
	}

	/**
	 * 关联目的地
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 关联目的地
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 点赞次数（顶）
	 */
	public int getLike_count() {
		return like_count;
	}

	/**
	 * 点赞次数（顶）
	 */
	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}

	/**
	 * 收藏次数
	 */
	public int getCollection_count() {
		return collection_count;
	}

	/**
	 * 收藏次数
	 */
	public void setCollection_count(int collection_count) {
		this.collection_count = collection_count;
	}

	/**
	 * 分享次数
	 */
	public int getShare_count() {
		return share_count;
	}

	/**
	 * 分享次数
	 */
	public void setShare_count(int share_count) {
		this.share_count = share_count;
	}

	/**
	 * 阅读次数
	 */
	public int getRead_count() {
		return read_count;
	}

	/**
	 * 阅读次数
	 */
	public void setRead_count(int read_count) {
		this.read_count = read_count;
	}

	/**
	 * 回复次数
	 */
	public int getReply_count() {
		return reply_count;
	}

	/**
	 * 回复次数
	 */
	public void setReply_count(int reply_count) {
		this.reply_count = reply_count;
	}

	/**
	 * 发布时间
	 */
	public Date getIssued_time() {
		return issued_time;
	}

	/**
	 * 发布时间
	 */
	public void setIssued_time(Date issued_time) {
		this.issued_time = issued_time;
	}

	/**
	 * 创建时间
	 */
	public Date getCreate_time() {
		return create_time;
	}

	/**
	 * 创建时间
	 */
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	/**
	 * 修改时间
	 */
	public Date getUpdate_time() {
		return update_time;
	}

	/**
	 * 修改时间
	 */
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	/**
	 * 发布状态（1：草稿；2：发布审核；3：发布成功；4：审核失败；5：再次审核）
	 */
	public String getIssued_state() {
		return issued_state;
	}

	/**
	 * 发布状态（1：草稿；2：发布审核；3：发布成功；4：审核失败；5：再次审核）
	 */
	public void setIssued_state(String issued_state) {
		this.issued_state = issued_state;
	}

	/**
	 * 审核人ID
	 */
	public String getAuditor_id() {
		return auditor_id;
	}

	/**
	 * 审核人ID
	 */
	public void setAuditor_id(String auditor_id) {
		this.auditor_id = auditor_id;
	}

	/**
	 * 审核时间
	 */
	public Date getAuditor_time() {
		return auditor_time;
	}

	/**
	 * 审核时间
	 */
	public void setAuditor_time(Date auditor_time) {
		this.auditor_time = auditor_time;
	}

	/**
	 * 是否删除
	 */
	public int getIsdelete() {
		return isdelete;
	}

	/**
	 * 是否删除
	 */
	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}

}
