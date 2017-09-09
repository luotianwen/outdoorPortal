package com.op.entity.behaviour;

import java.io.Serializable;

/**
 * 
 * ClassName: BrowsingRecord 
 * @Description: 用户浏览记录
 * @author WinZhong
 * @date 2016年1月4日 下午4:48:32
 */
public class BrowsingRecord implements Serializable {
	
	/**
	 * @Fields serialVersionUID :
	 */
	private static final long serialVersionUID = -4416341464798718149L;

	private String br_id;
	// 用户id
	private String userId;
	// 浏览的活动或者景点或者线路id
	private int recordId;
	// 浏览类型 (1:活动;2:景点;3:线路;)
	private int recordType;
	// 浏览时间
	private String careteTime;
	
	public BrowsingRecord(){
		
	}
	public BrowsingRecord(String userId,int recordId,int recordType){
		this.userId = userId;
		this.recordId = recordId;
		this.recordType = recordType;
		//this.careteTime = new Date();
		//this.br_id = UuidUtil.get32UUID();
	}
	public String getBr_id() {
		return br_id;
	}
	public void setBr_id(String br_id) {
		this.br_id = br_id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	public int getRecordType() {
		return recordType;
	}
	public void setRecordType(int recordType) {
		this.recordType = recordType;
	}
	public String getCareteTime() {
		return careteTime;
	}
	public void setCareteTime(String careteTime) {
		this.careteTime = careteTime;
	}

	
}
