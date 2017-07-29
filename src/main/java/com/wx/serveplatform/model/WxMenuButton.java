/**
 * <p>Title: WxMenuButton.java</p>
 * <p></p>
 * @author damon
 * @date 2015年4月9日
 * @version 1.0
 */
package com.wx.serveplatform.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wx.middleware.server.util.Constants;
import com.wx.serveplatform.base.model.BaseSearchEntity;

/**
 * <p>Title: WxMenuButton</p>
 * <p></p> 
 * @author damon
 * @date 2015年4月9日
 */
@Entity
@Table(name = "wx_menu")
public class WxMenuButton extends BaseSearchEntity {

	/**
	 * serialVersionUID 
	 */
	private static final long serialVersionUID = 1785456085529322943L;

	@Column(name = "mtype")
	private String mtype = Constants.WX_MENU_BUTTON_TYPE_VIEW;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "mkey")
	private String mkey;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "father_button")
	private Integer fatherButton;
	
	@Column(name = "isOauth2Url")
	private boolean isOauth2Url = true;
	
//***********************************	getter and setter	*************************************//
	
	/**
	 * @return the mtype
	 */
	public String getMtype() {
		return mtype;
	}

	/**
	 * @param mtype the mtype to set
	 */
	public void setMtype(String mtype) {
		this.mtype = mtype;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the mkey
	 */
	public String getMkey() {
		return mkey;
	}

	/**
	 * @param mkey the mkey to set
	 */
	public void setMkey(String mkey) {
		this.mkey = mkey;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the fatherButton
	 */
	public Integer getFatherButton() {
		return fatherButton;
	}

	/**
	 * @param fatherButton the fatherButton to set
	 */
	public void setFatherButton(Integer fatherButton) {
		this.fatherButton = fatherButton;
	}
	
	/**
	 * @return the isOauth2Url
	 */
	public boolean isOauth2Url() {
		return isOauth2Url;
	}

	/**
	 * @param isOauth2Url the isOauth2Url to set
	 */
	public void setOauth2Url(boolean isOauth2Url) {
		this.isOauth2Url = isOauth2Url;
	}
	
}
