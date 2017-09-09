package com.op.util;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;



import com.op.entity.users.Users;
import  com.op.util.jedis.RedisUtil;

public class XxTest {
	
	static Users user;
	static Map<String,Users> map = new HashMap<String,Users>();

	
	static boolean ss;
	
	public static boolean isSs() {
		return ss;
	}

	public static void setSs(boolean ss) {
		XxTest.ss = ss;
	}

	/**
	 * 方法描述： 返回类型：void
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws Exception {
		LinkedList<String> a=new LinkedList();
		a.add("a");
		a.add("b");
		for (String s:a){
			System.out.println(s);
		}
		a.add("c");
		a.removeFirst();
		for (String s:a){
			System.out.println(s);
		}
		/*user = new Users();
		user.setuId("2");
		
		map.put("user_o", user);
		
		user=null;
		
		System.out.println(map.get("user_o"));
		System.out.println(user);*/
		
		/*
		 * 
		 * Map<String,Object> map = new HashMap<String,Object>();
		 * List list = new ArrayList();
		 * Set set = new HashSet();
		 * String[] array = new String[2];
		 * 
		 */
		
		/*String template = "你好${姓名!'未知'}，今天是${date?string('yyyy-MM-dd')}"; //变量参考freemarker语法  
	    Map m = new HashMap();
	    m.put("姓名", "管理员");
	    m.put("date", new Date());
	   log.info(FreeMarkerReplaceUtil.freemarkerProcess(m, template)); //"你好管理员，今天是2013-09-11"  
*/		
		/*
		 * Properties prop =
		 * Resources.getResourceAsProperties("dbconfig.properties");
		 *log.info(prop.getProperty("url"));
		 * 
		 * 
		 * BufferedReader bw = new
		 * BufferedReader(Resources.getResourceAsReader("interceptorPath.txt"));
		 * String line = null; while ((line = bw.readLine()) != null) {
		 *log.info(line); }
		 * 
		 * 
		 * Object obj = new Object();
		 */
		//System.out.println(DateUtil.getAfterDayDate("7"));
		
		/*Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.222:1521:orcl", "op_java", "java123");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from users ");
		
		while(rs.next()){
			System.out.println(rs.getString("u_id")+":"+rs.getString("uname"));
		}
		
		
		*/
		
		
		//System.out.println(DateUtil.YYYY_MM_DDgetAfterDay("365"));
		
		/*int yzm = ((int) (Math.random()*8999)+1000);
		System.out.println(yzm);*/
		
		
		/*Map<String,Object> map = new HashMap<String,Object>();
		// 循环100W
		for(int i=0;i<1000000;i++){
			Users user = new Users();
			user.setuId(1+"");
			map.put(i+"_user", user);
		}

		List<Object> list = new ArrayList<Object>();
		List<Object> list3 = new ArrayList<Object>();
		
		----------------	Entry解析Map	-----------------------
		long st1 = System.currentTimeMillis();
		Set<Entry<String,Object>> set = map.entrySet();
		for(Entry<String,Object> entry:set){
			list.add(entry.getValue());
		}
		long test1 = System.currentTimeMillis()-st1;
		
		-----------------	keySet解析Map ----------------------
		long st3 = System.currentTimeMillis();
		Set<String> set3 = map.keySet();
		for(String str:set3){
			list3.add(map.get(str));
		}
		long test3 = System.currentTimeMillis()-st3;
		
		
		System.out.println("Entry解析Map用时："+test1+"毫秒。");
		System.out.println("keySet解析Map用时："+test3+"毫秒。");*/
		
		
		/*StringBuffer ss = new StringBuffer("hello");
		ss.append("yx");
		System.out.println(ss);*/
		
		/*StringBuilder sb = new StringBuilder("simple");
		sb.append("  test");
		System.out.println(sb.toString());*/
		
