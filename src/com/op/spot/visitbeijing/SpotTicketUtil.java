package com.op.spot.visitbeijing;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cn.com.visitbeijing.e.api.TicketService;
import cn.com.visitbeijing.e.api.TicketService_Service;
import cn.com.visitbeijing.e.api.pack;

import com.op.plugin.sms.AlidayuSMS;
import com.op.plugin.sms.SmsTemplateCode;
import com.op.spot.OutTicketStatus;
import com.op.spot.entity.S_orderTravelPeople;
import com.op.util.Const;
import com.op.util.jedis.RedisUtil;


/**
 * 北京旅游网景点、景点门票工具类
 * @author WinZhong
 * 2016年9月26日10:59:05
 */
public class SpotTicketUtil {

	protected static Logger logger = Logger.getLogger(SpotTicketUtil.class);

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	//key
	private static String key = "c873729bea15268c1074571f53ce62bb";
	//合作伙伴编号
	private static String partnerCode = "distestonly";
	//合作伙伴Id
	private static String proxyId = "distestonly2015";
	////消息序列号
	private static final String messageId = "1";
	
	
	/**
	 * 门票下单
	 * @param tp
	 * @return
	 */
	public static S_orderTravelPeople submitOrder(S_orderTravelPeople tp){
		String timeStamp = sdf.format(new Date());
		String msg = key + messageId + partnerCode + timeStamp + tp.getOtp_id();
		String signed = org.apache.commons.codec.binary.Base64
				.encodeBase64String(pack.str2pack(DigestUtils.md5Hex(msg)));
		StringBuffer sb = new StringBuffer();
		sb.append("<request><head><version>1</version>");
		sb.append("<messageId>").append(messageId).append("</messageId>");
		sb.append("<partnerCode>").append(partnerCode).append("</partnerCode>");
		sb.append("<proxyId>").append(proxyId).append("</proxyId>");
		sb.append("<signed>").append(signed).append("</signed>");
		sb.append("<timeStamp>").append(timeStamp).append("</timeStamp>");
		sb.append("</head>");
		sb.append("<body>");

		sb.append("<order>");
			sb.append("<orderId>").append(tp.getOtp_id()).append("</orderId>");//商家订单编号
			sb.append("<productSn>").append(tp.getProductSn()).append("</productSn>");//产品编号
			sb.append("<productType>").append(tp.getProductType()).append("</productType>");//产品种类  默认为1,是普通产品；2为打包产品，（2.0新增）
			sb.append("<count>").append(tp.getCount()).append("</count>");//产品数量
			sb.append("<settlementPrice>").append(tp.getSettlementPrice()).append("</settlementPrice>");//结算价格  以分为单位
			sb.append("<isSendSms>0</isSendSms>");//是否发送短信 0、不发送  1、发送
			sb.append("<payType>1</payType>");//支付方式  1、在线支付
			sb.append("<inDate>").append(tp.getInDate()).append("</inDate>");//入园日期  格式yyyy-MM-dd
			sb.append("<validTimeBegin>").append(tp.getInDate()).append("</validTimeBegin>");//电子票开始时间 格式yyyy-MM-dd
			sb.append("<validTimeEnd>").append(tp.getInDate()).append("</validTimeEnd>");//电子票结束时间  格式yyyy-MM-dd
			sb.append("<user>");
				sb.append("<name>").append(tp.getVisitorName()).append("</name>");//客户姓名
				sb.append("<mobile>").append(tp.getVisitorPhone()).append("</mobile>");//客户电话
				sb.append("<sex>").append(tp.getSex()).append("</sex>");//客户性别  0:男,1:女
				sb.append("<idCard>").append(tp.getCardNum()).append("</idCard>");//身份证号
			sb.append("</user>");
		sb.append("</order>");
		
		sb.append("</body></request>");
		
		logger.info("门票订单号："+tp.getOrderId()+" 子订单号："+tp.getOtp_id()+" 请求详情："+sb);
		TicketService_Service ticketService_service = new TicketService_Service();
		TicketService ticketService = ticketService_service.getTicketServiceSOAP();
		String responseXml = ticketService.submitOrder(sb.toString());

	 
			Document document = null;
			try {
				document = DocumentHelper.parseText(responseXml);
				// 获取根元素
				Element root = document.getRootElement();
				// 获取名字为指定名称的第一个子元素
				Element body = root.element("body");
				Element result = body.element("result");
				boolean isSuccess = Boolean.parseBoolean(result.elementText("isSuccess"));
				tp.setIsSuccess(isSuccess);
				logger.info("操作是否成功:"+isSuccess);
				tp.setErrorCode(result.elementText("errorCode"));
				logger.info("错误码:"+result.elementText("errorCode"));
				tp.setErrorMsg(result.elementText("errorMsg"));
				logger.info("错误说明:"+result.elementText("errorMsg"));
				tp.setDealTime(result.elementText("dealTime"));
				logger.info("处理完成时间:"+result.elementText("dealTime"));
				if(isSuccess){
					Element order = body.element("order");
					logger.info("订单编号:"+order.elementText("orderId"));
					tp.setAgentOrderId(order.elementText("agentOrderId"));
					logger.info("北京旅游网订单编号:"+order.elementText("agentOrderId"));
					logger.info("结算价格 :"+order.elementText("settlementPrice"));
		        	//订单状态 2:等待出票
		        	tp.setStatus(2);
					try {
						tp.setCodeNumber(order.elementText("codeNumber"));
						logger.info("辅助码:"+order.elementText("codeNumber"));
			        	//订单状态3：已出票
			        	tp.setStatus(3);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						tp.setEmsContent(order.elementText("emsContent"));
						logger.info("短信内容:"+order.elementText("emsContent"));
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						String ticketTimeInfo = order.elementText("ticketTimeInfo");
						tp.setValidTimeBegin(ticketTimeInfo.split("\\|")[0]);
						tp.setValidTimeEnd(ticketTimeInfo.split("\\|")[1]);
						logger.info("电子票日期:"+order.elementText("ticketTimeInfo"));
					} catch (Exception e) {
						e.printStackTrace();
					}
					if(tp.getEmsContent() != null){
						AlidayuSMS.sendNotify(tp.getVisitorPhone(),tp.getEmsContent().replaceAll("入园凭证：", ""),SmsTemplateCode.SPOTTICKE);
					}
					//等待出票状态，加入队列，定时检测出票
					if(tp.getStatus() == 2){
						 RedisUtil.rpush(Const.CHECK_TICKET_QUEUE,tp.getAgentOrderId());
					}
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		
		return tp;
	}
	
	/**
	 * 申请退票
	 * @param tp
	 * @return
	 */
	public static boolean refundTicket(S_orderTravelPeople tp){
		String timeStamp = sdf.format(new Date());
		String msg = key + messageId + partnerCode + timeStamp + tp.getAgentOrderId();
		String signed = org.apache.commons.codec.binary.Base64
				.encodeBase64String(pack.str2pack(DigestUtils.md5Hex(msg)));
		StringBuffer sb = new StringBuffer();
		sb.append("<request><head><version>1</version>");
		sb.append("<messageId>").append(messageId).append("</messageId>");
		sb.append("<partnerCode>").append(partnerCode).append("</partnerCode>");
		sb.append("<proxyId>").append(proxyId).append("</proxyId>");
		sb.append("<signed>").append(signed).append("</signed>");
		sb.append("<timeStamp>").append(timeStamp).append("</timeStamp>");
		sb.append("</head>");
		sb.append("<body>");

		sb.append("<order>");
			sb.append("<orderId>").append(tp.getAgentOrderId()).append("</orderId>");//北京旅游网订单编号
			sb.append("<refundCount>").append(tp.getCount()).append("</refundCount>");//退票数量
		sb.append("</order>");
		
		sb.append("</body></request>");
		
		logger.info("门票订单号："+tp.getAgentOrderId()+" 请求详情："+sb);
		TicketService_Service ticketService_service = new TicketService_Service();
		TicketService ticketService = ticketService_service.getTicketServiceSOAP();
		String responseXml = ticketService.refundTicket(sb.toString());
			Document document = null;
			try {
				document = DocumentHelper.parseText(responseXml);
				// 获取根元素
				Element root = document.getRootElement();
				// 获取名字为指定名称的第一个子元素
				Element body = root.element("body");
				Element result = body.element("result");
				boolean isSuccess = Boolean.parseBoolean(result.elementText("isSuccess"));
				tp.setIsSuccess(isSuccess);
				logger.info("操作是否成功:"+isSuccess);
				tp.setErrorCode(result.elementText("errorCode"));
				logger.info("错误码:"+result.elementText("errorCode"));
				tp.setErrorMsg(result.elementText("errorMsg"));
				logger.info("错误说明:"+result.elementText("errorMsg"));
				tp.setDealTime(result.elementText("dealTime"));
				logger.info("处理完成时间:"+result.elementText("dealTime"));
				if(isSuccess){
					Element order = body.element("order");
					logger.info("订单编号:"+order.elementText("orderId"));
					order.elementText("orderId");
					tp.setStatus(11);
					return true;
				}else{
					tp.setStatus(12);
					return false;
				}
			} catch (DocumentException e) {
				e.printStackTrace();
				return false;
			}
		
	}
	
	/**
	 * 查询订单接口
	 * @param orderId 北京旅游网订单编号
	 * @return
	 */
	public static int queryOrder(String orderId){
		String timeStamp = sdf.format(new Date());
		String msg = key + messageId + partnerCode + timeStamp + orderId;
		String signed = org.apache.commons.codec.binary.Base64
				.encodeBase64String(pack.str2pack(DigestUtils.md5Hex(msg)));
		StringBuffer sb = new StringBuffer();
		sb.append("<request><head><version>1</version>");
		sb.append("<messageId>").append(messageId).append("</messageId>");
		sb.append("<partnerCode>").append(partnerCode).append("</partnerCode>");
		sb.append("<proxyId>").append(proxyId).append("</proxyId>");
		sb.append("<signed>").append(signed).append("</signed>");
		sb.append("<timeStamp>").append(timeStamp).append("</timeStamp>");
		sb.append("</head>");
		sb.append("<body>");

		sb.append("<order>");
			sb.append("<orderId>").append(orderId).append("</orderId>");//北京旅游网订单编号
		sb.append("</order>");
		
		sb.append("</body></request>");
		TicketService_Service ticketService_service = new TicketService_Service();
		TicketService ticketService = ticketService_service.getTicketServiceSOAP();
		String responseXml = ticketService.queryOrder(sb.toString());

	 
			Document document = null;
			try {
				document = DocumentHelper.parseText(responseXml);
				// 获取根元素
				Element root = document.getRootElement();
				// 获取名字为指定名称的第一个子元素
				Element body = root.element("body");
				Element result = body.element("result");
				System.out.println("操作是否成功:"+result.elementText("isSuccess"));
				System.out.println("错误码:"+result.elementText("errorCode"));
				System.out.println("错误说明:"+result.elementText("errorMsg"));
				System.out.println("处理完成时间:"+result.elementText("dealTime"));
				boolean isSuccess = Boolean.parseBoolean(result.elementText("isSuccess"));
				if(isSuccess){
					Element order = body.element("order");
					System.out.println("订单编号:"+order.elementText("orderId"));
					System.out.println("北京旅游网订单编号:"+order.elementText("agentOrderId"));
					System.out.println("产品编号 :"+order.elementText("productSn"));
					System.out.println("产品种类 :"+order.elementText("productType"));
					System.out.println("产品数量 :"+order.elementText("count"));
					System.out.println("结算价格 :"+order.elementText("settlementPrice"));//以分为单位
					System.out.println("订单金额 :"+order.elementText("orderAmount"));
					System.out.println("订单状态 :"+order.elementText("status"));
					System.out.println("下单时间 :"+order.elementText("orderTime"));
					System.out.println("支付方式 :"+order.elementText("payType"));
					System.out.println("辅助码:"+order.elementText("codeNumber"));
					System.out.println("客户电话:"+order.elementText("mobile"));
					System.out.println("已验证数量:"+order.elementText("usedNum"));
					/** 
					 * 北京旅游网订单状态说明
					 * 订单状态	说明
						0	未确认
						1	已确认
						2	已取消
						3	无效
						4	已退票
						5	已分单             此为最终出票成功状态
						6	预订失败
						7	审核退款
						8	部分审核退款
						9	部分退票
						10	已处理
						11	拒绝退票
						12	不处理
					 */
					String status = order.elementText("status");
					if("5".equals(status)){
						return OutTicketStatus.STATUS_3;
					}else if("6".equals(status)){
						return OutTicketStatus.STATUS_13;
					}
					
				}else{
					return OutTicketStatus.STATUS_2;
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			return OutTicketStatus.STATUS_2;

	 
	}
	
	public static void main(String[] args) {
		//S_orderTravelPeople tp = new S_orderTravelPeople();
		//tp.setAgentOrderId("20160926141335187");
/*		tp.setProductSn("LD201603310001");
		tp.setVisitorPhone("15010322573");
*/		//tp.setCount(1);
		//SpotTicketUtil.refundTicket(tp);
		
		System.out.println(SpotTicketUtil.queryOrder("20160930110006195"));
	}

	public static String getKey() {
		return key;
	}

	public static void setKey(String key) {
		SpotTicketUtil.key = key;
	}

	public static String getPartnerCode() {
		return partnerCode;
	}

	public static void setPartnerCode(String partnerCode) {
		SpotTicketUtil.partnerCode = partnerCode;
	}

	public static String getProxyId() {
		return proxyId;
	}

	public static void setProxyId(String proxyId) {
		SpotTicketUtil.proxyId = proxyId;
	}

	public static String getMessageid() {
		return messageId;
	}

	
	
	
	
	
	
}
