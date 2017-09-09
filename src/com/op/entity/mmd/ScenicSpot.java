package com.op.entity.mmd;

import java.io.Serializable;
import java.util.Date;
/** 
 * 景点(scenicSpot)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-04-26 17:59:43 
 */  
public class ScenicSpot implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private String id;
 	//所属目的地id
  	 private String destination_id;
 	//景点中文名称
  	 private String zh_name;
 	//景点英文名称
  	 private String en_name;
 	//经纬度
  	 private String coordinates;
 	//景点坐标经度
  	 private String lat;
 	//景点坐标纬度
  	 private String lng;
 	//景点地址
  	 private String address;
 	//景点介绍
  	 private String briefing;
 	//电话
  	 private String phone;
 	//网址
  	 private String website;
 	//交通
  	 private String traffic;
 	//门票
  	 private String ticket;
 	//开放时间
  	 private String opening_time;
 	//用时参考（游览时间）
  	 private String tour_time;
 	//编辑人（最后一次）
  	 private String edit_user;
 	//编辑时间（最后一次）
  	 private Date edit_time;


  	 //数据提取地址
  	 private String data_src;
  	 //是否已爬取数据
  	 private String isCrawling;

	 
    /**
    *id
    */ 
	public String getId() {
		return id;
	}
    /**
    *id
    */ 
	public void setId(String id) {
		this.id = id;
	}
    /**
    *所属目的地id
    */ 
	public String getDestination_id() {
		return destination_id;
	}
    /**
    *所属目的地id
    */ 
	public void setDestination_id(String destination_id) {
		this.destination_id = destination_id;
	}
    /**
    *景点中文名称
    */ 
	public String getZh_name() {
		return zh_name;
	}
    /**
    *景点中文名称
    */ 
	public void setZh_name(String zh_name) {
		this.zh_name = zh_name;
	}
    /**
    *景点英文名称
    */ 
	public String getEn_name() {
		return en_name;
	}
    /**
    *景点英文名称
    */ 
	public void setEn_name(String en_name) {
		this.en_name = en_name;
	}
    /**
    *经纬度
	* @return
    */ 
	public String getCoordinates() {
		return coordinates;
	}
    /**
    *经纬度
	* @param type
    */ 
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
    /**
    *景点坐标经度
    */ 
	public String getLat() {
		return lat;
	}
    /**
    *景点坐标经度
    */ 
	public void setLat(String lat) {
		this.lat = lat;
	}
    /**
    *景点坐标纬度
    */ 
	public String getLng() {
		return lng;
	}
    /**
    *景点坐标纬度
    */ 
	public void setLng(String lng) {
		this.lng = lng;
	}
    /**
    *景点地址
    */ 
	public String getAddress() {
		return address;
	}
    /**
    *景点地址
    */ 
	public void setAddress(String address) {
		this.address = address;
	}
    /**
    *景点介绍
    */ 
	public String getBriefing() {
		return briefing;
	}
    /**
    *景点介绍
    */ 
	public void setBriefing(String briefing) {
		this.briefing = briefing;
	}
    /**
    *电话
    */ 
	public String getPhone() {
		return phone;
	}
    /**
    *电话
    */ 
	public void setPhone(String phone) {
		this.phone = phone;
	}
    /**
    *网址
    */ 
	public String getWebsite() {
		return website;
	}
    /**
    *网址
    */ 
	public void setWebsite(String website) {
		this.website = website;
	}
    /**
    *交通
    */ 
	public String getTraffic() {
		return traffic;
	}
    /**
    *交通
    */ 
	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}
    /**
    *门票
    */ 
	public String getTicket() {
		return ticket;
	}
    /**
    *门票
    */ 
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
    /**
    *开放时间
    */ 
	public String getOpening_time() {
		return opening_time;
	}
    /**
    *开放时间
    */ 
	public void setOpening_time(String opening_time) {
		this.opening_time = opening_time;
	}
    /**
    *用时参考（游览时间）
    */ 
	public String getTour_time() {
		return tour_time;
	}
    /**
    *用时参考（游览时间）
    */ 
	public void setTour_time(String tour_time) {
		this.tour_time = tour_time;
	}
    /**
    *编辑人（最后一次）
    */ 
	public String getEdit_user() {
		return edit_user;
	}
    /**
    *编辑人（最后一次）
    */ 
	public void setEdit_user(String edit_user) {
		this.edit_user = edit_user;
	}
    /**
    *编辑时间（最后一次）
    */ 
	public Date getEdit_time() {
		return edit_time;
	}
    /**
    *编辑时间（最后一次）
    */ 
	public void setEdit_time(Date edit_time) {
		this.edit_time = edit_time;
	}
	public String getData_src() {
		return data_src;
	}
	public void setData_src(String data_src) {
		this.data_src = data_src;
	}
	public String getIsCrawling() {
		return isCrawling;
	}
	public void setIsCrawling(String isCrawling) {
		this.isCrawling = isCrawling;
	}
	
	
}
