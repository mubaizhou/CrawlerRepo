package com.upay.aso.crawler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upay.aso.crawler.dao.KeyWordDicDao;
import com.upay.aso.crawler.entity.KeyWordDic;
import com.upay.aso.crawler.service.KeyWordDicService;

/**
 * @author muyk@upay360.com
 * @date 2017年9月8日 上午11:40:12
 * @description 关键词及关键词ID操作实现类。
 */
@Service("keyWordDicService")
public class KeyWordDicServiceImpl implements KeyWordDicService{
	@Autowired
	private KeyWordDicDao keyWordDicDao;

	@Override
	public int insert(KeyWordDic keyWordDic) {
		return keyWordDicDao.insertSelective(keyWordDic);
	}

	
	@Override
	public String getKeywordId(String Keyword) {
		return keyWordDicDao.selectKeywordIdByKeyword(Keyword);
	}
	
}
