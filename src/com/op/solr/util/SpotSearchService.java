package com.op.solr.util;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.TermsResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.op.dto.activity.searchResult.SearchResultDTO;
import com.op.solr.HttpSolrClient;
import com.op.solr.SolrPage;
import com.op.solr.SolrServer;
import com.op.spot.dto.SpotResult;
import com.op.spot.dto.SpotSearchParameter;
import com.op.util.Tools;
 /**
  * 
  * @author WinZhong
  *
  */
@Service("spotSearchService")
public class SpotSearchService {
	

	@Resource(name = "spotSolrServer")
	private HttpSolrClient spotSolrServer;

	public void querySpot(SpotSearchParameter sp,SolrPage page)throws SolrServerException {
		//HttpSolrServer solrServer = SolrServer.getInstance().getServer("spot");
		// 创建查询参数以及设定的查询参数
		SolrQuery query = new SolrQuery();
		
		// 搜索关键字
		if(!StringUtils.isEmpty(sp.getKeyword())){
			query.setQuery("status:1 AND search:" + sp.getKeyword());
		}else{
			query.setQuery("status:1");
		}

        //位置中文名称搜索
        if(!StringUtils.isEmpty(sp.getBl())){
       	 System.err.println("**************《进入附近搜索》******************");
            //距离过滤函数 
            query.set("fq", "{!geofilt}");
            //当前经纬度  (纬度,经度）
            query.set("pt", sp.getBl());
            //经纬度的字段 
            query.set("sfield", "coordinate");
            //就近1.5公里  单位：公里
            query.set("d", ""+sp.getD());
            //设置排序字段 按照距离排序
            query.set("sort", "geodist() asc");
            //指定文档结果中应返回的 Field 集
            query.set("fl", "id,name,price,address,scenicspotintroduction,labelss,coordinate,url,dist:geodist(coordinate,"+sp.getBl()+")");  //计算距离
        }else{
       	 //指定文档结果中应返回的 Field 集
            query.set("fl", "id,name,price,address,scenicspotintroduction,labelss,coordinate,url");
        }
        //游玩主题(多选) labelss  多个英文(,)逗号隔开
        if(!StringUtils.isEmpty(sp.getT())){
       	 	query.addFilterQuery("labelss:("+sp.getT().replaceAll(",", " OR ")+")");
        }

        //适合人群(多选) suitablecrowd  多个英文(,)逗号隔开
        if(!StringUtils.isEmpty(sp.getSc())){
       	 	query.addFilterQuery("suitablecrowd:("+sp.getSc().replaceAll(",", " OR ")+")");
        }

        //景区级别
        if(!StringUtils.isEmpty(sp.getLevel())){
       	 	query.addFilterQuery("level:"+sp.getLevel());
        }


        //收费
        if(sp.getPs() != 0.0 && sp.getPe() != 0.0){
	    	 query.addFilterQuery("price:[ "+sp.getPs()+" TO "+sp.getPe()+" ]");
	     }else if(sp.getPs() == 0.0 && sp.getPe() != 0.0){
	    	 query.addFilterQuery("price:{ * TO "+sp.getPe()+" ]");
	     }else if(sp.getPs() != 0.0 && sp.getPe() == 0.0){
	    	 query.addFilterQuery("price:["+sp.getPs()+" TO * }");
	     }
        


        //省
        if(!StringUtils.isEmpty(sp.getProvince())){
       	 	query.addFilterQuery("province:"+sp.getProvince());
        }

        //市
        if(!StringUtils.isEmpty(sp.getCity())){
       	 	query.addFilterQuery("city:"+sp.getCity());
        }

        //区
        if(!StringUtils.isEmpty(sp.getDistrict())){
       	 	query.addFilterQuery("area:"+sp.getDistrict());
        }
        
        if(sp.isHighlight()){
            // 开启高亮组件  
            query.setHighlight(true); 
            // 字段开启了高亮
            query.addHighlightField("name"); 
            // 以下两个方法主要是在高亮的关键字前后加上html代码 
            query.setHighlightSimplePre("<font color='red'>"); 
            query.setHighlightSimplePost("</font>");
        }
        //从多少条开始
        query.setStart((page.getPageNow()-1)*page.getPageSize());  
        //显示几条
        query.setRows(page.getPageSize()); 
		// 查询并获取相应的结果！
		QueryResponse response = spotSolrServer.query(query);
		//搜索结果数
		long resultCount = response.getResults().getNumFound();
		List<SpotResult> spotList = response.getBeans(SpotResult.class);
		/**** 如果开启字段高亮，则获取高亮结果 ****/
		if (sp.isHighlight() && resultCount > 0) {
			// 获取所有高亮的字段
			Map<String, Map<String, List<String>>> highlightMap = response.getHighlighting();
			for (SpotResult s : spotList) {
				// 获取高亮内容
				List<String> highlightList = highlightMap.get(s.getId()).get("name");
				if (highlightList != null && highlightList.size() > 0) {
					// 赋值高亮字段
					s.setName(highlightList.get(0));
				}
			}
		}
		
		
		
		/*//获取请求结果
        SolrDocumentList solrResultList = response.getResults();  
        //获取所有高亮的字段  
        Map<String,Map<String,List<String>>> highlightMap = response.getHighlighting();
        for(int i=0,len=solrResultList.size();i<len;i++){  
	       	 SolrDocument doc = solrResultList.get(i);
	       	 if(sp.isHighlight()){
		        	//获取高亮内容
		             List<String> titleList= highlightMap.get(doc.getFieldValue("id")).get("name");  
		             if(titleList!=null && titleList.size()>0){ 
		            	 //赋值高亮字段
		            	 doc.put("name", titleList.get(0));
		             }
	       	 }
	         System.out.println(doc.getFieldValue("name"));  
	     }
		*/
		
		
		// 获取并设置总条数 及查询结果
		page.setQueryResult(resultCount, spotList);
	}
 
