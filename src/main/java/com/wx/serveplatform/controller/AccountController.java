/**
 * <p>Title: AccountController.java</p>
 * <p></p>
 * @author damon
 * @date 2015年4月10日
 * @version 1.0
 */
package com.wx.serveplatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wx.serveplatform.model.Account;
import com.wx.serveplatform.service.AccountService;
import com.wx.serveplatform.utils.WxApiUtil;

/**
 * <p>Title: AccountController</p>
 * <p></p> 
 * @author damon
 * @date 2015年4月10日
 */
@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = "/show", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView account_show() {
		ModelAndView mav = new ModelAndView("/account/list");
    	List<Account> accounts = accountService.findAll();
    	mav.addObject("accounts", accounts);
		return mav;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView account_add(@ModelAttribute("model") Account model) {
		ModelAndView mav = new ModelAndView("/account/edit");
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView wxmenu_edit(@PathVariable(value = "id") Integer id, 
			@ModelAttribute("model") Account model) {
		ModelAndView mav = new ModelAndView("/account/edit");
		model = accountService.findById(id);
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String account_save(@ModelAttribute("model") Account model) {
		model = accountService.save(model);
		WxApiUtil.updateCache();
		return "redirect:/account/show";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String account_delete(String ids) {
		String[] _ids = ids.split(",");
		accountService.delete(_ids);
		WxApiUtil.updateCache();
		return "redirect:/account/show";
	}
	
}
