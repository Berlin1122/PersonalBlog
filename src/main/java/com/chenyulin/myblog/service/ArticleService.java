package com.chenyulin.myblog.service;

import com.chenyulin.myblog.bean.Article;
import com.chenyulin.myblog.bean.User;
import com.chenyulin.myblog.repository.ArticleRepository;
import com.chenyulin.myblog.utils.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    public int getArticleCountByUser(User user){
        return repository.queryArticleCountByUser(user);
    }

    /**
     * 获取当前用户下的所有，并显示指定页下的具体文章列表
     * @param user
     * @param page
     * @return
     */
    public List<Article> getArticleListByUser(User user,int page){
        int startIndex = PageUtil.calStartIndex(page);
        int countPerpage = (int)PageUtil.DATA_COUNT_PERPAGE;
        return repository.queryArticleByPage(user,startIndex,countPerpage);
    }

    public boolean removeArticleById(Article article){
        int row = repository.deleteArticleById(article);
        if(row <= 0){
            return false;
        }else{
            return true;
        }
    }

    public Article getArticleById(Article article){
        return repository.queryArticleById(article);
    }

    public List<Article> getArticleListByCategoryId(Article article,int page){
        int startIndex = PageUtil.calStartIndex(page);
        int countPerpage = (int)PageUtil.DATA_COUNT_PERPAGE;
        //TODO 如果对应的类别文章数为0 怎么处理
        List<Article> articleList = repository.queryArticleByCategoryId(article,startIndex,countPerpage);

        return articleList;
    }

    public int getArticleCountByCategory(Article article){
        return repository.queryCountByCategoryId(article);
    }
}
