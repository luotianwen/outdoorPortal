package com.op.spot.dto;

import org.apache.commons.lang.StringUtils;

import com.op.solr.util.SolrUtils;

/**
 * 
 * @author WinZhong
 *
 */
public class SpotSearchParameter {
	
 	//搜索关键字(景点关键字、地址……)
	private String keyword;
	// 地图中心点经纬度(纬度,经度）(纬度,经度）
	private String bl;
	// 地图东北角坐标（用作查询半径用）
	private String northEast;
	// 距离范围 单位公里 （半径）
	private double d;
	//是否高亮关键字
	private boolean isHighlight = false;
	//游玩主题(多选) labelss  多个英文(,)逗号隔开
	private String t;
	//景区级别
	private String level;
	//适合人群(多选) suitablecrowd  多个英文(,)逗号隔开
	private String sc;
	//收费价格start
	private double ps;
	//收费价格end
	private double pe;
	//省
	private String province;
	//市
	private String city;
	//区
	private String district;
	
	
	/**
	 * 搜索关键字(景点关键字、地址……)
	 * @return
	 */
	public String getKeyword() {
		return keyword;	
	}
	/**
	 * 搜索关键字(景点关键字、地址……)
	 * @param keyword
	 */
	public void setKeyword(String keyword) {
		if(StringUtils.isBlank(keyword) || keyword == null){
			this.keyword =  "*";		
		}else{
			//isHighlight = true;
			//转义特殊字符，防止注入
			this.keyword =  SolrUtils.escapeQueryChars(keyword);
		}
	}
	/**
	 * 地图中心点经纬度(纬度,经度）
	 * @return
	 */
	public String getBl() {
		return bl;
	}
	/**
	 * 地图中心点经纬度(纬度,经度）
	 * @param bl
	 */
	public void setBl(String bl) {
		this.bl = bl;
	}
	/**
	 * 地图东北角坐标（用作查询半径用）
	 * @return
	 */
	public String getNorthEast() {
		return northEast;
	}
	/**
	 * 地图东北角坐标（用作查询半径用）
	 * @param northEast
	 */
	public void setNorthEast(String northEast) {
		this.northEast = northEast;
	}
	/**
	 * 距离范围 单位公里 （半径）
	 * @return
	 */
	public double getD() {
		return d;
	}
	/**
	 * 距离范围 单位公里 （半径）
	 * @param d
	 */
	public void setD(double d) {
		this.d = d;
	} 
	
	/**
	 * 是否高亮关键字
	 * @return
	 */
	public boolean isHighlight() {
		if (keyword != null) {
			isHighlight = true;
		}
		return isHighlight;
	}

	/**
	 * 是否高亮关键字
	 * @param isHighlight
	 */
	public void setHighlight(boolean isHighlight) {
		this.isHighlight = isHighlight;
	}
	public String getT() {
		return t;
	}
	public void setT(String t) {
		this.t = t;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getSc() {
		return sc;
	}
	public void setSc(String sc) {
		this.sc = sc;
	}
	public double getPs() {
		return ps;
	}
	public void setPs(double ps) {
		this.ps = ps;
	}
	public double getPe() {
		return pe;
	}
	public void setPe(double pe) {
		this.pe = pe;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	
	
}
