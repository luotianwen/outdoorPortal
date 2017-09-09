package com.op.controller.pointService;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



import com.op.controller.BaseController;
import com.op.dto.pointService.order.BuyProjectDTO;
import com.op.dto.pointService.update.project.ProjectUpdateDTO;
import com.op.entity.pointService.project.PointServiceProjects;
import com.op.entity.users.Users;
import com.op.plugin.page.Page;
import com.op.service.pointService.PointComboCrowdTypeService;
import com.op.service.pointService.PointEvaluateService;
import com.op.service.pointService.PointServiceOrderService;
import com.op.service.pointService.PointServiceProjectsService;
import com.op.service.pointService.PojectPraiseService;
import com.op.service.pointService.ProjectEnshrineService;
import com.op.util.Const;
import com.op.util.SerializationUtil;
import  com.op.util.jedis.RedisUtil;

@Controller
@RequestMapping(value="/pointServiceProjects")
public class PointServiceProjectsController extends BaseController{
	// servlet 上下文
	String contextPath = null;
    
    // 模板文件名
    String ftl = null;
    
	@Resource(name="pointServiceProjectsServiceImpl")
	PointServiceProjectsService pointServiceProjectsServiceImpl;
	//项目收藏
	@Resource(name="projectEnshrineServiceImpl")
	ProjectEnshrineService projectEnshrineServiceImpl;
	//项目点赞
	@Resource(name="pojectPraiseServiceImpl")
	PojectPraiseService pojectPraiseServiceImpl;
	/**
	 * 场馆评论
	 */
	@Resource(name="pointEvaluateServiceImpl")
	PointEvaluateService pointEvaluateServiceImpl;
	//订单
	@Resource(name="pointServiceOrderServiceImpl")
	PointServiceOrderService pointServiceOrderServiceImpl;
	/**
	 * 适合人群
	 */
	@Resource(name="pointComboCrowdTypeServiceImpl")
	PointComboCrowdTypeService pointComboCrowdTypeServiceImpl;
	
	/**
	 * 项目详情
	 * @param id 项目ID
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
		    	ftl = "static/ftl/pointServiceProject/project-show.html";
			}
		    
		    // 要生成的目标目录及文件
		    String targetFile = "/view/pointService/zhanshi/project/"+id+".html";

		    // 创建该文件对象判断是否存在
		    File file = new File(contextPath+targetFile);
		    
		    if(!file.exists()){
				// 后台调取数据生成模板文件
				pointServiceProjectsServiceImpl.pointServiceProjectShow(id ,ftl,targetFile ,contextPath);
		    }
			
		    // 创建该文件对象判断是否存在
		    file = new File(contextPath+targetFile);
		    
		    if(file.exists()){
		    	mv.setViewName("pointService/zhanshi/project/"+id);
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
	@RequestMapping("/newProject")
	public void newProject(String id,HttpServletRequest request){
		try {
			if(ftl == null){
				// servlet 上下文
		    	contextPath = request.getServletContext().getRealPath("/");
		    	
		    	// 模板文件名
		    	ftl = "static/ftl/pointServiceProject/project-show.html";
			}
		    
		    // 要生成的目标目录及文件
		    String targetFile = "/view/pointService/zhanshi/"+id+".html";

		    // 创建该文件对象判断是否存在
		    File file = new File(contextPath+targetFile);
		    
		    //if(!file.exists()){
				// 后台调取数据生成模板文件
				pointServiceProjectsServiceImpl.pointServiceProjectShow(id ,ftl,targetFile ,contextPath);
		    //}
		} catch (Exception e) {
			logger.error("生成项目展示页异常！！！！！！！！！！！！！", e);
		}
		
	}
	
	/**
	 * 异步查询的数据
	 * 
	 * 1、所有项目
	 * 2、场馆收藏数量
	 * 3、场馆点赞数量
	 * 
	 * 4、项目评论
	 * 5、是否收藏
	 * 6、购买人数
	 * 7、项目分数
	 * 
	 */
	@RequestMapping("/updateProjectShow")
	@ResponseBody
	public Map<String,Object> updateProjectShow(String id,String venueId){
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
			//所有项目
			map.put("allProjects",pointServiceProjectsServiceImpl.findProjectByVenueId(venueId));
			

			//项目收藏数量
			if(StringUtils.isEmpty(RedisUtil.get("projectCollection_"+id))){
				String count = projectEnshrineServiceImpl.projectCollection(id);
				RedisUtil.set("projectCollection_"+id, count);
				RedisUtil.expire(("projectCollection_"+id), (int) (1.5*24*60*60));
				map.put("projectCollection", count);
			}else{
				String count = RedisUtil.get("projectCollection_"+id);
				map.put("projectCollection", count);
			}
			//项目点赞数量
			if(StringUtils.isEmpty(RedisUtil.get("projectLike_"+id))){
				String count = pojectPraiseServiceImpl.projectLike(id);
				RedisUtil.set("projectLike_"+id, count);
				RedisUtil.expire(("projectLike_"+id), (int) (1.5*24*60*60));
				map.put("projectLike", count);
			}else{
				String count = RedisUtil.get("projectLike_"+venueId);
				map.put("projectLike", count);
			}
			
			//是否收藏
			Users user = this.getSessionUser();
			boolean bool;
			if(user!=null){
				bool = projectEnshrineServiceImpl.isCollection(id,this.getSessionUser().getuId());
			}else{
				bool = false;
			}
			map.put("isCollection", bool);
			
			//购买人数
			int purchaseNumber = pointServiceOrderServiceImpl.findPurchaseNumber(id);
			map.put("purchaseNumber", purchaseNumber);
			//项目分数
			String avgProject = pointEvaluateServiceImpl.avgProject(id);
			if(StringUtils.isEmpty(avgProject)){
				map.put("avgProject", "0");
			}else{
				map.put("avgProject", avgProject);
			}
			
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put(Const.RESPONSE_STATE, "500");
		}
		
