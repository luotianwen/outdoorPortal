package com.op.util;

/***
 * 项目名：outdoorPortal 类描述：定量 创建人：Flame 创建时间： 2015-12-7 上午11:59:13
 * 最后修改时间：2015-12-7上午11:59:13
 */
public class Const {

	/***
	 * AJAX返回状态
	 */
	public static final String RESPONSE_STATE = "RESPONSE_STATE";
	
	/**
	 * AJAX返回数据
	 */
	public static final String RESPONSE_DATA = "RESPONSE_DATA";
	
	/**
	 * 返回成功状态
	 */
	public static final String RESPONSE_SUCCESS = "200";
	
	/**
	 * 返回错误信息key
	 */
	public static final String ERROR_INFO = "ERROR_INFO";
	
	/**
	 * 返回错误信息val
	 */
	public static final String ERROR_INFO_STR = "操作异常，请稍后尝试或者联系客服，给您带来的不便敬请谅解!";
	
	/**
	 * 回调函数
	 */
	public static final String FN = "FN";
	
	/**
	 * 返回成功信息
	 */
	public static final String SUCCESS_INFO = "SUCCESS_INFO";
	
	/**
	 * Session USER
	 */
	public static final String SESSION_USER = "SESSION_USER";
	
	/**
	 * Session USER ID
	 */
	public static final String SESSION_USER_ID = "SESSION_USER_ID";
	
	/**
	 * Websocket 未登录用户标识
	 */
	public static final String NO_LOGIN = "NO_LOGIN";
	
	/**
	 * 修改手机号码对象
	 */
	public static final String UPDATE_PHONE_OBJ = "UPDATE_PHONE_OBJ";
	
	/**
	 * 修改绑定邮箱对象
	 */
	public static final String UPDATE_EMAIL_OBJ = "UPDATE_EMAIL_OBJ";
	
	/**
	 * 修改手机号码对象
	 */
	public static final String UPDATE_PASSWORD_OBJ = "UPDATE_PASSWORD_OBJ";
	
	/**
	 * 注册手机号码对象
	 */
	public static final String REGISTER_PHONE_OBJ = "REGISTER_PHONE_OBJ";
	
	/**
	 * 绑定邮箱验证手机号
	 */
	public static final String EMAIL_PHONE_OBJ = "EMAIL_PHONE_OBJ";
	
	/**
	 * 找回密码验证手机号
	 */
	public static final String FORGET_PHONE_OBJ = "FORGET_PHONE_OBJ";
	
	/**
	 * 绑定手机号
	 */
	public static final String BINDING_PHONE = "BINDING_PHONE";
	
	/**
	 * 验证码
	 */
	public static final String YZM = "YZM";
	
	/**
	 * 注册手机号
	 */
	public static final String REGIS_PHONE = "REGIS_PHONE";
	
	/**
	 * 发送验证码时间
	 */
	public static final String POST_YZM_TIME = "POST_YZM_TIME";
	
	/**
	 * 登录IP
	 */
	public static final String LOGIN_IP = "LOGIN_IP";
	
	/**
	 * 登录验证码
	 */
	public static final String LOGIN_CAPTCHA_TOKEN = "LOGIN_CAPTCHA_TOKEN";
	
	/**
	 * 短信验证码发送间隔时间
	 */
	public static final Integer POST_YZM_INTERVAL_TIME = 60;
	
	/**
	 * session错误登录次数(保留)
	 */
	public static final String SESSION_LOGIN_ERROR_NUM = "SESSION_LOGIN_ERROR_NUM";
	
	/**
	 * SAVE_URL
	 */
	public static final String SAVE_URL = "SAVE_URL";

	/**
	 * 商家支付业务类型 类型一：活动报名支付
	 */
	public static final String PAY_ACTIVE = "PAY_ACTIVE";
	
	/**
	 * 短信模板
	 */
	public static final String EMAY_TEMPLATE = "EMAY_TEMPLATE";
	
	/**
	 * 活动详情模板
	 */
	public static final String ACTIVITY_DETAIL_TEMPLATE_ = "ACTIVITY_DETAIL_TEMPLATE_";

	/**
	 * 活动发布完成度
	 */
	public static final String ACTIVITY_RELEASE_FINISH_NUM = "ACTIVITY_RELEASE_FINISH_NUM";

	/**
	 * 活动ID
	 */
	public static final String ACTIVITY_ID = "ACTIVITY_ID";

	/**
	 * 线路ID
	 */
	public static final String LINE_ID = "LINE_ID";

	/**
	 * 发布活动添加数据类型
	 */
	public static final String HANDLE_TYPE = "HANDLE_TYPE";
	
	/**
	 * 订单报名类型
	 */
	public static final String ACTIVESIGNUP_STATE = "ACTIVESIGNUP_STATE";
	
	/**
	 * 返回操作数据数量
	 */
	public static final String DATA_NUM = "DATA_NUM";
	
	/**
	 * 
	 */
	public static final String TRAVELSHOTDATAKEY = "travelsHotData_";
	
	/**
	 * 登录类型QQ
	 */
	public static final String LOGIN_TYPE_QQ = "QQ";
	
	/**
	 * 登录类型微信
	 */
	public static final String LOGIN_TYPE_WEIXIN = "weixin";
	
	/**
	 * 联合登录标识
	 */
	public static final String JOIN_LOGIN = "JOIN_LOGIN";
	
	/**
	 * 地点服务一级类型
	 */
	public static final String POINT_SERVICE_TYPES = "POINT_SERVICE_TYPES";
	
	/**
	 * 地点服务适合人群
	 */
	public static final String POINT_SERVICE_CROWD = "POINT_SERVICE_CROWD";
	
	/**
	 * 发布项目检验通过标识
	 */
	public static final String REPLY_PROJECT_CHECK_AUTH_SUCCESS = "REPLY_PROJECT_CHECK_AUTH_SUCCESS";
	
	/**
	 * 景点类型自动更新时间
	 */
	public static final int SPOT_TYPE_UPDATE_TIME = 1*24*60*60;
	/**
	 * 景点详情缓存id
	 */
	public static final String SPOT_ID = "SPOT_%s_ID";
	/**
	 * 景点详情缓存自动更新时间
	 */
	public static final int SPOT_ID_UPDATE_TIME = 7*24*60*60;
	/**
	 * 检查景点出票状态订单队列
	 */
	public static final String CHECK_TICKET_QUEUE = "CHECK_TICKET_QUEUE";

}
