package com.op.util.aliyun;

import com.aliyun.oss.OSSClient;

/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：AliyunOSSFactory   
* 类描述：   阿里云开放存储服务OSS工厂类
* 创建人：Win Zhong   
* 创建时间：2015年12月19日 上午11:43:42   
* 修改人：Win Zhong   
* 修改时间：2015年12月19日 上午11:43:42   
* 修改备注：   
* @version    
*
 */
public class AliyunOSSFactory {

	
	private static OSSClient client;
	
	/**
	 * OSSClient 单例
	 * @return
	 */
	public static synchronized OSSClient getOSSClient(){
		if(client == null){
			client = new OSSClient(AliyunOSSProperties.ENDPOINT,AliyunOSSProperties.ACCESSKEYID, AliyunOSSProperties.ACCESSKEYSECRET);
		}
		return client;
	}
	
	
	/**
	 * 返回OSSClient
	 * @return
	 */
/*	public static OSSClient getOSSClient() {
		 OSSClient client = new OSSClient(AliyunOSSProperties.ENDPOINT,AliyunOSSProperties.ACCESSKEYID, AliyunOSSProperties.ACCESSKEYSECRET);
		return client;
	}*/
	
}
