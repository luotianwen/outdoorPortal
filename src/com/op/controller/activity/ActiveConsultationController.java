package com.op.controller.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.op.controller.BaseController;
import com.op.dto.activity.consultation.ReplyDTO;
import com.op.dto.activity.consultation.SearchConsultationDTO;
import com.op.dto.activity.reply.ReplyActiveDTO;
import com.op.dto.activity.reply.ReplyDataDTO;
import com.op.entity.activity.ActiveConsultation;
import com.op.entity.activity.ActiveConsultationReply;
import com.op.entity.users.Users;
import com.op.plugin.page.Page;
import com.op.service.activity.ActiveConsultationService;
import com.op.service.activity.ActivityService;
import com.op.service.recentVisit.RecentVisitService;
import com.op.service.userFollow.UserFollowService;
import com.op.service.users.UsersService;
import com.op.solr.SolrPage;
import com.op.solr.util.ActivityConsultSearchService;
import com.op.util.Const;
/** 
 * 活动咨询信息表(activeConsultation)实体类
 * @author Yan
 * @version Revision: 1.00 
 *  Date: 2015-12-30 11:19:08 
 */ 
@Controller
@RequestMapping(value="/activeConsultation")
public class ActiveConsultationController extends BaseController{
	
	Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 评论service
	 */
	@Resource(name="activeConsultationServiceImpl")
	ActiveConsultationService activeConsultationServiceImpl;
	
	/**
	 * 资讯嘻嘻service
	 */
	@Resource(name="activityConsultSearchService")
	ActivityConsultSearchService activityConsultSearchService;
	
	/**
	 * 活动service
	 */
	@Resource(name="activityServiceImpl")
	ActivityService activityServiceImpl;
	
	/**
	 * 最近访问
	 */
	@Resource(name="recentVisitServiceImpl")
	RecentVisitService recentVisitServiceImpl;
	
	/**
	 * 用户
	 */
	@Resource(name="usersServiceImpl")
	UsersService usersService;
	
	/**
	 * 用户关注
	 */
	@Resource(name="userFollowServiceImpl")
	UserFollowService userFollowServiceImpl;
	
