package com.chenyulin.myblog.service;

import com.chenyulin.myblog.bean.ArticleCategory;
import com.chenyulin.myblog.repository.ArticleCategoryRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleCategoryService {
    @Resource
    private ArticleCategoryRepository repository;

    public List<ArticleCategory> getAllArticleCategory(){
        return repository.queryAllArticleCategory();
    }

    public ArticleCategory getCategoryByCategoryName(ArticleCategory category){
        return repository.queryCategoryByCategoryName(category);
    }
}
