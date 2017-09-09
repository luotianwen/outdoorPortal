package com.op.spot.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;
/** 
 * 景点(s_spot)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-09-19 15:19:26 
 */  
public class SpotResult  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
	@Field("id")
  	 private String id;
 	//名称
	@Field("name")
  	 private String name;
 	//门票价格
	@Field("price")
  	 private double price;
 	//景区地址
	@Field("address")
  	 private String address;
 	//景区导语
	@Field("scenicspotintroduction")
  	 private String scenicspotintroduction;
 	//景区标签(多选)
	@Field("labelss")
  	 private String[] labelss;
 	// 经纬度 
	@Field("coordinate")
  	 private String coordinate;
 	//主图
	@Field("url")
  	 private String url;



	 
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
    *名称
	* @return
    */ 
	public String getName() {
		return name;
	}
    /**
    *名称
	* @param type
    */ 
	public void setName(String name) {
		this.name = name;
	}
    /**
    *门票价格
	* @return
    */ 
	public double getPrice() {
		return price;
	}
    /**
    *门票价格
	* @param type
    */ 
	public void setPrice(double price) {
		this.price = price;
	}
    /**
    *景区地址
	* @return
    */ 
	public String getAddress() {
		return address;
	}
    /**
    *景区地址
	* @param type
    */ 
	public void setAddress(String address) {
		this.address = address;
	}
    /**
    *景区导语
	* @return
    */ 
	public String getScenicspotintroduction() {
		return scenicspotintroduction;
	}
    /**
    *景区导语
	* @param type
    */ 
	public void setScenicspotintroduction(String scenicspotintroduction) {
		this.scenicspotintroduction = scenicspotintroduction;
	}
    /**
    *景区标签(多选)
	* @return
    */ 
	public String[] getLabelss() {
		return labelss;
	}
    /**
    *景区标签(多选)
	* @param type
    */ 
	public void setLabelss(String[] labelss) {
		this.labelss = labelss;
	}
    /**
    *主图
	* @return
    */ 
	public String getUrl() {
		return url;
	}
    /**
    *主图
	* @param type
    */ 
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 *  经纬度 
	 * @return
	 */
	public String getCoordinate() {
		return coordinate;
	}
	/**
	 *  经纬度 
	 * @param coordinate
	 */
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}
	
}