	/**
	 * 方法描述：保存评论回复
	 * 返回类型：Map<String,Object>
	 * @param acr_ac_id 评论表ID
	 * @param acr_comment 回复内容
	 * @param acr_ac_user_id 被回复用户ID
	 * @return
	 */
	@RequestMapping(value="scr")
	@ResponseBody
	public Map<String,Object> saveActiveConsultationReply(ActiveConsultationReply acr,HttpSession session){
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
			Users user = (Users) session.getAttribute(Const.SESSION_USER);
			activeConsultationServiceImpl.saveConsultationReply(acr,user);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
			map.put("acr", acr);
		} catch (Exception e) {
			log.debug("活动详情页面保存评论回复异常!", e);
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, "保存异常，请稍后重试");
		}
		return map;
	}
	
	/**
	 * 方法描述：保存用户评论
	 * 返回类型：Map<String,Object>
	 * @param ac
	 * @param session
	 * @return
	 */
	@RequestMapping(value="sc",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> saveActiveConsultation(ActiveConsultation ac
			,HttpSession session
			,@RequestParam(value="activeId") int ac_active_id
			,@RequestParam(value="value") String ac_comment){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Users user = (Users) session.getAttribute(Const.SESSION_USER);
			
			// 评论人ID
			ac.setAc_create_user_id(user.getuId());
			// 活动ID
			ac.setAc_active_id(ac_active_id);
			// 提问内容
			ac.setAc_comment(ac_comment);
			
			activeConsultationServiceImpl.saveConsultation(ac);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			log.debug("活动详情页面保存用户评论异常!", e);
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, "保存异常，请稍后重试");
		}
		
		return map;
	}
	
	
	/**
	 * 方法描述：加载更多最新提问信息
	 * 返回类型：Page<AjaxLoadMoreConsultationDto>
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/loadMore/{type}")
	@ResponseBody
	public Map<String,Object> loadMoreNew(Page<Map<String,Object>> page,
			@RequestParam(value="activeId") int activeId,
			@PathVariable(value="type") String type,
			HttpSession session){
		// 返回对象
		Map<String,Object> map = new HashMap<String,Object>();
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		
		try {
			// 查询数据
			map.put("activeId", activeId);
			map.put("type", type);
			map.put("userId", user == null?null:user.getuId());
			
			page.setT(map);
			
			activeConsultationServiceImpl.findConsultation(page);
			
			page.setT(null);
			map.clear();
			map.put("page", page);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			log.debug("活动详情页面加载更多最新提问信息异常!", e);
			map.clear();
			map.put(Const.RESPONSE_STATE, 500);
		}
		return map;
	}
	
	
	/**
	 * 方法描述：咨询点赞
	 * 返回类型：Map<String,Object>
	 * @param session
	 * @param ac_Id
	 * @return
	 */
	@RequestMapping(value="/praises")
	@ResponseBody
	public Map<String,Object> praises(HttpSession session,
			@RequestParam(value="ac_id") String ac_id,
			@RequestParam(value="type") String type){
		Map<String,Object> map = new HashMap<String,Object>();
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		try {
			// 操作类型
			map.put("type", type);
			// 咨询数据ID
			map.put("ac_id", ac_id);
			// 当前用户ID
			map.put("userId", user.getuId());
			activeConsultationServiceImpl.cdPraises(map);
		} catch (Exception e) {
			log.debug("活动详情页面对咨询点赞异常!", e);
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, "操作异常！");
		}
		return map;
	}
	
	
	/**
	 * 方法描述：查询咨询内容
	 * 返回类型：Map<String,Object>
	 * @param search
	 * @return
	 */
	@RequestMapping(value="/selectConsultation")
	@ResponseBody
	public Map<String,Object> consulation(SearchConsultationDTO search,SolrPage page,HttpSession session){
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			activityConsultSearchService.getConsultSearchResults(search, page);
			
			// 查询关联的点赞信息
			if(user != null && !StringUtils.isEmpty(user.getuId()) && ((List<?>)page.getResult()).size()>0){
				map.put("userId", user.getuId());
				map.put("page", page);
				activeConsultationServiceImpl.findPraisesByUserIdAndConsultationId(map);
			}
			
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
			map.put("page", page);
		} catch (Exception e) {
			map.clear();
			log.debug("活动详情页面查询咨询内容异常!", e);
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, "操作异常！");
		}
		return map;
	}
	
	/**
	 * 方法描述：个人中心	→	我的问答
	 * 返回类型：ModelAndView
	 * @param session
	 * @param mv
	 * @param page
	 * @param type	发布者查询类型（1：我来解答（发布者）；10：我的回答（发布者）
	 * @param uct	用户提问查询类型（null：所有问题；0：待回复；1：已回复；）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/consultation")
	public ModelAndView myConsultation(HttpSession session,ModelAndView mv,Page<Map<String,String>> page,String type,
			@RequestParam(value="uct",required=false) String uct,String id) throws Exception{
		// 当前登录用户
		Users user = userCenter(id, recentVisitServiceImpl,userFollowServiceImpl, usersService,mv);
		Users sessionUser = this.getSessionUser();
		
		// 访问目标用户不能为空
		if(user == null){
			mv.setViewName("login");
			return mv;
		}
		
		//判断类型是否为空
		if(null==type||equals(type)){
			type = "2";
		}
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("userId", user.getuId());
		map.put("uct", uct);
		page.setT(map);
		
		// 我的问答
		List<ReplyActiveDTO> list = activityServiceImpl.myConsultation(type,page);
		
		mv.addObject("list", list);
		mv.addObject("page", page);
		
		if("1".equals(type) && sessionUser != null && user.getuId().equals(sessionUser.getuId())){
			// 我来解答
			mv.setViewName("usercenter/consultation/activity/my-question");
		}else if("2".equals(type) || "1".equals(type)){
			// 我的回答
			mv.setViewName("usercenter/consultation/activity/my-answers");
		}else if("3".equals(type)){
			// 用户问答
			mv.setViewName("usercenter/consultation/activity/my-answers-user");
			mv.addObject("numDTO", activeConsultationServiceImpl.userConsultationNum(user.getuId()));
		}
		
		mv.addObject("uct", uct);
		mv.addObject("users", user);
		this.setHeaderMap(mv, "我的问答", type+"");
		return mv;
	}
	
	
	/**
	 * 方法描述：个人中心	→	我的问答	→	异步加载数据
	 * 返回类型：Map<String,Object>
	 * @param search
	 * @return
	 */
	@RequestMapping(value="/loadConsultation")
	@ResponseBody
	public Map<String,Object> loadConsultation(HttpSession session,
			@RequestParam(value="type",required=false) Integer replyType,
			@RequestParam(value="id") String activityId,
			@RequestParam(value="userId") String userId,
			@RequestParam(value="loadType") String loadType,
			Page<Map<String,Object>> page){
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("activityId", activityId);
		map.put("replyType", replyType);
		map.put("loadType", loadType);
		
		page.setT(map);
		// 5条回复
		page.setPageSize(5);
		
		try {
			List<ReplyDataDTO> list = activeConsultationServiceImpl.loadConsultation(user.getuType(), page ,loadType);
			map.clear();
			map.put(Const.RESPONSE_STATE, 200);
			map.put("list", list);
			map.put("totalPage", page.getTotalPage());
			map.put("currentPage", page.getCurrentPage());
		} catch (Exception e) {
			log.error("个人中心	→	我的问答	→	异步加载数据异常！！！", e);
			map.clear();
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "查询异常，请稍后重试或者联系客服，给您带来的不便敬请谅解...");
		} 
		
		return map;
	}
	
	

	/**
	 * 方法描述：个人中心	→	我的问答	→	回复
	 * 返回类型：Map<String,String>
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/reply")
	@ResponseBody
	public Map<String,String> reply(HttpSession session,ReplyDTO dto){
		Map<String,String> map = new HashMap<String,String>();
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		dto.setReplyUserId(user.getuId());
		try {
			activeConsultationServiceImpl.saveReply(dto);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			log.error("【个人中心	→	我的问答	→	回复】异常", e);
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "回复异常，请稍后重试或者联系客服，给您带来的不便敬请谅解!");
		}
		return map;
	}


	/**
	 * 方法描述：设置个人中心首页需要的数据
	 * 返回类型：void
	 * @param mv
	 */
	public void setHeaderMap(ModelAndView mv,String center,String left){
		Map<String,Object> map = new HashMap<String,Object>();
		// 当前导航标签
		map.put("center", center);
		//当前左侧标签
		map.put("left", left);
		mv.addObject("map", map);
	}
 
}
