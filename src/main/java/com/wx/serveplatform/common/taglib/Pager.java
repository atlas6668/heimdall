package com.wx.serveplatform.common.taglib;

import java.util.List;

/**
 * Created by vincent on 2014/5/26.
 * 分页的bean
 */
public class Pager<T> {
    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 总数量
     */
    private int totalNum;

    /**
     * 总页数
     */
    private int totalPageNum;

    /**
     * 每页数量
     */
    private int pageSize;

    /**
     * url
     */
    private String pageUrl;

    /**
     * 实体
     */
    private List<T> items;

    public Pager(int pageNum, int totalNum, int pageSize, List<T> items, String pageUrl) {
        this.pageNum = pageNum;
        this.totalNum = totalNum;
        this.totalPageNum = (int) Math.ceil((double)totalNum / pageSize);
        this.pageSize = pageSize;
        this.items = items;
        this.pageUrl = pageUrl;
    }

    public Pager(int totalNum, List<T> items, int pageNum, String pageUrl) {
        this.pageSize = 10;
        this.totalNum = totalNum;
        this.items = items;
        this.pageNum = pageNum;
        this.totalPageNum = (int) Math.ceil((double)totalNum / pageSize);
        this.pageUrl = pageUrl;
    }
    
    public Pager(int pageNum, int totalNum, int pageSize, List<T> items) {
        this.pageNum = pageNum;
        this.totalNum = totalNum;
        this.totalPageNum = (int) Math.ceil((double)totalNum / pageSize);
        this.pageSize = pageSize;
        this.items = items;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }
}
