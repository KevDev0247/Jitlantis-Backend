package com.jitlantis.backend.API.base;

public class PageRequest {

    private int PageNum;

    private int PageSize;

    public int getPageNum() {
        return PageNum;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageNum(int pageNum) {
        PageNum = pageNum;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }
}
