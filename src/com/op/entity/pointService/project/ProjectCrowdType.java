package com.op.entity.pointService.project;

import java.io.Serializable;

/**
 * 项目适合人群(projectCrowdType)实体类
 * 
 * @author 世峰户外商城
 * @version Revision: 1.00 Date: 2016-06-24 10:49:38
 */
public class ProjectCrowdType implements Serializable {

	/**
	 * 
	 */
	static final long serialVersionUID = 1L;

	// id
	int id;
	// 项目ID
	int projectId;
	// 适合人群ID
	int crowdTypeId;

	/**
	 * id
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 项目ID
	 */
	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	/**
	 * 适合人群ID
	 */
	public int getCrowdTypeId() {
		return crowdTypeId;
	}

	public void setCrowdTypeId(int crowdTypeId) {
		this.crowdTypeId = crowdTypeId;
	}

}
