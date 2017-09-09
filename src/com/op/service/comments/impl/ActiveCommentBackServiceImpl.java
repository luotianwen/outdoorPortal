package com.op.service.comments.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.dto.activity.comment.CommentInfoBackDTO;
import com.op.entity.comments.ActiveCommentBack;
import com.op.plugin.page.Page;
import com.op.service.comments.ActiveCommentBackService;


/**
 * 评价回复Service实现类
 * @author Yan
 *
 */
@Service("activeCommentBackServiceImpl")
public class ActiveCommentBackServiceImpl implements ActiveCommentBackService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	/**
	 * 方法描述：评论回复
	 * 实现接口：@see com.op.service.comments.ActiveCommentBackService#insert(com.op.entity.comments.ActiveCommentBack)
	 * @param back
	 * @throws Exception
	 */
	@Override
	public void insert(ActiveCommentBack back) throws Exception {
		dao.save("activeCommentBackMapper.insert", back);
	}

	/**
	 * 方法描述：评价详情页面加载回复数据
	 * 实现接口：@see com.op.service.comments.ActiveCommentBackService#loadReply(com.op.plugin.Page)
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	public void loadReply(Page<String> page) throws Exception {
		page.setResultList(dao.findForList("activeCommentBackMapper.loadReplyPage", page));
	}

	/**
	 * 方法描述：评论回复
	 * 实现接口：@see com.op.service.comments.ActiveCommentBackService#save(java.util.Map)
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void save(Map<String, String> map) throws Exception {
		dao.save("activeCommentBackMapper.save", map);
	}
}
