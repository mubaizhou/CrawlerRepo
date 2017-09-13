package com.upay.aso.crawler.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author muyk@upay360.com
 * @date 2017年9月11日 下午4:09:07
 * @description 爬虫任务表实体类。
 */
public class TaskInfo  implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private Integer id;

    private Integer taskid;

    private String appid;

    private String keyword;
    
    private String keywordid;

    private Date ctime;

    private Date starttime;

    private Date endtime;

    private Integer interval;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getKeywordid() {
        return keywordid;
    }

    public void setKeywordid(String keywordid) {
        this.keywordid = keywordid == null ? null : keywordid.trim();
    }
    
    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }
    
}