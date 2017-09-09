package com.op.dto.usercenter.user.update;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目名：outdoorPortal
 * 类描述：更换手机号码DTO
 * 创建人：Yan
 * 创建时间： 2016-1-12 上午11:21:18
 * 最后修改时间：2016-1-12上午11:21:18
 */
public class YzmDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 旧手机号码
	private String oldPhone;
	// 新手机号码
	private String newPhone;
	// 旧验证码
	private String oldYzm;
	// 新验证码
	private String newYzm;
	// 旧发送时间
	private Date oldPostDate;
	// 新发送时间
	private Date newPostDate;
	// 当前用户ID
	private String uId;
	//新号码发送次数
	private int count;
	
	public boolean check(){
		if(oldPhone == null || newPhone == null){
			return true;
		}
		return false;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getOldPhone() {
		return oldPhone;
	}

	public void setOldPhone(String oldPhone) {
		this.oldPhone = oldPhone;
	}

	public String getNewPhone() {
		return newPhone;
	}

	public void setNewPhone(String newPhone) {
		this.newPhone = newPhone;
	}


	public String getOldYzm() {
		return oldYzm;
	}

	public void setOldYzm(String oldYzm) {
		this.oldYzm = oldYzm;
	}

	public String getNewYzm() {
		return newYzm;
	}

	public void setNewYzm(String newYzm) {
		this.newYzm = newYzm;
	}

	public Date getOldPostDate() {
		return oldPostDate;
	}

	public void setOldPostDate(Date oldPostDate) {
		this.oldPostDate = oldPostDate;
	}

	public Date getNewPostDate() {
		return newPostDate;
	}

	public void setNewPostDate(Date newPostDate) {
		this.newPostDate = newPostDate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
