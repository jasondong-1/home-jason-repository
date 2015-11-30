package com.ideal.tagportrait.dto;

import java.util.Collection;
import java.util.List;

/**
 * Created by zhou on 2015/10/30.
 */
public class PageModel {
    private int start;
    private int pageSize;
    private Collection pageData;
    private int count;
    private int pageNum;

    public PageModel() {

    }

    public PageModel(int count, Collection pageData) {
        this.count = count;
        this.pageData = pageData;
    }


    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Collection getPageData() {
        return pageData;
    }

    public void setPageData(Collection pageData) {
        this.pageData = pageData;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
