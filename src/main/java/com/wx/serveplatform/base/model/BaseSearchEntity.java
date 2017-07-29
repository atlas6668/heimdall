/**
 * <p>Title: BaseSearchEntity.java</p>
 * <p>Description: 基础搜索实体，声明常用搜索属性</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: xiaoma.com</p>
 * @author damon
 * @date 2014年7月10日
 * @version 1.0
 */
package com.wx.serveplatform.base.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>Title: BaseSearchEntity</p>
 * <p>Description: 基础搜索实体，声明常用搜索属性</p>
 * <p>Company: xiaoma.com</p> 
 * @author damon
 * @date 2014年7月10日
 */
public class BaseSearchEntity extends BaseEntity {
	
	/** serialVersionUID */
	private static final long serialVersionUID = -2688779104585103258L;

	/** 页码 */
	private Integer pageNum;
	
	/** 定量 */
	private Integer pageSize;
	
	/** 开始时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date beginTime;
	
	/** 结束时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endTime;
	
//***********************************	getter and setter	*************************************//
	
	/**
	 * @return the pageNum
	 */
	public Integer getPageNum() {
		return pageNum == null ? 1 : pageNum;
	}

	/**
	 * @param pageNum the pageNum to set
	 */
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize == null ? 15 : pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	/**
	 * @return the beginTime
	 */
	public Date getBeginTime() {
		return beginTime;
	}

	/**
	 * @param beginTime the beginTime to set
	 */
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}
