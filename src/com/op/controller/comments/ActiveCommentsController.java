package com.op.controller.comments;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.op.dto.activity.comment.CommentInfoDTO;
import com.op.dto.activity.comment.LoadEvaluateDTO;
import com.op.entity.comments.ActiveComments;
import com.op.entity.users.Users;
import com.op.service.comments.ActiveCommentsService;
import com.op.util.Const;

/**
 * 活动评论信息表(comments)Controller
 * 
 * @author 世峰户外商城
 * @version Revision: 1.00 Date: 2016-01-08 10:42:02
 */
@Controller
@RequestMapping(value = "/activeComments")
public class ActiveCommentsController {

	@Resource(name = "commentsServiceImpl")
	private ActiveCommentsService commentsServiceImpl;

	/**
	 * 方法描述：用户评论活动 返回类型：Map<String,Object>
	 * 
	 * @param session
	 * @param co
	 * @return
	 */
	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> comment(HttpSession session, ActiveComments co,String images) {
		Map<String, Object> map = new HashMap<String, Object>();
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		// 用户ID
		co.setUser_id(user.getuId());

		try {
			commentsServiceImpl.insertComment(co, map,images);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, "更改异常，请稍后重试!");
		}
		return map;
	}

	/**
	 * 方法描述：异步加载活动评价信息 返回类型：LoadEvaluateDTO
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/loadEvaluateData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loadEvaluateData(LoadEvaluateDTO dto) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 计算该活动的满意度 和 参与评价的人数
		try {
			// 计算该活动的满意度 和 参与评价的人数 返回类型
			evaluateNumAndSatisfaction(dto);
			
			// 查询最新评价
			if(dto.isFindNe()){
				dto.getnE().setT(dto.getaId()+"");
				commentsServiceImpl.newDatas(dto.getnE());	
			}
			
			// 查询最热评价
			if(dto.isFindHe()){
				dto.gethE().setT(dto.getaId()+"");
				commentsServiceImpl.hotDatas(dto.gethE());	
			}
			
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
			map.put(Const.RESPONSE_DATA, dto);
		} catch (Exception e) {
			
			System.out.println(e.toString());
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, "查询异常!");
		}

		return map;
	}

	/**
	 * 方法描述：计算该活动的满意度 和 参与评价的人数 返回类型：void
	 * 
	 * @param dto
	 * @throws Exception
	 */
	void evaluateNumAndSatisfaction(LoadEvaluateDTO dto) throws Exception {
		Map<String, Object> map = commentsServiceImpl.evaluateNumAndSatisfaction(dto.getaId());
		dto.setSatisfaction(map.get("SATISFACTION") == null ? 100d : Double.parseDouble(map.get("SATISFACTION").toString()));
		dto.setEvaluateNum(Integer.parseInt(map.get("EVALUATENUM").toString()));
	}
	

	/**
	 * 方法描述：活动评价详情
	 * 返回类型：ModelAndView
	 * @param mv	
	 * @param id	评价ID
	 * @return
	 */
	@RequestMapping(value = "/comment/{id}")
	public ModelAndView comment(ModelAndView mv,
			@PathVariable(value="id") String id,
			HttpSession session) throws Exception{
		// 当前操作用户
		Users user = (Users) session.getAttribute(Const.SESSION_USER);

		CommentInfoDTO dto = commentsServiceImpl.commentInfo(id);
		
		// 引入的头部文件需要的数据
		setHeaderMap(mv);

		mv.addObject("id", id);
		mv.addObject("dto", dto);
		mv.setViewName("activity/detail/comment");
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
		map.put("ht", "活动");
		mv.addObject("map", map);
	}
	
}
