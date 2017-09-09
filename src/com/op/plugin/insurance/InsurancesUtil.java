package com.op.plugin.insurance;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzins.channel.api.model.common.CommonResult;
import com.hzins.channel.api.model.info.ApplicantInfo;
import com.hzins.channel.api.model.info.ApplicationData;
import com.hzins.channel.api.model.info.InsurantInfo;
import com.hzins.channel.api.model.req.InsureDownloadReq;
import com.hzins.channel.api.model.req.OrderApplyReq;
import com.hzins.channel.api.model.req.OrderCancelReq;
import com.hzins.channel.api.model.resp.InsureDownloadResp;
import com.hzins.channel.api.model.resp.OrderApplyResp;
import com.hzins.channel.api.model.resp.OrderCancelResp;
import com.hzins.openapi.client.Configure;
import com.hzins.openapi.client.OpenApiRemoteOperation;
import com.hzins.openapi.client.common.ProxyFactory;
import com.op.dto.insurance.BaoXianren;
import com.op.dto.insurance.InsurancePlanPriceDTO;
import com.op.entity.insurance.Insured;
import com.op.entity.insurance.Policyholders;
import com.op.util.DateUtil;
import com.op.util.Tools;
import com.op.util.UuidUtil;
import com.op.util.aliyun.AliyunOSSFactory;
import com.op.util.aliyun.AliyunOSSProperties;

