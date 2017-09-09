package com.op.controller.alipay;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.op.controller.BaseController;
import com.op.dto.activity.activeSignUpPay.ActiveSignUpPay;
import com.op.dto.insurance.BaoXianren;
import com.op.dto.pointService.order.PointServiceOrderInfoDTO;
import com.op.entity.alipay.Alipay;
import com.op.entity.usercenter.ActiveApplicant;
import com.op.entity.users.Users;
import com.op.plugin.alipay.config.AlipayConfig;
import com.op.plugin.alipay.util.AlipayNotify;
import com.op.plugin.alipay.util.AlipaySubmit;
import com.op.service.balance.BalanceService;
import com.op.service.balance.CashRecordService;
import com.op.service.insurance.InsurancesService;
import com.op.service.messageprivate.MessagePrivateService;
import com.op.service.pointService.PointServiceOrderService;
import com.op.service.usercenter.ActiveApplicantLogService;
import com.op.service.usercenter.ActiveSignupService;
import com.op.service.users.UsersService;
import com.op.util.Const;
import com.op.util.DateUtil;
/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：AlipayController   
* 类描述：   支付
* 创建人：Win Zhong   
* 创建时间：2015年12月25日 下午3:51:24   
* 修改人：Win Zhong   
* 修改时间：2015年12月25日 下午3:51:24   
* 修改备注：   
* @version    
*
 */
@Controller
@RequestMapping(value="alipay")
public class AlipayController extends BaseController{

	// 活动报名订单Service
	@Resource(name="activeSignupServiceImpl")
	private ActiveSignupService activeSignupServiceImpl;
	//用户资金账户余额
	@Resource(name="balanceServiceImpl")
	private BalanceService balanceServiceImpl;
	//用户资金明细
	@Resource(name="cashRecordServiceImpl")
	private CashRecordService cashRecordServiceImpl;
	//订单操作日志
	@Resource(name="activeApplicantLogServiceImpl")
	private ActiveApplicantLogService activeApplicantLogServiceImpl;
	//购买保险
	@Resource(name="insurancesServiceImpl")
	private InsurancesService insurancesServiceImpl;
	//用户
	@Resource(name="usersServiceImpl")
	private UsersService usersServiceImpl;
	//场馆订单
	@Resource(name="pointServiceOrderServiceImpl")
	PointServiceOrderService pointServiceOrderServiceImpl;
	//站内信
	@Resource(name="messagePrivateServiceImpl")
	MessagePrivateService messagePrivateServiceImpl;
	
