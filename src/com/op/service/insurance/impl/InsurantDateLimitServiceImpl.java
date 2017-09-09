package com.op.service.insurance.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao; 
import com.op.dto.insurance.InsurantDateLimitDTO;
import com.op.service.insurance.InsurantDateLimitService;

/** 
 * 保险保障期限(InsurantDateLimit)接口实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-03-16 11:19:59 
 */  
@Service("insurantDateLimitServiceImpl")
public class InsurantDateLimitServiceImpl implements InsurantDateLimitService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	/**
	 * 根据保险id获取保险期限
	 * @param planId
	 * @return
	 */
	@Override
	public List<InsurantDateLimitDTO> findByProductId(String productId)throws Exception{
		return (List<InsurantDateLimitDTO>) dao.findForList("InsurantDateLimitMapper.findByProductId", productId);
	}
	
}
