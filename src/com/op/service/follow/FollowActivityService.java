package com.op.service.follow;

import java.util.List;
import java.util.Map;

import com.op.dto.activity.update.UpdateActiveDTO;
import com.op.dto.usercenter.keeper.MyActiveInfoDTO;
import com.op.plugin.page.Page;


/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：FollowActivityService   
* 类描述：   
* 创建人：Win Zhong   
* 创建时间：2015年12月23日 下午4:20:12   
* 修改人：Win Zhong   
* 修改时间：2015年12月23日 下午4:20:12   
* 修改备注：   
* @version    
*
 */
public interface FollowActivityService {

    /**
     * 关注取消关注活动
     * @param obj
     * @return
     * @throws Exception
     */
	void isFollowActivity(Map<String,Object> map)throws Exception;
	
	/**
	 * 方法描述：查询是否已经收藏活动
	 * 返回类型：boolean
	 * @param map
	 * @return
	 * @throws Exception
	 */
	boolean isFollow(UpdateActiveDTO dto) throws Exception;
	
	/**
	 * 方法描述：我收藏的活动
	 * 返回类型：List<MyActiveInfoDTO>
	 * @param page
	 * @return
	 * @throws Exception
	 */
	List<MyActiveInfoDTO> myFollowActive(Page<String> page) throws Exception;

	
}
