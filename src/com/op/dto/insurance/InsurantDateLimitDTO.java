package com.op.dto.insurance;

import java.io.Serializable;

public class InsurantDateLimitDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private String id;
 	//最低保障期限
  	 private int minDeadline;
 	//保障期限
  	 private String deadline;
 	//最长保障期限
  	 private int maxDeadline;
 	//期限单位（1：天；2：年）
  	 private int unit;



	 
    /**
    *id
    */ 
	public String getId() {
		return id;
	}
    /**
    *id
    */ 
	public void setId(String id) {
		this.id = id;
	}
    /**
    *最低保障期限
    */ 
	public void setMinDeadline(int minDeadline) {
		this.minDeadline = minDeadline;
	}
    /**
    *最长保障期限
    */ 
	public void setMaxDeadline(int maxDeadline) {
		this.maxDeadline = maxDeadline;
	}
    /**
    *期限单位（1：天；2：年）
    */ 
	public void setUnit(int unit) {
		this.unit = unit;
	}
	public String getDeadline() {
		if(maxDeadline == 0){
			this.deadline = minDeadline+(unit == 1 ? "天":"年" );
		}else{
			this.deadline = minDeadline+"-"+maxDeadline+(unit == 1 ? "天":"年" );
		}
		
		return deadline;
	} 
	
	
	
}
