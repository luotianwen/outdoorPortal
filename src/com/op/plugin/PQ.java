package com.op.plugin;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class PQ {
	static Logger log = Logger.getLogger(PQ.class);

	//226餐饮 217旅游 209酒店 3休闲娱乐 2丽人 388结婚 289教育培训 390爱车 206运动健身 600家装 379购物 389亲子 450医疗 4生活服务 385会议宴会 1853K歌 1854电影 1861宠物 1862其他  5电商购物
    public static void main(String[] args) throws Exception{
    	 List<FL> flList = new ArrayList<FL>();
    	 int[] ids = new int[]{226,217,209,3,2,388,289,390,206,600,379,389,450,4,385,1853,1854,1861,1862,5};
    	 for(int i:ids){
    		 PQ.getFL(i,flList);
    	 }
    	log.info(flList.size());
    	 /*
    	 StringBuffer str = new StringBuffer();
    	 for(FL fl:flList){
 	    	System.out.println(fl.getCategoryName());
 	     }
 	     */
    }
    
    
    public static void getFL(int id, List<FL> flList) throws Exception{
       		HttpClient httpclient = HttpClients.createDefault();
    		// 目标地址
    		HttpPost httppost = new HttpPost(new URI("http://www.dianping.com/psw/shopCates"));
    		//设置请求和传输超时时间
    		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000).build();
    		httppost.setConfig(requestConfig);
    		// 使用NameValuePair来保存要传递的Post参数
    		List<NameValuePair> params = new ArrayList<NameValuePair>();
    		// 添加要传递的参数
    		params.add(new BasicNameValuePair("do", "getchannelcates"));
    		// 添加要传递的参数
    		params.add(new BasicNameValuePair("t", id+""));
    		// 设置字符集
    		HttpEntity httpentity = new UrlEncodedFormEntity(params, "UTF-8");
    		// 设置请求的数据
    		httppost.setEntity(httpentity);
    		// 执行请求
    		HttpResponse httpresponse = httpclient.execute(httppost);
    		//获取请求内容
    		HttpEntity entity = httpresponse.getEntity();
    		String body = EntityUtils.toString(entity);
    		System.out.println(body);
    		JSONObject objJson = JSONObject.fromObject(body);
    		String code = objJson.get("code").toString();
    		Object v = objJson.get("msg");
    		objJson = JSONObject.fromObject(v);
    		v = objJson.get("channelCates");
    		System.out.println(v.toString());
    		objJson = JSONObject.fromObject(v);
    		 // 取出 jsonObject 中的字段的值的空格  
    	    Iterator itt = objJson.keys();
    	    while (itt.hasNext()) {
    	    	List<FL> temp = (List<FL>) JSONArray.toCollection(objJson.getJSONArray(itt.next().toString()), FL.class);
    	    	// 保存一级类型ID
    	    	for(Iterator<FL> item = temp.iterator();item.hasNext();){
    	    		FL fl = item.next();
    	    		fl.setOneTypeId(id);
    	    	}
    	    	flList.addAll(temp);
    	    }
    	    
    }
}

/*
<option selected="selected" value="226">餐饮</option>
<option  value="217">旅游</option>
<option  value="209">酒店</option>
<option  value="3">休闲娱乐</option>
<option  value="2">丽人</option>
<option  value="388">结婚</option>
<option  value="289">教育培训</option>
<option  value="390">爱车</option>
<option  value="206">运动健身</option>
<option  value="600">家装</option>
<option  value="379">购物</option>
<option  value="389">亲子</option>
<option  value="450">医疗</option>
<option  value="4">生活服务</option>
<option  value="385">会议宴会</option>
<option  value="1853">K歌</option>
<option  value="1854">电影</option>
<option  value="1861">宠物</option>
<option  value="1862">其他</option>
<option  value="5">电商购物</option>*/