@Service("insurancesUtil")
public class InsurancesUtil {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 申请保险订单
	 * @param startDate  起保时间
	 * @param endDate	    终保时间 
	 * @param planPrice  保险方案代码及价格
	 * @param baoXianrenList 保人信息集合
	 * @return
	 */
	public Map<String,Object> orderApply(String startDate,String endDate,InsurancePlanPriceDTO planPrice,List<BaoXianren> baoXianrenList){
		Map<String,Object> map = new HashMap<String,Object>();
		// 流水号
		String transNo = "WM" + DateUtil.getTimes() + Tools.getRandomNum();
		OrderApplyReq req = new OrderApplyReq();
		//投保人信息
		ApplicantInfo applicantInfo = new ApplicantInfo();
		//投保期限信息
		ApplicationData applicationData = new ApplicationData();
		//被保人信息集合
		List<InsurantInfo> insurantInfos = new ArrayList<InsurantInfo>();
		
		//String	必填	投保时间 格式：yyyy-MM-dd HH:mm:ss
		applicationData.setApplicationDate(DateUtil.getTime());
		//String	必填	起保时间 格式：yyyy-MM-dd
		applicationData.setStartDate(startDate);
		//String	必填	终保时间 格式：yyyy-MM-dd
		applicationData.setEndDate(endDate);
		
		
		int size = baoXianrenList.size()-1;
		
		//投保人
		Policyholders policyholders = new Policyholders();
		policyholders.setId(UuidUtil.get32UUID());
		policyholders.setApplicationDate(applicationData.getApplicationDate());
		policyholders.setStartDate(startDate);
		policyholders.setEndDate(endDate);
		policyholders.setTransNo(transNo);
		policyholders.setCaseCode(planPrice.getCaseCode()); 
		//DecimalFormat df = new DecimalFormat("#.00");
		policyholders.setPriceTotal(planPrice.getPrice()*size); 
		policyholders.setInsurantCount(size);
		policyholders.setOrder_type("1");
		
		//被保人
		List<Insured> insuredList = new ArrayList<Insured>();
		
		BaoXianren toubaoren = new BaoXianren();
		
		for(int i = 0;i<baoXianrenList.size();i++){
			if(1 == baoXianrenList.get(i).getAa_signup_linkman()){
				toubaoren = baoXianrenList.get(i);
			}
		}
		
		for(int i = 0;i<baoXianrenList.size();i++){
			 BaoXianren baoXianren = baoXianrenList.get(i);
			 //判断是否是投保人
			 if(1 == baoXianren.getAa_signup_linkman()){
				 this.setApplicantInfo(baoXianren, planPrice, applicantInfo,policyholders);
			 }else{
				 if(toubaoren.getAa_user_certificates_num().equals(baoXianren.getAa_user_certificates_num())){
					 baoXianren.setAa_signup_linkman(1);
				 }
				 this.setInsurantInfo(baoXianren, planPrice, i, insurantInfos,insuredList);
			 }
		 }
		
		
		/**********************************/
		//投保人信息
		req.setApplicantInfo(applicantInfo);
		//订单信息
		req.setApplicationData(applicationData);
		//被保人信息列表
		req.setInsurantInfos(insurantInfos);
		//transNo	String	必填	交易流水号，每次请求不能相同
		req.setTransNo(transNo);
		//partnerId	int	必填	渠道商身份标识，由慧择指定
		req.setPartnerId(BaoXianProperties.partnerId);
		//caseCode	String	必填	方案代码，每一款保险公司产品的方案代码都不相同，由慧择分配
		req.setCaseCode(planPrice.getCaseCode());
		
		/*System.out.println("交易流水号:"+req.getTransNo());
		System.out.println("方案代码:"+req.getCaseCode());
		System.out.println("渠道商身份标识:"+req.getPartnerId());
		
		System.out.println("订单信息:");
		System.out.println("\t投保时间"+req.getApplicationData().getApplicationDate());
		System.out.println("\t起保时间"+req.getApplicationData().getStartDate());
		System.out.println("\t终保时间"+req.getApplicationData().getEndDate());
		
		System.out.println("投保人信息:");
		System.out.println("\t投保人姓名:"+req.getApplicantInfo().getcName());
		System.out.println("\t投保人证件类型:"+req.getApplicantInfo().getCardType());
		System.out.println("\t投保人证件号:"+req.getApplicantInfo().getCardCode());
		System.out.println("\t投保人性别:"+req.getApplicantInfo().getSex());
		System.out.println("\t投保人出生日期:"+req.getApplicantInfo().getBirthday());
		System.out.println("\t投保人移动电话:"+req.getApplicantInfo().getMobile());
		System.out.println("\t投保人邮箱:"+req.getApplicantInfo().getEmail());
		
		System.out.println("被保人信息列表:");
		for(int i=0,len=req.getInsurantInfos().size();i<len;i++){
			System.out.println("被保人"+(i+1));
			System.out.println("\t被保人ID:"+req.getInsurantInfos().get(i).getInsurantId());
			System.out.println("\t被保险人姓名:"+req.getInsurantInfos().get(i).getcName());
			System.out.println("\t被保险人性别:"+req.getInsurantInfos().get(i).getSex());
			System.out.println("\t被保险人证件类型:"+req.getInsurantInfos().get(i).getCardType());
			System.out.println("\t被保险人证件号:"+req.getInsurantInfos().get(i).getCardCode());
			System.out.println("\t被保险人出生日期:"+req.getInsurantInfos().get(i).getBirthday());
			System.out.println("\t与投保人关系:"+req.getInsurantInfos().get(i).getRelationId());
			System.out.println("\t被保险人购买份数:"+req.getInsurantInfos().get(i).getCount());
			System.out.println("\t产品结算价:"+req.getInsurantInfos().get(i).getSinglePrice());
		}*/
		
		Configure.Channel.channelKey = BaoXianProperties.channelKey;
		Configure.Channel.partnerId = BaoXianProperties.partnerId;
		logger.info("保险投保申请流水号："+transNo);
		OpenApiRemoteOperation operation = ProxyFactory.create(OpenApiRemoteOperation.class);
		CommonResult<OrderApplyResp> res = operation.orderApply(req);
		try {
			ObjectMapper objectMapper=new ObjectMapper();
			logger.info(objectMapper.writeValueAsString(res));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		String insureNum = null;
		if(0 == res.getRespCode()){
			logger.info("投保成功响应信息："+res.getRespMsg());
			policyholders.setResultCode(res.getRespCode());
			//List<OrderExtInfo> erderExtInfoList = res.getData().getOrderExts();
			//policyholders.setInsureNum(res.getData().getInsureNum());
			insureNum = res.getData().getInsureNum();
			//List<OrderExtInfo> l = res.getData().getOrderExts();
		}else{
			
			policyholders.setResultCode(res.getRespCode());
			policyholders.setErrorInfo(res.getRespMsg());
			logger.error("投保失败，错误代码:"+res.getRespCode()+"\t错误信息："+res.getRespMsg());
		}
		
		
		

		map.put("resultCode", res.getRespCode());
		map.put("insureNum", insureNum);
		map.put("policyholders", policyholders);
		map.put("insuredList", insuredList);
		return map;
	}
	
	/**
	 * 设置投保人信息
	 * @param baoXianren
	 * @param applicantInfo
	 */
	private void setApplicantInfo(BaoXianren baoXianren,InsurancePlanPriceDTO planPrice,ApplicantInfo applicantInfo,Policyholders policyholders){
		/******投保人信息*******/
		//String	必填	投保人姓名
		applicantInfo.setcName(baoXianren.getAa_user_name());
		//String		投保人姓名拼音，境外旅游险必填
		//applicantInfo.seteName("WinZhong");
		//int	必填	投保人证件类型（取值参考附录1）
		applicantInfo.setCardType(baoXianren.getAa_user_certificates_type());
		//String	必填	投保人证件号
		applicantInfo.setCardCode(baoXianren.getAa_user_certificates_num());
		//int	必填	投保人性别 0：女 1：男
		applicantInfo.setSex(baoXianren.getAa_user_sex());
		//String	必填	投保人出生日期 格式：yyyy-MM-dd
		applicantInfo.setBirthday(baoXianren.getBirthday());
		//String	必填	投保人移动电话
		applicantInfo.setMobile(baoXianren.getAa_user_phone());
		//String	必填	投保人邮箱
		applicantInfo.setEmail(baoXianren.getEmail());
		
		policyholders.setOrder_id(baoXianren.getAsu_id());
		//String	必填	投保人姓名
		policyholders.setCName(baoXianren.getAa_user_name());
		//int	必填	投保人证件类型（取值参考附录1）
		policyholders.setCardTypeId(baoXianren.getAa_user_certificates_type());
		//String	必填	投保人证件号
		policyholders.setCardCode(baoXianren.getAa_user_certificates_num());
		//int	必填	投保人性别 0：女 1：男
		policyholders.setSex(baoXianren.getAa_user_sex());
		//String	必填	投保人出生日期 格式：yyyy-MM-dd
		policyholders.setBirthday(baoXianren.getBirthday());
		//String	必填	投保人移动电话
		policyholders.setMobile(baoXianren.getAa_user_phone());
		//String	必填	投保人邮箱
		policyholders.setEmail(baoXianren.getEmail());
		
		//投保计划ID
		policyholders.setPlanId(planPrice.getPlanId());
		//投保计划名称
		policyholders.setPlanName(planPrice.getPlanName());
		//投保产品ID
		policyholders.setProductId(planPrice.getProductId());
		//投保产品名称
		policyholders.setProductName(planPrice.getProductName());
		//投保保险公司名称
		policyholders.setCompanyName(planPrice.getCompanyName());
		
		//联系人ID
		policyholders.setLinkman_id(baoXianren.getAa_id());
		
	}
	
	/**
	 * 设置被保人信息
	 * @param baoXianren
	 * @param planPrice
	 * @param i
	 * @param insurantInfos
	 */
	private void setInsurantInfo(BaoXianren baoXianren,InsurancePlanPriceDTO planPrice,int i,List<InsurantInfo> insurantInfos,List<Insured> insuredList){
		/**************被保人信息****************/
		InsurantInfo insurantInfo = new InsurantInfo();
		insurantInfos.add(insurantInfo);
		//String	必填	被保人ID，由渠道商传递（同一单此字段不能重复，只能为Int范围内的数字）
		insurantInfo.setInsurantId(i+"");
		//String	必填	被保险人姓名
		insurantInfo.setcName(baoXianren.getAa_user_name());
		//String		被保险人姓名拼音，境外旅游险必填
		//insurantInfo.seteName("x6");
		//sex	int	必填	被保险人性别 0：女 1：男
		insurantInfo.setSex(baoXianren.getAa_user_sex());
		//cardType	int	必填	被保险人证件类型（取值参考附录1）
		insurantInfo.setCardType(baoXianren.getAa_user_certificates_type());
		//cardCode	String	必填	被保险人证件号
		insurantInfo.setCardCode(baoXianren.getAa_user_certificates_num());
		//birthday	String	必填	被保险人出生日期 格式：yyyy-MM-dd
		insurantInfo.setBirthday(baoXianren.getBirthday());
		//int	必填	与投保人关系（取值参考附录3） 6	其他
		insurantInfo.setRelationId(baoXianren.getAa_signup_linkman() == 1 ? 1 : 2);
		//int	必填	被保险人购买份数
		insurantInfo.setCount(1);
		//double	必填	产品结算价（单位：元）
		insurantInfo.setSinglePrice(planPrice.getPrice());
		//String		所在地区，特定产品必填
		//insurantInfo.setCity("北京");
		//int		出行目的（取值参考附录2）
		//insurantInfo.setTripPurposeId(0);
		
		Insured insured = new Insured();
		insuredList.add(insured);
		insured.setId(UuidUtil.get32UUID());
		//String	必填	被保人ID，由渠道商传递（同一单此字段不能重复，只能为Int范围内的数字）
		insured.setInsurantId(i+"");
		//String	必填	被保险人姓名
		insured.setCName(baoXianren.getAa_user_name());
		//sex	int	必填	被保险人性别 0：女 1：男
		insured.setSex(baoXianren.getAa_user_sex()+"");
		//cardType	int	必填	被保险人证件类型（取值参考附录1）
		insured.setCardTypeId(baoXianren.getAa_user_certificates_type());
		//cardCode	String	必填	被保险人证件号
		insured.setCardCode(baoXianren.getAa_user_certificates_num());
		//birthday	String	必填	被保险人出生日期 格式：yyyy-MM-dd
		insured.setBirthday(baoXianren.getBirthday());
		//int	必填	与投保人关系（取值参考附录3） 6	其他
		insured.setRelationId(insurantInfo.getRelationId());
		//int	必填	被保险人购买份数
		insured.setCount(1);
		//double	必填	产品结算价（单位：元）
		insured.setSinglePrice(planPrice.getPrice());
		
		//联系人ID
		insured.setLinkman_id(baoXianren.getAa_id());
		//订单ID
		insured.setOrder_id(baoXianren.getAsu_id());
	}
	
	
	/**
	 * 保单下载
	 * @param insureNum  投保单号
	 */
	public boolean policyDownload(String insureNum){
		// 流水号
		String transNo = "WM" + DateUtil.getTimes() + Tools.getRandomNum();
		InsureDownloadReq req = new InsureDownloadReq();
		//transNo	String	必填	交易流水号，每次请求不能相同
		req.setTransNo(transNo);
		//partnerId	int	必填	渠道商身份标识，由慧择指定
		req.setPartnerId(BaoXianProperties.partnerId);
		//insureNum	String	必填	投保单号
		req.setInsureNum(insureNum);
		
		Configure.Channel.channelKey = BaoXianProperties.channelKey;
		Configure.Channel.partnerId = BaoXianProperties.partnerId;
		//sign	String	必填	签名，预签名数据：密钥+渠道商身份标识+交易流水号+方案代码
		logger.info("流水号："+transNo);
		OpenApiRemoteOperation operation = ProxyFactory.create(OpenApiRemoteOperation.class);
		CommonResult<InsureDownloadResp> res = operation.download(req);
		if(res.getRespCode() == 0){
			InsureDownloadResp resp = res.getData();
			//logger.info(resp.transNo);//String	交易流水号，与请求报文的流水号一致
			logger.info(resp.fileName);//String	文件名称
			logger.info(resp.fileSize);//Long	文件大小 单位：字节(B)
			logger.info(resp.contentType);//String	文件类型，默认application/pdf
			//logger.info(resp.data)	;//String	文件流内容，采用Base64编码
			
			OSSClient ossClient = AliyunOSSFactory.getOSSClient();
			// 上传
			byte[] content = Base64.decodeBase64(resp.data.getBytes());
			// 上传Object.
			PutObjectResult result = ossClient.putObject(AliyunOSSProperties.BUCKETNAME,  "policy/"+resp.fileName, new ByteArrayInputStream(content));
			// 打印ETag
			logger.info("保单下载上传阿里云成功，投保单号："+insureNum+"，保单文件ETag：" + result.getETag());
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 保险退保 对未起保（未生效）的保单进行退保处理
	 * @param insureNum  投保单号
	 */
	public Map<String,Object> orderCancel(String insureNum){
		Map<String,Object> map = new HashMap<String,Object>();
		OrderCancelReq req = new OrderCancelReq();
		// 流水号
		String transNo = "WM" + DateUtil.getTimes() + Tools.getRandomNum();
		//transNo	String	必填	交易流水号，每次请求不能相同
		req.setTransNo(transNo);
		//partnerId	int	必填	渠道商身份标识，由慧择指定
		req.setPartnerId(BaoXianProperties.partnerId);
		//insureNum	String	必填	投保单号
		req.setInsureNum(insureNum);

		
		Configure.Channel.channelKey = BaoXianProperties.channelKey;
		Configure.Channel.partnerId = BaoXianProperties.partnerId;
		//sign	String	必填	签名，预签名数据：密钥+渠道商身份标识+交易流水号+方案代码
		logger.info("流水号："+transNo);
		OpenApiRemoteOperation operation = ProxyFactory.create(OpenApiRemoteOperation.class);
		CommonResult<OrderCancelResp> res = operation.orderCancel(req);
		if(res.getRespCode() == 0){
			logger.info("msg:"+res.getRespMsg());
			OrderCancelResp order = res.getData();
			logger.info("交易流水号，与请求报文的流水号一致:"+order.getTransNo());
			logger.info("投保单号:"+order.getInsureNum());
			//logger.info("渠道商身份标识，由慧择指定:"+order.getPartnerId());
			map.put("type", "success");
			map.put("msg", res.getRespMsg());
		}else{
			logger.info("退保失败原因:"+res.getRespMsg());
			if(res.getRespCode()==50148){
				map.put("type", "success");
				map.put("msg", res.getRespMsg());
			}else{
				map.put("type", "error");
				map.put("msg", res.getRespMsg());
			}
			
		}
		
		return map;
	}
}
