/**
 * <p>Title: WxMaterialUtil.java</p>
 * <p></p>
 * @author damon
 * @date 2015年3月23日
 * @version 1.0
 */
package com.wx.serveplatform.utils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wx.middleware.server.bean.material.NewsItem;
import com.wx.middleware.server.bean.material.NewsItemContentItem;
import com.wx.middleware.server.bean.material.OthersItem;
import com.wx.middleware.server.util.WeiXinUtil;
import com.wx.serveplatform.common.utils.EhCacheUtil;
import com.wx.serveplatform.common.utils.SpringContextHolder;
import com.wx.serveplatform.model.AutoResponse;
import com.wx.serveplatform.repository.sdj.AutoResponseRepository;

/**
 * <p>Title: WxMaterialUtil</p>
 * <p></p> 
 * @author damon
 * @date 2015年3月23日
 */
public class WxMaterialUtil {
	
	private final static Logger log = LoggerFactory.getLogger(WxMaterialUtil.class);
	private static AutoResponseRepository autoResponseRepository = SpringContextHolder.getBean(AutoResponseRepository.class);
	
	// about 操作系统
	public static final String os = System.getProperty("os.name");
	public static final String windows_sep = "\\";
	public static final String linux_sep = "/";

	public static final String WX_MATERIAL_CACHE = "wxMaterialCache";
	
	public static final String CACHE_WX_IMAGE_LIST = "wxImageList";
	
	public static final String CACHE_WX_VOICE_LIST = "wxVoiceList";
	
	public static final String CACHE_WX_VIDEO_LIST = "wxVideoList";
	
	public static final String CACHE_WX_NEWS_LIST = "wxNewsList";
	
	public static final String CACHE_KEYWORD_MAP = "keywordMap";
	
	public static final String CACHE_SUBSCRIBE = "subscribe";
	
	public static final String CACHE_MESSAGE = "message";
	
	private static final int pageSize = 20;
	
	/**
	 * <p>getWxImageList</p>
	 * <p>获取缓存的微信图片</p>
	 * @author damon
	 * @date 2015年3月23日
	 * @param appId
	 * @return
	 */
	public static List<OthersItem> getWxImageList(String appId){
		// 操作系统适配 解决下载路径问题
		String sep = linux_sep;
		if(os != null && os.startsWith("Windows")) {
			sep = windows_sep;
		}
		try {
			Map<String, String> accessTokenMap = WxApiUtil.getAccessTokenMap();
			String access_token = accessTokenMap.get(appId);
			@SuppressWarnings("unchecked")
			List<OthersItem> imagelist = (List<OthersItem>) EhCacheUtil.get(WX_MATERIAL_CACHE, CACHE_WX_IMAGE_LIST+appId);
			int totalCount;
				totalCount = WxUtil.get_materialcount_image(access_token);
			int listSize = 0;
			if(imagelist != null){
				listSize = imagelist.size();
			} else {
				imagelist = new ArrayList<OthersItem>();
			}
			// 更新缓存
			if(listSize < totalCount){
				// 清空
				imagelist.clear();
				int offset = 0;
				int count = pageSize;
				List<OthersItem> items;
				while(offset < totalCount){
					items = WxUtil.batchget_material_image(access_token, offset, count);
					for(OthersItem item : items){
						// 下载资源
						WeiXinUtil.get_material(access_token, item.getMedia_id(), System.getProperty("wx_material_localpath")+sep+"images"+sep+item.getName());
						// 装填地址
						item.setImage_localurl("/resources/wxmaterial/images/"+item.getName());
						imagelist.add(item);
					}
					offset += count;
				}
				EhCacheUtil.put(WX_MATERIAL_CACHE, CACHE_WX_IMAGE_LIST+appId, imagelist);
			}
			return imagelist;
		} catch (KeyManagementException | NoSuchAlgorithmException
				| NoSuchProviderException | IOException e) {
			log.error(e.getMessage());
			return null;
		}
	}
	
