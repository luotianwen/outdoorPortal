package com.op.controller.usercenter.keeper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.op.controller.BaseController;
import com.op.dto.activity.activeSignUpPay.ActiveSignUpPay;
import com.op.dto.activity.queryActivity.QueryActivity;
import com.op.dto.insurance.BaoXianren;
import com.op.dto.usercenter.leader.activeManager.activeSignUp.LeaderSignUp;
import com.op.dto.usercenter.leader.activeManager.activeSignUp.MySignUp;
import com.op.entity.activity.Activity;
import com.op.entity.alipay.Alipay;
import com.op.entity.insurance.Policyholders;
import com.op.entity.usercenter.ActiveApplicant;
import com.op.entity.usercenter.ActiveSignUpCorrelationCost;
import com.op.entity.usercenter.ActiveSignup;
import com.op.entity.usercenter.LinkSignUpUser;
import com.op.entity.users.Users;
import com.op.plugin.page.Page;
import com.op.service.activity.ActivityService;
import com.op.service.balance.BalanceService;
import com.op.service.balance.CashRecordService;
import com.op.service.insurance.InsurancesService;
import com.op.service.insurance.PolicyholdersService;
import com.op.service.messageprivate.MessagePrivateService;
import com.op.service.usercenter.ActiveApplicantLogService;
import com.op.service.usercenter.ActiveApplicantService;
import com.op.service.usercenter.ActiveSignUpCorrelationCostService;
import com.op.service.usercenter.ActiveSignupService;
import com.op.service.usercenter.ComplaintLeadService;
import com.op.service.usercenter.LinkSignUpUserService;
import com.op.service.usercenter.SignupStateService;
import com.op.service.users.UsersService;
import com.op.util.Const;
import com.op.util.DateUtil;

/**
 * 我的报名信息实体类
 * @author PYW
 * Date: 2015年12月18日 09:21:22
 */
@RequestMapping("/activeSignup")
@Controller
public class ActiveSignupController extends BaseController {
	// 报名订单
	@Resource(name="activeSignupServiceImpl")
	private ActiveSignupService activeSignupServiceImpl;
	
	// 报名状态
	@Resource(name="signupStateServiceImpl")
	private SignupStateService signupStateServiceImpl;
	
	//活动信息
	@Resource(name="activityServiceImpl")
	private ActivityService activityServiceImpl;
	
	// 投诉领队
	@Resource(name="complaintLeadServiceImpl")
	private ComplaintLeadService complaintLeadServiceImpl;

	//常用联系人
	@Resource(name="linkSignUpUserServiceImpl")
	private LinkSignUpUserService linkSignUpUserServiceImpl;
		
	//订单报名人
	@Resource(name="activeApplicantServiceImpl")
	private ActiveApplicantService activeApplicantServiceImpl;
	
	//操作日志
	@Resource(name="activeApplicantLogServiceImpl")
	private ActiveApplicantLogService activeApplicantLogServiceImpl;
	
	//用户
	@Resource(name="usersServiceImpl")
	private UsersService usersServiceImpl;
	
	//用户资金账户余额
	@Resource(name="balanceServiceImpl")
	private BalanceService balanceServiceImpl;
	
	//用户资金明细
	@Resource(name="cashRecordServiceImpl")
	private CashRecordService cashRecordServiceImpl;
	
	//用户资金明细
	@Resource(name="activeSignUpCorrelationCostServiceImpl")
	private ActiveSignUpCorrelationCostService activeSignUpCorrelationCostServiceImpl;
	
	//站内信
	@Resource(name="messagePrivateServiceImpl")
	private MessagePrivateService messagePrivateServiceImpl;
	
	//购买保险
	@Resource(name="insurancesServiceImpl")
	private InsurancesService insurancesServiceImpl;
	
