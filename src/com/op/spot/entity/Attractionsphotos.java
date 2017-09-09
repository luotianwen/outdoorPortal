package com.op.spot.entity;
import java.io.Serializable;

/**
 * 景点照片
 */
public class Attractionsphotos  implements Serializable {
	 
	
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
     * 图片地址       db_column: pho 
     */ 	
	private String pho;
	//columns END
	//图片类型
	private String status;
 
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	 
}
 
