package com.op.spot.entity;
import java.io.Serializable;

/**
 * 景点
 */
public class Spot  implements Serializable {
	 
	
	//columns START
    /**
     * id       db_column: id 
     */ 	
	private Integer id;
    /**
     * 名称       db_column: name 
     */ 	
	private String name;
    /**
     * 景区级别       db_column: level 
     */ 	
	private String level;
    /**
     * 门票价格       db_column: price 
     */ 	
	private Double price;
    /**
     * 景区地址       db_column: address 
     */ 	
	private String address;
    /**
     * 景区导语       db_column: scenicspotintroduction 
     */ 	
	private String scenicspotintroduction;
    /**
     * 渠道id       db_column: channel 
     */ 	
	private Integer channel;
    /**
     * 景区标签       db_column: labelss 
     */ 	
	private String labelss;
    /**
     * 创建时间       db_column: cdate 
     */ 	
	private String cdate;
    /**
     * 状态       db_column: status 
     */ 	
	private Integer status;
    /**
     * 编号       db_column: resourceId 
     */ 	
	private String resourceId;
    /**
     * 景区类型       db_column: type 
     */ 	
	private String type;
    /**
     * 省       db_column: province 
     */ 	
	private Integer province;
    /**
     * 市       db_column: city 
     */ 	
	private Integer city;
    /**
     * 区       db_column: area 
     */ 	
	private Integer area;
    /**
     * 网址       db_column: website 
     */ 	
	private String website;
    /**
     * 经度       db_column: longitude 
     */ 	
	private String longitude;
    /**
     * 纬度       db_column: latitude 
     */ 	
	private String latitude;
    /**
     * 主图       db_column: url 
     */ 	
	private String url;
	//columns END
	private String suitablecrowd;
	private String lastdate;

	public String getSuitablecrowd() {
		return suitablecrowd;
	}

	public void setSuitablecrowd(String suitablecrowd) {
		this.suitablecrowd = suitablecrowd;
	}

	public String getLastdate() {
		return lastdate;
	}

	public void setLastdate(String lastdate) {
		this.lastdate = lastdate;
	}

	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	public void setLevel(String value) {
		this.level = value;
	}
	
	public String getLevel() {
		return this.level;
	}
	public void setPrice(Double value) {
		this.price = value;
	}
	
	public Double getPrice() {
		return this.price;
	}
	public void setAddress(String value) {
		this.address = value;
	}
	
	public String getAddress() {
		return this.address;
	}
	public void setScenicspotintroduction(String value) {
		this.scenicspotintroduction = value;
	}
	
	public String getScenicspotintroduction() {
		return this.scenicspotintroduction;
	}
	public void setChannel(Integer value) {
		this.channel = value;
	}
	
	public Integer getChannel() {
		return this.channel;
	}
	public void setLabelss(String value) {
		this.labelss = value;
	}
	
	public String getLabelss() {
		return this.labelss;
	}
	public void setCdate(String value) {
		this.cdate = value;
	}
	
	public String getCdate() {
		return this.cdate;
	}
	public void setStatus(Integer value) {
		this.status = value;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	public void setResourceId(String value) {
		this.resourceId = value;
	}
	
	public String getResourceId() {
		return this.resourceId;
	}
	public void setType(String value) {
		this.type = value;
	}
	
	public String getType() {
		return this.type;
	}
	public void setProvince(Integer value) {
		this.province = value;
	}
	
	public Integer getProvince() {
		return this.province;
	}
	public void setCity(Integer value) {
		this.city = value;
	}
	
	public Integer getCity() {
		return this.city;
	}
	public void setArea(Integer value) {
		this.area = value;
	}
	
	public Integer getArea() {
		return this.area;
	}
	public void setWebsite(String value) {
		this.website = value;
	}
	
	public String getWebsite() {
		return this.website;
	}
	public void setLongitude(String value) {
		this.longitude = value;
	}
	
	public String getLongitude() {
		return this.longitude;
	}
	public void setLatitude(String value) {
		this.latitude = value;
	}
	
	public String getLatitude() {
		return this.latitude;
	}
	public void setUrl(String value) {
		this.url = value;
	}
	
	public String getUrl() {
		return this.url;
	}

	
	 
}
 