		return map;
	}
	
	/**
	 * 用户收藏
	 */
	@RequestMapping("/conllectionProject")
	@ResponseBody
	public void conllectionProject(String id){
		
		try {
			projectEnshrineServiceImpl.addConllection(id,this.getSessionUser().getuId());
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
			projectEnshrineServiceImpl.cancelCollection(id,this.getSessionUser().getuId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 用户点赞
	 */
	@RequestMapping("/likeProject")
	@ResponseBody
	public void likeProject(String id){
	
		try {
			pojectPraiseServiceImpl.addLike(id,this.getSessionUser().getuId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 取消点赞
	 * @param id
	 */
	@RequestMapping("/cancellike")
	@ResponseBody
	public void cancellike(String id){
	
		try {
			pojectPraiseServiceImpl.cancellike(id,this.getSessionUser().getuId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 方法描述：设置首页需要的数据
	 * 返回类型：void
	 * @param mv
	 */
	@Override
	public void setHeaderMap(ModelAndView mv,String ht){
		Map<String,String> map = new HashMap<String,String>();
		// 当前导航标签
		map.put("ht", ht);
		mv.addObject("map", map);
	}
	
	/**
	 * 购买项目
	 */
	@RequestMapping("/buyProject")
	public ModelAndView buyProject(ModelAndView mv,String id,String n){
		try {
			BuyProjectDTO buyProjectDTO = pointServiceProjectsServiceImpl.buyProject(id);
			
			if(buyProjectDTO!=null){
				buyProjectDTO.setBuyNum(n);
				


				RedisUtil.del(("buyProject_"+buyProjectDTO.getPsp_id()+"_"+this.getSessionUser().getuId()).getBytes());
				RedisUtil.set(("buyProject_"+buyProjectDTO.getPsp_id()+"_"+this.getSessionUser().getuId()).getBytes(),SerializationUtil.serialize(buyProjectDTO));
				RedisUtil.expire(("buyProject_"+buyProjectDTO.getPsp_id()+"_"+this.getSessionUser().getuId()).getBytes(), 6*60*60);
				
				mv.addObject("buyProjectDTO", buyProjectDTO);
				mv.setViewName("pointService/order/venue-order");
			}else{
				mv.setViewName("404");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}
	
	/**
	 * 商家查看所有项目
	 */
	@RequestMapping("MyProject")
	public ModelAndView MyProject(ModelAndView mv,Page<String> page){
		page.setT(this.getSessionUser().getuId());
		try {
			List<PointServiceProjects> list = pointServiceProjectsServiceImpl.findProjectByUserId(page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mv;
	}
	
	/**
	 * 方法描述：修改项目信息
	 * 返回类型：ModelAndView
	 * @param mv
	 * @param session
	 * @param ps_id
	 * @return
	 */
	@RequestMapping(value="/editProject")
	public ModelAndView reply(ModelAndView mv, HttpSession session, @RequestParam(value="id")String psp_id){
		try {
			//获取场馆描述信息
			ProjectUpdateDTO dto = pointServiceProjectsServiceImpl.findProjectUpdateDTOByPspId(psp_id,this.getSessionUser().getuId());
			if(dto!=null){
				mv.addObject("dto", dto);
				
				// 适合人群
				Map<String,String> crowdMap = pointComboCrowdTypeServiceImpl.shuaxinhuancun();
				
				mv.addObject("crowds", crowdMap);
				
				mv.setViewName("pointService/update/update-project");
			}else{
				mv.setViewName("500");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mv.setViewName("500");
		}
		
		return mv;
	}
	
	
	
	
	
	
	
}
