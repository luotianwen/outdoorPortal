package com.op.spot.entity;
import java.io.Serializable;

/**
 * 点评图片
 */
public class CommentPho  implements Serializable {
	 
	
	//columns START
    /**
     * id       db_column: id 
     */ 	
	private Integer id;
    /**
     * 点评id       db_column: cid 
     */ 	
	private Integer cid;
    /**
     * 图片地址       db_column: pho 
     */ 	
	private String pho;
	//columns END

 
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setCid(Integer value) {
		this.cid = value;
	}
	
	public Integer getCid() {
		return this.cid;
	}
	public void setPho(String value) {
		this.pho = value;
	}
	
	public String getPho() {
		return this.pho;
	}

	
	 
}
 
