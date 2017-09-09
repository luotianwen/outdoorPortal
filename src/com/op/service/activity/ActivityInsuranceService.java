package com.op.service.activity;

import java.util.Map;

import org.springframework.stereotype.Service;


@Service("activityInsuranceService")
public interface ActivityInsuranceService {
	
	/**
	 * 方法描述：添加活动保险
	 * 返回类型：void
	 * @param map
	 * @throws Exception
	 */
	void insertActiveInsurance(com.op.dto.activity.baoxian.BaoxianDTO dto,Map<String,String> map) throws Exception;
	
	/**
	 * 方法描述：根据活动ID和创建用户和保险ID删除活动保险
	 * 返回类型：void
	 * @param map
	 * @throws Exception
	 */
	void deleteByActiveIdAndUserId(Map<String,String> map) throws Exception;
	
}
