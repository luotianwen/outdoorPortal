package com.op.solr.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;




import com.op.dto.travels.TravelsHotDataDTO;
import com.op.dto.travels.TravelsSearchDTO;
import com.op.dto.travels.TravelsSearchResultsDTO;
import com.op.service.travels.TravelsService;
import com.op.solr.HttpSolrClient;
import com.op.solr.SolrPage;
import com.op.solr.SolrServer;
import com.op.util.Const;
import com.op.util.Tools;
import  com.op.util.jedis.RedisUtil;
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
@Service("travelsSearchService")
public class TravelsSearchService {

	//活动搜索返回字段
	@Value("#{properties['travelsConsultSearchReturnField']}")
	private String travelsConsultSearchReturnField;
	 
	/**
	 * 首页游记封面图片样式
	 */
    @Value("#{properties['HomeTravelCoverStyle']}")
    private String HomeTravelCoverStyle;
	
	/**
	 * 游记
	 */
	@Resource(name="travelsServiceImpl")
	TravelsService travelsServiceImpl;


	@Resource(name = "travelsSolrServer")
	private HttpSolrClient travelsSolrServer;
	/**
	 * 游记搜索
	 * @param keyword
	 * @param page
	 * @return
	 * @throws SolrServerException
	 */
	public SolrPage getTravelsSearchResults(TravelsSearchDTO travelsSearchDTO,SolrPage page)throws SolrServerException {
		//HttpSolrServer solrServer = SolrServer.getInstance().getServer("travels");
		SolrQuery query = new SolrQuery();

		// 请求到select中
		query.set("qt", "/select");
		if(null == travelsSearchDTO.getKeyword()){
			travelsSearchDTO.setKeyword("*");
		}else if(travelsSearchDTO.getKeyword().matches("[a-zA-Z]+")){
			travelsSearchDTO.setKeyword(travelsSearchDTO.getKeyword()+"*");
		}else{
			//keyword = ClientUtils.escapeQueryChars(keyword);
			 //query.set("defType","dismax");
			 /*设置需要查询的Field权重   
			  * 在Solr权重的设置中，所有权重标准为1，意思是当权重设置大于1时，
			  * 代表这个字段的权重变大，如果权重设置小于1并且大于0的时候，代表这个字段权重变小 。
			  */
			//query.set("qf","zh_name^3 en_name^2");
		}
		// 搜索关键字
		query.setQuery("search:" + travelsSearchDTO.getKeyword());
		// 3：发布成功
		query.addFilterQuery("issued_state:(3 OR 5)");
		// 0：未删除的游记
		query.addFilterQuery("isdelete:0");
		System.err.println("**************《开始游记搜索，关键字：" + travelsSearchDTO.getKeyword()+ "》******************");
		//指定文档结果中应返回的 Field 集
       // query.set("fl", travelsConsultSearchReturnField);
        //从多少条开始
        query.setStart((page.getPageNow()-1)*page.getPageSize());  
        //显示几条
        query.setRows(page.getPageSize()); 
		query.set("sort", "issued_time desc");
		/*// 开启高亮组件
		query.setHighlight(true);
		// 字段开启了高亮
		query.addHighlightField("content");
		query.set("hl.alternateField","digest");
		query.set("hl.maxAlternateFieldLength","70");
		// 以下两个方法主要是在高亮的关键字前后加上html代码
		query.setHighlightSimplePre("<font color='red'>");
		query.setHighlightSimplePost("</font>");
		//query.setHighlightFragsize(20); //设置对应content内容字数
		query.setHighlightSnippets(0);//设置高亮片段为0，这样关键字也就不会在高亮显示
*/		// 请求solr搜索服务器
		QueryResponse response = travelsSolrServer.query(query);
		// 获取请求结果
		SolrDocumentList solrResultList = response.getResults();
		System.err.println("**************《共搜索到" + solrResultList.getNumFound()+ "条结果》******************");
		// 获取所有高亮的字段
		//Map<String, Map<String, List<String>>> highlightMap = response.getHighlighting();
		List<TravelsSearchResultsDTO> resultList = new ArrayList<TravelsSearchResultsDTO>();
		

		for (int i = 0, len = solrResultList.size(); i < len; i++) {
			SolrDocument doc = solrResultList.get(i);
			String id = doc.getFieldValue("id").toString();
			System.out.println(id);
			boolean bl = RedisUtil.hkeys(Const.TRAVELSHOTDATAKEY+id).isEmpty();
			//判断Redis里是否有游记点赞，收藏……等热数据
			if(bl){
				try {
					TravelsHotDataDTO th = travelsServiceImpl.findHotData(id.toString());
					// 发布者名称
					doc.put("userName", th.getUserName());
					// 发布者头像
					doc.put("uHeadImg", th.getuHeadImg());
					if(th.getCover_image() == null){
						// 游记封面图片
						doc.put("cover_image", th.getCover_image());
					}else{
						// 游记封面图片
						doc.put("cover_image", th.getCover_image()+HomeTravelCoverStyle);
					}
					// 点赞次数（顶）
					doc.put("like_count", th.getLike_count());
					// 收藏次数
					doc.put("collection_count", th.getCollection_count());
					// 分享次数
					doc.put("share_count", th.getShare_count());
					// 阅读次数
					doc.put("read_count", th.getRead_count());
					// 回复次数
					doc.put("reply_count", th.getReply_count());
					// 发布状态（1：草稿；2：发布审核； 3：发布成功；4：审核失败；5：再次审核）
					doc.put("issued_state", th.getIssued_state());
					// 是否删除(0：正常；1：删除)
					doc.put("reply_count", th.getIsdelete());
					//存入热数据到缓存
					RedisUtil.hmset(Const.TRAVELSHOTDATAKEY+id,Tools.transBean2MapString(th));
					//108000秒过期 即一天半后自动删除缓存
					RedisUtil.expire(Const.TRAVELSHOTDATAKEY+id, 108000);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				System.out.println("============【走缓存啦】============");
				 List<String> hotData = RedisUtil.hmget(Const.TRAVELSHOTDATAKEY+id,"userName","like_count","collection_count","share_count","read_count","reply_count","issued_state","reply_count","uHeadImg","cover_image");
				// 发布者名称
				doc.put("userName", hotData.get(0));
				// 点赞次数（顶）
				doc.put("like_count", hotData.get(1));
				// 收藏次数
				doc.put("collection_count", hotData.get(2));
				// 分享次数
				doc.put("share_count", hotData.get(3));
				// 阅读次数
				doc.put("read_count", hotData.get(4));
				// 回复次数
				doc.put("reply_count", hotData.get(5));
				// 发布状态（1：草稿；2：发布审核； 3：发布成功；4：审核失败；5：再次审核）
				doc.put("issued_state", hotData.get(6));
				// 是否删除(0：正常；1：删除)
				doc.put("reply_count", hotData.get(7));
				// 发布者头像
				doc.put("uHeadImg", hotData.get(8));
				// 游记封面图片
				doc.put("cover_image", hotData.get(9));
			}
			//获取高亮内容
/*			List<String> titleList = highlightMap.get(id).get("content");
			StringBuffer content = new StringBuffer();
			if(titleList != null ){
				for(String item:titleList){
					content.append(item.replaceAll("\\n", ""));
				}
			}
			// 赋值高亮字段
			doc.put("content", content.toString());*/
			TravelsSearchResultsDTO travelsSearchResultsDTO = new TravelsSearchResultsDTO();
			Tools.transMap2Bean2(doc, travelsSearchResultsDTO);
			resultList.add(travelsSearchResultsDTO);
		}
		//获取并设置总条数 及查询结果
        page.setQueryResult((int) solrResultList.getNumFound(),resultList);  
	     return page;
	}
 
	 
	  
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws SolrServerException {
		TravelsSearchService s = new TravelsSearchService();
		SolrPage page = s.getTravelsSearchResults(new TravelsSearchDTO(),new SolrPage());
		for(TravelsSearchResultsDTO travels:(List<TravelsSearchResultsDTO>)page.getResult()){
		   System.out.println(travels.getTitle()); 
		   System.out.println(travels.getDigest()); 
		   System.out.println("user_id:"+travels.getUser_id()); 
		}
	     
	 
	}

}
