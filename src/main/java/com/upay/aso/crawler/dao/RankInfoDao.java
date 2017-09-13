package com.upay.aso.crawler.dao;


import org.springframework.stereotype.Repository;

import com.upay.aso.crawler.entity.RankInfo;

/**
 * @author muyk@upay360.com
 * @date 2017年9月8日 下午1:58:15
 * @description 排名信息Mapper映射器。
 */
@Repository("rankInfoDao")
public interface RankInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RankInfo record);

    int replaceIntoSelective(RankInfo record);

    RankInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RankInfo record);

    int updateByPrimaryKey(RankInfo record);
}