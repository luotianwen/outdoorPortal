package com.op.service.usercenter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.op.entity.usercenter.ActiveSignUpCorrelationCost;

@Service("activeSignUpCorrelationCostService")
public interface ActiveSignUpCorrelationCostService {

	/**
	 * 新增费用明细
	 * @throws Exception
	 */
	void saveActiveSignUpCorrelationCost(ActiveSignUpCorrelationCost activeSignUpCorrelationCost) throws Exception;
	
	/**
	 * 根据订单ID查询所有费用
	 */
	List<ActiveSignUpCorrelationCost> findAllByActiveSignUpId(Map<String,String> map) throws Exception;
}
