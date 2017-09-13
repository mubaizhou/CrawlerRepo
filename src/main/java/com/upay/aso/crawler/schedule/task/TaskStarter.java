package com.upay.aso.crawler.schedule.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author muyk@upay360.com
 * @date 2017年9月12日 下午2:48:49
 * @description 定时任务启动入口
 */
public class TaskStarter {
	
	private static Logger log = LoggerFactory.getLogger(AsoWordIdCrawlerTask.class);
	
	public static void main(String[] args) {
		try {
			AsoWordIdCrawlerTask.run();
			AsoRanksCrawlerTask.run();
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}

}
