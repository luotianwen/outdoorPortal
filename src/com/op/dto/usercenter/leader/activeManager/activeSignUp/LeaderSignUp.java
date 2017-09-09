package com.op.dto.usercenter.leader.activeManager.activeSignUp;

import java.util.List;

import com.op.plugin.page.Page;

public class LeaderSignUp {
	//活动ID
	private String activityId;
	
	//订单
	private List<MySignUp> mySignUp;

	private Page<MySignUp> mysignupPage;
	
	private String pageStr;
	
	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public List<MySignUp> getMySignUp() {
		return mySignUp;
	}

	public void setMySignUp(List<MySignUp> mySignUp) {
		this.mySignUp = mySignUp;
	}

	public Page<MySignUp> getMysignupPage() {
		return mysignupPage;
	}

	public void setMysignupPage(Page<MySignUp> mysignupPage) {
		this.mysignupPage = mysignupPage;
	}

	public String getPageStr() {
		return pageStr;
	}

	public void setPageStr(String pageStr) {
		this.pageStr = pageStr;
	}
	
	
}
