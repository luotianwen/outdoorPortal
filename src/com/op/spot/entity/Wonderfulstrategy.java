package com.op.spot.entity;
import java.io.Serializable;

public class Wonderfulstrategy implements Serializable {
	 
	
	//columns START
    /**
     * id       db_column: id 
     */ 	
	private Integer id;
    /**
     * 景点id       db_column: sid 
     */ 	
	private Integer sid;
    /**
     * 概述       db_column: content 
     */ 	
	private String content;

	private String name;
	private String sname;
    /**
     * 是否首页(默认0 显示1)       db_column: isfirst 
     */ 	
	private Integer isfirst;
	//columns主图
	private String pho;
	private String creator;
	private String cdate;

	public String getPho() {
		return pho;
	}

	public void setPho(String pho) {
		this.pho = pho;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

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
	public void setContent(String value) {
		this.content = value;
	}
	
	public String getContent() {
		return this.content;
	}
	public void setIsfirst(Integer value) {
		this.isfirst = value;
	}
	
	public Integer getIsfirst() {
		return this.isfirst;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}
}
 
