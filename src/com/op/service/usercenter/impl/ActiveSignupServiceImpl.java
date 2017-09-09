package com.op.service.usercenter.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;







import org.springframework.util.StringUtils;

import com.op.dao.BaseDao;
import com.op.dto.activity.activeSignUpPay.ActiveSignUpPay;
import com.op.dto.activity.queryActivity.QueryActivity;
import com.op.dto.signUp.ActiveAndSignUpDTO;
import com.op.dto.signUp.InsertSignUpDTO;
import com.op.dto.usercenter.leader.activeManager.activeRefund.LeaderActiveRefundsDTO;
import com.op.dto.usercenter.leader.activeManager.activeRefund.LeaderSearchActiveRefundDTO;
import com.op.dto.usercenter.leader.activeManager.activeSignUp.LeaderSignUp;
import com.op.dto.usercenter.leader.activeManager.activeSignUp.MySignUp;
import com.op.dto.usercenter.leader.activeManager.activeSignUp.SignUpList;
import com.op.entity.activity.Activity;
import com.op.entity.activity.cost.ActiveCorrelationCost;
import com.op.entity.alipay.Alipay;
import com.op.entity.insurance.Policyholders;
import com.op.entity.usercenter.ActiveApplicant;
import com.op.entity.usercenter.ActiveSignUpCorrelationCost;
import com.op.entity.usercenter.ActiveSignup;
import com.op.entity.usercenter.ActiveSignupInfo;
import com.op.entity.usercenter.LinkSignUpUser;
import com.op.entity.users.Users;
import com.op.plugin.page.Page;
import com.op.service.balance.BalanceService;
import com.op.service.balance.CashRecordService;
import com.op.service.insurance.InsurancesService;
import com.op.service.insurance.PolicyholdersService;
import com.op.service.messageprivate.MessagePrivateService;
import com.op.service.usercenter.ActiveApplicantLogService;
import com.op.service.usercenter.ActiveApplicantService;
import com.op.service.usercenter.ActiveSignupService;
import com.op.service.users.UsersService;
import com.op.util.Const;
import com.op.util.DateUtil;
import com.op.util.IdCardUtils;
import com.op.util.SerializationUtil;
import  com.op.util.jedis.RedisUtil;

/**
 * 项目名：outdoorPortal
 * 类描述：活动报名实现类
 * 创建人：Yan
 * 创建时间： 2015-12-16 上午11:14:50
 * 最后修改时间：2015-12-16上午11:14:50
 */
@Service("activeSignupServiceImpl")
public class ActiveSignupServiceImpl implements ActiveSignupService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	@Resource(name="usersServiceImpl")
	private UsersService usersServiceImpl;
	
	//站内信
	@Resource(name="messagePrivateServiceImpl")
	private MessagePrivateService messagePrivateServiceImpl;
	
	//操作日志
	@Resource(name="activeApplicantLogServiceImpl")
	private ActiveApplicantLogService activeApplicantLogServiceImpl;
	
	//购买保险
	@Resource(name="insurancesServiceImpl")
	private InsurancesService insurancesServiceImpl;
	
	//保单信息
	@Resource(name="policyholdersServiceImpl")
	private PolicyholdersService policyholdersServiceImpl;
	
	//联系人
	@Resource(name="activeApplicantServiceImpl")
	private ActiveApplicantService activeApplicantServiceImpl;
	
	//用户资金账户余额
	@Resource(name="balanceServiceImpl")
	private BalanceService balanceServiceImpl;
	//用户资金明细
	@Resource(name="cashRecordServiceImpl")
	private CashRecordService cashRecordServiceImpl;
	
	@Resource(name="activeSignupServiceImpl")
	private ActiveSignupService activeSignupServiceImpl;
	
	/**
	 * 我的活动(报名的活动或者发布的活动)或者退款管理
	 */
	@Override
	public List<ActiveSignupInfo> selectActive(Page<ActiveSignupInfo> page) throws Exception {
		return (List<ActiveSignupInfo>)dao.findForList("activeSignupMapper.selectActiveListPage", page);
	}
	
	/**
	 * 查询报名信息
	 */
	@Override
	public Map<String, Object> selectSignupInfo(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) dao.findForObject("activeSignupMapper.selectSignupInfo", map);
	}
	
	/**
	 * 查询是否存在传入的订单id
	 */
	@Override
	public int selectOrderId(Map<String, Object> map) throws Exception {
		return (int) dao.findForObject("activeSignupMapper.selectOrderId", map);
	}
	
	/**
	 * 修改报名信息
	 */
	@Override
	public void updateActive(Map<String, Object> map) throws Exception {
		dao.update("activeSignupMapper.updateActive",map);
	}
	
