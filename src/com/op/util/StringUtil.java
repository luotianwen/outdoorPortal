package com.op.util;
/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：StringUtil   
* 类描述：   字符串相关方法
* 创建人：Win Zhong   
* 创建时间：2015年11月20日 上午11:40:08   
* 修改人：Win Zhong   
* 修改时间：2015年11月20日 上午11:40:08   
* 修改备注：   
* @version    
*
 */
public class StringUtil {

	 
	
	/**
	 * 检测字符串是否不为空(null,"","null")
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s){
		return s!=null && !"".equals(s) && !"null".equals(s);
	}
	
	/**
	 * 检测字符串是否为空(null,"","null")
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s){
		return s==null || "".equals(s) || "null".equals(s);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
