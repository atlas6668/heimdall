/**
 * <p>Title: Account.java</p>
 * <p>微信账号信息</p>
 * @author damon
 * @date 2015年1月19日
 * @version 1.0
 */
package com.wx.serveplatform.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wx.serveplatform.base.model.BaseSearchEntity;

/**
 * <p>Title: Account</p>
 * <p>微信账号信息</p> 
 * @author damon
 * @date 2015年1月19日
 */
@Entity
@Table(name = "wx_account")
public class Account extends BaseSearchEntity {

	/**
	 * serialVersionUID 
	 */
	private static final long serialVersionUID = 4527036622586536240L;

	@Column(name = "appId")
	private String appid;
	
	@Column(name = "secret")
	private String secret;
	
	@Column(name = "name")
	private String name;
	
	/* ********** ********** getter and setter ********** ********** */
	
	/**
	 * @return the appid
	 */
	public String getAppid() {
		return appid;
	}

	/**
	 * @param appid the appid to set
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}

	/**
	 * @return the secret
	 */
	public String getSecret() {
		return secret;
	}

	/**
	 * @param secret the secret to set
	 */
	public void setSecret(String secret) {
		this.secret = secret;
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
	
}
