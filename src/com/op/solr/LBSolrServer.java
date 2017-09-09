package com.op.solr;

import org.apache.solr.client.solrj.impl.LBHttpSolrServer;

import com.op.util.PropertyFile;

public class LBSolrServer {
	private static String url= PropertyFile.getPropertiesMap("solr.properties").get("solr.base.url");
	private static String urlBak= PropertyFile.getPropertiesMap("solr.properties").get("solr.base.url.bak");
	//为每个core 建立单独的server连接
	private static LBHttpSolrServer activitySolrServer =null; //活动
	private static LBHttpSolrServer scenicSpotsSolrServer = null; //景点
	private static LBSolrServer solrServer;
	
	public static synchronized LBSolrServer getInstance(){
		if(solrServer==null){
			solrServer = new LBSolrServer();
		}
		return solrServer;
	}
	public LBHttpSolrServer getServer(String core){
		LBHttpSolrServer server = getServerPei(core);
		 
		server.setSoTimeout(5000);
		server.setConnectionTimeout(5000);
/*		server.setDefaultMaxConnectionsPerHost(100);
		server.setMaxTotalConnections(50);
		server.setFollowRedirects(false);
		server.setAllowCompression(true);
		server.setMaxRetries(1);*/ 
		return server;
	}
	/**
	 * 分别为没有core  建立不同的 服务 。。。
	 */
	public LBHttpSolrServer getServerPei(String core){
		try{
			if(core.equals("activity")){//活动
				if(activitySolrServer == null ){
					System.err.println("--------------"+url+core);
					activitySolrServer =  new LBHttpSolrServer(url+core,urlBak+core);//new HttpSolrServer(url+core);
					return activitySolrServer;
				}else{
					return activitySolrServer;
				}
			}else if(core.equals("scenicSpots")){//景点
				if(scenicSpotsSolrServer == null ){
					scenicSpotsSolrServer=  new LBHttpSolrServer(url+core,urlBak+core);//new HttpSolrServer(url+core);
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
