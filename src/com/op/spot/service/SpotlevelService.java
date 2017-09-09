package com.op.spot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.op.spot.entity.Spotlevel;

@Service("spotlevelService")
public interface SpotlevelService {
	/**
	 * 查询所有景点级别
	 * @return
	 * @throws Exception
	 */
	public List<Spotlevel> spotlevelList() throws Exception;
	
}
