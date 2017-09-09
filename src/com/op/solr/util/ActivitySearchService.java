package com.op.solr.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.LBHttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Suggestion;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.op.dto.activity.searchResult.SearchResultDTO;
import com.op.entity.activity.Activity;
import com.op.entity.search.Search;
import com.op.solr.HttpSolrClient;
import com.op.solr.LBSolrServer;
import com.op.solr.SolrPage;
import com.op.solr.SolrServer;
import com.op.util.DateUtil;
import com.op.util.Tools;
/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：SearchUtil   
* 类描述：   搜索工具类
* 创建人：Win Zhong   
* 创建时间：2015年11月27日 下午2:04:19   
* 修改人：Win Zhong   
* 修改时间：2015年11月27日 下午2:04:19   
* 修改备注：   
* @version    
*
 */
@Service("activitySearchService")
public class ActivitySearchService {
	

	@Resource(name = "activitySolrServer")
	private HttpSolrClient activitySolrServer;
	
	Logger log = Logger.getLogger(this.getClass());
	//活动搜索返回字段
	@Value("#{properties['activitySearchReturnField']}")
	private String activitySearchReturnField;
	
	 public SolrPage getActivitySearchResults(Search search,SolrPage page) throws SolrServerException {
		 //LBHttpSolrServer solrServer = LBSolrServer.getInstance().getServer("activity");
		 //HttpSolrServer solrServer = SolrServer.getInstance().getServer("activity");
		 SolrQuery query = new SolrQuery();
		 
		 //请求到select中  
         query.set("qt", "/select");
         
         //搜索关键字
         query.setQuery("search:"+search.getKeyword()); //+search.getPosition()
         //活动类型
         if(!StringUtils.isEmpty(search.getTypes())){
        	 query.addFilterQuery("cl_id:("+search.getTypes().replaceAll(",", " OR ")+")");
         }
         //发布者类型
         if(!StringUtils.isEmpty(search.getAct())){
        	 query.addFilterQuery("a_active_add_user_type:("+search.getAct().replaceAll(",", " OR ")+")");
         }
         
         // 设置权重
 		 //query.set("defType", "edismax");
 		 //query.set("defType","dismax");
 		 
 		 /*设置需要查询的Field权重   
 		  * 在Solr权重的设置中，所有权重标准为1，意思是当权重设置大于1时，
 		  * 代表这个字段的权重变大，如果权重设置小于1并且大于0的时候，代表这个字段权重变小 。
 		  */
 		 //query.set("qf","title^100");
	     //query.addFilterQuery("cl_id:(1 OR 2 OR 3)"); 
	 		
		 		/*
		 		* 设置权重
		 		*/
		 		//query.set("bf", "sum(if(exists(cl_id),20000,0),linear(cl_id,2,100))");
         //query.set("bf", "sum(product(cl_id,6))");
         System.err.println("**************《开始搜索，关键字："+search.getKeyword()+"\t位置："+search.getPosition()+"》******************\t返回结果字段："+activitySearchReturnField);
       
         //添加分组统计 
         //query.setFacet(true); 
         //query.setFacetSort("ch_id");//排序字段
         //query.addFacetField("ch_id");//分组统计字段
         //query.setFacetLimit(10);
         /*query.set("facet", "on");
         query.set("facet.field", "ch_id", "cl_id");
         query.set("facet.mincount", "1");
         query.set("facet.limit", "15");*/
         
         
         //活动时间范围搜索
         if(search.getSt() != null && search.getLt() != null){
        	 query.addFilterQuery("activityTime:[ "+DateUtil.UTCFormat(search.getSt())+" TO "+DateUtil.UTCFormat(search.getLt())+" ]");
         }else if(search.getSt() == null && search.getLt() != null){
        	 query.addFilterQuery("activityTime:{ * TO "+DateUtil.UTCFormat(search.getLt())+" ]");
         }else if(search.getSt() != null && search.getLt() == null){
        	 query.addFilterQuery("activityTime:["+DateUtil.UTCFormat(search.getSt())+" TO * }");
         }
         //是否收费
         if(!StringUtils.isEmpty(search.getIsCharge())){
        	 query.addFilterQuery("isCharge:("+search.getIsCharge().replaceAll(",", " OR ")+")");
         }
         //活动时长
         if(!StringUtils.isEmpty(search.getA_date_length())){
        	 query.addFilterQuery("a_date_length:"+search.getA_date_length());
         }
         //适合人群
         if(!StringUtils.isEmpty(search.getSc_id())){
        	 query.addFilterQuery("sc_id:"+search.getSc_id());
         }
         //活动难度等级
         if(!StringUtils.isEmpty(search.getA_difficulty_type())){
        	 query.addFilterQuery("a_difficulty_type:"+search.getA_difficulty_type());
         }
         //适合儿童年龄段
         if(!StringUtils.isEmpty(search.getA_children_age())){
        	 query.addFilterQuery("a_children_age:"+search.getA_children_age());
         }
         //活动收费
         if(search.getPrice_start() != 0.0 && search.getPrice_end() != 0.0){
	    	 query.addFilterQuery("price:[ "+search.getPrice_start()+" TO "+search.getPrice_end()+" ]");
	     }else if(search.getPrice_start() == 0.0 && search.getPrice_end() != 0.0){
	    	 query.addFilterQuery("price:{ * TO "+search.getPrice_end()+" ]");
	     }else if(search.getPrice_start() != 0.0 && search.getPrice_end() == 0.0){
	    	 query.addFilterQuery("price:["+search.getPrice_start()+" TO * }");
	     }
         //位置中文名称搜索
         if(!StringUtils.isEmpty(search.getPosition())){
        	query.addFilterQuery("dz:"+search.getPosition());
         }
         if(!StringUtils.isEmpty(search.getBl())){
        	 System.err.println("**************《进入附近搜索》******************");
             //距离过滤函数 
             query.set("fq", "{!geofilt}");//{!func}geodist()
             //当前经纬度  (纬度,经度）
             query.set("pt", search.getBl());
             //经纬度的字段 
             query.set("sfield", "coordinates");
             //就近1.5公里  单位：公里
             query.set("d", ""+search.getD());
             //设置排序字段 按照距离排序
             query.set("sort", "geodist() asc");
           //指定文档结果中应返回的 Field 集
             query.set("fl", activitySearchReturnField+",dist:geodist(coordinates,"+search.getBl()+")");  //计算距离
         }else{
        	 //指定文档结果中应返回的 Field 集
             query.set("fl", activitySearchReturnField);
         }
         //从多少条开始
         query.setStart((page.getPageNow()-1)*page.getPageSize());  
         //显示几条
         query.setRows(page.getPageSize()); 
         if(search.isHighlight()){
             // 开启高亮组件  
             query.setHighlight(true); 
             // 字段开启了高亮
             query.addHighlightField("title"); 
             // 高亮字段
             //query.addHighlightField("search,title");
             //启用多字段高亮
             //query.set("hl.highlightMultiTerm","true");
             //让高亮结果集中 多值字段返回 所有值
             //query.set("hl.preserveMulti","true");
             // 以下两个方法主要是在高亮的关键字前后加上html代码 
             query.setHighlightSimplePre("<font color='red'>"); 
             query.setHighlightSimplePost("</font>");
         }
        
         //请求solr搜索服务器
         QueryResponse response = activitySolrServer.query(query);  
         //获取请求结果
         SolrDocumentList solrResultList = response.getResults();  
         System.err.println("**************《共搜索到"+solrResultList.getNumFound()+"条结果》******************");
         //获取所有高亮的字段  
         Map<String,Map<String,List<String>>> highlightMap = response.getHighlighting();
         List<Object> resultList = new ArrayList<Object>();
         for(int i=0,len=solrResultList.size();i<len;i++){  
        	 SolrDocument doc = solrResultList.get(i);
        	 //System.out.println(solrResultList.get(i).getFieldValue("id"));
        	 if(search.isHighlight()){
	        	//获取高亮内容
	             List<String> titleList= highlightMap.get(doc.getFieldValue("id")).get("title");  
	             if(titleList!=null && titleList.size()>0){ 
	            	 //赋值高亮字段
	            	 doc.put("title", titleList.get(0));
	            	 //System.out.println(titleList.get(0));  
	             }
        	 }
             SearchResultDTO activity = new SearchResultDTO();
	         Tools.transMap2Bean2(doc, activity);
	         //System.out.println("===============距离定位点："+activity.getDist());  
	         //System.out.println(activity.getTitle());  
	         resultList.add(activity);
	     }
         
         //获取分组统计信息
         //List<Count> childfacetList = response.getFacetField("ch_id").getValues();
         
         
         //获取并设置总条数 及查询结果
         page.setQueryResult((int) solrResultList.getNumFound(),resultList);  
	     return page;
	}

