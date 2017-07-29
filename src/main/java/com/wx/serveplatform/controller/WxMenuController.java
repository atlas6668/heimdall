/**
 * <p>Title: WxMenuController.java</p>
 * <p></p>
 * @author damon
 * @date 2015年4月8日
 * @version 1.0
 */
package com.wx.serveplatform.controller;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wx.serveplatform.model.WxMenuButton;
import com.wx.serveplatform.service.WxMenuService;

/**
 * <p>Title: WxMenuController</p>
 * <p></p> 
 * @author damon
 * @date 2015年4月8日
 */
@Controller
@RequestMapping("/wxmenu")
public class WxMenuController {

	@Autowired
	private WxMenuService wxMenuService;
	
	/**
	 * <p>wxmenu_show</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年4月9日
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/show", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView wxmenu_show() {
		ModelAndView mav = new ModelAndView("/wxmenu/list");
    	List<WxMenuButton> menus = wxMenuService.findAll();
    	mav.addObject("menus", menus);
		return mav;
	}
	
	/**
	 * <p>wxmenu_add</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年4月9日
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView wxmenu_add(@ModelAttribute("model") WxMenuButton model) {
		ModelAndView mav = new ModelAndView("/wxmenu/edit");
		List<WxMenuButton> firstlevelbuttons = wxMenuService.findByFatherButton(null);
		mav.addObject("firstlevelbuttons", firstlevelbuttons);
		mav.addObject("model", model);
		return mav;
	}
	
	/**
	 * <p>wxmenu_edit</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年4月9日
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView wxmenu_edit(@PathVariable(value = "id") Integer id, 
			@ModelAttribute("model") WxMenuButton model) {
		ModelAndView mav = new ModelAndView("/wxmenu/edit");
		model = wxMenuService.findById(id);
		mav.addObject("model", model);
		List<WxMenuButton> firstlevelbuttons = wxMenuService.findByFatherButton(null);
		mav.addObject("firstlevelbuttons", firstlevelbuttons);
		return mav;
	}
	
	/**
	 * <p>wxmenu_save</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年4月9日
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String wxmenu_save(@ModelAttribute("model") WxMenuButton model) {
		wxMenuService.save(model);
		return "redirect:/wxmenu/show";
	}
	
	/**
	 * <p>wxmenu_delete</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年4月9日
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String wxmenu_delete(String ids) {
		String[] _ids = ids.split(",");
		wxMenuService.delete(_ids);
		return "redirect:/wxmenu/show";
	}
	
	@RequestMapping(value = "/publish", method = RequestMethod.GET)
	public String wxmenu_publish() {
		try {
			wxMenuService.wxmenu_publish();
		} catch (KeyManagementException | NoSuchAlgorithmException
				| NoSuchProviderException | IOException e) {
			System.out.println(e.getMessage());
		}
		return "redirect:/wxmenu/show";
	}
	
}
