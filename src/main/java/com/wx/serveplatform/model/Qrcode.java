/**
 * <p>Title: Qrcode.java</p>
 * <p></p>
 * @author damon
 * @date 2015年5月27日
 * @version 1.0
 */
package com.wx.serveplatform.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wx.serveplatform.base.model.BaseSearchEntity;

/**
 * <p>Title: Qrcode</p>
 * <p></p> 
 * @author damon
 * @date 2015年5月27日
 */
@Entity
@Table(name = "wx_qrcode")
public class Qrcode extends BaseSearchEntity {

	/**
	 * serialVersionUID 
	 */
	private static final long serialVersionUID = 7233372982072449890L;
	
	/**
	 * app 公众号
	 */
	@Column(name = "app")
	private String app;
	
	/**
	 * name 图片名称
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * path 图片路径
	 */
	@Column(name = "path")
	private String path;
	
	/**
	 * scene 场景
	 */
	@Column(name = "scene")
	private String scene;
	
	/**
	 * key 场景wkey值
	 */
	@Column(name = "wkey")
	private String wkey;
	
	/**
	 * loadtimemills 下载毫秒数
	 */
	@Column(name = "loadtimemills")
	private Long loadtimemills;
	
	/* ********** ********** getter and setter ********** ********** */
	
	/**
	 * @return the app
	 */
	public String getApp() {
		return app;
	}

	/**
	 * @param app the app to set
	 */
	public void setApp(String app) {
		this.app = app;
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
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	/**
	 * @return the scene
	 */
	public String getScene() {
		return scene;
	}

	/**
	 * @param scene the scene to set
	 */
	public void setScene(String scene) {
		this.scene = scene;
	}

	/**
	 * @return the wkey
	 */
	public String getWkey() {
		return wkey;
	}

	/**
	 * @param wkey the wkey to set
	 */
	public void setWkey(String wkey) {
		this.wkey = wkey;
	}
	
	/**
	 * @return the loadtimemills
	 */
	public Long getLoadtimemills() {
		return loadtimemills;
	}

	/**
	 * @param loadtimemills the loadtimemills to set
	 */
	public void setLoadtimemills(Long loadtimemills) {
		this.loadtimemills = loadtimemills;
	}

}
