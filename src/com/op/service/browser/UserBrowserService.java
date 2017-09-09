package com.op.service.browser;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import eu.bitwalker.useragentutils.UserAgent;

@Service("userBrowserService")
public interface UserBrowserService {

	/**
	 * 记录用户浏览器
	 * @param userAgent
	 */
   void recordUserBrowser(UserAgent userAgent,HttpServletRequest request);
	
   
	/**
	 * 保存用户浏览器
	 * @param userAgent
	 */
  void saveUserBrowser();
   
}