	/**
	 * 跳转到支付宝支付页面
	 * @param mv
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/pay")
	public ModelAndView pay(ModelAndView mv,HttpSession session,Alipay alipay) throws IOException {
		
		
		
		////////////////////////////////////请求参数,示例为前台输入参数，正式请放入session,从session获取     WinZhong//////////////////////////////////////
	
		//支付类型
		String payment_type = "1";
		//必填，不能修改
		//服务器异步通知页面路径
		String notify_url = AlipayConfig.pay_notify_url;
		//需http://格式的完整路径，不能加?id=123这类自定义参数
	
		//页面跳转同步通知页面路径
		String return_url = AlipayConfig.pay_return_url;
		//需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
	
		//商户订单号
		String out_trade_no = this.getParameter("out_trade_no").trim();
		//商户网站订单系统中唯一订单号，必填
	
		Alipay ap = (Alipay) session.getAttribute("pay_"+out_trade_no);
		
		//订单名称
		String subject = ap.getSubject();
		//必填
	
		//付款金额
		String total_fee =  ap.getTotal_fee();
		//必填
	
		//订单描述
		String body = ap.getBody();
		
		//自定义公用回传参数
		String extra_common_param = ap.getExtra_common_param();
		
		//默认支付方式
		//String paymethod = this.getParameter("paymethod");
		//必填
		//默认网银
		String defaultbank = this.getParameter("bank");
		//必填，银行简码请参考接口技术文档
		
		//商品展示地址
		String show_url = "";
		
		if("active".equals(extra_common_param)){
			show_url = MessageFormat.format(AlipayConfig.buy_active_url, ap.getShow_url());
		}else if("pointService".equals(extra_common_param)){
			show_url = MessageFormat.format(AlipayConfig.buy_pointService_url, ap.getShow_url());
		}
		
		//需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html
	
		//防钓鱼时间戳
		String anti_phishing_key = "";
		try {
			anti_phishing_key = AlipaySubmit.query_timestamp();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			anti_phishing_key = "";
		}
		//若要使用请调用类文件submit中的query_timestamp函数
	
		//客户端的IP地址
		String exter_invoke_ip = "";
		//非局域网的外网IP地址，如：221.0.0.1
		
		
		//////////////////////////////////////////////////////////////////////////////////
		logger.info("打包支付请求参数成数组");
		
		if("active".equals(extra_common_param)){
			logger.info("订单类型：活动订单");
		}else if("pointService".equals(extra_common_param)){
			logger.info("订单类型：场馆订单");
		}
		logger.info("订单编号："+out_trade_no);
		logger.info("订单名称："+subject);
		logger.info("支付金额："+total_fee);
		
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "create_direct_pay_by_user");
	    sParaTemp.put("partner", AlipayConfig.partner);
	    sParaTemp.put("seller_email", AlipayConfig.seller_email);
	    sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		sParaTemp.put("extra_common_param", extra_common_param);
		//判断是否是网银支付
		if(!"ZFB".equals(defaultbank)){
			sParaTemp.put("paymethod", "bankPay");
			sParaTemp.put("defaultbank", defaultbank);
		}
		
		sParaTemp.put("show_url", show_url);
		sParaTemp.put("anti_phishing_key", anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
		logger.info("建立支付请求");
		//建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
		System.out.println("=================================================\n"+sHtmlText
				+"\n=================================================");
		mv.setViewName("pay/pay");
		mv.addObject("payHtml", sHtmlText);
		logger.info("发送支付请求");
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
	public  void notify(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
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
		String out_trade_no = this.getParameter("out_trade_no");

		//支付宝交易号

		String trade_no = this.getParameter("trade_no");

		//交易状态
		String trade_status = this.getParameter("trade_status");

		//自定义公用回传参数
		String extra_common_param = this.getParameter("extra_common_param");
		
		PrintWriter out = response.getWriter();
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		if(AlipayNotify.verify(params)){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码
			

			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			
			if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
					//如果有做过处理，不执行商户的业务程序
				
				// 获取当前操作用户session
				//HttpSession session = request.getSession();
				
				// 判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
				//Alipay ap = (Alipay) session.getAttribute(out_trade_no);
				
				
				if("active".equals(extra_common_param)){
					//查询活动信息
					List<ActiveSignUpPay> activeSignUpPays = activeSignupServiceImpl.findAsuIdByAccountNum(out_trade_no);
					ActiveSignUpPay activeSignUpPay =  activeSignUpPays.get(0);
					
					// 判断请支付金额 和 合作身份者ID
					if(activeSignUpPay != null && Double.parseDouble(params.get("total_fee")) > 0
							&& (params.get("seller_id")+"").equals(AlipayConfig.partner) ){
						
						Alipay ap = new Alipay();
						
						//订单编号
						ap.setOut_trade_no(out_trade_no);
						
						// 保存支付宝交易号，退款用
						ap.setTrade_no(trade_no);
						
						// 付款时间
						ap.setPay_time(new Date());
						
						// 付款金额
						double money = Double.parseDouble(params.get("total_fee"));
						// 单个订单对应的金额
						ap.setTotal_fee((money/activeSignUpPays.size())+"");
						
						//获取报名人ID
						List<String> asu_ids = new ArrayList<String>();
						for(int i=0;i<activeSignUpPays.size();i++){
							asu_ids.add(activeSignUpPays.get(i).getAsu_id());
						}
						
						if(money==activeSignUpPay.getAsu_account_payable()){
							try {
								// 更改订单信息
								activeSignupServiceImpl.updateActiveSignUp(ap);
								
								//领队已同意
								if(activeSignUpPay.getAsu_state()==20){
									//强制购买保险
									if("1".equals(activeSignUpPay.getIsinsurance())){
										//订单创建用户邮箱
										String userEmail = usersServiceImpl.findUserEmailById(activeSignUpPay.getAsu_user_id());
										
										BaoXianren toubaoren = new BaoXianren();
										for(int i=0;i<activeSignUpPay.getActiveApplicant().size();i++){
											ActiveApplicant activeApplicant = activeSignUpPay.getActiveApplicant().get(i);
											if(activeApplicant.getAa_signup_linkman()==1){
												//活动订单号
												toubaoren.setAsu_id(activeApplicant.getAsu_id());
												//参加人员姓名
												toubaoren.setAa_user_name(activeApplicant.getAa_user_name());
												//投保人邮箱
												toubaoren.setEmail(userEmail);
												//参加人员性别（1：男；2：女）
												toubaoren.setAa_user_sex(activeApplicant.getAa_user_sex());
												//参加人员手机号码
												toubaoren.setAa_user_phone(activeApplicant.getAa_user_phone());
												//参加人员证件类型
												toubaoren.setAa_user_certificates_type(activeApplicant.getAa_user_certificates_type());
												//参加人员证件号码
												toubaoren.setAa_user_certificates_num(activeApplicant.getAa_user_certificates_num());
												//是否投保人（1：是；2：不是）
												toubaoren.setAa_signup_linkman(1);
											}
										}
										
										for(int i=0;i<activeSignUpPay.getActiveApplicant().size();i++){
											//保险信息
											List<BaoXianren> baoXianrenList = new ArrayList<BaoXianren>();
											ActiveApplicant activeApplicant = activeSignUpPay.getActiveApplicant().get(i);
											BaoXianren baoXianren = new BaoXianren();
											
											//活动订单号
											baoXianren.setAsu_id(activeApplicant.getAsu_id());
											//参加人员姓名
											baoXianren.setAa_user_name(activeApplicant.getAa_user_name());
											//投保人邮箱
											baoXianren.setEmail(userEmail);
											//参加人员性别（1：男；2：女）
											baoXianren.setAa_user_sex(activeApplicant.getAa_user_sex());
											//参加人员手机号码
											baoXianren.setAa_user_phone(activeApplicant.getAa_user_phone());
											//参加人员证件类型
											baoXianren.setAa_user_certificates_type(activeApplicant.getAa_user_certificates_type());
											//参加人员证件号码
											baoXianren.setAa_user_certificates_num(activeApplicant.getAa_user_certificates_num());
											//是否投保人（1：是；2：不是）
											baoXianren.setAa_signup_linkman(2);
											//订单ID
											baoXianren.setAsu_id(activeApplicant.getAsu_id());
											//联系人ID
											baoXianren.setAa_id(activeApplicant.getAa_id());
											
											baoXianrenList.add(toubaoren);
											baoXianrenList.add(baoXianren);
											
											//购买保险
											insurancesServiceImpl.buy(activeSignUpPay.getAsu_active_id(),activeSignUpPay.getIn_id(),activeSignUpPay.getAsu_user_id(),DateUtil.YYYY_MM_DDgetDay(activeSignUpPay.getActivityTime()), DateUtil.YYYY_MM_DDgetDay(activeSignUpPay.getEndTime()), baoXianrenList);
										}
										
										//订单应付金额
										activeSignUpPay.setAsu_account_payable(activeSignUpPay.getAsu_account_payable()-activeSignUpPay.getInsurance().getAcp_cost());
									}
									
									//新增资金明细
									cashRecordServiceImpl.saveCashRecordByActiveSignup(activeSignUpPay.getCreateUser(), activeSignUpPay.getAsu_reserve_price(), activeSignUpPay.getAsu_account_payable(), activeSignUpPay.getAsu_id());
									//更改发布者钱包信息
									balanceServiceImpl.updateMoneyByUserId(activeSignUpPay.getCreateUser(),activeSignUpPay.getAsu_reserve_price(),activeSignUpPay.getAsu_account_payable());
								}
								
								String aa_idstr = "";
								
								for(int i=0;i<activeSignUpPay.getActiveApplicant().size();i++){
									if(i!=0){
										aa_idstr+=",";
									}
									ActiveApplicant activeApplicant = activeSignUpPay.getActiveApplicant().get(i);
									aa_idstr += activeApplicant.getAa_id();
								}
								
								String[] aa_ids = aa_idstr.split(",");
								
								//钱包日志
								activeApplicantLogServiceImpl.saveActiveApplicantLog(aa_ids, activeSignUpPay.getAsu_id(),activeSignUpPay.getAsu_active_id()+"", 0, "付款成功，付款金额为￥"+(money/aa_ids.length), activeSignUpPay.getAsu_user_id(), "2");
								
								logger.info("支付成功。");
								logger.info("订单编号："+out_trade_no);
								logger.info("支付宝交易号："+trade_no);
								
								/**
								 * 发送消息（付款成功，给领队发送消息） 
								 */
								Users user = usersServiceImpl.findUserById(activeSignUpPay.getCreateUser());
								Users users = usersServiceImpl.findUserById(activeSignUpPay.getAsu_user_id());
								
