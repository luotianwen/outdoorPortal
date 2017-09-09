package com.op.entity.issue;

import java.io.Serializable;
import java.util.Date;
/** 
 * 意见建议(issue)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-01-08 13:46:46 
 */  
public class Issue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private String id;
 	//类型（1：BUG问题；2：体验问题；3：其他）
  	 private int issueType;
 	//反馈内容
  	 private String content;
 	//联系方式
  	 private String contact;
 	//提交者（空为游客提交）
  	 private String userId;
 	//提交时间
  	 private Date submitTime;
 	//处理意见
  	 private String postil;
 	//处理人
  	 private String operator;



	 
    /**
    *id
    */ 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    /**
    *类型（1：BUG问题；2：体验问题；3：其他）
    */ 
	public int getIssueType() {
		return issueType;
	}
	public void setIssueType(int issueType) {
		this.issueType = issueType;
	}
    /**
    *反馈内容
    */ 
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
    /**
    *联系方式
    */ 
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
    /**
    *提交者（空为游客提交）
    */ 
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
    /**
    *提交时间
    */ 
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
    /**
    *处理意见
    */ 
	public String getPostil() {
		return postil;
	}
	public void setPostil(String postil) {
		this.postil = postil;
	}
    /**
    *处理人
    */ 
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
}
