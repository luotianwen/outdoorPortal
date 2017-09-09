package com.op.service.keyword;

public interface KeywordService {

	/**
	 * 记录关键字
	 */
	void record(String keyword);
	
	/**
	 * 保存热词(关键字)
	 */
	void saveHotWord();
}
