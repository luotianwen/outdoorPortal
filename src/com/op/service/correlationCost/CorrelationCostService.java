package com.op.service.correlationCost;

import java.util.List;

import org.springframework.stereotype.Service;


@Service("correlationCostService")
public interface CorrelationCostService {
	
	/**
	 * 方法描述：查询所有的费用
	 * 返回类型：List<com.op.entity.correlationCost.CorrelationCost>
	 * @return
	 * @throws Exception
	 */
	List<com.op.entity.correlationCost.CorrelationCost> findAllCost()throws Exception;
}
