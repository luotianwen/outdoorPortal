package com.op.solr;

import org.apache.solr.client.solrj.impl.HttpSolrServer;

public class HttpSolrClient extends HttpSolrServer{
 

	/**
	 * 
	 */
	private static final long serialVersionUID = 8531214808812706733L;

	/*public HttpSolrClient(String baseURL) {
		super(baseURL);
	}*/
	
	public HttpSolrClient(String baseURL,int timeout,int connectionTimeout,int defaultMaxConnectionsPerHost,
			int maxTotal,boolean followRedirects,boolean allowCompression) {
		super(baseURL);
		//读取超时
		this.setSoTimeout(timeout);
		//链接超时时间
		this.setConnectionTimeout(connectionTimeout);
		//单个主机打开的最大连接数
		this.setDefaultMaxConnectionsPerHost(defaultMaxConnectionsPerHost);
		//最大连接数
		this.setMaxTotalConnections(maxTotal);
		//重定向
		this.setFollowRedirects(followRedirects);
		//是否允许压缩
		this.setAllowCompression(allowCompression);
	}
	

}
