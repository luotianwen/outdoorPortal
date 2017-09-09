package com.op.controller.activity;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.op.dto.activity.draft.DraftResultsDTO;
import com.op.dto.activity.edit.ActivityDraftDTO;
import com.op.dto.activity.insertActivity.ActivityBaseInfoDTO;
import com.op.dto.activity.insertActivity.ActivityRestsInfoDTO;
import com.op.dto.activity.insertActivity.cost.ActivityCostDTO;
import com.op.dto.activity.insertActivity.line.InsertLineDTO;
import com.op.dto.activity.queryActivity.QueryActivity;
import com.op.dto.activity.update.UpdateActiveDTO;
import com.op.dto.usercenter.keeper.MyActiveInfoDTO;
import com.op.dto.usercenter.keeper.MyActiveResultDTO;
import com.op.dto.usercenter.leader.activeManager.LeaderActivesDTO;
import com.op.entity.channels.Channels;
import com.op.entity.classification.Classification;
import com.op.entity.correlationCost.CorrelationCost;
import com.op.entity.suitablecrowd.SuitableCrowd;
import com.op.entity.usercenter.LinkSignUpUser;
import com.op.entity.users.Users;
import com.op.entity.zd.ActiveChildrenAge;
import com.op.entity.zd.ActiveDifficultyType;
import com.op.entity.zd.Traffic;
import com.op.plugin.page.Page;
import com.op.service.activity.ActivityService;
import com.op.service.behaviour.BrowsingRecordService;
import com.op.service.channels.ChannelsService;
import com.op.service.classification.ClassificationService;
import com.op.service.correlationCost.CorrelationCostService;
import com.op.service.follow.FollowActivityService;
import com.op.service.lines.LinesService;
import com.op.service.suitablecrowd.SuitableCrowdService;
import com.op.service.usercenter.ActiveSignupService;
import com.op.service.usercenter.LinkSignUpUserService;
import com.op.service.zd.ActiveChildrenAgeService;
import com.op.service.zd.ActiveDifficultyTypeService;
import com.op.service.zd.TrafficService;
import com.op.util.Const;
import com.op.util.DateUtil;
import com.op.util.JsonUitl;
import com.op.util.mail.EmailUtil;
/** 
 * 活动发布信息表(activity)实体类
 * @author MrYan
 * @version Revision: 1.00 
 *  Date: 2015-11-30 11:16:49 
 */ 
@Controller
@RequestMapping(value="/huodong")
public class ActivityController{
	
	// servlet 上下文
	String contextPath = null;
	
    // 模板文件名
    String activityFtlPath = null;
    
	/**
	 * 报名订单
	 */
	@Resource(name="activeSignupServiceImpl")
	ActiveSignupService activeSignupServiceImpl;
	
	/**
	 * 活动service
	 */
	@Resource(name="activityServiceImpl")
	ActivityService activityServiceImpl;
	
	/**
	 * 活动频道
	 */
	@Resource(name="channelsServiceImpl")
	ChannelsService channelsServiceImpl;
	
	/**
	 *  活动类型
	 */
	@Resource(name="classificationServiceImpl")
	ClassificationService classificationServiceImpl;
	
	/**
	 *  适合人群
	 */
	@Resource(name="suitableCrowdServiceImpl")
	SuitableCrowdService suitableCrowdServiceImpl;
	
	/**
	 *  活动等级
	 */
	@Resource(name="activeDifficultyTypeServiceImpl")
	ActiveDifficultyTypeService activeDifficultyTypeServiceImpl;
	
	/**
	 *  年龄段
	 */
	@Resource(name="activeChildrenAgeServiceImpl")
	ActiveChildrenAgeService activeChildrenAgeServiceImpl;
	
	/**
	 *  线路
	 */
	@Resource(name="linesServiceImpl")
	LinesService linesServiceImpl;
	
	/**
	 *  常用报名人
	 */
	@Resource(name="linkSignUpUserServiceImpl")
	LinkSignUpUserService LinkSignUpUserServiceImpl;
	
	/**
	 *  用户浏览记录
	 */
	@Resource(name="browsingRecordServiceImpl")
	BrowsingRecordService browsingRecordServiceImpl;
	
	@Resource(name="emailUtil")
	EmailUtil emailUtil;

	/**
	 * 收藏
	 */
    @Resource(name="followActivityServiceImpl")
    FollowActivityService followActivityService;
    
