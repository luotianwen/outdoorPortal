package com.op.spot.entity;
import java.io.Serializable;

/**
 * 出行小贴士
 */
public class Traveltips  implements Serializable {
	 
	
	//columns START
    /**
     * id       db_column: id 
     */ 	
	private Integer id;
    /**
     * 景点id       db_column: sid 
     */ 	
	private Integer sid;

	private String name;
    /**
     * 旺季       db_column: bbusyseason 
     */ 	
	private String bbusyseason;
    /**
     * 旺季结束       db_column: ebusyseason 
     */ 	
	private String ebusyseason;
    /**
     * 淡季       db_column: blowseason 
     */ 	
	private String blowseason;
    /**
     * 淡季结束       db_column: elowseason 
     */ 	
	private String elowseason;
    /**
     * 提示       db_column: notice 
     */ 	
	private String notice;
    /**
     * 景区电话       db_column: phone 
     */ 	
	private String phone;
    /**
     * 最佳旅游时间       db_column: besttime 
     */ 	
	private String besttime;
    /**
     * 大约浏览时间       db_column: aboutbrowsingtime 
     */ 	
	private String aboutbrowsingtime;
    /**
     * 景点设施       db_column: attractionsfacilities 
     */ 	
	private String attractionsfacilities;
	//columns END

 
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setSid(Integer value) {
		this.sid = value;
	}
	
	public Integer getSid() {
		return this.sid;
	}
	public void setBbusyseason(String value) {
		this.bbusyseason = value;
	}
	
	public String getBbusyseason() {
		return this.bbusyseason;
	}
	public void setEbusyseason(String value) {
		this.ebusyseason = value;
	}
	
	public String getEbusyseason() {
		return this.ebusyseason;
	}
	public void setBlowseason(String value) {
		this.blowseason = value;
	}
	
	public String getBlowseason() {
		return this.blowseason;
	}
	public void setElowseason(String value) {
		this.elowseason = value;
	}
	
	public String getElowseason() {
		return this.elowseason;
	}
	public void setNotice(String value) {
		this.notice = value;
	}
	
	public String getNotice() {
		return this.notice;
	}
	public void setPhone(String value) {
		this.phone = value;
	}
	
	public String getPhone() {
		return this.phone;
	}
	public void setBesttime(String value) {
		this.besttime = value;
	}
	
	public String getBesttime() {
		return this.besttime;
	}
	public void setAboutbrowsingtime(String value) {
		this.aboutbrowsingtime = value;
	}
	
	public String getAboutbrowsingtime() {
		return this.aboutbrowsingtime;
	}
	public void setAttractionsfacilities(String value) {
		this.attractionsfacilities = value;
	}
	
	public String getAttractionsfacilities() {
		return this.attractionsfacilities;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
 
