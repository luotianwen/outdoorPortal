package com.op.entity.pointService.order;

import java.io.Serializable;
/** 
 * 退款原因(pointServiceRefundReason)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-07-15 14:58:25 
 */  
public class PointServiceRefundReason implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private String psrr_id;
 	//退款原因
  	 private String psrr_refund_reason;
 	//申请退款ID
  	 private String psoar_id;

	 
    /**
    *id
	* @return
    */ 
	public String getPsrr_id() {
		return psrr_id;
	}
    /**
    *id
	* @param type
    */ 
	public void setPsrr_id(String psrr_id) {
		this.psrr_id = psrr_id;
	}
    /**
    *退款原因
	* @return
    */ 
	public String getPsrr_refund_reason() {
		return psrr_refund_reason;
	}
    /**
    *退款原因
	* @param type
    */ 
	public void setPsrr_refund_reason(String psrr_refund_reason) {
		this.psrr_refund_reason = psrr_refund_reason;
	}
    /**
    *申请退款ID
	* @return
    */ 
	public String getPsoar_id() {
		return psoar_id;
	}
    /**
    *申请退款ID
	* @param type
    */ 
	public void setPsoar_id(String psoar_id) {
		this.psoar_id = psoar_id;
	}
	
}
