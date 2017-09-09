package com.op.solr;

import org.apache.solr.client.solrj.impl.HttpSolrServer;

import com.op.util.PropertyFile;

public class SolrServer {
	private static String url= PropertyFile.getPropertiesMap("solr.properties").get("solr.base.url");
	//为每个core 建立单独的server连接
	private static HttpSolrServer activitySolrServer =null; //活动
	private static HttpSolrServer consultSpotsSolrServer = null; //活动咨询
	private static HttpSolrServer scenicSpotsSolrServer = null; //景点
	private static HttpSolrServer travelsSolrServer = null; //游记搜索
	private static HttpSolrServer spotSolrServer = null; //景点
	private static SolrServer solrServer;
	
	public static synchronized SolrServer getInstance(){
		if(solrServer==null){
			solrServer=new SolrServer();
		}
		return solrServer;
	}
	public HttpSolrServer getServer(String core){
		HttpSolrServer server = getServerPei(core);
		server.setSoTimeout(5000);
		server.setConnectionTimeout(5000);
		server.setDefaultMaxConnectionsPerHost(100);
		server.setMaxTotalConnections(50);
		server.setFollowRedirects(false);
		server.setAllowCompression(true);
		server.setMaxRetries(1);
		return server;
	}
	/**
	 * 分别为没有core  建立不同的 服务 。。。
	 */
	public HttpSolrServer getServerPei(String core){
		try{
			if(core.equals("activity")){//活动
				if(activitySolrServer == null ){
					System.err.println("--------------"+url+core);
					activitySolrServer = new HttpSolrServer(url+core);
					return activitySolrServer;
				}else{
					return activitySolrServer;
				}
			}else if(core.equals("consult")){//活动咨询
				if(consultSpotsSolrServer == null ){
					consultSpotsSolrServer=new HttpSolrServer(url+core);
					return consultSpotsSolrServer;
				}else{
					return consultSpotsSolrServer;
				}
			} else if(core.equals("jingdian")){//景点
				if(scenicSpotsSolrServer == null ){
					scenicSpotsSolrServer=new HttpSolrServer(url+core);
					return scenicSpotsSolrServer;
				}else{
					return scenicSpotsSolrServer;
				}
			}  else if(core.equals("travels")){//游记
				if(scenicSpotsSolrServer == null ){
					travelsSolrServer=new HttpSolrServer(url+core);
					return travelsSolrServer;
				}else{
					return travelsSolrServer;
				}
			} else if(core.equals("spot")){//景点
				if(scenicSpotsSolrServer == null ){
					scenicSpotsSolrServer=new HttpSolrServer(url+core);
					return scenicSpotsSolrServer;
				}else{
					return scenicSpotsSolrServer;
				}
			} 
		}catch (Exception e) {
			// TODO: handle exception
		}
		return activitySolrServer;
	}
	
}
