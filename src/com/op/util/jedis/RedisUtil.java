package com.op.util.jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.op.util.PropertyFile;







import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：RedisUtil   
* 类描述：   
* 创建人：Win Zhong   
* 创建时间：2015年12月14日 上午10:10:08   
* 修改人：Win Zhong   
* 修改时间：2015年12月14日 上午10:10:08   
* 修改备注：   
* @version    
*
 */
public final class RedisUtil {

	// Redis服务器IP
	private static String ADDR = "192.168.1.222";

	// Redis的端口号
	private static int PORT = 6379;

	// 访问密码
	private static String AUTH = "8ddc94cbcf384cf7b95c93bdda65e9fd";

	// 可用连接实例的最大数目，默认值为8；
	// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	private static int MAX_ACTIVE = 1024;

	// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = 200;

	// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	private static int MAX_WAIT = 10000;

	private static int TIMEOUT = 10000;

	// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private static boolean TEST_ON_BORROW = true;

	private static JedisPool jedisPool = null;

	static Logger log = Logger.getLogger(RedisUtil.class);

	private static  boolean dev =true ;
	/**
	 * 初始化Redis连接池
	 */
	static {
		try {
			
			 Map<String, String> map = PropertyFile.getPropertiesMap("redisconfig.properties");
			 ADDR = map.get("ADDR");
			dev =Boolean.parseBoolean(map.get("dev"));
		     if (ADDR == null) {
		         log.error("RedisUtil:ADDR property is not configured in redisconfig.properties!!!");
		     }
		     PORT = Integer.valueOf(map.get("PORT"));
		     if (PORT == 0) {
		         log.error("RedisUtil:PORT property is not configured in redisconfig.properties!!!");
		     }
		     AUTH = map.get("AUTH");
		     if (AUTH == null) {
		         log.error("RedisUtil:AUTH property is not configured in redisconfig.properties!!!");
		     }
		     MAX_ACTIVE = Integer.valueOf(map.get("MAX_ACTIVE"));
		     if (MAX_ACTIVE == 0) {
		         log.error("RedisUtil:MAX_ACTIVE property is not configured in redisconfig.properties!!!");
		     }
		     MAX_IDLE = Integer.valueOf(map.get("MAX_IDLE"));
		     if (MAX_IDLE == 0) {
		         log.error("RedisUtil:MAX_IDLE property is not configured in redisconfig.properties!!!");
		     }
		     MAX_WAIT = Integer.valueOf(map.get("MAX_WAIT"));
		     if (MAX_WAIT == 0) {
		         log.error("RedisUtil:MAX_WAIT property is not configured in redisconfig.properties!!!");
		     }
		     TIMEOUT = Integer.valueOf(map.get("TIMEOUT"));
		     if (TIMEOUT == 0) {
		         log.error("RedisUtil:TIMEOUT property is not configured in redisconfig.properties!!!");
		     }
		     if ("true".equals(map.get("TEST_ON_BORROW"))) {
		    	 TEST_ON_BORROW = true;
		     }else{
		    	 TEST_ON_BORROW = false;
		     }
		    
			 
			 
			 
			JedisPoolConfig config = new JedisPoolConfig();
			
			// 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
			config.setBlockWhenExhausted(true);
			
			// 设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
			config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");

			// 是否启用pool的jmx管理功能, 默认true
			config.setJmxEnabled(true);

			// MBean ObjectName = new
			// ObjectName("org.apache.commons.pool2:type=GenericObjectPool,name="
			// + "pool" + i); 默 认为"pool", JMX不熟,具体不知道是干啥的...默认就好.
			config.setJmxNamePrefix("pool");

			// 是否启用后进先出, 默认true
			config.setLifo(true);
			
			// 最大连接数
			config.setMaxTotal(MAX_ACTIVE);
			
			// 最大空闲连接数
			config.setMaxIdle(MAX_IDLE);
			
			// 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,
			// 默认-1
			config.setMaxWaitMillis(MAX_WAIT);
			
			// 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
			config.setMinEvictableIdleTimeMillis(1800000);
			
			// 最小空闲连接数, 默认0
			config.setMinIdle(0);

			// 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
			config.setNumTestsPerEvictionRun(3);

			// 对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数
			// 时直接逐出,不再根据MinEvictableIdleTimeMillis判断 (默认逐出策略)
			config.setSoftMinEvictableIdleTimeMillis(1800000);

			// 在获取连接的时候检查有效性, 默认false
			config.setTestOnBorrow(TEST_ON_BORROW);
			// 在空闲时检查有效性, 默认false
			config.setTestWhileIdle(false);

			// 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
			config.setTimeBetweenEvictionRunsMillis(-1);
			 if(dev)
			jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT ,AUTH);
			else
				 jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取Jedis实例
	 *
	 * @return
	 *//*
	public synchronized static Jedis getJedis() {
		try {
			if (jedisPool != null) {
				Jedis resource = jedisPool.getResource();
				return resource;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/


	/**
	 * 插入key-val键值数据
	 */
	public static void set(String key, String val) {

		try (Jedis jedis = jedisPool.getResource()) {
			jedis.set(key, val);
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}

	}
	public static void set(byte[] key, byte[] val) {
		try (Jedis jedis = jedisPool.getResource()) {
			jedis.set(key, val);
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	/**
	 * 获取key-val数据
	 */
	public static String get(String key) {

		try (Jedis jedis = jedisPool.getResource()) {
			String val = jedis.get(key);

			return val;
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return "";
	}

	public static byte[] get(byte[] key) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.get(key);
		}
	}




	public static long del(String key) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.del(key);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return 0;
		}
	}
	public static Long del(byte[] key) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.del(key);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return 0l;
		}
	}
	public static Set<String> hkeys(String key){
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.hkeys(key);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}

	}
	public static Long hdel(byte[] key, byte[]... fields) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.hdel(key,fields);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	public static Long hdel(String key, String... fields) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.hdel(key,fields);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	public static Long hdel(String key) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.hdel(key);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	/**
	 * 设置key的过期时间
	 */
	public static long expire(String key, int seconds) {
		try (Jedis jedis = jedisPool.getResource()) {
			long rv = jedis.expire(key, seconds);
			return rv;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return 0;
		}
	}
	/**
	 * 设置key的过期时间
	 */
	public static long expire(byte[] key, int seconds) {
		try (Jedis jedis = jedisPool.getResource()) {
			long rv = jedis.expire(key, seconds);
			return rv;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return 0;
		}
	}
	public static String hmset(String key, Map<String, String> hash) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.hmset(key,hash);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}

	public static String hmset(byte[] key, Map<byte[], byte[]> fields) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.hmset(key,fields);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	public static List<byte[]> hmget(byte[] key, byte[]... fields) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.hmget(key,fields);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	public static List<String> hmget(String key, String... fields){
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.hmget(key,fields);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}

	}
	public static Set<byte[]> hkeys(byte[] key) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.hkeys(key);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}

	}
	public static Long sadd(byte[] key, byte[] fields) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.sadd(key,fields);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return 0l;
		}
	}
	public static long hset(byte[] var1, byte[] var2, byte[] var3) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.hset(var1,   var2,   var3);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return 0l;
		}
	}
	public static long hset(String var1, String var2, String var3) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.hset(var1,   var2,   var3);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return 0l;
		}
	}
	public static Set<byte[]> smembers(byte[] bytes) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.smembers(bytes);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	public static Long lpush(String key,String... fields) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.lpush(key,fields);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	public static Long rpush(String key,String... fields) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.rpush(key,fields);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	public static List<String> lrange(String key, long start, long end){
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.lrange(key,start,end);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	public static Long lrem(String key, long count, String value){
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.lrem(key,count,value);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
		
	}



}