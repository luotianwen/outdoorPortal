package com.op.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.ibatis.io.Resources;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import com.op.entity.zd.ZdColumn;
import com.op.entity.zd.ZdTable;

/**
 * 项目名称：outdoorPortal 类名称：ZdGetValue 类描述： 创建人：Yan 创建时间：2015年11月10日 上午10:27:38
 * 修改人：Yan 修改时间：2015年11月10日 上午10:27:38 修改备注：
 * 
 * @version
 * 
 */
public class ZdGetValue {
	/**
	 * 节点
	 */
	private final static String TABLE = "table";
	private final static String COLUMN = "column";
	private final static String ENTRY = "entry";
	private final static String KEY = "key";
	private final static String VALUE = "value";

	private static List<ZdTable> tableList = null;

	private ZdGetValue(){
		
	}
	
	private static void setTableList() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		Document document = null;
		try {
			db = dbf.newDocumentBuilder();
			String fileName = "zdb.xml";
			document = db.parse(Resources.getResourceAsFile(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// table节点
		// {getElementsByTagName:'获取节点',getAttribute(pro):'获取该节点的属性'}
		NodeList tables = document.getElementsByTagName(TABLE);
		tableList = new ArrayList<ZdTable>();
		for (int i = 0,tles = tables.getLength(); i < tles; i++) {
			Element table = (Element) tables.item(i);
			
			// 封装表
			ZdTable tb = new ZdTable();
			Set<ZdColumn> columnList = new HashSet<ZdColumn>();
			tb.setName(table.getAttribute("name").trim());
			tb.setColumns(columnList);

			// column节点,column集合
			NodeList columns = table.getElementsByTagName(COLUMN);
			for (int j = 0,cles = columns.getLength(); j < cles; j++) {
				Element column = (Element) columns.item(j);
				
				// 封装列
				ZdColumn cl = new ZdColumn();
				Map<String, String> map = new LinkedHashMap<String, String>();// 保证数据按照顺序排列
				cl.setName(column.getAttribute("name").trim());
				cl.setMap(map);

				// entry节点，entry集合
				NodeList entrys = column.getElementsByTagName(ENTRY);
				for (int e = 0,eles = entrys.getLength(); e < eles; e++) {
					Element entry = (Element) entrys.item(e);

					/* 内部属性已经修改为<entry key="1" value="个人发布者" /> */
					String key = entry.getAttribute(KEY).trim();
					String value = entry.getAttribute(VALUE).trim();

					/* 下列代码对应的解析内容为<entry><key>1</key><value>男</value></entry> */
					// String key =
					// entry.getElementsByTagName(KEY).item(0).getFirstChild().getNodeValue().trim();
					// String value =
					// entry.getElementsByTagName(VALUE).item(0).getFirstChild().getNodeValue().trim();
					map.put(key, value);
				}
				columnList.add(cl);
			}
			tableList.add(tb);
		}
	}

	/**
	 * 方法描述：获取字典表属性 注意：@param key要保持大小写一致 其他两个参数不用进行大小写一致 返回类型：String
	 * 
	 * @param tableName
	 *            表名
	 * @param columnName
	 *            列名
	 * @param key
	 *            数据key 大小写一致
	 * @return
	 */
	public static String getTableColumnKeyVal(String tableName, String columnName, String key) {
		createObj();
		int tbs = tableList.size();
		for (int i = 0; i < tbs; i++) {
			ZdTable table = tableList.get(i);
			if (tableName.toLowerCase().equals(table.getName().toLowerCase())) {// 匹配表名
				Set<ZdColumn> columns = table.getColumns();

				for (ZdColumn zdColumn : columns) {
					if (columnName.toLowerCase().equals(zdColumn.getName().toLowerCase())) {// 匹配列名
						Map<String, String> map = zdColumn.getMap();
						if (map.containsKey(key)) {// 匹配key值
							return map.get(key);
						}
					}
				}
			}
		}
		return "";
	}

	/**
	 * 方法描述：获取该表该列所有内容 ##：用作循环展示 返回类型：Set<Map<String,String>>
	 * 
	 * @param tableName
	 * @param columnName
	 * @return
	 */
	public synchronized static Map<String, String> getTablleColumnEntrys(String tableName,
			String columnName) {
		createObj();
		int tbs = tableList.size();
		for (int i = 0; i < tbs; i++) {
			ZdTable table = tableList.get(i);
			if (tableName.toLowerCase().equals(table.getName().toLowerCase())) {// 匹配表名
				Set<ZdColumn> columns = table.getColumns();

				for (ZdColumn zdColumn : columns) {
					if (columnName.toLowerCase().equals(
							zdColumn.getName().toLowerCase())) {// 匹配列名
						return zdColumn.getMap();// 返回该列所map集合
					}
				}
			}
		}
		return null;
	}

	/**
	 * 方法描述：实例化 返回类型：void
	 */ 
	private static void createObj() {
		if (tableList == null) {
			synchronized (ZdGetValue.class) {
				if (tableList == null) {
					setTableList();
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Map<String, String> map = getTablleColumnEntrys("USERS", "UTYPE");
		Set<Entry<String, String>> set = map.entrySet();
		for (Entry<String, String> entry : set) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		System.out.println(tableList);
		/*System.out.println(ZdGetValue.class.getClassLoader().getResource("zdb.xml"));
		System.out.println(Resources.getResourceURL("zdb.xml"));*/
	}
}
