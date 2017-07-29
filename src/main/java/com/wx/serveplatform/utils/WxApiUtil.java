/**
 * <p>Title: WxApiUtil.java</p>
 * <p>微信接口</p>
 * @author damon
 * @date 2015年3月16日
 * @version 1.0
 */
package com.wx.serveplatform.utils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wx.middleware.server.util.WeiXinUtil;
import com.wx.serveplatform.common.utils.EhCacheUtil;
import com.wx.serveplatform.common.utils.SpringContextHolder;
import com.wx.serveplatform.model.Account;
import com.wx.serveplatform.repository.sdj.AccountRepository;

/**
 * <p>Title: WxApiUtil</p>
 * <p>微信接口</p> 
 * @author damon
 * @date 2015年3月16日
 */
public class WxApiUtil {
	private final static Logger log = LoggerFactory.getLogger(WxApiUtil.class);
	private static AccountRepository accountRepository = SpringContextHolder.getBean(AccountRepository.class);
	
	/**
	 * 微信缓存 缓存名称
	 */
	public static final String WX_CACHE = "wxCache";
	
	/**
	 * 微信缓存 微信access_token缓存
	 */
	public static final String CACHE_ACCESS_TOKEN_MAP = "accessTokenMap";
	
	/**
	 * 微信缓存 微信JS ticket票据缓存
	 */
	public static final String CACHE_JSTICKET_MAP = "jsTicketMap";
	
	/**
	 * <p>getAccessTokenMap</p>
	 * <p>获取微信access_token缓存</p>
	 * @author damon
	 * @date 2015年3月16日
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getAccessTokenMap(){
		Map<String, String> accessTokenMap = (Map<String, String>) EhCacheUtil.get(WX_CACHE, CACHE_ACCESS_TOKEN_MAP);
		try {
			if(accessTokenMap == null){
				accessTokenMap = new HashMap<String, String>();
				Collection<Account> sets = accountRepository.findAll();
				String accessToken;
				for(Account account : sets){
					accessToken = WeiXinUtil.getAccess_token(account.getAppid(), account.getSecret());
	        		accessTokenMap.put(account.getAppid(), accessToken);
	        		log.info("appid:"+account.getAppid()+",access_token:"+accessToken);
	        	}
				EhCacheUtil.put(WX_CACHE, CACHE_ACCESS_TOKEN_MAP, accessTokenMap);
			}
		} catch (KeyManagementException | NoSuchAlgorithmException
				| NoSuchProviderException | IOException e) {
			log.error(e.getMessage());
		}
		return accessTokenMap;
	}
	
	/**
	 * <p>getJsTicketMap</p>
	 * <p>获取微信JS ticket票据缓存</p>
	 * @author damon
	 * @date 2015年3月16日
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getJsTicketMap(){
		Map<String, String> jsTicketMap = (Map<String, String>) EhCacheUtil.get(WX_CACHE, CACHE_JSTICKET_MAP);
		try {
			if(jsTicketMap == null){
				Map<String, String> accessTokenMap = getAccessTokenMap();
				jsTicketMap = new HashMap<String, String>();
				Collection<Account> sets = accountRepository.findAll();
				String accessToken;
				String ticket;
	        	for(Account account : sets){
	        		accessToken = accessTokenMap.get(account.getAppid());
					ticket = WeiXinUtil.getTicket(accessToken);
	        		jsTicketMap.put(account.getAppid(), ticket);
	        		log.info("appid:"+account.getAppid()+",ticket:"+ticket);
	        	}
				EhCacheUtil.put(WX_CACHE, CACHE_JSTICKET_MAP, jsTicketMap);
			}
		} catch (KeyManagementException | NoSuchAlgorithmException
				| NoSuchProviderException | IOException e) {
			log.error(e.getMessage());
		}
		return jsTicketMap;
	}
	
	/**
	 * <p>timerScheduleTask</p>
	 * <p>定时刷新微信缓存任务</p>
	 * @author damon
	 * @date 2015年3月16日
	 */
	public static void timerScheduleTask() {
		java.util.Timer timer = new java.util.Timer(false);  
		// 设定任务
        java.util.TimerTask task = new java.util.TimerTask(){  
            @Override  
            public void run() {
            	// 在这里编辑定时任务
            	updateCache();
            }             
        };
        // 延迟执行 秒 --> 分钟 --> 小时 --> 天
        long delay = 0;
        // 执行周期 秒 --> 分钟 --> 小时 --> 天
        // 根据微信票据失效时间，这里约定2小时
        long period = 1000 * 60 * 60 * 2;
        // 任务 延迟执行时间 任务执行时间间隔
        timer.schedule(task, delay, period);
	}
	
	public static void updateCache() {
		Collection<Account> sets = accountRepository.findAll();
    	Map<String, String> jsTicketMap = getJsTicketMap();
    	Map<String, String> accessTokenMap = getAccessTokenMap();
    	String accessToken;
		String ticket;
		try {
        	for(Account account : sets){
				accessToken = WeiXinUtil.getAccess_token(account.getAppid(), account.getSecret());
        		accessTokenMap.put(account.getAppid(), accessToken);
        		log.info("appid:"+account.getAppid()+",access_token:"+accessToken);
        		ticket = WeiXinUtil.getTicket(accessToken);
        		jsTicketMap.put(account.getAppid(), ticket);
        		log.info("appid:"+account.getAppid()+",ticket:"+ticket);
        	}
		} catch (KeyManagementException | NoSuchAlgorithmException
				| NoSuchProviderException | IOException e) {
			log.error(e.getMessage());
		}
    	EhCacheUtil.put(WX_CACHE, CACHE_ACCESS_TOKEN_MAP, accessTokenMap);
    	EhCacheUtil.put(WX_CACHE, CACHE_JSTICKET_MAP, jsTicketMap);
    	log.info(System.currentTimeMillis()+" : 缓存自动更新完毕！");
	}
	
}
