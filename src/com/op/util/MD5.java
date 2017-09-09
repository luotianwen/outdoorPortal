package com.op.util;

import java.security.MessageDigest;
/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：MD5   
* 类描述：   
* 创建人：Win Zhong   
* 创建时间：2015年11月5日 下午2:54:37   
* 修改人：Win Zhong   
* 修改时间：2015年11月5日 下午2:54:37   
* 修改备注：   
* @version    
*
 */
public class MD5 {

	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return str;
	}
	public static void main(String[] args) {
		System.out.println(md5("31119@qq.com"+"123456"));
		System.out.println(md5(null));
	}
}
