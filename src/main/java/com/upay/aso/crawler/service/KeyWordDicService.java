package com.upay.aso.crawler.service;

import com.upay.aso.crawler.entity.KeyWordDic;

/**
 * @author muyk@upay360.com
 * @date 2017年9月8日 上午11:39:46
 * @description 关键词及关键词ID相关操作。
 */
public interface KeyWordDicService {

	/**
	 * @author:muyk@upay360.com
	 * @date:2017年9月8日
	 * @description：将关键词及关键词ID存入表中。
	 * @param keyWordDic
	 * 
	 */
	int insert(KeyWordDic keyWordDic);

	/**
	 * @author:muyk@upay360.com
	 * @date:2017年9月11日
	 * @description：
	 * @param keyword
	 * @return
	 * String
	 */
	String getKeywordId(String keyword);

}
