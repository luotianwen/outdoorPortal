package com.op.entity.travels;

import java.io.Serializable;
import java.util.Date;

/**
 * 游记内容(travelsContent)实体类
 * 
 * @author Win Zhong
 * @version Revision: 1.00 Date: 2016-04-18 14:30:32
 */
public class TravelsContent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// id
	private String id;
	// 游记ID
	private String travels_id;
	// 内容类型（1：文字；2：图片；3：视频；4：段落标题；5：day模板）
	private String content_type;
	// 内容
	private String content;
	// 图片ID
	private String photo_id;
	// 段落标题ID
	private String title_id;
	// 视频ID
	private String video_id;
	// 排序
	private int sort;
	//创建人
	private String user_id;
	//创建时间
	private Date create_time;
	//修改时间
	private Date update_time;
	// 父内容ID（默认0为一级）
	private String parent_id;
	
	//内容图片
	private TravelsPhotos travelsPhotos;
	//内容段落
	private TravelsTitle travelsTitle;
	
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

	/**
	 * 内容图片
	 */
	public TravelsPhotos getTravelsPhotos() {
		return travelsPhotos;
	}

	/**
	 * 内容图片
	 */
	public void setTravelsPhotos(TravelsPhotos travelsPhotos) {
		this.travelsPhotos = travelsPhotos;
	}

	/**
	 * 内容标题
	 */
	public TravelsTitle getTravelsTitle() {
		return travelsTitle;
	}

	/**
	 * 内容标题
	 */
	public void setTravelsTitle(TravelsTitle travelsTitle) {
		this.travelsTitle = travelsTitle;
	}

}
