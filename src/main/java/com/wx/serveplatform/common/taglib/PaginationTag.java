package com.wx.serveplatform.common.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 分页标签
 * @author vincent
 * @author Damon
 * @version 1.0
 * @since 0.2
 */
public class PaginationTag extends SimpleTagSupport {

	/**
     * 默认的一些字符串
     */
    private final String A_HREF_START_BEFORE = "<a href='";
    private final String A_HREF_START_AFTER = "'/>";
    private final String A_HREF_END = "</a>";

    private final String BLANK_HREF_START = "<a>";
    private final String BLANK_HREF_END = "</a>";
    
    /**
     * 默认分页标签
     * <div class='pagination'>
     * 		<ul>
     * 			<li class='active'>
     * 				<a></a>
     * 			</li>
     * 			<li>
     * 				<a></a>
     * 			</li>
     * 			<li>
     * 				<a></a>
     * 			</li>
     * 		</ul>
     * </div>
     */
    private final String PAGE_TAG_TYPE_DIV_UL_LI_A = "1";
    
    /**
     * 分页数据类
     */
    @SuppressWarnings("rawtypes")
	private Pager pager;

    /**
     * 分页分类
     */
    private String pageTagType = "1";
    
    /**
     * 分页样式
     */
    private String pageTagClass = "pagination";

    /**
     * 当前页样式
     */
    private String currentPageTagClass = "active";

    /**
     * 第一个节点 默认是 首页
     */
    private String firstPageName = "\u9996\u9875";
    /**
     * 最后一个节点 默认是 末页
     */
    private String lastPageName = "\u672b\u9875";
    /**
     * 上一页
     */
    private String previousPageName = "\u4e0a\u4e00\u9875";
    /**
     * 下一页
     */
    private String nextPageName = "\u4e0b\u4e00\u9875";

    /**
     * 下面的参数不需要在页面设置
     */
    private String pageTagRoot = "div";
    private String pageTagRootStart = "<div class='pagination'>";
    private String pageTagRootEnd = "</div>";
    private String pageTagElementStair = "ul";
    private String pageTagElementStairStart = "<ul>";
    private String pageTagElementStairEnd = "</ul>";
    private String pageTagElementSecondary = "li";
    private String pageTagElementSecondaryStart = "<li>";
    private String pageTagElementSecondaryStartCurrent = "<li class='active'>";
    private String pageTagElementSecondaryEnd = "</li>";
    
    /**
     * 标签主体的组合部分
     * @throws JspException
     * @throws IOException
     */
    public void doTag() throws JspException, IOException {
        init();
        StringBuffer sb = new StringBuffer();
        // 分页根标签开始
        sb.append(this.getPageTagRootStart());
        // 分页一级元素标签开始
        sb.append(this.getPageTagElementStairStart());
        // 第一页
        sb.append(this.getFirstHtml());
        // 上一页
        sb.append(this.getPreviousHtml());
        // 中间获取10页
        sb.append(this.getTenPage());
        // 下一页
        sb.append(this.getNextHtml());
        // 最后一页
        sb.append(this.getLastHtml());
        // 分页一级元素标签结束
        sb.append(this.getPageTagElementStairEnd());
        // 分页根标签结束
        sb.append(this.getPageTagRootEnd());

        JspWriter out = getJspContext().getOut();
        out.print(sb.toString());
    }

    /**
     * 初始化标签
     */
    private void init() {
    	if(this.pageTagType.equals(PAGE_TAG_TYPE_DIV_UL_LI_A)){
    		this.setPageTagRoot("div");
    		this.setPageTagRootStart("<div class='" + this.pageTagClass + "'>");
    		this.setPageTagRootEnd("</div>");
    		this.setPageTagElementStair("ul");
    		this.setPageTagElementStairStart("<ul>");
    		this.setPageTagElementStairEnd("</ul>");
    		this.setPageTagElementSecondary("li");
    		this.setPageTagElementSecondaryStart("<li>");
    		this.setPageTagElementSecondaryStartCurrent("<li class='" + this.currentPageTagClass + "' >");
    		this.setPageTagElementSecondaryEnd("</li>");
    	}
        
    }

    /**
     * 获取首个节点
     * @return
     */
    private String getFirstHtml() {
       if(pager.getPageNum() >= 2 ){
           return makeOnePageHTML(1, firstPageName);
       }
       return "";
    }

    /**
     * 获取最后节点
     * @return
     */
    private String getLastHtml() {
        if(pager.getPageNum() <= (pager.getTotalPageNum() -1) ){
            return makeOnePageHTML(pager.getTotalPageNum(), lastPageName);
        }
        return "";
    }

    /**
     * 获取上一个页面
     * @return
     */
    private String getPreviousHtml() {
        if( pager.getPageNum() >= 2 ){
            return makeOnePageHTML((pager.getPageNum() - 1), previousPageName);
        }
        return "";
    }

