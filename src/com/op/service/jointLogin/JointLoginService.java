package com.op.service.jointLogin;

import com.op.entity.jointLogin.JointLogin;
import com.op.entity.users.Users;

/** 
 * 第三方平台账号联合登录(jointLogin)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-05-18 11:44:38 
 */  
public interface JointLoginService {

	/**
	 * 检测第三方登录账号是否已绑定网站账户
	 * @param jointLogin
	 * @return 绑定的用户信息
	 * @throws Exception
	 */
	Users findUserByOpenId(JointLogin jointLogin)throws Exception;
    
	/**
	 * 自动为第三方登录用户开通网站账户
	 * @param jointLogin
	 * @throws Exception
	 */
	Users insertJointLoginInfo(JointLogin jointLogin)throws Exception;
    
	/**
	 * 更新第三方登录用户信息
	 * @param jointLogin
	 * @throws Exception
	 */
	void updateUserJointLoginInfo(JointLogin jointLogin)throws Exception;
	
}
