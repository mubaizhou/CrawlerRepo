package com.upay.aso.crawler.entity;

import java.io.Serializable;


/**
 * @author muyk@upay360.com
 * @date 2017年9月5日 下午2:39:58
 * @description 【关键词】与【关键词ID】字典
 */
public class KeyWordDic implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    private Integer id;

    /*
	 * 搜索的关键词。
	 */
    private String keyword;

    /*
	 * ASO网站上对关键词给的ID。
	 */
    private String keywordid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

}