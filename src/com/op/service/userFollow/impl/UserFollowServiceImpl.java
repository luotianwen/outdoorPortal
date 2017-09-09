package com.op.service.userFollow.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.op.dao.BaseDao;
import com.op.dto.usercenter.usercenter.UserCenterInfo;
import com.op.entity.userFollow.UserFollow;
import com.op.plugin.page.Page;
import com.op.service.userFollow.UserFollowService;


@Service("userFollowServiceImpl")
public class UserFollowServiceImpl implements UserFollowService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	
	/**
	 * 查询关注用户
	 * @return
	 */
	@Override
	public List<UserCenterInfo> findFollowForCenterPage(String userId,Page<String> page) throws Exception{
		/*将查询关注用户加入缓存的方法
		 *
		
		byte[] bs = RedisUtil.get(("follow_"+userId).getBytes());
		
		List<UserCenterInfo> list = (List<UserCenterInfo>) SerializationUtil.deserialize(bs);
    	
    	if(list==null){
    		//查询所有粉丝
    		list = (List<UserCenterInfo>) dao.findForList("userFollowMapper.findFollowForCenter",userId);
			
			RedisUtil.del(("follow_"+userId).getBytes());
			RedisUtil.set(("follow_"+userId).getBytes(),SerializationUtil.serialize(list));
			//259200秒过期 即三天后自动删除缓存
			RedisUtil.expire(("follow_"+userId).getBytes(), (int) (60));
    	}

    	List<UserCenterInfo> finalList = new ArrayList<UserCenterInfo>();
    	
    	//每页显示条数
    	int pageSize = page.getPageSize();
    	//List条数是否够一页
    	if(list.size()>pageSize){
    		//获取总页数
        	if(list.size()%pageSize>0){
        		page.setTotalPage(list.size()/pageSize+1);
        	}else{
        		page.setTotalPage(list.size()/pageSize);
        	}
        	//当前页数
        	int currentPage = page.getCurrentPage();
        	
        	//当前页不是最后一页
        	if(list.size()>pageSize*currentPage){
        		for(int i=list.size()-pageSize*(currentPage-1)-1,len=list.size()-pageSize*currentPage;i>=len;i--){
        			finalList.add(list.get(i));
            	}
        	}else{
        		for(int i=list.size()-pageSize*(currentPage-1)-1;i>=0;i--){
            		finalList.add(list.get(i));
            	}
        	}
    	}else{
    		for(int i=list.size()-1;i>=0;i--){
        		finalList.add(list.get(i));
        	}
    	}
    	return finalList;*/
		
		page.setT(userId);
		return (List<UserCenterInfo>) dao.findForList("userFollowMapper.findFollowForCenterPage",page);
	}
	
	/**
	 * 查询用户粉丝
	 * @return
	 */
	@Override
	public List<UserCenterInfo> findFansForCenterPage(String userId,Page<String> page) throws Exception{
		page.setT(userId);
		
		return (List<UserCenterInfo>) dao.findForList("userFollowMapper.findFansForCenterPage",page);
	}
	
	/**
	 * 新增关注
	 */
	@Override
	public void saveFollow(String userId,String login) throws Exception{
		UserFollow userFollow = new UserFollow();
		userFollow.setUf_fans(login);
		userFollow.setUf_follow(userId);
		
		dao.save("userFollowMapper.saveFollow", userFollow);
		
		/*将关注加入缓存的方法
		 *
		
		byte[] bs = RedisUtil.get(("follow_"+userId).getBytes());
		
		List<UserCenterInfo> list = (List<UserCenterInfo>) SerializationUtil.deserialize(bs);
    	
    	if(list==null){
    		Page<String> page = new Page<String>();
    		page.setPageSize(0);
    		list = findFollowForCenterPage(userId, page);
    	}else{
    		UserCenterInfo userCenterInfo = new UserCenterInfo();
    		userCenterInfo = (UserCenterInfo) dao.findForObject("usersMapper.findUserCenterInfo", login);
    		list.add(userCenterInfo);
    		RedisUtil.del(("follow_"+userId).getBytes());
			RedisUtil.set(("follow_"+userId).getBytes(),SerializationUtil.serialize(list));
			//259200秒过期 即三天后自动删除缓存
			RedisUtil.expire(("follow_"+userId).getBytes(), (int) (60));
    	}*/
	}
	
	/**
	 * 取消关注
	 */
	@Override
	public void deleteFollow(String userId,String login) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put("userId", userId);
		map.put("login", login);
		
		dao.delete("userFollowMapper.deleteFollow", map);
	}
	
	/**
	 * 是否关注
	 */
	@Override
	public boolean isFollow(String userId,String login) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put("userId", userId);
		map.put("login", login);
		
		int count = (int) dao.findForObject("userFollowMapper.isFollow", map);
		
		if(count>0){
			return true;
		}else{
			return false;
		}
		
	}
	
	
}
