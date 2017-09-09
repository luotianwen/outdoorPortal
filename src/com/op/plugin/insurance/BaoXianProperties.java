package com.op.plugin.insurance; 

import java.util.Map;

import com.op.util.PropertyFile;

/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：BaoXianProperties   
* 类描述：   保险配置信息
* 创建人：Win Zhong   
* 创建时间：2016年3月15日 下午2:44:11   
* 修改人：Win Zhong   
* 修改时间：2016年3月15日 下午2:44:11   
* 修改备注：   
* @version    
*
 */
public class BaoXianProperties{
	 
    
    public static Integer partnerId=23994;
    
    public static String channelKey="23994^*#%";

    static {
        Map<String, String> map = PropertyFile.getPropertiesMap("baoxian.properties");

        partnerId = Integer.valueOf(map.get("partnerId").toString());
        channelKey = map.get("channelKey");

    }
    
}
 