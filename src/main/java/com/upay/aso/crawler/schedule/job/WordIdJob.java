package com.upay.aso.crawler.schedule.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.upay.aso.crawler.commons.CrawlCommons;
import com.upay.aso.crawler.entity.TaskInfo;
import com.upay.aso.crawler.pageprocessor.Aso100WordDicPageProcessor;
import com.upay.aso.crawler.pipeline.KeyWordDicPipeline;
import com.upay.aso.crawler.service.TaskInfoService;

import us.codecraft.webmagic.Spider;

/**
 * @author muyk@upay360.com
 * @date 2017年9月8日 下午4:56:24
 * @description 更新爬虫任务表中keywordId字段值的定时任务Job。
 */
public class WordIdJob implements Job {
	
	private static Logger log = LoggerFactory.getLogger(WordIdJob.class);
	private ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		String appId , keyWord  , taskId;
		
		/*
		 * 从task表中找出需要爬取数据的任务，并进行数据爬取。
		 */
		TaskInfoService taskInfoService = ac.getBean("taskInfoService",TaskInfoService.class);
    	List<TaskInfo> taskList = taskInfoService.getAvailableTask();
    	if(taskList != null){
    		for(TaskInfo task : taskList){
    			log.info("现在的时间为：{}，正在执行的任务ID为：{}",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) , task.getTaskid());
				keyWord = task.getKeyword();
				appId = task.getAppid();
    			taskId = task.getTaskid().toString().trim();
    			try {
					if(CrawlCommons.checkCrawlParams(task)){
						Spider.create(new Aso100WordDicPageProcessor( appId, keyWord, taskId))
		    			  .addUrl("https://aso100.com/app/keyword/appid/"+appId+"/country/cn")
		    			  //.addPipeline(new ConsolePipeline())
		    			  .addPipeline(new KeyWordDicPipeline())
		    			  .thread(1)
		    			  .run();
					}
				} catch (Exception e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
        	}
    	}
	}
	
}
