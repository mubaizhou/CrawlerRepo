package com.upay.aso.crawler.entity;

import java.io.Serializable;

/**
 * @author muyk@upay360.com
 * @date 2017年9月5日 下午5:11:40
 * @description 排名信息实体类。
 */
public class RankInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private String id;

    /*
	 * 任务ID
	 */
    private String taskid;

    /*
	 * 应用ID
	 */
    private String appid;

    /*
	 * 搜索关键词
	 */
    private String keyword;

    /*
	 * 排名
	 */
    private String rank;

    /*
	 * 与排名对应的时间点
	 */
    private String ranktime;


    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
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

    public String getRanktime() {
        return ranktime;
    }

    public void setRanktime(String ranktime) {
        this.ranktime = ranktime == null ? null : ranktime.trim();
    }

}