package com.op.util;

import org.springframework.util.StringUtils;

/**
 * 转义工具类
 * @author sen
 *
 */
public class HTMLEscape {
	
	/**
	 * html转义
	 * @return
	 */
	public static String htmlEscape(String str){
		if(StringUtils.isEmpty(str)){
			return str;
		}
		//转义&符号
		str = str.replaceAll("&", "&amp;");
		//转义<符号
		str = str.replaceAll("<", "&lt;");
		//转义>符号
		str = str.replaceAll(">", "&gt;");
		//转义玩嘛表情
		str = str.replaceAll("&lt;img class=\"emoji\"","<img class=\"emoji\"");
		str = str.replaceAll("align=\"absmiddle\"/&gt;", "align=\"absmiddle\"/>");
		//转义\n换行
		str = str.replaceAll("\n", "<br>");
		//转义多个空格
		str = str.replaceAll("  ", "&nbsp;&nbsp;");
		return str;
	}
	
	/**
	 * html转义
	 * @return
	 */
	public static String htmlEscapeNoEmoji(String str){
		if(StringUtils.isEmpty(str)){
			return str;
		}
		//转义&符号
		str = str.replaceAll("&", "&amp;");
		//转义<符号
		str = str.replaceAll("<", "&lt;");
		//转义>符号
		str = str.replaceAll(">", "&gt;");
		//转义\n换行
		str = str.replaceAll("\n", "<br>");
		//转义多个空格
		str = str.replaceAll("  ", "&nbsp;&nbsp;");
		return str;
	}
	
	/**
	 * html反转义
	 */
	public static String htmlReverseEscape(String str){
		if(StringUtils.isEmpty(str)){
			return str;
		}
		
		//转义&符号
		str = str.replaceAll("&amp;", "&");
		//转义<符号
		str = str.replaceAll("&lt;", "<");
		//转义>符号
		str = str.replaceAll("&gt;", ">");
		//转义\n换行
		str = str.replaceAll("<br>", "");
		//转义多个空格
		str = str.replaceAll("&nbsp;", " ");
		return str;
	}
	
}
