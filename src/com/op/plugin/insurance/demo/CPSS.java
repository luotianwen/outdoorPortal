package com.op.plugin.insurance.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzins.channel.api.model.common.CommonResult;
import com.hzins.channel.api.model.req.OrderTrialReq;
import com.hzins.channel.api.model.resp.OrderTrialResp;
import com.hzins.openapi.client.Configure;
import com.hzins.openapi.client.OpenApiRemoteOperation;
import com.hzins.openapi.client.common.ProxyFactory;
import com.op.plugin.insurance.BaoXianProperties;
import com.op.util.DateUtil;
import com.op.util.Tools;

/**
 * 保险试算
 * @author sen
 *
 */
public class CPSS {
	static OpenApiRemoteOperation operation = ProxyFactory.create(OpenApiRemoteOperation.class);
	ObjectMapper objectMapper = new ObjectMapper();
	
	public static void main(String[] args) {
		// 流水号
		String transNo = "WM" + DateUtil.getTimes() + Tools.getRandomNum();

		//试算
		OrderTrialReq req = new OrderTrialReq();
		
		//transNo	String	必填	交易流水号，每次请求不能相同
		req.setTransNo(transNo);
		//partnerId	int	必填	渠道商身份标识，由慧择指定
		req.setPartnerId(BaoXianProperties.partnerId);
		//caseCode	String	必填	方案代码，每一款保险公司产品的方案代码都不相同，由慧择分配
		req.setCaseCode("0000069121501337");
		//birthday	String	必填	被保人生日 格式：yyyy-MM-dd
		req.setBirthday("1970-01-01");
		//startDate	String	必填	起保日期 格式：yyyy-MM-dd
		req.setStartDate("2017-12-16");
		//endDate	String	必填	终保日期 格式：yyyy-MM-dd
		req.setEndDate("2018-01-20");

		Configure.Channel.channelKey = BaoXianProperties.channelKey;
		Configure.Channel.partnerId = BaoXianProperties.partnerId;
		CommonResult<OrderTrialResp> res = operation.orderTrial(req);
		OrderTrialResp resp = res.getData();
		
		//caseCode	String	方案代码，每一款保险公司产品的方案代码都不相同，由慧择分配
		System.out.println(resp.getCaseCode());
		//startDate	String	起保日期 格式：yyyy-MM-dd
		System.out.println(resp.getStartDate());
		//endDate	String	终保日期 格式：yyyy-MM-dd
		System.out.println(resp.getEndDate());
		//singlePrice	double	产品单价（单位：元）
		System.out.println(resp.getSinglePrice());
		//buyQuota	int	被保人在同一时间段内可购买份数
		System.out.println(resp.getBuyQuota());

		
	}
}
