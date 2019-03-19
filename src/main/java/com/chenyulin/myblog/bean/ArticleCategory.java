package com.chenyulin.myblog.bean;

import java.io.Serializable;

public class ArticleCategory implements Serializable {

    private int categoryId;
    private String categoryName;
    private User user;              //用于表示此类别隶属于哪个管理员

    public ArticleCategory() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