	 /**
	  * 根据id获取详细信息
	  * @param id
	  * @return
	  * @throws SolrServerException
	  */
	 public Activity getSearchActivityResults(String id) throws SolrServerException {
		 //LBHttpSolrServer solrServer = LBSolrServer.getInstance().getServer("activity");
		 //HttpSolrServer solrServer = SolrServer.getInstance().getServer("activity");
		 SolrQuery query = new SolrQuery();
		 
		 //请求到select中  
         query.set("qt", "/select");
         
         //设置查询类容
         query.setQuery("id:"+id); 
         System.err.println("**************《开始根据ID搜索："+id+"》******************");
       
         //请求solr搜索服务器
         QueryResponse response = activitySolrServer.query(query);  
         
         //获取请求结果
         SolrDocumentList solrResultList = response.getResults();  

         System.err.println("**************《共搜索到"+solrResultList.getNumFound()+"条结果》******************");
         Activity activity = null ;
         if(!StringUtils.isEmpty(solrResultList)){
        	activity = new Activity();
         Tools.transMap2Bean2(solrResultList.get(0), activity);
         }
	     return activity;
	}


	 
	 /**
	  * 
	  * @Description: 关键词搜索提示
	  * @param keyword
	  * @return
	  * @throws SolrServerException   
	  * @return Map<String,Object>  
	  * @author WinZhong
	  * @date 2016年1月25日 下午4:23:32
	  */
	public Map<String,Object> getActivitySuggest(String keyword) throws SolrServerException {
		 //LBHttpSolrServer solrServer = LBSolrServer.getInstance().getServer("activity");
		// HttpSolrServer solrServer = SolrServer.getInstance().getServer("activity");
		 SolrQuery query = new SolrQuery();
		 //请求到suggest中  
         query.set("qt", "/suggest");
         //设置查询类容
         query.set("q", "title_autocomplete:"+keyword);  
         query.set("spellcheck.build", "true");  
         //从多少条开始
         query.setStart(0);  
         //显示几条
         query.setRows(10); 
         //请求solr搜索服务器
         SpellCheckResponse  spellCheckResponse = activitySolrServer.query(query).getSpellCheckResponse();  
         //获取请求结果
         List<Suggestion> suggestions = spellCheckResponse.getSuggestions();
         System.err.println("**************《共搜索到"+suggestions.size()+"条结果》******************");
        // List<Object> rList = getResultList();
         Map<String,Object> sug = new HashMap<String,Object>();
         sug.put("q", keyword);
/*         for(int i=0;i<suggestions.size();i++){  
	        log.info(suggestions.get(i).getAlternatives().toArray()[0]);  
	     }*/

         if(suggestions.size() > 0){
        	 sug.put("s",suggestions.get(0).getAlternatives().toArray());
         }
        
         return sug;
	}	 
	 
	 
	 
