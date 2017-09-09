package com.op.spot.entity;
import java.io.Serializable;
import java.util.List;

/**
 * 点评
 */
public class Spotcomment  implements Serializable {
	 
	
	//columns START
    /**
     * id       db_column: id 
     */ 	
	private Integer id;
    /**
     * 用户id       db_column: userid 
     */ 	
	private Integer userid;
    /**
     * 用户昵称       db_column: username 
     */ 	
	private String username;
    /**
     * 用户头像       db_column: userpho 
     */ 	
	private String userpho;
    /**
     * 点评时间       db_column: sdate 
     */ 	
	private String sdate;
    /**
     * 内容       db_column: content 
     */ 	
	private String content;
    /**
     * 状态(0不通过1默认状态2通过3删除)       db_column: status 
     */ 	
	private Integer status;
    /**
     * 景点id       db_column: sid 
     */ 	
	private Integer sid;
    /**
     * 总体评价       db_column: num 
     */ 	
	private Integer num;
	//columns END

	/**
	 * 景点名称
	 * @param value
	 */
	private String sname;
	/**
	 * 点评照片
	 * @param value
	 */
	private List<CommentPho> commentPho;
	/**
	 * 点评小类
	 * @param value
	 */
	private List<CommentProject> commentProject;
	
 
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setUserid(Integer value) {
		this.userid = value;
	}
	
	public Integer getUserid() {
		return this.userid;
	}
	public void setUsername(String value) {
		this.username = value;
	}
	
	public String getUsername() {
		return this.username;
	}
	public void setUserpho(String value) {
		this.userpho = value;
	}
	
	public String getUserpho() {
		return this.userpho;
	}
	public void setSdate(String value) {
		this.sdate = value;
	}
	
	public String getSdate() {
		return this.sdate;
	}
	public void setContent(String value) {
		this.content = value;
	}
	
	public String getContent() {
		return this.content;
	}
	public void setStatus(Integer value) {
		this.status = value;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	public void setSid(Integer value) {
		this.sid = value;
	}
	
	public Integer getSid() {
		return this.sid;
	}
	public void setNum(Integer value) {
		this.num = value;
	}
	
	public Integer getNum() {
		return this.num;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public List<CommentPho> getCommentPho() {
		return commentPho;
	}

	public void setCommentPho(List<CommentPho> commentPho) {
		this.commentPho = commentPho;
	}

	public List<CommentProject> getCommentProject() {
		return commentProject;
	}

	public void setCommentProject(List<CommentProject> commentProject) {
		this.commentProject = commentProject;
	}

	

	
	 
}
 
