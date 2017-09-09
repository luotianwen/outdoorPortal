package com.op.plugin.insurance.demo;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzins.channel.api.model.common.CommonResult;
import com.hzins.channel.api.model.req.ProductDetailsReq;
import com.hzins.channel.api.model.resp.ProductDetailsResp;
import com.hzins.openapi.client.Configure;
import com.hzins.openapi.client.OpenApiRemoteOperation;
import com.hzins.openapi.client.common.ProxyFactory;
import com.op.plugin.insurance.BaoXianProperties;
import com.op.util.DateUtil;
import com.op.util.Tools;
/**
 * 
 * 调用示例
 *
 * </p>
 * 
 * @author 李淼淼
 * @date 2016年1月6日 下午3:36:08
 * @version
 */
public class CPXQ {

    static OpenApiRemoteOperation operation = ProxyFactory.create(OpenApiRemoteOperation.class);
    
    ObjectMapper objectMapper=new ObjectMapper();
    
     
    
    public static void main(String[] args) throws JsonProcessingException {
		
		// 流水号
		String transNo = "WM" + DateUtil.getTimes() + Tools.getRandomNum();
		ProductDetailsReq req = new ProductDetailsReq();
		//transNo	String	必填	交易流水号，每次请求不能相同
		req.setTransNo(transNo);
		//partnerId	int	必填	渠道商身份标识，由慧择指定
		req.setPartnerId(BaoXianProperties.partnerId);
		//caseCode String  必填   方案代码，每一款保险公司产品的方案代码都不相同，由慧择分配
		req.setCaseCode("0000069121501337");
		//platformType  必填   平台标识 0:PC 1:H5
		req.setPlatformType(0);
		//产品ID
		//req.setProductId(480);
		//计划ID
		//req.setPlanId(480);
		
		Configure.Channel.channelKey = BaoXianProperties.channelKey;
		Configure.Channel.partnerId = BaoXianProperties.partnerId;
		//sign	String	必填	签名，预签名数据：密钥+渠道商身份标识+交易流水号+方案代码
		System.out.println("流水号："+transNo);
		CommonResult<ProductDetailsResp> res = operation.productDetails(req);
		ProductDetailsResp resp = res.getData();
		System.out.println("交易流水号:"+resp.getTransNo());
		System.out.println("渠道商身份标识:"+resp.getPartnerId());
		//System.out.println("产品ID:"+resp.getProductId());
		//System.out.println("计划ID:"+resp.getPlanId());
		//System.out.println("产品名称:"+resp.getProductName());
		//System.out.println("计划名称:"+resp.getPlanName());
		System.out.println("产品价格:"+resp.getPrice());
		//List<PlanDetail> pList = resp.getPlanDetails(); 
//		for(PlanDetail p :pList){
//			System.out.println("保障名称:"+p.getName());
//			System.out.println("保障金额说明:"+p.getText());              
//		}
		System.out.println("投保须知:"+resp.getTips());
    }

}