	 /**
	  * 
	  * @Description: 拼写检查 
	  * @param word
	  * @return
	  * @throws SolrServerException   
	  * @return String  
	  * @author WinZhong
	  * @date 2016年1月25日 下午4:25:24
	  */
	public String spellCheck(String word) throws SolrServerException {
		 //LBHttpSolrServer solrServer = LBSolrServer.getInstance().getServer("activity");
		// HttpSolrServer solrServer = SolrServer.getInstance().getServer("activity");
		SolrQuery query = new SolrQuery();
		 //请求到suggest中  
        query.set("qt", "/spell");
        query.set("q", "title_spell:"+word);
		query.set("defType", "edismax");// 加权
		query.set("qf", "title_spell^20.0");
		query.set("spellcheck", "true");
		query.set("spellcheck.build", "true");// 遇到新的检查词，会自动添加到索引里面
		query.set("spellcheck.count", 1);
        //从多少条开始
        query.setStart(0);  
        //显示几条
        query.setRows(1); 
        //请求solr搜索服务器
        QueryResponse response = activitySolrServer.query(query);  
        //获取请求结果
        SolrDocumentList solrResultList = response.getResults();  
        //获取并设置总条数
        System.err.println("**************《共搜索到"+solrResultList.getNumFound()+"条结果》******************");
		  
		if(solrResultList.getNumFound()>0){
			 System.err.println("**************《推荐词："+solrResultList.get(0).get("title")+"》******************");	
		}		 
		return null;
	}
	 
	 
	  