		/*int height = 20;
		double ratioStart = 11d;
		double ratioEnd = 18d;
		double ratioBase = 20d;
		
		double start = ratioStart/ratioBase*height;
		double end = ratioEnd/ratioBase*height;
		double space = end-start;
		
		System.out.println((int)(Math.random()*space));*/

		/*List<Lines> list = new ArrayList<Lines>();
		
		Lines line = new Lines();
		line.setL_time(DateUtil.YYYY_MM_DDfomatDate("2016-03-24"));
		list.add(line);
		
		Lines line2 = new Lines();
		line2.setL_time(DateUtil.YYYY_MM_DDfomatDate("2016-03-22"));
		list.add(line2);
		
		Lines line3 = new Lines();
		line3.setL_time(DateUtil.YYYY_MM_DDfomatDate("2016-03-20"));
		list.add(line3);

		
		System.out.println(DateUtil.YYYY_MM_DDgetDay(list.get(0).getL_time()));
		System.out.println(DateUtil.YYYY_MM_DDgetDay(list.get(1).getL_time()));
		System.out.println(DateUtil.YYYY_MM_DDgetDay(list.get(2).getL_time()));

		for (int i=0,len=list.size();i<len;i++) {
			for(int j=i+1;j<len;j++){
				Lines item1 = list.get(i);
				Lines item2 = list.get(j);
				// 比较两条线路的日期，从小到大排序
				if(item2.getL_time().getTime()<item1.getL_time().getTime()){
					Lines temp = item1;
					list.set(i, item2);
					list.set(j, temp);
				}
				
			}
		}
		System.out.println("------------------------------------------");
		
		System.out.println(DateUtil.YYYY_MM_DDgetDay(list.get(0).getL_time()));
		System.out.println(DateUtil.YYYY_MM_DDgetDay(list.get(1).getL_time()));
		System.out.println(DateUtil.YYYY_MM_DDgetDay(list.get(2).getL_time()));
	
		*/
		
		/*String line = "116.247917,39.957596|116.248039,39.958504|";
		for(String str : line.split("\\|")){
			System.out.println(str);
		}*/
		/*InputStreamReader read = new InputStreamReader (XxTest.class.getClassLoader().getResourceAsStream("zdb.xml"));
		BufferedReader bw = new BufferedReader(read);

		String line = null;
		while ((line = bw.readLine()) != null) {
			System.out.println(line);
		}*/
		
		/*String resCode = HttpRequest.sendPost("http://localhost/outdoorPortal/test", "str=123", "UTF-8");
		System.out.println(resCode);*/
		/*
		RedisUtil.del("13264256329");*/
		/*for(int i=0;i<4;i++){
			System.out.println(XxTest.checkRedisPhoneNum("13264256329", new HashMap<String,String>()));
		}*/
		
		/*InputStreamReader read = new InputStreamReader (new FileInputStream("E:/a.txt"),"utf8");
		BufferedReader bw = new BufferedReader(read);

		String line = null;
		int i=1;
		String sql = "insert into venuetype(id,categoryId ,categoryName ,categoryLevel ,parentId ,oneTypeId )\n";
		while ((line = bw.readLine()) != null) {
			String value = line.substring(line.indexOf("=\"")+2, line.indexOf("\">"));
			String categoryName = line.substring(line.indexOf("\">")+2, line.indexOf("</"));
			sql += " SELECT "+i+","+value+",'"+categoryName+"',1,0,"+value+" FROM DUAL UNION ALL \n";
			i++;
		}
		System.out.println(sql);*/
		/*String key=String.format(Const.SPOT_ID,"2");
		System.out.println(key);*/
		
		/*Map<String,String> map = (Map<String, String>) SerializationUtil.deserialize(RedisUtil.get(Const.POINT_SERVICE_CROWD.getBytes()));
		Set<String> set = map.keySet();
		for(String key:set){
			System.out.println(key);
		}*/
		
	}
	
}


