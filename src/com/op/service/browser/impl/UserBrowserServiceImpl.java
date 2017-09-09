package com.op.service.browser.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;



import com.op.dao.BaseDao;
import com.op.service.browser.UserBrowserService;
import com.op.util.Common;
import com.op.util.DateUtil;
import com.op.util.SerializationUtil;
import  com.op.util.jedis.RedisUtil;

import eu.bitwalker.useragentutils.UserAgent;

@Service("userBrowserServiceImpl")
public class UserBrowserServiceImpl implements UserBrowserService{
	Logger log = Logger.getLogger(this.getClass());
	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	 
	/**
	 * 记录用户浏览器	
	 * @param userAgent
	 */
   @Override
public void recordUserBrowser(UserAgent userAgent,HttpServletRequest request){
		log.info("\n浏览器名称："+userAgent.getBrowser().toString());
		log.info("浏览器版本："+userAgent.getBrowserVersion().toString());
		log.info("操作系统版本："+userAgent.getOperatingSystem().getName()); 
		log.info("Ip地址："+Common.getIpAddr(request)); 
		 
		/*	try {

				String key = "UserBrowser_"+DateUtil.getDays();
				Map<String, String> map = new HashMap<String, String>();
				map.put("ip", Common.getRemoteAddrIp(request));
				map.put("browser", userAgent.getBrowser().toString());
				map.put("browserVersion", userAgent.getBrowserVersion().toString());
				map.put("operatingSystem", userAgent.getOperatingSystem().getName());
				map.put("accessTime", DateUtil.getTime());
				 
				RedisUtil.sadd(key.getBytes(),SerializationUtil.serialize(map));
				RedisUtil.expire(key.getBytes(), 108000);//108000秒过期 即一天半后自动删除缓存
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
	   
   }
   
   
	/**
	 * 保存用户浏览器
	 * @param userAgent
	 */
	@Override
	public void saveUserBrowser(){
		
		try {

			String key = "UserBrowser_"+DateUtil.getDays();//DateUtil.YYYYMMDDgetAfterDay("-1");
			Set<byte[]> byteList = RedisUtil.smembers(key.getBytes());
			if(!StringUtils.isEmpty(byteList) && byteList.size()>0){
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				//只获取最新的99条浏览记录
				Iterator<byte[]> it = byteList.iterator();//先迭代出来
			    while(it.hasNext()){//遍历  
			    	Map<String, Object> map = (Map<String, Object>)SerializationUtil.deserialize(it.next());
			    	list.add(map);  
			    	//每一百条保存一次
			    	if(list.size() == 100){
			    		try {
							dao.save("UserBrowserMapper.saveUserBrowser",list);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}finally{
							list.clear();
						}
			    	}
			    }
			    if(list.size() > 0){
			    	try {
						dao.save("UserBrowserMapper.saveUserBrowser",list);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			    RedisUtil.del(key.getBytes());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	 }
   
}
