package com.op.controller.activity.insurance;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.op.dto.activity.baoxian.BaoxianDTO;
import com.op.entity.users.Users;
import com.op.service.activity.ActivityInsuranceService;
import com.op.util.Const;

/** 
 * 活动保险表(activityInsurance)Controller
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-03-28 14:10:00 
 */ 
@Controller
@RequestMapping(value="/activityInsurance")
public class ActivityInsuranceController {
	
	@Resource(name="activityInsuranceServiceImpl")
	private ActivityInsuranceService activityInsuranceServiceImpl;
	
	
	Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 方法描述：关联活动保险信息
	 * 返回类型：Map<String,String>
	 * @param ai_activity_id	活动ID
	 * @param ai_insurance_id	保险ID
	 * @param session	
	 * @return
	 */
	@RequestMapping(value="/insurance")
	@ResponseBody
	public Map<String,String> insertActiveInsurance(BaoxianDTO dto,
			HttpSession session){
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		Map<String,String> map = new HashMap<String,String>();
		dto.setUserId(user.getuId());
		try {
			activityInsuranceServiceImpl.insertActiveInsurance(dto,map);
		} catch (Exception e) {
			log.error("添加保险失败!", e);
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, StringUtils.isEmpty(map.get(Const.ERROR_INFO))?"添加保险失败，请稍后操作或者联系客服，对您造成的不便敬请谅解!":map.get(Const.ERROR_INFO));
		}
		
		return map;
	}

	
	/**
	 * 方法描述：根据活动ID和创建用户和保险ID删除活动保险
	 * 返回类型：Map<String,String>
	 * @param ai_activity_id	活动ID
	 * @param ai_insurance_id	保险ID
	 * @param session	
	 * @return
	 */
	@RequestMapping(value="/del")
	@ResponseBody
	public Map<String,String> deleteByActiveIdAndUserIdAndInsuranceId(
			@RequestParam(value="activityId") String ai_activity_id,
			@RequestParam(value="insuranceId") String ai_insurance_id,
			HttpSession session){
		
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		Map<String,String> map = new HashMap<String,String>();
		map.put("ai_activity_id", ai_activity_id);
		map.put("ai_insurance_id", ai_insurance_id);
		map.put("ai_create_user", user.getuId());
		
		try {
			activityInsuranceServiceImpl.deleteByActiveIdAndUserId(map);
		} catch (Exception e) {
			log.error("删除保险异常!", e);
			map.clear();
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "删除失败，请稍后操作或者联系客服，对您造成的不便敬请谅解!");
		}
		return map;
	}
	
	
	
	
}
