package com.op.entity.pointService.project;

import java.io.Serializable;
/** 
 * 项目收藏(projectEnshrine)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-06-27 15:25:43 
 */  
public class ProjectEnshrine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private int pe_id;
 	//项目ID
  	 private int pe_project_id;
 	//用户ID
  	 private String pe_user_id;



	 
    /**
    *id
	* @return
    */ 
	public int getPe_id() {
		return pe_id;
	}
    /**
    *id
	* @param type
    */ 
	public void setPe_id(int pe_id) {
		this.pe_id = pe_id;
	}
    /**
    *项目ID
	* @return
    */ 
	public int getPe_project_id() {
		return pe_project_id;
	}
    /**
    *项目ID
	* @param type
    */ 
	public void setPe_project_id(int pe_project_id) {
		this.pe_project_id = pe_project_id;
	}
    /**
    *用户ID
	* @return
    */ 
	public String getPe_user_id() {
		return pe_user_id;
	}
    /**
    *用户ID
	* @param type
    */ 
	public void setPe_user_id(String pe_user_id) {
		this.pe_user_id = pe_user_id;
	}
	
}
