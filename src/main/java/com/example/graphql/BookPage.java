package com.example.graphql;

import java.util.List;

import com.example.graphql.Entity.Book;

public class BookPage {
    private List<Book> content;
    private PageInfo pageInfo;

    public BookPage(List<Book> content, int totalPages, int totalElements) {
        this.content = content;
        this.pageInfo = new PageInfo(totalPages, totalElements);
    }

    public List<Book> getContent() {
        return content;
    }

    public void setContent(List<Book> content) {
        this.content = content;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}

