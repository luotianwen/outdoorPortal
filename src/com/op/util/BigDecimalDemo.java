package com.op.util;

import java.math.BigDecimal;

public class BigDecimalDemo {

	public static void main(String[] args) {
		// BigDecimal的compareTo方法来进行比较 返回的结果是int类型,-1表示小于,0是等于,1是大于.
		 BigDecimal b1 = new BigDecimal(20);  
		    BigDecimal b2 = new BigDecimal("21"); 
		    // 进行加法运算
		   System.out.println(b1.add(b2).doubleValue()); 
		    // 进行减法运算
		   System.out.println(b1.subtract(b2).doubleValue()); 
		    // 进行乘法运算
		   System.out.println(b1.multiply(b2).doubleValue()); 
		    // 进行除法运算
		   System.out.println(b1.divide(b2,1,BigDecimal.ROUND_HALF_UP).doubleValue());
		   System.out.println(b1.divide(b2,2,BigDecimal.ROUND_HALF_UP).doubleValue());
		   System.out.println(b1.divide(b2,3,BigDecimal.ROUND_HALF_UP).doubleValue());  
		    //  进行四舍五入
		   System.out.println(b1.divide(b2,1,BigDecimal.ROUND_HALF_UP).doubleValue()); 
	}

}
