package com.op.dto.pointService.insert.fabu;

import java.io.Serializable;

/**
 * 项目适合人群(projectCrowdType)实体类
 * 
 * @author 世峰户外商城
 * @version Revision: 1.00 Date: 2016-06-24 10:49:38
 */
public class ProjectCrowdTypeDTO implements Serializable {

	/**
	 * 
	 */
	static final long serialVersionUID = 1L;

	// id
	String id;
	
	// 项目ID
	String projectId;
	
	// 适合人群ID
	String crowdTypeId;
	

	/**
	 * 
	 * @param projectId	项目ID
	 * @param crowdTypeId	适合人群ID
	 */
	public ProjectCrowdTypeDTO(String projectId, String crowdTypeId) {
		this.projectId = projectId;
		this.crowdTypeId = crowdTypeId;
	}
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getCrowdTypeId() {
		return crowdTypeId;
	}
	public void setCrowdTypeId(String crowdTypeId) {
		this.crowdTypeId = crowdTypeId;
	}
	

}
