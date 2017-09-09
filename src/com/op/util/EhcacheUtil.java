package com.op.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：EhcacheUtil   
* 类描述：   
* 创建人：Win Zhong   
* 创建时间：2015年12月18日 上午11:59:47   
* 修改人：Win Zhong   
* 修改时间：2015年12月18日 上午11:59:47   
* 修改备注：   
* @version    
*
 */
@Service("ehcacheUtil")
public class EhcacheUtil {  
	  
    @Autowired
    private CacheManager manager;  
    /**
     * 添加缓存
     * @param cacheName 
     * @param key
     * @param value
     */
    public void put(String cacheName, String key, Object value) {  
        Cache cache = manager.getCache(cacheName);  
        Element element = new Element(key, value);  
        cache.put(element);  
    }  
  /**
   * 根据cacheName key 取缓存
   * @param cacheName
   * @param key
   * @return
   */
    public Object get(String cacheName, String key) {  
        Cache cache = manager.getCache(cacheName);  
        Element element = cache.get(key);  
        return element == null ? null : element.getObjectValue();  
    }  
    /**
     * 取cache
     * @param cacheName
     * @return
     */
    public Cache get(String cacheName) {  
        return manager.getCache(cacheName);  
    }  
  /**
   * 清空cacheName里某个key的缓存
   * @param cacheName
   * @param key
   */
    public void remove(String cacheName, String key) {  
        Cache cache = manager.getCache(cacheName);  
        cache.remove(key);  
    }    
    /**
     * 清空cacheName里的所有缓存
     * @param cacheName
     */
/*    public void removeAll(String cacheName) {  
      // manager.getCache(cacheName).getKeys().clear();
    }  */
  
}