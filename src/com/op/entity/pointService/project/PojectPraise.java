package com.op.entity.pointService.project;

import java.io.Serializable;
/** 
 * 项目点赞(pojectPraise)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-06-27 15:25:43 
 */  
public class PojectPraise implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private int pp_id;
 	//项目ID
  	 private int pp_project_id;
 	//用户ID
  	 private String pp_user_id;



	 
    /**
    *id
	* @return
    */ 
	public int getPp_id() {
		return pp_id;
	}
    /**
    *id
	* @param type
    */ 
	public void setPp_id(int pp_id) {
		this.pp_id = pp_id;
	}
    /**
    *项目ID
	* @return
    */ 
	public int getPp_project_id() {
		return pp_project_id;
	}
    /**
    *项目ID
	* @param type
    */ 
	public void setPp_project_id(int pp_project_id) {
		this.pp_project_id = pp_project_id;
	}
    /**
    *用户ID
	* @return
    */ 
	public String getPp_user_id() {
		return pp_user_id;
	}
    /**
    *用户ID
	* @param type
    */ 
	public void setPp_user_id(String pp_user_id) {
		this.pp_user_id = pp_user_id;
	}
	
}
