package com.op.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import com.op.entity.alipay.Alipay;
import com.op.plugin.page.Page;

public class UrlParamsFormatUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.remove("currentPage");
		int[] a = new int[]{1,2};
		String[] c = new String[]{"啊啊","订单"};
		map.put("k", "bb");
		map.put("p", "1");
		map.put("a", a);
		map.put("c", c);
		
		System.out.println(StringUtils.isEmpty(getUrlParamsByMap(map)));
		Alipay Alipay = new Alipay();
		Page<Alipay> p = new Page<Alipay>();
		p.setT(Alipay);
		System.out.println(p.getT().getClass());
		
	}

    /**
     * 把request里面的参数转化成map
     *
     * @param request
     * @return String
     * @author WinZhong
     */
    public static String getUrlParamsByRequest(HttpServletRequest request){
        Map<String, Object> keyMap = new HashMap<String, Object>();
        Enumeration<String> enu = request.getParameterNames();
        String[] item ;
        while (enu.hasMoreElements()) {
            String paramname = enu.nextElement();
            item = request.getParameterValues(paramname);
            if(item.length == 1){
                //String paramvalue = request.getParameter(paramname);
                keyMap.put(paramname, item[0]);
            }else{
            	keyMap.put(paramname, item);
            }
        }
        //去掉分页参数
        keyMap.remove("page");
        return getUrlParamsByMap(keyMap);
    }
	
	/** 
	 * 将map转换成url 
	 * @param map 
	 * @return 
	 */  
	public static String getUrlParamsByMap(Map<String, Object> map) {
	    if(map == null) {  
	        return "";  
	    }  
	    StringBuffer sb = new StringBuffer();  
	    for(Map.Entry<String, Object> entry : map.entrySet()){
	    	Object value = entry.getValue();
	    	String key = entry.getKey();
	    	//判断是否为String数组类型  
            if (value instanceof String[]){
            	String[] obj = (String[])value;
            	for(String i :obj){
            		sb.append(key + "=" + i);
        	        sb.append("&");
            	}
            }else if(value instanceof int[]){
            	int[] obj = (int[])value;
            	for(int i :obj){
            		sb.append(key + "=" + i);
        	        sb.append("&");
            	}
            }else{
    	        sb.append(key + "=" + value);
    	        sb.append("&");
            }

	    }
	    String s = sb.toString();
	    if(s.endsWith("&")){
	        s = org.apache.commons.lang.StringUtils.substringBeforeLast(s, "&");
	    }
	    return s;
	}  
	/** 
	 * 将String转换成url 
	 * @param map 
	 * @return 
	 */  
	public static String getUrlParamsByString(String str) {  
	    if (str == null) {  
	        return "";  
	    }  
	     
	    return str;  
	} 
}
