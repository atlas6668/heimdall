/**
 * <p>Title: AutoResponse.java</p>
 * <p></p>
 * @author damon
 * @date 2015年3月26日
 * @version 1.0
 */
package com.wx.serveplatform.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wx.serveplatform.base.model.BaseSearchEntity;

/**
 * <p>Title: AutoResponse</p>
 * <p></p> 
 * @author damon
 * @date 2015年3月26日
 */
@Entity
@Table(name = "wx_autoresponse")
public class AutoResponse extends BaseSearchEntity {

	public static final String SUBSCRIBE = "subscribe";
	
	public static final String MESSAGE = "message";
			
	public static final String KEYWORD = "keyword";
	
	/**
	 * serialVersionUID 
	 */
	private static final long serialVersionUID = -6809991227516985354L;
	
	/**
	 * category 分类
	 */
	@Column(name = "category")
	private String category;
	
	/**
	 * messageType 回复消息类型
	 */
	@Column(name = "messageType")
	private String messageType;
	
	/**
	 * content 回复内容
	 */
	@Column(name = "content")
	private String content;
	
	/**
	 * ruleName 规则名称
	 */
	@Column(name = "ruleName")
	private String ruleName;
	
	/**
	 * keyword 关键词
	 */
	@Column(name = "keyword")
	private String keyword;
	
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
	 * @return the messageType
	 */
	public String getMessageType() {
		return messageType;
	}

	/**
	 * @param messageType the messageType to set
	 */
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the ruleName
	 */
	public String getRuleName() {
		return ruleName;
	}

	/**
	 * @param ruleName the ruleName to set
	 */
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	/**
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * @param keyword the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
