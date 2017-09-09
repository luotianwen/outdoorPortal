package com.op.dto.signUp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.op.entity.usercenter.ActiveSignup;

/**
 * 项目名：outdoorPortal
 * 类描述：保存报名人列表
 * 创建人：Yan
 * 创建时间： 2015-12-28 上午10:18:02
 * 最后修改时间：2015-12-28上午10:18:02
 */
public class InsertSignUpDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ActiveSignup> signUp = new ArrayList<ActiveSignup>();

	public List<ActiveSignup> getSignUp() {
		return signUp;
	}

	public void setSignUp(List<ActiveSignup> signUp) {
		this.signUp = signUp;
	}
	
	

}
