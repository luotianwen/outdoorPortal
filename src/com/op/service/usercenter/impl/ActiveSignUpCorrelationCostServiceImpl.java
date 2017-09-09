package com.op.service.usercenter.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.entity.usercenter.ActiveSignUpCorrelationCost;
import com.op.service.usercenter.ActiveSignUpCorrelationCostService;


@Service("activeSignUpCorrelationCostServiceImpl")
public class ActiveSignUpCorrelationCostServiceImpl implements ActiveSignUpCorrelationCostService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	/**
	 * 新增费用明细
	 * @throws Exception
	 */
	@Override
	public void saveActiveSignUpCorrelationCost(ActiveSignUpCorrelationCost activeSignUpCorrelationCost) throws Exception {
		dao.save("activeSignUpCorrelationCostMapper.saveActiveSignUpCorrelationCost", activeSignUpCorrelationCost);
	}

	/**
	 * 根据订单ID查询所有费用
	 */
	@Override
	public List<ActiveSignUpCorrelationCost> findAllByActiveSignUpId(Map<String,String> map) throws Exception{
		return (List<ActiveSignUpCorrelationCost>)dao.findForList("activeSignUpCorrelationCostMapper.findAllByActiveSignUpId",map);
	}
	

	
}
