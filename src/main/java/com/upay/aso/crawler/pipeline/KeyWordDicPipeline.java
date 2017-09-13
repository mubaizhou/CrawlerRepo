package com.upay.aso.crawler.pipeline;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.upay.aso.crawler.entity.KeyWordDic;
import com.upay.aso.crawler.entity.TaskInfo;
import com.upay.aso.crawler.service.KeyWordDicService;
import com.upay.aso.crawler.service.TaskInfoService;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author muyk@upay360.com
 * @date 2017年9月8日 下午1:28:21
 * @description 用于将aso100网站上关键词及与其对应的关键词ID持久化到数据库.
 */
public class KeyWordDicPipeline implements Pipeline{
	
	ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	TaskInfoService taskInfoService = ac.getBean("taskInfoService",TaskInfoService.class);
	
	@Override
	public void process(ResultItems resultItems, Task task) {
		
		Map<Object,Object> wordDicMap = resultItems.get("KEY_WORD_DIC");
		String appId = resultItems.get("APP_ID");
		String taskId = resultItems.get("TASK_ID");
		
		TaskInfo taskInfo = new TaskInfo();
		taskInfo.setAppid(appId);
		taskInfo.setTaskid(Integer.valueOf(taskId));
		wordDicMap.forEach((key,value)->{
			taskInfo.setKeyword(key.toString().trim());
			taskInfo.setKeywordid(value.toString().trim());
		});
		
		taskInfoService.updateKeywordId(taskInfo);
		
	}

}
