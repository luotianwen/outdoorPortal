package com.op.service.usercenter;

import java.util.List;

import org.springframework.stereotype.Service;

import com.op.entity.usercenter.ActiveSignup;
import com.op.entity.usercenter.LinkSignUpUser;
import com.op.entity.users.Users;


@Service("activeApplicantService")
public interface ActiveApplicantService {

	/**
	 * 新增订单联系人
	 * @throws Exception
	 */
	String[] saveActiveApplicant(List<LinkSignUpUser> linkSignUpUser,Users users,ActiveSignup activeSignup) throws Exception;
	
	/**
	 * 到时间未付款取消订单
	 */
	void payCloseForAsu_id(String id) throws Exception;
	
	/**
	 * 订单已退款金额
	 */
	double getRefundPrice(String asu_id) throws Exception;
	
	
}
