package com.op.service.follow;

import java.util.Map;

/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：FollowUserService   
* 类描述：   
* 创建人：Win Zhong   
* 创建时间：2015年12月23日 下午4:23:18   
* 修改人：Win Zhong   
* 修改时间：2015年12月23日 下午4:23:18   
* 修改备注：   
* @version    
*
 */
public interface FollowUserService {

    /**
     * 关注取消关注用户
     * @param obj
     * @return
     * @throws Exception
     */
	void isFollowUser(Map<String,Object> map)throws Exception;
	
}
