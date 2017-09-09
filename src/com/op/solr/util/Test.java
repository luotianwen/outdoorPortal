package com.op.solr.util;

import java.util.List;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Suggestion;
import com.op.solr.SolrServer;

public class Test {

	public static void main(String[] args) throws SolrServerException {//consult
		 HttpSolrServer solrServer = SolrServer.getInstance().getServer("activity");
		 SolrQuery query = new SolrQuery();
		 //请求到suggest中  
         query.set("qt", "/suggest");
         //设置查询类容
         query.set("q", "title_autocomplete:户");  
         query.set("spellcheck.build", "true");  
         //从多少条开始
         query.setStart(0);  
         //显示几条
         query.setRows(10); 
         SpellCheckResponse  spellCheckResponse = solrServer.query(query).getSpellCheckResponse();  
         //获取请求结果
         List<Suggestion> suggestions = spellCheckResponse.getSuggestions();
         System.err.println("**************《共搜索到"+suggestions.size()+"条结果》******************");
         if(suggestions.size() > 0){
        	 System.err.println(suggestions.get(0).getAlternatives());
         }
         
        /* //请求solr搜索服务器
         QueryResponse response = solrServer.query(query);  
         //获取请求结果
         SolrDocumentList solrResultList = response.getResults();  
         System.err.println("**************《共搜索到"+solrResultList.getNumFound()+"条结果》******************");
   	  
     	if(solrResultList.getNumFound()>0){
     		 System.err.println("**************《推荐词："+solrResultList.get(0).get("ac_reply_comment")+"》******************");	
     	}*/
 
	}

	
/*	HttpSolrServer solrServer = SolrServer.getInstance().getServer("consult");
	SolrQuery query = new SolrQuery();
	 //请求到suggest中  
    query.set("qt", "/spell");
    query.set("q", "consult_spell:个人");
	query.set("defType", "edismax");// 加权
	query.set("qf", "consult_spell^20.0");
	query.set("spellcheck", "true");
	query.set("spellcheck.build", "true");// 遇到新的检查词，会自动添加到索引里面
	query.set("spellcheck.count", 1);
    //从多少条开始
    query.setStart(0);  
    //显示几条
    query.setRows(1); 
    //请求solr搜索服务器
    QueryResponse response = solrServer.query(query);  
    //获取请求结果
    SolrDocumentList solrResultList = response.getResults();  
    //获取并设置总条数
    System.err.println("**************《共搜索到"+solrResultList.getNumFound()+"条结果》******************");
	  
	if(solrResultList.getNumFound()>0){
		 System.err.println("**************《推荐词："+solrResultList.get(0).get("ac_reply_comment")+"》******************");	
	}		 */
}
