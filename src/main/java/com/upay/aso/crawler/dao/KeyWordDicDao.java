package com.upay.aso.crawler.dao;

import org.springframework.stereotype.Repository;

import com.upay.aso.crawler.entity.KeyWordDic;

/**
 * @author muyk@upay360.com
 * @date 2017年9月8日 下午1:58:24
 * @description 关键词及关键词ID信息操作Mapper映射器。
 */
@Repository("keyWordDicDao")
public interface KeyWordDicDao {
    int deleteByPrimaryKey(Integer id);

    int insert(KeyWordDic record);

    int insertSelective(KeyWordDic record);

    KeyWordDic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(KeyWordDic record);

    int updateByPrimaryKey(KeyWordDic record);

	/**
	 * @author:muyk@upay360.com
	 * @date:2017年9月11日
	 * @description：
	 * @param keyword
	 * @return
	 * String
	 */
	String selectKeywordIdByKeyword(String keyword);
}