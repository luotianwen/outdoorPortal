package com.op.util;

import java.util.ArrayList;
import java.util.List;

import com.op.entity.activity.Activity;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUitl{

	public List<Activity> json(String str) {
		
		JSONObject objJson = JSONObject.fromObject("{'u':"+str+"}");		
		JSONArray j = objJson.getJSONArray("u");
		List<Activity> la = new ArrayList<Activity>();
		for(int i = 0;i<j.size();i++){
			Activity a = new Activity();
			JSONObject s = JSONObject.fromObject(j.get(i));
			a.setTitle(s.getString("name"));
			a.setAddress(s.getString("address"));
			a.setCoordinates(s.getString("lat")+","+s.getString("lng"));
			a.setDetails(s.getString("type")+"<br/>"+s.getString("tel")); 
			la.add(a);
		}	
		
		
		return la;
 
	}
 
}
