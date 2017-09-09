package com.op.controller.alipay;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.newAlipay.AlipayInfo;
import com.newAlipay.config.AlipayConfig;
import com.newAlipay.util.AlipayNotify;
import com.newAlipay.util.AlipaySubmit;
import com.op.service.pay.AlipayInfoService;
import com.op.util.IdWorker;

@Controller
@RequestMapping(value="ali")
public class NewAlipayController {

	protected Logger logger = Logger.getLogger(this.getClass());

	@Resource(name="alipayInfoServiceImpl")
	private AlipayInfoService alipayInfoServiceImpl;

	/**
	 * 跳转到支付宝支付页面
	 * @param mv
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/pay")
	public ModelAndView pay(ModelAndView mv,HttpSession session,HttpServletRequest request,@ModelAttribute("alipayInfo") AlipayInfo alipayInfo){
		////////////////////////////////////请求参数//////////////////////////////////////

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = alipayInfo.getOut_trade_no();//request.getParameter("out_trade_no");

        //订单名称，必填
        String subject = alipayInfo.getSubject();//request.getParameter("subject");

        //付款金额，必填
        String total_fee = alipayInfo.getTotal_fee()+"";//request.getParameter("total_fee");

        //商品描述，可空
        String body = alipayInfo.getBody();//request.getParameter("body");
		
		//自定义公用回传参数
		String extra_common_param = alipayInfo.getExtra_common_param();//request.getParameter("extra_common_param");
		alipayInfo.setSerial_number(IdWorker.getSerialNumber());
        //防钓鱼时间戳
        try {
			AlipayConfig.anti_phishing_key = AlipaySubmit.query_timestamp();
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			alipayInfoServiceImpl.saveInfo(alipayInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//////////////////////////////////////////////////////////////////////////////////
		
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", AlipayConfig.service);
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_id", AlipayConfig.seller_id);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", AlipayConfig.payment_type);
		sParaTemp.put("notify_url", AlipayConfig.notify_url);
		sParaTemp.put("return_url", AlipayConfig.return_url);
		sParaTemp.put("anti_phishing_key", AlipayConfig.anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", AlipayConfig.exter_invoke_ip);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		sParaTemp.put("extra_common_param", extra_common_param);
		//商品展示网址
		if(alipayInfo.getShow_url() != null){
			sParaTemp.put("show_url", alipayInfo.getShow_url());
		}
		
		//扫码支付参数
		//sParaTemp.put("qr_pay_mode", "1");
		//其他业务参数根据在线开发文档，添加参数.文档地址:https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.O9yorI&treeId=62&articleId=103740&docType=1
        //如sParaTemp.put("参数名","参数值");
		
		//建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
		System.out.println(sHtmlText);
		mv.setViewName("pay/pay");
		mv.addObject("payHtml", sHtmlText);
		return mv;
	}
	
	
	/**
	 * 
	 * @Description: 支付宝返回支付结果 异步通知
	 * @param request
	 * @param response
	 * @throws Exception   
	 * @return void  
	 * @author WinZhong
	 * @date 2016年3月12日 下午2:58:02
	 */
	@RequestMapping("/notify")
	public void notify(HttpServletRequest request,HttpServletResponse response,AlipayInfo alipayInfo){
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号

		String out_trade_no = request.getParameter("out_trade_no");

		//支付宝交易号

		String trade_no = request.getParameter("trade_no");

		//交易状态
		String trade_status = request.getParameter("trade_status");

		//自定义公用回传参数
		String extra_common_param = request.getParameter("extra_common_param");
		//类名,返回地址
		String[] rurl = extra_common_param.split(",");
		alipayInfo.setExtra_common_param(rurl[0]);

		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		logger.info("订单号："+out_trade_no+"\n"+params);
		if(AlipayNotify.verify(params)){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码

			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			
			if(trade_status.equals("TRADE_FINISHED")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
					//如果有做过处理，不执行商户的业务程序
					
				//注意：
				//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
			} else if (trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
					//如果有做过处理，不执行商户的业务程序
				//修改支付信息
				try {
					alipayInfoServiceImpl.updateInfo(alipayInfo);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					out.print("fail");
				}
				//注意：
				//付款完成后，支付宝系统发送该交易状态通知
			}

			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				
			out.print("success");	//请不要修改或删除
			logger.info("订单号："+out_trade_no+" 支付结果：success");
			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			out.print("fail");
			logger.info("订单号："+out_trade_no+" 支付结果：fail");
		}
	}
	
	
	/**
	 * 支付宝返回支付结果 同步通知
	 * @param mv
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/return")
	public ModelAndView returns(HttpServletRequest request,ModelAndView mv,AlipayInfo alipayInfo){
		
		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号

		String out_trade_no = request.getParameter("out_trade_no");
		System.out.println(alipayInfo.getOut_trade_no());
		//支付宝交易号

		String trade_no = request.getParameter("trade_no");

		//交易状态
		String trade_status = request.getParameter("trade_status");
		
		//自定义公用回传参数
		String extra_common_param = request.getParameter("extra_common_param");
		//类名,返回地址
		String[] rurl = extra_common_param.split(",");
		alipayInfo.setExtra_common_param(rurl[0]);
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		logger.info("订单号："+out_trade_no+"\n"+params);
		//计算得出通知验证结果
		boolean verify_result = AlipayNotify.verify(params);
		
		if(verify_result){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码

			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
				
				//修改支付信息
				try {
					alipayInfoServiceImpl.updateInfo(alipayInfo);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.error("订单号："+out_trade_no+"修改支付信息错误：", e);
				}
				
			}
			
			//该页面可做页面美工编辑
			//System.out.println("验证成功<br />");
			mv.addObject("orderId", out_trade_no);
			/*mv.addObject("total_fee", params.get("total_fee"));*/
			mv.addObject("result", "success");
			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
			mv.setViewName("redirect:/"+rurl[1]);
			//////////////////////////////////////////////////////////////////////////////////////////
		}else{
			//该页面可做页面美工编辑
			System.out.println("验证失败");
			mv.addObject("result", "fail");
			mv.setViewName("redirect:/"+rurl[1]);
		}
		
		
		return mv;
	}
	
}
