package com.op.service.insurance;

import java.util.List;

import com.op.dto.insurance.InsurancePlanDTO;
/** 
 * 保险计划详情(InsurancePlan)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-03-16 11:34:14 
 */  
public interface InsurancePlanService {

	/**
	 * 根据保险产品ID获取保险计划列表
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	List<InsurancePlanDTO> getInsurancePlanList(String productId)throws Exception;

    

	
}
