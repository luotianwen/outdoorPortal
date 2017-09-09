package com.op.spot.entity;
import java.io.Serializable;

public class Characteristicaspect  implements Serializable {
	 
	
	//columns START
    /**
     * id       db_column: id 
     */ 	
	private Integer id;
	private String name;
    /**
     * 景点id       db_column: sid 
     */ 	
	private Integer sid;
    /**
     * 内容       db_column: content 
     */ 	
	private String content;
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
	public void setContent(String value) {
		this.content = value;
	}
	
	public String getContent() {
		return this.content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
 
