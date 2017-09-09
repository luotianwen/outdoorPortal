package com.op.service.user_check;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.op.entity.user_check.UserCheck;

/**
 * 数据库中(领队信息)接口
 * @author PYW
 * Date: 2015年12月18日 09:25:19
 */
public interface UserCheckService {
	/**
	 * 新增领队
	 * @return
	 * @throws Exception
	 */
	void insertUserCheck(UserCheck userCheck,HttpSession session,Map<String, Object> map) throws Exception;
	
	/**
	 * 根据用户id查询是否已经申请过领队(通过)或正在审核中
	 * @return
	 * @throws Exception
	 */
	Integer selectUser_Check(String u_id) throws Exception;
	
	/**
	 * 修改注册领队信息
	 */
	void updateUserCheck(UserCheck userCheck,HttpSession session,Map<String, Object> map) throws Exception;
}
