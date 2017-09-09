package com.op.solr.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.op.dto.activity.consultation.ResultConsultationDTO;
import com.op.dto.activity.consultation.SearchConsultationDTO;
import com.op.solr.HttpSolrClient;
import com.op.solr.SolrPage;
import com.op.solr.SolrServer;
import com.op.util.Tools;
/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：ActivityConsultSearchService   
* 类描述：   活动咨询查询
* 创建人：Win Zhong   
* 创建时间：2016年3月7日 下午2:38:38   
* 修改人：Win Zhong   
* 修改时间：2016年3月7日 下午2:38:38   
* 修改备注：   
* @version    
*
 */
@Service("activityConsultSearchService")
public class ActivityConsultSearchService {
	
	@Resource(name = "consultSolrServer")
	private HttpSolrClient consultSolrServer;
	
	Logger log = Logger.getLogger(this.getClass());
	//活动咨询搜索返回字段
	@Value("#{properties['activityConsultSearchReturnField']}")
	private String activityConsultSearchReturnField;
	
	 public SolrPage getConsultSearchResults(SearchConsultationDTO search,SolrPage page) throws SolrServerException {
		 //HttpSolrServer consultSolrServer = SolrServer.getInstance().getServer("consult");
		 SolrQuery query = new SolrQuery();
		 //请求到select中  
         query.set("qt", "/select");
         //搜索关键字
         query.setQuery("search:"+search.getKeyword());  
         //活动类型
         query.addFilterQuery("ac_active_id:"+search.getActiveId());
         System.err.println("**************《开始搜索，关键字："+search.getKeyword()+"\t活动ID："+search.getActiveId()+"》******************");
         //指定文档结果中应返回的 Field 集
         query.set("fl", activityConsultSearchReturnField);
         // 加权
     	 query.set("defType", "edismax");
    	 query.set("qf", "ac_comment^20.0");
         //从多少条开始
         query.setStart((page.getPageNow()-1)*page.getPageSize());  
         //显示几条
         query.setRows(page.getPageSize()); 
         if(!StringUtils.isEmpty(search.getType())){
             //设置排序字段
             switch (search.getType()) {
    			case "NEW":
    				//按照回复时间排序
    				query.set("sort", "ac_reply_time desc");
    				break;
    			case "PRAISES":
    				//按照点赞排序
    				query.set("sort", "ac_praises desc");
    				break;
             }
         }
         // 开启高亮组件  
         query.setHighlight(true); 
         // 字段开启了高亮
         //query.addHighlightField("title"); 
         // 高亮字段
         query.addHighlightField("ac_comment,ac_reply_comment");
         //启用多字段高亮
         query.set("hl.highlightMultiTerm","true");
         //让高亮结果集中 多值字段返回 所有值
         //query.set("hl.preserveMulti","true");
         // 以下两个方法主要是在高亮的关键字前后加上html代码 
         query.setHighlightSimplePre("<font color='red'>"); 
         query.setHighlightSimplePost("</font>");
         //请求solr搜索服务器
         QueryResponse response = consultSolrServer.query(query);  
         //获取请求结果
         SolrDocumentList solrResultList = response.getResults();  
         System.err.println("**************《共搜索到"+solrResultList.getNumFound()+"条结果》******************");
         //获取所有高亮的字段  
         Map<String,Map<String,List<String>>> highlightMap = response.getHighlighting();
         List<Object> resultList = new ArrayList<Object>();
         for(int i=0,len=solrResultList.size();i<len;i++){  
        	 SolrDocument doc = solrResultList.get(i);
        	 //System.out.println(solrResultList.get(i).getFieldValue("id"));
	         //获取高亮内容
             List<String> highlightList = highlightMap.get(doc.getFieldValue("id")).get("ac_comment");  
             if(highlightList!=null && highlightList.size()>0){ 
            	 //赋值高亮字段
            	 doc.put("ac_comment", highlightList.get(0));
            	//log.info(highlightList.get(0));  
             }
	         //获取高亮内容
             highlightList = highlightMap.get(doc.getFieldValue("id")).get("ac_reply_comment");  
             if(highlightList!=null && highlightList.size()>0){ 
            	 //赋值高亮字段
            	 doc.put("ac_reply_comment", highlightList.get(0));
            	 //System.out.println(highlightList.get(0));  
             } 
             ResultConsultationDTO consultation = new ResultConsultationDTO();
	         Tools.transMap2Bean2(doc, consultation);
	         resultList.add(consultation);
	     }
         //获取并设置总条数 及查询结果
         page.setQueryResult((int) solrResultList.getNumFound(),resultList);  
	     return page;
	}
	  
	public static void main(String[] args) throws SolrServerException {
		 HttpSolrServer consultSolrServer = SolrServer.getInstance().getServer("consult");
		 	SolrQuery query = new SolrQuery();
		 	//请求到select中  
	         query.set("qt", "/select");
	         //搜索关键字
	         query.setQuery("search:行*");  
	         //活动类型
	         query.addFilterQuery("ac_active_id:10736");
	         //从多少条开始
	         query.setStart(0);  
	         //显示几条
	         query.setRows(10); 
	         // 开启高亮组件  
	         query.setHighlight(true); 
	         // 字段开启了高亮
	         //query.addHighlightField("ac_reply_comment"); 
	         // 高亮字段
	         query.addHighlightField("ac_comment,ac_reply_comment");
	         //启用多字段高亮
	         query.set("hl.highlightMultiTerm","true");
	         //让高亮结果集中 多值字段返回 所有值
	         //query.set("hl.preserveMulti","true");
	         // 以下两个方法主要是在高亮的关键字前后加上html代码 
	         query.setHighlightSimplePre("<font color='red'>"); 
	         query.setHighlightSimplePost("</font>");
	         //请求solr搜索服务器
	         QueryResponse response = consultSolrServer.query(query);  
	         //获取请求结果
	         SolrDocumentList solrResultList = response.getResults();  
	         System.err.println("**************《共搜索到"+solrResultList.getNumFound()+"条结果》******************");
	         //获取所有高亮的字段  
	         Map<String,Map<String,List<String>>> highlightMap = response.getHighlighting();
	         for(int i=0,len=solrResultList.size();i<len;i++){  
	        	 SolrDocument doc = solrResultList.get(i);
	        	 System.out.println(solrResultList.get(i).getFieldValue("id"));
		         //获取高亮内容
	             List<String> highlightList= highlightMap.get(doc.getFieldValue("id")).get("ac_comment");  
	             if(highlightList!=null && highlightList.size()>0){ 
	            	 //赋值高亮字段
	            	 doc.put("ac_comment", highlightList.get(0));
	            	 System.out.println(highlightList.get(0));  
	             }
		         //获取高亮内容
	             highlightList= highlightMap.get(doc.getFieldValue("id")).get("ac_reply_comment");  
	             if(highlightList!=null && highlightList.size()>0){ 
	            	 //赋值高亮字段
	            	 doc.put("ac_reply_comment", highlightList.get(0));
	            	 System.out.println(highlightList.get(0));  
	             }
	         }
	}

}
