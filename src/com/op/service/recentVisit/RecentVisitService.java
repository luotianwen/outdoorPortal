package com.op.service.recentVisit;


import java.util.List;
import org.springframework.stereotype.Service;

import com.op.dto.usercenter.usercenter.UserCenterInfo;
import com.op.plugin.page.Page;


@Service("recentVisitService")
public interface RecentVisitService {

	/**
	 * 查询最近访问用户
	 * @return
	 */
	List<UserCenterInfo> findRecentVisitForCenterPage(String userId,Page<String> page) throws Exception;
	
	/**
	 * 新增最近访问用户
	 */
	void saveRecentVisit(String userId,String login) throws Exception;
	
	
}
