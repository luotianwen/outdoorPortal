package com.op.plugin.insurance.demo;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzins.channel.api.model.common.CommonResult;
import com.hzins.channel.api.model.info.ProductInfo;
import com.hzins.channel.api.model.req.ProductListReq;
import com.hzins.channel.api.model.resp.ProductListResp;
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
public class CPLB {

    static OpenApiRemoteOperation operation = ProxyFactory.create(OpenApiRemoteOperation.class);
    
    ObjectMapper objectMapper=new ObjectMapper();
    
     
    
    public static void main(String[] args) throws JsonProcessingException {
		
		// 流水号
		String transNo = "WM" + DateUtil.getTimes() + Tools.getRandomNum();
		ProductListReq req = new ProductListReq();
		//transNo	String	必填	交易流水号，每次请求不能相同
		req.setTransNo(transNo);
		//partnerId	int	必填	渠道商身份标识，由慧择指定
		req.setPartnerId(BaoXianProperties.partnerId);
		
		Configure.Channel.channelKey = BaoXianProperties.channelKey;
		Configure.Channel.partnerId = BaoXianProperties.partnerId;
		//sign	String	必填	签名，预签名数据：密钥+渠道商身份标识+交易流水号+方案代码
		System.out.println("流水号："+transNo);
		CommonResult<ProductListResp> res = operation.productList(req);
		ProductListResp resp = res.getData();
		
		System.out.println("交易流水号:"+resp.getTransNo());
		System.out.println("渠道商身份标识:"+resp.getPartnerId());
		List<ProductInfo> pList = resp.getProductInfos(); 
		for(ProductInfo p :pList){
			System.out.println("=============="+p.getName()+"================");
			System.out.println("产品id:"+p.getProdId());
			System.out.println("产品名称:"+p.getName());
			System.out.println("方案代码:"+p.getCaseCode());
			System.out.println("公司名称:"+p.getCompanyName());
			System.out.println("计划id:"+p.getPlanId());
			System.out.println("计划名称:"+p.getPlanName());
			//产品类型 0：境内旅意险，1：寿险健康险，2：境外旅意险，3：家财险
			System.out.println("产品类型:"+p.getType());
			//是否下架（0未下架，1下架）
			System.out.println("是否下架:"+p.isInvalid());
			System.out.println("==============================\n");
		}
    }

}
