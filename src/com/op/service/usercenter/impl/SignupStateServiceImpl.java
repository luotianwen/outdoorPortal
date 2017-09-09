package com.op.service.usercenter.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;



import com.op.dao.BaseDao;
import com.op.entity.usercenter.SignupState;
import com.op.service.usercenter.SignupStateService;

/**
 * 项目名：outdoorPortal
 * 类描述：活动报名状态字典信息实现类
 * 创建人：Yan
 * 创建时间： 2015-12-16 上午11:15:13
 * 最后修改时间：2015-12-16上午11:15:13
 */
@Service("signupStateServiceImpl")
public class SignupStateServiceImpl implements SignupStateService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	/**
	 * 根据状态类型查询对应的状态信息
	 */
	@Override
	public List<SignupState> selectState(int sus_type) throws Exception {
		return (List<SignupState>) dao.findForList("signupStateMapper.selectSignupState", sus_type);
	}
}