	public static void main(String[] args) throws SolrServerException {
		 LBHttpSolrServer solrServer = LBSolrServer.getInstance().getServer("activity");
 		 Map<String,String> params = new HashMap<String,String>();
		 	params.put("qt", "/select");//请求到select中  
	        params.put("q", "*:*");  
	        params.put("fq", "{!geofilt}");//距离过滤函数 
	        params.put("pt", "39.88028,116.730744");//当前经纬度 
	        params.put("sfield", "coordinates");//经纬度的字段  (纬度,经度）
	        params.put("d", "1.5");//就近2公里 
	        params.put("sort", "geodist() asc");//根据距离排序 
	        params.put("fl", "*,dist:geodist(coordinates,39.88028,116.730744)");  
	        params.put("start", "0");//记录开始位置  
	        params.put("rows", "10");//查询的行数  
	        QueryResponse resp = solrServer.query(new MapSolrParams(params), METHOD.POST);  
	        SolrDocumentList docs = resp.getResults();  
	        for(int i=0;i<docs.size();i++){  
	        	Activity a = new Activity();
	        	Tools.transMap2Bean2(docs.get(i), a);
	        	System.out.println(a.getTitle());  
	        }   
		/* SolrQuery query = new SolrQuery();
		 //请求到select中  
         query.set("qt", "/select");
         //设置查询类容
         query.set("q", "*:*");  
         //距离过滤函数 
         query.set("fq", "{!geofilt}");
         //当前经纬度  (纬度,经度）
         query.set("pt", "39.88028,116.730744");
         //经纬度的字段 
         query.set("sfield", "coordinates");
         //就近1.5公里  单位：公里
         query.set("d", "1.5");
         //设置排序字段 按照距离排序
         query.set("sort", "geodist() asc");
         query.set("fl", "*,dist:geodist(coordinates,39.88028,116.730744)");  //计算距离
         //从多少条开始
         query.setStart(0);  
         //显示几条
         query.setRows(10); 
         // 开启高亮组件  
         query.setHighlight(true); 
         // 字段开启了高亮
         query.addHighlightField("title"); 
         // 以下两个方法主要是在高亮的关键字前后加上html代码 
         query.setHighlightSimplePre("<font color='red'>"); 
         query.setHighlightSimplePost("</font>");
         //请求solr搜索服务器
         QueryResponse response = solrServer.query(query);  
         //获取请求结果
         SolrDocumentList resultList = response.getResults();  
         for(int i=0;i<resultList.size();i++){  
	           log.info(resultList.get(i));  
	        } */
	}

}
