/**
 * <p>Title: MaterialController.java</p>
 * <p></p>
 * @author damon
 * @date 2015年3月5日
 * @version 1.0
 */
package com.wx.serveplatform.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.wx.serveplatform.common.taglib.Pager;
import com.wx.serveplatform.common.utils.DateUtil;
import com.wx.serveplatform.model.NewsHouse;
import com.wx.serveplatform.model.Photo;
import com.wx.serveplatform.service.MaterialService;

/**
 * <p>Title: MaterialController</p>
 * <p>素材库管理</p> 
 * @author damon
 * @date 2015年3月5日
 */
@Controller
@RequestMapping("/material")
public class MaterialController {
	private final static Logger log = LoggerFactory.getLogger(MaterialController.class);
	
	@Autowired
	private MaterialService materialService;
	
	//-- ---------- ---------- ---------- ---------- ---------- --//
	//-- 						图片库
	//-- ---------- ---------- ---------- ---------- ---------- --//
	@RequestMapping(value = "/photos", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView photos_show(@ModelAttribute("model") Photo model) {
		ModelAndView mav = new ModelAndView("/material/photos/show");
		int pageNum = model.getPageNum(); 
    	int pageSize = 12;
    	Map<String, Object> searchParams = new HashMap<String, Object>();
    	searchParams.put("pageNum", pageNum);
    	searchParams.put("pageSize", pageSize);
    	searchParams.put("model", model);
    	
    	Page<Photo> photos = materialService.searchPhotos(searchParams);
    	Pager<Photo> pager = new Pager<Photo>(pageNum,(int)photos.getTotalElements(), 
    			pageSize, (List<Photo>)photos.getContent());
    	
    	mav.addObject("photos", photos.getContent());
    	mav.addObject("pager", pager);
    	mav.addObject("model", model);
		return mav;
	}
	/**
	 * <p>photo_upload_view</p>
	 * <p>跳转到图片上传界面</p>
	 * @author damon
	 * @date 2015年3月6日
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/photos/upload/view", method = RequestMethod.GET)
	public ModelAndView photos_upload_view(@ModelAttribute("model") Photo model) {
		ModelAndView mav = new ModelAndView("/material/photos/view");
		return mav;
	}
	
	/**
	 * <p>photo_edit</p>
	 * <p>跳转到图片编辑界面</p>
	 * @author damon
	 * @date 2015年3月6日
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/photos/edit/{id}", method = RequestMethod.GET)
	public ModelAndView photos_edit(@ModelAttribute("model") Photo model, 
			@PathVariable(value = "id") Integer id) {
		ModelAndView mav = new ModelAndView("/material/photos/edit");
		model = materialService.findPhotoById(id);
		mav.addObject("model", model);
		return mav;
	}
	
	/**
	 * <p>photo_upload</p>
	 * <p>上传图片</p>
	 * @author damon
	 * @date 2015年3月6日
	 * @param model
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/photos/upload", method = RequestMethod.POST)
	public String photos_upload(@ModelAttribute("model") Photo model,
			@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		try {
			// 上传图片 
			String targetPath = materialService.upload(file, "/resources/upload/material/photo/" + DateUtil.getDate("yyyyMMdd"), request);
			model.setName(file.getOriginalFilename());
			model.setPath(targetPath);
			model.setLoadtimemills(System.currentTimeMillis());
			model = materialService.save(model);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return "redirect:/material/photos";
	}
	
	/**
	 * <p>photo_rename</p>
	 * <p>重命名</p>
	 * @author damon
	 * @date 2015年3月9日
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/photos/rename", method = RequestMethod.POST)
	public String photos_rename(@ModelAttribute("model") Photo model) {
		if(model.getId() != null){
			Photo photo = materialService.findPhotoById(model.getId());
			photo.setName(model.getName());
			materialService.save(photo);
		}
		return "redirect:/material/photos";
	}
	
	/**
	 * <p>delete</p>
	 * <p>删除图片</p>
	 * @author damon
	 * @date 2015年3月9日
	 * @param ids
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/photos/delete", method = RequestMethod.POST)
	public String photos_delete(String ids, HttpServletRequest request){
		String[] _ids = ids.split(",");
		try {
			materialService.delete(_ids, request);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return "redirect:/material/photos";
	}
	
	
	//-- ---------- ---------- ---------- ---------- ---------- --//
	//-- 						图文消息库
	//-- ---------- ---------- ---------- ---------- ---------- --//
	@RequestMapping(value = "/news", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView news_show(@ModelAttribute("model") NewsHouse model) {
		ModelAndView mav = new ModelAndView("/material/news/show");
		int pageNum = model.getPageNum(); 
    	int pageSize = model.getPageSize();
    	Map<String, Object> searchParams = new HashMap<String, Object>();
    	searchParams.put("pageNum", pageNum);
    	searchParams.put("pageSize", pageSize);
    	searchParams.put("model", model);
    	
    	Page<NewsHouse> news = materialService.searchNews(searchParams);
    	Pager<NewsHouse> pager = new Pager<NewsHouse>(pageNum,(int)news.getTotalElements(), 
    			pageSize, (List<NewsHouse>)news.getContent());
    	
    	mav.addObject("news", news.getContent());
    	mav.addObject("pager", pager);
    	mav.addObject("model", model);
		return mav;
	}
	
	
	
}
