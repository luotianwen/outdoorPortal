package com.op.service.dialog.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;



import com.op.dao.BaseDao; 
import com.op.entity.dialog.Dialog;
import com.op.service.dialog.DialogService;
import com.op.util.SerializationUtil;
import  com.op.util.jedis.RedisUtil;

/** 
 * 对话表(Dialog)接口实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-03-12 13:29:51 
 */  
@Service("dialogServiceImpl")
public class DialogServiceImpl implements DialogService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	/**
     * 删除对话(连同对话内容一起删除)
     * @param dialogId 对话表ID
     */
	@Override
	public void deleteDialog(String dialogId) throws Exception {
		//删除对话所有聊天内容
		dao.delete("dialogContentMapper.deleteDialogContentByDialogId", dialogId);
		//删除对话
		dao.delete("DialogMapper.deleteDialog", dialogId);
	}
	
	/**
     * 查询用户所有对话
     * @param addresser 发信人ID
     */
    @Override
	public List<Dialog> findDialogByUserId (String addresser,String id,String count) throws Exception{
    	Map<String,String> map = new HashMap<String,String>();
    	//发信人
    	map.put("addresser", addresser);
    	map.put("id", id);
    	map.put("count", count);
    	
//    	Page<Map<String,String>> page = new Page<Map<String,String>>();
//    	page.setPageSize(10);
//    	page.setT(map);
    	
    	List<Dialog> list = new ArrayList<Dialog>();
    	list = (List<Dialog>) dao.findForList("DialogMapper.findDialogByUserId",map);
    	

		byte[] bs = RedisUtil.get(("DialogCount_"+addresser).getBytes());
		map = (Map<String,String>) SerializationUtil.deserialize(bs);
    	for(int i=0;i<list.size();i++){
    		if(!StringUtils.isEmpty(map.get(list.get(i).getId()))){
    			list.get(i).setCount(map.get(list.get(i).getId()));
    		}else{
    			list.get(i).setCount("0");
    		}
    	}
    	
    	return list;
    }
    
    /**
     * 判断用户之间是否有对话
     * @param addresser 发信人ID
     * @param addressee 接收人ID
     * @return
     * @throws Exception
     */
    @Override
	public List<Dialog> findDialog (String addresser,String addressee) throws Exception{
    	Map<String,String> map = new HashMap<String,String>();
    	//发信人
    	map.put("addresser", addresser);
    	//接收人
    	map.put("addressee", addressee);
    	
    	List<Dialog> list = new ArrayList<Dialog>();
    	list = (List<Dialog>) dao.findForList("DialogMapper.findDialog",map);
    	

		byte[] bs = RedisUtil.get(("DialogCount_"+addresser).getBytes());
		map = (Map<String,String>) SerializationUtil.deserialize(bs);
    	for(int i=0;i<list.size();i++){
    		if(StringUtils.isEmpty(map.get(list.get(i).getId()))){
    			list.get(i).setCount(map.get(list.get(i).getId()));
    		}
    	}
    	
    	return list;
    }
    
    /**
     * 关闭对话框
     */
    @Override
	public void deleteDialog(String id,String userId) throws Exception{
    	Map<String,String> map = new HashMap<String,String>();
    	
    	map.put("dialogId", id);
    	map.put("userId", userId);
    	
    	dao.update("DialogMapper.deleteDialogByCreate", map);
    	dao.update("DialogMapper.deleteDialogByFriend", map);
		dao.update("DialogContentMapper.updateDialogContent", map);
		

		byte[] bs = RedisUtil.get(("DialogCount_"+userId).getBytes());
		map = (Map<String,String>) SerializationUtil.deserialize(bs);
		
		if(!StringUtils.isEmpty(map.get(id))){
			map.put("dialogAll", Integer.valueOf(map.get("dialogAll"))-Integer.valueOf(map.get(id))+"");
			map.remove(id);
			RedisUtil.set(("DialogCount_"+userId).getBytes(),SerializationUtil.serialize(map));
			//43200秒过期 即半天后自动删除缓存
			RedisUtil.expire(("DialogCount_"+userId).getBytes(), (int) (0.5*24*60*60));
		}
		
    }
    
    
}
