/**
 * <p>Title: News.java</p>
 * <p></p>
 * @author damon
 * @date 2015年3月17日
 * @version 1.0
 */
package com.wx.serveplatform.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.wx.serveplatform.base.model.BaseSearchEntity;

/**
 * <p>Title: News</p>
 * <p></p> 
 * @author damon
 * @date 2015年3月17日
 */
@Entity
@Table(name = "wx_news")
public class News extends BaseSearchEntity {

	/**
	 * serialVersionUID 
	 */
	private static final long serialVersionUID = 8859509380491131520L;
	
	/**
	 * isMain 是否是多图文主消息
	 */
	private Integer isMain;
	
	/**
	 * title 标题
	 */
	private String title;
	
	/**
	 * author 作者
	 */
	private String author;
	
	/**
	 * cover 封面图片
	 */
	private String cover;
	
	/**
	 * covershowoncontent 封面图片是否正文展示
	 */
	private Integer covershowoncontent;
	
	/**
	 * nabstract 摘要
	 */
	private String nabstract;
	
	/**
	 * content 正文
	 */
	private String content;
	
	/**
	 * link 链接
	 */
	private String link;
	
	/* ********** ********** getter and setter ********** ********** */
	
	/**
	 * @return the isMain
	 */
	public Integer getIsMain() {
		return isMain;
	}

	/**
	 * @param isMain the isMain to set
	 */
	public void setIsMain(Integer isMain) {
		this.isMain = isMain;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the cover
	 */
	public String getCover() {
		return cover;
	}

	/**
	 * @param cover the cover to set
	 */
	public void setCover(String cover) {
		this.cover = cover;
	}

	/**
	 * @return the covershowoncontent
	 */
	public Integer getCovershowoncontent() {
		return covershowoncontent;
	}

	/**
	 * @param covershowoncontent the covershowoncontent to set
	 */
	public void setCovershowoncontent(Integer covershowoncontent) {
		this.covershowoncontent = covershowoncontent;
	}

	/**
	 * @return the nabstract
	 */
	public String getNabstract() {
		return nabstract;
	}

	/**
	 * @param nabstract the nabstract to set
	 */
	public void setNabstract(String nabstract) {
		this.nabstract = nabstract;
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
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}

}
