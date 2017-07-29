/**
 * <p>Title: QrcodeService.java</p>
 * <p></p>
 * @author damon
 * @date 2015年5月27日
 * @version 1.0
 */
package com.wx.serveplatform.service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wx.serveplatform.model.Qrcode;

/**
 * <p>Title: QrcodeService</p>
 * <p></p> 
 * @author damon
 * @date 2015年5月27日
 */
public interface QrcodeService {

	/**
	 * <p>propertyDownloadDir</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年5月27日
	 * @param request
	 * @param targetPath
	 */
	void propertyDownloadDir(HttpServletRequest request, String targetPath);

	/**
	 * <p>findAll</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年5月27日
	 * @return
	 */
	List<Qrcode> findAll();

	/**
	 * <p>createAndDownloadQrcode</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年5月27日
	 * @param request
	 * @param targetDirectory
	 * @return
	 */
	String createAndDownloadQrcode(Qrcode model, HttpServletRequest request, String targetDirectory) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException;

	/**
	 * <p>findById</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年5月27日
	 * @param id
	 * @return
	 */
	Qrcode findById(Integer id);

}
