package com.op.controller.follow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.op.dto.usercenter.keeper.MyActiveInfoDTO;
import com.op.entity.users.Users;
import com.op.plugin.page.Page;
import com.op.service.follow.FollowActivityService;
import com.op.service.follow.FollowUserService;
import com.op.util.Const;
/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：FollowController   
* 类描述：   
* 创建人：Win Zhong   
* 创建时间：2015年12月24日 上午9:33:20   
* 修改人：Win Zhong   
* 修改时间：2015年12月24日 上午9:33:20   
* 修改备注：   
* @version    
*
 */
@Controller
@RequestMapping(value="/follow")
public class FollowController{

	/**
	 * 收藏活动
	 */
    @Resource(name="followActivityServiceImpl")
    FollowActivityService followActivityService;
	
    /**
     * 关注用户
     */
    @Resource(name="followUserServiceImpl")
    FollowUserService followUserService;	
    
    /**
     * 日志
     */
    Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 关注/取消关注活动
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/activity/{activity_id}")
	@ResponseBody
	public Map<String,Object> activity(@PathVariable(value="activity_id") int activity_id,
			HttpSession session){
		Map<String,Object> map = new HashMap<String,Object>();
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		map.put("activityId", activity_id);
		map.put("userId", user.getuId());
		try {
			followActivityService.isFollowActivity(map);
		} catch (Exception e) {
			log.error("关注/取消关注活动异常...", e);
			map.clear();
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, "操作异常！请稍后重试，或者联系客服，给您带来的不便敬请谅解!");
		}
		return map;
	}
	
	/**
	 * 个人中心	→	我的管家	→	我的收藏
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/myActive")
	public ModelAndView myFollowActive(HttpSession session,ModelAndView mv,Page<String> page) throws Exception{
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		page.setT(user.getuId());
		page.setPageSize(6);
		
		List<MyActiveInfoDTO> list = followActivityService.myFollowActive(page);
		
		// 分页
		mv.addObject("page", page);
		
		// 我的收藏活动
		mv.addObject("list", list);
		
		// 个人中心所需数据
		this.setHeaderMap(mv, "我的管家", "活动收藏");
		mv.setViewName("usercenter/housekeeper/follow/my-activity");
		return mv;
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

	
	/**
	 * 关注/取消关注领队
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/user/{fUserId}")
	@ResponseBody
	public Map<String,Object> user(@PathVariable(value="fUserId") String fUserId,
			HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		map.put("fUserId", fUserId);
		map.put("userId", user.getuId());
		try {
			followUserService.isFollowUser(map);
		} catch (Exception e) {
			log.error("关注/取消关注领队异常...", e);
			map.clear();
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, "操作异常！请稍后重试，或者联系客服，给您带来的不便敬请谅解!");
		}
		return map;
	}
	
	
}
