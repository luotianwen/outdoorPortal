package com.op.controller.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.op.controller.BaseController;
import com.op.entity.classification.Classification;
import com.op.entity.search.Search;
import com.op.service.classification.ClassificationService;
import com.op.solr.SolrPage;
import com.op.solr.util.ActivitySearchService;
import com.op.util.Common;
import com.op.util.DateUtil;
import com.op.util.Distance;
import com.op.util.StringUtil;

/**
 * 项目名：outdoorPortal
 * 类描述：搜索
 * 创建人：Yan
 * 创建时间： 2015-12-5 下午1:33:23
 * 最后修改时间：2015-12-5下午1:33:23
 */
@Controller
@RequestMapping(value="search")
public class SearchController extends BaseController{

	@Resource(name="activitySearchService")
	ActivitySearchService activitySearchService;
	
	// 活动类型
	@Resource(name="classificationServiceImpl")
	private ClassificationService classificationServiceImpl;
	
	/**
	 * 方法描述：搜索活动
	 * 返回类型：Object
	 * @param page	分页
	 * @param search	搜索条件
	 * @return 活动需要字段:a_active_img(主图)、id、price(活动价格)、activityTime(出发时间)、confirmUserNum(确认人数)、needUserNum(需要人数)、alreadyInNum(已报名人数)、
	 * 					  city(所在市)、l_province(活动终点省)、l_city(活动终点市)、createUser(发布者名称)、提问(咨询问答数量)、满意度 、characteristic(活动特色)
	 * @throws Exception
	 */
	@RequestMapping(value="active")
	@ResponseBody
	public Object search(HttpServletRequest request,SolrPage page,Search search) throws Exception{
		String id;
		if(this.getSessionUser() == null){
			System.err.println("**用户没有登录**ip:"+Common.getRemoteAddrIp(request));
			id = Common.getRemoteAddrIp(request);
		}else{
			System.err.println("*****"+this.getSessionUser().getuName());
			id = this.getSessionUser().getuId();
		}
		// 封装查询半径(移动地图异步查询)
		if(!StringUtils.isEmpty(search.getBl()) 
				&& !StringUtils.isEmpty(search.getNorthEast())){
			mapMoveendSet(search);
		}


		Object obj = activitySearchService.getActivitySearchResults(search,page);
		
		return obj;
	}
	
	/**
	 * 方法描述：封装查询半径（移动地图异步查询）
	 * 返回类型：void
	 * @param search
	 */
	public void mapMoveendSet(Search search){
		String[] bl = search.getBl().split(",");// 中心坐标
		String[] northEast = search.getNorthEast().split(",");// 东北角坐标
		double latCenterRad = Double.parseDouble(bl[0]);// 中心点纬度
		double lonCenterRad = Double.parseDouble(bl[1]);// 中心点经度
		double latVals = Double.parseDouble(northEast[1]);// 半径外纬度
		double lonVals = Double.parseDouble(northEast[0]);// 半径外经度
		search.setD(Distance.doubleVal(latCenterRad, lonCenterRad, latVals, lonVals));// 查询半径（单位：公里）
		search.setBl(bl[0]+","+bl[1]);// 重置中心点坐标（纬度在前，精度在后）
	}
	
