package com.op.service.insurance.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.op.dao.BaseDao;
import com.op.dto.insurance.BaoXianren;
import com.op.dto.insurance.InsurancePlanPriceDTO;
import com.op.dto.insurance.InsurancesDTO;
import com.op.plugin.insurance.InsurancesUtil;
import com.op.service.insurance.InsurancesService;
/** 
 * 保险信息(Insurances)接口实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-03-16 11:19:59 
 */  
@Service("insurancesServiceImpl")
public class InsurancesServiceImpl implements InsurancesService{
	
	protected Logger logger = Logger.getLogger(this.getClass());

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	@Resource(name = "insurancesUtil")
	private InsurancesUtil insurancesUtil;
	/**
	 * 获取保险列表
	 * @return
	 * @throws Exception
	 */
    @Override
	public List<InsurancesDTO> getInsurancesList()throws Exception{
    	return (List<InsurancesDTO>)dao.findForList("InsurancesMapper.list");
    }
	
    /**
     * 购买保险
     * @param lpId 保险价格ID
     * @param buy_user_id 购买保险的用户id
     * @param startDate 活动开始时间 yyyy-MM-dd
     * @param endDate   活动结束时间 yyyy-MM-dd
     * @param toubaorenList
     * @return
     * @throws Exception 
     */
    @Override
	@Async
	public void buy(int aId,String lpId,String buy_user_id,String startDate,String endDate,List<BaoXianren> baoXianrenList) throws Exception{
			//获取保险计划方案代码，及价格
    		Map<String,Object> imap = new HashMap<String,Object>();
    		imap.put("aId", aId);
    		imap.put("planId", lpId);
    		
			InsurancePlanPriceDTO planPrice = (InsurancePlanPriceDTO)dao.findForObject("InsurancePlanMapper.findByPiceId", imap);
			System.out.println("方案代码："+planPrice.getCaseCode()+"\n价格："+planPrice.getPrice());
			Map<String,Object> map = insurancesUtil.orderApply(startDate, endDate, planPrice, baoXianrenList);
			map.put("planPrice", planPrice);
			map.put("user_id", buy_user_id);
			dao.save("policyholdersMapper.savePolicyholders", map);
			dao.save("InsuredMapper.saveInsured", map);
	}
    
    /**
	 * 保险退单
	 * @param asu_id 订单ID
	 * @param aa_id 联系人ID
	 * @throws Exception
	 */
    @Override
	@Async
	public void orderCancel(String asu_id,String aa_id) throws Exception{
    	Map<String,String> map = new HashMap<String,String>();
    	map.put("order_id", asu_id);
    	map.put("linkman_id", aa_id);
    	
    	//投保单号
		String insureNum = (String) dao.findForObject("InsuredMapper.getInsureNum", map);
		
		if(!StringUtils.isEmpty(insureNum)){
			InsurancesUtil i = new InsurancesUtil();
			Map<String,Object> m = i.orderCancel(insureNum);
			if("error".equals(m.get("type"))){
				map.put("status", "2");
				map.put("msg", m.get("msg").toString());
				dao.update("policyholdersMapper.updateInsuredState", map);
			}else if("success".equals(m.get("type"))){
				map.put("status", "1");
				map.put("msg", "退保成功");
				dao.update("policyholdersMapper.updateInsuredState", map);
			}
		}
		
	}
    
}
