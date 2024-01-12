package com.wzh086.model;

import java.util.List;

public class Page {
    private int pageNumber;
    private int pageSize;
    private int totalCount;
    private int totalPage;
    private List<Object> list;

    public void setPageSizeAndTotalCount(int pageSize, int totalCount){
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage =  (int)Math.ceil((double)totalCount/pageSize);
    }
    public Page() {
    }

    public Page(int pageNumber, int pageSize, int totalCount, int totalPage, List<Object> list) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.list = list;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public List getList() {
        return list;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setList(List list) {
        this.list = list;
    }
}
