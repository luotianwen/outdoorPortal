package com.op.interceptor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.op.entity.users.Users;
import com.op.util.Const;

/**
 * 项目名：outdoorPortal
 * 类描述：对于要求的资源进行登录拦截，并且登录之后返回之前操作页面
 * 创建人：Yan
 * 创建时间： 2015-12-26 上午9:54:26
 * 最后修改时间：2015-12-26上午9:54:26
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter{

	// 格式：/{controllerMapping}/{methodMapping}**
	private Set<String> interceptorPathSet = null;
	
	public LoginHandlerInterceptor(){
		InputStreamReader read=null;
		try {
			read = new InputStreamReader (this.getClass().getClassLoader().getResourceAsStream("interceptorPath.txt"));
			BufferedReader bw = new BufferedReader(read);
			interceptorPathSet = new HashSet<String>();
			String line = null;
			while ((line = bw.readLine()) != null) {
				interceptorPathSet.add(line.trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("----------------------------------------读取interceptorPath.txt异常");
		}
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 访问资源名 
		String path = request.getServletPath();
		
		path = replacePath(path);
		
		if(contains(path)){
			HttpSession session = request.getSession();
			Users user = (Users)session.getAttribute(Const.SESSION_USER);
			
			if(user!=null){
				return true;
			}else{
				
	          if (request.getHeader("x-requested-with") != null&& request.getHeader("x-requested-with")
                          .equalsIgnoreCase("XMLHttpRequest"))//如果是ajax请求响应头会有，x-requested-with；
	          {
	              response.setHeader("timeout", "true");//在响应头设置session状态
	              return false;
	          }
				
				String url = parseParameter(request);
				request.getSession().setAttribute(Const.SAVE_URL, url);
				response.sendRedirect(request.getContextPath()+"/login.html");
				return false;
			}
		}else{
			return true;
		}
	}
	
	// 判断是否和拦截路径匹配
	public boolean contains(String path){
		for (String str : interceptorPathSet) {
			if(path.contains(str)){
				return true;
			}
		}
		return false;
	}
	
	// 解析request参数，便于登录之后访回之前操作页面
	public String parseParameter(HttpServletRequest request){
		String path = request.getServletPath();
		Map<String,String[]> map = request.getParameterMap();
		Set<Entry<String,String[]>> set = map.entrySet();
		String value="";
		int i=0;
		for (Entry<String,String[]> entry : set) {
			if(i==0){
				path+="?";
			}else{
				path+="&";
			}
			if(entry.getValue() == null){
				value = "";
			}else{
				String[] values = entry.getValue();
				if(values.length > 0){
					for(int s=0,len=values.length;s<len;s++){
						value += values[i]+",";
					}
					value = value.substring(0, value.length()-1);
				}
			}
			
			path+=entry.getKey()+"="+value;
		}
		if(path.indexOf("/") == 0){
			path = path.substring(1, path.length());
		}
		return path;
	}
	
	// 匹配请求路径替换通配符
	public String replacePath(String path){
		int indexof = path.indexOf(".");
		if(indexof != -1){
			path = path.substring(0,indexof);
		}
		return path;
	}
}
