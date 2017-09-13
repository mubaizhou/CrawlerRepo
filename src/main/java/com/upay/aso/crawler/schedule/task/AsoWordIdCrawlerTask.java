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
import com.upay.aso.crawler.schedule.job.WordIdJob;


/**
 * @author muyk@upay360.com
 * @date 2017年9月11日 上午9:49:09
 * @description 更新爬虫任务表中keywordId字段值的定时任务。
 */
public class AsoWordIdCrawlerTask {
	
	private static Logger log = LoggerFactory.getLogger(AsoWordIdCrawlerTask.class);
	
	public static void run() throws Exception {
		Scheduler sched = StdSchedulerFactory.getDefaultScheduler();
		JobDetail jobDetail = newJob(WordIdJob.class).withIdentity("job2", "group2").build();
		//Trigger trigger = newTrigger().withIdentity("trigger2", "group2").withSchedule(cronSchedule("0 0/20 * * * ?")).build();//每20min执行一次。
		Trigger trigger = newTrigger().withIdentity("trigger2", "group2").withSchedule(cronSchedule("0/20 * * * * ?")).build();//每20min执行一次。
		sched.scheduleJob(jobDetail,trigger);
		if(!sched.isShutdown()){
            sched.start();
            log.info("AsoWordIdCrawlerTask 已启动。");
        }
	}
    
    /*public static void main(String[] args) throws Exception {
    	run();
	}*/
}
