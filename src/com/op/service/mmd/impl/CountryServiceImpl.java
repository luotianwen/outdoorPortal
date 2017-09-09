package com.op.service.mmd.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao; 
import com.op.entity.mmd.Country;
import com.op.service.mmd.CountryService;

/** 
 * 国家(country)接口实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-04-24 14:02:54 
 */  
@Service("countryServiceImpl")
public class CountryServiceImpl implements CountryService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	/**
	 * 添加国家
	 * @param country
	 * @throws Exception
	 */
	@Override
	public void insertCountry(Country country)throws Exception{
		dao.save("countryMapper.insertCountry", country);
	}
	


	/**
	 * 根据国家名字获取国家id
	 * @param zh_name
	 * @return
	 * @throws Exception 
	 */
	@Override
	public String getCountryId(String zh_name) throws Exception{
		System.out.println("匹配："+zh_name);
		return (String)dao.findForObject("countryMapper.getCountryId", zh_name);
	}
}
