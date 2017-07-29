/**
 * <p>Title: WxMaterialController.java</p>
 * <p></p>
 * @author damon
 * @date 2015年3月23日
 * @version 1.0
 */
package com.wx.serveplatform.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wx.serveplatform.service.WxMaterialService;

/**
 * <p>Title: WxMaterialController</p>
 * <p></p> 
 * @author damon
 * @date 2015年3月23日
 */
@Controller
@RequestMapping("/wxmaterial")
public class WxMaterialController {

	@Autowired
	private WxMaterialService wxMaterialService;
	
	//-- ---------- ---------- ---------- ---------- ---------- --//
	//-- 						图片库
	//-- ---------- ---------- ---------- ---------- ---------- --//
	@RequestMapping(value = "/images", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView images_show(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/wxmaterial/images/show");
		wxMaterialService.propertyDownloadDir(request, "/resources/wxmaterial");
		mav.addObject("path", "/resources/wxmaterial/images/");
		return mav;
	}
	
	//-- ---------- ---------- ---------- ---------- ---------- --//
	//-- 						语音库
	//-- ---------- ---------- ---------- ---------- ---------- --//
	@RequestMapping(value = "/voices", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView voices_show(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/wxmaterial/voices/show");
		wxMaterialService.propertyDownloadDir(request, "/resources/wxmaterial");
		mav.addObject("path", "/resources/wxmaterial/voices/");
		return mav;
	}
	
	//-- ---------- ---------- ---------- ---------- ---------- --//
	//-- 						视频库
	//-- ---------- ---------- ---------- ---------- ---------- --//
	@RequestMapping(value = "/videos", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView videos_show(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/wxmaterial/videos/show");
		wxMaterialService.propertyDownloadDir(request, "/resources/wxmaterial");
		mav.addObject("path", "/resources/wxmaterial/videos/");
		return mav;
	}
	
	//-- ---------- ---------- ---------- ---------- ---------- --//
	//-- 						图文库
	//-- ---------- ---------- ---------- ---------- ---------- --//
	@RequestMapping(value = "/news", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView news_show(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/wxmaterial/news/show");
		wxMaterialService.propertyDownloadDir(request, "/resources/wxmaterial");
		mav.addObject("path", "/resources/wxmaterial/news/");
		return mav;
	}
}
