package com.op.shiro.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

public class AuthcAuthorizationFilter extends AuthorizationFilter{

	protected Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 所有请求都会经过的方法。 
	*/ 
	@Override 
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
		log.info("******进入onAccessDenied方法*********");
        ////判断是否登录，未登录重定向到登录页面 
        if (!SecurityUtils.getSubject().isAuthenticated()) {
        	HttpServletRequest hrequest = (HttpServletRequest) request;
        	HttpServletResponse hresponse = (HttpServletResponse) response;
        	//如果是ajax请求响应头会有，x-requested-with；
        	if (hrequest.getHeader("x-requested-with") != null&& hrequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
        		log.info("Ajax访问："+hrequest.getRequestURL()+",被拦截，原因：用户未登录，或登录超时");
        		hresponse.setHeader("timeout", "true");//在响应头设置session状态
        		return false;
        	}else{
	        	saveRequestAndRedirectToLogin(request, response);
	        }
            
        }
        return false;
    }

	@Override
	 public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
		log.info("******进入isAccessAllowed方法*********");
        if(SecurityUtils.getSubject().isAuthenticated()) {  
            return true;
        }  
        return false;
    }

	 
}
