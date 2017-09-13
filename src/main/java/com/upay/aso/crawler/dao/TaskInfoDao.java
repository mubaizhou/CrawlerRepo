package com.upay.aso.crawler.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.upay.aso.crawler.entity.TaskInfo;
/**
 * 
 * @author muyk@upay360.com
 * @date 2017年9月11日 下午4:08:54
 * @description
 */
@Repository("taskInfoDao")
public interface TaskInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TaskInfo record);

    int insertSelective(TaskInfo record);

    TaskInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TaskInfo record);

    int updateByPrimaryKey(TaskInfo record);

	/**
	 * @author:muyk@upay360.com
	 * @date:2017年9月11日
	 * @description：
	 * @return List<TaskInfo>
	 * 
	 */
	List<TaskInfo> selectAvailableTasks();

	/**
	 * @author:muyk@upay360.com
	 * @date:2017年9月12日
	 * @description：
	 * @param taskInfo
	 * @return int
	 * 
	 */
	int updateKeywordId(TaskInfo taskInfo);
}