	/**
	 * 方法描述：综合搜索跳转控制
	 * 返回类型：String
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="group")
	public String searchActiveList(ModelAndView mv,HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception{
		// 解析搜索参数
		if(request.getParameter("default_position") != null){
			List<Map<String,String>> list = parseRequest(request);
			redirectAttributes.addFlashAttribute("searchList", list);
			
			// 如果重定向方法想接收此处传递参数以下为几种方式
			// 1:addFlashAttribute 因为此方式是隐藏传递参数，所以直接用request或者@RequestParam获取不到，得用@ModelAttribute(在对面方法该参数可能为空的时候切记不能是一个接口)
			// 2:addAttribute 直接用request或者@RequestParam获取
		}
		return "redirect:/search/actives.html";
	};
	
	
	/**
	 * 方法描述：跳转活动搜索页面
	 * 返回类型：ModelAndView
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="actives")
	public ModelAndView searchActiveList(ModelAndView mv,
			HttpServletRequest request) throws Exception{
		
		// 查询活动类型集合，级别默认为0（当前是一级菜单）
		int level = 1;
		List<Classification> types = classificationServiceImpl.findTypesByLevel(level);
		
		// 最小选择时间的秒数
		mv.addObject("minValidDate", DateUtil.YYYY_MM_DDfomatDate(DateUtil.YYYY_MM_DDgetDay()).getTime() / 1000);
		// 设置首页需要的数据
		setHeaderMap(mv,"活动");
		
		mv.addObject("types", types);
		mv.setViewName("activity/search/searchList");
		return mv;
	}
	
	// 解析request参数
	private List<Map<String,String>> parseRequest(HttpServletRequest request){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		// 解析搜索位置
		list.add(parseLocation(request));
		String bl = request.getParameter("bl");
		// 是否选择了范围距离
		if(!StringUtil.isEmpty(bl)){
			Map map = new HashMap<String,String>();
			map.put("key", "bl");
			map.put("val", bl);
			list.add(map);
		}
		// 解析时间
		parseTime(request,list);
		
		// 去哪儿
		Map<String,String> where = parseWhere(request);
		if(where != null){
			list.add(where);
		}
		
		// 玩什么
		Map<String,String> type = parseType(request);
		if(type != null){
			list.add(type);
		}
		
		return list;
	}
	
	// 去哪儿
	private Map<String, String> parseWhere(HttpServletRequest request){
		Map<String,String>  map = null;
		// 用户选择的类型
		String d = request.getParameter("d");
		// 是否选择了范围距离
		if(!StringUtil.isEmpty(d)){
			map = new HashMap<String,String>();
			map.put("key", "d");
			map.put("val", d);
		}
		return map;
	}
	
	// 玩什么
	private Map<String,String> parseType(HttpServletRequest request){
		Map<String,String>  map = null;
		// 用户选择的类型
		String keyword = request.getParameter("keyword");
		// 手动输入的类型
		String in_keyword = request.getParameter("in_keyword");
		// 如果用户选择类型，采用该类型集合
		if(!StringUtil.isEmpty(keyword)){
			// 判断是否同时还存在手动输入的类型，拼接
			if(!StringUtil.isEmpty(in_keyword)){
				keyword += ","+in_keyword;
			}
			
			map = new HashMap<String,String>();
			map.put("key", "keyword");
			map.put("val", keyword);
			
		}else if(!StringUtil.isEmpty(in_keyword)){
			map = new HashMap<String,String>();
			map.put("key", "keyword");
			map.put("val", in_keyword);
		}
		
		return map;
	}

	// 解析搜索位置
	private void parseTime(HttpServletRequest request,List<Map<String,String>> list){
		// 手动选择开始日
		String st = request.getParameter("st");
		if(!StringUtil.isEmpty(st)){
			Map<String,String>  map = new HashMap<String,String>();
			map.put("key", "st");
			map.put("val", st);
			list.add(map);
		}
		// 手动选择结束日
		String lt = request.getParameter("lt");
		if(!StringUtil.isEmpty(lt)){
			Map<String,String>  map = new HashMap<String,String>();
			map.put("key", "lt");
			map.put("val", lt);
			list.add(map);
		}
	}

	// 解析搜索位置
	private Map<String,String> parseLocation(HttpServletRequest request){
		Map<String,String>  map = new HashMap<String,String>();
		map.put("key", "position");
		// 获取用户位置
		String position = request.getParameter("position");
		
		// 判断是否手动输入地址
		if(!StringUtils.isEmpty(position)){
			map.put("val", position);
		}else{
			map.put("val", request.getParameter("default_position"));
		}
		return map;
	}
	
	@RequestMapping(value="sug")
	@ResponseBody
	public Object sug() throws Exception{
		//searchService.spellCheck(this.getParameter("keyword"));
		 return activitySearchService.getActivitySuggest(this.getParameter("keyword"));
	}

}
