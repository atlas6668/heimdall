/**
 * <p>Title: Photo.java</p>
 * <p></p>
 * @author damon
 * @date 2015年3月6日
 * @version 1.0
 */
package com.wx.serveplatform.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wx.serveplatform.base.model.BaseSearchEntity;

/**
 * <p>Title: Photo</p>
 * <p>图片库</p> 
 * @author damon
 * @date 2015年3月6日
 */
@Entity
@Table(name = "wx_photohouse")
public class Photo extends BaseSearchEntity {

	/**
	 * serialVersionUID 
	 */
	private static final long serialVersionUID = 3912624450569991309L;
	
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
	 * loadtimemills 上传时间毫秒数
	 */
	@Column(name = "loadtimemills")
	private Long loadtimemills;
	
	/* ********** ********** getter and setter ********** ********** */
	
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
