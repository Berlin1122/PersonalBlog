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

    public boolean addArtilcle(Article article) {
        int row = repository.insertArticle(article);
        if (row <= 0) {
            return false;//写入数据库失败
        } else {
            return true;
        }
    }

    public int getArticleCountByUser(User user) {
        return repository.queryArticleCountByUser(user);
    }

    /**
     * 获取当前用户下所有文章，用于管理（删除更改等）
     *
     * @param user
     * @param page
     * @return
     */
    public List<Article> getArticleListByUser(User user, int page) {
        int startIndex = PageUtil.calStartIndex(page);
        int countPerpage = (int) PageUtil.DATA_COUNT_PERPAGE;
        return repository.queryArticleByPage(user, startIndex, countPerpage);
    }

    public boolean removeArticleById(Article article) {
        int row = repository.deleteArticleById(article);
        if (row <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public Article getArticleById(Article article) {
        return repository.queryArticleById(article);
    }

    /**
     * 查询某个类别下的文章,根据页码决定查询数量
     *
     * @param article
     * @param page    1表示第一页,2表示第二页...
     * @return
     */
    public List<Article> getArticleListByCategoryId(Article article, int page) {
        int startIndex = PageUtil.calStartIndex(page);
        int countPerpage = (int) PageUtil.DATA_COUNT_PERPAGE;
        //TODO 如果对应的类别文章数为0 怎么处理
        List<Article> articleList = repository.queryArticleByCategoryId(article, startIndex, countPerpage);

        return articleList;
    }

    public int getArticleCountByCategory(Article article) {
        return repository.queryCountByCategoryId(article);
    }

    /**
     * 模糊查询,根据title,userId 查询符合条件的文章，并分页
     *
     * @param user
     * @param article
     * @param page    1表示第一页,2表示第二页...
     * @return
     */
    public List<Article> getArticleListByTitle(User user, Article article, int page) {
        int startIndex = PageUtil.calStartIndex(page);
        int countPerpage = (int) PageUtil.DATA_COUNT_PERPAGE;
        return repository.queryArticleByTitle(article, user, startIndex, countPerpage);
    }

    /**
     * 获取当前用户最近发布的前6篇文章，用于首页显示
     *
     * @param user
     * @return
     */

    public List<Article> getTop6ArticleListByUser(User user) {
        return repository.queryTopSixArticleByUser(user);
    }

    /**
     * 获取模糊查询符合条件的文章数量
     *
     * @param article
     * @param user
     * @return
     */
    public int getCountByTitle(Article article, User user) {
        return repository.queryCountByTitle(article, user);
    }
}
