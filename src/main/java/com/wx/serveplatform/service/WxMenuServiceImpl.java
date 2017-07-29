/**
 * <p>Title: WxMenuServiceImpl.java</p>
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wx.middleware.server.bean.menu.WxMenu;
import com.wx.middleware.server.util.Constants;
import com.wx.middleware.server.util.WeiXinUtil;
import com.wx.serveplatform.model.WxMenuButton;
import com.wx.serveplatform.repository.sdj.WxMenuRepository;
import com.wx.serveplatform.utils.WxApiUtil;

/**
 * <p>Title: WxMenuServiceImpl</p>
 * <p></p> 
 * @author damon
 * @date 2015年4月9日
 */
@Service
@Transactional
public class WxMenuServiceImpl implements WxMenuService {
	public static final String REQUEST_ROB = "http://rob.xiaoma.com";
    public static final String REQUEST_BINDING = "http://rob.xiaoma.com/rob/user/binding";
    public static final String REQUEST_CANCELSUB = "http://rob.xiaoma.com/rob/sub/cancelSub";
    private final String appid = Constants.APPID;
    
	@Autowired
	private WxMenuRepository wxMenuRepository;

	@Override
	public List<WxMenuButton> findAll() {
		//多个order
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.DESC, "id"));
		return wxMenuRepository.findAll(buildKeywordsSpecification(), new Sort(orders));
	}

	/**
	 * <p>buildKeywordsSpecification</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年4月9日
	 * @param searchParams
	 * @return
	 */
	private Specification<WxMenuButton> buildKeywordsSpecification() {
		return new Specification<WxMenuButton>(){  
	        public Predicate toPredicate(Root<WxMenuButton> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	        	//where 1=1 --> cb.conjunction()
	        	List<Predicate> pl = new ArrayList<Predicate>();
	        	pl.add(cb.conjunction());
	        	
				//拼接where 条件
	        	Object[] oa = pl.toArray();
	        	Predicate[] pp = new Predicate[oa.length];
	        	for(int i = 0; i < oa.length; i++){
	        		pp[i] = (Predicate) oa[i];
	        	}
	        	query.where(pp);
	            return query.getRestriction();  
	        }  
    	};
	}

	@Override
	public WxMenuButton findById(Integer id) {
		return wxMenuRepository.findById(id);
	}

	@Override
	public void delete(String[] _ids) {
		for(String id : _ids){
			wxMenuRepository.delete(Integer.parseInt(id));
		}
	}

	@Override
	public WxMenuButton save(WxMenuButton model) {
		return wxMenuRepository.save(model);
	}

	@Override
	public void wxmenu_publish() throws KeyManagementException, 
		NoSuchAlgorithmException, NoSuchProviderException, IOException {
		Map<String, String> accessTokenMap = WxApiUtil.getAccessTokenMap();
		String access_token = accessTokenMap.get(appid);
		//-- -------- -------- -------- -------- -------- --//
		//--			更新微信公众平台菜单
		//-- -------- -------- -------- -------- -------- --//
		// 删除原有菜单
		String dele = WeiXinUtil.deleteMenu(access_token);
		System.out.println(dele);
		// 构建菜单 并推送微信公众平台
		String str = buildMenu(appid);
		System.out.println(str);
		String menu = WeiXinUtil.createMenu(str, access_token);
		System.out.println(menu);
		// 查看公众号菜单
		String seemenu = WeiXinUtil.getMenu(access_token);
		System.out.println(seemenu);
	}
	
	/**
	 * <p>buildMenu</p>
	 * <p>构造菜单</p>
	 * @author damon
	 * @date 2014年12月23日
	 * @return
	 */
	public String buildMenu(String appId) {
		List<com.wx.middleware.server.bean.menu.WxMenuButton> buttons = new ArrayList<com.wx.middleware.server.bean.menu.WxMenuButton>();
		List<com.wx.middleware.server.bean.menu.WxMenuButton> sub_buttons;
		com.wx.middleware.server.bean.menu.WxMenuButton fbutton;
		com.wx.middleware.server.bean.menu.WxMenuButton cbutton;
		List<WxMenuButton> allmenus = (List<WxMenuButton>) wxMenuRepository.findAll();
		List<WxMenuButton> firstlevelmenus = new ArrayList<WxMenuButton>();
		List<WxMenuButton> secondlevelmenus;
		for(WxMenuButton wxmenu : allmenus){
			if(wxmenu.getFatherButton() == null){
				firstlevelmenus.add(wxmenu);
			}
		}
		for(WxMenuButton wxmenu : firstlevelmenus){
			fbutton = new com.wx.middleware.server.bean.menu.WxMenuButton(
					wxmenu.getMtype(),wxmenu.getName(),wxmenu.getMkey(),wxmenu.getUrl(),wxmenu.isOauth2Url(),appid,null);
			secondlevelmenus = wxMenuRepository.findByFatherButton(wxmenu.getId());
			if(secondlevelmenus != null && !secondlevelmenus.isEmpty()){
				sub_buttons = new ArrayList<com.wx.middleware.server.bean.menu.WxMenuButton>();
				for(WxMenuButton wxmenuc : secondlevelmenus){
					cbutton = new com.wx.middleware.server.bean.menu.WxMenuButton(
							wxmenuc.getMtype(),wxmenuc.getName(),wxmenuc.getMkey(),wxmenuc.getUrl(),wxmenu.isOauth2Url(),appid,null);
					sub_buttons.add(cbutton);
				}
				fbutton.setSub_buttons(sub_buttons);
			}
			buttons.add(fbutton);
		}
		WxMenu menu = new WxMenu(buttons);
		System.out.println(menu.toString());
		return menu.toString();
	}

	@Override
	public List<WxMenuButton> findByFatherButton(Integer fid) {
		return wxMenuRepository.findByFatherButton(fid);
	}
	
}
