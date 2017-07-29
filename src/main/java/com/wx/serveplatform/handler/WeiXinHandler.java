/**
 * <p>Title: WeiXinHandler.java</p>
 * <p>微信对接处理类</p>
 * @author damon
 * @date 2014年12月19日
 * @version 1.0
 */
package com.wx.serveplatform.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qq.weixin.mp.aes.AesException;
import com.wx.middleware.server.bean.ReceiveXml;
import com.wx.middleware.server.bean.material.NewsItem;
import com.wx.middleware.server.bean.material.NewsItemContentItem;
import com.wx.middleware.server.bean.message.send.News;
import com.wx.middleware.server.bean.message.send.NewsMessage;
import com.wx.middleware.server.bean.message.send.TextMessage;
import com.wx.middleware.server.util.Constants;
import com.wx.middleware.server.util.WeiXinUtil;
import com.wx.serveplatform.common.utils.EhCacheUtil;
import com.wx.serveplatform.model.AutoResponse;
import com.wx.serveplatform.utils.WxMaterialUtil;

/**
 * <p>Title: WeiXinHandler</p>
 * <p>微信对接处理类</p> 
 * @author damon
 * @date 2014年12月19日
 */
public class WeiXinHandler {
	
	/**
     * <p>msgHandler</p>
     * <p>接收微信消息 所有接入消息的处理入口</p>
     * <p>明文、密文兼容明文、密文</p>
     * <p>文本、事件、语音等</p>
     * @author damon
     * @date 2014年12月22日
     * @param msg
	 * @throws IOException 
     * @throws Exception 
     */
	public static String msgHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String result = "";
		ReceiveXml xmlEntity = Msg2ReceiveXml(request);
		if(xmlEntity == null){
			throw new Exception(" 获取ReceiveXml对象异常 ");
		}
		// 接收文本消息
		if(Constants.WX_MESSAGE_TYPE_TEXT.equals(xmlEntity.getMsgType())){
			@SuppressWarnings("unchecked")
			Map<String, Object> keywordmap = (Map<String, Object>) EhCacheUtil.get(WxMaterialUtil.WX_MATERIAL_CACHE, WxMaterialUtil.CACHE_KEYWORD_MAP);
			if(keywordmap.containsKey(xmlEntity.getContent())){
				AutoResponse re = (AutoResponse) keywordmap.get(xmlEntity.getContent());
				// 回复文本消息
				if(re.getMessageType().equals(Constants.WX_MESSAGE_TYPE_TEXT)){
					result = text_response(xmlEntity, re.getContent());
				// 回复图片消息
				} else if(re.getMessageType().equals(Constants.WX_MESSAGE_TYPE_IMAGE)) {
					result = image_response(xmlEntity, re);
				// 回复图文消息
				} else if(re.getMessageType().equals(Constants.WX_MESSAGE_TYPE_NEWS)) {
					result = news_response(xmlEntity, re);
				}
			} else {
				result = defaultResult(xmlEntity);
			}
		// 接收图片消息
		} else if (Constants.WX_MESSAGE_TYPE_IMAGE.equals(xmlEntity.getMsgType())){
			result = defaultResult(xmlEntity);
		// 接收语音消息
		} else if (Constants.WX_MESSAGE_TYPE_VOICE.equals(xmlEntity.getMsgType())){
			result = defaultResult(xmlEntity);
		// 接收视频消息
		} else if (Constants.WX_MESSAGE_TYPE_VIDEO.equals(xmlEntity.getMsgType())){
			result = defaultResult(xmlEntity);
		// 接收图文消息
		} else if (Constants.WX_MESSAGE_TYPE_NEWS.equals(xmlEntity.getMsgType())){
			result = defaultResult(xmlEntity);
		// 接收事件消息
		} else if (Constants.WX_MESSAGE_TYPE_EVENT.equals(xmlEntity.getMsgType())){
			String eventType = xmlEntity.getEvent();
			if(eventType.equals(Constants.WX_MESSAGE_EVENT_TYPE_SUBSCRIBE)) {
				// 关注  
				if(!xmlEntity.getEventKey().equals("") && !xmlEntity.getTicket().equals("")){
					
				} else {
					String content = (String) EhCacheUtil.get(WxMaterialUtil.WX_MATERIAL_CACHE, WxMaterialUtil.CACHE_SUBSCRIBE);
					result = text_response(xmlEntity, content);
				}
			} else if(eventType.equals(Constants.WX_MESSAGE_EVENT_TYPE_UNSUBSCRIBE)) {  
				// 取消关注
			} else if(eventType.equals(Constants.WX_MESSAGE_EVENT_TYPE_SCAN)){
				// 扫码
				
			} else if(eventType.equals(Constants.WX_MESSAGE_EVENT_TYPE_CLICK)) {  
				// 事件KEY值，与创建自定义菜单时指定的KEY值对应  
			} else if(eventType.equals(Constants.WX_MESSAGE_EVENT_TYPE_VIEW)) {
				// 页面跳转
			} 
		} else {
			result = defaultResult(xmlEntity);
		}
		return result;
	}
    
	/**
     * <p>Msg2ReceiveXml</p>
     * <p>解析微信端发送过来的数据 封装到ReceiveXml对象</p>
     * @author damon
     * @date 2014年12月23日
     * @param request
     * @return
     * @throws Exception
     */
    public static ReceiveXml Msg2ReceiveXml(HttpServletRequest request) throws Exception{
    	ReceiveXml xmlEntity = null;
    	// 接收微信端发送过来的数据  
        String msg = WeiXinUtil.ReceivedMsg(request.getInputStream()); 
		
        String encryptType = request.getParameter("encrypt_type");
        String msgSignature = request.getParameter("msg_signature"); 
		String timestamp = request.getParameter("timestamp");  
    	String nonce = request.getParameter("nonce"); 
        if ((timestamp != null && timestamp.length() > 1) && 
        		(nonce != null && nonce.length() > 1) && 
        		(msgSignature != null && msgSignature.length() > 1)) {  
            
        	// 解析微信端发送过来的数据 封装到ReceiveXml对象
        	xmlEntity = new ReceiveXmlHandler().getMsgEntity(msg, encryptType, msgSignature, timestamp, nonce); 
        }
        return xmlEntity;
    }
    
    /**
     * <p>defaultResult</p>
     * <p>默认回复</p>
     * @author damon
     * @date 2015年4月7日
     * @param xmlEntity
     * @return
     */
    private static String defaultResult(ReceiveXml xmlEntity) {
		// 默认处理 
		String content = (String) EhCacheUtil.get(WxMaterialUtil.WX_MATERIAL_CACHE, WxMaterialUtil.CACHE_MESSAGE);
		if(content != null && !content.equals("")){
			return text_response(xmlEntity, content);
		}
		return "";
    }
    
    /**
     * <p>text_response</p>
     * <p>回复文本消息</p>
     * @author damon
     * @date 2015年4月7日
     * @param xmlEntity
     * @param content
     * @return
     */
    private static String text_response(ReceiveXml xmlEntity, String content) {
    	try {
	    	TextMessage tm = new TextMessage();
			tm.setToUserName(xmlEntity.getFromUserName());
			tm.setFromUserName(xmlEntity.getToUserName());
			tm.setCreateTime(WeiXinUtil.createTimestamp());
			tm.setMsgType(Constants.WX_MESSAGE_TYPE_TEXT);
			tm.setContent(content);
			return WeiXinUtil.encryptMsg(tm.toString());
    	} catch (AesException e) {
    		System.out.println(e.getMessage());
    	}
    	return "";
    }

	/**
	 * <p>image_response</p>
	 * <p>回复图片消息</p>
	 * @author damon
	 * @date 2015年4月7日
	 * @param xmlEntity
	 * @param re
	 * @return
	 */
	private static String image_response(ReceiveXml xmlEntity, AutoResponse re) {
		// TODO Auto-generated method stub
		return "";
	}
	
	/**
	 * <p>news_response</p>
	 * <p>回复图文消息</p>
	 * @author damon
	 * @date 2015年4月7日
	 * @param xmlEntity
	 * @param re
	 * @return
	 */
	private static String news_response(ReceiveXml xmlEntity, AutoResponse re) {
		try {
			List<News> items = new ArrayList<News>();
			if(re.getContent() != null){
				@SuppressWarnings("unchecked")
				List<NewsItem> newslist = (List<NewsItem>) EhCacheUtil.get(WxMaterialUtil.WX_MATERIAL_CACHE, WxMaterialUtil.CACHE_WX_NEWS_LIST+Constants.APPID);
				News news;
				for(NewsItem newsItem : newslist){
					if(re.getContent().trim().equals("news-"+newsItem.getMedia_id())){
						for(NewsItemContentItem newsItemContentItem : newsItem.getContent().getNews_item()){
							news = new News();
							news.setTitle(newsItemContentItem.getTitle());
							news.setDescription(newsItemContentItem.getDigest());
							news.setPicUrl(newsItemContentItem.getImage_localurl());
							news.setUrl(newsItemContentItem.getContent_source_url());
							items.add(news);
						}
					}
				}
			}
			NewsMessage newsMessage = new NewsMessage(items);
			newsMessage.setToUserName(xmlEntity.getFromUserName());
			newsMessage.setFromUserName(xmlEntity.getToUserName());
			newsMessage.setCreateTime(WeiXinUtil.createTimestamp());
			newsMessage.setMsgType(Constants.WX_MESSAGE_TYPE_NEWS);
			newsMessage.setArticleCount(items.size());
			return WeiXinUtil.encryptMsg(newsMessage.toString());
		} catch (AesException e) {
    		System.out.println(e.getMessage());
    	}
		return "";
	}
	
}
