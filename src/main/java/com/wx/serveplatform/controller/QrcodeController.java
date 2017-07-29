/**
 * <p>Title: QrcodeController.java</p>
 * <p></p>
 * @author damon
 * @date 2015年5月27日
 * @version 1.0
 */
package com.wx.serveplatform.controller;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wx.serveplatform.model.Qrcode;
import com.wx.serveplatform.service.QrcodeService;

/**
 * <p>Title: QrcodeController</p>
 * <p></p> 
 * @author damon
 * @date 2015年5月27日
 */
@Controller
@RequestMapping("/wxqrcode")
public class QrcodeController {

	@Autowired
	private QrcodeService qrcodeService;
	
	@RequestMapping(value = "/show", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView qrcode_show(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/qrcode/show");
		List<Qrcode> qrcodes = qrcodeService.findAll();
		mav.addObject("qrcodes", qrcodes);
		return mav;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView qrcode_create(@ModelAttribute("model") Qrcode model) {
		ModelAndView mav = new ModelAndView("/qrcode/edit");
		return mav;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String qrcode_save(@ModelAttribute("model") Qrcode model, HttpServletRequest request) {
		try {
			qrcodeService.createAndDownloadQrcode(model, request, "/resources/qrcodes");
		} catch (KeyManagementException | NoSuchAlgorithmException
				| NoSuchProviderException | IOException e) {
			e.printStackTrace();
		}
		return "redirect:/wxqrcode/show";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView qrcode_edit(@ModelAttribute("model") Qrcode model, 
			@PathVariable(value = "id") Integer id) {
		ModelAndView mav = new ModelAndView("/qrcode/edit");
		model = qrcodeService.findById(id);
		mav.addObject("model", model);
		return mav;
	}
	
}