	/**
	 * <p>getWxVoiceList</p>
	 * <p>获取缓存的微信音频</p>
	 * @author damon
	 * @date 2015年3月23日
	 * @param appId
	 * @return
	 */
	public static List<OthersItem> getWxVoiceList(String appId){
		// 操作系统适配 解决下载路径问题
		String sep = linux_sep;
		if(os != null && os.startsWith("Windows")) {
			sep = windows_sep;
		}
		try {
			Map<String, String> accessTokenMap = WxApiUtil.getAccessTokenMap();
			String access_token = accessTokenMap.get(appId);
			@SuppressWarnings("unchecked")
			List<OthersItem> voicelist = (List<OthersItem>) EhCacheUtil.get(WX_MATERIAL_CACHE, CACHE_WX_VOICE_LIST+appId);
			int totalCount = WxUtil.get_materialcount_voice(access_token);
			int listSize = 0;
			if(voicelist != null){
				listSize = voicelist.size();
			} else {
				voicelist = new ArrayList<OthersItem>();
			}
			// 更新缓存
			if(listSize < totalCount){
				// 清空
				voicelist.clear();
				int offset = 0;
				int count = pageSize;
				List<OthersItem> items;
				while(offset < totalCount){
					items = WxUtil.batchget_material_voice(access_token, offset, count);
					for(OthersItem item : items){
						// 下载资源
						WeiXinUtil.get_material(access_token, item.getMedia_id(), System.getProperty("wx_material_localpath")+sep+"voices"+sep+item.getName());
						// 装填地址
						item.setImage_localurl("/resources/wxmaterial/voices/"+item.getName());
						voicelist.add(item);
					}
					offset += count;
				}
				EhCacheUtil.put(WX_MATERIAL_CACHE, CACHE_WX_VOICE_LIST+appId, voicelist);
			}
			return voicelist;
		} catch (KeyManagementException | NoSuchAlgorithmException
				| NoSuchProviderException | IOException e) {
			log.error(e.getMessage());
			return null;
		}
	}
	
	/**
	 * <p>getWxVideoList</p>
	 * <p>获取缓存的微信视频</p>
	 * @author damon
	 * @date 2015年3月23日
	 * @param appId
	 * @return
	 */
	public static List<OthersItem> getWxVideoList(String appId){
		// 操作系统适配 解决下载路径问题
		String sep = linux_sep;
		if(os != null && os.startsWith("Windows")) {
			sep = windows_sep;
		}
		try {
			Map<String, String> accessTokenMap = WxApiUtil.getAccessTokenMap();
			String access_token = accessTokenMap.get(appId);
			@SuppressWarnings("unchecked")
			List<OthersItem> videolist = (List<OthersItem>) EhCacheUtil.get(WX_MATERIAL_CACHE, CACHE_WX_VIDEO_LIST+appId);
			int totalCount = WxUtil.get_materialcount_video(access_token);
			int listSize = 0;
			if(videolist != null){
				listSize = videolist.size();
			} else {
				videolist = new ArrayList<OthersItem>();
			}
			// 更新缓存
			if(listSize < totalCount){
				// 清空
				videolist.clear();
				int offset = 0;
				int count = pageSize;
				List<OthersItem> items;
				while(offset < totalCount){
					items = WxUtil.batchget_material_video(access_token, offset, count);
					for(OthersItem item : items){
						// 下载资源
						WeiXinUtil.get_material(access_token, item.getMedia_id(), System.getProperty("wx_material_localpath")+sep+"videos"+sep+item.getName());
						// 装填地址
						item.setImage_localurl("/resources/wxmaterial/videos/"+item.getName());
						videolist.add(item);
					}
					offset += count;
				}
				EhCacheUtil.put(WX_MATERIAL_CACHE, CACHE_WX_VIDEO_LIST+appId, videolist);
			}
			return videolist;
		} catch (KeyManagementException | NoSuchAlgorithmException
				| NoSuchProviderException | IOException e) {
			log.error(e.getMessage());
			return null;
		}
	}

