package com.op.service.userFollow;

import java.util.List;
import org.springframework.stereotype.Service;

import com.op.dto.usercenter.usercenter.UserCenterInfo;
import com.op.plugin.page.Page;


@Service("userFollowService")
public interface UserFollowService {

	/**
	 * 查询关注用户
	 * @return
	 */
	List<UserCenterInfo> findFollowForCenterPage(String userId,Page<String> page) throws Exception;
	
	/**
	 * 查询用户粉丝
	 * @return
	 */
	List<UserCenterInfo> findFansForCenterPage(String userId,Page<String> page) throws Exception;
	
	/**
	 * 添加关注用户
	 */
	void saveFollow(String userId,String login) throws Exception;
	
	/**
	 * 取消关注用户
	 */
	void deleteFollow(String userId,String login) throws Exception;
	
	/**
	 * 是否关注
	 */
	boolean isFollow(String userId,String login) throws Exception;
	
}
