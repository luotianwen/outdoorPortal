package com.op.controller.pointService;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.op.controller.BaseController;
import com.op.dto.pointService.auth.InsertPointAuthDTO;
import com.op.dto.pointService.evaluate.PointEvaluateInfo;
import com.op.dto.pointService.insert.fabu.PointServiceInfoDTO;
import com.op.dto.pointService.insert.ruzhu.InsertPointServiceDTO;
import com.op.dto.pointService.show.PointEvaluateDTO;
import com.op.dto.pointService.show.PointServiceImgDTO;
import com.op.dto.pointService.show.PointServiceProjectDTO;
import com.op.dto.pointService.show.PointServiceShowDTO;
import com.op.dto.pointService.update.point.PointServiceUpdateDTO;
import com.op.dto.pointService.update.point.PointTypeDTO;
import com.op.dto.screening.ScreeningDTO;
import com.op.entity.district.Provinces;
import com.op.entity.pointService.PointServiceType;
import com.op.entity.users.Users;
import com.op.plugin.page.Page;
import com.op.service.district.CitysService;
import com.op.service.district.ProvincesService;
import com.op.service.pointService.PointComboCrowdTypeService;
import com.op.service.pointService.PointEvaluateService;
import com.op.service.pointService.PointServiceService;
import com.op.service.pointService.PointServiceTypeService;
import com.op.service.recentVisit.RecentVisitService;
import com.op.service.screening.ScreeningService;
import com.op.service.userFollow.UserFollowService;
import com.op.service.users.UsersService;
import com.op.util.Const;

/**
 * =============================================================
 * 项目名：outdoorPortal
 * 类描述：场馆Controller
 * 创建人：Yan
 * 创建时间： 2016-6-7
 * modification list：
 * =============================================================
 */
@Controller
@RequestMapping(value="/pointService")
public class PointServiceController extends BaseController{
	// servlet 上下文
	String contextPath = null;
    
    // 模板文件名
    String ftl = null;
	
	/**
	 * 省份信息
	 */
	@Resource(name="provincesServiceImpl")
	ProvincesService provincesServiceImpl;
	
	/**
	 * 地点服务类型信息
	 */
	@Resource(name="pointServiceTypeServiceImpl")
	PointServiceTypeService pointServiceTypeServiceImpl;
	
	/**
	 * 城市
	 */
	@Resource(name="citysServiceImpl")
	CitysService citysServiceImpl;
	
	/**
	 * 地点服务
	 */
	@Resource(name="pointServiceServiceImpl")
	PointServiceService pointServiceServiceImpl;
	
	/**
	 * 场馆评论
	 */
	@Resource(name="pointEvaluateServiceImpl")
	PointEvaluateService pointEvaluateServiceImpl;
	
	@Resource(name="recentVisitServiceImpl")
	RecentVisitService recentVisitServiceImpl;
	
	@Resource(name="userFollowServiceImpl")
	UserFollowService userFollowServiceImpl;
	
	@Resource(name="usersServiceImpl")
	UsersService usersService;
	
	/**
	 * 适合人群
	 */
	@Resource(name="pointComboCrowdTypeServiceImpl")
	PointComboCrowdTypeService pointComboCrowdTypeServiceImpl;
	
