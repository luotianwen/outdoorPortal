package com.op.service.usercenter;

import java.util.List;

import org.springframework.stereotype.Service;

import com.op.entity.usercenter.SignupState;

/**
 * 项目名：outdoorPortal
 * 类描述：活动报名状态字典信息
 * 创建人：Yan
 * 创建时间： 2015-12-16 上午11:15:28
 * 最后修改时间：2015-12-16上午11:15:28
 */
@Service("signupStateService")
public interface SignupStateService {
	/**
	 * 根据状态类型查询对应的状态信息
	 */
	public List<SignupState> selectState(int sus_type) throws Exception;
}