    /**
     * 交通工具
     */
    @Resource(name="trafficServiceImpl")
    TrafficService trafficServiceImpl;
	
    
    /**
     * 交通工具
     */
    @Resource(name="correlationCostServiceImpl")
    CorrelationCostService correlationCostServiceImpl;
    
    /**
     * log日志
     */
    Logger log = Logger.getLogger(this.getClass());
	 
	/**
	 * 获取活动发布信息之"从表"信息,从表:channels,classification,Lines,SuitableCrowd......
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/release")
	public ModelAndView activityInfo(HttpSession session,ModelAndView mv) throws Exception{
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		// 判断是否拥有添加活动的身份（1：个人发布者；2：企业；3：普通用户；4：后台管理用户）
		if(user.getuType() != 1 && user.getuType() != 2){
			mv.clear();
			mv.setViewName("redirect:/activePrompt.html");
			return mv;
		}
		
		// 查询发布页面所需要加载的数据
		loadReleaseData(mv, user.getuId(), true);

		this.setHeaderMap(mv, "活动");

		mv.addObject("user", user);
		mv.setViewName("activity/release/release-activity");
		return mv;
	}
	
	/**
	 * 方法描述：添加活动基础信息
	 * 返回类型：Map<String,Object>
	 * @param dto
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/baseInfo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> insertBaseInfo(ActivityBaseInfoDTO dto,HttpSession session){
		// response对象
		Map<String,Object> map = new HashMap<String,Object>();
		
		// 封装UserID
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		dto.setUserId(user.getuId());
		
		try {
			activityServiceImpl.insertBaseInfo(dto, map ,session);
		} catch (Exception e) {
			log.error("添加活动基础信息异常！", e);
			// 删除已经复制的图片
			/*List<String> list = (List<String>) map.get("newPathLocationUrl");
			if(list != null && list.size()>0){
				for(String str:list){
					new File(str).delete();	
				}
			}*/
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, map.get(Const.ERROR_INFO)==null?"发生异常，请稍后操作或联系我们客服，给您带来的不变敬请谅解!":map.get(Const.ERROR_INFO));
		}
		
		return map;
	}
	
	/**
	 * 方法描述：活动其他信息保存（描述、介绍、特色、费用、退款说明、活动须知...）
	 * 返回类型：Map<String,Object>
	 * @return
	 */
	@RequestMapping(value="/restsInfo")
	@ResponseBody
	public Map<String,Object> insertRestsInfo(ActivityRestsInfoDTO dto,HttpSession session){
		// response对象
		Map<String,Object> map = new HashMap<String,Object>();
		
		// 封装UserID
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		dto.setuId(user.getuId());
		dto.setuType(user.getuType());
		
		try {
			activityServiceImpl.insertRestsInfo(dto, map);
		} catch (Exception e) {
			log.error("活动其他信息保存异常", e);
			map.clear();
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, map.get(Const.ERROR_INFO)==null?"发生异常，请稍后操作或联系我们客服，给您带来的不变敬请谅解!":map.get(Const.ERROR_INFO));
		}
		
		return map;
	}
	
	
	/**
	 * 方法描述：添加活动线路
	 * 返回类型：Map<String,Object>
	 * @param dto
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/insertLine")
	@ResponseBody
	public Map<String,Object> insertLine(InsertLineDTO dto,HttpSession session){
		// response对象
		Map<String,Object> map = new HashMap<String,Object>();
		
		// 封装UserID
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		dto.setL_create_user(user.getuId());
		
		try {
			activityServiceImpl.insertLine(dto, map ,session);
		} catch (Exception e) {
			log.error("添加活动线路异常！", e);
			// 删除已经复制的图片
			/*List<String> list = (List<String>) map.get("newPathLocationUrl");
			if(list != null && list.size()>0){
				for(String str:list){
					new File(str).delete();	
				}
			}*/
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, map.get(Const.ERROR_INFO)==null?"发生异常，请稍后操作或联系我们客服，给您带来的不变敬请谅解!":map.get(Const.ERROR_INFO));
		}
		
		return map;
	}
	 

	/**
	 * 方法描述：添加活动额外费用
	 * 返回类型：Map<String,Object>
	 * @param dto
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/insertCost")
	@ResponseBody
	public Map<String,Object> insertCost(HttpSession session,ActivityCostDTO dto){
		// response对象
		Map<String,Object> map = new HashMap<String,Object>();
		
		// 封装UserID
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		dto.setAcp_create_user(user.getuId());
		
		try {
			activityServiceImpl.insertCost(dto, map);
		} catch (Exception e) {
			log.error("添加活动额外费用异常！", e);
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, map.get(Const.ERROR_INFO)==null?"发生异常，请稍后操作或联系我们客服，给您带来的不变敬请谅解!":map.get(Const.ERROR_INFO));
		}
		
		return map;
	}
    
	/**
	 * 方法描述：活动详情
	 * 返回类型：ModelAndView
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/info/{id}")
	public ModelAndView info(@PathVariable(value="id") String id,String type,ModelAndView mv,HttpSession session){
		try {
			
			// 当前操作用户
			Users user = (Users) session.getAttribute(Const.SESSION_USER);
	    	
			String userId = "";
			if(user!=null){
				userId = user.getuId();
			}
			
			// 判断当前用户是否登录
			/*if(user != null){
				// 保存浏览记录
				browsingRecordServiceImpl.addBrowsingRecord(user.getuId(), id, 1);
			}
			
		    // 活动存在
		    mv.setViewName("activity/detail/"+id);*/
			
			Map<String,String> map = activityServiceImpl.findActiveState(id);
			if("5".equals(map.get("state"))||"15".equals(map.get("state"))||userId.equals(map.get("createUser"))||"admin".equals(type)){
				if(StringUtils.isEmpty(contextPath)){
				    // servlet 上下文
			    	contextPath = session.getServletContext().getRealPath("/");
			    	
			    	// 模板文件名
			    	activityFtlPath = "static/ftl/activity/activity-show.html";
				}
		    	
			    // 要生成的目标目录及文件
			    String targetFile = "/view/activity/detail/"+id+".html";
			    
			    // 创建该文件对象判断是否存在
			    File file = new File(contextPath+targetFile);
			    
			    // 该文件不存在
			    //if(!file.exists()){
			    	// 后台调取数据生成模板文件
					if(activityServiceImpl.detailById(id ,activityFtlPath,targetFile ,contextPath)){
						// 判断当前用户是否登录
						if(user != null){
							// 保存浏览记录
							//browsingRecordServiceImpl.addBrowsingRecord(user.getuId(), id, 1);
						}
					    // 活动存在
					    mv.setViewName("activity/detail/"+id);
					}else{
					    // 活动不存在
						mv.setViewName("not");
				    }
			    /*}else{
			    	// 判断当前用户是否登录
					if(user != null){
						// 保存浏览记录
						browsingRecordServiceImpl.addBrowsingRecord(user.getuId(), id, 1);
					}
				    // 活动存在
				    mv.setViewName("activity/detail/"+id);
			    }*/
			}else{
				mv.setViewName("not");
			}
			
			

		} catch (Exception e) {
			log.error("活动详情出错",e);
		}
	    return mv;
	}
	
	
	/**
	 * 获取列表
	 * @param page
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping("/test")
	public String test(String str) throws Exception{
		System.err.println("*-----------------------------*"+str);
		JsonUitl ju = new JsonUitl();
		//activityServiceImpl.saveActivity(ju.json(str));  
		
	   	// 取得velocity的上下文context
		Map<String,Object> parameter = new HashMap<String,Object>();
		
		System.err.println("*---------------《邮箱验证码："+emailUtil.sendCheckEmail("2355566242@qq.com","EmailCheck")+"》--------------*");
		emailUtil.sendEmail("2355566242@qq.com","Welcome",null);
		parameter.put("date", DateUtil.getTime());
		parameter.put("orderNumber", "798878668");
		emailUtil.sendEmail("2355566242@qq.com","order",parameter);
		
		return str;
	}
	
	/**
	 * 方法描述：活动报名页面
	 * 返回类型：ModelAndView
	 * @param activeId
	 * @param mv
	 * @param session
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="signUp")
	public ModelAndView signUp(@RequestParam(value="activeId") String activeId,ModelAndView mv,HttpSession session){
		try {
			Users user = (Users) session.getAttribute(Const.SESSION_USER);
			if(null==user.getRelation()){
				user.setRelation("");
			}
			// 查询是否已经报名过该活动
			/*int count = activityServiceImpl.findCountByUserAndActiveId(activeId+"", user.getuId());
			if(count > 0){
				// 已经报名的活动跳转【我的报名】页面，并且查询出该活动的报名信息
				mv.addObject("asu_active_id", activeId);
				mv.addObject("isAlreadySignup", "yes");
				mv.setViewName("redirect:/activeSignup/selectActive.html");
				return mv;
			}*/
			
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
			
			// 查询常用报名人管理
			List<LinkSignUpUser> users = LinkSignUpUserServiceImpl.selectUser(user.getuId());
			mv.addObject("user", user);
			mv.addObject("users", users);
			mv.addObject("queryActivity", queryActivity);
			mv.addObject("neednumber", queryActivity.getNeedUserNum()-queryActivity.getConfirmUserNum());
			
			
			setHeaderMap(mv, "活动");
			
			mv.setViewName("activity/order/buy");
		} catch (Exception e) {
			log.error("signup我要预定异常！！！！！！！！", e);
		}
		return mv;
	}
	
	/**
	 * 方法描述：查询领队所发布的活动列表
	 * 返回类型：ModelAndView
	 * @param mv 传递数据渲染模板对象
	 * @param session 用户回合信息
	 * @param page 分页
	 * @param sa 查询对象
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="actives")
	public ModelAndView activesByLeaderId(ModelAndView mv,HttpSession session
			,Page<LeaderActivesDTO> page,LeaderActivesDTO sa) throws Exception{
		// 当前登录用户
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		// 根据当前登录用户查询该用户发布的活动列表
		sa.setCreateUserId(user.getuId());
		
		page.setT(sa);
		// 查询数据
		List<LeaderActivesDTO> lsa = activityServiceImpl.findByUserId(page);
		
		mv.addObject("sa", sa);
		mv.addObject("lsa", lsa);
		mv.addObject("page", page);
		mv.setViewName("usercenter/leader/activeManager/leaderActives");
		return mv;
	}
	

	/**
	 * 方法描述：活动报名列表
	 * 返回类型：Map<String,Object>
	 * @param page
	 * @param activityId 活动ID
	 * @return
	 */
	@RequestMapping(value="/activeBuyer",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> activeBuyer(Page<String> page,
			@RequestParam(value="activityId") String activityId){
		// response对象
		Map<String,Object> response = new HashMap<String,Object>();
		
		page.setT(activityId);
		
		try {
			page.setResultList(activeSignupServiceImpl.findSignUpByActivityId(page));
			response.put("page", page);
		} catch (Exception e) {
			e.printStackTrace();
			response.put(Const.RESPONSE_STATE, "500");
			response.put(Const.ERROR_INFO, "查询报名列表异常，请稍后重试，给您带来不便敬请谅解!");
		}
		
		response.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		return response;
	}
	
	/**
	 * 方法描述：提交审核
	 * 返回类型：Map<String,String>
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/submit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> submitAudit(@RequestParam(value="id") String id,
			HttpSession session){
		Map<String,String> map = new HashMap<String,String>();
		// 当前登录用户
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		map.put("userId", user.getuId());
		map.put("id", id);
		map.put("uType", user.getuType()+"");
		
		try {
			activityServiceImpl.submitAudit(map);
			
			
			if(StringUtils.isEmpty(contextPath)){
		    	
			    // servlet 上下文
		    	contextPath = session.getServletContext().getRealPath("/");
		    	
		    	// 模板文件名
		    	activityFtlPath = "static/ftl/activity/activity-show.html";
			}
	    	
		    // 要生成的目标目录及文件
		    String targetFile = "/view/activity/detail/"+id+".html";
		    
	    	// 后台调取数据生成模板文件
			activityServiceImpl.detailById(id ,activityFtlPath,targetFile ,contextPath);
		} catch (Exception e) {
			log.error("提交活动审核异常!", e);
			map.clear();
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, map.get(Const.ERROR_INFO)==null?"发生异常，请稍后操作或联系我们客服，给您带来的不变敬请谅解!":map.get(Const.ERROR_INFO));
		}
		
		return map;
	}
	
	/**
	 * 方法描述：个人中心→我的管家→我的活动
	 * 返回类型：ModelAndView
	 * @param mv
	 * @param session
	 * @param type	搜索类型（1：去过的（普通用户）/已经结束的（领队 or 俱乐部）  2：进行中的  3：待进行）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myActive")
	public ModelAndView info(ModelAndView mv,HttpSession session,
			@RequestParam(value="type",defaultValue="3") int type,
			Page<String> page) throws Exception{
		// 当前登录用户
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		// 根据用户ID查询
		page.setT(user.getuId());
		page.setPageSize(6);
		
		// 查询我的活动
		MyActiveResultDTO dto = activityServiceImpl.myActive(user.getuType(),type,page);
		
		// 查询数据
		mv.addObject("dto", dto);
		
		// 分页
		mv.addObject("page", page);
		
		// 搜索类型
		mv.addObject("type", type);
		
		// 个人中心需要数据
		setUserCenterHeaderMap(mv, "我的管家","我的活动");
		mv.setViewName("usercenter/housekeeper/activity/my-activity");
		return mv;
	}
	
	/**
	 * 方法描述：个人中心→我的管家→活动草稿
	 * 返回类型：ModelAndView
	 * @param mv
	 * @param session
	 * @param type	搜索类型（1：去过的（普通用户）/已经结束的（领队 or 俱乐部）  2：进行中的  3：待进行）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/uCenterDrafts")
	public ModelAndView info(ModelAndView mv,HttpSession session) throws Exception{
		// 当前登录用户
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		
		// 查询我的活动
		List<MyActiveInfoDTO> dto = activityServiceImpl.myDraftActivity(user.getuId());
		
		// 查询数据
		mv.addObject("dto", dto);
		//TODO
		// 个人中心需要数据
		setUserCenterHeaderMap(mv, "我的管家","活动草稿");
		mv.setViewName("usercenter/housekeeper/draft/draft-activity");
		return mv;
	}


	/**
	 * 方法描述：设置个人中心首页需要的数据
	 * 返回类型：void
	 * @param mv
	 */
	public void setUserCenterHeaderMap(ModelAndView mv,String center,String left){
		Map<String,Object> map = new HashMap<String,Object>();
		// 当前导航标签
		map.put("center", center);
		//当前左侧标签
		map.put("left", left);
		mv.addObject("map", map);
	}


	/**
	 * 方法描述：设置个人中心首页需要的数据
	 * 返回类型：void
	 * @param mv
	 */
	public void setHeaderMap(ModelAndView mv,String ht){
		Map<String,Object> map = new HashMap<String,Object>();
		// 当前导航标签
		map.put("ht", ht);
		mv.addObject("map", map);
	}
	
	/**
	 * 方法描述：展示活动草稿箱列表
	 * 返回类型：ModelAndView
	 * @param session
	 * @param mv
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/drafts")
	public ModelAndView showDraftActivityList(HttpSession session,
			ModelAndView mv,
			Page<String> page) throws Exception{
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		page.setT(user.getuId());
		page.setPageSize(3);
		
		List<DraftResultsDTO> list = activityServiceImpl.showDraftActivityList(page);
		
		mv.addObject("list", list);
		mv.addObject("page", page);
		mv.setViewName("activity/release/my-drafts");
		return mv;
	}
	
	/**
	 * 方法描述：删除活动草稿
	 * 返回类型：Map<String,String>
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/deleteDraft/{id}")
	@ResponseBody
	public Map<String,String> deleteDraft(@PathVariable(value="id") String id,
			HttpSession session){
		Map<String,String> map = new HashMap<String,String>();
		// 当前登录用户
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		map.put("userId", user.getuId());
		map.put("id", id);
		
		try {
			
			activityServiceImpl.deleteDraft(map);
		} catch (Exception e) {
			log.error("删除活动草稿!", e);
			map.clear();
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, map.get(Const.ERROR_INFO)==null?"发生异常，请稍后操作或联系我们客服，给您带来的不变敬请谅解!":map.get(Const.ERROR_INFO));
		}
		
		return map;
	}

	
	/**
	 * 方法描述：编写活动草稿信息
	 * 返回类型：ModelAndView
	 * @param session
	 * @param mv
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/editDraft/{id}")
	public ModelAndView editDraft(HttpSession session,
			@PathVariable(value="id") String activityId,
			ModelAndView mv){
		try {
			Users user = (Users) session.getAttribute(Const.SESSION_USER);
			// 判断是否拥有添加活动的身份（1：个人发布者；2：企业；3：普通用户；4：后台管理用户）
			if(user.getuType() != 1 && user.getuType() != 2){
				mv.clear();
				mv.setViewName("redirect:/activePrompt.html");
				return mv;
			}
			
			Map<String,String> map = new HashMap<String,String>();
			map.put("userId", user.getuId());
			map.put("activityId", activityId);
			
			// 查询草稿活动
			ActivityDraftDTO dto = activityServiceImpl.editDraft(map);
			
			if(dto!=null){
				// 查询发布页面所需要加载的数据
				loadReleaseData(mv, user.getuId(), true);
		
				mv.addObject("dto", dto);
				mv.addObject("user", user);
				mv.setViewName("activity/again/again-release");
			}else{
				mv.setViewName("not");
			}
		} catch (Exception e) {
			log.error("编写活动草稿信息异常", e);
			mv.setViewName("500.html");
		}
		mv.setViewName("activity/edit/editDraftRelease");
		return mv;
	}
	
	/**
	 * 方法描述：再次发布活动
	 * 返回类型：ModelAndView
	 * @param session
	 * @param mv
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/againRelease/{id}")
	public ModelAndView againRelease(HttpSession session,
			@PathVariable(value="id") String activityId,
			ModelAndView mv){
		try {
			Users user = (Users) session.getAttribute(Const.SESSION_USER);
			Map<String,String> map = new HashMap<String,String>();
			map.put("userId", user.getuId());
			map.put("activityId", activityId);
			
			// 查询草稿活动
			ActivityDraftDTO dto = activityServiceImpl.againRelease(map);
			
			if(dto!=null){
				// 查询发布页面所需要加载的数据
				loadReleaseData(mv, user.getuId(), true);
		
				mv.addObject("dto", dto);
				mv.addObject("user", user);
				mv.setViewName("activity/again/again-release");
			}else{
				mv.setViewName("not");
			}
			
		} catch (Exception e) {
			log.error("再次发布活动异常", e);
			mv.setViewName("500");
		}
		return mv;
	}
	

	
	/**
	 * 方法描述：
	 * 返回类型：void
	 * @param mv
	 * @param uId	用户ID
	 * @param isDraft	是否查询草稿箱
	 * @throws Exception
	 */
	void loadReleaseData(ModelAndView mv, String uId, boolean isDraft) throws Exception{

		// 查询频道信息
		List<Channels> listChannels = channelsServiceImpl.selectList();
		
		// 查询活动类型信息
		List<Classification> listClassification = classificationServiceImpl.selectList();
		// 查询适合人群信息
		List<SuitableCrowd> listSuitableCrowd = suitableCrowdServiceImpl.selectList();
		// 活动难度等级
		List<ActiveDifficultyType> listActiveDifficultyType = activeDifficultyTypeServiceImpl.selectList();
		// 适合儿童的活动年龄阶段
		List<ActiveChildrenAge> listActiveChildrenAge = activeChildrenAgeServiceImpl.selectList();
		// 交通工具
		List<Traffic> listTraffic = trafficServiceImpl.allTraffic();
		// 额外费用
		List<CorrelationCost> cost = correlationCostServiceImpl.findAllCost();

		if(isDraft){
			// 草稿箱
			int draftNum = activityServiceImpl.draft(uId);
			mv.addObject("draftNum", draftNum);
		}
		
		mv.addObject("cost", cost);
		mv.addObject("listTraffic",listTraffic);
		mv.addObject("listChannels",listChannels);
		mv.addObject("listSuitableCrowd",listSuitableCrowd);
		mv.addObject("listClassification",listClassification);
		mv.addObject("listActiveChildrenAge",listActiveChildrenAge);
		mv.addObject("listActiveDifficultyType",listActiveDifficultyType);
		
	}
	
	/**
	 * 方法描述：选择线路地图
	 * 返回类型：ModelAndView
	 * @param mv
	 * @param showLineAddressId
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping(value="/chooseActiveLine")
	public ModelAndView chooseActiveLine(ModelAndView mv,
			@RequestParam(value="showLineAddressId") String showLineAddressId,
			@RequestParam(value="start",required=false) String start,
			@RequestParam(value="end",required=false) String end){
		mv.addObject("end", end);
		mv.addObject("start", start);
		mv.addObject("showLineAddressId", showLineAddressId);
		mv.setViewName("activity/search/chooseActiveLine");
		return mv;
	}
	
	/**
	 * 方法描述：查询活动数据（更新活动的最新数据）
	 * 返回类型：Map<String,Object>
	 * @param id
	 * @return
	 */
	@RequestMapping(value="updateActivityData",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateActivityData(@RequestParam(value="id") String id){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			// 当前登录用户
			Users user = (Users) SecurityUtils.getSubject().getSession().getAttribute(Const.SESSION_USER);
			UpdateActiveDTO dto = null;
			// 查询活动最新的字段
			dto = activityServiceImpl.updateActivityData(id);
			
			// 当前时间（倒计时）
			dto.setCurrentTime(new java.util.Date());
			
			// 查询是否收藏该活动
			if(user != null){
				dto.setActivityId(id);
				dto.setUserId(user.getuId());
				dto.setuName(user.getuName());
				dto.setuType(user.getuType()+"");
				dto.setuHeadImg(user.getuHeadImg());
				dto.setFollow(followActivityService.isFollow(dto));
			}
			
			map.put("info", dto);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			log.error("查询活动数据（更新模板）异常", e);
			map.put(Const.RESPONSE_STATE, 500);
		}
		
		return map;
	}
	
	/**
	 * 方法描述：选择保险跳转
	 * 返回类型：ModelAndView
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/selectInsure")
	public ModelAndView selectInsure(ModelAndView mv){
		mv.setViewName("activity/release/selectInsure");
		return mv;
	}
	
	/**
	 * 活动搜索页面查看大图
	 */
	@RequestMapping("/bigMap")
	public ModelAndView bigMap(ModelAndView mv,String center){
		mv.addObject("center", center);
		mv.setViewName("activity/search/bigMap");
		
		return mv;
	}
	
	/**
	 * 查询活动模板，再次发布活动
	 */
	@RequestMapping("/template")
	public ModelAndView template(ModelAndView mv,HttpSession session,Page<String> page){
		// 当前登录用户
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		// 根据用户ID查询
		page.setT(user.getuId());
		page.setPageSize(6);
		
		try {
			// 查询我的活动
			List<MyActiveInfoDTO> list = activityServiceImpl.findActiveTemplate(page);
			// 查询数据
			mv.addObject("list", list);
			
			// 分页
			mv.addObject("page", page);
			
			// 个人中心需要数据
			setUserCenterHeaderMap(mv, "我的管家","活动模板");
			mv.setViewName("usercenter/housekeeper/template/template-activity");
		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("500");
		}
		
		return mv;
	}
	
	/**
	 * 添加活动模板
	 */
	@RequestMapping("saveTemplate")
	@ResponseBody
	public Map<String,String> saveTemplate(String id,HttpSession session){
		Map<String,String> map = new HashMap<String,String>();
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		
		map.put("userId", user.getuId());
		map.put("id", id);
		
		try {
			activityServiceImpl.saveTemplate(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, map.get(Const.ERROR_INFO)==null?"发生异常，请稍后操作或联系我们客服，给您带来的不变敬请谅解!":map.get(Const.ERROR_INFO));
		}
		
		return map;
	}
	
	
	/**
	 * 删除活动模板
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/deleteTemplate")
	@ResponseBody
	public Map<String,String> deleteTemplate(String id,HttpSession session){
		Map<String,String> map = new HashMap<String,String>();
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		
		map.put("userId", user.getuId());
		map.put("id", id);
		
		try {
			activityServiceImpl.deleteTemplate(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, map.get(Const.ERROR_INFO)==null?"发生异常，请稍后操作或联系我们客服，给您带来的不变敬请谅解!":map.get(Const.ERROR_INFO));
		}
		
		return map;
	}
	
	/**
	 * 申请关闭活动
	 */
	@RequestMapping("/closeActive")
	@ResponseBody
	public Map<String,String> closeActive(String id,HttpSession session){
		Map<String,String> map = new HashMap<String,String>();
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		
		map.put("userId", user.getuId());
		map.put("id", id);
		
		try {
			activityServiceImpl.closeActive(map);
		} catch (Exception e) {
			log.error("申请关闭活动方法异常！！！！！！！！", e);
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, map.get(Const.ERROR_INFO)==null?"发生异常，请稍后操作或联系我们客服，给您带来的不变敬请谅解!":map.get(Const.ERROR_INFO));
		}
		
		return map;
	}
	
	
}

