package com.op.spot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.op.spot.entity.Project;

@Service("projectService")
public interface ProjectService {
	public List<Project> findProject() throws Exception;
	
}
