package com.upay.aso.crawler.service;

import com.upay.aso.crawler.entity.RankInfo;

/**
 * @author muyk@upay360.com
 * @date 2017年9月7日 上午11:16:11
 * @description 关键词排名信息Service接口。
 */
public interface RankInfoService {

	/**
	 * @author:muyk@upay360.com
	 * @date:2017年9月7日
	 * @description：保存排名信息。
	 * @param rankInfo
	 */
	int insertRankData(RankInfo rankInfo);

}
