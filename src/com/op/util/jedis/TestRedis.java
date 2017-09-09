package com.op.util.jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.op.util.Const;

import redis.clients.jedis.Jedis;


/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：TestRedis   
* 类描述：   
* 创建人：Win Zhong   
* 创建时间：2015年12月14日 上午9:32:59   
* 修改人：Win Zhong   
* 修改时间：2015年12月14日 上午9:32:59   
* 修改备注：   
* @version    
*
 */
public class TestRedis {
    private Jedis jedis;
    
 
    
   /* *//**
     * redis存储字符串
     *//*
    
    public void testString() {
        //-----添加数据----------  
        RedisUtil.set("name","xinxin");//向key-->name中放入了value-->xinxin
       System.out.println(RedisUtil.get("name"));//执行结果：xinxin
        
        RedisUtil.append("name", " is my lover"); //拼接
       System.out.println(RedisUtil.get("name"));
        
        RedisUtil.del("name");  //删除某个键
       System.out.println(RedisUtil.get("name"));
        //设置多个键值对
        RedisUtil.mset("name","hehehehe","age","23","qq","476777XXX");
        RedisUtil.incr("age"); //进行加1操作
       System.out.println(RedisUtil.get("name") + "-" + RedisUtil.get("age") + "-" + RedisUtil.get("qq"));
        
    }
    
    *//**
     * redis操作Map
     *//*
    
    public void testMap() {
        //-----添加数据----------  
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "xinxin");
        map.put("age", "22");
        map.put("qq", "123456");
        RedisUtil.hmset("user",map);
        //取出user中的name，执行结果:[xinxin]-->注意结果是一个泛型的List  
        //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数  
        List<String> rsmap = RedisUtil.hmget("user", "name");
       System.out.println(rsmap.get(0));  
        System.err.println("***"+RedisUtil.hkeys("user7").isEmpty());
        //修改map key的值
        RedisUtil.hset("user", "name","哈哈哈哈");
        //删除map中的某个键值  
        RedisUtil.hdel("user","age");
       System.out.println(RedisUtil.hmget("user", "age")); //因为删除了，所以返回的是null
       System.out.println(RedisUtil.hlen("user")); //返回key为user的键中存放的值的个数2
       System.out.println(RedisUtil.exists("user"));//是否存在key为user的记录 返回true
       System.out.println(RedisUtil.hkeys("user"));//返回map对象中的所有key
       System.out.println(RedisUtil.hvals("user"));//返回map对象中的所有value
        Iterator<String> iter=RedisUtil.hkeys("user").iterator();
        while (iter.hasNext()){  
            String key = iter.next();  
           System.out.println(key+":"+RedisUtil.hmget("user",key));
        }  
    }
    
    *//**
     * jedis操作List 
     *//*

    public void testList(){  
        //开始前，先移除所有的内容  
        RedisUtil.del("java framework");
       System.out.println(RedisUtil.lrange("java framework",0,-1));
        //先向key java framework中存放三条数据  
        RedisUtil.lpush("java framework","spring");
        RedisUtil.lpush("java framework","struts");
        RedisUtil.lpush("java framework","hibernate");
        //再取出所有数据RedisUtil.lrange是按范围取出，
        // 第一个是key，第二个是起始位置，第三个是结束位置，RedisUtil.llen获取长度 -1表示取得所有
       System.out.println(RedisUtil.lrange("java framework",0,-1));
        
        RedisUtil.del("java framework");
        RedisUtil.rpush("java framework","spring");
        RedisUtil.rpush("java framework","struts");
        RedisUtil.rpush("java framework","hibernate");
       System.out.println(RedisUtil.lrange("java framework",0,-1));
    }  
    
    *//**
     * jedis操作Set 
     *//*

    public void testSet(){  
        //添加  
        RedisUtil.sadd("user","liuling");
        RedisUtil.sadd("user","xinxin");
        RedisUtil.sadd("user","ling");
        RedisUtil.sadd("user","xinxin");
        RedisUtil.sadd("user","who");
        //移除noname  
        RedisUtil.srem("user","who");
       System.out.println(RedisUtil.smembers("user"));//获取所有加入的value
       System.out.println(RedisUtil.sismember("user", "who"));//判断 who 是否是user集合的元素
       System.out.println(RedisUtil.srandmember("user"));
       System.out.println(RedisUtil.scard("user"));//返回集合的元素个数
    }  
  

    public void test() throws InterruptedException {  
        //jedis 排序  
        //注意，此处的rpush和lpush是List的操作。是一个双向链表（但从表现来看的）  
        RedisUtil.del("a");//先清除数据，再加入数据进行测试
        RedisUtil.rpush("a", "1");
        RedisUtil.lpush("a","6");
        RedisUtil.lpush("a","3");
        RedisUtil.lpush("a","9");
       System.out.println(RedisUtil.lrange("a",0,-1));// [9, 3, 6, 1]
       System.out.println(RedisUtil.sort("a")); //[1, 3, 6, 9]  //输入排序后结果
       System.out.println(RedisUtil.lrange("a",0,-1));
    }  
    

    public void testRedisPool() {
        RedisUtil.getJedis().set("newname", "中文测试");
       System.out.println(RedisUtil.getJedis().get("newname"));
    }

	*//**
	 * 简单添加
	 *//*

	public void test1() {
		String name = "name";
		String value = "qq";
		RedisUtil.set(name, value);
		System.out.println("追加前：" + RedisUtil.get(name)); // 追加前：qq
		// 在原有值得基础上添加,如若之前没有该key，则导入该key
		RedisUtil.append(name, "ww");
		System.out.println("追加后：" + RedisUtil.get(name)); // 追加后：qqww

		RedisUtil.append("id", "ee");
		System.out.println("没此key：" + RedisUtil.get(name));
		System.out.println("get此key：" + RedisUtil.get("id"));
	}

	*//**
	 * mset 是设置多个key-value值 参数（key1,value1,key2,value2,...,keyn,valuen） mget
	 * 是获取多个key所对应的value值 参数（key1,key2,key3,...,keyn） 返回的是个list
	 *//*

	public void test2() {
		RedisUtil.mset("name1", "aa", "name2", "bb", "name3", "cc");
		System.out.println(RedisUtil.mget("name1", "name2", "name3"));
	}

	*//**
	 * map
	 *//*

	public void test3() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "fujianchao");
		map.put("password", "123");
		map.put("age", "12");
		// 存入一个map
		RedisUtil.hmset("user", map);
		// map key的个数
		System.out.println("map的key的个数" + RedisUtil.hlen("user"));
		// map key
		System.out.println("map的key" + RedisUtil.hkeys("user"));
		// map value
		System.out.println("map的value" + RedisUtil.hvals("user"));
		// (String key, String... fields)返回值是一个list
		List<String> list = RedisUtil.hmget("user", "age", "name");
		System.out.println("redis中key的各个 fields值："
				+ RedisUtil.hmget("user", "age", "name") + list.size());
		// 删除map中的某一个键 的值 password
		// 当然 (key, fields) 也可以是多个fields
		RedisUtil.hdel("user", "age");
		System.out.println("删除后map的key" + RedisUtil.hkeys("user"));
	}

	*//**
	 * list
	 *//*

	public void test4() {
		RedisUtil.lpush("list", "aa");
		RedisUtil.lpush("list", "bb");
		RedisUtil.lpush("list", "cc");
		System.out.println(RedisUtil.lrange("list", 0, -1));
		System.out.println(RedisUtil.lrange("list", 0, 1));
		System.out.println(RedisUtil.lpop("list")); // 栈顶
		RedisUtil.del("list");
	}

	*//**
	 * 自定义对象 User为例 id name
	 * 
	 * RedisTemplate 中有 序列化和反序列化
	 * 如：template.getStringSerializer().serialize("name")
	 *//*

	public void test5() {
		Users user = new Users();
		user.setuId("123");
		user.setuName("fighter");
		// 存入一个 user对象
		RedisUtil.set("user".getBytes(), SerializationUtil.serialize(user));
		// 获取
		byte[] bs = RedisUtil.get("user".getBytes());
		Users desUser = (Users) SerializationUtil.deserialize(bs);
		System.out.println(desUser.getuId() + ":" + desUser.getuName());
	}

	@SuppressWarnings("unchecked")

	public void test6() {
		EmailTemplate et = new EmailTemplate();
		et.setEt_id("123");
		et.setEt_name("fighter");
		Map<String,EmailTemplate> map = new HashMap<String,EmailTemplate>();
		map.put("mu", et); 
		// 存入一个 user对象
		RedisUtil.set("qqqq".getBytes(), SerializationUtil.serialize(map));
		// 获取
		byte[] bs = RedisUtil.get("qqqq".getBytes());
		map = (Map<String,EmailTemplate>) SerializationUtil.deserialize(bs);
		System.out.println(map.get("mu").getEt_name());
		RedisUtil.del("EmailTemplate".getBytes());
	}	
	@SuppressWarnings("unchecked")

	public void test7() {
		EmailTemplate et = new EmailTemplate();
		et.setEt_id("123");
		et.setEt_name("fighter");
		List<EmailTemplate> list = new ArrayList<EmailTemplate>();
		list.add(et);
		// 存入一个 user对象
		RedisUtil.set("qqqq".getBytes(), SerializationUtil.serialize(list));
		// 获取
		byte[] bs = RedisUtil.get("qqqq".getBytes());
		list = (List<EmailTemplate>) SerializationUtil.deserialize(bs);
		System.out.println(list.get(0).getEt_name());
		EmailTemplate et1 = new EmailTemplate();
		et1.setEt_id("123");
		et.setEt_name("第二次");
		list.clear();
		list.add(et1);
		// 存入一个 user对象
		RedisUtil.set("qqqq".getBytes(), SerializationUtil.serialize(list));
		//108000秒过期 即一天半后自动删除缓存
		RedisUtil.expire("qqqq", 108000);
		// 获取
		bs = RedisUtil.get("qqqq".getBytes());
		list = (List<EmailTemplate>) SerializationUtil.deserialize(bs);
		System.out.println(list.get(0).getEt_name());
		RedisUtil.del("qqqq".getBytes());
	}*/
	public static void main(String[] args) throws Exception {
		/*TestRedis tr = new TestRedis();
		 // tr.RedisUtil.expire("222", 2);//5秒过期
		for (int i=0;i<2000;i++)
		{
			//tr.
			RedisUtil.set("222", "111");
			System.out.println(i+"----"+RedisUtil.get("222"));
		}*/
		
		   RedisUtil.del("java framework");
	       System.out.println(RedisUtil.lrange("java framework",0,-1));
	        //先向key java framework中存放三条数据  
	        RedisUtil.lpush("java framework","spring");
	        RedisUtil.lpush("java framework","struts");
	        RedisUtil.lpush("java framework","hibernate");
	        RedisUtil.lpush("java framework","struts");
	        //再取出所有数据RedisUtil.lrange是按范围取出，
	        // 第一个是key，第二个是起始位置，第三个是结束位置，RedisUtil.llen获取长度 -1表示取得所有
	        List<String> l = RedisUtil.lrange("java framework",0,10);
	        System.out.println(l);
	        RedisUtil.lrem("java framework", 2, "struts");
	        System.out.println(RedisUtil.lrange("java framework",0,-1));
	        
	        RedisUtil.del("java framework");
	        RedisUtil.rpush("java framework","spring");
	        RedisUtil.rpush("java framework","struts");
	        RedisUtil.rpush("java framework","hibernate");
	        System.out.println(RedisUtil.lrange("java framework",0,-1));
	        System.out.println(RedisUtil.lrange("java framework",0,0));
	        System.out.println(RedisUtil.lrange("sms_yzm_key_18610347047",0,-1));
	      /* System.out.println(RedisUtil.lrange("java framework",0,-1));
	       RedisUtil.rpush(Const.CHECK_TICKET_QUEUE,"20160930110006195");
	       System.out.println(RedisUtil.lrange(Const.CHECK_TICKET_QUEUE,0,-1));*/
	       String key=String.format(Const.SPOT_ID,"2");
		   System.out.println(key);
		   int i = 0;
		   System.out.println((++i));
		   System.out.println((++i));
		   System.out.println((++i));
		   
		   
		   

			Map<String, String> map = new HashMap<String, String>();
			map.put("name", "fujianchao");
			map.put("password", "123");
			map.put("age", "12");
			// 存入一个map
			RedisUtil.hmset("user", map); 
			
			// map key
			System.out.println("map的key" + RedisUtil.hkeys("user")); 
			// (String key, String... fields)返回值是一个list
			List<String> list = RedisUtil.hmget("user", "age", "name");
			System.out.println("redis中key的各个 fields值："
					+ RedisUtil.hmget("user", "age", "name") + list.size());
			// 删除map中的某一个键 的值 password
			   
			System.out.println(RedisUtil.hmget("user555", "name").get(0) == null); 
		   
	}
    
}