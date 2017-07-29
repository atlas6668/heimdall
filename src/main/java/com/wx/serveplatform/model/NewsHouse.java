/**
 * <p>Title: NewsHouse.java</p>
 * <p></p>
 * @author damon
 * @date 2015年3月17日
 * @version 1.0
 */
package com.wx.serveplatform.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wx.serveplatform.base.model.BaseSearchEntity;

/**
 * <p>Title: NewsHouse</p>
 * <p></p> 
 * @author damon
 * @date 2015年3月17日
 */
@Entity
@Table(name = "wx_newshouse")
public class NewsHouse extends BaseSearchEntity {

	/**
	 * serialVersionUID 
	 */
	private static final long serialVersionUID = 8859509380491131520L;
	
	/**
	 * category 分类
	 */
	@Column(name = "category")
	private String category;
	
	/**
	 * createtimemills 创建时间毫秒数
	 */
	@Column(name = "createtimemills")
	private Long createtimemills;
	
	/* ********** ********** getter and setter ********** ********** */
	
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the createtimemills
	 */
	public Long getCreatetimemills() {
		return createtimemills;
	}

	/**
	 * @param createtimemills the createtimemills to set
	 */
	public void setCreatetimemills(Long createtimemills) {
		this.createtimemills = createtimemills;
	}

}
