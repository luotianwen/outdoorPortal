package com.op.service.emay.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.entity.emay.EmayInfo;
import com.op.service.emay.EmayInfoService;
import com.op.util.Const;
import com.op.util.SerializationUtil;
import  com.op.util.jedis.RedisUtil;


@Service("emayInfoServiceImpl")
public class EmayInfoServiceImpl implements EmayInfoService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	/**
	 * 方法描述：查找所有模板
	 * 实现接口：@see com.op.service.emay.EmayInfoService#emays()
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<EmayInfo> emays() throws Exception {
		// TODO Auto-generated method stub
		return (List<EmayInfo>) dao.findForList("emayInfoMapper.listAllEmayInfo", null);
	}

	/**
	 * 方法描述：获取短信模板
	 * 实现接口：@see com.op.service.emay.EmayInfoService#getEmayTemplate()
	 * @return
	 * @throws Exception
	 */
	@Override
	public String getEmayTemplate(String type) throws Exception {

    	// 获取
		byte[] bs = RedisUtil.get(Const.EMAY_TEMPLATE.getBytes());
		Map<String,String> map = (Map<String,String>) SerializationUtil.deserialize(bs);
		// 如果缓存不存在，加载缓存
		if(map == null || map.get(type) == null){
			return initEmailTemplate(type);
		}
		
		return map.get(type);
	}


	/**
	 * 方法描述：初始化短信模板
	 * 实现接口：@see com.op.service.emay.EmayInfoService#initEmailTemplate()
	 * @throws Exception
	 */
	@Override
	public String initEmailTemplate(String type) throws Exception {
		List<EmayInfo> list = (List<EmayInfo>) dao.findForList("emayInfoMapper.listAllEmayInfo");
		Map<String,String> map = new HashMap<String,String>();
		for (EmayInfo e : list) {
			map.put(e.getEl_type(), e.getEl_value());
		}
		// 同步缓存

  	    RedisUtil.set(Const.EMAY_TEMPLATE.getBytes(),SerializationUtil.serialize(map));
  	    
  	    return map.get(type);
	}
	
}
