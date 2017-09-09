package com.op.service.issue;

import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * 意见建议(接口)
 * @author panyongwei
 * Date: 2016年1月11日 11:49:00
 */
@Service("issueService")
public interface IssueService {
	/**
	 * 添加意见建议
	 * @param map
	 * @throws Exception
	 */
	void insertIssue(Map<String,Object> map) throws Exception;
}
