package com.op.spot.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.spot.entity.Project;
import com.op.spot.service.ProjectService;

@Service("projectServiceImpl")
public class ProjectServiceImpl implements ProjectService{
	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	@Override
	public List<Project> findProject() throws Exception {
		return (List<Project>) dao.findForList("projectMapper.findProject");
	}
	
}