	//保单信息
	@Resource(name="policyholdersServiceImpl")
	private PolicyholdersService policyholdersServiceImpl;
	
	
	/**
	 * 方法描述：提交订单
	 * @throws Exception 
	 */
	@RequestMapping(value="/tijiaodingdan",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> submitorder(ActiveSignup activeSignup,HttpSession session){
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
			//当前登录用户
			Users users = this.getSessionUser();
			if(users.getuType()==3){
				//报名人
				List<LinkSignUpUser> linkSignUpUser = new ArrayList<LinkSignUpUser>();
				linkSignUpUser = activeSignup.getLinkSignUpUser();
					
				QueryActivity queryActivity = new QueryActivity();
				queryActivity = activityServiceImpl.orderById(activeSignup.getAsu_active_id());
				
				// 判断活动是否符合要求
				Activity a = new Activity();
				// 活动状态
				a.setState(queryActivity.getState());
				// 报名截止时间
				a.setA_enroll_end_time(queryActivity.getA_enroll_end_time());
				// 需要人数
				a.setNeedUserNum(queryActivity.getNeedUserNum());
				// 已确认人数
				a.setConfirmUserNum(queryActivity.getConfirmUserNum());
				
				if(!checkActivity(a, map, linkSignUpUser.size(), new Date())){
					return map;
				}
					
				//新增订单
				activeSignup.setAsu_user_id(users.getuId());
				activeSignup.setAsu_type(1);
				
				String asu_id = activeSignupServiceImpl.saveOrder(activeSignup,queryActivity,map,users);
				
				if(!"false".equals(asu_id)){
					//新增订单报名人
					String[] aa_ids = activeApplicantServiceImpl.saveActiveApplicant(linkSignUpUser,users,activeSignup);
				
					//操作日志
					activeApplicantLogServiceImpl.saveActiveApplicantLog(aa_ids, asu_id+"", activeSignup.getAsu_active_id(), 1, "报名成功", users.getuId(), "10");
					
					//修改用户的紧急联系人
					users.setEmergencyName(activeSignup.getAsu_user_emergency());
					users.setEmergencyMobile(activeSignup.getAsu_user_emergency_phone());
					users.setRelation(activeSignup.getAsu_user_relation());
					
					usersServiceImpl.updateUsersEmergency(users);
					
					session.setAttribute(Const.SESSION_USER, users);
					
					map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
					map.put(Const.SUCCESS_INFO, "报名成功，正在跳转付款页面。。。");
					map.put("asu_id", asu_id);
					
					/**
					 * 发送消息（报名成功，给领队发送消息）
					 */
					Users user = usersServiceImpl.findUserById(queryActivity.getCreateUser());
					Map<String,Object> objectMap = new HashMap<String,Object>();
					objectMap.put("uId", users.getuId());
					objectMap.put("uName", users.getuName());
					objectMap.put("activeId", queryActivity.getId()+"");
					objectMap.put("title", queryActivity.getTitle());
					
					messagePrivateServiceImpl.saveMessagePrivate(objectMap,user,"acSignup");
					
				}
			}else{
				map.put(Const.RESPONSE_STATE, "500");
				map.put(Const.ERROR_INFO, "该账号类型暂不能报名活动！");
			}

		} catch (Exception e) {
			logger.error("!!!!!!!!!提交订单异常!!!!!!!!!",e);
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, Const.ERROR_INFO_STR);
		}
		return map;
	}

	/**
	 * 免费活动跳转报名成功页面
	 * @param mv
	 * @param asu_id
	 * @param activeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="signUpFree")
	@ResponseBody
	public ModelAndView signUpFree(ModelAndView mv,String asu_id,String activityId) throws Exception{
		// 查询该活动简介信息
		QueryActivity queryActivity = new QueryActivity();
		queryActivity = activityServiceImpl.orderById(activityId);
		
		if(queryActivity!=null){
			if(queryActivity.getActivityTime()!=null){
				queryActivity.setActivityTimeStr(DateUtil.YYYY_MM_DDgetDay(queryActivity.getActivityTime()));
			}
			if(queryActivity.getEndTime()!=null){
				queryActivity.setEndTimeStr(DateUtil.YYYY_MM_DDgetDay(queryActivity.getEndTime()));
			}
			if(queryActivity.getA_gather_time()!=null){
				queryActivity.setGatherTimeStr(DateUtil.YYYY_MM_DDgetDay(queryActivity.getA_gather_time()));
			}
			
			Users users = this.getSessionUser();
			mv.addObject("user", users);
			mv.addObject("asu_id",asu_id);
			mv.addObject("queryActivity",queryActivity);
			if(queryActivity.getPaytype()!=2){
				mv.setViewName("activity/order/signup-free");
			}else{
				mv.setViewName("activity/order/signup-success");
			}
			
			
			Map<String,String> map = new HashMap<String,String>();
			// 当前导航标签
			map.put("ht", "活动");
			mv.addObject("map", map);
			
		}else{
			mv.setViewName("redirect:/404.html");
		}
		
		return mv;
	}
	
	/**
	 * 方法描述：点击立即付款跳转【选择支付接口】页面
	 * 返回类型：ModelAndView
	 * @param mv
	 * @param asu_id	已存在的支付订单号
	 * @return
	 */
	@RequestMapping(value="toImmediatePay")
	@ResponseBody
	public Map<String,Object> toImmediatePay(ModelAndView mv
			,@RequestParam(value="asu_id",required=false) String asu_id
			,HttpSession session){
		// 返回对象
		Map<String,Object> map = new HashMap<String,Object>();
		
		// 查询订单关联的活动ID
		// 此处不采用前台传过来的活动ID，防止用户恶意修改
		ActiveSignUpPay dto = new ActiveSignUpPay();
		try {
			// 查询该订单对应的活动信息
			dto = activeSignupServiceImpl.findActivitySignUpPay(asu_id);
			
			// 是否已经删除(0：默认；1：是；)
			if(dto.getAsu_is_delete() == 1){
				map.put(Const.ERROR_INFO, "查找不到该订单！");
				map.put(Const.RESPONSE_STATE, 500);
			}else{
				// 应支付的金额
				double average = 0;
				if(dto.getAsu_price_type()!=2){
					average = dto.getAsu_account_payable();
				}else{
					average = dto.getAsu_reserve_price();
				}
				
				// 创建session支付数据
				getout_trade_no(dto.getAsu_active_id(),average,session,asu_id,dto.getTitle());
				
				map.put("out_trade_no", asu_id);
				map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
			
			}	
		} catch (Exception e) {
			e.printStackTrace();
			map.put(Const.ERROR_INFO, "查询关联活动异常，请稍后重试！");
			map.put(Const.RESPONSE_STATE, 500);
		}
		
		return map;
	}
	
	/**
	 * 立即付款
	 * @param asu_id
	 * @param activeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="immediatePay")
	@ResponseBody
	public ModelAndView immediatePay(String asu_id,String activeId,ModelAndView mv,HttpSession session) throws Exception{
		// 查询该活动简介信息
		QueryActivity queryActivity = new QueryActivity();
		queryActivity = activityServiceImpl.orderById(activeId);
		
		if(queryActivity.getActivityTime()!=null){
			queryActivity.setActivityTimeStr(DateUtil.YYYY_MM_DDgetDay(queryActivity.getActivityTime()));
		}
		if(queryActivity.getEndTime()!=null){
			queryActivity.setEndTimeStr(DateUtil.YYYY_MM_DDgetDay(queryActivity.getEndTime()));
		}
		if(queryActivity.getA_gather_time()!=null){
			queryActivity.setGatherTimeStr(DateUtil.YYYY_MM_DDgetDay(queryActivity.getA_gather_time()));
		}
		
		Users users = this.getSessionUser();
		
		// 查询订单关联的活动ID
		// 此处不采用前台传过来的活动ID，防止用户恶意修改
		ActiveSignUpPay dto = new ActiveSignUpPay();
		try {
			// 查询该订单对应的活动信息
			dto = activeSignupServiceImpl.findActivitySignUpPay(asu_id);
			
			// 应支付的金额
			double average = 0;
			if(dto.getAsu_price_type()!=2){
				average = dto.getAsu_account_payable();
			}else{
				average = dto.getAsu_reserve_price();
			}
			
			// 创建session支付数据
			getout_trade_no(dto.getAsu_active_id(),average,session,asu_id,dto.getTitle());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setHeaderMap(mv);
		mv.addObject("user", users);
		mv.addObject("queryActivity", queryActivity);
		mv.addObject("dto", dto);
		mv.addObject("asu_id", asu_id);
		mv.setViewName("activity/order/paysignup");
		return mv;
	}
	
	/**
	 * 方法描述：设置支付页面需要的数据
	 * 返回类型：void
	 * @param mv
	 */
	public void setHeaderMap(ModelAndView mv){
		Map<String,String> map = new HashMap<String,String>();
		// 当前导航标签
		map.put("ht", "活动");
		mv.addObject("map", map);
	}
	
	/**
	 * 方法描述：跳转【选择支付接口】页面
	 * 返回类型：ModelAndView
	 * @param mv
	 * @param asu_id	不存在支付订单号，要根据订单ID重新生成支付信息
	 * @return
	 */
	@RequestMapping(value="payOrder")
	@ResponseBody
	public Map<String,Object> payOrder(ModelAndView mv
			,@RequestParam(value="asu_id",required=false) String asu_id
			,HttpSession session){
		// 返回对象
		Map<String,Object> map = new HashMap<String,Object>();
		
		// 如果订单号为空，订单ID不为空，入口来源则为【我的报名】对订单进行支付操作
		if(!StringUtils.isEmpty(asu_id)){
			
			// 查询订单关联的活动ID
			// 此处不采用前台传过来的活动ID，防止用户恶意修改
			ActiveSignUpPay dto = new ActiveSignUpPay();
			try {
				// 查询该订单对应的活动信息
				dto = activeSignupServiceImpl.findActivitySignUpPay(asu_id);
				
				// 是否已经删除(0：默认；1：是；)
				if(dto.getAsu_is_delete() == 1){

					map.put(Const.ERROR_INFO, "查找不到该订单！");
					map.put(Const.RESPONSE_STATE, 500);
				}else{
					// 应支付的金额
					double average = 0;
					if(dto.getAsu_price_type()!=2){
						average = dto.getAsu_account_payable();
					}else{
						average = dto.getAsu_reserve_price();
					}
					
					// 判断活动是否符合要求
					Activity a = new Activity();
					// 活动状态
					a.setState(dto.getState());
					// 报名截止时间
					a.setA_enroll_end_time(dto.getA_enroll_end_time());
					// 需要人数
					a.setNeedUserNum(dto.getNeedUserNum());
					// 已确认人数
					a.setConfirmUserNum(dto.getConfirmUserNum());
					
					if(checkActivity(a, map, dto.getActiveApplicant().size(), new Date())){
						// 创建session支付数据
						getout_trade_no(dto.getAsu_active_id(),average,session,asu_id,dto.getTitle());
						
						map.put("out_trade_no", asu_id);
						map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
					}
				}	
			} catch (Exception e) {
				e.printStackTrace();
				map.put(Const.ERROR_INFO, "查询关联活动异常，请稍后重试！");
				map.put(Const.RESPONSE_STATE, 500);
			}
		}else{
			map.put("out_trade_no", asu_id);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		}
		
		return map;
	}
	
	/**
	 * 活动验证
	 * @param a 活动实体
	 * @param map
	 * @param num 报名人数
	 * @param cd 报名时间
	 * @return
	 */
	public boolean checkActivity(Activity a,Map<String, Object> map,int num,Date cd){
		map.put(Const.RESPONSE_STATE, "500");// 失败标识
		// 活动状态（1：草稿；2：发布待审核；3：审核中；4：审核不通过；5：审核成功（发布））
		if(a.getState() != 5){
			String alert="";
			
			switch (a.getState()) {
			
			case 1:
				alert="草稿";
				break;
				
			case 2:
				alert="发布待审核";
				break;
				
			case 3:
				alert="审核中";
				break;
				
			case 4:
				alert="审核不通过";
				break;
			}
			map.put(Const.ERROR_INFO, "该活动状态为"+alert+"；请选择审核成功的活动进行报名!");
			return false;
		}
		
		// 截止时间判断
		if(cd.getTime()-a.getA_enroll_end_time().getTime()>0){
			map.put(Const.ERROR_INFO, "该活动已经截止报名!");
			return false;
		}
		
		// 剩余人数判断
		if(num > (a.getNeedUserNum()-a.getConfirmUserNum())){
			map.put(Const.ERROR_INFO, "报名人数超过该活动剩余人数上限。剩余报名人数为["+(a.getNeedUserNum()-a.getConfirmUserNum())+"]位!");
			return false;
		}
		
		return true;
	}
	
	/**
	 * 方法描述：
	 * 返回类型：String
	 * @param aId	活动ID
	 * @param average	应该支付的金额
	 * @param session	session
	 * @param  asu_id 订单ID
	 * @param 
	 * @return
	 */
	private String getout_trade_no(int aId,double average,HttpSession session,String asu_id,String title){
		Alipay ap = new Alipay();
		
		// 商户订单号 商户网站订单系统中唯一订单号
		ap.setOut_trade_no(asu_id);
		
		// 订单名称
		ap.setSubject(title);
		
		// 订单订单描述
		ap.setBody("活动报名费用");
		
		// 商品地址(ID)
		ap.setShow_url(aId+"");
		
		// 支付订单号对应的金额；key为对应的支付订单号，值为支付的金额
		ap.setTotal_fee(average+"");
		
		// 商家支付业务类型为活动报名
		ap.setOutDoorPayType(Const.PAY_ACTIVE);
		
		//自定义公用回传参数
		ap.setExtra_common_param("active");
		
		// 放入session，谨防修改; key为对应的支付订单号，值为支付的对象
		session.setAttribute("pay_"+asu_id, ap);
		
		return asu_id;
		
	}
	
	/**
	 * 我的订单
	 * @param order 订单状态（0：全部订单；1：已支付；2：等待付款；3：退款处理中；4：退款完成;5：已关闭；）
	 */
	@RequestMapping("/MySignUp")
	public ModelAndView selectActive(Page<Map<String,Object>> page,ModelAndView mv,HttpSession session,String order,MySignUp mySignUp,String type) throws Exception{
		Users users = this.getSessionUser();
		if(users.getuType()==3){
			userSignUp(page, mv, session, order,users);
		}else if(users.getuType()!=3){
			leaderSignupsManager(session, mv, page, mySignUp, type, users);
		}
		
		return mv;
	}
	
	/**
	 * 普通用户查看订单
	 * @throws Exception 
	 */
	public void userSignUp(Page<Map<String,Object>> page,ModelAndView mv,HttpSession session,String order,Users users) throws Exception{
		if(null==order||"".equals(order)){
			order = "0";
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", users.getuId());
		map.put("order", order);
		
		page.setT(map);
		
		List<MySignUp> mySignUps= activeSignupServiceImpl.findMySignUpByUserId(page);
		
		mv.addObject("mySignUps",replace(mySignUps,order,"1"));
		
		//我的订单总数
		Map<String,String> countmap = new HashMap<String,String>();
		countmap = activeSignupServiceImpl.countMySignUpByUserId(users.getuId());
		
		mv.addObject("alreadypaid",countmap!=null?countmap.get("ALREADYPAY"):0);//已支付
		mv.addObject("waitpay", countmap!=null?countmap.get("WAITPAY"):0);//等待付款
		mv.addObject("refunding", countmap!=null?countmap.get("REFUNDING"):0);//退款处理中
		mv.addObject("refundsuccess", countmap!=null?countmap.get("REFUNDSUCCESS"):0);//退款完成
		mv.addObject("close", countmap!=null?countmap.get("CLOSE"):0);//已关闭
		 
		//页面设置
		setUserCenterHeaderMap(mv, "我的管家","活动订单");
		
		//订单类型
		mv.addObject("order",order);
		mv.addObject("page", page);
		mv.setViewName("usercenter/housekeeper/sigunUp/my-order");
	}
	
	/**
	 * 方法描述： 领队查看订单
	 * 返回类型：ModelAndView
	 * @param session
	 * @param mv
	 * @return
	 */
	public void leaderSignupsManager(HttpSession session,ModelAndView mv
			,Page<Map<String,Object>> page,MySignUp mySignUp,String type,Users users)throws Exception{
		if(null==type||"".equals(type)){
			type = "0";
		}
		
		// 当前登录用户ID
		Map<String,Object> map = new HashMap<String,Object>();
		mySignUp.setCreateUser(users.getuId());
		map.put("createUser",users.getuId());
		map.put("type", type);
		page.setPageSize(5);
		page.setT(map);
		
		// 该活动对应的报名详情
		List<LeaderSignUp> leaderSignUp = activeSignupServiceImpl.leaderActiveManagerPage(page);
		
		for(int i=0;i<leaderSignUp.size();i++){
			Page<MySignUp> mysignupPage = new Page<MySignUp>();
			mySignUp.setActivityId(leaderSignUp.get(i).getActivityId());
			mysignupPage.setT(mySignUp);
			mysignupPage.setPageSize(3);
			
			List<MySignUp> mySignUps = activeSignupServiceImpl.leaderActiveSignupManagerPage(mysignupPage);
			leaderSignUp.get(i).setMySignUp(replace(mySignUps,"0","2"));
			leaderSignUp.get(i).setMysignupPage(mysignupPage);
			leaderSignUp.get(i).setPageStr(getPageStr(mysignupPage));
		}
		
		map.clear();
		map.put("userId", users.getuId());
		Map<String,String> count = activeSignupServiceImpl.leaderActiveManagerCount(map);
		
		mv.addObject("wait", count!=null?count.get("WAIT")!=null?count.get("WAIT"):0:0);
		mv.addObject("stop", count!=null?count.get("STOP")!=null?count.get("STOP"):0:0);
		mv.addObject("ing", count!=null?count.get("ING")!=null?count.get("ING"):0:0);
		mv.addObject("leaderSignUp",leaderSignUp);
		mv.addObject("mySignUp",mySignUp);
		mv.addObject("page", page);
		
		//页面设置
		setUserCenterHeaderMap(mv, "我的管家","活动订单");
		
		mv.addObject("type",type);
		mv.setViewName("usercenter/leader/housekeeper/activeSignUp/leader-order");
	}
	
	/**
	 * 我的订单显示内容
	 * @param mySignUps List<MySignUp>
	 * @param mv ModelAndView
	 * @param order 订单状态
	 * @param type （1：用户；2：领队）
	 * @return
	 */
	public List<MySignUp> replace(List<MySignUp> mySignUps,String order,String type){
		List<MySignUp> newmySignUps = new ArrayList<MySignUp>();
		
		MySignUp mySignUp = new MySignUp(); 
		for(int i=0;i<mySignUps.size();i++){
			mySignUp = mySignUps.get(i);
			
			signUpState(mySignUp,type);
			
			//活动状态(1：活动未开始；2：已截止报名；3：活动已开始；4：活动已结束)
			Date date = new Date();
			
			if(mySignUp.getActivitytime().getTime()>date.getTime()){
				if(mySignUp.getA_enroll_end_time().getTime()>date.getTime()){
					mySignUp.setActivityState("1");
				}else{
					mySignUp.setActivityState("2");
				}
			}else{
				if(mySignUp.getEndtime().getTime()>date.getTime()){
					mySignUp.setActivityState("3");
				}else{
					mySignUp.setActivityState("4");
				}
			}

			if(mySignUp.getState().equals(order)||"0".equals(order)){
				newmySignUps.add(mySignUp);
			}
		}
		
		return newmySignUps;
	}
	
	/**
	 * 订单状态
	 * @param mySignUp
	 * @param type （1：用户；2：领队）
	 */
	public void signUpState(MySignUp mySignUp,String type){
		//正常交易中报名人
		List<ActiveApplicant> activeApplicantNormal = new ArrayList<ActiveApplicant>();
		//退款中报名人
		List<ActiveApplicant> activeApplicantRefunding = new ArrayList<ActiveApplicant>();
		//领队已同意退款的报名人
		List<ActiveApplicant> activeApplicantRefund = new ArrayList<ActiveApplicant>();
		//申请客服
		List<ActiveApplicant> activeApplicantRefundfail = new ArrayList<ActiveApplicant>();
		
		if(mySignUp.getAsu_pay_state()==1){
			if(mySignUp.getAsu_state()==10){
				mySignUp.setState("2");
				mySignUp.setAsu_stateStr("等待支付（等待领队确认）");
			}else if(mySignUp.getAsu_state()==20){
				mySignUp.setState("2");
				mySignUp.setAsu_stateStr("等待支付(领队已确认)");
			}else if(mySignUp.getAsu_state()==30){
				mySignUp.setState("1");
				mySignUp.setAsu_stateStr("交易完成");
			}else if(mySignUp.getAsu_state()==40){
				mySignUp.setState("5");
				mySignUp.setAsu_stateStr("已关闭");
			}else if(mySignUp.getAsu_state()==50){
				mySignUp.setState("2");
				mySignUp.setAsu_stateStr("等待支付（等待领队确认取消）");
			}
			int state = 0;
			for(int i=0;i<mySignUp.getActiveApplicants().size();i++){
				ActiveApplicant activeApplicant = mySignUp.getActiveApplicants().get(i);
				if(activeApplicant.getAa_signup_linkman()==1){
					mySignUp.setSignUpLinkman(activeApplicant.getAa_user_name());
					mySignUp.setLinkmanPhone(activeApplicant.getAa_user_phone());
				}
				if(activeApplicant.getAa_state()==10){
					if(state!=1){
						state = 2;
						mySignUp.setState("2");
						mySignUp.setAsu_stateStr("等待支付（等待领队确认）");
					}
					activeApplicant.setState("2");
					activeApplicant.setAa_state_str("等待支付（等待领队确认）");
					activeApplicantNormal.add(activeApplicant);
				}else if(activeApplicant.getAa_state()==20){
					if(state>2||state==0){
						state = 3;
						mySignUp.setState("2");
						mySignUp.setAsu_stateStr("等待支付（领队已确认）");
					}
					activeApplicant.setState("2");
					activeApplicant.setAa_state_str("等待支付（领队已确认）");
					
					activeApplicantNormal.add(activeApplicant);
				}else if(mySignUp.getAsu_state()==30){
					state = 1;
					mySignUp.setState("1");
					mySignUp.setAsu_stateStr("交易完成");
					activeApplicant.setState("1");
					activeApplicant.setAa_state_str("交易完成");
					if("1".equals(type)){
						activeApplicantRefunding.add(activeApplicant);
					}else{
						activeApplicantNormal.add(activeApplicant);
					}
				}else if(activeApplicant.getAa_state()==40){
					if(state>4||state==0){
						state = 5;
						mySignUp.setState("5");
						mySignUp.setAsu_stateStr("已关闭");
					}
					activeApplicant.setState("5");
					activeApplicant.setAa_state_str("已关闭");
					if("1".equals(type)){
						activeApplicantRefunding.add(activeApplicant);
					}else{
						activeApplicantNormal.add(activeApplicant);
					}
				}else if(activeApplicant.getAa_state()==50){
					if(state>3||state==0){
						state = 4;
						mySignUp.setState("2");
						mySignUp.setAsu_stateStr("等待支付（等待领队确认取消）");
					}
					activeApplicant.setState("2");
					activeApplicant.setAa_state_str("等待支付（等待领队确认取消）");
					if("1".equals(type)){
						activeApplicantNormal.add(activeApplicant);
					}else{
						activeApplicantRefunding.add(activeApplicant);
					}
				}
			}
		}else{
			double refund = 0;
			int state = 0;

			for(int i=0;i<mySignUp.getActiveApplicants().size();i++){
				ActiveApplicant activeApplicant = mySignUp.getActiveApplicants().get(i);
				if(activeApplicant.getAa_signup_linkman()==1){
					mySignUp.setSignUpLinkman(activeApplicant.getAa_user_name());
				}
				
				if(activeApplicant.getAa_refund_flag()==0){
					if(mySignUp.getAsu_state()==10){
						if(state!=1){
							state = 2;
							mySignUp.setState("1");
							mySignUp.setAsu_stateStr("已支付（等待领队确认）");
						}
						activeApplicant.setState("1");
						activeApplicant.setAa_state_str("已支付（等待领队确认）");
						activeApplicantNormal.add(activeApplicant);
					}else if(mySignUp.getAsu_state()==20){
						if(state!=2){
							state = 3;
							mySignUp.setState("1");
							mySignUp.setAsu_stateStr("已支付（领队已确认）");
						}
						activeApplicant.setState("1");
						activeApplicant.setAa_state_str("已支付（领队已确认）");
						activeApplicantNormal.add(activeApplicant);
					}else if(mySignUp.getAsu_state()==30){
						state = 1;
						mySignUp.setState("1");
						mySignUp.setAsu_stateStr("交易完成");
						activeApplicant.setState("1");
						activeApplicant.setAa_state_str("交易完成");
						if("1".equals(type)){
							activeApplicantRefunding.add(activeApplicant);
						}else{
							activeApplicantNormal.add(activeApplicant);
						}
					}else if(mySignUp.getAsu_state()==40){
						state = 1;
						mySignUp.setState("5");
						mySignUp.setAsu_stateStr("已关闭");
						activeApplicant.setState("5");
						activeApplicant.setAa_state_str("已关闭");
						activeApplicantRefunding.add(activeApplicant);
					}else if(mySignUp.getAsu_state()==50){
						state = 1;
						mySignUp.setState("5");
						mySignUp.setAsu_stateStr("已支付（等待领队确认取消）");
						activeApplicant.setState("5");
						activeApplicant.setAa_state_str("已支付（等待领队确认取消）");
						
						activeApplicantRefunding.add(activeApplicant);
					}
				}else{
					String statestr = "";
					if(mySignUp.getAsu_account_paid()>0){
						statestr = "退款";
					}else{
						statestr = "取消";
					}
					
					if(activeApplicant.getAa_refund_state()==110){
						if(mySignUp.getAsu_state()==10||mySignUp.getAsu_state()==20||mySignUp.getAsu_state()==50){
							if(state>3||state==0){
								state = 4;
								mySignUp.setState("1");
								mySignUp.setAsu_stateStr("已经付款（"+statestr+"已取消）");
							}
							activeApplicant.setState("1");
							activeApplicant.setAa_state_str("已经付款（"+statestr+"已取消）");
							if("1".equals(type)){
								activeApplicantNormal.add(activeApplicant);
							}else{
								activeApplicantRefundfail.add(activeApplicant);
							}
						}else if(mySignUp.getAsu_state()==30){
							state = 1;
							mySignUp.setState("1");
							mySignUp.setAsu_stateStr("交易完成");
							activeApplicant.setState("1");
							activeApplicant.setAa_state_str("交易完成");
							activeApplicantRefunding.add(activeApplicant);
						}
					}else if(activeApplicant.getAa_refund_state()==80){
						if(mySignUp.getAsu_state()==20||mySignUp.getAsu_state()==50){
							if(state>4||state==0){
								state = 5;
								mySignUp.setState("3");
								mySignUp.setAsu_stateStr(""+statestr+"处理中（等待领队确认"+statestr+"）");
							}
							activeApplicant.setState("3");
							activeApplicant.setAa_state_str(""+statestr+"处理中（等待领队确认"+statestr+"）");
							activeApplicantRefunding.add(activeApplicant);
						}
					}else if(activeApplicant.getAa_refund_state()==90){
						if(mySignUp.getAsu_state()==20||mySignUp.getAsu_state()==50){
							if(state>5||state==0){
								state = 6;
								mySignUp.setState("3");
								if("1".equals(type)){
									mySignUp.setAsu_stateStr(""+statestr+"处理中（领队已确认"+statestr+"）");
								}else{
									mySignUp.setAsu_stateStr(""+statestr+"处理中（等待用户确认"+statestr+"）");
								}
							}
							if("1".equals(type)){
								activeApplicant.setState("3");
								activeApplicant.setAa_state_str(""+statestr+"处理中（领队已确认"+statestr+"）");
								activeApplicantRefund.add(activeApplicant);
							}else{
								activeApplicant.setState("3");
								activeApplicant.setAa_state_str(""+statestr+"处理中（等待用户确认"+statestr+"）");
								activeApplicantRefund.add(activeApplicant);
							}
							
						}
					}else if(activeApplicant.getAa_refund_state()==100){
						if(mySignUp.getAsu_state()==10||mySignUp.getAsu_state()==20||mySignUp.getAsu_state()==30||mySignUp.getAsu_state()==50){
							if(state>6||state==0){
								state = 7;
								mySignUp.setState("3");
								mySignUp.setAsu_stateStr(""+statestr+"处理中（等待退款到账）");
							}
							activeApplicant.setState("3");
							activeApplicant.setAa_state_str(""+statestr+"处理中（等待退款到账）");
							if("1".equals(type)){
								activeApplicantRefunding.add(activeApplicant);
							}else{
								activeApplicantRefund.add(activeApplicant);
							}
						}else if(mySignUp.getAsu_state()==40){
							if(state>6||state==0){
								state = 7;
								mySignUp.setState("5");
								mySignUp.setAsu_stateStr("已关闭");
							}
							activeApplicant.setState("5");
							activeApplicant.setAa_state_str("已关闭");
							if("1".equals(type)){
								activeApplicantRefunding.add(activeApplicant);
							}else{
								activeApplicantRefund.add(activeApplicant);
							}
						}
					}else if(activeApplicant.getAa_refund_state()==120){
						if(mySignUp.getAsu_state()==20||mySignUp.getAsu_state()==50){
							if(state>7||state==0){
								state = 8;
								mySignUp.setState("3");
								mySignUp.setAsu_stateStr(""+statestr+"处理中（等待客服处理）");
							}
							activeApplicant.setState("3");
							activeApplicant.setAa_state_str(""+statestr+"处理中（等待客服处理）");
							if("1".equals(type)){
								activeApplicantRefunding.add(activeApplicant);
							}else{
								activeApplicantRefund.add(activeApplicant);
							}
						}
					}else if(activeApplicant.getAa_refund_state()==130){
						if(mySignUp.getAsu_state()==10||mySignUp.getAsu_state()==20||mySignUp.getAsu_state()==50){
							if(state>8||state==0){
								state = 9;
								
								mySignUp.setState("4");
								mySignUp.setAsu_stateStr("退款完成");
							}
							activeApplicant.setState("4");
							activeApplicant.setAa_state_str("退款完成");
							refund += activeApplicant.getAa_refund_price();
							if("1".equals(type)){
								activeApplicantRefunding.add(activeApplicant);
							}else{
								activeApplicantRefund.add(activeApplicant);
							}
						}else if(mySignUp.getAsu_state()==40){
							if(state>9||state==0){
								state = 10;
								
								mySignUp.setState("5");
								mySignUp.setAsu_stateStr("已关闭");
							}
							activeApplicant.setState("5");
							activeApplicant.setAa_state_str("已关闭");
							if("1".equals(type)){
								activeApplicantRefunding.add(activeApplicant);
							}else{
								activeApplicantRefund.add(activeApplicant);
							}
						}
					}
				}
			}
			
			
			mySignUp.setRefund(refund);
		}
		
		mySignUp.setActiveApplicantNormal(activeApplicantNormal);
		mySignUp.setActiveApplicantRefunding(activeApplicantRefunding);
		mySignUp.setActiveApplicantRefund(activeApplicantRefund);
		mySignUp.setActiveApplicantRefundfail(activeApplicantRefundfail);
		
		if("2".equals(mySignUp.getState())){
			mySignUp.setPaytime(DateUtil.YYYYMMDDgetAfterHour(mySignUp.getAsu_create_time(),"1"));
		}
	}
	
	/**
	 * 取消订单
	 * @param asu_id
	 * @param aa_id
	 * @param aa_refund_area
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/quxiaodigndan")
	@ResponseBody
	public Map<String,String> cancelSignUpForUser(String asu_id,String aa_id,String aa_refund_area){
		Map<String, String> map = null;
		try {
			map = activeSignupServiceImpl.updateCancelSignUpForUser(this.getSessionUser().getuId(),this.getSessionUser().getuName(), asu_id,aa_id, aa_refund_area);
		} catch (Exception e) {
			logger.error("取消订单方法异常！！！！！！！！！", e);
		}
		return map;
	}
	
	/**
	 * 方法描述： 根据活动ID查询剩余订单（领队）
	 * 返回类型：ModelAndView
	 * @param session
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/signupByactive")
	@ResponseBody
	public Map<String,Object> signupByactive(HttpSession session,Page<MySignUp> page,MySignUp mySignUp,String activityId)throws Exception{
		// 根据当前登录用户ID和活动ID联合查询,防止别的领队恶意查询
		Users user = this.getSessionUser();
		// 当前登录用户ID
		mySignUp.setCreateUser(user.getuId());
		
		mySignUp.setActivityId(activityId);
		page.setT(mySignUp);
		page.setPageSize(3);
		
		List<MySignUp> mySignUps = activeSignupServiceImpl.leaderActiveSignupManagerPage(page);
		replace(mySignUps,"0","2");
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mySignUps", mySignUps);
		map.put("pageStr", getPageStr(page));

		return map;
	}
	
	/**
	 * 分页
	 */
	public String getPageStr(Page<MySignUp> page) {
		if(page.getResultList() != null){
			return "";
		}
		
		StringBuffer sb = new StringBuffer();
		if(page.getTotalResult()>0){
			sb.append("	<div class=\"page\">\n");
			if(page.getCurrentPage()==1){
				sb.append("	<a href=\"javascript:\" title=\"上一页\" class=\"pn-prev\">上一页</a>\n");
			}else{
				sb.append("	<a id=\"flip\" page=\""+(page.getCurrentPage()-1)+"\" href=\"javascript:\" title=\"上一页\" class=\"pn-prev\">上一页</a>\n");
			}
			if(page.getCurrentPage()>4 && page.getTotalPage() > 5){
				sb.append("	<a id=\"flip\" page=\""+1+"\" href=\"javascript:\">"+1+"</a>\n");
				sb.append("	<span class=\"pn-break\">...</span>\n");
			}
			int showTag = 5;//分页标签显示数量
			int startTag = 1;
			if(page.getCurrentPage()>=showTag){
				startTag = page.getCurrentPage()-1;
			}
			if(startTag > 4 && startTag >= page.getTotalPage()-4){
				startTag = page.getTotalPage()-4;
			}
			int endTag = startTag+showTag-1;
			for(int i=startTag; i<=page.getTotalPage() && i<=endTag; i++){
				if(page.getCurrentPage()==i)
					sb.append("<a href=\"javascript:\" class=\"cur\">"+i+"</a>\n");
				else
					sb.append("<a id=\"flip\" page=\""+i+"\" href=\"javascript:\">"+i+"</a>\n");
			}
			if(page.getCurrentPage()==page.getTotalPage()){
				sb.append("	<a href=\"javascript:\" title=\"下一页\" class=\"pn-next\">下一页</a>\n");
			}else{
				if(page.getTotalPage()-4 >= page.getCurrentPage()){
					sb.append("	<span class=\"pn-break\">...</span>\n");
					sb.append("	<a id=\"flip\" page=\""+page.getTotalPage()+"\" href=\"javascript:\")\">"+page.getTotalPage()+"</a>\n");
				}
				sb.append("	<a id=\"flip\" page=\""+(page.getCurrentPage()+1)+"\" href=\"javascript:\" title=\"下一页\" class=\"pn-next\">下一页</a>\n");
			}
			sb.append("	</div>\n");

		}
		return sb.toString();
	}
	
	/**
	 * 方法描述： 领队确认报名
	 * 返回类型：Map<String,Object>
	 * @param activeId
	 * @param asuId
	 * @return
	 */
	@RequestMapping(value="confirmSignUp",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> confirmSignUp(int activityId,String aaIds,String asu_id,HttpSession session){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			ActiveSignUpPay dto = activeSignupServiceImpl.findActivitySignUpPay(asu_id);
			
			if(dto.getAsu_pay_state()==2){
				if("1".equals(dto.getIsinsurance())){
					String userEmail = usersServiceImpl.findUserEmailById(dto.getAsu_user_id());
					
					BaoXianren toubaoren = new BaoXianren();
					for(int i=0;i<dto.getActiveApplicant().size();i++){
						ActiveApplicant activeApplicant = dto.getActiveApplicant().get(i);
						
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
							//订单ID
							toubaoren.setAsu_id(activeApplicant.getAsu_id());
							//联系人ID
							toubaoren.setAa_id(activeApplicant.getAa_id());
						}
					}
					
					for(int i=0;i<dto.getActiveApplicant().size();i++){
						List<BaoXianren> baoXianrenList = new ArrayList<BaoXianren>();
						ActiveApplicant activeApplicant = dto.getActiveApplicant().get(i);
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
						insurancesServiceImpl.buy(dto.getAsu_active_id(),dto.getIn_id(),dto.getAsu_user_id(),DateUtil.YYYY_MM_DDgetDay(dto.getActivityTime()), DateUtil.YYYY_MM_DDgetDay(dto.getEndTime()), baoXianrenList);
					}
					
					dto.setAsu_account_payable(dto.getAsu_account_payable()-dto.getInsurance().getAcp_cost());
				}
				
				//新增资金明细
				cashRecordServiceImpl.saveCashRecordByActiveSignup(dto.getCreateUser(), dto.getAsu_reserve_price(), dto.getAsu_account_payable(), dto.getAsu_id());
				//更改发布者钱包信息
				balanceServiceImpl.updateMoneyByUserId(dto.getCreateUser(),dto.getAsu_reserve_price(),dto.getAsu_account_payable());
			}
			
			Users user = (Users) session.getAttribute(Const.SESSION_USER);
			map.put("activeId", activityId);
			map.put("userId", user.getuId());
			map.put("aaIds", aaIds.split(","));
			map.put("asu_id", asu_id);
			activeSignupServiceImpl.updateConfirmSignUp(map);
			
			String aa_id = "";
			for(int i=0;i<dto.getActiveApplicant().size();i++){
				if(i!=0){
					aa_id+=",";
				}
				ActiveApplicant activeApplicant = dto.getActiveApplicant().get(i);
				aa_id += activeApplicant.getAa_id();
			}
			
			String[] aa_ids = aa_id.split(",");
			
			//操作日志
			activeApplicantLogServiceImpl.saveActiveApplicantLog(aa_ids, asu_id+"", dto.getAsu_active_id()+"", 1, "领队已确认报名", user.getuId(), "20");
			
			/**
			 * 发送消息（领队确认报名，给用户发送消息）
			 */
			Users users = usersServiceImpl.findUserById(dto.getAsu_user_id());
			Map<String,Object> objectMap = new HashMap<String,Object>();
			objectMap.put("activeId", dto.getAsu_active_id()+"");
			objectMap.put("title", dto.getTitle());
			
			messagePrivateServiceImpl.saveMessagePrivate(objectMap,users,"acAgSignup");
			
		} catch (Exception e) {
			map.clear();
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, "修改异常，请稍后重试!");
		}
		return map;
	}
	
	/**
	 * 方法描述：领队确认取消报名
	 * 返回类型：Map<String,Object>
	 * @param activeId
	 * @param asuId
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/cancelSignUp",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> cancelSignUp(int activityId,String aaIds,String asu_id,String refund_money,String refund_remark,HttpSession session){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Users user = (Users) session.getAttribute(Const.SESSION_USER);
			map.put("activeId", activityId);
			map.put("asu_id", asu_id);
			map.put("aaIds", aaIds);
			map.put("refund_money", refund_money);
			map.put("refund_remark", refund_remark);
			map.put("userId", user.getuId());
			activeSignupServiceImpl.updateCancelSignUp(map);
		} catch (Exception e) {
			map.clear();
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, "修改异常，请稍后重试!");
		}
		return map;
	}
	
	/**
	 * 方法描述：用户确认领队退款金额
	 * 返回类型：Map<String,Object>
	 * @param activeId
	 * @param asuId
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/updateRefund",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateRefund(int activityId,String aaIds,String asu_id,String isagree,HttpSession session){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Users user = (Users) session.getAttribute(Const.SESSION_USER);
			map.put("activeId", activityId);
			map.put("asu_id", asu_id);
			map.put("aaIds", aaIds);
			map.put("isagree", isagree);
			map.put("userId", user.getuId());
			activeSignupServiceImpl.updateRefundByUser(map);
		} catch (Exception e) {
			map.clear();
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, "修改异常，请稍后重试!");
		}
		
		return map;
	}
	
	/**
	 * 方法描述：用户确认交易完成
	 * 返回类型：Map<String,Object>
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/complete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> complete(HttpSession session,String id){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Users user = (Users) session.getAttribute(Const.SESSION_USER);
			map.put("userId", user.getuId());
			map.put("asu_id", id);
			activeSignupServiceImpl.updateComplete(map);
		} catch (Exception e) {
			map.clear();
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, "修改异常，请稍后重试!");
		}
		
		return map;
	}
	
	/**
	 * 付款成功
	 * @param activeId
	 * @param asu_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="paysuccess")
	public ModelAndView paysuccess(String activeId,String asu_id,ModelAndView mv) throws Exception{
		Users users = this.getSessionUser();
		
		Page<Map<String,Object>> page = new Page<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", users.getuId());
		map.put("order", 0);
		map.put("asu_id", asu_id);
		map.put("activeId", activeId);
		page.setT(map);
		
		if(activeSignupServiceImpl.isComment(map)){
			List<MySignUp> mySignUps = activeSignupServiceImpl.findMySignUpByUserId(page);
			
			MySignUp mySignUp = new MySignUp();
			
			if(mySignUps.size()>0){
				mySignUp = mySignUps.get(0);
			}
			
			this.setHeaderMap(mv, null);
			
			mv.addObject("mySignUp", mySignUp);
	    	mv.setViewName("activity/order/rate");
		}else{
			mv.setViewName("comment");
		}
		
    	return mv;
	}
	
	/**
	 * 订单详情页
	 * @throws Exception 
	 */
	@RequestMapping("/orderDetails")
	public ModelAndView orderDetails(String asu_id,ModelAndView mav) throws Exception{
		Map<String, Object> orderMap=new HashMap<String,Object>();//订单map
		Users user = this.getSessionUser();
		orderMap.put("asu_user_id", user.getuId());//用户ID
		orderMap.put("asu_id", asu_id);//报名编号
		
		MySignUp mySignUp = new MySignUp();
			
		if(user.getuType()==1 || user.getuType()==2){
			mySignUp = activeSignupServiceImpl.selectDetailsForLeader(orderMap);//订单信息
		}else{
			mySignUp = activeSignupServiceImpl.selectDetails(orderMap);//订单信息
		}
		
		if(mySignUp!=null){
			detailsinfo(mav,mySignUp.getActivityId(),mySignUp.getAsu_id(),user);
			
			List<MySignUp> list = new ArrayList<MySignUp>();
			list.add(mySignUp);
			mav.addObject("userName",user.getuName());
			
			if(user.getuType()==1 || user.getuType()==2){
				mav.addObject("uId",mySignUp.getCreateUser());
			}else{
				mav.addObject("uId",mySignUp.getAsu_user_id());
			}
			
			this.setHeaderMap(mav, null);
			
			Policyholders policyholders = policyholdersServiceImpl.getPriceTotal(mySignUp.getAsu_id());
			if(user.getuType()==1 || user.getuType()==2){
				mySignUp = replace(list, "0", "2").get(0);
				double insurance = 0;
				if(policyholders!=null){
					insurance = policyholders.getPriceTotal();
				}
				
				double refundPrice = activeApplicantServiceImpl.getRefundPrice(asu_id);
				double maxrefund = mySignUp.getAsu_account_paid() - insurance - refundPrice;
				
				mav.addObject("insurance", insurance);
				mav.addObject("maxrefund", maxrefund);
				mav.addObject("mySignUp",mySignUp);//查询报名详情
				mav.setViewName("usercenter/leader/housekeeper/activeSignUp/leader-order-info");
			}else{
				double insurance = 0;
				if(policyholders!=null){
					mav.addObject("refundstatus", policyholders.getRefundstatus());
					insurance = policyholders.getPriceTotal()/policyholders.getInsurantCount();
					mav.addObject("day", DateUtil.getDaySub(DateUtil.getDay() ,policyholders.getStartDate()));
				}
				
				mav.addObject("insurance", insurance);
				mav.addObject("mySignUp",replace(list, "0", "1").get(0));//查询报名详情
				mav.setViewName("usercenter/housekeeper/sigunUp/order-info");
			}
		}else{
			mav.setViewName("redirect:/404.html");
		}
		
		return mav;
	}
	
	/**
	 * 跳转取消报名人详情页
	 * @param asu_id
	 * @throws Exception 
	 */
	@RequestMapping(value="/applicantList")
	@ResponseBody
	public ModelAndView applicantList(String asu_id,ModelAndView mav) throws Exception{
		Map<String, Object> orderMap=new HashMap<String,Object>();//订单map
		Users user = this.getSessionUser();
		orderMap.put("asu_user_id", user.getuId());//用户ID
		orderMap.put("asu_id", asu_id);//报名编号
		
		MySignUp mySignUp=activeSignupServiceImpl.selectDetails(orderMap);//订单信息
		
		if(mySignUp!=null){
			List<MySignUp> list = new ArrayList<MySignUp>();
			list.add(mySignUp);
			MySignUp signUp = replace(list, "0", "1").get(0);
			
			double price = signUp.getAsu_account_paid()/signUp.getActiveApplicants().size();
			
			detailsinfo(mav,mySignUp.getActivityId(),mySignUp.getAsu_id(),user);
			
			mav.addObject("price", price);
			mav.addObject("userName",user.getuName());
			mav.addObject("uId",user.getuId());
			mav.addObject("mySignUp",signUp);//查询报名详情
			mav.setViewName("usercenter/housekeeper/sigunUp/order-info");
		}else{
			mav.setViewName("redirect:/404.html");
		}
		return mav;
	}
	
	public void detailsinfo(ModelAndView mav,String activityId,String asu_id,Users user) throws Exception{
		// 查询该活动简介信息
		QueryActivity queryActivity = new QueryActivity();
		queryActivity = activityServiceImpl.orderById(activityId);
		
		if(queryActivity.getActivityTime()!=null){
			queryActivity.setActivityTimeStr(DateUtil.YYYY_MM_DDgetDay(queryActivity.getActivityTime()));
		}
		if(queryActivity.getEndTime()!=null){
			queryActivity.setEndTimeStr(DateUtil.YYYY_MM_DDgetDay(queryActivity.getEndTime()));
		}
		if(queryActivity.getA_gather_time()!=null){
			queryActivity.setGatherTimeStr(DateUtil.YYYY_MM_DDgetDay(queryActivity.getA_gather_time()));
		}
		
		Map<String, String> map = new HashMap<String,String>();
		map.put("asp_create_user", user.getuId());
		map.put("asp_asu_id", asu_id);
		
		List<ActiveSignUpCorrelationCost> activeSignUpCorrelationCost = activeSignUpCorrelationCostServiceImpl.findAllByActiveSignUpId(map);
		
		mav.addObject("queryActivity",queryActivity);
		mav.addObject("activeSignUpCorrelationCost",activeSignUpCorrelationCost);

	}
	
}
