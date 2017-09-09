package com.op.service.lines.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.entity.lines.SubsectionLines;
import com.op.service.lines.LinesService;
import com.op.util.Const;

/** 
 * 活动线路表(lines)实现类
 * @author 潘永威
 * @version Revision: 1.00 
 *  Date: 2015年12月14日 15:22:35
 */ 
@Service("linesServiceImpl")
public class LinesServiceImpl implements LinesService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	/**
	 * 列表(全部)
	 */
	@Override
	public List<SubsectionLines> selectList() throws Exception {
		return (List<SubsectionLines>) dao.findForList("linesMapper.selectList", null);
	}

	/**
	 * 方法描述：根据id和创建用户删除线路数据
	 * 实现接口：@see com.op.service.lines.LinesService#delete(java.util.Map)
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void delete(Map<String, String> map) throws Exception {
		// 删除线路
		dao.delete("linesMapper.deleteByIdAndUser", map);
		
		// 删除图片
		dao.delete("lineImagesMapper.deleteLineImages", map.get("l_id"));

		map.clear();
		map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
	}
}
