package com.upay.aso.crawler.commons;

import com.upay.aso.crawler.entity.TaskInfo;

/**
 * @author muyk@upay360.com
 * @date 2017年9月12日 下午2:34:56
 * @description 公共方法提供类。
 */
public class CrawlCommons {
	/**
	 * 
	 * @author:muyk@upay360.com
	 * @date:2017年9月12日
	 * @description：判断爬虫参数。
	 * @param task
	 * @return boolean
	 * @throws Exception
	 * 
	 */
	public static boolean checkCrawlParams(TaskInfo task) throws Exception{
		if(task.getTaskid()==null){
			throw new Exception("TaskId为空。");
		}
		if(task.getAppid()==null || task.getAppid().isEmpty()){
			throw new Exception("TaskId为:"+task.getTaskid()+"的AppId为空。");
		}
		if(task.getKeyword()==null || task.getKeyword().isEmpty()){
			throw new Exception("TaskId为:"+task.getTaskid()+"的Keyword为空。");
		}
		if(task.getKeywordid()==null || task.getKeywordid().isEmpty()){
			throw new Exception("TaskId为:"+task.getTaskid()+",keyWord为:["+task.getKeyword()+"]的KeyWordId为空。");
		}
		return true;
	}
}
