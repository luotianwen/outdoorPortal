package com.op.solr.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Service;

import com.op.dto.mdd.PoiDTO;
import com.op.solr.HttpSolrClient;
import com.op.solr.SolrServer;
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
@Service("scenicSpotSearchService")
public class ScenicSpotSearchService {
	

	@Resource(name = "jingdianSolrServer")
	private HttpSolrClient jingdianSolrServer;	

	Logger log = Logger.getLogger(this.getClass());
	
	public List<PoiDTO> getScenicSpotSearchResults(String keyword)throws SolrServerException {
		//HttpSolrServer solrServer = SolrServer.getInstance().getServer("jingdian");
		SolrQuery query = new SolrQuery();

		// 请求到select中
		query.set("qt", "/select");
		if(null == keyword){
			return null;
		}else{
			keyword = ClientUtils.escapeQueryChars(keyword);
			if(keyword.matches("[a-zA-Z]+")){
				keyword = keyword+"*";
			}else{
				 query.set("defType","dismax");
				 /*设置需要查询的Field权重   
				  * 在Solr权重的设置中，所有权重标准为1，意思是当权重设置大于1时，
				  * 代表这个字段的权重变大，如果权重设置小于1并且大于0的时候，代表这个字段权重变小 。
				  */
				query.set("qf","zh_name^3 en_name^2");
			}
		}
		// 搜索关键字
		query.setQuery("search:" + keyword);

		System.err.println("**************《开始搜索，关键字：" + keyword+ "》******************");

		// 从多少条开始
		query.setStart(0);
		// 显示几条
		query.setRows(10);
		//query.set("sort", "zh_name asc");
		/*// 开启高亮组件
		query.setHighlight(true);
		// 字段开启了高亮
		query.addHighlightField("name");
		// 以下两个方法主要是在高亮的关键字前后加上html代码
		query.setHighlightSimplePre("<font color='red'>");
		query.setHighlightSimplePost("</font>");*/

		// 请求solr搜索服务器
		QueryResponse response = jingdianSolrServer.query(query);
		// 获取请求结果
		SolrDocumentList solrResultList = response.getResults();
		System.err.println("**************《共搜索到" + solrResultList.getNumFound()+ "条结果》******************");
		// 获取所有高亮的字段
		//Map<String, Map<String, List<String>>> highlightMap = response.getHighlighting();
		List<PoiDTO> resultList = new ArrayList<PoiDTO>();
		for (int i = 0, len = solrResultList.size(); i < len; i++) {
			SolrDocument doc = solrResultList.get(i);
			//log.info(solrResultList.get(i).getFieldValue("id"));
			// 获取高亮内容
/*			List<String> titleList = highlightMap.get(doc.getFieldValue("id")).get("name");
			if (titleList != null && titleList.size() > 0) {
				// 赋值高亮字段
				doc.put("name", titleList.get(0));
				//log.info(titleList.get(0));
			}*/
			PoiDTO poi = new PoiDTO();
			Tools.transMap2Bean2(doc, poi);
			resultList.add(poi);
		}
		return resultList;
	}
 
	 
	  
	public static void main(String[] args) throws SolrServerException {
		ScenicSpotSearchService s = new ScenicSpotSearchService();
		List<PoiDTO> poiList = s.getScenicSpotSearchResults("北京");
		for(PoiDTO poi:poiList){
		   System.out.println(poi.getZh_name()+"******"+poi.getEn_name()+"******"+poi.getBelongs()); 
		}
	     
	 
	}

}
