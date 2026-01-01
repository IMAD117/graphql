package com.example.graphql;

import java.util.List;

import com.example.graphql.Entity.Book;

public class SearchPage {
    private List<?> content;
    private PageInfo pageInfo;

    public SearchPage(List<?> content, int totalPages, int totalElements) {
        this.content = content;
        this.pageInfo = new PageInfo(totalPages, totalElements);
    }

    public List<?> getContent() {
        return content;
    }

    public void setContent(List<?> content) {
        this.content = content;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}

