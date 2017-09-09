package com.op.entity.pointService.order;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单识别码(pointServiceUniqueness)实体类
 * 
 * @author Win Zhong
 * @version Revision: 1.00 Date: 2016-07-11 17:14:06
 */
public class PointServiceUniqueness implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// id
	private String psu_id;
	// 订单ID
	private int pso_id;
	// 唯一识别码
	private int psu_uniqueness;
	// 识别码状态(0：待消费，1：已使用)
	private int psu_state;
	// 退款状态(0：未申请退款；10：等待玩嘛确认，20：退款中，30：退款成功，40：退款失败，50：取消退款)
	private int pso_refund_state;
	// 使用时间
	private Date psu_use_time;
	
	/**
	 * id
	 * 
	 * @return
	 */
	public String getPsu_id() {
		return psu_id;
	}

	/**
	 * id
	 * 
	 * @param type
	 */
	public void setPsu_id(String psu_id) {
		this.psu_id = psu_id;
	}

	/**
	 * 订单ID
	 * 
	 * @return
	 */
	public int getPso_id() {
		return pso_id;
	}

	/**
	 * 订单ID
	 * 
	 * @param type
	 */
	public void setPso_id(int pso_id) {
		this.pso_id = pso_id;
	}

	/**
	 * 唯一识别码
	 * 
	 * @return
	 */
	public int getPsu_uniqueness() {
		return psu_uniqueness;
	}

	/**
	 * 唯一识别码
	 * 
	 * @param type
	 */
	public void setPsu_uniqueness(int psu_uniqueness) {
		this.psu_uniqueness = psu_uniqueness;
	}

	/**
	 * 识别码状态(0：待消费，1：已使用)
	 * 
	 * @return
	 */
	public int getPsu_state() {
		return psu_state;
	}

	/**
	 * 识别码状态(0：待消费，1：已使用)
	 * 
	 * @param type
	 */
	public void setPsu_state(int psu_state) {
		this.psu_state = psu_state;
	}

	/**
	 * 退款状态(0：未申请退款；10：等待玩嘛确认，20：退款中，30：退款成功，40：退款失败，50：取消退款)
	 * 
	 * @return
	 */
	public int getPso_refund_state() {
		return pso_refund_state;
	}

	/**
	 * 退款状态(0：未申请退款；10：等待玩嘛确认，20：退款中，30：退款成功，40：退款失败，50：取消退款)
	 * 
	 * @param type
	 */
	public void setPso_refund_state(int pso_refund_state) {
		this.pso_refund_state = pso_refund_state;
	}

	/**
	 * 使用时间
	 * 
	 * @return
	 */
	public Date getPsu_use_time() {
		return psu_use_time;
	}

	/**
	 * 使用时间
	 * 
	 * @return
	 */
	public void setPsu_use_time(Date psu_use_time) {
		this.psu_use_time = psu_use_time;
	}

}
