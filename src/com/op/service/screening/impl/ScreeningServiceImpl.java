package com.op.service.screening.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;



import org.springframework.web.servlet.ModelAndView;

import com.op.dao.BaseDao; 
import com.op.dto.screening.ScreeningDTO;
import com.op.service.screening.ScreeningService;
import com.op.util.SerializationUtil;
import  com.op.util.jedis.RedisUtil;

/** 
 * 筛选条件(screening)接口实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-08-20 13:25:21 
 */  
@Service("screeningServiceImpl")
public class ScreeningServiceImpl implements ScreeningService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
    /**
     * 查询所有筛选条件
     * @return
     * @throws Exception
     */
    @Override
	public List<ScreeningDTO> findAllScreening(String sc_modularType,ModelAndView mv) throws Exception{

		byte[] bs = RedisUtil.get("pointServiceScreening".getBytes());
		List<ScreeningDTO> list = (List<ScreeningDTO>) SerializationUtil.deserialize(bs);
    	
    	if(StringUtils.isEmpty(list)){
    		try {
    			list = (List<ScreeningDTO>) dao.findForList("screeningMapper.findAllScreening", sc_modularType);
				
				RedisUtil.set("pointServiceScreening".getBytes(),SerializationUtil.serialize(list));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	mv.addObject("screeningList", list);
    	return list;
    };
    
}
