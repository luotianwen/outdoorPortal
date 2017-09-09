package com.op.controller.travels;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;






import com.op.controller.BaseController;
import com.op.dto.travels.MyAllTravelsDTO;
import com.op.dto.travels.MyTravelsResultsDTO;
import com.op.dto.travels.TravelsDraftDTO;
import com.op.dto.travels.TravelsHotDataDTO;
import com.op.dto.travels.TravelsModifyDTO;
import com.op.dto.travels.TravelsModifyData;
import com.op.dto.travels.TravelsSearchDTO;
import com.op.dto.travels.show.TravelsPerfectInfoDTO;
import com.op.dto.travels.show.UpdateTravelsNewInfo;
import com.op.dto.travels.updateImg.ReceptionUpdateImgDTO;
import com.op.entity.travels.Travels;
import com.op.entity.travels.TravelsComment;
import com.op.entity.travels.TravelsCommentReply;
import com.op.entity.travels.TravelsContent;
import com.op.entity.users.Users;
import com.op.plugin.page.Page;
import com.op.service.messageprivate.MessagePrivateService;
import com.op.service.recentVisit.RecentVisitService;
import com.op.service.travels.TravelsCommentReplyService;
import com.op.service.travels.TravelsCommentService;
import com.op.service.travels.TravelsContentService;
import com.op.service.travels.TravelsFollowService;
import com.op.service.travels.TravelsPhotosService;
import com.op.service.travels.TravelsPraiseService;
import com.op.service.travels.TravelsService;
import com.op.service.travels.TravelsTitleService;
import com.op.service.userFollow.UserFollowService;
import com.op.service.users.UsersService;
import com.op.solr.SolrPage;
import com.op.solr.util.ScenicSpotSearchService;
import com.op.solr.util.TravelsSearchService;
import com.op.util.Const;
import com.op.util.DateUtil;
import com.op.util.FreeMarkerUtil;
import com.op.util.HTMLEscape;
import com.op.util.Tools;
import  com.op.util.jedis.RedisUtil;
import com.op.util.words.WordsAnalyzer;

/** 
 * 游记(travels)Controller
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-04-18 14:30:31 
 */ 
@Controller
@RequestMapping(value="/travels")
public class TravelsController extends BaseController {

	// servlet 上下文
	String contextPath = null;
    
    // 模板文件名
    String ftl = null;
    
	/**
	 * 游记
	 */
	@Resource(name="travelsServiceImpl")
	TravelsService travelsServiceImpl;
	
	/**
	 * 游记内容
	 */
	@Resource(name="travelsContentServiceImpl")
	TravelsContentService travelsContentServiceImpl;
	
	/**
	 * 游记照片
	 */
	@Resource(name="travelsPhotosServiceImpl")
	TravelsPhotosService travelsPhotosServiceImpl;
	
	/**
	 * 游记标题
	 */
	@Resource(name="travelsTitleServiceImpl")
	TravelsTitleService travelsTitleServiceImpl;
	
	/**
	 * 搜索工具类
	 */
	@Resource(name="scenicSpotSearchService")
	ScenicSpotSearchService scenicSpotSearchService;
	
	/**
	 * 游记收藏
	 */
	@Resource(name="travelsFollowServiceImpl")
	TravelsFollowService travelsFollowServiceImpl;
	
	/**
	 * 游记点赞
	 */
	@Resource(name="travelsPraiseServiceImpl")
	TravelsPraiseService travelsPraiseServiceImpl;
	
	/**
	 * 游记评论
	 */
	@Resource(name="travelsCommentServiceImpl")
	TravelsCommentService travelsCommentServiceImpl;

	/**
	 * 游记回复
	 */
	@Resource(name="travelsCommentReplyServiceImpl")
	TravelsCommentReplyService travelsCommentReplyServiceImpl;
	
	/**
	 * 游记搜索
	 */
	@Resource(name="travelsSearchService")
	TravelsSearchService travelsSearchService;
	
	
	@Resource(name="freeMarkerUtil")
	FreeMarkerUtil freeMarkerUtil;
	
	/**
	 * 最近访问
	 */
	@Resource(name="recentVisitServiceImpl")
	RecentVisitService recentVisitServiceImpl;
	
	/**
	 * 用户关注
	 */
	@Resource(name="userFollowServiceImpl")
	UserFollowService userFollowServiceImpl;
	
	/**
	 * 用户
	 */
	@Resource(name="usersServiceImpl")
    UsersService usersService;
	
	/**
	 * 发送站内信
	 */
	@Resource(name="messagePrivateServiceImpl")
	MessagePrivateService messagePrivateServiceImpl;
	
	Logger log = Logger.getLogger(this.getClass());

