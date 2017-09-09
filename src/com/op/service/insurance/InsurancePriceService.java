package com.op.service.insurance;

import java.util.List;
import java.util.Map;

import com.op.dto.insurance.InsuranceInquiryParameterDto;
import com.op.dto.insurance.InsurancePriceDTO;
/** 
 * 保险价格(InsurancePrice)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-03-16 11:36:25 
 */  
public interface InsurancePriceService {
	
	/**
	 * 根据保险计划ID获取保险保障期限价格
	 * @param planId
	 * @return
	 */
	List<InsurancePriceDTO> findByPlanId(String planId)throws Exception;

	/**
	 * 根据保险、计划、期限 id获取保险详情
	 * @param insuranceInquiryParameter
	 * @return
	 */
	 Map<String,Object> details(InsuranceInquiryParameterDto insuranceInquiryParameter)throws Exception;

    

	
}
