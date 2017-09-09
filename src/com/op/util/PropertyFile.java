package com.op.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：PropertyFile   
* 类描述：   读取配置文件中的属性
* 创建人：Win Zhong   
* 创建时间：2015年11月26日 下午5:53:42   
* 修改人：Win Zhong   
* 修改时间：2015年11月26日 下午5:53:42   
* 修改备注：   
* @version    
*
 */
public class PropertyFile {

	
	
	 

	/**
	 * 构造方法中对传入的文件使用classloader获取输入流
	 * @param fileName
	 */
 

	public static Map<String, String> getPropertiesMap(String fileName) {
		InputStream in;
		if (fileName == null) {
			throw new RuntimeException("property file's name is null");
		}
		try {
			 in = PropertyFile.class.getClassLoader().getResourceAsStream(fileName);
		} catch (Exception e) {
			throw new RuntimeException("FileNotFoundException: " + fileName + " \r\n" + e);
		}
		Map<String, String> map = new HashMap<String, String>();
		String lineStr;
		String key = null;
		String value = null;
		try {
			while (true) {
				lineStr = readLine(in);
				if (lineStr == null) {
					break;
				}
				lineStr = lineStr.trim();
				if (!lineStr.isEmpty()) {
					if (!lineStr.startsWith("#")) {
						String[] strList = lineStr.split("=", 2);
						if (strList.length < 2) {
							throw new RuntimeException("properties file's content is not corect!!!");
						}
						key = strList[0].trim();
						value = strList[1].trim();
						map.put(key, value);
					}
				}
			}
		} catch (IOException e) {
			throw new RuntimeException("IOException : " + e);
		}
		return map;
	}

	private static String readLine(InputStream in) throws IOException {
		StringBuffer input = new StringBuffer();
		int c = -1;
		boolean eol = false;

		while (!eol) {
			switch (c = in.read()) {
			case -1:
			case '\n':
				eol = true;
				break;
			case '\r':
				eol = true;
				break;
			default:
				input.append((char) c);
				break;
			}
		}

		if ((c == -1) && (input.length() == 0)) {
			return null;
		}
		return input.toString();
	}
	
	
	
	
	
	
	
	
	
	
}
