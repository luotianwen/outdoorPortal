package com.op.service.comments;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.op.entity.comments.ActiveCommentBack;
import com.op.plugin.page.Page;

/**
 * 评价回复Service
 * @author Yan
 *
 */
@Service("activeCommentBackService")
public interface ActiveCommentBackService {
	
	/**
	 * 方法描述：评论回复
	 * 返回类型：void
	 * @param back
	 * @throws Exception
	 */
	void insert(ActiveCommentBack back)throws Exception;
	
	/**
	 * 方法描述：评价详情页面加载回复数据
	 * 返回类型：java.util.List<com.op.dto.activity.comment.CommentInfoBackDTO>
	 * @param page
	 * @return
	 * @throws Exception
	 */
	void loadReply(Page<String> page)throws Exception;
	
	/**
	 * 方法描述：评论回复
	 * 返回类型：void
	 * @param map
	 * @throws Exception
	 */
	void save(Map<String,String> map)throws Exception;
	
}
