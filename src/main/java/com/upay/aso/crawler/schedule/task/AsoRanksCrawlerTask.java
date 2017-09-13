package com.upay.aso.crawler.schedule.task;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.upay.aso.crawler.schedule.job.RankInfoJob;


/**
 * @author muyk@upay360.com
 * @date 2017年9月11日 上午9:49:09
 * @description ASO100网站关键词排名信息爬虫定时任务入口。
 */
public class AsoRanksCrawlerTask {
	
	private static Logger log = LoggerFactory.getLogger(AsoRanksCrawlerTask.class);
	
	public static void run() throws Exception {
		Scheduler sched = StdSchedulerFactory.getDefaultScheduler();
		JobDetail jobDetail = newJob(RankInfoJob.class).withIdentity("job1", "group1").build();
		//Trigger trigger = newTrigger().withIdentity("trigger1", "group1").withSchedule(cronSchedule("0 0 0/1 * * ?")).build();//每1h执行一次。
		Trigger trigger = newTrigger().withIdentity("trigger1", "group1").withSchedule(cronSchedule("0/20 * * * * ?")).build();//每1h执行一次。
		sched.scheduleJob(jobDetail,trigger);
		if(!sched.isShutdown()){
            sched.start();
            log.info("AsoRanksCrawlerTask 已启动。");
        }
	}
    
    /*public static void main(String[] args) throws Exception {
    	run();
	}*/
}
