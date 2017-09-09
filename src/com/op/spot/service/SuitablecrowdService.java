package com.op.spot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.op.spot.entity.Suitablecrowd;

@Service("suitablecrowdService")
public interface SuitablecrowdService {
	/**
	 * 查询所有适用人群
	 * @return
	 * @throws Exception
	 */
	public List<Suitablecrowd> suitablecrowdList() throws Exception;
	
}
