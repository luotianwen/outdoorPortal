package com.op.spot.entity;
import java.io.Serializable;

/**
 * 点评项目
 */
public class Project  implements Serializable {
	 
	
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
     * 状态(1正常0下线)       db_column: status 
     */ 	
	private Integer status;
    /**
     * 更新时间       db_column: cdate 
     */ 	
	private String cdate;
    /**
     * 说明       db_column: notes 
     */ 	
	private String notes;
	//columns END

 
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
	public void setStatus(Integer value) {
		this.status = value;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	public void setCdate(String value) {
		this.cdate = value;
	}
	
	public String getCdate() {
		return this.cdate;
	}
	public void setNotes(String value) {
		this.notes = value;
	}
	
	public String getNotes() {
		return this.notes;
	}

	
	 
}
 
