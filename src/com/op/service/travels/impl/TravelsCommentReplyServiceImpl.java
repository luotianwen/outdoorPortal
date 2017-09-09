package com.op.service.travels.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.entity.travels.TravelsCommentReply;
import com.op.plugin.page.Page;
import com.op.service.travels.TravelsCommentReplyService;


@Service("travelsCommentReplyServiceImpl")
public class TravelsCommentReplyServiceImpl implements TravelsCommentReplyService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	/**
	 * 方法描述：分页查询回复信息
	 * 实现接口：@see com.op.service.travels.TravelsCommentReplyService#findCommentReplys(com.op.plugin.page.Page)
	 * @param page
	 * @throws Exception
	 */
	@Override
	public void findCommentReplys(Page<String> page) throws Exception {
		List<TravelsCommentReply> replys = (List<TravelsCommentReply>) dao.findForList("travelsCommentReplyMapper.findCommentReplysPage", page);
		page.setResultList(replys);
	}

	/**
	 * 方法描述：回复层主
	 * 实现接口：@see com.op.service.travels.TravelsCommentReplyService#insertReplyComment(com.op.entity.travels.TravelsCommentReply)
	 * @param reply
	 * @throws Exception
	 */
	@Override
	public void insertReplyComment(TravelsCommentReply reply) throws Exception {
		// TODO Auto-generated method stub
		dao.save("travelsCommentReplyMapper.insertReplyComment", reply);
	}
	
}
