package com.op.service.follow.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.dto.activity.update.UpdateActiveDTO;
import com.op.dto.usercenter.keeper.MyActiveInfoDTO;
import com.op.plugin.page.Page;
import com.op.service.follow.FollowActivityService;
import com.op.util.Const;

/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：FollowActivityServiceImpl   
* 类描述：   
* 创建人：Win Zhong   
* 创建时间：2015年12月23日 下午4:24:06   
* 修改人：Win Zhong   
* 修改时间：2015年12月23日 下午4:24:06   
* 修改备注：   
* @version    
*
 */
@Service("followActivityServiceImpl")
public class FollowActivityServiceImpl implements FollowActivityService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
    /**
     * 关注取消关注活动
     * @param obj
     * @return
     * @throws Exception
     */
	@Override
	public void isFollowActivity(Map<String,Object> map)throws Exception{
		int num = (int) dao.findForObject("followActivityMapper.findByFollowActivityForMap", map);
		if(num > 0){
			//取消关注
			dao.delete("followActivityMapper.deleteFollowActivityById", map);
		}else{
			//关注
			dao.save("followActivityMapper.saveFollowActivity", map);
		}
		map.clear();
		map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
	}

	/**
	 * 方法描述：查询是否已经收藏活动
	 * 实现接口：@see com.op.service.follow.FollowActivityService#isFollow(java.util.Map)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean isFollow(UpdateActiveDTO dto) throws Exception {
		int fa_id = (int) dao.findForObject("followActivityMapper.findByFollowActivity", dto);
		return fa_id == 0?false:true;
	}

	/**
	 * 方法描述：我收藏的活动
	 * 实现接口：@see com.op.service.follow.FollowActivityService#myFollowActive(com.op.plugin.Page)
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<MyActiveInfoDTO> myFollowActive(Page<String> page) throws Exception {
		
		return (List<MyActiveInfoDTO>) dao.findForList("followActivityMapper.myFollowActivePage", page);
	}

	
}
