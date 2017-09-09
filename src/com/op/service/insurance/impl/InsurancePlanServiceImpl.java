package com.op.service.insurance.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.dto.insurance.InsurancePlanDTO;
import com.op.service.insurance.InsurancePlanService;

/** 
 * 保险计划详情(InsurancePlan)接口实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-03-16 11:34:14 
 */  
@Service("insurancePlanServiceImpl")
public class InsurancePlanServiceImpl implements InsurancePlanService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	/**
	 * 根据保险产品ID获取保险计划列表
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<InsurancePlanDTO> getInsurancePlanList(String productId)throws Exception{
		return (List<InsurancePlanDTO>)dao.findForList("InsurancePlanMapper.listByProductId",productId);
	}
	
}
