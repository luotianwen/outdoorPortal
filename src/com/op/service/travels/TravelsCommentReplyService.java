package com.op.service.travels;

import org.springframework.stereotype.Service;

import com.op.entity.travels.TravelsCommentReply;
import com.op.plugin.page.Page;


@Service("travelsCommentReplyService")
public interface TravelsCommentReplyService {
	
	/**
	 * 方法描述：分页查询回复信息
	 * 返回类型：void
	 * @param page
	 * @throws Exception
	 */
	void findCommentReplys(Page<String> page) throws Exception;
	
	/**
	 * 方法描述：回复层主
	 * 返回类型：void
	 * @param reply
	 * @throws Exception
	 */
	void insertReplyComment(TravelsCommentReply reply) throws Exception;
}
