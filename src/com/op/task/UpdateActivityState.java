package com.op.task;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.op.service.activity.ActivityService;

/**
 * =============================================================
 * 项目名：outdoorPortal
 * 类描述：更改过期活动状态
 * 创建人：Yan
 * 创建时间： 2016-5-24
 * modification list：
 * =============================================================
 */
public class UpdateActivityState {

	/**
	 * 活动service
	 */
	@Resource(name="activityServiceImpl")
	ActivityService activityServiceImpl;
	
	Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 方法描述：更改过期活动状态
	 * 返回类型：void
	 */
	public void updateActivity(){
		try {
			activityServiceImpl.updatePastActivity();
		} catch (Exception e) {
			log.error("更改过期活动状态异常", e);
		}
	}
}