	/**
	 * 游记草稿
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="travelsdraft")
	public ModelAndView travelsdraft(String id,ModelAndView mv,RedirectAttributes redirectAttributes) {
		try {
			Travels travels = new Travels();
			//有ID则通过ID查询游记草稿
			if(null!=id&&!"".equals(id)){
				Map<String,String> map = new HashMap<String,String>();
				map.put("id", id);
				map.put("user_id", this.getSessionUser().getuId());
				
				travels = travelsServiceImpl.findTravelsById(map);
				
				if(travels!=null){
					//获取图片数量
					int photoscount = travelsPhotosServiceImpl.findTravelsPhotosCountByTagId(id);
					//获取标题数量
					int titlecount = travelsTitleServiceImpl.findTravelsTitleCountByTagId(id);
					
					mv.addObject("photoscount",photoscount);
					mv.addObject("titlecount",titlecount);
					mv.addObject("travels",travels);
					if(travels.getTravelsContentList().size()>0){
						//当前排序
						mv.addObject("sort",travels.getTravelsContentList().get(travels.getTravelsContentList().size()-1).getSort());
					}
					
					mv.setViewName("travels/release/modify-note");
				}else{
					mv.setViewName("redirect:404.html");
				}
			}else{
				//没有ID则新建游记草稿
				try {
					Map<String,String> map = new HashMap<String,String>();
					Users users = this.getSessionUser();
					map.put("user_id", users.getuId());
					
					//游记草稿的数量(最多10个)
					int count = travelsServiceImpl.travelsDraftCount(map);
					if(count>=10){
						//type 页面提示超出
						redirectAttributes.addFlashAttribute("type", "full");
						
						mv.setViewName("redirect:/travels/draft.html");
					}else{
						travels.setUser_id(this.getSessionUser().getuId());
						travelsServiceImpl.saveTravels(travels);
						
						mv.setViewName("redirect:/travels/travelsdraft.html?id="+travels.getId());
					}
				} catch (Exception e) {
					log.error("游记travelsdraft方法跳转草稿箱异常",e);
				}
				
			}
		} catch (Exception e) {
			log.error("游记travelsdraft方法异常",e);
		}
		return mv;
	}

	/**
	 * 保存修改游记
	 * @param travelsCreateDTO
	 * @return
	 */
	@RequestMapping(value="create")
	@ResponseBody
	public Map<String,Object> create(TravelsModifyDTO travelsModifyDTO,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Users user = this.getSessionUser();
			travelsModifyDTO.setUser_id(user.getuId());
			
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
			switch (travelsModifyDTO.getAct()) {
			case "insert":
				//新增游记内容
				travelsContentServiceImpl.addTravelsContent(travelsModifyDTO, map);
				break;
			case "update":
				//修改游记内容
				travelsContentServiceImpl.updateTravelsContent(travelsModifyDTO, map);
				break;
			case "delete":
				//删除游记内容
				travelsContentServiceImpl.delTravelsContent(travelsModifyDTO, map,request);
				break;
			default:
				map.put(Const.RESPONSE_STATE, "500");
				map.put(Const.ERROR_INFO, "服务异常，请稍后再试");
				break;
			}
			
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, "服务异常，请稍后再试");
		}
		return map;
	}
	
	/**
	 * 给所有内容排序
	 * @param contentIds
	 * @return
	 */
	@RequestMapping(value="sort")
	@ResponseBody
	public Map<String,String> updatesort(String contentIds,String id) {
		Map<String,String> map = new HashMap<String,String>();
		try {
			Map<String,String> travelsMap = new HashMap<String,String>();
			travelsMap.put("contentIds",contentIds);
			travelsMap.put("id",id);
			travelsContentServiceImpl.updatesort(travelsMap);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "服务器异常，请稍后再试");
			log.error("游记updatesort方法异常", e);
		}
		return map;
	}
	
	/**
	 * 删除游记
	 */
	@RequestMapping(value="delTravels")
	@ResponseBody
	public Map<String,Object> delTravels(String id) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
			Users user = this.getSessionUser();
			map.put("id", id);
			map.put("user_id", user.getuId());
			
			travelsServiceImpl.updateDelTravels( map);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			map.put(Const.RESPONSE_STATE, "500");
			log.error("游记delTravels方法异常", e);
		}
		
		return map;
	}
	
	/**
	 * 方法描述：查看游记详情
	 * 返回类型：ModelAndView
	 * @param mv
	 * @param id  游记ID
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/detail/{id}")
	public ModelAndView detail(ModelAndView mv, @PathVariable(value="id")String id,String type, HttpServletRequest request) {
		try {
			Map<String,Object> map = travelsServiceImpl.findTravelsState(id);
			
			String userId = "";
			Users user = this.getSessionUser();
			if(user!=null){
				userId = user.getuId();
			}
			
			if((("3".equals(map.get("ISSUED_STATE")+"")||"5".equals(map.get("ISSUED_STATE")+""))&&"0".equals(map.get("ISDELETE")+""))||userId.equals(map.get("USER_ID")+"")||"admin".equals(type)){
				travelsContentServiceImpl.updateTravelsRead(id);
				
				// 缓存不存在数据
				if(RedisUtil.hkeys(Const.TRAVELSHOTDATAKEY+id).isEmpty()){
					// 获取该游记数据
					TravelsHotDataDTO th = travelsServiceImpl.findHotData(id.toString());
					
					//存入热数据到缓存
					RedisUtil.hmset(Const.TRAVELSHOTDATAKEY+id,Tools.transBean2MapString(th));
					//108000秒过期 即一天半后自动删除缓存
					RedisUtil.expire(Const.TRAVELSHOTDATAKEY+id, (int) (1.5*24*60*60));
					
				}else{
					List<String> hotData = RedisUtil.hmget(Const.TRAVELSHOTDATAKEY+id,"read_count");
					RedisUtil.hset(Const.TRAVELSHOTDATAKEY+id, "read_count",Integer.valueOf(hotData.get(0))+1+"");
				}
				
				// servlet 上下文
				contextPath = request.getServletContext().getRealPath("/");
				// 要生成的目标目录及文件
				String newUrl = "/view/travels/show/"+id+".html";

			    // 创建该文件对象判断是否存在
				File newFile = new File(contextPath+newUrl);
				
				Users users = getSessionUser();

				if(newFile.exists()){
					mv.setViewName("travels/show/"+id);
				}else{
					try {
				    	// 初次加载要赋值
					    if(ftl == null){
						    // servlet 上下文
					    	contextPath = request.getServletContext().getRealPath("/");
					    	
					    	// 模板文件名
					    	ftl = "static/ftl/travels/travels-show.html";
					    }
					    
					    // 要生成的目标目录及文件
					    String targetFile = "/view/travels/show/"+id+".html";
			
					    // 创建该文件对象判断是否存在
					    File file = new File(contextPath+targetFile);
					    
					    //if(!file.exists()){
					    	// 后台调取数据生成模板文件
					    	travelsServiceImpl.selectInfoById(id ,ftl,targetFile ,contextPath );
					    //}
					} catch (Exception e) {
						log.error("游记detail方法异常", e);
					}
					
					newFile = new File(contextPath+newUrl);
					if(newFile.exists()){
						mv.setViewName("travels/show/"+id);
					}else{
						mv.setViewName("not");
					}
				}
			}else{
				mv.setViewName("not");
			}
		} catch (Exception e) {
			logger.error("游记展示页异常，更新被查看次数异常！！！！！！！", e);
		}
		
		return mv;
	}
	
	/**
	 * 生成游记展示页
	 */
	@RequestMapping(value="/newTravels/{id}")
	@ResponseBody
	public Map<String,String> newTravels(@PathVariable(value="id")String id, HttpServletRequest request){
		Map<String,String> map = new HashMap<String,String>();
		map.put(Const.RESPONSE_STATE, "500");
		map.put(Const.ERROR_INFO, "服务器异常！");
		try {
	    	// 初次加载要赋值
		    if(ftl == null){
			    // servlet 上下文
		    	contextPath = request.getServletContext().getRealPath("/");
		    	
		    	// 模板文件名
		    	ftl = "static/ftl/travels/travels-show.html";
		    }
		    
		    // 要生成的目标目录及文件
		    String targetFile = "/view/travels/show/"+id+".html";
		    
	    	// 后台调取数据生成模板文件
	    	travelsServiceImpl.selectInfoById(id ,ftl,targetFile ,contextPath );
	    	map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
	    	map.remove(Const.ERROR_INFO);
	    	

	    	boolean bl = RedisUtil.hkeys(Const.TRAVELSHOTDATAKEY+id).isEmpty();
			//判断Redis里是否有游记点赞，收藏……等热数据
			if(bl){
				try {
					TravelsHotDataDTO th = travelsServiceImpl.findHotData(id.toString());
					//存入热数据到缓存
					RedisUtil.hmset(Const.TRAVELSHOTDATAKEY+id,Tools.transBean2MapString(th));
					//108000秒过期 即一天半后自动删除缓存
					RedisUtil.expire(Const.TRAVELSHOTDATAKEY+id, 108000);
				} catch (Exception e) {
					log.error("发布游记生成静态页面时，添加缓存异常！！！！！！！", e);
					e.printStackTrace();
				}
			}
			
		} catch (Exception e) {
			log.error("游记newTravels方法异常", e);
		}
		
		return map;
	}
	
