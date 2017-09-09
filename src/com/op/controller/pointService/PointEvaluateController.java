package com.op.controller.pointService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.op.controller.BaseController;
import com.op.dto.pointService.show.PointEvaluateDTO;
import com.op.dto.pointService.show.PointEvaluateReplyDTO;
import com.op.entity.pointService.PointEvaluate;
import com.op.entity.users.Users;
import com.op.plugin.page.Page;
import com.op.service.pointService.PointEvaluateService;
import com.op.util.Const;
import com.op.util.HTMLEscape;

@Controller
@RequestMapping(value="/pointEvaluate")
public class PointEvaluateController extends BaseController{

	/**
	 * 场馆评论
	 */
	@Resource(name="pointEvaluateServiceImpl")
	PointEvaluateService pointEvaluateServiceImpl;
	
	/**
	 * 场馆点评
	 */
	@RequestMapping("/pointComment")
	@ResponseBody
	public Map<String,Object> pointServiceComment(String id,Page<Map<String,String>> page,String img){
		//场馆点评
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
			Map<String,String> pageMap = new HashMap<String,String>();
			pageMap.put("id", id);
			pageMap.put("img", img);
			
			page.setT(pageMap);
			List<PointEvaluateDTO> pointEvaluateDTOList = pointEvaluateServiceImpl.findPointCommentById(page);
			
			map.put("pointEvaluateDTOList", pointEvaluateDTOList);
			map.put("page", page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
	
	/**
	 * 项目评论
	 */
	@RequestMapping("/projectComment")
	@ResponseBody
	public Map<String,Object> projectComment(String id,Page<Map<String,String>> page,String img){
		//项目评论
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
			Map<String,String> pageMap = new HashMap<String,String>();
			pageMap.put("id", id);
			pageMap.put("img", img);
			
			page.setT(pageMap);
			List<PointEvaluateDTO> pointEvaluateDTOList = pointEvaluateServiceImpl.findProjectCommentById(page);
			
			map.put("pointEvaluateDTOList", pointEvaluateDTOList);
			map.put("page", page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
	
	/**
	 * 评论回复
	 */
	@RequestMapping("/replyComment")
	@ResponseBody
	public Map<String,Object> replyComment(String id,Page<String> page){
		//项目评论
		Map<String,Object> map = new HashMap<String,Object>();
		page.setPageSize(5);
		try {
			page.setT(id);
			List<PointEvaluateReplyDTO> pointEvaluateReplyDTOList = pointEvaluateServiceImpl.findPointEvaluateReplyById(page);
			
			map.put("pointEvaluateReplyDTOList", pointEvaluateReplyDTOList);
			map.put("page", page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
	
	/**
	 * 回复评论
	 */
	@RequestMapping("/replyEvaluate")
	@ResponseBody
	public Map<String,Object> replyEvaluate(String pseId,String comment,String buId){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			if(!StringUtils.isEmpty(pseId)){
				Users users = this.getSessionUser();
				
				comment = HTMLEscape.htmlEscape(comment);
				
				pointEvaluateServiceImpl.replyEvaluate(pseId,comment,users.getuId(),buId);
				map.put(Const.RESPONSE_STATE, "200");
				map.put("ruName", users.getuName());
				map.put("ruHeadImg", users.getuHeadImg());
				map.put("ruId", users.getuId());
				map.put("comment", comment);
				map.put("time", new Date());
				
				if(!StringUtils.isEmpty(buId)){
					map.put("uId", users.getuId());
					map.put("uName", users.getuName());
				}
			}else{
				map.put(Const.RESPONSE_STATE, "500");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("回复评论方法异常！！！！！！！！！！！！！", e);
			map.put(Const.RESPONSE_STATE, "500");
		}
		
		return map;
	}
	
	
	/**
	 * 删除评论
	 */
	@RequestMapping("/deleteEvaluate")
	@ResponseBody
	public Map<String,String> deleteEvaluate(String id){
		Map<String,String> map = new HashMap<String,String>();
		
		map.put("id", id);
		map.put("userId", this.getSessionUser().getuId());
		
		try {
			pointEvaluateServiceImpl.deleteEvaluate(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
	
	/**
	 * 点评场馆
	 */
	@RequestMapping("/savePointEvaluate")
	@ResponseBody
	public Map<String,String> savePointEvaluate(PointEvaluate pointEvaluate,String images){
		Map<String,String> map = new HashMap<String,String>();
		
		try {
			Users users = this.getSessionUser();
			
			pointEvaluate.setPse_create_user_id(users.getuId());
			if(StringUtils.isEmpty(pointEvaluate.getPse_venue_order_id())){
				pointEvaluate.setAudit_state("0");
			}else{
				pointEvaluate.setAudit_state("20");
			}
			
			pointEvaluateServiceImpl.saveEvaluate(pointEvaluate,images);
			map.put(Const.RESPONSE_STATE, "200");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put(Const.RESPONSE_STATE, "500");
		}
		
		return map;
	}
	
}
