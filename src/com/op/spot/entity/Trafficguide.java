package com.op.spot.entity;
import java.io.Serializable;

/**
 * 交通指南
 */
public class Trafficguide  implements Serializable {
	 
	
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
     * 线路图       db_column: pho 
     */ 	
	private String pho;
	private String name;
    /**
     * 乘车路线       db_column: busline 
     */ 	
	private String busline;
    /**
     * 自驾路线       db_column: selfdrivingroute 
     */ 	
	private String selfdrivingroute;
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
	public void setPho(String value) {
		this.pho = value;
	}
	
	public String getPho() {
		return this.pho;
	}
	public void setBusline(String value) {
		this.busline = value;
	}
	
	public String getBusline() {
		return this.busline;
	}
	public void setSelfdrivingroute(String value) {
		this.selfdrivingroute = value;
	}
	
	public String getSelfdrivingroute() {
		return this.selfdrivingroute;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
 
