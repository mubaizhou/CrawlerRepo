package com.upay.aso.crawler.service;

import java.util.List;

import com.upay.aso.crawler.entity.TaskInfo;

/**
 * @author muyk@upay360.com
 * @date 2017年9月11日 下午4:29:07
 * @description 爬虫任务表Service接口。
 */
public interface TaskInfoService {

	/**
	 * @author:muyk@upay360.com
	 * @date:2017年9月11日
	 * @description：得到需要爬取数据的task。
	 * @return List<TaskInfo>
	 */
	List<TaskInfo> getAvailableTask();

	/**
	 * @author:muyk@upay360.com
	 * @date:2017年9月12日
	 * @description：
	 * @param taskInfo
	 */
	int updateKeywordId(TaskInfo taskInfo);

}
