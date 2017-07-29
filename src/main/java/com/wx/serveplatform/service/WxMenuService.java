/**
 * <p>Title: WxMenuService.java</p>
 * <p></p>
 * @author damon
 * @date 2015年4月9日
 * @version 1.0
 */
package com.wx.serveplatform.service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;

import com.wx.serveplatform.model.WxMenuButton;

/**
 * <p>Title: WxMenuService</p>
 * <p></p> 
 * @author damon
 * @date 2015年4月9日
 */
public interface WxMenuService {

	/**
	 * <p>findAll</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年4月9日
	 * @return
	 */
	List<WxMenuButton> findAll();

	/**
	 * <p>findById</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年4月9日
	 * @param id
	 * @return
	 */
	WxMenuButton findById(Integer id);

	/**
	 * <p>delete</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年4月9日
	 * @param _ids
	 */
	void delete(String[] _ids);

	/**
	 * <p>save</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年4月9日
	 * @param model
	 * @return
	 */
	WxMenuButton save(WxMenuButton model);

	/**
	 * <p>wxmenu_publish</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年4月9日
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws IOException
	 */
	void wxmenu_publish() throws KeyManagementException, 
		NoSuchAlgorithmException, NoSuchProviderException, IOException;

	/**
	 * <p>findByFatherButton</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年4月10日
	 * @param fid
	 * @return
	 */
	List<WxMenuButton> findByFatherButton(Integer fid);

}
