package com.upay.aso.crawler.pageprocessor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.upay.aso.crawler.pipeline.KeyWordDicPipeline;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author muyk@upay360.com
 * @date 2017年8月30日 下午5:45:03
 * @description 从ASO100网站上爬取App相关的搜索【关键词】及【关键词ID】信息形成数据字典。
 */
public class Aso100WordDicPageProcessor implements PageProcessor{
	
	private String appId;
	
	private String keyWord;
	
	private String taskId;
	
	public Aso100WordDicPageProcessor(){}
	
	public Aso100WordDicPageProcessor(String appId,String keyWord, String taskId){
		this.appId = appId;
		this.keyWord = keyWord;
		this.taskId = taskId;
	}
	
	private Site site =  new Site().setRetryTimes(1).setSleepTime(100)  
            //添加cookie之前一定要先设置主机地址，否则cookie信息不生效  
            .setDomain("aso100.com")  	
            //添加抓包获取的cookie信息  
            .addCookie("TY_SESSION_ID","0315d6b1-3c94-47f3-be7e-9743aafa67e9")
            .addCookie("fuid","126ef5b08b68ce0d66e5335ad180692e")
            .addCookie("MEIQIA_EXTRA_TRACK_ID","0sbhhnxDxYlJaLVhlI8N7Xis2fg")
            .addCookie("aso_ucenter","f4c4ec1ABrBTQMxD1u7EgLN8rF3N9riwYduvAr4HFM7mPY%2FASHn690U")
            .addCookie("USERINFO","Qag98lrhhAwVnkCeW63Zx87C6a7flOWtYrkz%2B54QWshYgoC3CMxCXBgcm6zP37wOziz61S%2FwI4ZhcTTun9zQWvErjhJ1mkFyFxupeuGg%2F3Fh%2FX%2BRTXxWYO1GlVDDflAi4q7TvY3i9jI%3D")
            .addCookie("aliyungf_tc","AQAAAGAjI0jpHwsABTUmanb2lIfpcy8B")
            .addCookie("acw_tc","AQAAAD6USUsWfg0ABTUmakJv5Apm3uIl")
            .addCookie("PHPSESSID","49f34r7187bd2u50shcc5lfhm5")
            .addCookie("ASOD","4YP1nfqrjUiXRk96mpG8V2v8")
            .addCookie("Hm_lvt_0bcb16196dddadaf61c121323a9ec0b6","1503564305,1503891270,1503992553")
            .addCookie("Hm_lpvt_0bcb16196dddadaf61c121323a9ec0b6","1503996048")
            //添加请求头，有些网站会根据请求头判断该请求是由浏览器发起还是由爬虫发起的  
            .addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3192.0 Safari/537.36")  
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")  
            .addHeader("Accept-Encoding", "gzip, deflate, br").addHeader("Accept-Language", "zh-CN,zh;q=0.8")  
            .addHeader("Connection", "keep-alive")
            .addHeader("Cache-Control", "max-age=0")
            .addHeader("Host", "aso100.com")
            .addHeader("Upgrade-Insecure-Requests", "1"); 
	
	public void process(Page page) {
		String tableData = page.getHtml().xpath("//*[@id='keyword-list']/script/html()").get();
		page.putField("KEY_WORD_DIC", getKeyWordDicMap(tableData));
		page.putField("APP_ID", appId);
		page.putField("TASK_ID", taskId);
	}

	/**
	 * 从抓取到的表格数据中获取keyword 和 keywordId，并存到Map中。
	 * @param tableData
	 * @return Map<Object,Object>
	 */
	private Map<Object,Object> getKeyWordDicMap(String tableData){
		Map<Object,Object> wordDicMap = new TreeMap<Object,Object>();
		tableData=tableData.substring(tableData.indexOf("["));
		JSONArray jsonArr = (JSONArray) JSONObject.parseArray(tableData);
		if(jsonArr.size()>0){
			for(int i=0 ; i<jsonArr.size(); i++){
				String s = jsonArr.get(i).toString();
				JSONArray jsArr = (JSONArray) JSONObject.parseArray(s);
				Iterator<Object> iterator = jsArr.iterator();
				int index = 0;
				Object keyWord = null;
				Object keyWordId = null;
				while(iterator.hasNext()){
					if(index==0){//取第一个元素（即关键词）
						keyWord = iterator.next();
						index++;
						continue;
					}
					if(index==6){//取第七个元素（即关键词id)
						keyWordId = iterator.next();
						wordDicMap.put(keyWord, keyWordId);
						break;
					}
					iterator.next();
					index++;
				}
			}
		}
		Map<Object,Object> okMap = new HashMap<Object,Object>();//用于过滤，只存放taskid的keyword的keywordId.
		okMap.put(keyWord, wordDicMap.get(keyWord));
		return okMap;
	}
	
	public Site getSite() {
		return site;
	}
	
	/*public static void main(String[] args) {
		Spider.create(new Aso100WordDicPageProcessor())
		.addUrl("https://aso100.com/app/keyword/appid/444934666/country/cn")
		//.addPipeline(new ConsolePipeline())
		.addPipeline(new KeyWordDicPipeline())
		.thread(4)
		.run();
	}*/

}
