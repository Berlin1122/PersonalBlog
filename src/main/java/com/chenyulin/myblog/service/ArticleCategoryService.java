package com.chenyulin.myblog.service;

import com.chenyulin.myblog.bean.Article;
import com.chenyulin.myblog.bean.ArticleCategory;
import com.chenyulin.myblog.bean.User;
import com.chenyulin.myblog.repository.ArticleCategoryRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleCategoryService {
    @Resource
    private ArticleCategoryRepository repository;

    public List<ArticleCategory> getArticleCategoryByUserId(int userId) {
        return repository.queryArticleCategoryByUserId(userId);
    }

    public boolean deleteCategory(ArticleCategory category) {
        int row = repository.deleteCategory(category);
        if (row <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addCategory(ArticleCategory category) {
        int row = repository.insertArticleCategory(category);
        if (row <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public ArticleCategory getCategoryByCategoryId(int categoryId) {
        return repository.queryCategoryByCategoryId(categoryId);
    }

}
