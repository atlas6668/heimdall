/**
 * <p>Title: WeiXinController.java</p>
 * <p>对接微信公众平台</p>
 * @author damon
 * @date 2014年12月18日
 * @version 1.0
 */
package com.wx.serveplatform.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.wx.middleware.server.util.JavaRequestAndResponse;
import com.wx.middleware.server.util.WeiXinUtil;
import com.wx.serveplatform.handler.WeiXinHandler;
import com.wx.serveplatform.utils.WxApiUtil;

/**
 * <p>Title: WeiXinController</p>
 * <p>对接微信公众平台</p> 
 * @author damon
 * @date 2014年12月18日
 */
@Controller
@RequestMapping("/weixin")
public class WeiXinController {
	private final static Logger log = LoggerFactory.getLogger(WeiXinController.class);
	private final static String CHARSET = ";charset=UTF-8";
	
	public WeiXinController() {
		// 启动定时自动更新缓存任务
		WxApiUtil.timerScheduleTask();
	}
	/**
	 * <p>server</p>
	 * <p>接入微信公众服务平台 后台服务</p>
	 * @author damon
	 * @date 2014年12月18日
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody void server(HttpServletRequest request, HttpServletResponse response) {
		try {  
        	request.setCharacterEncoding("UTF-8");  
        	response.setCharacterEncoding("UTF-8");
        	
	        String result = "";  
	        // 判断是否是微信接入激活验证，只有首次接入验证时才会收到echostr参数，此时需要把它直接返回 
	        String echostr = request.getParameter("echostr");  
	        if (echostr != null && echostr.length() > 1) {  
	            result = echostr;  
	        } else {  
	        	// 获取消息处理 响应结果
	        	result = WeiXinHandler.msgHandler(request,response);
	        }  
	        // 响应微信的消息，返回结果
	        JavaRequestAndResponse.response(response.getOutputStream(), result);  
        } catch (Exception e) {  
        	log.error(e.getMessage());
        }  
	}
	
	
	// -- ---------------------------------------------------------------- --//
	// -- 微信平台 功能接口 API
	// -- ---------------------------------------------------------------- --//
	
	/**
	 * <p>getJsTicket</p>
	 * <p>JS接口 获得接入微信JS的验证</p>
	 * @author damon
	 * @date 2015年1月19日
	 * @param callBack
	 * @param appId
	 * @param signUrl
	 * @return
	 */
	@RequestMapping(value = "/api/getJsTicket", method = {RequestMethod.GET,RequestMethod.POST}, 
	produces = MediaType.APPLICATION_JSON_VALUE + CHARSET)
	public @ResponseBody String getJsApiTicket(@RequestParam(value = "callback") String callBack, 
			@RequestParam(value = "appId") String appId, 
			@RequestParam(value = "signUrl") String signUrl) {
		Map<String, String> jsTicketMap = WxApiUtil.getJsTicketMap();
		String ticket = jsTicketMap.get(appId);
		// 取得 权限验证配置各项参数
		JSONObject jsticket = WeiXinUtil.getJsApiTicket(ticket, signUrl);
		log.info("页面("+signUrl+")取得各项接入微信权限验证的配置"+jsticket.toString());
		try {
			if (callBack == null) {
	            return jsticket.toString();
	        } else {
	            ObjectMapper objectMapper = new ObjectMapper();
				return objectMapper.writeValueAsString(new JSONPObject(callBack, jsticket));
	        }
        } catch (Exception e) {
        	log.error(e.getMessage());
        	return "";
        }
	}
	
	/**
	 * <p>getAccessToken</p>
	 * <p>accessToken接口 获取全局缓存的accessToken</p>
	 * @author damon
	 * @date 2015年3月20日
	 * @param appId
	 * @return
	 */
	@RequestMapping(value = "/api/getAccessToken", method = {RequestMethod.GET,RequestMethod.POST}, 
	produces = MediaType.APPLICATION_JSON_VALUE + CHARSET)
	public @ResponseBody String getAccessToken(@RequestParam(value = "appId") String appId) {
		Map<String, String> accessTokenMap = WxApiUtil.getAccessTokenMap();
		String accessToken = accessTokenMap.get(appId);
		Map<String, Object> ret = new HashMap<String, Object>();
		if(accessToken != null){
			ret.put("msg", "ok");
			ret.put("appId", appId);
			ret.put("accessToken", accessToken);
		} else {
			ret.put("msg", "error");
			ret.put("appId", appId);
			ret.put("accessToken", "");
		}
		JSONObject result = new JSONObject(ret);
		return result.toString();
	}
	
	/**
	 * <p>decorateOauth2Url</p>
	 * <p>装饰重定向URL成Oauth@Url</p>
	 * @author damon
	 * @date 2015年6月18日
	 * @param appid
	 * @param redirecturl
	 * @return
	 */
	@RequestMapping(value = "/api/decorateOauth2Url", method = {RequestMethod.GET,RequestMethod.POST}, 
	produces = MediaType.APPLICATION_JSON_VALUE + CHARSET)
	public @ResponseBody String decorateOauth2Url(@RequestParam(value="appid") String appid, 
			@RequestParam(value="redirecturl") String redirecturl) {
		Map<String, Object> ret = new HashMap<String, Object>();
		System.out.println("redirecturl:" + redirecturl);
		try {
			String encodeurl = java.net.URLEncoder.encode(redirecturl, "UTF-8");
			String oauth2url = WeiXinUtil.decorateOauth2Url(appid, encodeurl);
			if(oauth2url != null){
				ret.put("msg", "ok");
				ret.put("appid", appid);
				ret.put("oauth2url", oauth2url);
			} else {
				ret.put("msg", "error");
				ret.put("appid", appid);
				ret.put("oauth2url", "");
			}
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}
		JSONObject result = new JSONObject(ret);
		System.out.println("Oauth2Url:"+result.toString());
		return result.toString();
	}
	
	/**
	 * <p>getOpenIdByOauth2</p>
	 * <p>OpenId接口 通过Oauth2用户授权认证 获取用户OpenId</p>
	 * @author damon
	 * @date 2015年3月17日
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/api/getOpenIdByOauth2", method = {RequestMethod.GET,RequestMethod.POST}, 
	produces = MediaType.APPLICATION_JSON_VALUE + CHARSET)
	public @ResponseBody String getOpenIdByOauth2(@RequestParam(value="code") String code) {
		Map<String, Object> ret = new HashMap<String, Object>();
		String openId;
		try {
			openId = WeiXinUtil.getOpenIdByOauth2(code);
			if(openId != null){
				ret.put("msg", "ok");
				ret.put("code", code);
				ret.put("openId", openId);
			} else {
				ret.put("msg", "error");
				ret.put("code", code);
				ret.put("openId", "");
			}
		} catch (KeyManagementException | NoSuchAlgorithmException
				| NoSuchProviderException | IOException e) {
			log.error(e.getMessage());
		}
		JSONObject result = new JSONObject(ret);
		System.out.println("Oauth2:"+result.toString());
		return result.toString();
	}
}
