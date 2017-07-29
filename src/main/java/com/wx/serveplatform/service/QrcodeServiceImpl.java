/**
 * <p>Title: QrcodeServiceImpl.java</p>
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
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wx.middleware.server.util.WeiXinUtil;
import com.wx.serveplatform.model.Qrcode;
import com.wx.serveplatform.repository.sdj.QrcodeRepository;
import com.wx.serveplatform.utils.WxApiUtil;

/**
 * <p>Title: QrcodeServiceImpl</p>
 * <p></p> 
 * @author damon
 * @date 2015年5月27日
 */
@Service
@Transactional
public class QrcodeServiceImpl implements QrcodeService {
	// about 操作系统
	public static final String os = System.getProperty("os.name");
	public static final String windows_sep = "\\";
	public static final String linux_sep = "/";
	
	@Autowired
	private QrcodeRepository qrcodeRepository;

	@Override
	public void propertyDownloadDir(HttpServletRequest request,
			String targetPath) {
		@SuppressWarnings("deprecation")
		String realPath = request.getRealPath(targetPath);
		System.setProperty("wx_qrcode_localpath", realPath);
	}

	@Override
	public List<Qrcode> findAll() {
		return (List<Qrcode>) qrcodeRepository.findAll();
	}

	@Override
	public String createAndDownloadQrcode(Qrcode model, HttpServletRequest request,
			String targetDirectory) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
		// 操作系统适配 解决下载路径问题
		String sep = linux_sep;
		if(os != null && os.startsWith("Windows")) {
			sep = windows_sep;
		}
		Map<String, String> accessTokenMap = WxApiUtil.getAccessTokenMap();
		String access_token = accessTokenMap.get(model.getApp());
		@SuppressWarnings("deprecation")
		String realPath = request.getRealPath(targetDirectory);
		// 创建二维码
		StringBuffer sb = new StringBuffer();
		sb.append("{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": ");
		sb.append(model.getWkey()).append("}}}");
		String ticket = WeiXinUtil.createQrcodeAndGetTicket(access_token, sb.toString());
		// 下载二维码
		WeiXinUtil.getQrcode(ticket, realPath+sep+model.getScene()+".jpg");
		// 装填地址
		model.setPath(targetDirectory+"/"+model.getScene()+".jpg");
		model.setLoadtimemills(System.currentTimeMillis());
		qrcodeRepository.save(model);
		return null;
	}

	@Override
	public Qrcode findById(Integer id) {
		return qrcodeRepository.findById(id);
	}

}