    /**
     * 获取下一个页面
     * @return
     */
    private String getNextHtml() {
        if(pager.getPageNum() <= (pager.getTotalPageNum() -1) ){
            return makeOnePageHTML((pager.getPageNum() + 1), nextPageName);
        }
        return "";
    }

    /**
     * 获取中间的10页
     * @return
     */
    private StringBuffer getTenPage() {
        // 计算中间的页码的10位数字的 左边5个 右边4个 仿google
        int totalPageCount = 10;
        int firstPageNum = 1;
        int lastPageNum =10;
        int leftPage = 4;
        // 计算出最左边的
        // 如果 比5大, 当前页大于等于6
        if (pager.getPageNum() >= 6 ){
            // 计算第一个值
            firstPageNum = pager.getPageNum() - 5;
            // 计算剩余的值
            leftPage = 4;

        } else {
            // 如果当前页 比6 小
            firstPageNum = 1;
            // 计算剩余的值
            leftPage = totalPageCount - pager.getPageNum();
        }
        // 计算最后一页页数
        lastPageNum = ( pager.getTotalPageNum() > (pager.getPageNum() + leftPage) ) ?
                (pager.getPageNum() + leftPage) : pager.getTotalPageNum();
        // 如果此时页面数量不够10个,再向前取
        if ( (lastPageNum - firstPageNum) < 10 ){
            // 计算需要补齐的数量
            int restNum = 10 - (lastPageNum - firstPageNum + 1);
            firstPageNum = ((firstPageNum - restNum) > 0 ) ? (firstPageNum - restNum) : 1;
        }
        // 循环生成所有页
        StringBuffer sb = new StringBuffer();
        for (int i = firstPageNum; i <= lastPageNum; i++){
            sb.append(makeOnePageHTML(i, String.valueOf(i)));
        }
        return sb;
    }

    /**
     * 获取一个完整的 页码HTML
     * @param pageNum 页号
     * @param tagBody 页的文字说明 比如 第一页, 1 ,末页
     * @return
     */
    private String makeOnePageHTML(int pageNum, String tagBody){
        StringBuffer sb = new StringBuffer();
        // 如果是当前页 要加上一个class active
        if (pageNum == this.pager.getPageNum()) {
            sb.append(this.getPageTagElementSecondaryStartCurrent());
            // 不加a标签
            String blank_href = BLANK_HREF_START + tagBody + BLANK_HREF_END;
            sb.append(blank_href);
        } else {
            sb.append(this.getPageTagElementSecondaryStart());
            // 加a标签
            sb.append(getLinkHTML(pageNum, tagBody));

        }
        sb.append( this.getPageTagElementSecondaryEnd());
        return sb.toString();
    }
    
    /**
     * 获取url
     * @param pageNum
     * @return
     */
    private String getUrl(int pageNum){
        return  this.pager.getPageUrl() + "?page=" + pageNum;
    }

    /**
     * 组一个a标签
     * @param pageNum
     * @param tagBody
     * @return
     */
    private String getLinkHTML(int pageNum, String tagBody){
    	if(this.pager.getPageUrl() != null){
    		return A_HREF_START_BEFORE + this.getUrl(pageNum) + A_HREF_START_AFTER
               + tagBody + A_HREF_END;
    	} else {
    		return A_HREF_START_BEFORE + "javascript:void(0)' onclick='page(" 
    				+ pageNum + "," + pager.getPageSize() + ")" + A_HREF_START_AFTER
    				+ tagBody + A_HREF_END;
    	}
    }

/* ===================================getter and setter==================================== */

    /**
	 * @return the pager
	 */
	@SuppressWarnings("rawtypes")
	public Pager getPager() {
		return pager;
	}

	/**
	 * @param pager the pager to set
	 */
	@SuppressWarnings("rawtypes")
	public void setPager(Pager pager) {
		this.pager = pager;
	}

	/**
	 * @return the pageTagType
	 */
	public String getPageTagType() {
		return pageTagType;
	}

	/**
	 * @param pageTagType the pageTagType to set
	 */
	public void setPageTagType(String pageTagType) {
		this.pageTagType = pageTagType;
	}

	/**
	 * @return the pageTagClass
	 */
	public String getPageTagClass() {
		return pageTagClass;
	}

	/**
	 * @param pageTagClass the pageTagClass to set
	 */
	public void setPageTagClass(String pageTagClass) {
		this.pageTagClass = pageTagClass;
	}

	/**
	 * @return the currentPageTagClass
	 */
	public String getCurrentPageTagClass() {
		return currentPageTagClass;
	}

	/**
	 * @param currentPageTagClass the currentPageTagClass to set
	 */
	public void setCurrentPageTagClass(String currentPageTagClass) {
		this.currentPageTagClass = currentPageTagClass;
	}

	/**
	 * @return the firstPageName
	 */
	public String getFirstPageName() {
		return firstPageName;
	}

	/**
	 * @param firstPageName the firstPageName to set
	 */
	public void setFirstPageName(String firstPageName) {
		this.firstPageName = firstPageName;
	}

