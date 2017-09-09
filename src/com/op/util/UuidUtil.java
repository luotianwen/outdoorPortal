package com.op.util;

import java.util.UUID;

/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：UuidUtil   
* 类描述：   生成32位的uuid
* 创建人：Win Zhong   
* 创建时间：2015年11月5日 下午4:43:29   
* 修改人：Win Zhong   
* 修改时间：2015年11月5日 下午4:43:29   
* 修改备注：   
* @version    
*
 */
public class UuidUtil {
 
    /**
     * 返回32位的uuid
     * @return
     */
	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
	
	public static void main(String[] args) {
		for(int i = 0;i<10;i++){
			System.out.println(get32UUID());
		}
			
	}
}

