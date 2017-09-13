package com.upay.aso.crawler.pageprocessor;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

/**
 * @author muyk@upay360.com
 * @date 2017年9月5日 下午4:51:49
 * @description 从ASO100网站上抓取关键词排名数据。
 */
public class Aso100WordRanksPageProcessor implements PageProcessor {
	
	private String appId;
	
	private String keyWord;
	
	private String keyWordId;
	
	private String taskId;
	
	public Aso100WordRanksPageProcessor(){}
	
	public Aso100WordRanksPageProcessor(String appId,String keyWord,String keyWordId, String taskId){
		this.appId = appId;
		this.keyWord = keyWord;
		this.keyWordId = keyWordId;
		this.taskId = taskId;
	}
	
	private Site site = new Site().setRetryTimes(3).setSleepTime(1000)  
            //添加cookie之前一定要先设置主机地址，否则cookie信息不生效  
            .setDomain("aso100.com")  	
            //添加抓包获取的cookie信息  
            .addCookie("fuid","126ef5b08b68ce0d66e5335ad180692e")
            .addCookie("MEIQIA_EXTRA_TRACK_ID","0sbhhnxDxYlJaLVhlI8N7Xis2fg")
            .addCookie("aso_ucenter","f4c4ec1ABrBTQMxD1u7EgLN8rF3N9riwYduvAr4HFM7mPY%2FASHn690U")
            .addCookie("USERINFO","Qag98lrhhAwVnkCeW63Zx87C6a7flOWtYrkz%2B54QWshYgoC3CMxCXBgcm6zP37wOziz61S%2FwI4ZhcTTun9zQWvErjhJ1mkFyFxupeuGg%2F3Fh%2FX%2BRTXxWYO1GlVDDflAi4q7TvY3i9jI%3D")
            .addCookie("aliyungf_tc","AQAAABUMHD/AAQoABTUmal9aO7s4kA1E")
            .addCookie("acw_tc","AQAAACNQiVQAiAwABTUmam96MlB1cMjg")
            .addCookie("PHPSESSID","36dl144umu0l3bt1vd1p4hmci2")
            .addCookie("ASOD","2fiWzqS8qAuN9V9QE3IRLvH7")
            .addCookie("Hm_lvt_0bcb16196dddadaf61c121323a9ec0b6","1503564305,1503891270")
            .addCookie("Hm_lpvt_0bcb16196dddadaf61c121323a9ec0b6","1503905821")
            //添加请求头，有些网站会根据请求头判断该请求是由浏览器发起还是由爬虫发起的  
            .addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3192.0 Safari/537.36")  
            .addHeader("Accept", "application/json, text/javascript, */*; q=0.01")  
            .addHeader("Accept-Encoding", "gzip, deflate, br").addHeader("Accept-Language", "zh-CN,zh;q=0.8")  
            .addHeader("Connection", "keep-alive").addHeader("Referer", "https://aso100.com/app/keyword/appid/"+appId+"/country/cn"); 

	@Override
	public void process(Page page) {
		List<String> data = new JsonPathSelector("$.[*].list."+keyWordId+".data").selectList(page.getRawText());
		Map<String,String> rankTreeMap = new TreeMap<String,String>();
		if(data.size()>0){
			String datas = data.get(0);
     	    JSONArray jsonArray = JSONObject.parseArray(datas);
			for(Object obj : jsonArray){
				if(obj instanceof JSONArray){
					JSONArray oArray=(JSONArray)obj;
					Iterator<Object> iterator = oArray.iterator();
					int indx = 0;
					String key = "";
					String value = "";
					while(iterator.hasNext()){
						if(indx==0){
							key = String.valueOf(iterator.next());
							indx++;
						}else{
							value = String.valueOf(iterator.next());
						}
					}
					rankTreeMap.put(key, value);
				}
			}
			page.putField("ASO100_RANK_DATA", rankTreeMap);
			page.putField("APP_ID", appId);
			page.putField("KEYWORD", keyWord);
			page.putField("TASK_ID", taskId);
		}
		
	}

	@Override
	public Site getSite() {
		return site;
	}
	
}

