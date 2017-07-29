/**
 * <p>Title: WxUtil.java</p>
 * <p></p>
 * @author damon
 * @date 2015年3月20日
 * @version 1.0
 */
package com.wx.serveplatform.utils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;

import com.wx.middleware.server.bean.material.MaterialNews;
import com.wx.middleware.server.bean.material.MaterialOthers;
import com.wx.middleware.server.bean.material.NewsItem;
import com.wx.middleware.server.bean.material.OthersItem;
import com.wx.middleware.server.enums.WxMaterialType;
import com.wx.middleware.server.util.WeiXinUtil;

/**
 * <p>Title: WxUtil</p>
 * <p></p> 
 * @author damon
 * @date 2015年3月20日
 */
public class WxUtil {
	
    /**
     * <p>batchget_material_news</p>
     * <p>获取指定offset起的count条图文</p>
     * @author damon
     * @date 2015年3月23日
     * @param access_token
     * @param offset
     * @param count
     * @return
     * @throws IOException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
    public static List<NewsItem> batchget_material_news(String access_token, 
    		int offset, int count) throws KeyManagementException, 
    		NoSuchAlgorithmException, NoSuchProviderException, IOException {
    	List<NewsItem> itemlist = new ArrayList<NewsItem>();
    	MaterialNews mn = WeiXinUtil.batchget_material_news(access_token, offset, count);
    	NewsItem[] items = mn.getItem();
    	for(NewsItem item : items){
    		itemlist.add(item);
    	}
    	return itemlist;
    }
    
    /**
     * <p>batchget_material_image</p>
     * <p>获取微信素材库 图片素材</p>
     * @author damon
     * @date 2015年3月23日
     * @param access_token
     * @param offset
     * @param count
     * @return
     * @throws IOException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
    public static List<OthersItem> batchget_material_image(String access_token, 
    		int offset, int count) throws KeyManagementException, 
    		NoSuchAlgorithmException, NoSuchProviderException, IOException {
    	return batchget_material_others(access_token, WxMaterialType.IMAGE, offset, count);
    }
    
    /**
     * <p>batchget_material_voice</p>
     * <p>获取微信素材库 音频素材</p>
     * @author damon
     * @date 2015年3月23日
     * @param access_token
     * @param offset
     * @param count
     * @return
     * @throws IOException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
    public static List<OthersItem> batchget_material_voice(String access_token, 
    		int offset, int count) throws KeyManagementException, 
    		NoSuchAlgorithmException, NoSuchProviderException, IOException {
    	return batchget_material_others(access_token, WxMaterialType.VOICE, offset, count);
    }
    
    /**
     * <p>batchget_material_video</p>
     * <p>获取微信素材库 视频素材</p>
     * @author damon
     * @date 2015年3月23日
     * @param access_token
     * @param offset
     * @param count
     * @return
     * @throws IOException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
    public static List<OthersItem> batchget_material_video(String access_token, 
    		int offset, int count) throws KeyManagementException, 
    		NoSuchAlgorithmException, NoSuchProviderException, IOException {
    	return batchget_material_others(access_token, WxMaterialType.VIDEO, offset, count);
    }
    

    /**
     * <p>batchget_material_others</p>
     * <p>获取指定offset起的count条其他类型（图片、语音、视频）</p>
     * @author damon
     * @date 2015年3月23日
     * @param access_token
     * @param type
     * @param offset
     * @param count
     * @return
     * @throws IOException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
    private static List<OthersItem> batchget_material_others(String access_token, 
    		WxMaterialType type, int offset, int count) throws KeyManagementException, 
    		NoSuchAlgorithmException, NoSuchProviderException, IOException {
    	List<OthersItem> itemlist = new ArrayList<OthersItem>();
		MaterialOthers mo = WeiXinUtil.batchget_material_others(access_token, type, offset, count);
    	OthersItem[] items = mo.getItem();
    	for(OthersItem item : items){
    		itemlist.add(item);
    	}
    	return itemlist;
    }
    
    public static int get_materialcount_image(String access_token) 
    		throws KeyManagementException, NoSuchAlgorithmException, 
    		NoSuchProviderException, IOException {
    	int totalCount = WeiXinUtil.get_materialcountByType(access_token, WxMaterialType.IMAGE);
    	return totalCount;
    }
    
    public static int get_materialcount_voice(String access_token) 
    		throws KeyManagementException, NoSuchAlgorithmException, 
    		NoSuchProviderException, IOException {
    	int totalCount = WeiXinUtil.get_materialcountByType(access_token, WxMaterialType.VOICE);
    	return totalCount;
    }
    
    public static int get_materialcount_video(String access_token) 
    		throws KeyManagementException, NoSuchAlgorithmException, 
    		NoSuchProviderException, IOException {
    	int totalCount = WeiXinUtil.get_materialcountByType(access_token, WxMaterialType.VIDEO);
    	return totalCount;
    }
    
    public static int get_materialcount_news(String access_token) 
    		throws KeyManagementException, NoSuchAlgorithmException, 
    		NoSuchProviderException, IOException {
    	int totalCount = WeiXinUtil.get_materialcountByType(access_token, WxMaterialType.NEWS);
    	return totalCount;
    }

    public static void main(String[] args) {
    	String access_token = "92IIcK5Exf-Sja246zbP9zwjQjubVrZ-sBXb-m3H_V6zWidBoPvS9s2UYB7Rd3TCGLL4H55PvoybFwb0d6by3CtSt_04D9q2Y5hMPRfqclY";
    	String params = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": 1}}}";
    	try {
    		String ticket = WeiXinUtil.createQrcodeAndGetTicket(access_token, params);
    		System.out.println(ticket);
		} catch (KeyManagementException | NoSuchAlgorithmException
				| NoSuchProviderException | IOException e) {
			e.printStackTrace();
		}
    	
    	
//    	String os = System.getProperty("os.name");  
//        String osUser=System.getProperty("user.name"); 
//        System.out.println(os);
//        System.out.println(osUser);
    }
	
}