//	/**
//	 * 报名详情
//	 */
//	public ActiveSignupInfo selectDetails(Map<String, Object> map) throws Exception {
//		return (ActiveSignupInfo) dao.findForObject("activeSignupMapper.selectDetails", map);
//	}

	/**
	 * 方法描述：报名添加
	 * 返回类型：void
	 * @param signUp 报名人信息
	 * @param fullPay 预付款/全款标识（0：预付款；1：全款；）
	 * @param activeId 活动ID
	 * @param map ajax提示信息
	 * @throws Exception
	 */
	@Override
	public void insertActiveSignUp(InsertSignUpDTO dto, int fullPay,
			int activeId, Map<String, Object> map,HttpSession session,String notes) throws Exception {/*
		// 当前用户
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		// 当前时间
		Date cd = new Date();
		// 商户订单号 商户网站订单系统中唯一订单号，必填
		String out_trade_no = UuidUtil.get32UUID();
		// 报名人个人数
		int len = dto.getSignUp().size();
		// 查询活动数据进行价格计算，避免用户恶意修改
		Activity a = (Activity) dao.findForObject("activityMapper.signUpFindActiveById", activeId);
		
		// 判断活动相关信息
		if(checkActivity(a,map,len,cd)){
			// 应该支付的金额
			double price=0;
			
			// 付款类型（10：全款；20：预付款；30：免费；）
			int asu_price_type = 30;
			
			---------------------------------		计算付款金额		-------------------------
			
			// 预付款/全款标识（0：预付款；1：全款；）
			if(fullPay == 0){
				asu_price_type = 20;
				price = len*a.getA_reserve_price();
			}else{
				asu_price_type = 10;
				price = len*a.getPrice();
			}
			
			---------------------------------		报名联系人手机号码		-------------------------
			// 报名联系人手机号码
			long asu_link_user_phone = 0;
			for (ActiveSignup su : dto.getSignUp()) {
				// 是否为报名列表的联系人(0：否；1：是；)
				if(su.getIsLinkman() == 1){
					asu_link_user_phone = su.getAsu_user_phone();
					break;
				}
			}
			
			
			
			---------------------------------		封装报名数据  保存订单		-------------------------
			// 封装订单数据
			List<String> asu_ids = new ArrayList<String>();
			pd(a,user,cd,notes,asu_price_type,asu_link_user_phone,dto,asu_ids,out_trade_no);
			
			// 生成订单
			dao.save("activeSignupMapper.generateOrder", dto.getSignUp());
			
			
			---------------------------------		保存常用联系人		-------------------------
			// 保存常用联系人对象
			List<ActiveSignup> sl = null;
			
			for (ActiveSignup su : dto.getSignUp()) {
				// 当联系人不是选择已存在的常用联系人	并且	需要保存为常用联系人
				if(StringUtils.isEmpty(su.getLsuu_id()) && su.getNeedSave() != null && su.getNeedSave() == 1){
					if(sl == null){
						sl = new ArrayList<ActiveSignup>();
					}
					
					// 如果为新的联系人，保存数据
					su.setLsuu_id(UuidUtil.get32UUID());
					sl.add(su);
				}
			}
			
			if(sl != null){
				dao.save("linkSignUpUserMapper.signUpInsertUsers", sl);
			}
			
			
			---------------------------------		报名成功修改活动信息		-------------------------
			// 修改活动报名人数
			Map<String,Object> aumap = new HashMap<String,Object>();

			// 活动ID
			aumap.put("id", a.getId());
			// 已经报名的人数
			aumap.put("alreadyInNum", a.getAlreadyInNum()+len);

			dao.update("activityMapper.updateAlreadyNum", aumap);
			
			
			---------------------------------		返回支付信息	-------------------------
			// 支付订单号
			map.put("out_trade_no", out_trade_no);
			
			// 创建session支付数据
			ca(out_trade_no,a,price,session,asu_ids);

			// 执行成功
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
			map.remove(Const.ERROR_INFO);
		}
		
		
	*/}
	
	
	/**
	 * 方法描述：创建session支付数据
	 * 返回类型：void
	 * @param out_trade_no 支付订单号
	 * @param a	活动对象
	 * @param price	支付总额
	 * @param average	平均单人金额
	 * @param session	session
	 */
	private void ca(String out_trade_no,Activity a,double price,HttpSession session
			,List<String> asu_ids){
		Alipay ap = new Alipay();
		
		// 支付对应的订单ID集合
		//ap.setAsu_ids(asu_ids);
		
		// 商户订单号 商户网站订单系统中唯一订单号
		ap.setOut_trade_no(out_trade_no);
		
		// 订单名称
		ap.setSubject("玩嘛活动订单");
		
		// 订单订单描述
		ap.setBody("活动报名费用");
		
		// 商品地址(ID)
		ap.setShow_url(a.getId()+"");
		
		// 支付订单号对应的金额；key为对应的支付订单号，值为支付的金额
		ap.setTotal_fee(price+"");
		
		// 商家支付业务类型为活动报名
		ap.setOutDoorPayType(Const.PAY_ACTIVE);
		
		// 放入session，谨防修改; key为对应的支付订单号，值为支付的对象
		session.setAttribute(out_trade_no, ap);
	}
	
	/**
	 * 方法描述：
	 * 返回类型：boolean
	 * @param a 活动
	 * @param map 返回提示信息
	 * @param num 报名人数
	 * @param cd 当前时间
	 * @return
	 */
	@Override
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
		if(num > (a.getNeedUserNum()-a.getAlreadyInNum())){
			map.put(Const.ERROR_INFO, "报名人数超过该活动剩余人数上限。剩余报名人数为["+(a.getNeedUserNum()-a.getAlreadyInNum())+"]位!");
			return false;
		}
		
		return true;
	}
	
	/**
	 * 方法描述：根据订单ID查询活动信息
	 * 实现接口：@see com.op.service.usercenter.ActiveSignupService#findActiveSignUpById(int)
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public ActiveAndSignUpDTO findActiveSignUpById(String asu_id) throws Exception {
		// TODO Auto-generated method stub
		return (ActiveAndSignUpDTO) dao.findForObject("activeSignupMapper.findActiveSignUpById", asu_id);
	}
	
	/**
	 * 方法描述：领队报名退款订单
	 * 实现接口：@see com.op.service.usercenter.ActiveSignupService#leaderRefunds(com.op.dto.usercenter.leader.activeManager.activeRefund.LeaderSearchActiveRefundDTO)
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<LeaderActiveRefundsDTO> leaderRefunds(Page<LeaderSearchActiveRefundDTO> page) throws Exception {
		
		return (List<LeaderActiveRefundsDTO>) dao.findForList("activeSignupMapper.leaderRefundsPage", page);
	}

	
	/**
	 * 方法描述：领队设置退款订单的退款金额和退款备注
	 * 实现接口：@see com.op.service.usercenter.ActiveSignupService#updateRefundOfLeader(java.lang.String, java.lang.String, double, java.lang.String)
	 * @param asu_active_id	活动ID
	 * @param asu_id	订单ID
	 * @param asu_refund_price	退款金额
	 * @param asu_leader_refund_area	退款备注
	 * @throws Exception
	 */
	@Override
	public void updateRefundOfLeader(Map<String,Object> map)
			throws Exception {
		
		// 判断当前执行修改操作的用户是不是该活动的发布者，防止别的领队修改参数，修改别的活动的订单状态
		String uid = (String) dao.findForObject("activityMapper.findUserIdByActiveId", map.get("asu_active_id"));
		if(!uid.equals(map.get("userId")+"")){
			map.clear();
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, "您不是该活动的发起者，没有权限操作！");
			return;
		}
		
		// 设置退款订单的退款金额和退款备注
		dao.update("activeSignupMapper.updateRefundOfLeader", map);
		
		map.clear();
		map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		
	}



	
	/** -------------------------------------------        分割线                ---------------------------------------- */
	
	
	
	/**
	 * 新增订单
	 * @param activeSignup 订单实体类
	 * @throws Exception 
	 */
	@Override
	public String saveOrder(ActiveSignup activeSignup,QueryActivity queryActivity,Map<String,Object> map,Users users) throws Exception{
		boolean bool = true;
		String info = "";
		//验证身份证号是否正确
		for(int i=0;i<activeSignup.getLinkSignUpUser().size();i++){
			LinkSignUpUser linkSignUpUser = activeSignup.getLinkSignUpUser().get(i);
			if(linkSignUpUser.getLsuu_cart_type()==1){
				if(!IdCardUtils.validateCard(linkSignUpUser.getLsuu_cart_num())){
					if("".equals(info)){
						info += linkSignUpUser.getLsuu_user_name();
					}else{
						info += "、"+linkSignUpUser.getLsuu_user_name();
					}
					bool = false;
				}
			}
			
		}
		if(!bool){
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO,"报名人身份证信息不正确："+info);

			return "false";
		}else{
			//人数
			int peoplenum = activeSignup.getLinkSignUpUser().size();
			
			if(queryActivity.getNeedUserNum()-queryActivity.getConfirmUserNum()>=peoplenum){
				Map<String,Object> activityMap = new HashMap<String,Object>();
				activityMap.put("alreadyInNum", queryActivity.getAlreadyInNum()+peoplenum);
				activityMap.put("id", queryActivity.getId());
				dao.update("activityMapper.updateAlreadyNum", activityMap);
				
				//可选费用
				List<ActiveCorrelationCost> optional = new ArrayList<ActiveCorrelationCost>();
				optional = activeSignup.getOptional();
				
				//可选费用
				List<ActiveCorrelationCost> Q_optional = queryActivity.getOptional();
				
				double sum = queryActivity.getPrice()*peoplenum;
				double reserve_price = queryActivity.getA_reserve_price()*peoplenum;
				
				if(optional!=null&&optional.size()>0){
					for(int i=0;i<optional.size();i++){
						for(int j=0;j<Q_optional.size();j++){
							if(optional.get(i).getAcp_id().equals(Q_optional.get(j).getAcp_id())){
								sum += Q_optional.get(j).getAcp_cost()*peoplenum;
								reserve_price += Q_optional.get(j).getAcp_cost()*peoplenum;
							}
						}
					}
				}
				
				if(sum==0||(queryActivity.getPaytype()==2&&reserve_price==0)){
					activeSignup.setAsu_pay_state(2);
				}else{
					activeSignup.setAsu_pay_state(1);
				}
				activeSignup.setAsu_price_type(queryActivity.getPaytype());
				
				activeSignup.setAsu_account_payable(sum);
				activeSignup.setAsu_reserve_price(reserve_price);
				
				activeSignup.setAsu_create_time(new Date());
				dao.save("activeSignupMapper.saveOrder", activeSignup);
				
				// 获取缓存对象


				if(RedisUtil.hkeys("waitPay".getBytes()).iterator().hasNext()){
					// 将新增订单添加到缓存
					RedisUtil.hset("waitPay".getBytes(), activeSignup.getAsu_id().getBytes(),SerializationUtil.serialize(activeSignup));
					//一天后自动删除缓存
					RedisUtil.expire("waitPay".getBytes(), 24*60*60);
				}else{
					Map<byte[],byte[]> activeSignupMap = new HashMap<byte[],byte[]>();
					activeSignupMap.put(activeSignup.getAsu_id().getBytes(), SerializationUtil.serialize(activeSignup));
					//将新增的订单添加入缓存
					RedisUtil.hmset("waitPay".getBytes(),activeSignupMap);
					//一天后自动删除缓存
					RedisUtil.expire("waitPay".getBytes(), 24*60*60);
				}
				
				map.put("price", sum);
				map.put("time", DateUtil.YYYYMMDDgetAfterHour(new Date(),"1"));
				
				if(optional!=null&&optional.size()>0){
					for(int i=0;i<optional.size();i++){
						for(int j=0;j<Q_optional.size();j++){
							if(optional.get(i).getAcp_id().equals(Q_optional.get(j).getAcp_id())){
								ActiveSignUpCorrelationCost activeSignUpCorrelationCost = new ActiveSignUpCorrelationCost();
								
							 	//费用名称
								activeSignUpCorrelationCost.setAsp_cost_name(Q_optional.get(i).getAcp_cost_name());
							 	//单价/补差费用
								activeSignUpCorrelationCost.setAsp_cost(Q_optional.get(i).getAcp_cost());
							 	//费用说明
								activeSignUpCorrelationCost.setAsp_comment(Q_optional.get(i).getAcp_comment());
							 	//创建人
								activeSignUpCorrelationCost.setAsp_create_user(users.getuId());
							 	//创建时间
								activeSignUpCorrelationCost.setAsp_create_time(Q_optional.get(i).getAcp_create_time());
							 	//订单ID
								activeSignUpCorrelationCost.setAsp_asu_id(activeSignup.getAsu_id());
								
								dao.save("activeSignUpCorrelationCostMapper.saveActiveSignUpCorrelationCost", activeSignUpCorrelationCost);
							}
						}
					}
				}
				
				return activeSignup.getAsu_id();
			}else{
				map.put(Const.RESPONSE_STATE, "500");
				map.put(Const.ERROR_INFO, "报名人数超过该活动剩余人数上限。剩余报名人数为["+(queryActivity.getNeedUserNum()-queryActivity.getAlreadyInNum())+"]位!");
				
				return "false";
			}
		}
	}
	
	/**
	 * 付款
	 * @param asu_id 订单ID
	 */
	@Override
	public ActiveSignUpPay findActivitySignUpPay(String asu_id) throws Exception{
		ActiveSignUpPay activeSignUpPay = new ActiveSignUpPay();
		activeSignUpPay = (ActiveSignUpPay) dao.findForObject("activeSignupMapper.findActivitySignUpPay", asu_id);
		
		return activeSignUpPay;
	}
	
	/**
	 * 方法描述：修改支付订单信息
	 * 实现接口：@see com.op.service.usercenter.ActiveSignupService#updateActiveSignUp(com.op.entity.alipay.Alipay)
	 * @param ap
	 * @throws Exception
	 */
	@Override
	public void updateActiveSignUp(Alipay ap) throws Exception {
		
		dao.update("activeSignupMapper.updateActiveSignUpPayInfo", ap);
		
	}
	
	/**
	 * 方法描述：根据支付流水号查询该订单数据，处理支付宝异步通知修改支付信息
	 * 返回类型：List<ActiveSignUpPay>
	 * @param out_trade_no 商户订单号
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ActiveSignUpPay> findAsuIdByAccountNum(String out_trade_no) throws Exception{
		return (List<ActiveSignUpPay>) dao.findForList("activeSignupMapper.findAsuIdByAccountNum", out_trade_no);
	}
	
	/**
	 * 方法描述：根据活动ID查询报名列表
	 * 返回类型：List<SignUpList>
	 * @param activityId	活动ID
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<SignUpList> findSignUpByActivityId(Page<String> page) throws Exception{
		return (List<SignUpList>) dao.findForList("activeSignupMapper.findSignUpByActivityIdPage", page);
	}
	
	/**
	 * 我的订单
	 * @param page 分页
	 */
	@Override
	public List<MySignUp> findMySignUpByUserId(Page<Map<String,Object>> page) throws Exception{
		List<MySignUp> list = new ArrayList<MySignUp>();
		list = (List<MySignUp>) dao.findForList("activeSignupMapper.findMySignUpByUserIdPage",page);
		return list;
	}
	
	/**
	 * 我的订单数量
	 * @param userId 用户ID
	 */
	@Override
	public Map<String,String> countMySignUpByUserId(String userId) throws Exception{
		return (Map<String, String>) dao.findForObject("activeSignupMapper.countMySignUpByUserId", userId);
	}
	
	/**
	 * 用户取消订单
	 * @param userId 用户ID
	 * @param asu_id 订单ID
	 * @throws Exception
	 */
	@Override
	public Map<String,String> updateCancelSignUpForUser(String userId,String uName,String asu_id,String aa_id,String aa_refund_area) throws Exception{
		Map<String,String> statemap = new HashMap<String,String>();
		statemap.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		statemap.put(Const.SUCCESS_INFO, "取消成功！");
		
		Page<Map<String,Object>> page = new Page<Map<String,Object>>();
		
		if(StringUtils.isEmpty(aa_id)){
			statemap.put(Const.RESPONSE_STATE, "500");
			statemap.put(Const.ERROR_INFO, "请选择报名人");
			return statemap;
		}
		String[] aa_ids = aa_id.split(",");
		
		//根据用户ID，订单ID查询订单相关数据
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("asu_id", asu_id);
		map.put("order", 0);
		 
		page.setT(map);
		
		List<MySignUp> list = new ArrayList<MySignUp>();
		
		try {
			list = (List<MySignUp>) dao.findForList("activeSignupMapper.findMySignUpByUserIdPage", page);
		} catch (Exception e) {
			statemap.put(Const.RESPONSE_STATE, "500");
			statemap.put(Const.ERROR_INFO, "查询报名信息出错!操作执行失败!");
			return statemap;
		}
		
		MySignUp mySignUp = new MySignUp();
		if(list.size()>0){
			mySignUp = list.get(0);
			
			//修改活动数据
			Map<String,Object> activityMap = new HashMap<String,Object>();
			activityMap.put("userId", userId);
			//修改报名人
			Map<String,Object> aaMap = new HashMap<String,Object>();
			aaMap.put("userId", userId);
			//修改订单数据
			Map<String,Object> asuMap = new HashMap<String,Object>();
			asuMap.put("userId", userId);
			
			int state = 0;
			boolean bool = true;
			
			if(mySignUp.getAsu_pay_state()==1){
				int count = 0;
				int people = 0;
				for(int i=0;i<mySignUp.getActiveApplicants().size();i++){
					ActiveApplicant activeApplicant = mySignUp.getActiveApplicants().get(i);
					
					if(activeApplicant.getAa_state()!=40&&activeApplicant.getAa_refund_state()!=100&&activeApplicant.getAa_refund_state()!=130){
						people++;
					}
					
					for(int j=0;j<aa_ids.length;j++){
						if(activeApplicant.getAa_id().equals(aa_ids[j])){
							aaMap = new HashMap<String,Object>();
							aaMap.put("userId", userId);
							//订单报名人ID
							aaMap.put("aa_id", aa_ids[j]);
							
							activityMap = new HashMap<String,Object>();
							//活动ID
							activityMap.put("id", mySignUp.getActivityId());
							
							if(activeApplicant.getAa_state()==10){
								count++;
								updateActiveApplicant(aaMap, activityMap, userId, aa_refund_area, mySignUp, activeApplicant, 40,i);
								
								String[] logaa_ids = new String[]{aa_ids[j]};
								//操作日志
								activeApplicantLogServiceImpl.saveActiveApplicantLog(logaa_ids, asu_id+"", mySignUp.getActivityId(), 1, "报名已取消，取消原因："+aa_refund_area, userId, "40");
								activeApplicantLogServiceImpl.saveActiveApplicantLog(logaa_ids, asu_id+"", mySignUp.getActivityId(), 2, aa_refund_area, userId, "40");
								
								statemap.put(Const.SUCCESS_INFO, "报名已取消");
								
								/**
								 * 发送消息（取消报名，给领队发送消息）
								 */
								Users users = usersServiceImpl.findUserById(mySignUp.getAsu_user_id());
								Map<String,Object> objectMap = new HashMap<String,Object>();
								objectMap.put("uId", userId);
								objectMap.put("uName", uName);
								objectMap.put("activeId", mySignUp.getActivityId()+"");
								objectMap.put("orderId", mySignUp.getAsu_id());
								objectMap.put("title", mySignUp.getTitle());
								objectMap.put("content", "取消原因："+aa_refund_area);
								
								messagePrivateServiceImpl.saveMessagePrivate(objectMap,users,"acCancelSignup");
								
							}else if(activeApplicant.getAa_state()==20){
								updateActiveApplicant(aaMap, activityMap, userId, aa_refund_area, mySignUp, activeApplicant, 50,i);
								
								String[] logaa_ids = new String[]{aa_ids[j]};
								//操作日志
								activeApplicantLogServiceImpl.saveActiveApplicantLog(logaa_ids, asu_id+"", mySignUp.getActivityId(), 1, "已申请取消报名，取消原因:"+aa_refund_area, userId, "50");
								activeApplicantLogServiceImpl.saveActiveApplicantLog(logaa_ids, asu_id+"", mySignUp.getActivityId(), 2, aa_refund_area, userId, "50");
								
								statemap.put(Const.SUCCESS_INFO, "已申请取消报名");
								
								/**
								 * 发送消息（申请取消报名，给领队发送消息）
								 */
								Users users = usersServiceImpl.findUserById(mySignUp.getCreateUser());
								
								Map<String,Object> objectMap = new HashMap<String,Object>();
								objectMap.put("uId", userId);
								objectMap.put("uName", uName);
								objectMap.put("activeId", mySignUp.getActivityId());
								objectMap.put("orderId", mySignUp.getAsu_id());
								objectMap.put("title", mySignUp.getTitle());
								objectMap.put("content", "取消原因："+aa_refund_area);
								
								messagePrivateServiceImpl.saveMessagePrivate(objectMap,users,"acApplyCancelSignup");
							}else if(activeApplicant.getAa_state()==40){
								statemap.put(Const.RESPONSE_STATE, "500");
								statemap.put(Const.ERROR_INFO, "该报名人报名已关闭，无法取消报名："+activeApplicant.getAa_user_name());
								return statemap;
							}else if(activeApplicant.getAa_state()==50){
								statemap.put(Const.RESPONSE_STATE, "500");
								statemap.put(Const.ERROR_INFO, "该报名人已申请取消报名，请等待领队确认："+activeApplicant.getAa_user_name());
								return statemap;
							}else{
								statemap.put(Const.RESPONSE_STATE, "500");
								statemap.put(Const.ERROR_INFO, "该报名人无需取消报名："+activeApplicant.getAa_user_name());
								return statemap;
							}
							bool = false;
						}
						
					}
					state = updateSignUp(activeApplicant, state);
				}
				
				if(!bool){
					asuMap.put("asu_id", mySignUp.getAsu_id());
					asuMap.put("asu_state", state);
					
					if(count>0){
						asuMap.put("count", count);
						asuMap.put("people", people);
					}
					
					dao.update("activeSignupMapper.updateByAsu_id", asuMap);
				}else{
					statemap.put(Const.RESPONSE_STATE, "500");
					statemap.put(Const.ERROR_INFO, "报名编号有误，请刷新后重试");
					return statemap;
				}
			}else{
				int count = 0;
				int people = 0;
				for(int i=0;i<mySignUp.getActiveApplicants().size();i++){
					ActiveApplicant activeApplicant = mySignUp.getActiveApplicants().get(i);
					
					if(activeApplicant.getAa_state()!=40&&activeApplicant.getAa_refund_state()!=100&&activeApplicant.getAa_refund_state()!=130){
						people++;
					}
					
					for(int j=0;j<aa_ids.length;j++){
						if(activeApplicant.getAa_id().equals(aa_ids[j])){
							aaMap = new HashMap<String,Object>();
							aaMap.put("userId", userId);
							//订单报名人ID
							aaMap.put("aa_id", aa_ids[j]);
							
							activityMap = new HashMap<String,Object>();
							//活动ID
							activityMap.put("id", mySignUp.getActivityId());
							
							if(activeApplicant.getAa_state()==10){
								count++;
								if(mySignUp.getAsu_account_paid()>0){
									updateActiveApplicant(aaMap, activityMap, userId, aa_refund_area, mySignUp, activeApplicant, 30,i);
									
									String[] logaa_ids = new String[]{aa_ids[j]};
									//操作日志
									activeApplicantLogServiceImpl.saveActiveApplicantLog(logaa_ids, asu_id+"", mySignUp.getActivityId(), 1, "退款成功，等待退款到账，退款原因："+aa_refund_area, userId, "100");
									activeApplicantLogServiceImpl.saveActiveApplicantLog(logaa_ids, asu_id+"", mySignUp.getActivityId(), 2, aa_refund_area, userId, "100");
									
									statemap.put(Const.SUCCESS_INFO, "取消成功，等待财务退款");
									
									/**
									 * 发送消息（退款成功，给领队发送消息）
									 */
									Users users = usersServiceImpl.findUserById(mySignUp.getAsu_user_id());
									Map<String,Object> objectMap = new HashMap<String,Object>();
									objectMap.put("uId", userId);
									objectMap.put("uName", uName);
									objectMap.put("activeId", mySignUp.getActivityId()+"");
									objectMap.put("orderId", mySignUp.getAsu_id());
									objectMap.put("title", mySignUp.getTitle());
									objectMap.put("content", "退款原因："+aa_refund_area);
									
									messagePrivateServiceImpl.saveMessagePrivate(objectMap,users,"acRefundSuccess");
								}else{
									updateActiveApplicant(aaMap, activityMap, userId, aa_refund_area, mySignUp, activeApplicant, 40,i);
									
									String[] logaa_ids = new String[]{aa_ids[j]};
									//操作日志
									activeApplicantLogServiceImpl.saveActiveApplicantLog(logaa_ids, asu_id+"", mySignUp.getActivityId(), 1, "报名已取消，取消原因："+aa_refund_area, userId, "40");
									activeApplicantLogServiceImpl.saveActiveApplicantLog(logaa_ids, asu_id+"", mySignUp.getActivityId(), 2, aa_refund_area, userId, "40");
									
									statemap.put(Const.SUCCESS_INFO, "报名已取消");
									
									/**
									 * 发送消息（取消报名，给领队发送消息）
									 */
									Users users = usersServiceImpl.findUserById(mySignUp.getAsu_user_id());
									Map<String,Object> objectMap = new HashMap<String,Object>();
									objectMap.put("uId", userId);
									objectMap.put("uName", uName);
									objectMap.put("activeId", mySignUp.getActivityId()+"");
									objectMap.put("orderId", mySignUp.getAsu_id());
									objectMap.put("title", mySignUp.getTitle());
									objectMap.put("content", "取消原因："+aa_refund_area);
									
									messagePrivateServiceImpl.saveMessagePrivate(objectMap,users,"acCancelSignup");
								}
							}else if(activeApplicant.getAa_state()==20){
								if(mySignUp.getAsu_account_payable()>0){
									updateActiveApplicant(aaMap, activityMap, userId, aa_refund_area, mySignUp, activeApplicant, 50,i);
									
									String[] logaa_ids = new String[]{aa_ids[j]};
									//操作日志
									activeApplicantLogServiceImpl.saveActiveApplicantLog(logaa_ids, asu_id+"", mySignUp.getActivityId(), 1, "已申请退款，退款原因："+aa_refund_area, userId, "80");
									activeApplicantLogServiceImpl.saveActiveApplicantLog(logaa_ids, asu_id+"", mySignUp.getActivityId(), 2, aa_refund_area, userId, "80");
									
									statemap.put(Const.SUCCESS_INFO, "已申请退款");
									
									/**
									 * 发送消息（申请退款，给领队发送消息）
									 */
									Users users = usersServiceImpl.findUserById(mySignUp.getCreateUser());
									
									Map<String,Object> objectMap = new HashMap<String,Object>();
									objectMap.put("uId", userId);
									objectMap.put("uName", uName);
									objectMap.put("activeId", mySignUp.getActivityId());
									objectMap.put("orderId", mySignUp.getAsu_id());
									objectMap.put("title", mySignUp.getTitle());
									objectMap.put("content", "退款原因："+aa_refund_area);
									
									messagePrivateServiceImpl.saveMessagePrivate(objectMap,users,"acRefund");
								}else{
									updateActiveApplicant(aaMap, activityMap, userId, aa_refund_area, mySignUp, activeApplicant, 50,i);
									
									String[] logaa_ids = new String[]{aa_ids[j]};
									//操作日志
									activeApplicantLogServiceImpl.saveActiveApplicantLog(logaa_ids, asu_id+"", mySignUp.getActivityId(), 1, "已申请取消报名，取消原因："+aa_refund_area, userId, "50");
									activeApplicantLogServiceImpl.saveActiveApplicantLog(logaa_ids, asu_id+"", mySignUp.getActivityId(), 2, aa_refund_area, userId, "50");
									
									statemap.put(Const.SUCCESS_INFO, "已申请取消报名");
									
									/**
									 * 发送消息（申请取消报名，给领队发送消息）
									 */
									Users users = usersServiceImpl.findUserById(mySignUp.getCreateUser());
									
									Map<String,Object> objectMap = new HashMap<String,Object>();
									objectMap.put("uId", userId);
									objectMap.put("uName", uName);
									objectMap.put("activeId", mySignUp.getActivityId());
									objectMap.put("orderId", mySignUp.getAsu_id());
									objectMap.put("title", mySignUp.getTitle());
									objectMap.put("content", "取消原因："+aa_refund_area);
									
									messagePrivateServiceImpl.saveMessagePrivate(objectMap,users,"acApplyCancelSignup");
								}
							}else if(activeApplicant.getAa_state()==30){
								statemap.put(Const.RESPONSE_STATE, "500");
								statemap.put(Const.ERROR_INFO, "该报名人无法申请退款："+activeApplicant.getAa_user_name());
								return statemap;
							}else if(activeApplicant.getAa_state()==50){
								if(mySignUp.getAsu_account_payable()>0){
									statemap.put(Const.RESPONSE_STATE, "500");
									statemap.put(Const.ERROR_INFO, "该报名人已申请退款，请等待领队确认："+activeApplicant.getAa_user_name());
									return statemap;
								}else{
									statemap.put(Const.RESPONSE_STATE, "500");
									statemap.put(Const.ERROR_INFO, "此订单已申请取消报名，请等待领队确认");
									return statemap;
								}
							}else{
								statemap.put(Const.RESPONSE_STATE, "500");
								statemap.put(Const.ERROR_INFO, "该报名人无需取消报名："+activeApplicant.getAa_user_name());
								return statemap;
							}
							bool = false;
						}
						
					}
					
					state = updateSignUp(activeApplicant, state);
				}
				if(!bool){
					asuMap.put("asu_id", mySignUp.getAsu_id());
					asuMap.put("asu_state", state);
					
					if(count>0){
						asuMap.put("count", count);
						asuMap.put("people", people);
					}
					
					dao.update("activeSignupMapper.updateByAsu_id", asuMap);
				}else{
					statemap.put(Const.RESPONSE_STATE, "500");
					statemap.put(Const.ERROR_INFO, "报名编号有误，请刷新后重试");
					return statemap;
				}
			}
			
			return statemap;
		}else{
			statemap.put(Const.RESPONSE_STATE, "500");
			statemap.put(Const.ERROR_INFO, "报名编号不正确!没有找到报名信息！");
			return statemap;
		}
		
	}
	
	/**
	 * 修改报名人报名状态
	 * @param aaMap
	 * @param activityMap
	 * @param userId
	 * @param aa_refund_area
	 * @param mySignUp
	 * @param activeApplicant
	 * @param aa_state
	 * @param i
	 * @throws Exception
	 */
	public void updateActiveApplicant(Map<String,Object> aaMap,Map<String,Object> activityMap,String userId,String aa_refund_area,MySignUp mySignUp,ActiveApplicant activeApplicant,int aa_state,int i) throws Exception{
		if(aa_state==30){
			aa_state = activeApplicant.getAa_state();
			//退款状态
			aaMap.put("aa_refund_state", "100");
			activeApplicant.setAa_refund_state(100);
			//退款总额
			aaMap.put("aa_refund_price", mySignUp.getAsu_account_paid());
			activeApplicant.setAa_refund_price(mySignUp.getAsu_account_paid());
		}else if(aa_state==50){
			//退款状态
			aaMap.put("aa_refund_state", "80");
			activeApplicant.setAa_refund_state(80);
		}
		
		//退款时间
		aaMap.put("aa_refund_time", new Date());
		activeApplicant.setAa_refund_time(new Date());
		//是否退款
		aaMap.put("aa_refund_flag", "1");
		activeApplicant.setAa_refund_flag(1);
		//最后修改时间
		aaMap.put("aa_last_update_time", new Date());
		activeApplicant.setAa_last_update_time(new Date());
		//最后修改用户
		aaMap.put("aa_last_update_user", userId);
		activeApplicant.setAa_last_update_user(userId);
		//退款备注
		aaMap.put("aa_refund_area", aa_refund_area);
		activeApplicant.setAa_refund_area(aa_refund_area);
		//报名状态
		aaMap.put("aa_state", aa_state);
		activeApplicant.setAa_state(aa_state);
		
		if(aa_state!=50){
			mySignUp.setAlreadyInNum(mySignUp.getAlreadyInNum()-1);
			activityMap.put("alreadyInNum", mySignUp.getAlreadyInNum());
			
			dao.update("activityMapper.updateAlreadyNum", activityMap);
		}
		dao.update("activeApplicantMapper.updateByAsu_id", aaMap);
		
		mySignUp.getActiveApplicants().set(i, activeApplicant);
	}
	
	/**
	 * 单个或多个报名人退款之后，修改报名状态
	 * @param mySignUp
	 * @throws Exception
	 */
	public int updateSignUp(ActiveApplicant activeApplicant,int state) throws Exception{
		if(activeApplicant.getAa_state()==10){
			state = 10;
		}else if(activeApplicant.getAa_state()==20){
			if(state!=10){
				state = 20;
			}
		}else if(activeApplicant.getAa_state()==30){
			if(state==0){
				state = 30;
			}
		}else if(activeApplicant.getAa_state()==40){
			if(state==0){
				state = 40;
			}
		}else if(activeApplicant.getAa_state()==50){
			if(state!=10&&state!=20){
				state = 50;
			}
		}
		return state;
	}
	
	/**
	 * 方法描述：领队查询所有的活动
	 * 返回类型：List<ActiveSignupInfo>
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<LeaderSignUp> leaderActiveManagerPage(Page<Map<String,Object>> page) throws Exception {
		return (List<LeaderSignUp>)dao.findForList("activeSignupMapper.leaderActiveManagerPage", page);
	}
	
	/**
	 * 方法描述：领队活动数量
	 */
	@Override
	public Map<String,String> leaderActiveManagerCount(Map<String,Object> map) throws Exception{
		return (Map<String,String>)dao.findForObject("activeSignupMapper.leaderActiveManagerCount", map);
	}
	
	/**
	 * 方法描述：领队查询该活动报名详情
	 * 返回类型：List<LeaderActiveSignUpsDTO>
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<MySignUp> leaderActiveSignupManagerPage(Page<MySignUp> page) throws Exception{
		return (List<MySignUp>)dao.findForList("activeSignupMapper.leaderActiveSignupManagerPage", page);
	}
	
	/**
	 * 方法描述：领队确认用户报名
	 * @param map
	 */
	@Override
	public void updateConfirmSignUp(Map<String, Object> map)throws Exception {
		
		// 判断当前执行修改操作的用户是不是该活动的发布者，防止别的领队修改参数，修改别的活动的订单状态
		Activity activity = (Activity) dao.findForObject("activityMapper.findUserOrSignUpUserNumIdByActiveId", map.get("activeId"));
		if(!(map.get("userId")+"").equals(activity.getCreateUser())){
			map.clear();
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, "您不是该活动的发起者，没有权限操作！");
			return;
		}
		
		int needupdate = (int) dao.findForObject("activeApplicantMapper.needupdateByConfirm", map.get("asu_id"));
		
		// 领队确认报名
		int num = (int) dao.update("activeApplicantMapper.confirmSignUpById", map);
		
		if(num>0){
			
			if(needupdate==num){
				dao.update("activeSignupMapper.confirmSignUpById", map);
			}
			
			// 已经报名的人数
			int sn = activity.getAlreadyInNum();
			activity.setAlreadyInNum(sn-num);
			// 已经确认的人数
			int cn = activity.getConfirmUserNum();
			activity.setConfirmUserNum(cn+num);
			
			Map<String,Integer> mapActivity = new HashMap<String,Integer>();
			
			mapActivity.put("alreadyInNum", activity.getAlreadyInNum());
			mapActivity.put("confirmUserNum", activity.getConfirmUserNum());
			mapActivity.put("id", activity.getId());
			
			// 更新人数
			dao.update("activityMapper.updateAlreadyNum", mapActivity);
		}
		
		map.clear();
		// 执行成功
		map.put(Const.RESPONSE_STATE, 200);
		map.put(Const.SUCCESS_INFO, "确认成功！");
		
	}
	
	/**
	 * 方法描述：领队确认取消用户报名
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void updateCancelSignUp(Map<String, Object> map) throws Exception {
		
		// 判断当前执行修改操作的用户是不是该活动的发布者，防止别的领队修改参数，修改别的活动的订单状态
		Activity activity = (Activity) dao.findForObject("activityMapper.findUserOrSignUpUserNumIdByActiveId", map.get("activeId"));
		if(!(map.get("userId")+"").equals(activity.getCreateUser())){
			map.clear();
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, "您不是该活动的发起者，没有权限操作！");
			return;
		}
		
		double asu_account_paid = (double) dao.findForObject("activeSignupMapper.getAsu_account_paid", map.get("asu_id")+"");
		Policyholders policyholders = policyholdersServiceImpl.getPriceTotal(map.get("asu_id")+"");
		double insurance = 0;
		if(policyholders!=null){
			insurance = policyholders.getPriceTotal();
		}
		
		double refundPrice = activeApplicantServiceImpl.getRefundPrice(map.get("asu_id")+"");
		if(Double.valueOf(map.get("refund_money")+"")>(asu_account_paid-insurance-refundPrice)){
			map.clear();
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, "退款金额不能大于付款金额(若页面显示有误，请刷新后再退款)！");
			return;
		}
		
		int needupdate = (int) dao.findForObject("activeApplicantMapper.needupdateByCancel", map.get("asu_id"));
		
		String[] aa_id = map.get("aaIds").toString().split(",");
		
		map.put("refund_money", Double.valueOf(map.get("refund_money").toString())/aa_id.length);
		
		//if(Double.valueOf(map.get("refund_money").toString())>0){
			map.put("aa_state", "50");
			map.put("aa_refund_state", "90");
			//操作日志
			activeApplicantLogServiceImpl.saveActiveApplicantLog(aa_id, map.get("asu_id")+"", map.get("activeId")+"", 3,map.get("refund_remark")+"", map.get("userId")+"", "90");
			activeApplicantLogServiceImpl.saveActiveApplicantLog(aa_id, map.get("asu_id")+"", map.get("activeId")+"", 1, "领队已同意退款", map.get("userId")+"", "90");
			
			List<ActiveSignUpPay> activeSignUpPays = activeSignupServiceImpl.findAsuIdByAccountNum(map.get("asu_id")+"");
			ActiveSignUpPay activeSignUpPay =  activeSignUpPays.get(0);
			
			/**
			 * 发送消息（领队已同意退款，给用户发送消息）
			 */
			Users users = usersServiceImpl.findUserById(activeSignUpPay.getAsu_user_id());
			Map<String,Object> objectMap = new HashMap<String,Object>();
			objectMap.put("activeId", activeSignUpPay.getAsu_active_id()+"");
			objectMap.put("orderId", activeSignUpPay.getAsu_id());
			objectMap.put("title", activeSignUpPay.getTitle());
			objectMap.put("content", "退款金额："+map.get("refund_money"));
			
			messagePrivateServiceImpl.saveMessagePrivate(objectMap,users,"acAgRefund");
		//}
