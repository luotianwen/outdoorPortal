package com.op.dto.mdd;

public class PoiDTO {
	
 	//id
 	 private String id;
 	 //景点id
 	private String sid;
 	//城市目的地id
 	private String did;
 	//国家id
 	private String cid;
	//景点中文名称
 	 private String zh_name;
	//景点英文名称
 	 private String en_name; 
 	 //归属于
 	 private String belongs;
 	 //数据类型  scenic：景点  city：城市   country：国家
 	 private String type;


	 
   /**
   *id
   */ 
	public String getId() {
		switch (type) {
		// 景点 
		case "scenic":// 表scenicSpot
			id = sid;
			break;
		// 城市
		case "city":// 表 destination
			id = did;
			break;
		// 国家
		case "country":// 表 country
			id = cid;
			break;
		}
		return id;
	}
   /**
   *id
   */ 
	public void setId(String id) {
		this.id = id;
	}
   /**
   *景点中文名称
   */ 
	public String getZh_name() {
		return zh_name;
	}
   /**
   *景点中文名称
   */ 
	public void setZh_name(String zh_name) {
		this.zh_name = zh_name;
	}
   /**
   *景点英文名称
   */ 
	public String getEn_name() {
		return en_name;
	}
   /**
   *景点英文名称
   */ 
	public void setEn_name(String en_name) {
		this.en_name = en_name;
	}
	/**
	 * 归属于
	 * @return
	 */
	public String getBelongs() {
		return belongs;
	}
	/**
	 * 归属于
	 * @param belongs
	 */
	public void setBelongs(String belongs) {
		this.belongs = belongs;
	}
	/**
	 * 数据类型
	 * @return
	 */
	public String getType() {
		return type;
	}
	/**
	 * 数据类型
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	
}
