package com.upay.aso.crawler.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upay.aso.crawler.dao.RankInfoDao;
import com.upay.aso.crawler.entity.RankInfo;
import com.upay.aso.crawler.service.RankInfoService;

/**
 * @author muyk@upay360.com
 * @date 2017年9月7日 上午11:16:52
 * @description 关键词排名信息操作实现类。
 */
@Service("rankInfoService")
public class RankInfoServiceImpl implements RankInfoService{
	
	@Autowired
	private RankInfoDao rankInfoDao;
	
	@Override
	public int insertRankData(RankInfo rankInfo) {
		return rankInfoDao.replaceIntoSelective(rankInfo);
	}

}
