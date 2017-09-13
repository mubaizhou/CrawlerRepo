package com.upay.aso.crawler.pipeline;

import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.upay.aso.crawler.entity.RankInfo;
import com.upay.aso.crawler.service.RankInfoService;
import com.upay.aso.crawler.service.impl.RankInfoServiceImpl;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author muyk@upay360.com
 * @date 2017年9月6日 下午5:42:53
 * @description 用于将aso100网站上关键词排名数据持久化到数据库.
 */
public class RankDataPipeline implements Pipeline{
	
	ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	RankInfoService rankInfoService = ac.getBean("rankInfoService",RankInfoService.class);
	
	@Override
	public void process(ResultItems resultItems, Task task) {
		TreeMap<String,String> dataMap = resultItems.get("ASO100_RANK_DATA");
		RankInfo rankInfo = new RankInfo();
		rankInfo.setAppid(resultItems.get("APP_ID").toString().trim());
		rankInfo.setKeyword(resultItems.get("KEYWORD").toString().trim());
		rankInfo.setTaskid(resultItems.get("TASK_ID").toString().trim());
		dataMap.forEach((key,value)->{
			rankInfo.setRanktime(key.trim());
			rankInfo.setRank(value.trim());
			rankInfoService.insertRankData(rankInfo);
		});
		
	}

}