	/**
	 * @return the lastPageName
	 */
	public String getLastPageName() {
		return lastPageName;
	}

	/**
	 * @param lastPageName the lastPageName to set
	 */
	public void setLastPageName(String lastPageName) {
		this.lastPageName = lastPageName;
	}

	/**
	 * @return the previousPageName
	 */
	public String getPreviousPageName() {
		return previousPageName;
	}

	/**
	 * @param previousPageName the previousPageName to set
	 */
	public void setPreviousPageName(String previousPageName) {
		this.previousPageName = previousPageName;
	}

	/**
	 * @return the nextPageName
	 */
	public String getNextPageName() {
		return nextPageName;
	}

	/**
	 * @param nextPageName the nextPageName to set
	 */
	public void setNextPageName(String nextPageName) {
		this.nextPageName = nextPageName;
	}

	/**
	 * @return the pageTagRoot
	 */
	public String getPageTagRoot() {
		return pageTagRoot;
	}

	/**
	 * @param pageTagRoot the pageTagRoot to set
	 */
	public void setPageTagRoot(String pageTagRoot) {
		this.pageTagRoot = pageTagRoot;
	}

	/**
	 * @return the pageTagRootStart
	 */
	public String getPageTagRootStart() {
		return pageTagRootStart;
	}

	/**
	 * @param pageTagRootStart the pageTagRootStart to set
	 */
	public void setPageTagRootStart(String pageTagRootStart) {
		this.pageTagRootStart = pageTagRootStart;
	}

	/**
	 * @return the pageTagRootEnd
	 */
	public String getPageTagRootEnd() {
		return pageTagRootEnd;
	}

	/**
	 * @param pageTagRootEnd the pageTagRootEnd to set
	 */
	public void setPageTagRootEnd(String pageTagRootEnd) {
		this.pageTagRootEnd = pageTagRootEnd;
	}

	/**
	 * @return the pageTagElementStair
	 */
	public String getPageTagElementStair() {
		return pageTagElementStair;
	}

	/**
	 * @param pageTagElementStair the pageTagElementStair to set
	 */
	public void setPageTagElementStair(String pageTagElementStair) {
		this.pageTagElementStair = pageTagElementStair;
	}

	/**
	 * @return the pageTagElementStairStart
	 */
	public String getPageTagElementStairStart() {
		return pageTagElementStairStart;
	}

	/**
	 * @param pageTagElementStairStart the pageTagElementStairStart to set
	 */
	public void setPageTagElementStairStart(String pageTagElementStairStart) {
		this.pageTagElementStairStart = pageTagElementStairStart;
	}

	/**
	 * @return the pageTagElementStairEnd
	 */
	public String getPageTagElementStairEnd() {
		return pageTagElementStairEnd;
	}

	/**
	 * @param pageTagElementStairEnd the pageTagElementStairEnd to set
	 */
	public void setPageTagElementStairEnd(String pageTagElementStairEnd) {
		this.pageTagElementStairEnd = pageTagElementStairEnd;
	}

	/**
	 * @return the pageTagElementSecondary
	 */
	public String getPageTagElementSecondary() {
		return pageTagElementSecondary;
	}

	/**
	 * @param pageTagElementSecondary the pageTagElementSecondary to set
	 */
	public void setPageTagElementSecondary(String pageTagElementSecondary) {
		this.pageTagElementSecondary = pageTagElementSecondary;
	}

	/**
	 * @return the pageTagElementSecondaryStart
	 */
	public String getPageTagElementSecondaryStart() {
		return pageTagElementSecondaryStart;
	}

	/**
	 * @param pageTagElementSecondaryStart the pageTagElementSecondaryStart to set
	 */
	public void setPageTagElementSecondaryStart(String pageTagElementSecondaryStart) {
		this.pageTagElementSecondaryStart = pageTagElementSecondaryStart;
	}

	/**
	 * @return the pageTagElementSecondaryStartCurrent
	 */
	public String getPageTagElementSecondaryStartCurrent() {
		return pageTagElementSecondaryStartCurrent;
	}

	/**
	 * @param pageTagElementSecondaryStartCurrent the pageTagElementSecondaryStartCurrent to set
	 */
	public void setPageTagElementSecondaryStartCurrent(
			String pageTagElementSecondaryStartCurrent) {
		this.pageTagElementSecondaryStartCurrent = pageTagElementSecondaryStartCurrent;
	}

	/**
	 * @return the pageTagElementSecondaryEnd
	 */
	public String getPageTagElementSecondaryEnd() {
		return pageTagElementSecondaryEnd;
	}

	/**
	 * @param pageTagElementSecondaryEnd the pageTagElementSecondaryEnd to set
	 */
	public void setPageTagElementSecondaryEnd(String pageTagElementSecondaryEnd) {
		this.pageTagElementSecondaryEnd = pageTagElementSecondaryEnd;
	}
	
}