								Map<String,Object> objectMap = new HashMap<String,Object>();
								objectMap.put("uId", users.getuId());
								objectMap.put("uName", users.getuName());
								objectMap.put("activeId", activeSignUpPay.getAsu_active_id()+"");
								objectMap.put("title", activeSignUpPay.getTitle());
								objectMap.put("orderId", activeSignUpPay.getAsu_id());
								
								messagePrivateServiceImpl.saveMessagePrivate(objectMap,user,"acPay");
								
							} catch (Exception e) {
								e.printStackTrace();
							}
						}else{
							activeApplicantLogServiceImpl.saveActiveApplicantLog(new String[]{""}, activeSignUpPay.getAsu_id(),activeSignUpPay.getAsu_active_id()+"", 0, "付款异常，付款金额与应付款金额不同，付款金额：￥"+money+"，应付款金额：￥"+activeSignUpPay.getAsu_account_payable(), activeSignUpPay.getAsu_user_id(), "1");
						}
					}
					
				}else if("pointService".equals(extra_common_param)){
					double money = Double.parseDouble(params.get("total_fee"));
					// 判断请支付金额 和 合作身份者ID
					if(money > 0 && (params.get("seller_id")+"").equals(AlipayConfig.partner) ){
						Alipay ap = new Alipay();
						
						//订单编号
						ap.setOut_trade_no(out_trade_no);
						
						// 保存支付宝交易号，退款用
						ap.setTrade_no(trade_no);
						
						// 付款时间
						ap.setPay_time(new Date());
						
						// 付款金额
						ap.setTotal_fee(money+"");
						
						double price = pointServiceOrderServiceImpl.findPointServiceOrderPriceById(out_trade_no);
						
						if(money==price){
							pointServiceOrderServiceImpl.updateOrderPayState(ap);
							pointServiceOrderServiceImpl.addPointServiceUniqueness(out_trade_no);
						}
					}
				}
				
				//注意：
				//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
			} /*else if (trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
					//如果有做过处理，不执行商户的业务程序
					
				//注意：
				//付款完成后，支付宝系统发送该交易状态通知
			}*/

			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				
			out.println("success");	//请不要修改或删除

			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			out.println("fail");
		}

	}
	
	/**
	 * 支付宝返回支付结果 同步通知
	 * @param mv
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/return")
	public ModelAndView returns(HttpServletRequest request,ModelAndView mv) throws Exception {
		
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

		String out_trade_no = this.getParameter("out_trade_no");

		//支付宝交易号

		String trade_no = this.getParameter("trade_no");

		//交易状态
		String trade_status = this.getParameter("trade_status");

		//自定义公用回传参数
		String extra_common_param = this.getParameter("extra_common_param");
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		if(AlipayNotify.verify(params)){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码
			

			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			
			if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
					//如果有做过处理，不执行商户的业务程序
				
				// 获取当前操作用户session
				HttpSession session = request.getSession();
				
				// 判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
				Alipay ap = (Alipay) session.getAttribute("pay_"+out_trade_no);

				if("active".equals(ap.getExtra_common_param())){
					//查询活动信息
					List<ActiveSignUpPay> activeSignUpPays = activeSignupServiceImpl.findAsuIdByAccountNum(out_trade_no);
					
					ActiveSignUpPay activeSignUpPay =  activeSignUpPays.get(0);
					
					// 判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
					if(ap != null && Double.parseDouble(params.get("total_fee")) > 0  
							&& (params.get("seller_id")+"").equals(AlipayConfig.partner) ){
						
						// 保存支付宝交易号，退款用
						ap.setTrade_no(trade_no);
						
						// 付款时间
						ap.setPay_time(new Date());
						
						// 付款金额
						double money = Double.parseDouble(params.get("total_fee"));
						
						// 每个订单对应的支付金额
						ap.setTotal_fee(money+"");
						
						// 判断业务类型
						switch (ap.getOutDoorPayType()) {
						
						// 活动报名业务
						case Const.PAY_ACTIVE:
							try {
								// 更改订单信息
								activeSignupServiceImpl.updateActiveSignUp(ap);
								
								//领队已确认报名
								if(activeSignUpPay.getAsu_state()==20){
									//是否强制购买保险
									if("1".equals(activeSignUpPay.getIsinsurance())){
										//订单创建用户邮箱
										String userEmail = usersServiceImpl.findUserEmailById(activeSignUpPay.getAsu_user_id());
										
										BaoXianren toubaoren = new BaoXianren();
										for(int i=0;i<activeSignUpPay.getActiveApplicant().size();i++){
											ActiveApplicant activeApplicant = activeSignUpPay.getActiveApplicant().get(i);
											if(activeApplicant.getAa_signup_linkman()==1){
												//活动订单号
												toubaoren.setAsu_id(activeApplicant.getAsu_id());
												//参加人员姓名
												toubaoren.setAa_user_name(activeApplicant.getAa_user_name());
												//投保人邮箱
												toubaoren.setEmail(userEmail);
												//参加人员性别（1：男；2：女）
												toubaoren.setAa_user_sex(activeApplicant.getAa_user_sex());
												//参加人员手机号码
												toubaoren.setAa_user_phone(activeApplicant.getAa_user_phone());
												//参加人员证件类型
												toubaoren.setAa_user_certificates_type(activeApplicant.getAa_user_certificates_type());
												//参加人员证件号码
												toubaoren.setAa_user_certificates_num(activeApplicant.getAa_user_certificates_num());
												//是否投保人（1：是；2：不是）
												toubaoren.setAa_signup_linkman(1);
											}
										}
										
										for(int i=0;i<activeSignUpPay.getActiveApplicant().size();i++){
											//保险信息
											List<BaoXianren> baoXianrenList = new ArrayList<BaoXianren>();
											ActiveApplicant activeApplicant = activeSignUpPay.getActiveApplicant().get(i);
											BaoXianren baoXianren = new BaoXianren();
											
											//活动订单号
											baoXianren.setAsu_id(activeApplicant.getAsu_id());
											//参加人员姓名
											baoXianren.setAa_user_name(activeApplicant.getAa_user_name());
											//投保人邮箱
											baoXianren.setEmail(userEmail);
											//参加人员性别（1：男；2：女）
											baoXianren.setAa_user_sex(activeApplicant.getAa_user_sex());
											//参加人员手机号码
											baoXianren.setAa_user_phone(activeApplicant.getAa_user_phone());
											//参加人员证件类型
											baoXianren.setAa_user_certificates_type(activeApplicant.getAa_user_certificates_type());
											//参加人员证件号码
											baoXianren.setAa_user_certificates_num(activeApplicant.getAa_user_certificates_num());
											//是否投保人（1：是；2：不是）
											baoXianren.setAa_signup_linkman(2);
											//订单ID
											baoXianren.setAsu_id(activeApplicant.getAsu_id());
											//联系人ID
											baoXianren.setAa_id(activeApplicant.getAa_id());
											
											baoXianrenList.add(toubaoren);
											baoXianrenList.add(baoXianren);
											
											//购买保险
											insurancesServiceImpl.buy(activeSignUpPay.getAsu_active_id(),activeSignUpPay.getIn_id(),activeSignUpPay.getAsu_user_id(),DateUtil.YYYY_MM_DDgetDay(activeSignUpPay.getActivityTime()), DateUtil.YYYY_MM_DDgetDay(activeSignUpPay.getEndTime()), baoXianrenList);
										}
										
										//订单应付金额
										activeSignUpPay.setAsu_account_payable(activeSignUpPay.getAsu_account_payable()-activeSignUpPay.getInsurance().getAcp_cost());
									}
									
									//新增资金明细
									cashRecordServiceImpl.saveCashRecordByActiveSignup(activeSignUpPay.getCreateUser(), activeSignUpPay.getAsu_reserve_price(), activeSignUpPay.getAsu_account_payable(), activeSignUpPay.getAsu_id());
									//更改发布者钱包信息
									balanceServiceImpl.updateMoneyByUserId(activeSignUpPay.getCreateUser(),activeSignUpPay.getAsu_reserve_price(),activeSignUpPay.getAsu_account_payable());
								}
								
								String aa_idstr = "";
								
								for(int i=0;i<activeSignUpPay.getActiveApplicant().size();i++){
									if(i!=0){
										aa_idstr+=",";
									}
									ActiveApplicant activeApplicant = activeSignUpPay.getActiveApplicant().get(i);
									aa_idstr += activeApplicant.getAa_id();
								}
								
								String[] aa_ids = aa_idstr.split(",");
								//钱包日志
								activeApplicantLogServiceImpl.saveActiveApplicantLog(aa_ids, activeSignUpPay.getAsu_id(),activeSignUpPay.getAsu_active_id()+"", 0, "付款成功，付款金额为￥"+(money/aa_ids.length), activeSignUpPay.getAsu_user_id(), "2");
								
								// 清除sesison中该订单的信息
								
								/**
								 * 发送消息（付款成功，给领队发送消息） 
								 */
								Users user = usersServiceImpl.findUserById(activeSignUpPay.getCreateUser());
								Users users = (Users) session.getAttribute(Const.SESSION_USER);
								
								Map<String,Object> objectMap = new HashMap<String,Object>();
								objectMap.put("uId", users.getuId());
								objectMap.put("uName", users.getuName());
								objectMap.put("activeId", activeSignUpPay.getAsu_active_id()+"");
								objectMap.put("title", activeSignUpPay.getTitle());
								objectMap.put("orderId", activeSignUpPay.getAsu_id());
								
								messagePrivateServiceImpl.saveMessagePrivate(objectMap,user,"acPay");
								
								session.removeAttribute(out_trade_no);
							} catch (Exception e) {
								e.printStackTrace();
							}
							break;
						}
					}
					
					mv.setViewName("redirect:/activeSignup/MySignUp.html");
				}else if("pointService".equals(extra_common_param)){
					// 判断请支付金额 和 合作身份者ID
					if(ap != null && Double.parseDouble(params.get("total_fee")) > 0 && (params.get("seller_id")+"").equals(AlipayConfig.partner) ){
						//订单编号
						ap.setOut_trade_no(out_trade_no);
						
						// 保存支付宝交易号，退款用
						ap.setTrade_no(trade_no);
						
						// 付款时间
						ap.setPay_time(new Date());
						
						// 付款金额
						ap.setTotal_fee(Double.parseDouble(params.get("total_fee"))+"");
						
						pointServiceOrderServiceImpl.updateOrderPayState(ap);
						
						pointServiceOrderServiceImpl.addPointServiceUniqueness(out_trade_no);
					}
				}
				
				
				//注意：
				//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
			} /*else if (trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
					//如果有做过处理，不执行商户的业务程序
					
				//注意：
				//付款完成后，支付宝系统发送该交易状态通知
			}*/

			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				
			System.out.println("success");	//请不要修改或删除

			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			System.out.println("fail");
		}

		
		return mv;
	}
	
}
