package com.op.dto.activity.comment;

import java.io.Serializable;

import com.op.plugin.page.Page;


/**
 * =============================================================
 * 项目名：outdoorPortal
 * 类描述：异步加载活动评价信息DTO
 * 创建人：Yan
 * 创建时间： 2016-3-3
 * modification list：
 * =============================================================
 */
public class LoadEvaluateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 最新评价分页数据
	Page<String> nE;
	
	// 最热评价分页数据
	Page<String> hE;
	
	// 该活动的满意度(默认100%)
	double satisfaction;
	
	// 参与评价的人数
	int evaluateNum;
	
	// 活动ID
	int aId;
	
	// 查询最新评价标识
	boolean findNe=false;
	
	// 查询最热评价标识
	boolean findHe=false;
	
	
	/*--------------------------------------------	get	&&	set	-----------------------------------*/
	
	

	public boolean isFindNe() {
		return findNe;
	}

	public void setFindNe(boolean findNe) {
		this.findNe = findNe;
	}

	public boolean isFindHe() {
		return findHe;
	}

	public void setFindHe(boolean findHe) {
		this.findHe = findHe;
	}


	public Page<String> getnE() {
		return nE;
	}

	public void setnE(Page<String> nE) {
		this.nE = nE;
	}

	public Page<String> gethE() {
		return hE;
	}

	public void sethE(Page<String> hE) {
		this.hE = hE;
	}

	public double getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(double satisfaction) {
		this.satisfaction = satisfaction;
	}

	public int getEvaluateNum() {
		return evaluateNum;
	}

	public void setEvaluateNum(int evaluateNum) {
		this.evaluateNum = evaluateNum;
	}

	public int getaId() {
		return aId;
	}

	public void setaId(int aId) {
		this.aId = aId;
	}
	
}
