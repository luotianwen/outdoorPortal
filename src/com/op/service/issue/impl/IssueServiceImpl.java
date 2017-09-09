package com.op.service.issue.impl;

import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.op.dao.BaseDao;
import com.op.service.issue.IssueService;

/**
 * 意见建议(实现类)
 * @author panyongwei
 * Date: 2016年1月11日 11:49:00
 */
@Service("issueServiceImpl")
public class IssueServiceImpl implements IssueService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	/**
	 * 添加意见建议
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void insertIssue(Map<String, Object> map) throws Exception {
		dao.save("issueMapper.insertIssue",map);
	}
	
}
