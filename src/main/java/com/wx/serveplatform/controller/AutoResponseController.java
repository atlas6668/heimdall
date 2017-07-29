/**
 * <p>Title: AutoResponseController.java</p>
 * <p></p>
 * @author damon
 * @date 2015年3月26日
 * @version 1.0
 */
package com.wx.serveplatform.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wx.serveplatform.common.utils.EhCacheUtil;
import com.wx.serveplatform.model.AutoResponse;
import com.wx.serveplatform.service.AutoResponseService;
import com.wx.serveplatform.utils.WxMaterialUtil;

/**
 * <p>Title: AutoResponseController</p>
 * <p></p> 
 * @author damon
 * @date 2015年3月26日
 */
@Controller
@RequestMapping("/autoresponse")
public class AutoResponseController {
	@Autowired
	private AutoResponseService autoResponseService;
	
	public AutoResponseController() {
		// 缓存关注自动回复内容
		WxMaterialUtil.cacheSubscribe();
		// 缓存消息自动回复内容
		WxMaterialUtil.cacheMessage();
		// 缓存关键词自动回复内容
		WxMaterialUtil.cacheKeywordMap();
	}
	
	//-- ---------- ---------- ---------- ---------- ---------- --//
	//-- 					关注自动回复
	//-- ---------- ---------- ---------- ---------- ---------- --//
	@RequestMapping(value = "/subscribe", method = RequestMethod.GET)
	public ModelAndView subscribe(@ModelAttribute("model") AutoResponse model) {
		ModelAndView mav = new ModelAndView("/autoresponse/subscribe/edit");
		List<AutoResponse> list = autoResponseService.findByCategory(AutoResponse.SUBSCRIBE);
		if(list != null && !list.isEmpty()){
			model = list.get(0);
		}
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "/subscribe/save", method = RequestMethod.POST)
	public ModelAndView subscribe_save(@ModelAttribute("model") AutoResponse model) {
		ModelAndView mav = new ModelAndView("/autoresponse/subscribe/edit");
		model.setCategory(AutoResponse.SUBSCRIBE);
		model = autoResponseService.save(model);
		EhCacheUtil.put(WxMaterialUtil.WX_MATERIAL_CACHE, WxMaterialUtil.CACHE_SUBSCRIBE, model.getContent());
		mav.addObject("model", model);
		return mav;
	}
	
	//-- ---------- ---------- ---------- ---------- ---------- --//
	//-- 					消息自动回复
	//-- ---------- ---------- ---------- ---------- ---------- --//
	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public ModelAndView message(@ModelAttribute("model") AutoResponse model) {
		ModelAndView mav = new ModelAndView("/autoresponse/message/edit");
		List<AutoResponse> list = autoResponseService.findByCategory(AutoResponse.MESSAGE);
		if(list != null && !list.isEmpty()){
			model = list.get(0);
		}
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "/message/save", method = RequestMethod.POST)
	public ModelAndView message_save(@ModelAttribute("model") AutoResponse model) {
		ModelAndView mav = new ModelAndView("/autoresponse/message/edit");
		model.setCategory(AutoResponse.MESSAGE);
		model = autoResponseService.save(model);
		EhCacheUtil.put(WxMaterialUtil.WX_MATERIAL_CACHE, WxMaterialUtil.CACHE_MESSAGE, model.getContent());
		mav.addObject("model", model);
		return mav;
	}
	
	//-- ---------- ---------- ---------- ---------- ---------- --//
	//-- 					关键词自动回复
	//-- ---------- ---------- ---------- ---------- ---------- --//
	@RequestMapping(value = "/keyword", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView keyword(@ModelAttribute("model") AutoResponse model) {
		ModelAndView mav = new ModelAndView("/autoresponse/keyword/list");
		int pageNum = model.getPageNum(); 
    	int pageSize = model.getPageSize();
    	Map<String, Object> searchParams = new HashMap<String, Object>();
    	searchParams.put("pageNum", pageNum);
    	searchParams.put("pageSize", pageSize);
    	searchParams.put("model", model);
    	
    	Page<AutoResponse> keywords = autoResponseService.searchKeywords(searchParams);
    	
    	mav.addObject("keywords", (List<AutoResponse>) keywords.getContent());
    	mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "/keyword/add", method = RequestMethod.GET)
	public ModelAndView keyword_add(@ModelAttribute("model") AutoResponse model) {
		ModelAndView mav = new ModelAndView("/autoresponse/keyword/edit");
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "/keyword/edit/{id}", method = RequestMethod.GET)
	public ModelAndView keyword_edit(@PathVariable(value = "id") Integer id, 
			@ModelAttribute("model") AutoResponse model) {
		ModelAndView mav = new ModelAndView("/autoresponse/keyword/edit");
		model = autoResponseService.findByIdAndCategory(id, AutoResponse.KEYWORD);
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "/keyword/save", method = RequestMethod.POST)
	public String keyword_save(@ModelAttribute("model") AutoResponse model) {
		model.setCategory(AutoResponse.KEYWORD);
		model = autoResponseService.save(model);
		WxMaterialUtil.cacheKeywordMap();
		return "redirect:/autoresponse/keyword";
	}
	
	@RequestMapping(value = "/keyword/delete", method = RequestMethod.POST)
	public String keyword_delete(String ids) {
		String[] _ids = ids.split(",");
		autoResponseService.delete(_ids);
		WxMaterialUtil.cacheKeywordMap();
		return "redirect:/autoresponse/keyword";
	}
	
	
}