	/**
	 * 筛选条件
	 */
	@Resource(name="screeningServiceImpl")
	ScreeningService screeningServiceImpl;
	
	
	/**
	 * log4j
	 */
	Logger log = Logger.getLogger(this.getClass());


	
	/**
	 * 方法描述：用户申请入驻
	 * 返回类型：ModelAndView
	 * @param mv
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/apply")
	public ModelAndView apply(ModelAndView mv){
		try {
			Users user = (Users) SecurityUtils.getSubject().getSession().getAttribute(Const.SESSION_USER);
			
			// 该用户身份已经是商户，不可再次进行申请
			if(checkUserTypeIsVenue(user)){
				mv.setViewName("redirect:/500.html");
				return mv;
			}else{
				// 地理位置级联菜单（省）
				List<Provinces> provinces = provincesServiceImpl.selectProvinces();
				
				// 场馆一级类型集合
				List<PointServiceType> types = pointServiceTypeServiceImpl.selectVenueOneTypeByParentId();
				
				mv.addObject("provinces", provinces);
				mv.addObject("types", types);
				mv.setViewName("pointService/ruzhu/ruzhu-pointService");
			}	
		} catch (Exception e) {
			log.error("申请入驻,跳转【填写入驻信息】页面异常", e);
			mv.setViewName("redirect:/500.html");
		}
		
		return mv;
	}
	
	
	/**
	 * 方法描述：发现好店
	 * 返回类型：ModelAndView
     * 创建人：WuErMao
     * 创建时间： 2016-8-22
	 * @param mv
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/myfind")
	public ModelAndView myfind(ModelAndView mv){
		try {
				// 地理位置级联菜单（省）
				List<Provinces> provinces = provincesServiceImpl.selectProvinces();
				
				// 场馆一级类型集合
				List<PointServiceType> types = pointServiceTypeServiceImpl.selectVenueOneTypeByParentId();
				
				mv.addObject("provinces", provinces);
				mv.addObject("types", types);
				mv.setViewName("pointService/findshop/myfind"); 
		} catch (Exception e) {
			log.error("申请入驻,跳转【填写入驻信息】页面异常", e);
			mv.setViewName("redirect:/500.html");
		}
		
		return mv;
	}
	/**
	 * 方法描述：发布项目，完善商户信息
	 * 返回类型：ModelAndView
	 * @param mv
	 * @param session
	 * @param ps_id
	 * @return
	 */
	@RequestMapping(value="/reply")
	public ModelAndView reply(ModelAndView mv, HttpSession session, @RequestParam(value="id")String ps_id){
		
		// 没有经过检验
		/*if(session.getAttribute(Const.REPLY_PROJECT_CHECK_AUTH_SUCCESS) == null){
			mv.setViewName("redirect:/500.html");
			return mv;
		}*/
		try {
			boolean bool = pointServiceServiceImpl.checkPointCreateUser(ps_id,this.getSessionUser().getuId());
			if(bool){
				//获取场馆描述信息
				PointServiceInfoDTO dto = pointServiceServiceImpl.findPointServiceInfoByPsId(ps_id);
				mv.addObject("dto", dto);
				
				// 适合人群
				Map<String,String> crowdMap = pointComboCrowdTypeServiceImpl.shuaxinhuancun();
				
				mv.addObject("crowds", crowdMap);
				
				mv.addObject("ps_id", ps_id);
				mv.setViewName("pointService/fabu/reply-pointService");
			}else{
				mv.setViewName("redirect:/500.html");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mv.setViewName("redirect:/500.html");
		}
		
		return mv;
	}
	
	/**
	 * 方法描述：跳转认证页面
	 * 返回类型：ModelAndView
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/auth")
	public ModelAndView auth(ModelAndView mv,@RequestParam(value="id") String ps_id){
		mv.addObject("ps_id", ps_id);
		mv.setViewName("pointService/renzheng/point-service-auth");
		return mv;
	}
	
	
	/**
	 * 方法描述：发布项目认证校验
	 * 返回类型：Map<String,String>
	 * 一：用户对某商户发布项目校验
	 *		1：判断该商户是否已经认证
	 *			1）：已认证：如果是本人认证那么跳转发布页面；否则提示用户该商户已经认证，不可对其操作，如要认领请联系客服；
	 *			2）：未认证：是否存在认证信息
	 *				a)：已存在：判断是否本人，如果是本人提示待审核；非本人进行认证操作
	 *				b)：不存在：进行认证操作
	 * @param pointServiceId  商户id
	 * @return
	 */
	@RequestMapping(value="/checkAuth")
	@ResponseBody
	public Map<String,String> checkAuth(@RequestParam(value="id") String pointServiceId,
			HttpSession session){
		Map<String,String> map = new HashMap<String,String>();
		map.put(Const.RESPONSE_STATE, "500");
		Users user = (Users) SecurityUtils.getSubject().getSession().getAttribute(Const.SESSION_USER);
		
		// 如果为普通用户则跳转申请商户页面
		if(user.getuType() == 3){
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
			map.put("location", "pointService/apply.html");
			return map;
		}
		// 如果用户不是普通用户也不是商户，那么跳转500，暂时不对领队或者企业开放该入口
		else if(user.getuType() != 50){
			map.put(Const.ERROR_INFO, "抱歉，目前仅支持商户进行该操作");
			return map;
		}
		map.put("pointServiceId", pointServiceId);
		map.put("uId", user.getuId());
		
		try {
			pointServiceServiceImpl.checkAuth(map);
			
			// 检验成功，标识该session已被检验
			if(Const.RESPONSE_SUCCESS.equals(map.get(Const.RESPONSE_STATE))
					&& "pointService/reply.html".equals(map.get("location"))){
				session.setAttribute(Const.REPLY_PROJECT_CHECK_AUTH_SUCCESS, "YES");
			}
		} catch (Exception e) {
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, Const.ERROR_INFO_STR);
			log.error("发布项目认证校验异常", e);
		}
		return map;
	}
	
	
	/**
	 * 方法描述：添加认证数据
	 * 返回类型：Map<String,String>
	 * @return
	 */
	@RequestMapping(value="/insertAuth")
	@ResponseBody
	public Map<String,String> insertAuth(InsertPointAuthDTO dto){
		Map<String,String> map = new HashMap<String,String>();
		Users user = (Users) SecurityUtils.getSubject().getSession().getAttribute(Const.SESSION_USER);
		dto.setUser_id(user.getuId());
		try {
			pointServiceServiceImpl.insertAuth(dto);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, Const.ERROR_INFO_STR);
			log.error("添加认证数据异常", e);
		}
		
