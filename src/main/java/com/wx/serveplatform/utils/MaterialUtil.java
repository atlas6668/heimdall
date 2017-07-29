/**
 * <p>Title: MaterialUtil.java</p>
 * <p></p>
 * @author damon
 * @date 2015年3月12日
 * @version 1.0
 */
package com.wx.serveplatform.utils;

import java.util.List;

import com.wx.serveplatform.common.utils.EhCacheUtil;
import com.wx.serveplatform.common.utils.SpringContextHolder;
import com.wx.serveplatform.model.Photo;
import com.wx.serveplatform.repository.sdj.PhotoRepository;

/**
 * <p>Title: MaterialUtil</p>
 * <p></p> 
 * @author damon
 * @date 2015年3月12日
 */
public class MaterialUtil {

	private static PhotoRepository photoRepository = SpringContextHolder.getBean(PhotoRepository.class);
	
	public static final String MATERIAL_CACHE = "materialCache";
	
	public static final String CACHE_PHOTO_LIST = "photoList";
	
	public static final String CACHE_NEWS_LIST = "newsList";
	
	public static final String CACHE_KEYWORD_LIST = "keywordList";
	
	@SuppressWarnings("unchecked")
	public static List<Photo> getPhotoList(){
		List<Photo> photolist = (List<Photo>) EhCacheUtil.get(MATERIAL_CACHE, CACHE_PHOTO_LIST);
		if(photolist == null){
			photolist = (List<Photo>) photoRepository.findAll();
			EhCacheUtil.put(MATERIAL_CACHE, CACHE_PHOTO_LIST, photolist);
		}
		return photolist;
	}
	
	public static void getNewsList(){
		
		
	}
	
	public static void getKeywordList(){
		
		
	}
	
}
