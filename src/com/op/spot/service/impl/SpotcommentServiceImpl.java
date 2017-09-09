package com.op.spot.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.op.dao.BaseDao;
import com.op.entity.users.Users;
import com.op.plugin.page.Page;
import com.op.spot.entity.CommentPho;
import com.op.spot.entity.CommentProject;
import com.op.spot.entity.Project;
import com.op.spot.entity.Spotcomment;
import com.op.spot.service.SpotcommentService;
import com.op.util.HTMLEscape;

@Service("spotcommentServiceImpl")
public class SpotcommentServiceImpl implements SpotcommentService{
	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	/**
	 * 查询所有点评
	 */
	public List<Spotcomment> findAllSpotCommentPage(Page<Map<String,String>> page,String id) throws Exception{
		Map<String,String> pageMap = new HashMap<String,String>();
		pageMap.put("id", id);
		page.setT(pageMap);
		
		return (List<Spotcomment>) dao.findForList("spotcommentMapper.findAllSpotCommentPage", page);
	}
	
	
	/**
	 * 用户点评
	 */
	public void saveComment(Users users,Spotcomment spotcomment) throws Exception{
		spotcomment.setUserid(Integer.valueOf(users.getuId()));
		spotcomment.setUsername(users.getuName());
		spotcomment.setUserpho(users.getuHeadImg());
		
		spotcomment.setContent(HTMLEscape.htmlEscape(spotcomment.getContent()));
		
		dao.save("spotcommentMapper.saveComment", spotcomment);
		
		List<CommentProject> commentProject = spotcomment.getCommentProject();
		if(commentProject!=null){
			for(int i=0;i<commentProject.size();i++){
				if(commentProject.get(i)!=null){
					commentProject.get(i).setCid(spotcomment.getId());
					dao.save("commentProjectMapper.saveCommentProject", commentProject.get(i));
				}
			}
		}
		
		List<CommentPho> commentPhos = spotcomment.getCommentPho();
		String[] phos = null;
		if(commentPhos!=null&&commentPhos.size()>0){
			phos = commentPhos.get(0).getPho().split(",");
		}
		
		if(phos!=null){
			for(int i=0;i<phos.length;i++){
				if(phos[i]!=null){
					CommentPho commentPho = new CommentPho();
					commentPho.setCid(spotcomment.getId());
					commentPho.setPho(phos[i]);
					dao.save("commentPhoMapper.saveCommentPho", commentPho);
				}
			}
		}
		
		
	}
	
}
