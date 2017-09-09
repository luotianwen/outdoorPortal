package com.op.spot.entity;
import java.io.Serializable;

/**
 * 门票价格
 */
public class Ticketprice  implements Serializable {
	 
	
	//columns START
    /**
     * id       db_column: id 
     */ 	
	private Integer id;
    /**
     * 开始金额       db_column: bmoney 
     */ 	
	private Integer bmoney;
    /**
     * 结束金额       db_column: emoney 
     */ 	
	private Integer emoney;
    /**
     * 名称       db_column: name 
     */ 	
	private String name;
    /**
     * 状态(1正常0下线)       db_column: status 
     */ 	
	private Integer status;
    /**
     * 更新说明       db_column: cdate 
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
	public void setBmoney(Integer value) {
		this.bmoney = value;
	}
	
	public Integer getBmoney() {
		return this.bmoney;
	}
	public void setEmoney(Integer value) {
		this.emoney = value;
	}
	
	public Integer getEmoney() {
		return this.emoney;
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
 
