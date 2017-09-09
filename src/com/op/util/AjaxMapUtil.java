package com.op.util;

import java.util.Map;

/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：AjaxMapUtil   
* 类描述：   Ajax返回Map结构信息工具类
* 创建人：Win Zhong   
* 创建时间：2016年3月11日 下午4:42:22   
* 修改人：Win Zhong   
* 修改时间：2016年3月11日 下午4:42:22   
* 修改备注：   
* @version    
*
 */
public class AjaxMapUtil {

	
	/**
	 * 方法描述：Ajax返回成功提示
	 * 返回类型：void
	 * @param map
	 */
	public static void successMap(Map<String,Object> map){
		map.clear();
		map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
	}
	
	/**
	 * 方法描述：返回失败提示，只定义错误状态，前台展示错误信息
	 * 返回类型：void
	 * @param map
	 * @param state
	 */
	public static void errorMap(Map<String,Object> map){
		map.clear();
		map.put(Const.RESPONSE_STATE, 500);
		
	}
	
	/**
	 * 方法描述：返回失败提示，设置错误状态和错误信息
	 * 返回类型：void
	 * @param map
	 * @param state
	 * @param error
	 */
	public static void errorInfoMap(Map<String,Object> map,String error){
		map.clear();
		map.put(Const.ERROR_INFO, error);
		map.put(Const.RESPONSE_STATE, 500);
	}
	
	
}
