package com.op.plugin.insurance.demo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzins.channel.api.model.common.CommonResult;
import com.hzins.channel.api.model.info.ProductInfo;
import com.hzins.channel.api.model.req.ProductDetailsReq;
import com.hzins.channel.api.model.req.ProductListReq;
import com.hzins.channel.api.model.resp.ProductDetailsResp;
import com.hzins.channel.api.model.resp.ProductListResp;
import com.hzins.openapi.client.Configure;
import com.hzins.openapi.client.OpenApiRemoteOperation;
import com.hzins.openapi.client.common.ProxyFactory;
import com.op.plugin.insurance.BaoXianProperties;
import com.op.util.DateUtil;
import com.op.util.Tools;

/**
 * 保险数量
 * @author sen
 *
 */
public class CPSL {
	static OpenApiRemoteOperation operation = ProxyFactory.create(OpenApiRemoteOperation.class);
	ObjectMapper objectMapper = new ObjectMapper();

	public static void main(String[] args) {
		// 流水号
		String transNo = "WM" + DateUtil.getTimes() + Tools.getRandomNum();
		ProductListReq plreq = new ProductListReq();
		// transNo String 必填 交易流水号，每次请求不能相同
		plreq.setTransNo(transNo);
		// partnerId int 必填 渠道商身份标识，由慧择指定
		plreq.setPartnerId(BaoXianProperties.partnerId);

		Configure.Channel.channelKey = BaoXianProperties.channelKey;
		Configure.Channel.partnerId = BaoXianProperties.partnerId;
		CommonResult<ProductListResp> plres = operation.productList(plreq);
		ProductListResp resp = plres.getData();

		List<ProductInfo> pList = resp.getProductInfos();
		List<String> list = new ArrayList<String>();
		for (ProductInfo p : pList) {
			list.add(p.getCaseCode());
		}
		System.out.println("保险数量："+list.size());
		
		List<String> product = new ArrayList<String>();
		for(int i=0,len=list.size();i<len;i++){
			// 流水号
			ProductDetailsReq pdreq = new ProductDetailsReq();
			//transNo	String	必填	交易流水号，每次请求不能相同
			pdreq.setTransNo(transNo);
			//partnerId	int	必填	渠道商身份标识，由慧择指定
			pdreq.setPartnerId(BaoXianProperties.partnerId);
			//caseCode String  必填   方案代码，每一款保险公司产品的方案代码都不相同，由慧择分配
			pdreq.setCaseCode(list.get(i));
			//platformType  必填   平台标识 0:PC 1:H5
			pdreq.setPlatformType(0);
			
			Configure.Channel.channelKey = BaoXianProperties.channelKey;
			Configure.Channel.partnerId = BaoXianProperties.partnerId;
			CommonResult<ProductDetailsResp> pdres = operation.productDetails(pdreq);
			if(pdres.getData()!=null){
				ProductDetailsResp pdresp = pdres.getData();
				product.add(pdresp.getCaseCode());
			}
		}
		
		System.out.println("可查询到的保险数："+product.size()+"\n保险方案代码：");
		for(int i=0,len=product.size();i<len;i++){
			System.out.println("\t"+product.get(i));
		}
	}

}
