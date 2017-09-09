package com.op.service.insurance.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.plugin.insurance.InsurancesUtil;
import com.op.service.insurance.InsuredService;

/** 
 * 保险被保人(Insured)接口实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-03-16 11:19:59 
 */  
@Service("insuredServiceImpl")
public class InsuredServiceImpl implements InsuredService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	@Resource(name = "insurancesUtil")
	private InsurancesUtil insurancesUtil;
	
	protected Logger logger = Logger.getLogger(this.getClass());
	/**
	 * 更新保单信息
	 * @param insureNum  投保单号
	 */
	 @Override
	@Async
    public void updatePolicy(String insureNum){
    	try {
    		if(insurancesUtil.policyDownload(insureNum)){
    			dao.update("policyholdersMapper.updatepolicyDownloadStatus", null);
    			logger.info("保单下载更新成功，投保单号："+insureNum);
    		}else{
    			logger.info("保单下载更新失败，投保单号："+insureNum);
    		}
		} catch (Exception e) {
			logger.error("保单下载更新失败，投保单号："+insureNum+",错误信息", e);
		}
    }

}