//	/**
//	 * 游记审核成功生成静态页面
//	 */
//	@RequestMapping(value="/auditSuccess")
//	public void auditSuccess(String id,HttpServletRequest request){
//		if(null!=id&&!"".equals(id)){
//			Map<String, String> map;
//			try {
//				map = travelsServiceImpl.findTravelsState(id);
//				if(map.get("ISSUED_STATE").equals("3")){
//					// servlet 上下文
//					contextPath = request.getServletContext().getRealPath("/");
//					// 要生成的目标目录及文件
//					String newUrl = "/view/travels/show/"+id+".html";
//
//				    // 创建该文件对象判断是否存在
//					File newFile = new File(contextPath+newUrl);
//					
//					
//					if(!newFile.exists()){
//						// 初次加载要赋值
//					    if(ftl == null){
//					    	// 模板文件名
//					    	ftl = "static/ftl/travels/travels-show.html";
//					    }
//					    
//				    	// 后台调取数据生成模板文件
//				    	travelsServiceImpl.selectInfoById(id ,ftl,newUrl ,contextPath );
//				    	map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
//					}else{
//						newFile.renameTo(new File(id+".html"));   //改名     
//					}
//			    	
//				}
//			} catch (Exception e) {
//				log.error("游记审核成功生成静态页面时异常（auditSuccess方法）！！！！！！！！", e);
//			}
//			
//		}
//		
//	}
	
	/**
	 * 方法描述：发布游记	→	添加图片	→	查询标签	→	辅助搜索
	 * 返回类型：java.util.List<com.op.dto.mdd.PoiDTO>
	 * @param keyword
	 * @return
	 */
	@RequestMapping(value="/selectTags")
	@ResponseBody
	public java.util.List<com.op.dto.mdd.PoiDTO> selectTags(@RequestParam(value="keyword") String keyword){
		java.util.List<com.op.dto.mdd.PoiDTO> list = null;
		try {
			list = scenicSpotSearchService.getScenicSpotSearchResults(keyword);
		} catch (SolrServerException e) {
			log.error("查询标签异常", e);
		}
		return list;
	}
	
	
	/**
	 * 方法描述：发布游记	→	添加图片	→	完成		→	更新图片标签
	 * 返回类型：java.util.List<com.op.dto.mdd.PoiDTO>
	 * @param keyword
	 * @return
	 */
	@RequestMapping(value="/updateImgTag")
	@ResponseBody
	public java.util.Map<String, Object> updateImgTag(ReceptionUpdateImgDTO dto){
		java.util.Map<String, Object> map = new java.util.HashMap<String,Object>();
		Users user = this.getSessionUser();
		map.put("imgs", dto.getImg());
		map.put("user_id", user.getuId());
		try {
			travelsPhotosServiceImpl.updateImgTag(map);
		} catch (Exception e) {
			log.error("发布游记	→	添加图片	→	完成		→	更新图片标签【异常】", e);
		}
		map.clear();
		return map;
	}
	
	
	
	/**
	 * 方法描述：查询游记草稿
	 * @throws Exception
	 */
	@RequestMapping(value="/draft")
	@ResponseBody
	public ModelAndView draft(ModelAndView mv) {
		try {
			Map<String,String> map = new HashMap<String,String>();
			Users users = this.getSessionUser();
			map.put("user_id", users.getuId());
			List<TravelsDraftDTO> list = travelsServiceImpl.findTravelsDraft(map);
			if(list.size()>0){
				setUserCenterHeaderMap(mv, "我的管家", "游记草稿");
				mv.addObject("list", list);
				mv.setViewName("travels/draft/my-draft");
			}else{
				mv.setViewName("redirect:/travels/travelsdraft.html");
			}
		} catch (Exception e) {
			log.error("游记draft方法异常", e);
		}
		return mv;
	}
	
	/**
	 * 预览
	 * @param mv
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/preview")
	public ModelAndView preview(ModelAndView mv,String id){
		try {
			Travels travels = new Travels();
			Map<String,String> map = new HashMap<String,String>();
			map.put("id", id);
			map.put("user_id", this.getSessionUser().getuId());
			
			travels = travelsServiceImpl.findTravelsById(map);
			
			if(travels!=null){
				
				if(travels!=null){
					mv.addObject("travels",travels);
				}
				
				mv.setViewName("travels/preview/view");
			}else{
				mv.setViewName("redirect:404.html");
			}
		} catch (Exception e) {
			log.error("游记preview方法异常", e);
		}
		 
		return mv;
	}
	
	/**
	 * 首页游记推送
	 */
	@RequestMapping(value="/article")
	@ResponseBody
	public Object article(TravelsSearchDTO travelsSearchDTO,SolrPage page) {
		try {
			 page.setPageSize(6);
		     travelsSearchService.getTravelsSearchResults(travelsSearchDTO, page);
		     
		     Map<String,Object> map = new HashMap<String,Object>();
			 map.put("travelsList", page.getResult());
			 String str = freeMarkerUtil.generateStringByTemplate("view/travels/index-note-json.ftl", map);
		     //System.out.println("---------------"+str);
		     page.setResult(str);
			/* List<StringBuffer> htmlList = new ArrayList<StringBuffer>(); 
			 StringBuffer html = new StringBuffer();
			 for(TravelsSearchResultsDTO travels:(List<TravelsSearchResultsDTO>)page.getResult()){
				    html.append("<li>");
				    html.append("<div class=\"note-img\"><a href=\"#\" target=\"_blank\"><img src=\""+travels.getShow_image()+"\" /></a></div>");
				    html.append("<div class=\"note-title\" limit=\"18\"><a href=\"#\" target=\"_blank\" >"+travels.getTitle()+"</a></div>");
				    html.append("<div class=\"note-info\"><a href=\"#\" class=\"note-user\"><img  src=\""+travels.getuHeadImg()+"\">"+travels.getUserName()+"</a><span class=\"note-date\">"+DateUtil.YYYY_MM_DDgetDay(travels.getIssued_time())+"</span><a href=\"#\" class=\"note-good on\">"+travels.getLike_count()+"<i></i></a></div>");
				    html.append("<div class=\"note-word\"><a href=\"#\" target=\"_blank\">"+travels.getContent()+"</a></div>");
				    html.append("<div class=\"note-extra\">");
				    html.append("<a href=\"#\" class=\"note-place\"><i></i>"+travels.getAddress()+"</a>");
				    html.append("<span class=\"note-view\"><i></i>"+travels.getRead_count()+"/"+travels.getReply_count()+"</span>");
				    html.append("<span class=\"note-star\"><i></i>"+travels.getCollection_count()+"</span>");
				    html.append("</div>");
				    html.append("</li>");
				}
			 htmlList.add(html);*/
			 //page.setResult(htmlList);
		    /* for(TravelsSearchResultsDTO travels:(List<TravelsSearchResultsDTO>)page.getResult()){
		    	 	travels.setCover_image(travels.getCover_image());
				}*/
		     
			return page;
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 方法描述：收藏游记
	 * 返回类型：Map<String,String>
	 * @param id	游记ID
	 * @return
	 */
	@RequestMapping(value="/follow",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> followTravels(@RequestParam(value="id") String id){
		Users user = this.getSessionUser();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", user.getuId());
		map.put("id", id);
		
		try {
			travelsFollowServiceImpl.updateFollowTravels(map);
		} catch (Exception e) {
			log.error("收藏游记异常", e);
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, Const.ERROR_INFO_STR);
		}
		return map;
	}
	
	
	/**
	 * 方法描述：游记点赞
	 * 返回类型：Map<String,String>
	 * @param id	游记id
	 * @return
	 */
	@RequestMapping(value="/praise")
	@ResponseBody
	public Map<String,Object> travelsPraise(@RequestParam(value="id") String id){
		Users user = this.getSessionUser();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", user.getuId());
		map.put("id", id);
		
		try {
			travelsPraiseServiceImpl.updatePraise(map);
		} catch (Exception e) {
			log.error("游记点赞异常", e);
			map.clear();
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, Const.ERROR_INFO_STR);
		}
		return map;
	}
	
	
	/**
	 * 方法描述：更新游记最新数据
	 * 返回类型：Map<String,String>
	 * @param id	游记id
	 * @return
	 */
	@RequestMapping(value="/updateNewInfo")
	@ResponseBody
	public Map<String,Object> updateNewInfo(@RequestParam(value="id") String id){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			UpdateTravelsNewInfo info = new UpdateTravelsNewInfo();
			Users user = this.getSessionUser();

			TravelsHotDataDTO dto = null;
			
			// 缓存中数据不存在那么查询最新的数据
			if(RedisUtil.hkeys(Const.TRAVELSHOTDATAKEY+id).isEmpty()){
				dto = travelsServiceImpl.findHotData(id);
				
				if(!StringUtils.isEmpty(dto.getDeparture_time())){
					dto.setDeparture_time_str(DateUtil.YYYY_MM_DDgetDay(dto.getDeparture_time()));
				}
				
				//存入热数据到缓存
				RedisUtil.hmset(Const.TRAVELSHOTDATAKEY+id,Tools.transBean2MapString(dto));
				
				//108000秒过期 即一天半后自动删除缓存
				RedisUtil.expire(Const.TRAVELSHOTDATAKEY+id, (int) (1.5*24*60*60));
			}
			
			// 获取缓存中该游记点赞量
			this.getTravelsPraiseNum( info ,id ,dto);
			
			// 获取缓存中该游记的收藏量
			this.getTravelsFollowNum( info, id ,dto);
			
			// 获取缓存中该游记的阅读量
			this.getTravelsReadNum( info, id ,dto);
			
			// 获取缓存中该游记的完善信息
			this.getTravelsPerfect( info, id, dto);
			
			// 获取当前用户对该游记的点赞状态和收藏状态
			if(user != null){
				map.put("userId", user.getuId());
				map.put("id", id);
				info.setuName(user.getuName());
				info.setuType(user.getuType()+"");
				info.setuHeadImg(user.getuHeadImg());
				// 当前用户ID
				info.setuId(user.getuId());
				this.getCurrentUserPraiseOrFollowState(info, map);
			}
			
			map.clear();
			map.put("info", info);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			log.error("更新游记最新数据异常", e);
			map.clear();
			map.put(Const.RESPONSE_STATE, "500");
			
		}
		return map;
	}
	

	
	/**
	 * 方法描述：异步登录更新用户点赞、收藏数据
	 * 返回类型：Map<String,String>
	 * @param id	游记id
	 * @return
	 */
	@RequestMapping(value="/findUserTravelsPraiseOrFollow")
	@ResponseBody
	public Map<String,Object> findUserTravelsPraiseOrFollow(@RequestParam(value="id") String id){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			UpdateTravelsNewInfo info = new UpdateTravelsNewInfo();
			Users user = this.getSessionUser();
			
			// 获取当前用户对该游记的点赞状态和收藏状态
			if(user != null){
				map.put("userId", user.getuId());
				map.put("id", id);
				info.setuName(user.getuName());
				info.setuHeadImg(user.getuHeadImg());
				this.getCurrentUserPraiseOrFollowState(info, map);
			}
			
			map.clear();
			map.put("info", info);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			log.error("更新状态异常", e);
			map.clear();
			map.put(Const.RESPONSE_STATE, "500");
		}
		return map;
	}
	
	
	/**
	 * 方法描述：获取当前用户对该游记的点赞状态和收藏状态
	 * 返回类型：void
	 * @param user
	 * @param info
	 * @param id
	 */
	void getCurrentUserPraiseOrFollowState(UpdateTravelsNewInfo info, Map<String,Object> map) throws Exception{
		info.setCurrentUserFollowState(travelsFollowServiceImpl.currenUsertIsFollow(map));
		info.setCurrentUserPraiseState(travelsPraiseServiceImpl.currentUserIsPraise(map));
	}
	
	/**
	 * 方法描述：获取缓存中该游记点赞量
	 * 返回类型：void
	 * @param jedis	缓存对象
	 * @param info 传输对象
	 * @param id 游记ID
	 */
	void getTravelsPraiseNum(UpdateTravelsNewInfo info,String id ,TravelsHotDataDTO dto) throws Exception{
		if(dto == null){
			List<String> hotData = RedisUtil.hmget(Const.TRAVELSHOTDATAKEY+id,"like_count");
			info.setPraiseNum(Integer.valueOf(hotData.get(0)));
		}else{
			info.setPraiseNum(Integer.valueOf(dto.getLike_count()));
		}	
	}
	
	/**
	 * 方法描述：获取缓存中该游记收藏量
	 * 返回类型：void
	 * @param jedis	缓存对象
	 * @param info 传输对象
	 * @param id 游记ID
	 */
	void getTravelsFollowNum(UpdateTravelsNewInfo info,String id ,TravelsHotDataDTO dto) throws Exception{
		if(dto == null){
			List<String> hotData = RedisUtil.hmget(Const.TRAVELSHOTDATAKEY+id,"collection_count");
			info.setFollowNum(Integer.valueOf(hotData.get(0)));
		}else{
			info.setFollowNum(Integer.valueOf(dto.getCollection_count()));
		}	
	}
	
	/**
	 * 方法描述：获取缓存中该游记阅读量
	 * 返回类型：void
	 * @param jedis	缓存对象
	 * @param info 传输对象
	 * @param id 游记ID
	 */
	void getTravelsReadNum(UpdateTravelsNewInfo info,String id ,TravelsHotDataDTO dto) throws Exception{
		if(dto == null){
			List<String> hotData = RedisUtil.hmget(Const.TRAVELSHOTDATAKEY+id,"read_count");
			info.setSeeNum(Integer.valueOf(hotData.get(0)));
		}else{
			info.setSeeNum(Integer.valueOf(dto.getRead_count()));
		}	
	}
	
	/**
	 * 方法描述：获取缓存中该游记完善信息
	 * 返回类型：void
	 * @param jedis	缓存对象
	 * @param info 传输对象
	 * @param id 游记ID
	 */
	void getTravelsPerfect(UpdateTravelsNewInfo info,String id ,TravelsHotDataDTO dto) throws Exception{
		if(dto == null){
			List<String> hotData = RedisUtil.hmget(Const.TRAVELSHOTDATAKEY+id,"departure_time_str");
			if(hotData!=null){
				info.setDeparture_time_str(hotData.get(0));
			}
			hotData = RedisUtil.hmget(Const.TRAVELSHOTDATAKEY+id,"travel_days");
			if(hotData!=null){
				info.setTravel_days(hotData.get(0));
			}
			hotData = RedisUtil.hmget(Const.TRAVELSHOTDATAKEY+id,"travel_person");
			if(hotData!=null){
				info.setTravel_person(hotData.get(0));
			}
			hotData = RedisUtil.hmget(Const.TRAVELSHOTDATAKEY+id,"per_capita_cost");
			if(hotData!=null){
				info.setPer_capita_cost(hotData.get(0));
			}
		}else{
			info.setDeparture_time_str(dto.getDeparture_time_str());
			info.setTravel_days(dto.getTravel_days());
			info.setTravel_person(dto.getTravel_person());
			info.setPer_capita_cost(dto.getPer_capita_cost());
		}
	}
	
	/**
	 * 方法描述：评论游记
	 * 返回类型：int
	 * @param comment 评论对象
	 * @return
	 */
	@RequestMapping(value="/comment")
	@ResponseBody
	public Map<String,Object> comment(@RequestParam(value="comment") String tc_content,
			@RequestParam(value="id") String tc_travelsId,
			@RequestParam(value="img",required=false) String tc_img){
		Map<String,Object> map = new HashMap<String,Object>();
		
		// 控制评论字数长度
		if(!checkContentLength(tc_content,map)){
			return map;
		}
		
		Users user = this.getSessionUser();
		TravelsComment comment = new TravelsComment(tc_content,tc_travelsId,tc_img,user.getuId(),new java.util.Date());
		try {
			travelsCommentServiceImpl.saveComment(comment);
			comment.setuName(user.getuName());
			comment.setuHeadImg(user.getuHeadImg());
			map.put("comment", comment);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			log.error("评论游记异常", e);
			map.clear();
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, Const.ERROR_INFO_STR);
		}
		return map;
	}
	/**
	 * 方法描述：过滤html代码，并且过滤表情计算字符长度（一个表情占一位）
	 * 返回类型：boolean
	 * @param tc_content
	 * @param map
	 * @return
	 */
	boolean checkContentLength(String tc_content,Map<String,Object> map){
		String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
	    Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
	    
	    tc_content = HTMLEscape.htmlEscape(tc_content);
	    String new_tc_content = tc_content;
	    new_tc_content = p_html.matcher(new_tc_content.trim()).replaceAll("1");
	    new_tc_content = HTMLEscape.htmlReverseEscape(new_tc_content);
	    if(new_tc_content.length()>100){
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, "字数不能超过100（一个表情算一个字）");
	    	return false;
	    }
	    
	    return true;
	}
	
	/**
	 * 方法描述：查询游记评论
	 * 返回类型：Map<String,Object>
	 * @param page 分页
	 * @return
	 */
	@RequestMapping(value="loadComments",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> findComment(Page<String> page,
			@RequestParam(value="id") String id){
		Map<String,Object> map = new HashMap<String,Object>();
		page.setT(id);
		
		try {
			travelsCommentServiceImpl.findCommentByTravelsId(page);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
			map.put("page", page);
		} catch (Exception e) {
			log.error("加载游记评论异常", e);
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, Const.ERROR_INFO_STR);
		}
		
		return map;
	}
	
	/**
	 * 方法描述：查询游记评论
	 * 返回类型：Map<String,Object>
	 * @param page 分页
	 * @return
	 */
	@RequestMapping(value="loadCommentsReplys",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> findCommentReplys(Page<String> page,
			@RequestParam(value="id") String id){
		Map<String,Object> map = new HashMap<String,Object>();
		page.setT(id);
		page.setPageSize(5);
		
		try {
			travelsCommentReplyServiceImpl.findCommentReplys(page);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
			map.put("page", page);
		} catch (Exception e) {
			log.error("加载游记回复异常", e);
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, Const.ERROR_INFO_STR);
		}
		
		return map;
	}
	
	/**
	 * 方法描述：回复游记评论
	 * 返回类型：Map<String,Object>
	 * @param val	回复内容
	 * @param uId	被回复用户ID
	 * @return
	 */
	@RequestMapping(value="replyComment",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> replyComment(@RequestParam(value="val") String val,
			@RequestParam(value="uId") String uId,
			@RequestParam(value="cId") int cId){
		Users user = this.getSessionUser();
		Map<String,Object> map = new HashMap<String,Object>();
		
		// 长度限制
		if(val.length() > 100){
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, "回复字数不能超过100");
			return map;
		}
		
		TravelsCommentReply reply = new TravelsCommentReply(cId,val,user.getuId(),uId);
		try {
			travelsCommentReplyServiceImpl.insertReplyComment(reply);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
			map.put("val", val);
			map.put("uName", user.getuName());
			map.put("uHeadImg", user.getuHeadImg());
		} catch (Exception e) {
			log.error("回复游记评论异常", e);
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, Const.ERROR_INFO_STR);
		}
		
		return map;
	}
	
	/**
	 * 获取游记关联地点
	 * @param id
	 * @return
	 */
	@RequestMapping(value="address")
	@ResponseBody
	public List<String> address(String id){
		List<String> list = new ArrayList<String>();
		try {
			List<TravelsContent> travelsContentList = new ArrayList<TravelsContent>();
			travelsContentList = travelsContentServiceImpl.findTravelsContentForWord(id);
			
			// 定义HTML标签的正则表达式
			String regEx_html = "<[^>]+>";
	        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			
			String content = "";
			for(int i=0;i<travelsContentList.size();i++){
				content += travelsContentList.get(i).getContent().replaceAll("<br>","");
				Set<String> addressSer = WordsAnalyzer.place(travelsContentList.get(i).getContent().replaceAll("<br>",""));

				for (String address : addressSer) {
			    	list.add(address);
			    }
			}
			TravelsModifyDTO travelsModifyDTO = new TravelsModifyDTO();
			travelsModifyDTO.setId(id);
			travelsModifyDTO.setUser_id(this.getSessionUser().getuId());
			TravelsModifyData data = new TravelsModifyData();
			
			//去除内容内所有html标签
			content = p_html.matcher(content.trim()).replaceAll("").replaceAll("&nbsp;", "");
			if(content.length()>290){
				content = content.substring(0, 290);
			}
			
			//游记内容
			data.setC(content);
			//修改游记标识
			data.setO("digest");
			
			travelsModifyDTO.setData(data);
			travelsServiceImpl.updateTravelsDigest(travelsModifyDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("查询关联地点异常", e);
		}
		
		return list;
	}
	
	
	/**
	 * 根据user id 分页查询用户游记
	 *
	 * @param page
	 * @return
	 */
	@RequestMapping(value="travelsNote")
	public ModelAndView myTravels(ModelAndView mv,Page<String> page,String id){
		try {
			Users users = userCenter(id, recentVisitServiceImpl,userFollowServiceImpl, usersService,mv);

			if (users != null) {
				page.setT(users.getuId());
				// 统计用户游记信息
				MyAllTravelsDTO mycount = travelsServiceImpl.myTravelsCount(users.getuId());
				mv.addObject("mycount", mycount);
				// 判断用户是否写过游记
				if (mycount.getTravels_count() > 0) {
					List<MyTravelsResultsDTO> travelsList = travelsServiceImpl.findTravelsByUserId(page);
					mv.addObject("travelsList", travelsList);
				}
				Map<String, Object> map = new HashMap<String, Object>();
				// 当前导航标签
				map.put("center", "我的游记");
				mv.addObject("map", map);
				mv.addObject("page", page);
				mv.addObject("users", users);

				//关注与最近访问分页查询
		    	Page<String> pageFR = new Page<String>();
		    	pageFR.setPageSize(8);
		    	
		    	//关注用户
		    	mv.addObject("follow", userFollowServiceImpl.findFollowForCenterPage(users.getuId(), pageFR));
		    	
		    	//最近访问
		    	mv.addObject("recentVisit", recentVisitServiceImpl.findRecentVisitForCenterPage(users.getuId(), pageFR));
				
		    	mv.addObject("pageFR", pageFR);
		    	
				mv.setViewName("travels/my-note");
			} else {
				mv.setViewName("404");
			}

			return mv;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}	
	/**
	 * 根据user id 分页查询用户游记
	 *
	 * @param page
	 * @return
	 */
	@RequestMapping(value="my_Travels")
	@ResponseBody
	public Object my_Travels(Page<String> page){
		try {
			Users user = this.getSessionUser();
			if(user == null){
				return page;
			}else{
				page.setT(user.getuId());
				List<MyTravelsResultsDTO> travelsList =  travelsServiceImpl.findTravelsByUserId(page);
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("travelsList", travelsList);
				String str = freeMarkerUtil.generateStringByTemplate("view/travels/my-note-json.ftl", map);
				//System.out.println("============="+str);
				page.setResultList(str);
				return page;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}
	
	/**
	 * 完善游记信息
	 * @param travelsPerfectInfoDTO
	 * @return
	 */
	@RequestMapping(value="perfectinfo")
	@ResponseBody
	public Map<String,String> perfectinfo(TravelsPerfectInfoDTO travelsPerfectInfoDTO){
		Map<String,String> map = new HashMap<String,String>();
		map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		
		try {
			travelsPerfectInfoDTO.setUser_id(this.getSessionUser().getuId());
			travelsServiceImpl.updatePerfectinfo(travelsPerfectInfoDTO);
		} catch (Exception e) {
			log.error("完善游记信息异常", e);
		}
		
		return map;
	}
	
	/**
	 * 我的收藏
	 */
	@RequestMapping(value="myCollection")
	public ModelAndView myCollection(ModelAndView mv,Page<String> page){
		try {
			Users users = this.getSessionUser();

			if (users != null) {
				page.setT(users.getuId());
				
				List<MyTravelsResultsDTO> travelsList = travelsServiceImpl.findCollectionTravelsByUserId(page);
				mv.addObject("travelsList", travelsList);
				
				Map<String, Object> map = new HashMap<String, Object>();
				// 当前导航标签
				map.put("center", "我的管家");
				map.put("left", "游记收藏");
				mv.addObject("map", map);
				mv.addObject("page", page);
				mv.addObject("users", users);

				mv.setViewName("usercenter/housekeeper/travels/my-fav");
			} else {
				mv.setViewName("404");
			}

			return mv;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	
	}
	
	/**
	 * 取消收藏
	 */
	@RequestMapping(value="cancelCollection")
	@ResponseBody
	public Map<String,String> cancelCollection(String id){
		Map<String,String> map = new HashMap<String,String>();
		
		map.put("id", id);
		map.put("userId", this.getSessionUser().getuId());
		try {
			travelsFollowServiceImpl.deleteCollection(map);
			map.clear();
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("取消收藏方法异常！！！！！！！！", e);
			map.clear();
			map.put(Const.RESPONSE_STATE, "500");
		}
		
		return map;
	}
	
}
