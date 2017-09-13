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
import com.upay.aso.crawler.pageprocessor.Aso100WordRanksPageProcessor;
import com.upay.aso.crawler.pipeline.RankDataPipeline;
import com.upay.aso.crawler.service.TaskInfoService;

import us.codecraft.webmagic.Spider;

/**
 * @author muyk@upay360.com
 * @date 2017年9月8日 下午4:56:24
 * @description ASO100网站关键词排名信息爬虫操作。
 */
public class RankInfoJob implements Job {
	
	private static Logger log = LoggerFactory.getLogger(RankInfoJob.class);
	private ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String appId , keyWord , keyWordId , taskId;
		
		/*
		 * 从task表中找出需要爬取数据的任务，并进行数据爬取。
		 */
		TaskInfoService taskInfoService = ac.getBean("taskInfoService",TaskInfoService.class);
    	List<TaskInfo> taskList = taskInfoService.getAvailableTask();
    	if(taskList != null){
    		for(TaskInfo task : taskList){
    			log.info("现在的时间为：{}，正在执行的任务ID为：{}",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) , task.getTaskid());
    			keyWord = task.getKeyword();
    			keyWordId = task.getKeywordid();
				appId = task.getAppid();
    			taskId = task.getTaskid().toString().trim();
    			try {
					//keyWordId = getKeywordIdByKeyword(keyWord);
	    			if(CrawlCommons.checkCrawlParams(task)){
	    				Spider.create(new Aso100WordRanksPageProcessor(appId,keyWord,keyWordId,taskId))
		  			  		  .addUrl("https://aso100.com/app/keywordHistory/appid/"+appId+"?device=iphone&country=cn&sdate="+date+"&edate="+date+"&word="+keyWord+"&word_id="+keyWordId)
			  			      //.addPipeline(new ConsolePipeline())
		  			  		  .addPipeline(new RankDataPipeline())
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
	
	/**
	 * @author:muyk@upay360.com
	 * @date:2017年9月12日
	 * @description：根据keyword得到keywordID。
	 * @param Keyword
	 * @return String
	 * @throws Exception
	 * 
	 */
	/*public static String getKeywordIdByKeyword(String Keyword) throws Exception{
		KeyWordDicService keyWordDicService = ac.getBean("keyWordDicService",KeyWordDicService.class);
		String keywordId = keyWordDicService.getKeywordId(Keyword);
		if( keywordId==null || keywordId.isEmpty()){
			throw new Exception("keyWord:"+Keyword+" 的KeyWordId为空。");
		}
		return keywordId;
	}*/
	
	

}
