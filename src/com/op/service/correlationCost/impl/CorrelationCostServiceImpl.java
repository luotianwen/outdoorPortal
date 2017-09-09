package com.op.service.correlationCost.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.entity.correlationCost.CorrelationCost;
import com.op.service.correlationCost.CorrelationCostService;


@Service("correlationCostServiceImpl")
public class CorrelationCostServiceImpl implements CorrelationCostService{
	
	@Resource(name="baseDaoImpl")
	private BaseDao dao;

	/**
	 * 方法描述：查询所有的费用
	 * 实现接口：@see com.op.service.correlationCost.CorrelationCostService#findAllCost()
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<CorrelationCost> findAllCost() throws Exception {
		
		return (List<CorrelationCost>) dao.findForList("correlationCostMapper.findAllCost");
	}

	
}
