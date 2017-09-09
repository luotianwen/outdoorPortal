package com.op.controller.insurance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.op.controller.BaseController;
import com.op.entity.insurance.AsyncOrderInfo;
import com.op.entity.insurance.AsyncPolicyInfo;
import com.op.service.insurance.PolicyholdersService;

/**
 * 
*    
* 项目名称：opManage   
* 类名称：BaoXianController   
* 类描述：   保险异步通知
* 创建人：Win Zhong   
* 创建时间：2016年3月14日 下午2:45:39   
* 修改人：Win Zhong   
* 修改时间：2016年3月14日 下午2:45:39   
* 修改备注：   
* @version    
*
 */
@Controller
@RequestMapping(value="/baoxian")
public class BaoXianController extends BaseController{

	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Resource(name="policyholdersServiceImpl")
	private PolicyholdersService policyholdersServiceImpl;
	
	/**
	 * 投保异步通知
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/notify")
	public void notify(HttpServletRequest request,HttpServletResponse response){
		 
			try {
				//System.out.println(DateUtil.getTime()+"异步通知结果："+this.findKeyMapByRequest(request));
				BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
				String line = null;
				StringBuilder sb = new StringBuilder();  
				while ((line = br.readLine()) != null) {
				    sb.append(line);
				}
				logger.info("保险异步通知结果："+sb);
				String str = sb.toString();//" {\"insureNum\":\"20160325001934\",\"partnerId\":23994,\"policyList\":[{\"productId\":1525,\"productName\":\"(API测试 勿动）众行天下-慧择户外运动\",\"planId\":1756,\"planName\":\"计划A\",\"applicant\":\"孟虹颖\",\"insurant\":\"陈书萱、许琬凝\",\"startDate\":\"2016-03-26\",\"endDate\":\"2016-03-27\",\"totalnum\":2,\"issueState\":20,\"signKey\":\"a7d7b3fb9e810912fe83688408fdb1be\",\"policyNum\":\"AXIM999E0616E006259R\"}],\"resultCode\":0}";
				//str = "{\"insureNum\":\"20160601028437\",\"partnerId\":23994,\"policyList\":[{\"productId\":1215,\"productName\":\"API（勿动）安联安途中国（自驾游）旅行险计划\",\"planId\":1337,\"planName\":\"计划一\",\"applicant\":\"闫鑫鑫\",\"insurant\":\"闫鑫鑫、刘弘孚\",\"startDate\":\"2016-06-08\",\"endDate\":\"2016-06-09\",\"totalnum\":2,\"issueState\":20,\"signKey\":\"c6f1cc8a97790fbed1afccb0a6d813bf\",\"policyNum\":\"301-1-594-16-0000018176-00\"}],\"resultCode\":0}";
				//str = "{\"insureNum\":\"20160602025581\",\"partnerId\":23994,\"policyList\":[{\"productId\":1215,\"productName\":\"API（勿动）安联安途中国（自驾游）旅行险计划\",\"planId\":1337,\"planName\":\"计划一\",\"applicant\":\"闫鑫鑫\",\"insurant\":\"闫鑫鑫、刘弘孚、刘一森\",\"startDate\":\"2016-06-16\",\"endDate\":\"2016-06-17\",\"totalnum\":3,\"issueState\":20,\"signKey\":\"20d9fa3bf135efb54aaaf601b7c58d6e\",\"policyNum\":\"301-1-594-16-0000018187-00\"}],\"resultCode\":0}";
				if(StringUtils.isEmpty(str)){
					logger.info("【保险异步出单回调信息异常】：回调内容为空！");
				}else{
					JSONObject jsonobject = JSONObject.fromObject(str);
					AsyncOrderInfo asyncOrderInfo = (AsyncOrderInfo)JSONObject.toBean(jsonobject, AsyncOrderInfo.class);
					System.out.println(asyncOrderInfo.getInsureNum());
					if(asyncOrderInfo.getResultCode() == 0){//出单成功处理
						logger.info("【保险异步出单成功】单号："+asyncOrderInfo.getInsureNum());
						List<AsyncPolicyInfo> policyList = (List<AsyncPolicyInfo>) JSONArray.toCollection((JSONArray) jsonobject.get("policyList"), AsyncPolicyInfo.class);
						asyncOrderInfo.setPolicyList(policyList);
						//System.out.println("========="+policyList.get(0).getProductName());
						//System.out.println("========="+asyncOrderInfo.getPolicyList().get(0).getProductName());
						//根据保险异步回调信息处理保险订单信息
						policyholdersServiceImpl.callbackHandler(asyncOrderInfo);
					}else{//出单失败处理
						logger.info("【保险异步出单失败】单号："+asyncOrderInfo.getInsureNum());
					}
				}
				//{"insureNum":"20160601007841","partnerId":23994,"policyList":[{"productId":1215,"productName":"API（勿动）安联安途中国（自驾游）旅行险计划","planId":1337,"planName":"计划一","applicant":"唐秀颖","insurant":"唐秀颖、卜雅艳","startDate":"2016-06-07","endDate":"2016-06-08","totalnum":2,"issueState":20,"signKey":"a363bcba61434ae29322d1b7e0cfbf28","policyNum":"301-1-594-16-0000018175-00"}],"resultCode":0}

				
				PrintWriter out = response.getWriter();
				out.println("success");
			} catch (UnsupportedEncodingException e) {
				logger.error("【保险异步出单处理失败】"+e);
			} catch (IOException e) {
				logger.error("【保险异步出单处理失败】"+e);
			} catch (Exception e) {
				logger.error("【保险异步出单处理失败】"+e);
			}
		 
	}
	
	
	
}
