package com.op.plugin.insurance;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzins.channel.api.model.common.CommonResult;
import com.hzins.channel.api.model.info.ApplicantInfo;
import com.hzins.channel.api.model.info.ApplicationData;
import com.hzins.channel.api.model.info.InsurantInfo;
import com.hzins.channel.api.model.req.OrderApplyReq;
import com.hzins.channel.api.model.resp.OrderApplyResp;
import com.hzins.openapi.client.Configure;
import com.hzins.openapi.client.OpenApiRemoteOperation;
import com.hzins.openapi.client.common.ProxyFactory;
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
public class OpenApiRemoteOperationTest {

    static OpenApiRemoteOperation operation = ProxyFactory.create(OpenApiRemoteOperation.class);
    
    ObjectMapper objectMapper=new ObjectMapper();

    protected Logger logger = Logger.getLogger(this.getClass());
    

    public void testorderApply() throws JsonProcessingException {
		
	// 流水号
	String transNo = "WM" + DateUtil.getTimes() + Tools.getRandomNum();
	//System.out.println("流水号："+transNo);
	
	OrderApplyReq req = new OrderApplyReq();
	ApplicantInfo applicantInfo = new ApplicantInfo();
	ApplicationData applicationData = new ApplicationData();
	List<InsurantInfo> insurantInfos = new ArrayList<InsurantInfo>();
	req.setApplicantInfo(applicantInfo);
	req.setApplicationData(applicationData);
	req.setInsurantInfos(insurantInfos);
	//String	必填	投保时间 格式：yyyy-MM-dd HH:mm:ss
	applicationData.setApplicationDate(DateUtil.getTime());
	//String	必填	起保时间 格式：yyyy-MM-dd
	applicationData.setStartDate("2016-03-27");
	//String	必填	终保时间 格式：yyyy-MM-dd
	applicationData.setEndDate("2016-03-28");
	//String	必填	投保人姓名
	applicantInfo.setcName("孟虹颖");
	//String		投保人姓名拼音，境外旅游险必填
	applicantInfo.seteName("WinZhong");
	//int	必填	投保人证件类型（取值参考附录1）
	applicantInfo.setCardType(1);
	//String	必填	投保人证件号
	applicantInfo.setCardCode("451025197408122209");
	//int	必填	投保人性别 0：女 1：男
	applicantInfo.setSex(0);
	//String	必填	投保人出生日期 格式：yyyy-MM-dd
	applicantInfo.setBirthday("1974-08-12");
	//String	必填	投保人移动电话
	applicantInfo.setMobile("13800000000");
	//String	必填	投保人邮箱
	applicantInfo.setEmail("2355566253@qq.com");
	
	
	InsurantInfo insurantInfo = new InsurantInfo();
	insurantInfos.add(insurantInfo);
	//String	必填	被保人ID，由渠道商传递（同一单此字段不能重复，只能为Int范围内的数字）
	insurantInfo.setInsurantId("1001");
	//String	必填	被保险人姓名
	insurantInfo.setcName("陈书萱");
	//String		被保险人姓名拼音，境外旅游险必填
	insurantInfo.seteName("x6");
	//sex	int	必填	被保险人性别 0：女 1：男
	insurantInfo.setSex(0);
	//cardType	int	必填	被保险人证件类型（取值参考附录1）
	insurantInfo.setCardType(2);
	//cardCode	String	必填	被保险人证件号
	insurantInfo.setCardCode("451025200501107329");
	//birthday	String	必填	被保险人出生日期 格式：yyyy-MM-dd
	insurantInfo.setBirthday("1975-01-10");
	//int	必填	与投保人关系（取值参考附录3）
	insurantInfo.setRelationId(6);
	//int	必填	被保险人购买份数
	insurantInfo.setCount(1);
	//double	必填	产品结算价（单位：元）
	insurantInfo.setSinglePrice(5.00);
	//String		所在地区，特定产品必填
	//insurantInfo.setCity("北京");
	//int		出行目的（取值参考附录2）
	insurantInfo.setTripPurposeId(0);
	

	
	InsurantInfo insurantInfo2 = new InsurantInfo();
	insurantInfos.add(insurantInfo2);
	//String	必填	被保人ID，由渠道商传递（同一单此字段不能重复，只能为Int范围内的数字）
	insurantInfo2.setInsurantId("1002");
	//String	必填	被保险人姓名
	insurantInfo2.setcName("许琬凝");
	//String		被保险人姓名拼音，境外旅游险必填
	insurantInfo2.seteName("x6");
	//sex	int	必填	被保险人性别 0：女 1：男
	insurantInfo2.setSex(0);
	//cardType	int	必填	被保险人证件类型（取值参考附录1）
	insurantInfo2.setCardType(2);
	//cardCode	String	必填	被保险人证件号
	insurantInfo2.setCardCode("120000197607141240");
	//birthday	String	必填	被保险人出生日期 格式：yyyy-MM-dd
	insurantInfo2.setBirthday("1975-07-14");
	//int	必填	与投保人关系（取值参考附录3）
	insurantInfo2.setRelationId(6);
	//int	必填	被保险人购买份数
	insurantInfo2.setCount(1);
	//double	必填	产品结算价（单位：元）
	insurantInfo2.setSinglePrice(5.00);
	//String		所在地区，特定产品必填
	//insurantInfo2.setCity("北京");
	//int		出行目的（取值参考附录2）
	insurantInfo2.setTripPurposeId(0);
	
	//transNo	String	必填	交易流水号，每次请求不能相同
	req.setTransNo(transNo);
	//partnerId	int	必填	渠道商身份标识，由慧择指定
	req.setPartnerId(BaoXianProperties.partnerId);
	//caseCode	String	必填	方案代码，每一款保险公司产品的方案代码都不相同，由慧择分配
	req.setCaseCode("0000045152501756");
	//签名
	//String sign=Md5Utils.getMD5String(BaoXianProperties.channelKey+BaoXianProperties.partnerId+req.getTransNo()+req.getCaseCode());
	//req.setSign(sign);
	Configure.Channel.channelKey = BaoXianProperties.channelKey;
	Configure.Channel.partnerId = BaoXianProperties.partnerId;
	//sign	String	必填	签名，预签名数据：密钥+渠道商身份标识+交易流水号+方案代码
	logger.info("流水号："+transNo);
	CommonResult<OrderApplyResp> res = operation.orderApply(req);
	
	if(0 == res.getRespCode()){
		logger.info(res.getData().getTransNo()+"****"+res.getData().getInsureNum());
	}else{
		logger.debug(res.getRespMsg());
	}
	
	
	
	logger.info(objectMapper.writeValueAsString(res));
	//List<OrderExtInfo> l = res.getData().getOrderExts();
	String msg = objectMapper.writeValueAsString(res);
	logger.info("投保响应信息："+msg);
    }

}
