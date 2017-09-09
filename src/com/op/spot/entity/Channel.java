package com.op.spot.entity;
import java.io.Serializable;

/**
 * 渠道
 */
public class Channel  implements Serializable {
	 
	
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
     * 支付路径       db_column: payurl 
     */ 	
	private String payurl;
    /**
     * 对接人       db_column: pickup 
     */ 	
	private String pickup;
    /**
     * 联系人       db_column: contacts 
     */ 	
	private String contacts;
    /**
     * 联系电话       db_column: phone 
     */ 	
	private String phone;
    /**
     * 创建时间       db_column: cdate 
     */ 	
	private String cdate;
    /**
     * 状态(0关闭1正常2冻结)       db_column: status 
     */ 	
	private Integer status;
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
	public void setPayurl(String value) {
		this.payurl = value;
	}
	
	public String getPayurl() {
		return this.payurl;
	}
	public void setPickup(String value) {
		this.pickup = value;
	}
	
	public String getPickup() {
		return this.pickup;
	}
	public void setContacts(String value) {
		this.contacts = value;
	}
	
	public String getContacts() {
		return this.contacts;
	}
	public void setPhone(String value) {
		this.phone = value;
	}
	
	public String getPhone() {
		return this.phone;
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

	
	 
}
 
