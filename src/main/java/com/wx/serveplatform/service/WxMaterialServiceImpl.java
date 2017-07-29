/**
 * <p>Title: WxMaterialServiceImpl.java</p>
 * <p></p>
 * @author damon
 * @date 2015年3月23日
 * @version 1.0
 */
package com.wx.serveplatform.service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

/**
 * <p>Title: WxMaterialServiceImpl</p>
 * <p></p> 
 * @author damon
 * @date 2015年3月23日
 */
@Service
@Transactional
public class WxMaterialServiceImpl implements WxMaterialService {

	@Override
	public void propertyDownloadDir(HttpServletRequest request, String targetDirectory) {
		@SuppressWarnings("deprecation")
		String realPath = request.getRealPath(targetDirectory);
		System.setProperty("wx_material_localpath", realPath);
	}

}
