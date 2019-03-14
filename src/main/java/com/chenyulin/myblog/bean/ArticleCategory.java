package com.chenyulin.myblog.bean;

import java.io.Serializable;

public class ArticleCategory implements Serializable {

    private int categoryId;
    private String categoryName;

    public ArticleCategory() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
