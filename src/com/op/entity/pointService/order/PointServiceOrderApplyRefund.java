package com.op.entity.pointService.order;

import java.io.Serializable;
import java.util.Date;
/** 
 * 申请退款(pointServiceOrderApplyRefund)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-07-15 14:58:25 
 */  
public class PointServiceOrderApplyRefund implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private String psoar_id;
 	//订单ID
  	 private String pso_id;
 	//识别码ID
  	 private String psu_id;
 	//申请退款状态(10：等待玩嘛确认，20：退款中，30：退款成功，40：退款失败，50：取消退款)
  	 private int psoar_state;
 	//退款时间
  	 private Date psoar_refund_time;
 	//申请退款时间
  	 private Date psoar_refund_apply_time;
 	//退款到账时间
  	 private Date psoar_refund_success_time;
  	 //退款数量
  	 private int psoar_refund_number;
  	 //取消退款时间
  	 private Date psoar_cancel_refund_time;

	 
    /**
    *id
	* @return
    */ 
	public String getPsoar_id() {
		return psoar_id;
	}
    /**
    *id
	* @param type
    */ 
	public void setPsoar_id(String psoar_id) {
		this.psoar_id = psoar_id;
	}
    /**
    *订单ID
	* @return
    */ 
	public String getPso_id() {
		return pso_id;
	}
    /**
    *订单ID
	* @param type
    */ 
	public void setPso_id(String pso_id) {
		this.pso_id = pso_id;
	}
    /**
    *识别码ID
	* @return
    */ 
	public String getPsu_id() {
		return psu_id;
	}
    /**
    *识别码ID
	* @param type
    */ 
	public void setPsu_id(String psu_id) {
		this.psu_id = psu_id;
	}
    /**
    *申请退款状态(10：等待玩嘛确认，20：退款中，30：退款成功，40：退款失败，50：取消退款)
	* @return
    */ 
	public int getPsoar_state() {
		return psoar_state;
	}
    /**
    *申请退款状态(10：等待玩嘛确认，20：退款中，30：退款成功，40：退款失败，50：取消退款)
	* @param type
    */ 
	public void setPsoar_state(int psoar_state) {
		this.psoar_state = psoar_state;
	}
    /**
    *退款时间
	* @return
    */ 
	public Date getPsoar_refund_time() {
		return psoar_refund_time;
	}
    /**
    *退款时间
	* @param type
    */ 
	public void setPsoar_refund_time(Date psoar_refund_time) {
		this.psoar_refund_time = psoar_refund_time;
	}
    /**
    *申请退款时间
	* @return
    */ 
	public Date getPsoar_refund_apply_time() {
		return psoar_refund_apply_time;
	}
    /**
    *申请退款时间
	* @param type
    */ 
	public void setPsoar_refund_apply_time(Date psoar_refund_apply_time) {
		this.psoar_refund_apply_time = psoar_refund_apply_time;
	}
    /**
    *退款到账时间
	* @return
    */ 
	public Date getPsoar_refund_success_time() {
		return psoar_refund_success_time;
	}
    /**
    *退款到账时间
	* @param type
    */ 
	public void setPsoar_refund_success_time(Date psoar_refund_success_time) {
		this.psoar_refund_success_time = psoar_refund_success_time;
	}
	/**
	 * 退款数量
	 * @return
	 */
	public int getPsoar_refund_number() {
		return psoar_refund_number;
	}
	/**
	 * 退款数量
	 * @return
	 */
	public void setPsoar_refund_number(int psoar_refund_number) {
		this.psoar_refund_number = psoar_refund_number;
	}
	/**
	 * 取消退款时间
	 * @return
	 */
	public Date getPsoar_cancel_refund_time() {
		return psoar_cancel_refund_time;
	}
	/**
	 * 取消退款时间
	 * @return
	 */
	public void setPsoar_cancel_refund_time(Date psoar_cancel_refund_time) {
		this.psoar_cancel_refund_time = psoar_cancel_refund_time;
	}
	
}
