package com.op.spot;

/**
 * 景点门票订单出票状态
 * @author WinZhong
 *
 */
public class OutTicketStatus {
	//（0：等待付款、1：已付款、2：等待出票、3：已出票、4：申请退票、5：部分申请退票、6：退票成功、7：部分退票成功、8：拒绝退票、9：已完成、10：已取消、11：退票中、12：退票失败）
	
	/**
	 * 0：等待付款
	 */
	public static final int STATUS_0 = 0;
	
	/**
	 * 1：已付款
	 */
	public static final int STATUS_1 = 1;
	
	/**
	 * 2：等待出票
	 */
	public static final int STATUS_2 = 2;
	
	/**
	 * 3：已出票
	 */
	public static final int STATUS_3 = 3;
	
	/**
	 * 4：申请退票
	 */
	public static final int STATUS_4 = 4;
	
	/**
	 * 5：部分申请退票
	 */
	public static final int STATUS_5 = 5;
	
	/**
	 * 6：退票成功   （既对接平台退票成功，等待玩嘛退款给用户）
	 */
	public static final int STATUS_6 = 6;
	
	/**
	 * 7：部分退票成功
	 */
	public static final int STATUS_7 = 7;
	
	/**
	 * 8：拒绝退票
	 */
	public static final int STATUS_8 = 8;
	
	/**
	 * 9：已完成  （票以被使用）
	 */
	public static final int STATUS_9 = 9;
	
	/**
	 * 10：已取消
	 */
	public static final int STATUS_10 = 10;
	
	/**
	 * 11：退票中
	 */
	public static final int STATUS_11 = 11;
	
	/**
	 * 12：退票失败
	 */
	public static final int STATUS_12 = 12;
	
	/**
	 * 13：出票失败
	 */
	public static final int STATUS_13 = 13;

}