	public void getTerms(String keyword)throws SolrServerException {
		//HttpSolrServer solrServer = SolrServer.getInstance().getServer("spot");
		// 创建查询参数以及设定的查询参数
		SolrQuery query = new SolrQuery();
		//转义特殊字符，防止注入
		keyword = ClientUtils.escapeQueryChars(keyword);
		query.set("q", "*:*");
		query.set("qt", "/terms");
		query.set("df", "name");
		query.set("terms", "true");
		query.set("terms.fl", "name");
	    query.set("terms.regex", keyword+"+.*");  
	    query.set("terms.regex.flag", "case_insensitive");  
	    query.set("terms.limit", "10");  
		query.set("terms.raw", "true");
		query.set("terms.sort", "count");		
		query.setStart(0);
		query.setRows(0);
		// 查询并获取相应的结果！
		QueryResponse response = spotSolrServer.query(query);
		// 获取相关的查询结果
		if (response != null) {
			TermsResponse termsResponse = response.getTermsResponse();
			if (termsResponse != null) {
				Map<String, List<TermsResponse.Term>> termsMap = termsResponse.getTermMap();
				for (Map.Entry<String, List<TermsResponse.Term>> termsEntry : termsMap.entrySet()) {
					System.out.println("Field Name: " + termsEntry.getKey());
					List<TermsResponse.Term> termList = termsEntry.getValue();
					for (TermsResponse.Term term : termList) {
						System.out.println(term.getTerm() + " : " + term.getFrequency());
					}
				}
			}

		}
	}
 
	 
	  
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws SolrServerException {
		SpotSearchService s = new SpotSearchService();
		SolrPage page = new SolrPage();
		SpotSearchParameter sp = new SpotSearchParameter();
		sp.setKeyword("故宫");
		s.querySpot(sp, page);
		List<SpotResult> spotList = (List<SpotResult>) page.getResult();
		for (SpotResult sr : spotList) {
			System.out.println(sr.getName());
		}
		s.getTerms("故宫");
	 
	}

}
