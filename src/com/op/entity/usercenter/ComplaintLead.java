
package com.op.entity.usercenter;

import java.io.Serializable;
import java.util.Date;
/** 
 * 投诉领队(complaintLead)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-01-12 14:51:33 
 */  
public class ComplaintLead implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private String cl_id;
 	//投诉者id
  	 private String plaintiff;
 	//订单id
  	 private String orderId;
 	//订单类型（1：活动订单）
  	 private int orderType;
 	//手机号码
  	 private String phone;
 	//投诉内容
  	 private String conent;
 	//投诉状态（1：待受理；2：处理中；3：处理完成）
  	 private int state;
 	//处理结果
  	 private String handleResults;
 	//处理时间
  	 private Date handlingTime;
 	//处理人
  	 private String operator;



	 
    /**
    *id
    */ 
	public String getCl_id() {
		return cl_id;
	}
	public void setCl_id(String cl_id) {
		this.cl_id = cl_id;
	}
    /**
    *投诉者id
    */ 
	public String getPlaintiff() {
		return plaintiff;
	}
	public void setPlaintiff(String plaintiff) {
		this.plaintiff = plaintiff;
	}
    /**
    *订单id
    */ 
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
    /**
    *订单类型（1：活动订单）
    */ 
	public int getOrderType() {
		return orderType;
	}
	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}
    /**
    *手机号码
    */ 
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
    /**
    *投诉内容
    */ 
	public String getConent() {
		return conent;
	}
	public void setConent(String conent) {
		this.conent = conent;
	}
    /**
    *投诉状态（1：待受理；2：处理中；3：处理完成）
    */ 
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
    /**
    *处理结果
    */ 
	public String getHandleResults() {
		return handleResults;
	}
	public void setHandleResults(String handleResults) {
		this.handleResults = handleResults;
	}
    /**
    *处理时间
    */ 
	public Date getHandlingTime() {
		return handlingTime;
	}
	public void setHandlingTime(Date handlingTime) {
		this.handlingTime = handlingTime;
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
