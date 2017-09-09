package com.op.controller.webSocket;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 项目名：outdoorPortal
 * 类描述：webSocket
 */
@Controller
@RequestMapping(value = "/webSocket")
public class WebSocketController {

	@RequestMapping(value = "/path",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> register(HttpServletRequest request) {
		Map<String,String> map = new HashMap<String,String>();
		
		String path = request.getContextPath();
		String servicePort = "";
		if(!StringUtils.isEmpty(request.getServerPort())){
			servicePort = ":"+request.getServerPort()+"";
		}
		
		String basePath = request.getScheme()+"://"+request.getServerName()+servicePort+path+"/";
		String socketPath = "ws" + "://" + request.getServerName()+servicePort+path+"/";
		
		map.put("socketPath", socketPath);
		map.put("basePath", basePath);
		
		return map;
	}
}
