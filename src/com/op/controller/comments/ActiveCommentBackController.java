package com.op.controller.comments;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.op.entity.comments.ActiveCommentBack;
import com.op.entity.users.Users;
import com.op.plugin.page.Page;
import com.op.service.comments.ActiveCommentBackService;
import com.op.util.Const;

/** 
 * 活动评论回复(activeCommentBack)Controller
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-01-15 15:04:11 
 */ 
@Controller
@RequestMapping(value="/activeCommentBack")
public class ActiveCommentBackController{
	
	@Resource(name="activeCommentBackServiceImpl")
	private ActiveCommentBackService activeCommentBackServiceImpl;
	
	/**
	 * 方法描述：评论回复
	 * 返回类型：Map<String,Object>
	 * @param session
	 * @return
	 */
	@RequestMapping(value="back",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> backComment(HttpSession session,ActiveCommentBack back){
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			if(user != null){
				
				back.setUser_id(user.getuId());
				
				activeCommentBackServiceImpl.insert(back);
				
				map.put("uName", user.getuName());
				map.put("uHeadImg", user.getuHeadImg());
				map.put("content", back.getContent());
				
				map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
				
			}else{
				map.put(Const.RESPONSE_STATE, 500);
				map.put(Const.ERROR_INFO, "请先登录");
			}
		} catch (Exception e) {
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, "回复失败");
		}
		return map;
	}
	
	/**
	 * 方法描述：评价详情页面加载回复数据
	 * 返回类型：Map<String,Object>
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/loadReply",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> loadReply(@RequestParam(value="id") String id,Page<String> page){
		Map<String,Object> map = new HashMap<String,Object>();
		page.setT(id);
		page.setPageSize(3);
		
		try {
			activeCommentBackServiceImpl.loadReply(page);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
			map.put("page", page);
		} catch (Exception e) {
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, Const.ERROR_INFO_STR);
		}
		return map;
	}
	
	

	
	/**
	 * 方法描述：评论回复
	 * 返回类型：Map<String,Object>
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> save(HttpSession session,
			@RequestParam(value="content") String content,
			@RequestParam(value="id") String co_id){
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		Map<String,String> map = new HashMap<String,String>();
		map.put("co_id", co_id);
		map.put("user_id", user.getuId());
		map.put("content", content);
		try {
			activeCommentBackServiceImpl.save(map);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, Const.ERROR_INFO_STR);
		}
		return map;
	}
	
	
	
	
}