		return map;
	}
	
	/**
	 * 方法描述：修改认证数据
	 * 返回类型：Map<String,String>
	 * @return
	 */
	@RequestMapping(value="/updateAuth")
	@ResponseBody
	public Map<String,String> updateAuth(InsertPointAuthDTO dto){
		Map<String,String> map = new HashMap<String,String>();
		Users user = (Users) SecurityUtils.getSubject().getSession().getAttribute(Const.SESSION_USER);
		dto.setUser_id(user.getuId());
		try {
			pointServiceServiceImpl.updateAuth(dto);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, Const.ERROR_INFO_STR);
			log.error("修改认证数据异常", e);
		}
		
		return map;
	}
	
	
	/**
	 * 方法描述：提交入驻信息
	 * 返回类型：Map<String,String>
	 * @param session
	 * @param venue
	 * @return
	 */
	@RequestMapping(value="/ruzhu",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> ruzhuInfo(InsertPointServiceDTO dto){
		Map<String,String> map = new HashMap<String,String>();
		map.put(Const.RESPONSE_STATE, "500");
		Users user = (Users) SecurityUtils.getSubject().getSession().getAttribute(Const.SESSION_USER);
		
		// 用户ID
		dto.setPs_create_user_id(user.getuId());
		
		try {
			String ps_id = pointServiceServiceImpl.insertReplyPointService(dto);
			map.put("ps_id", ps_id);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			log.error("添加商户入驻信息异常", e);
			map.put(Const.ERROR_INFO, Const.ERROR_INFO_STR);
		}
		return map;
	}
	
	/**
	 * 方法描述：判断用户是否商户身份
	 * 返回类型：boolean
	 * @param user
	 * @return
	 */
	boolean checkUserTypeIsVenue(Users user){
		if(user.getuType()==50){
			return true;
		}
		return false;
	}
	
	/**
	 * 方法描述：获取子数据类型集合
	 * 返回类型：Map<String,String>
	 * @param oneTypeId
	 * @return
	 */
	@RequestMapping(value="/getChildrens")
	@ResponseBody
	Map<String,Object> getChildrens(@RequestParam(value="id") int oneTypeId){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			List<PointServiceType> types = pointServiceTypeServiceImpl.getChildrens(oneTypeId);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
			map.put("types", types);
		} catch (Exception e) {
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "获取类型数据异常");
			log.error("获取类型数据异常", e);
		}
		return map;
	}
	
	
	/**
	 * 方法描述：获取所有的市
	 * 返回类型：Map<String,Object>
	 * @return
	 */
	@RequestMapping(value="/getCitys")
	@ResponseBody
	public Map<String,Object> getCitys(@RequestParam(value="id") String id){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			map.put("citys", citysServiceImpl.selectCitys(id));
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "获取【市】数据异常");
			log.error("获取【市】字典表数据异常", e);
		}
		return map;
	}
	
	/**
	 * 方法描述：发布项目
	 * 返回类型：Map<String,Object>
	 * @param dto
	 * @return
	 */
	@RequestMapping(value="/fabu")
	@ResponseBody
	public Map<String,Object> fabu(PointServiceInfoDTO dto,String psp_update_id){
		Map<String,Object> map = new HashMap<String,Object>();
		Users user = (Users) SecurityUtils.getSubject().getSession().getAttribute(Const.SESSION_USER);
		dto.setCreate_user_id(user.getuId());
		
		try {
			boolean bool = pointServiceServiceImpl.checkPointCreateUser(dto.getPs_id(),dto.getCreate_user_id());
			
			if(bool){
				pointServiceServiceImpl.saveProjectAndPointServiceInfo(dto,psp_update_id);
				map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
			}else{
				map.put(Const.RESPONSE_STATE, "500");
				map.put(Const.ERROR_INFO, "您不是该场馆的所有者，无法进行此操作！");
			}
		} catch (Exception e) {
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "提交数据异常，请稍后再试！");
			log.error("发布项目异常", e);
		}
		
		return map;
	}
	
	/**
	 * 方法描述：选择线路地图
	 * 返回类型：ModelAndView
	 * @param mv
	 * @param showLineAddressId
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping(value="/line")
	public ModelAndView chooseActiveLine(ModelAndView mv,
			@RequestParam(value="showAddressId") String showAddressId,
			@RequestParam(value="start",required=false) String start){
		mv.addObject("start", start);
		mv.addObject("showAddressId", showAddressId);
		mv.setViewName("pointService/ruzhu/add-map-address");
		return mv;
	}
	
	/**
	 * 方法描述：搜索
	 * 返回类型：ModelAndView
	 * @param mv
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/search")
	public ModelAndView search(ModelAndView mv)throws Exception{
		//筛选条件
		List<ScreeningDTO> screeningList = screeningServiceImpl.findAllScreening("pointService",mv);
		
		boolean bool = true;
		for(int i=0,len=screeningList.size();i<len;i++){
			if(screeningList!=null){
				if(screeningList.get(i).getSc_type()==5||screeningList.get(i).getSc_type()==6){
					bool = false;
					break;
				}
			}
		}
		if(!bool){
			
			mv.addObject("city", screeningList);
		}
		
		mv.setViewName("search/point/searchList");
		// 设置首页需要的数据
		setPointHeaderMap(mv,"场馆");
		return mv;
	}

	
	/**
	 * 方法描述：设置首页需要的数据
	 * 返回类型：void
	 * @param mv
	 */
	void setPointHeaderMap(ModelAndView mv,String ht){
		Map<String,String> map = new HashMap<String,String>();
		// 当前导航标签
		map.put("ht", ht);
		mv.addObject("map", map);
	}
	
	/**
	 * 场馆详情
	 * @param id 场馆ID
	 * @param mv
	 * @param request
	 * @return
	 */
	@RequestMapping("/detail/{id}")
	public ModelAndView detail(@PathVariable(value="id")String id,ModelAndView mv,HttpServletRequest request){
		try {
			if(ftl == null){
				// servlet 上下文
		    	contextPath = request.getServletContext().getRealPath("/");
		    	
		    	// 模板文件名
		    	ftl = "static/ftl/pointService/pointService-show.html";
			}
		    
		    // 要生成的目标目录及文件
		    String targetFile = "/view/pointService/zhanshi/pointService/"+id+".html";

		    // 创建该文件对象判断是否存在
		    File file = new File(contextPath+targetFile);
		    
		    //if(!file.exists()){
				// 后台调取数据生成模板文件
		    	pointServiceServiceImpl.pointServiceShow(id ,ftl,targetFile ,contextPath);
		    //}
			
		    // 创建该文件对象判断是否存在
		    file = new File(contextPath+targetFile);
		    
		    if(file.exists()){
		    	mv.setViewName("pointService/zhanshi/pointService/"+id);
		    }else{
		    	mv.setViewName("not");
		    }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mv.setViewName("not");
		}
		return mv;
		
	}
	
	/**
	 * 生成项目展示页
	 */
	@RequestMapping("/newPointService")
	public void newPointService(@PathVariable(value="id")String id,HttpServletRequest request){
		try {
		    if(ftl == null){
				// servlet 上下文
		    	contextPath = request.getServletContext().getRealPath("/");
		    	
		    	// 模板文件名
		    	ftl = "static/ftl/pointService/pointService-show.html";
			}
		    
		    // 要生成的目标目录及文件
		    String targetFile = "/view/pointService/zhanshi/pointService/"+id+".html";

		    // 创建该文件对象判断是否存在
		    File file = new File(contextPath+targetFile);
		    
		    if(!file.exists()){
				// 后台调取数据生成模板文件
		    	pointServiceServiceImpl.pointServiceShow(id ,ftl,targetFile ,contextPath);
		    }
		    
		} catch (Exception e) {
			logger.error("生成项目展示页异常！！！！！！！！！！！！！", e);
		}
		
	}
	
	
	/**
	 * 异步修改场馆展示页面信息
	 * 项目分数
	 * 是否收藏
	 * 商家项目
	 * 购买人数
	 * 认证信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/updatePointServiceShow")
	@ResponseBody
	public Map<String,Object> updatePointServiceShow(String id){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//项目分数
			String avgPoint = pointEvaluateServiceImpl.avgPoint(id);
			if(StringUtils.isEmpty(avgPoint)){
				map.put("avgPoint", "0");
			}else{
				if(avgPoint.length()>3){
					avgPoint = avgPoint.substring(0, 3);
				}
				map.put("avgPoint", avgPoint);
			}
			
			//是否收藏
			Users user = this.getSessionUser();
			boolean isCollection;
			if(user!=null){
				isCollection = pointServiceServiceImpl.isCollection(id,this.getSessionUser().getuId());
			}else{
				isCollection = false;
			}
			map.put("isCollection", isCollection);
			
			//商家所有项目
			Page<String> page = new Page<String>();
			page.setT(id);
			List<PointServiceProjectDTO> pointServiceProjectDTOList = pointServiceServiceImpl.findPointServiceProjectDTOByPsId(page);
			map.put("pointServiceProjectDTOList", pointServiceProjectDTOList);
		
			//购买人数
			String purchaseNumber = pointServiceServiceImpl.findPointPurchaseNumber(id);
			map.put("purchaseNumber", purchaseNumber);
			
			//认证信息
			boolean isAuth = pointServiceServiceImpl.isAuth(id);
			map.put("isAuth", isAuth);
			
		} catch (Exception e) {
			log.error("异步修改场馆展示页面信息方法异常！！！！！！！！！！！！", e);
		}
		
		return map;
	}
	
	/**
	 * 查看更多项目
	 * @param id
	 * @param page
	 * @return
	 */
	@RequestMapping("/moreProject")
	@ResponseBody
	public Map<String,Object> moreProject(String id,Page<String> page){
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
			page.setT(id);
			List<PointServiceProjectDTO> pointServiceProjectDTOList = pointServiceServiceImpl.findPointServiceProjectDTOByPsId(page);
			map.put("pointServiceProjectDTOList", pointServiceProjectDTOList);
		} catch (Exception e) {
			log.error("查看更多项目异常", e);
		}
		
		return map;
	}
	
	
	/**
	 * 用户收藏
	 */
	@RequestMapping("/conllectionPoint")
	@ResponseBody
	public void conllectionPoint(String id){
		
		try {
			pointServiceServiceImpl.addConllection(id,this.getSessionUser().getuId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 用户取消收藏
	 */
	@RequestMapping("/cancelCollection")
	@ResponseBody
	public void cancelCollection(String id){
		
		try {
			pointServiceServiceImpl.cancelCollection(id,this.getSessionUser().getuId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 场馆点评
	 */
	@RequestMapping("/pointEvaluate")
	public ModelAndView pointEvaluate(ModelAndView mv,String id){
		try {
			PointEvaluateInfo pointEvaluateInfo = pointServiceServiceImpl.pointEvaluate(id);
			
			mv.addObject("pointEvaluateInfo", pointEvaluateInfo);
			mv.setViewName("usercenter/poinService/pointOrder/venue-rate");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("场馆点评页面展示信息查询方法异常！！！！！！！！！！",e);
			mv.setViewName("404");
		}
		
		return mv;
	}
	
	/**
	 * 获取场馆图片
	 */
	@RequestMapping("/pointServiceImg")
	@ResponseBody
	public Map<String,Object> pointServiceImg(String id){
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
			List<PointServiceImgDTO> pointServiceImgDTOList = pointServiceServiceImpl.findPointServiceImg(id);
			
			if(pointServiceImgDTOList.size()>0){
				map.put("title", "");
				map.put("id", pointServiceImgDTOList.get(0).getPsi_point_service_id());
				map.put("start", "");
				map.put("data", pointServiceImgDTOList);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
		
	}
	
	/**
	 * 修改商家信息
	 */
	@RequestMapping("/editPointService")
	public ModelAndView editPointService(ModelAndView mv,String id){
		try {
			PointServiceUpdateDTO dto = pointServiceServiceImpl.findInsertPointServiceDTO(id,this.getSessionUser().getuId());
			if(dto!=null){
				// 地理位置级联菜单（省）
				List<Provinces> provinces = provincesServiceImpl.selectProvinces();
				
				// 场馆一级类型集合
				List<PointServiceType> types = pointServiceTypeServiceImpl.selectVenueOneTypeByParentId();
				
				PointTypeDTO pointTypeDTO = dto.getPointTypeDTO();
				if("2".equals(pointTypeDTO.getCategorylevel())){
					pointTypeDTO.setTwo(pointTypeDTO.getThree());
				}else if("4".equals(pointTypeDTO.getCategorylevel())){
					pointTypeDTO.setFour(pointTypeDTO.getThree());
					pointTypeDTO.setThree(pointTypeDTO.getTwo());
					
					PointServiceType pointServiceType = pointServiceTypeServiceImpl.findPointServiceTypeById(pointTypeDTO.getThree());
					pointTypeDTO.setTwo(pointServiceType.getParentId()+"");
				}
				dto.setPointTypeDTO(pointTypeDTO);
				
				
				mv.addObject("provinces", provinces);
				mv.addObject("types", types);
				
				mv.addObject("dto", dto);
				mv.setViewName("pointService/update/update-pointService");
			}else{
				mv.setViewName("404");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mv.setViewName("505");
		}
		
		return mv;
	}
	
	/**
	 * 商家个人中心首页
	 * @param mv
	 * @param id
	 * @return
	 */
	@RequestMapping("/home")
	public ModelAndView home(ModelAndView mv,String id){
		Users users = userCenter(id, recentVisitServiceImpl,userFollowServiceImpl, usersService,mv);
		mv.addObject("users", users);
		try {
			List<PointServiceShowDTO> list = pointServiceServiceImpl.findPointService(users.getuId());
			mv.addObject("list", list);
			setUserCenterHeaderMap(mv, "我的场馆", "商家信息");
			mv.setViewName("usercenter/poinService/pointCenter/pointService/pointService");
		} catch (Exception e) {
			log.error("商家个人中心首页方法异常！！！！！！！", e);
		}
		
		return mv;
	}
	
	/**
	 * 商家认证信息
	 */
	@RequestMapping("/authInfo")
	public ModelAndView authInfo(ModelAndView mv,String id){
		Users users = userCenter(id, recentVisitServiceImpl,userFollowServiceImpl, usersService,mv);
		mv.addObject("users", users);
		try {
			InsertPointAuthDTO dto = pointServiceServiceImpl.findPointServiceAuth(users.getuId());
			mv.addObject("dto", dto);
			setUserCenterHeaderMap(mv, "我的场馆", "认证信息");
			mv.setViewName("usercenter/poinService/pointCenter/auth/update-auth");
		} catch (Exception e) {
			log.error("查看商家认证信息方法异常！！！！！！！", e);
		}
		
		return mv;
	}
	
	/**
	 * 商家所有项目
	 */
	@RequestMapping("/allProject")
	public ModelAndView allProject(ModelAndView mv,String id,Page<Map<String,String>> page){
		Users users = userCenter(id, recentVisitServiceImpl,userFollowServiceImpl, usersService,mv);
		Users user = this.getSessionUser();
		
		mv.addObject("users", users);
		try {
			Map<String,String> map = new HashMap<String,String>();
			map.put("userId", users.getuId());
			
			if(user==null||!users.getuId().equals(user.getuId())){
				map.put("type", "qt");
			}else{
				map.put("type", "zj");
			}
			
			//商家所有项目
			page.setT(map);
			List<PointServiceProjectDTO> pointServiceProjectDTOList = pointServiceServiceImpl.findPointServiceProjectDTO(page);
			
			mv.addObject("pointServiceProjectDTOList", pointServiceProjectDTOList);
			mv.setViewName("usercenter/poinService/pointCenter/project/my-project");
			setUserCenterHeaderMap(mv, "我的场馆", "项目管理");
		} catch (Exception e) {
			log.error("查看商家所有项目方法异常！！！！！！！", e);
		}
		
		return mv;
	}
	
	/**
	 * 商家所有评价
	 */
	@RequestMapping("/allEvaluate")
	public ModelAndView allEvaluate(ModelAndView mv,String id,Page<Map<String,String>> page){
		Users users = userCenter(id, recentVisitServiceImpl,userFollowServiceImpl, usersService,mv);
		
		mv.addObject("users", users);
		
		try {
			Map<String,String> pageMap = new HashMap<String,String>();
			pageMap.put("id", users.getuId());
			
			page.setT(pageMap);
			page.setPageSize(5);
			List<PointEvaluateDTO> pointEvaluateDTOList = pointEvaluateServiceImpl.allEvaluate(page);
			
			mv.addObject("pointEvaluateDTOList", pointEvaluateDTOList);
			mv.addObject("page", page);
			
			setUserCenterHeaderMap(mv, "我的场馆", "评价管理");
			mv.setViewName("usercenter/poinService/pointCenter/evaluate/rate-manage");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}
	
	/**
	 * 查询该用户名下所有场馆
	 * @return
	 */
	@RequestMapping("/allPoint")
	@ResponseBody
	public Map<String,Object> allPoint(){
		Map<String,Object> map = new HashMap<String,Object>();
		Users users = this.getSessionUser();
		try {
			List<PointServiceShowDTO> list = pointServiceServiceImpl.allPoint(users.getuId());
			
			map.put("list", list);
			map.put("uType", users.getuType());
		} catch (Exception e) {
			log.error("商家个人中心首页方法异常！！！！！！！", e);
		}
		
		return map;
	}
	
}
