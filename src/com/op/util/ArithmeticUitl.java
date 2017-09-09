package com.op.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：ArithmeticUitl   
* 类描述：   
* 创建人：Win Zhong   
* 创建时间：2015年11月5日 下午4:45:36   
* 修改人：Win Zhong   
* 修改时间：2015年11月5日 下午4:45:36   
* 修改备注：   
* @version    
*
 */
public class ArithmeticUitl {

	/**
	 * 保留两位计算小数
	 * @return
	 */
	public static String getReservedDecimal(double a){
		DecimalFormat    df   = new DecimalFormat("######0.00");   
		return df.format(a); 
	}
	
	/**
	 * 保留两位计算小数四舍五入  
	 * @return
	 */
	public static double getReservedDecimals(double a){
		BigDecimal   bd   =   new   BigDecimal(a);  
		return   bd.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
