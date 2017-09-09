package com.op.service.follow.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.service.follow.FollowUserService;
import com.op.util.Const;

/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：FollowUserServiceImpl   
* 类描述：   
* 创建人：Win Zhong   
* 创建时间：2015年12月23日 下午5:48:49   
* 修改人：Win Zhong   
* 修改时间：2015年12月23日 下午5:48:49   
* 修改备注：   
* @version    
*
 */
@Service("followUserServiceImpl")
public class FollowUserServiceImpl implements FollowUserService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
    /**
     * 关注取消关注活动
     * @param obj
     * @return
     * @throws Exception
     */
	@Override
	public void isFollowUser(Map<String,Object> map)throws Exception{
		Object fu_id = dao.findForObject("followUserMapper.findByFollowUser", map);
		if(fu_id != null){//取消关注
			dao.delete("followUserMapper.deleteFollowUserById", fu_id);
		}else{
			dao.save("followUserMapper.saveFollowActivity", map);
		}
		map.clear();
		map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
	}
	
}
