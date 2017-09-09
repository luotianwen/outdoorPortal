package com.op.spot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.op.controller.BaseController;
import com.op.entity.users.Users;
import com.op.plugin.page.Page;
import com.op.spot.entity.Project;
import com.op.spot.entity.Spotcomment;
import com.op.spot.service.ProjectService;
import com.op.spot.service.SpotcommentService;
import com.op.util.Const;

/**
 * 点评
 */
@Controller
@RequestMapping(value = "/spotcomment")
public class SpotcommentController extends BaseController {
	/**
	 * 点评
	 */
	@Resource(name="spotcommentServiceImpl")
	private SpotcommentService spotcommentServiceImpl;
	
	/**
	 * 点评分类
	 */
	@Resource(name="projectServiceImpl")
	private ProjectService projectServiceImpl;
	
	/**
	 * 查询所有点评
	 * @param mv
	 * @param page
	 * @return
	 */
	@RequestMapping("/spotComment")
	@ResponseBody
	public Map<String,Object> spotComment(ModelAndView mv,Page<Map<String,String>> page,String id){
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
			map.put("spotComment", spotcommentServiceImpl.findAllSpotCommentPage(page,id));
			map.put("page", page);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			logger.error("查询所有点评方法异常！！！", e);
			map.put(Const.RESPONSE_STATE, 500);
		}
		
		return map;
	}
	
	/**
	 * 点评类别
	 */
	@RequestMapping("/project")
	@ResponseBody
	public List<Project> project(){
		List<Project> list = new ArrayList<Project>();
		
		try {
			list = projectServiceImpl.findProject();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	/**
	 * 用户点评
	 */
	@RequestMapping("/saveComment")
	@ResponseBody
	public Map<String,String> saveComment(Spotcomment spotcomment){
		Map<String,String> map = new HashMap<String,String>();
		Users users = this.getSessionUser();
		
		try {
			spotcommentServiceImpl.saveComment(users,spotcomment);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put(Const.RESPONSE_STATE, "500");
		}
		return map;
	}
	
	
}
