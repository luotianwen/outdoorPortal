package com.op.task;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import com.op.service.browser.UserBrowserService;

public class TimedTask{

	protected Logger logger = Logger.getLogger(this.getClass());
	
	// 用户浏览器
	@Resource(name = "userBrowserServiceImpl")
	private UserBrowserService userBrowserServiceImpl;
	
	/**
	 * 定时保存用户浏览器
	 */
	/*@Scheduled(cron = "0 0/2 *  * * ? ")// 每30分钟执行一次*/ 
	public void saveUserBrowser(){
		//System.err.println("========================开始执行任务========================");
		userBrowserServiceImpl.saveUserBrowser();
	}
	
	
}
