package com.op.spot.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.op.entity.users.Users;
import com.op.plugin.page.Page;
import com.op.spot.entity.Spotcomment;

@Service("spotcommentService")
public interface SpotcommentService {
	/**
	 * 查询所有点评
	 */
	public List<Spotcomment> findAllSpotCommentPage(Page<Map<String,String>> page,String id) throws Exception;
	
	/**
	 * 用户点评
	 */
	public void saveComment(Users users,Spotcomment spotcomment) throws Exception;
	
	
}
