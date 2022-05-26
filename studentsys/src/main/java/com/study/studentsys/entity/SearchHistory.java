package com.study.studentsys.entity;

import java.util.Date;

public class SearchHistory {

    private Long id;
    private String keyword;
    private Date searchTime;
    private String email;

    public SearchHistory() {
    }

    public SearchHistory(Long id, String keyword, Date searchTime, String email) {
        this.id = id;
        this.keyword = keyword;
        this.searchTime = searchTime;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Date getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(Date searchTime) {
        this.searchTime = searchTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
