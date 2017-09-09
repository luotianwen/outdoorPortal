package com.op.plugin.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
/**
 * 
* 类名称：MyExceptionResolver.java
* 类描述： 
* @author 
* 作者单位： 
* 联系方式：
* @version 
 */
public class MyExceptionResolver implements HandlerExceptionResolver{

	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		// TODO Auto-generated method stub
		logger.error("==============异常开始=============");
		logger.error(ex);
		logger.error("==============异常结束=============");
		ModelAndView mv = new ModelAndView("error");
		//mv.addObject("exception", ex.toString().replaceAll("\n", "<br/>"));
		return mv;
	}

}
