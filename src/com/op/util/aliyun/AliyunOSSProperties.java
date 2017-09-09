package com.op.util.aliyun;

import java.util.Map;

import com.op.util.PropertyFile;
 

/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：AliyunOSSProperties   
* 类描述：   从aliyunoss.properties文件读取所有应用相关的配置信息 
* 创建人：Win Zhong   
* 创建时间：2015年12月19日 上午11:45:47   
* 修改人：Win Zhong   
* 修改时间：2015年12月19日 上午11:45:47   
* 修改备注：   
* @version    
*
 */
public class AliyunOSSProperties {
	/**
	 * Access Key ID
	 */
	public static String ACCESSKEYID ; 
	/**
	 * Access Key Secret
	 */
	public static String ACCESSKEYSECRET ;
	/**
	 * endpoint
	 */
	public static String ENDPOINT ;
	/**
	 * bucket名称
	 */
	public static String BUCKETNAME ;
	
	/**
	 * 图片访问url
	 */
	public static String IMGVISITURL;
	
	/**
	 * 文件访问url
	 */
	public static String FILEVISITURL;
	
	/**
	 * 文件web直传回调验证url
	 */
	public static String CALLBACKURL;
    static {
        Map<String, String> map = PropertyFile.getPropertiesMap("aliyunOSS.properties");

        ACCESSKEYID = map.get("AccessKeyID");
        if (ACCESSKEYID == null) {
            throw new RuntimeException("AccessKeyID property is not configured in aliyunoss.properties!!!");
        }
        ACCESSKEYSECRET = map.get("AccessKeySecret");
        if (ACCESSKEYSECRET == null) {
            throw new RuntimeException("AccessKeySecret property is not configured in aliyunoss.properties!!!");
        }
        ENDPOINT = map.get("Endpoint");
        if (ENDPOINT == null) {
            throw new RuntimeException("Endpoint property is not configured in aliyunoss.properties!!!");
        }
        BUCKETNAME = map.get("BucketName");
        if (BUCKETNAME == null) {
            throw new RuntimeException("BucketName property is not configured in aliyunoss.properties!!!");
        }
        IMGVISITURL = map.get("ImgVisitURL");
        if (IMGVISITURL == null) {
            throw new RuntimeException("ImgVisitURL property is not configured in aliyunoss.properties!!!");
        }
        FILEVISITURL = map.get("FileVisitURL");
        if (FILEVISITURL == null) {
            throw new RuntimeException("FileVisitURL property is not configured in aliyunoss.properties!!!");
        }
        CALLBACKURL = map.get("CallbackUrl");
        if (CALLBACKURL == null) {
            throw new RuntimeException("CallbackUrl property is not configured in aliyunoss.properties!!!");
        }
 

    }
	
	
	
	
	
	
	
	
	
}
