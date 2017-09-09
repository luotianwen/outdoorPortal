package com.op.spot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.op.spot.entity.Playtheme;

@Service("playthemeService")
public interface PlaythemeService {
	/**
	 * 查询所有游玩主题按分类
	 * @return
	 * @throws Exception
	 */
	public List<Playtheme> playthemeByPidList() throws Exception;
	/**
	 * 查询所有游玩主题
	 * @return
	 * @throws Exception
	 */
	public List<Playtheme> playthemeList() throws Exception;
}
