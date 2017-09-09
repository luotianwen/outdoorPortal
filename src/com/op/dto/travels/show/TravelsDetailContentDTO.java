package com.op.dto.travels.show;

import java.util.Date;

/**
 * =============================================================
 * 项目名：outdoorPortal
 * 类描述：游记内容DTO
 * 创建人：Yan
 * 创建时间： 2016-4-25
 * modification list：
 * =============================================================
 */
public class TravelsDetailContentDTO {

	// id
	String id;
	// 游记ID
	String travels_id;
	// 内容类型（1：文字；2：图片；3：视频；4：段落标题；5：day模板）
	String content_type;
	// 内容
	String content;
	// 图片ID
	String photo_id;
	// 段落标题ID
	String title_id;
	// 视频ID
	String video_id;
	// 排序
	int sort;
	//创建人
	String user_id;
	//创建时间
	Date create_time;
	//修改时间
	Date update_time;
	// 父内容ID（默认0为一级）
	String parent_id;
	
	// 游记图片
	TravelsDetailPhotosDTO photo;
	
	// 游记标题
	TravelsDetailTitleDTO title;
	
	
	public TravelsDetailPhotosDTO getPhoto() {
		return photo;
	}

	public void setPhoto(TravelsDetailPhotosDTO photo) {
		this.photo = photo;
	}

	public TravelsDetailTitleDTO getTitle() {
		return title;
	}

	public void setTitle(TravelsDetailTitleDTO title) {
		this.title = title;
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
	 * 游记ID
	 */
	public String getTravels_id() {
		return travels_id;
	}

	/**
	 * 游记ID
	 */
	public void setTravels_id(String travels_id) {
		this.travels_id = travels_id;
	}

	/**
	 * 内容类型（1：文字；2：图片；3：视频；4：段落标题；5：day模板）
	 */
	public String getContent_type() {
		return content_type;
	}

	/**
	 * 内容类型（1：文字；2：图片；3：视频；4：段落标题；5：day模板）
	 */
	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}

	/**
	 * 内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 图片ID
	 */
	public String getPhoto_id() {
		return photo_id;
	}

	/**
	 * 图片ID
	 */
	public void setPhoto_id(String photo_id) {
		this.photo_id = photo_id;
	}

	/**
	 * 段落标题ID
	 */
	public String getTitle_id() {
		return title_id;
	}

	/**
	 * 段落标题ID
	 */
	public void setTitle_id(String title_id) {
		this.title_id = title_id;
	}

	/**
	 * 视频ID
	 */
	public String getVideo_id() {
		return video_id;
	}

	/**
	 * 视频ID
	 */
	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}

	/**
	 * 排序
	 */
	public int getSort() {
		return sort;
	}

	/**
	 * 排序
	 */
	public void setSort(int sort) {
		this.sort = sort;
	}

	/**
	 * 创建人
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * 创建人
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	 * 父内容ID（默认0为一级）
	 */
	public String getParent_id() {
		return parent_id;
	}

	/**
	 * 父内容ID（默认0为一级）
	 */
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

}