	/**
	 * <p>getWxNewsList</p>
	 * <p>获取缓存的微信图文</p>
	 * @author damon
	 * @date 2015年3月23日
	 * @param appId
	 * @return
	 */
	public static List<NewsItem> getWxNewsList(String appId){
		// 操作系统适配 解决下载路径问题
		String sep = linux_sep;
		if(os != null && os.startsWith("Windows")) {
			sep = windows_sep;
		}
		try {
			Map<String, String> accessTokenMap = WxApiUtil.getAccessTokenMap();
			String access_token = accessTokenMap.get(appId);
			@SuppressWarnings("unchecked")
			List<NewsItem> newslist = (List<NewsItem>) EhCacheUtil.get(WX_MATERIAL_CACHE, CACHE_WX_NEWS_LIST+appId);
			int totalCount = WxUtil.get_materialcount_news(access_token);
			int listSize = 0;
			if(newslist != null){
				listSize = newslist.size();
			} else {
				newslist = new ArrayList<NewsItem>();
			}
			// 更新缓存
			if(listSize < totalCount){
				// 清空
				newslist.clear();
				int offset = 0;
				int count = pageSize;
				List<NewsItem> items;
				String fileName;
				while(offset < totalCount){
					items = WxUtil.batchget_material_news(access_token, offset, count);
					for(NewsItem item : items){
						for(NewsItemContentItem newsItemContentItem : item.getContent().getNews_item()){
							// 下载资源
							fileName = System.currentTimeMillis()+".jpg";
							WeiXinUtil.get_material(access_token, newsItemContentItem.getThumb_media_id(), System.getProperty("wx_material_localpath")+sep+"news"+sep+fileName);
							// 装填地址
							newsItemContentItem.setImage_localurl("/resources/wxmaterial/news/"+fileName);
						}
						newslist.add(item);
					}
					offset += count;
				}
				EhCacheUtil.put(WX_MATERIAL_CACHE, CACHE_WX_NEWS_LIST+appId, newslist);
			}
			return newslist;
		} catch (KeyManagementException | NoSuchAlgorithmException
				| NoSuchProviderException | IOException e) {
			log.error(e.getMessage());
			return null;
		}
	}
	
	public static void cacheSubscribe() {
		List<AutoResponse> subscribelist = autoResponseRepository.findByCategory(AutoResponse.SUBSCRIBE);
		if(subscribelist != null && !subscribelist.isEmpty()){
			AutoResponse subscribe = subscribelist.get(0);
			EhCacheUtil.put(WxMaterialUtil.WX_MATERIAL_CACHE, WxMaterialUtil.CACHE_SUBSCRIBE, subscribe.getContent());
		}
	}
	
	public static void cacheMessage() {
		List<AutoResponse> messagelist = autoResponseRepository.findByCategory(AutoResponse.MESSAGE);
		if(messagelist != null && !messagelist.isEmpty()){
			AutoResponse message = messagelist.get(0);
			EhCacheUtil.put(WxMaterialUtil.WX_MATERIAL_CACHE, WxMaterialUtil.CACHE_MESSAGE, message.getContent());
		}
	}
	
	public static Map<String, Object> cacheKeywordMap() {
		@SuppressWarnings("unchecked")
		Map<String, Object> keywordmap = (Map<String, Object>) EhCacheUtil.get(WX_MATERIAL_CACHE, CACHE_KEYWORD_MAP);
		if(keywordmap == null){
			keywordmap = new HashMap<String, Object>();
		}
		List<AutoResponse> autoResponses = autoResponseRepository.findByCategory(AutoResponse.KEYWORD);
		for(AutoResponse autoResponse : autoResponses){
			keywordmap.put(autoResponse.getKeyword(), autoResponse);
		}
		EhCacheUtil.put(WX_MATERIAL_CACHE, CACHE_KEYWORD_MAP, keywordmap);
		return keywordmap;
	}
	
}
