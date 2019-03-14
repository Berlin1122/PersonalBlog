package com.chenyulin.myblog.service;

import com.chenyulin.myblog.bean.Article;
import com.chenyulin.myblog.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ArticleService {
    @Resource
    private ArticleRepository repository;

    public boolean addArtilcle(Article article){
        int row = repository.insertArticle(article);
        if(row <= 0){
            return false;//写入数据库失败
        }else {
            return true;
        }
    }
}
