package com.op.entity.search;

import java.util.Date;

import org.apache.solr.client.solrj.util.ClientUtils;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * =============================================================
 * 项目名：outdoorPortal
 * 类描述：查询DTO
 * 创建人：WinZhong
 * 创建时间： 2015-11-30
 * modification list: 2015-11-30	创建
 * 					  2016-02-24	新增多种筛选条件，剔除冗余字段
 * =============================================================
 */
public class Search {

	// 搜索关键字(玩什么)
	private String keyword;
	// 位置中文名称
	private String position;
	// 地图中心点经纬度
	private String bl;
	// 地图东北角坐标（用作查询半径用）
	private String northEast;
	// 距离范围 单位公里 （半径）
	private double d;
	// 开始时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date st;
	// 结束时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lt;
	
	
	/*------------------------		筛选插件	-------------------------------*/
	
	// 活动类型:CHECKBOX(分隔符：,)
	private String types;
	
	// 发布者类型{1:"个人发布者",2:"企业"}：CHECKBOX(分隔符：,)
	private String act;
	
	// 活动是否收费{1:"收费",2:"免费"}：CHECKBOX(分隔符：,)
	private String isCharge;
	
	// 活动时长{1:"1天",2:"2天",3:"3天",4:"3天以上"}：RADIO
	private Integer a_date_length;
	
	// 适合人群{1:"成人",2:"儿童",3:"家庭",4:"夫妻",5:"朋友"}：RADIO
	private Integer sc_id;
	
	// 活动难度等级{1:"休闲",2:"一级(入门级)",3:"二级",4:"三级",5:"四级",6:"五级(专业)"}：RADIO
	private Integer a_difficulty_type;
	
	// 适合儿童年龄段{1:"婴儿(0-1)",2:"幼儿(2-4)",3:"大孩子(5-8)",4:"少年(9-12)",5:"青少年(13-18)"}：RADIO
	private Integer a_children_age;
	
	// 活动收费价格start
	private double price_start;
	
	// 活动收费价格end
	private double price_end;

	//是否高亮关键字
	private boolean isHighlight = false;
	
	
	/*------------------------------------	get && set-------------------------------------*/
	
	
	
	public Integer getSc_id() {
		return sc_id;
	}


	public String getTypes() {
		return types;
	}


	public void setTypes(String types) {
		this.types = types;
	}


	public String getIsCharge() {
		return isCharge;
	}


	public void setIsCharge(String isCharge) {
		this.isCharge = isCharge;
	}


	public void setSc_id(Integer sc_id) {
		this.sc_id = sc_id;
	}

	public Integer getA_children_age() {
		return a_children_age;
	}

	public void setA_children_age(Integer a_children_age) {
		this.a_children_age = a_children_age;
	}

	public Integer getA_date_length() {
		return a_date_length;
	}

	public void setA_date_length(Integer a_date_length) {
		this.a_date_length = a_date_length;
	}

	public Integer getA_difficulty_type() {
		return a_difficulty_type;
	}

	public void setA_difficulty_type(Integer a_difficulty_type) {
		this.a_difficulty_type = a_difficulty_type;
	}

	public String getKeyword() {
		if (keyword == null) {
			return "*";
		} else {
			return ClientUtils.escapeQueryChars(keyword);
		}
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getBl() {
		return bl;
	}

	public void setBl(String bl) {
		this.bl = bl;
	}


	public Date getSt() {
		return st;
	}

	public void setSt(Date st) {
		this.st = st;
	}

	public Date getLt() {
		return lt;
	}

	public void setLt(Date lt) {
		this.lt = lt;
	}

	public double getD() {
		return d;
	}

	public void setD(double d) {
		this.d = d;
	}

	public String getPosition() {
		if (position == null) {
			return "";
		} else {
			return position;
		}
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getNorthEast() {
		return northEast;
	}

	public void setNorthEast(String northEast) {
		this.northEast = northEast;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public double getPrice_start() {
		return price_start;
	}

	public void setPrice_start(double price_start) {
		this.price_start = price_start;
	}

	public double getPrice_end() {
		return price_end;
	}

	public void setPrice_end(double price_end) {
		this.price_end = price_end;
	}


	public boolean isHighlight() {
		if (keyword != null) {
			isHighlight = true;
		}
		return isHighlight;
	}


	public void setHighlight(boolean isHighlight) {
		this.isHighlight = isHighlight;
	}


}
