package com.op.dto.pointService.auth;

import java.io.Serializable;

/**
 * 地点商户认领认证信息(pointAuth)实体类
 * 
 * @author 世峰户外商城
 * @version Revision: 1.00 Date: 2016-06-27 10:06:24
 */
public class FindPointAuthDTO implements Serializable {
	// 用户id
	String user_id;
	// 审核结果（1：提交待审核；2：审核中；3：审核成功；4：审核失败；）
	String auditor_state;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getAuditor_state() {
		return auditor_state;
	}

	public void setAuditor_state(String auditor_state) {
		this.auditor_state = auditor_state;
	}

}
