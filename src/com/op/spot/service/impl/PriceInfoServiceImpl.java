package com.op.spot.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao; 
import com.op.spot.entity.PriceInfo;
import com.op.spot.service.PriceInfoService;

/** 
 * 景点门票价格信息(s_priceInfo)接口实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-09-21 13:40:39 
 */  
@Service("priceInfoServiceImpl")
public class PriceInfoServiceImpl implements PriceInfoService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	
	
	/**
	 * 根据门票产品编号 游玩日期查询当天价格
	 * @param productSn
	 * @param inDate
	 * @return
	 * @throws Exception
	 */
	public PriceInfo getPriceInfo(String productSn, String inDate)throws Exception {
		return (PriceInfo)dao.findForObject("s_priceInfoMapper.getPriceInfo", new String[]{productSn,inDate});
	}
	
	
}
