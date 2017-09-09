package com.op.service.recentVisit.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;



import com.op.dao.BaseDao;
import com.op.dto.usercenter.usercenter.UserCenterInfo;
import com.op.entity.recentVisit.RecentVisit;
import com.op.plugin.page.Page;
import com.op.service.recentVisit.RecentVisitService;


@Service("recentVisitServiceImpl")
public class RecentVisitServiceImpl implements RecentVisitService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	/**
	 * 查询最近访问用户
	 * @return
	 */
	@Override
	public List<UserCenterInfo> findRecentVisitForCenterPage(String userId,Page<String> page) throws Exception{
/*
		
		byte[] bs = RedisUtil.get(("recentVisit_"+userId).getBytes());
		
		List<UserCenterInfo> list = (List<UserCenterInfo>) SerializationUtil.deserialize(bs);
    	
    	if(list==null){
    		//查询所有最近访问用户
    		list = (List<UserCenterInfo>) dao.findForList("recentVisitMapper.findRecentVisitForCenter",userId);
			
			RedisUtil.del(("recentVisit_"+userId).getBytes());
			RedisUtil.set(("recentVisit_"+userId).getBytes(),SerializationUtil.serialize(list));
			//259200秒过期 即三天后自动删除缓存
			RedisUtil.expire(("recentVisit_"+userId).getBytes(), (int) (5));
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
		return (List<UserCenterInfo>) dao.findForList("recentVisitMapper.findRecentVisitForCenterPage",page);
		
		
	}
	
	/**
	 * 新增最近访问用户
	 */
	@Override
	public void saveRecentVisit(String userId,String login) throws Exception{
		RecentVisit recentVisit = new RecentVisit();
		recentVisit.setRv_user(userId);
		recentVisit.setRv_visit(login);
		
		int count = (int) dao.update("recentVisitMapper.updateRecentVisit", recentVisit);
		if(count==0){
			dao.save("recentVisitMapper.saveRecentVisit", recentVisit);
		}
		//修改历史访客数量
		dao.update("UsersMapper.updateHistoryVisit", userId);
		
//		//删除最近访问用户缓存数据
//
//		RedisUtil.del(("recentVisit_"+userId).getBytes());
	}
	
}
