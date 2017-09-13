package com.upay.aso.crawler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upay.aso.crawler.dao.TaskInfoDao;
import com.upay.aso.crawler.entity.TaskInfo;
import com.upay.aso.crawler.service.TaskInfoService;

/**
 * @author muyk@upay360.com
 * @date 2017年9月11日 下午4:29:38
 * @description 爬虫任务表操作实现类。
 */
@Service("taskInfoService")
public class TaskInfoServiceImpl implements TaskInfoService{
	
	@Autowired
	private TaskInfoDao taskInfoDao;

	@Override
	public List<TaskInfo> getAvailableTask() {
		List<TaskInfo> taskInfoList = taskInfoDao.selectAvailableTasks();
		return taskInfoList;
	}

	@Override
	public int updateKeywordId(TaskInfo taskInfo) {
		return taskInfoDao.updateKeywordId(taskInfo);
	}

}
