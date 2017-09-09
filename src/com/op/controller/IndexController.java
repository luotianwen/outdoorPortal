package com.op.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.op.entity.classification.Classification;
import com.op.service.classification.ClassificationService;
import com.op.util.DateUtil;
import com.op.util.FreeMarkerUtil;
import com.op.util.rsa.RSAUtils;
/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：IndexController   
* 类描述：  登录成功跳转控制
* 创建人：Win Zhong   
* 创建时间：2015年11月11日 下午4:47:24   
* 修改人：Win Zhong   
* 修改时间：2015年11月11日 下午4:47:24   
* 修改备注：   
* @version    
*
 */
@Controller
public class IndexController{
	// 活动类型
	@Resource(name="classificationServiceImpl")
	private ClassificationService classificationServiceImpl;
	
	@Resource(name="freeMarkerUtil")
	FreeMarkerUtil freeMarkerUtil;
	/**
	 * 跳转到网站首页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="index")
	public ModelAndView index(ModelAndView mv)throws Exception{ 

		// 查询活动类型集合，级别默认为0（当前是一级菜单）
		int level = 1;
		List<Classification> types = classificationServiceImpl.findTypesByLevel(level);
		
		// 首页日历插件
		dateTime(mv);
		
		// 设置首页需要的数据
		setHeaderMap(mv);
		
		mv.setViewName("index");
		mv.addObject("types", types);
		return mv;
	}	

	/**
	 * 方法描述：首页日历插件
	 * 返回类型：void
	 * @param mv
	 */
	void dateTime(ModelAndView mv){

		// 最小选择时间的秒数
		mv.addObject("minValidDate", DateUtil.YYYY_MM_DDfomatDate(DateUtil.YYYY_MM_DDgetDay()).getTime() / 1000);
		
		// 默认开始时间为当前时间
		mv.addObject("startDate", DateUtil.YYYY_MM_DDgetDay());
		
		// 默认结束时间为一周
		//mv.addObject("endDate", DateUtil.YYYY_MM_DDgetAfterDay("7"));
	}
	
	
	/**
	 * 方法描述：goRegister
	 * 返回类型：ModelAndView
	 * @return
	 */
	@RequestMapping(value="/register")
	public ModelAndView goRegister(ModelAndView mv){
		mv.setViewName("register");
		return mv;
	}
	
	/**
	 * 方法描述：goForget
	 * 返回类型：ModelAndView
	 * @return
	 */
	@RequestMapping(value="/forget")
	public ModelAndView goForget(ModelAndView mv){
		mv.setViewName("forget");
		return mv;
	}
	

	
	
	/**
	 * 方法描述：记录访问的路径，登录过后进行跳转
	 * 返回类型：ModelAndView
	 * @param url
	 * @return
	 */
	@RequestMapping(value="/login")
	public ModelAndView goLogin(ModelAndView mv){

		mv.addObject("publicKey",RSAUtils.getPublicKeyMap());
		mv.setViewName("login");
		return mv;
	}
	
	/**
	 * 方法描述：注销
	 * 返回类型：ModelAndView
	 * @param mv
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/logout")
	public ModelAndView logout(ModelAndView mv) throws Exception{ 
		SecurityUtils.getSubject().logout();
		mv.setViewName("redirect:/index.html");
		return mv;
	}
	
	/**
	 * 方法描述：设置首页需要的数据
	 * 返回类型：void
	 * @param mv
	 */
	public void setHeaderMap(ModelAndView mv){
		Map<String,String> map = new HashMap<String,String>();
		// 当前导航标签
		map.put("ht", "首页");
		mv.addObject("map", map);
	}
	
	/**
	 * 方法描述：不具备发布活动权限提示
	 * 返回类型：ModelAndView
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/activePrompt")
	public ModelAndView releaseActivePrompt(ModelAndView mv){
		// 设置首页需要的数据
		setHeaderMap(mv);
		mv.setViewName("prompt/activity/releaseActivePrompt");
		return mv;
	}
	

	@RequestMapping(value="/test")
	@ResponseBody
	public String test(HttpServletRequest request,
			@RequestParam(value="id") String id){
		Map m = new HashMap();
	    m.put("title", "游记标题");
	    m.put("date", new Date());
	    
	    String realPath = request.getServletContext().getRealPath("/");
	    String filePath = "/view/travels/show/"+id+".html";
	    File file = new File(realPath+filePath);
	    if(!file.exists()){
	    	freeMarkerUtil.createFile("/travels/travels-show.html", m, filePath,realPath);
	    }
		return "";
	}
	
	/**
	 * 方法描述：协议入口
	 * 返回类型：ModelAndView
	 * @param mv
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/agreement/{fileName}")
	public ModelAndView agreement(ModelAndView mv,@PathVariable(value="fileName") String name){
		mv.setViewName("agreement/"+name);
		return mv;
	}
	
}
