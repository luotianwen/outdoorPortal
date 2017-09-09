package com.op.service.travels;

import org.springframework.stereotype.Service;

import com.op.entity.travels.TravelsComment;
import com.op.plugin.page.Page;


@Service("travelsCommentService")
public interface TravelsCommentService {
	
	/**
	 * 方法描述：评论
	 * 返回类型：void
	 * @param comment
	 * @throws Exception
	 */
	void saveComment(TravelsComment comment) throws Exception;
	
	/**
	 * 方法描述：根据游记id查询评论内容
	 * 返回类型：void
	 * @param page
	 * @throws Exception
	 */
	void findCommentByTravelsId(Page<String> page) throws Exception;
}
