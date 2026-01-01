package com.pss.pss.article.dto;

public enum SortBy {
    CREATED_AT("createdAt"),
    UPDATED_AT("updatedAt"),
    TITLE("title"),
    VIEWCOUNT("viewCount"),
    ID("id");

    private String value;
    SortBy(String value) {
        this.value = value;
    }
    public String property() {
        return value;
    }
}

