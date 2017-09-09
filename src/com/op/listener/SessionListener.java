package com.op.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.op.entity.users.Users;
import com.op.service.behaviour.BrowsingRecordService;
import com.op.service.browser.UserBrowserService;
import com.op.util.Const;

import eu.bitwalker.useragentutils.UserAgent;

/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：SessionListener   
* 类描述：   监听Session 和 Request 的创建和销毁
* 创建人：Win Zhong   
* 创建时间：2016年1月27日 下午4:10:29   
* 修改人：Win Zhong   
* 修改时间：2016年1月27日 下午4:10:29   
* 修改备注：   
* @version    
*
 */
public class SessionListener implements HttpSessionListener,ServletRequestListener {

	// 用户浏览记录
	private BrowsingRecordService browsingRecordServiceImpl;
	
	// 用户浏览器
	private UserBrowserService userBrowserServiceImpl;
	
	private HttpServletRequest request;
	
	/**
	 * Session 创建
	 */
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
		 if(browsingRecordServiceImpl == null){
			 ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getSession().getServletContext());
			userBrowserServiceImpl = (UserBrowserService) applicationContext.getBean("userBrowserServiceImpl");
		 } 
		 userBrowserServiceImpl.recordUserBrowser(userAgent, request);
	}

	/**
	 * Session 销毁
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		
		/*if(browsingRecordServiceImpl == null){
			ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getSession().getServletContext());
			browsingRecordServiceImpl = (BrowsingRecordService) applicationContext.getBean("browsingRecordServiceImpl");
		}*/
		
		try {
			Users user = (Users) event.getSession().getAttribute(Const.SESSION_USER);
			
			if(user != null){
				//保存缓存的用户浏览记录记录
				browsingRecordServiceImpl.saveBrowsingRecord(user.getuId());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpSession session = event.getSession();
		// 销毁session自动调用shiro	session.stop进行销毁shiro会话
		session.invalidate();
	}

	/**
	 * request 销毁
	 */
	@Override
	public void requestDestroyed(ServletRequestEvent event) {
		// TODO Auto-generated method stub
	}

	/**
	 * request 创建
	 */
	@Override
	public void requestInitialized(ServletRequestEvent event) {
		// TODO Auto-generated method stub
		request = (HttpServletRequest)event.getServletRequest();
	}

}
