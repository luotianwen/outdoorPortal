package com.op.service.comments;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.op.dto.activity.comment.CommentInfoDTO;
import com.op.entity.comments.ActiveComments;
import com.op.plugin.page.Page;


@Service("commentsService")
public interface ActiveCommentsService {
	
	/**
	 * 方法描述：用户评论活动
	 * 返回类型：void
	 * @param co
	 * @param map
	 * @throws Exception
	 */
	void insertComment(ActiveComments co,Map<String,Object> map,String images) throws Exception;
	
	/**
	 * 方法描述：计算该活动的满意度 和 参与评价的人数
	 * 返回类型：Map<String,Object>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Map<String,Object> evaluateNumAndSatisfaction(int id) throws Exception;
	
	/**
	 * 方法描述：最新评论数据
	 * 返回类型：List<ActiveComments>
	 * @param page
	 * @return
	 * @throws Exception
	 */
	void newDatas(Page<String> page) throws Exception;
	
	
	/**
	 * 方法描述：最热评论数据
	 * 返回类型：List<ActiveComments>
	 * @param page
	 * @return
	 * @throws Exception
	 */
	void hotDatas(Page<String> page) throws Exception;
	
	/**
	 * 方法描述：活动详情	→	评价详情
	 * 返回类型：CommentInfoDTO
	 * @param id
	 * @return
	 * @throws Exception
	 */
	CommentInfoDTO commentInfo(String id) throws Exception;
	
}
