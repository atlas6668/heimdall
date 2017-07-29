/**
 * <p>Title: WxMaterialService.java</p>
 * <p></p>
 * @author damon
 * @date 2015年3月23日
 * @version 1.0
 */
package com.wx.serveplatform.service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: WxMaterialService</p>
 * <p></p> 
 * @author damon
 * @date 2015年3月23日
 */
public interface WxMaterialService {

	/**
	 * <p>propertyDownloadDir</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年4月3日
	 * @param request
	 */
	void propertyDownloadDir(HttpServletRequest request, String targetPath);

}