//		else if(Double.valueOf(map.get("refund_money").toString())==0){
//			map.put("aa_state", "40");
//			map.put("aa_refund_state", "130");
//			//操作日志
//			activeApplicantLogServiceImpl.saveActiveApplicantLog(aa_id, map.get("asu_id")+"", map.get("activeId")+"", 1, "报名已取消", map.get("userId")+"", "40");
//		}
		
		map.put("aaIds", aa_id);
		int num = (int) dao.update("activeApplicantMapper.cancelSignUpById", map);
		
		if(num>0){
			if(needupdate==num){
//				if(Double.valueOf(map.get("refund_money").toString())==0){
//					map.put("asu_state", "40");
//				}else{
//					map.put("asu_state", "50");
//				}
				map.put("asu_state", "50");
				
				// 1.将报名状态改为[报名已取消]
				dao.update("activeSignupMapper.cancelSignUpById", map);
			}
			
			// 2.将活动报名人数减去此次取消报名人数.3.将活动确认人数减去此次取消报名人数
			// 已经报名的人数
			int sn = activity.getAlreadyInNum();
			activity.setAlreadyInNum(sn-1);
			// 已经确认的人数
			int cn = activity.getConfirmUserNum();
			activity.setConfirmUserNum(cn-1);
			
			Map<String,Integer> mapActivity = new HashMap<String,Integer>();
			
			mapActivity.put("alreadyInNum", activity.getAlreadyInNum());
			mapActivity.put("confirmUserNum", activity.getConfirmUserNum());
			mapActivity.put("id", activity.getId());
			
			// 更新人数
			dao.update("activityMapper.updateAlreadyNum", mapActivity);
		}
		
		map.clear();
		// 执行成功
		map.put(Const.RESPONSE_STATE, 200);
		map.put(Const.SUCCESS_INFO, "确认取消成功！");

	}
	
	/**
	 * 用户同意/不同意退款
	 * @throws Exception 
	 */
	@Override
	public void updateRefundByUser(Map<String,Object> map) throws Exception{
		String[] aa_id = map.get("aaIds").toString().split(",");
		
		map.put("aaIds", aa_id);
		
		List<ActiveSignUpPay> activeSignUpPays = activeSignupServiceImpl.findAsuIdByAccountNum(map.get("asu_id")+"");
		ActiveSignUpPay activeSignUpPay =  activeSignUpPays.get(0);
		
		if(map.get("isagree")==null){
			map.put("aa_refund_state", 100);
			
			//操作日志
			activeApplicantLogServiceImpl.saveActiveApplicantLog(aa_id, map.get("asu_id")+"", map.get("activeId")+"", 1, "退款处理中", map.get("userId")+"", "100");
			
			/**
			 * 发送消息（用户已确认退款金额，给领队发送消息）
			 */
			Users lead = usersServiceImpl.findUserById(activeSignUpPay.getCreateUser());
			Users users = usersServiceImpl.findUserById(activeSignUpPay.getAsu_user_id());
			Map<String,Object> objectMap = new HashMap<String,Object>();
			objectMap.put("uId", users.getuId());
			objectMap.put("uName", users.getuName());
			objectMap.put("activeId", activeSignUpPay.getAsu_active_id()+"");
			objectMap.put("orderId", activeSignUpPay.getAsu_id());
			objectMap.put("title", activeSignUpPay.getTitle());
			
			messagePrivateServiceImpl.saveMessagePrivate(objectMap,lead,"acAgRefundMoney");
			
			//退款金额
			double refundMoney = (double) dao.findForObject("activeApplicantMapper.getRefundMoney", map.get("asu_id")+"");
			
			//新增资金明细
			cashRecordServiceImpl.saveRefundRecordByActiveSignup(activeSignUpPay.getCreateUser(), 0,refundMoney, activeSignUpPay.getAsu_id());
			//更改发布者钱包信息
			balanceServiceImpl.updateMoneyByUserId(activeSignUpPay.getCreateUser(),0,-refundMoney);
			
			Map<String,Integer> mapActivity = new HashMap<String,Integer>();
			mapActivity.put("num", aa_id.length);
			mapActivity.put("id", (int)map.get("activeId"));
			
			//退款成功减去部分确认人数
			dao.update("activityMapper.updateSubtractConfirmUserNum", mapActivity);
			
			for(int i=0,len=aa_id.length;i<len;i++){
				//保险退保
				insurancesServiceImpl.orderCancel(map.get("asu_id")+"", aa_id[i]);
			}
		}else if("true".equals(map.get("isagree").toString())){
			map.put("aa_refund_state", 120);
			//操作日志
			activeApplicantLogServiceImpl.saveActiveApplicantLog(aa_id, map.get("asu_id")+"", map.get("activeId")+"", 1, "申请客服处理", map.get("userId")+"", "120");
		}else if("false".equals(map.get("isagree").toString())){
			map.put("aa_refund_state", 110);
			//操作日志
			activeApplicantLogServiceImpl.saveActiveApplicantLog(aa_id, map.get("asu_id")+"", map.get("activeId")+"", 1, "退款已取消", map.get("userId")+"", "110");
			
			/**
			 * 发送消息（用户取消退款，给领队发送消息）
			 */
			Users lead = usersServiceImpl.findUserById(activeSignUpPay.getCreateUser());
			Users users = usersServiceImpl.findUserById(activeSignUpPay.getAsu_user_id());
			Map<String,Object> objectMap = new HashMap<String,Object>();
			objectMap.put("uId", users.getuId());
			objectMap.put("uName", users.getuName());
			objectMap.put("activeId", activeSignUpPay.getAsu_active_id()+"");
			objectMap.put("orderId", activeSignUpPay.getAsu_id());
			objectMap.put("title", activeSignUpPay.getTitle());
			
			messagePrivateServiceImpl.saveMessagePrivate(objectMap,lead,"acCancelRefund");
		}
		
		dao.update("activeApplicantMapper.updateRefundByUser",map);
		
		map.clear();
		// 执行成功
		map.put(Const.RESPONSE_STATE, 200);
		map.put(Const.SUCCESS_INFO, "修改成功！");
		
	}
	
	/**
	 * 方法描述：用户确认交易完成
	 * 实现接口：@see com.op.service.usercenter.ActiveSignupService#complete(java.util.Map)
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void updateComplete(Map<String, Object> map) throws Exception {

		int asuNum = (int) dao.update("activeSignupMapper.complete", map);
		
		if(asuNum>0){
			dao.update("activeApplicantMapper.complete", map);
			
			List<ActiveApplicant> activeApplicant = (List<ActiveApplicant>) dao.findForList("activeApplicantMapper.findActiveApplicantByAsuId", map);
			
			String aa_id = "";
			for(int i=0;i<activeApplicant.size();i++){
				if(i!=0){
					aa_id+=",";
				}
				aa_id += activeApplicant.get(i).getAa_id();
			}
			
			String[] aa_ids = aa_id.split(",");
			
			//操作日志
			activeApplicantLogServiceImpl.saveActiveApplicantLog(aa_ids, map.get("asu_id")+"", map.get("activeId")+"", 1, "交易完成", map.get("userId")+"", "30");
			
			List<ActiveSignUpPay> activeSignUpPays = activeSignupServiceImpl.findAsuIdByAccountNum(map.get("asu_id")+"");
			ActiveSignUpPay activeSignUpPay =  activeSignUpPays.get(0);
			
			
			/**
			 * 发送消息（交易完成，给领队发送消息）
			 */
			Users login = usersServiceImpl.findUserById(map.get("userId")+"");
			Users users = usersServiceImpl.findUserById(activeSignUpPay.getCreateUser());
			Map<String,Object> objectMap = new HashMap<String,Object>();
			objectMap.put("uId", login.getuId());
			objectMap.put("uName", login.getuName());
			objectMap.put("activeId", activeSignUpPay.getAsu_active_id()+"");
			objectMap.put("orderId", activeSignUpPay.getAsu_id());
			objectMap.put("title", activeSignUpPay.getTitle());
			
			messagePrivateServiceImpl.saveMessagePrivate(objectMap,users,"acComplete");
			
			map.clear();
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
			map.put(Const.SUCCESS_INFO, "交易成功！");
		}else{
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "等领队确认之后才可以点交易完成！");
		}
	}
	
	/**
	 * 用户查看报名详情
	 */
	@Override
	public MySignUp selectDetails(Map<String, Object> map) throws Exception {
		return (MySignUp) dao.findForObject("activeSignupMapper.selectDetails", map);
	}
	
	/**
	 * 领队查看报名详情
	 */
	@Override
	public MySignUp selectDetailsForLeader(Map<String, Object> map) throws Exception{
		return (MySignUp) dao.findForObject("activeSignupMapper.selectDetailsForLeader", map);
	}
	
	/**
	 * 查询所有未付款订单
	 */
	@Override
	public List<ActiveSignup> findAllWaitPaySignup() throws Exception{
		return (List<ActiveSignup>) dao.findForList("activeSignupMapper.findAllWaitPaySignup");
	}
	
	/**
	 * 到时间未付款取消订单
	 */
	@Override
	public int payCloseForAsu_id(String id) throws Exception{
		return (int) dao.update("activeSignupMapper.payCloseForAsu_id", id);
	}
	
	/**
	 * 订单是否评论
	 */
	@Override
	public boolean isComment(Map<String,Object> map) throws Exception{
		boolean bool = false;
		
		int count = (int) dao.findForObject("activeSignupMapper.isComment", map);
		if(count>0){
			bool = true;
		}
		
		return bool;
	}
	
}
