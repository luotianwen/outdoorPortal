package com.op.service.insurance.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.hzins.channel.api.model.common.CommonResult;
import com.hzins.channel.api.model.req.OrderTrialReq;
import com.hzins.channel.api.model.resp.OrderTrialResp;
import com.hzins.openapi.client.Configure;
import com.hzins.openapi.client.OpenApiRemoteOperation;
import com.hzins.openapi.client.common.ProxyFactory;
import com.op.dao.BaseDao; 
import com.op.dto.insurance.InsuranceInquiryParameterDto;
import com.op.dto.insurance.InsurancePriceDTO;
import com.op.entity.insurance.InsurantDateLimit;
import com.op.plugin.insurance.BaoXianProperties;
import com.op.service.insurance.InsurancePriceService;
import com.op.util.Const;
import com.op.util.DateUtil;
import com.op.util.Tools;

/** 
 * 保险价格(InsurancePrice)接口实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-03-16 11:36:25 
 */  
@Service("insurancePriceServiceImpl")
public class InsurancePriceServiceImpl implements InsurancePriceService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	/**
	 * 根据保险计划ID获取保险保障期限价格
	 * @param planId
	 * @return
	 */
	@Override
	public List<InsurancePriceDTO> findByPlanId(String planId)throws Exception{
		return (List<InsurancePriceDTO>)dao.findForList("InsurancePriceMapper.findByPlanId",planId);
	}
	
	
	
	/**
	 * 根据保险、计划、期限 id获取保险详情
	 * @param insuranceInquiryParameter
	 * @return
	 */
	@Override
	public Map<String,Object> details(InsuranceInquiryParameterDto insuranceInquiryParameter)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		List<InsurancePriceDTO> list = (List<InsurancePriceDTO>)dao.findForList("InsurancePriceMapper.details",insuranceInquiryParameter);
		
		OpenApiRemoteOperation operation = ProxyFactory.create(OpenApiRemoteOperation.class);
		
		Map<String,Date> activityDate = (Map<String, Date>) dao.findForObject("activityMapper.findActivityTime", insuranceInquiryParameter.getActivityId());
		
		String startDate = DateUtil.YYYY_MM_DDgetDay(activityDate.get("activityTime"));
		String endDate = DateUtil.YYYY_MM_DDgetDay(activityDate.get("endTime"));
		int day = (int)DateUtil.getDaySub(startDate, endDate)+1;
		
		if(DateUtil.getDaySub(DateUtil.YYYY_MM_DDgetAfterYear(1),startDate)<=0){
			InsurantDateLimit insurantdateLimit = (InsurantDateLimit) dao.findForObject("InsurantDateLimitMapper.findMaxDeadline", insuranceInquiryParameter.getProductId());
			
			int dateLimit = 0;
			if(insurantdateLimit.getMaxDeadline()>0){
				dateLimit = insurantdateLimit.getMaxDeadline();
			}else{
				dateLimit = insurantdateLimit.getMinDeadline();
			}
			
			if(day>dateLimit){
				map.put(Const.RESPONSE_STATE, "500");
				map.put(Const.ERROR_INFO, "活动时长超过该保险产品的最大保障期限！");
			}else{
				for(int i=0,len=list.size();i<len;i++){
					InsurancePriceDTO dto = list.get(i);
					dto.setUnit(1);
					dto.setStartDate(startDate);
					dto.setEndDate(endDate);
					dto.setMinDeadline(day);
					dto.setPrice(orderTrial(operation,dto));
					list.set(i, dto);
				}
				map.put("list", list);
				map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
			}
		}else{
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "起保时间(活动开始时间)应在"+DateUtil.YYYY_MM_DDgetAfterYear(1)+" 23:59:59之前！");
		}
		
		return map;
	}
	
	public double orderTrial(OpenApiRemoteOperation operation,InsurancePriceDTO dto) throws Exception{
		// 流水号
		String transNo = "WM" + DateUtil.getTimes() + Tools.getRandomNum();
		//试算
		OrderTrialReq req = new OrderTrialReq();
		//transNo	String	必填	交易流水号，每次请求不能相同
		req.setTransNo(transNo);
		//partnerId	int	必填	渠道商身份标识，由慧择指定
		req.setPartnerId(BaoXianProperties.partnerId);
		//caseCode	String	必填	方案代码，每一款保险公司产品的方案代码都不相同，由慧择分配
		req.setCaseCode(dto.getCaseCode());
		//birthday	String	必填	被保人生日 格式：yyyy-MM-dd
		req.setBirthday("1960-01-01");
		//startDate	String	必填	起保日期 格式：yyyy-MM-dd
		req.setStartDate(dto.getStartDate());
		//endDate	String	必填	终保日期 格式：yyyy-MM-dd
		req.setEndDate(dto.getEndDate());

		Configure.Channel.channelKey = BaoXianProperties.channelKey;
		Configure.Channel.partnerId = BaoXianProperties.partnerId;
		CommonResult<OrderTrialResp> res = operation.orderTrial(req);
		OrderTrialResp resp = res.getData();
		if(resp!=null){
			return resp.getSinglePrice();
		}else{
			return 0;
		}
		
	}
	
	
	
}
