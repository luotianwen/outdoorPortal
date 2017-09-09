package com.op.spot.entity;
import java.io.Serializable;

/**
 * 点评小类
 */
public class CommentProject  implements Serializable {
	 
	
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
     * 点评项目id       db_column: pid 
     */ 	
	private Integer pid;
    /**
     * 点评分值       db_column: num 
     */ 	
	private Integer num;
	//columns END

	/**
	 * 小类名称
	 * @param value
	 */
	private String cname;
	
 
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
	public void setPid(Integer value) {
		this.pid = value;
	}
	
	public Integer getPid() {
		return this.pid;
	}
	public void setNum(Integer value) {
		this.num = value;
	}
	
	public Integer getNum() {
		return this.num;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	
	 
}
 
