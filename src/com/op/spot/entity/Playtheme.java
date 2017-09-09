package com.op.spot.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 游玩主题
 */
public class Playtheme  implements Serializable {
	 
	
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
	private Date cdate;
    /**
     * 说明       db_column: notes 
     */ 	
	private String notes;
    /**
     * 父类ID       db_column: pid 
     */ 	
	private Integer pid;
    /**
     * 级次       db_column: level 
     */ 	
	private Integer level;
	//columns END

	/**
	 * 二级分类
	 * @param value
	 */
	List<Playtheme> playthemes;
	
 
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
	public void setCdate(Date value) {
		this.cdate = value;
	}
	
	public Date getCdate() {
		return this.cdate;
	}
	public void setNotes(String value) {
		this.notes = value;
	}
	
	public String getNotes() {
		return this.notes;
	}
	public void setPid(Integer value) {
		this.pid = value;
	}
	
	public Integer getPid() {
		return this.pid;
	}
	public void setLevel(Integer value) {
		this.level = value;
	}
	
	public Integer getLevel() {
		return this.level;
	}

	public List<Playtheme> getPlaythemes() {
		return playthemes;
	}

	public void setPlaythemes(List<Playtheme> playthemes) {
		this.playthemes = playthemes;
	}

	
	 
}
 
