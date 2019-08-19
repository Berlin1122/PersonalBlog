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

    public int getArticleCountByUser(int userId) {
        return repository.queryArticleCountByUser(userId);
    }

    /**
     * 获取当前用户下所有文章，用于管理（删除更改等）
     *
     * @param userId
     * @param page
     * @return
     */
    public List<Article> getArticleListByUser(int userId, int page) {
        int startIndex = PageUtil.calStartIndex(page);
        int countPerpage = (int) PageUtil.DATA_COUNT_PERPAGE;
        return repository.queryArticleByPage(userId, startIndex, countPerpage);
    }

    public boolean removeArticleById(int articleId) {
        int row = repository.deleteArticleById(articleId);
        if (row <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public Article getArticleById(int articleId) {
        return repository.queryArticleById(articleId);
    }

    /**
     * 查询某个类别下的文章,根据页码决定查询数量
     *
     * @param categoryId
     * @param page       1表示第一页,2表示第二页...
     * @return
     */
    public List<Article> getArticleListByCategoryId(int categoryId, int page) {
        int startIndex = PageUtil.calStartIndex(page);
        int countPerpage = (int) PageUtil.DATA_COUNT_PERPAGE;
        //TODO 如果对应的类别文章数为0 怎么处理
        List<Article> articleList = repository.queryArticleByCategoryId(categoryId, startIndex, countPerpage);

        return articleList;
    }

    public int getArticleCountByCategory(int categoryId) {
        return repository.queryCountByCategoryId(categoryId);
    }

    /**
     * 模糊查询,根据title,userId 查询符合条件的文章，并分页
     *
     * @param userId
     * @param title
     * @param page   1表示第一页,2表示第二页...
     * @return
     */
    public List<Article> getArticleListByTitle(int userId, String title, int page) {
        int startIndex = PageUtil.calStartIndex(page);
        int countPerpage = (int) PageUtil.DATA_COUNT_PERPAGE;
        return repository.queryArticleByTitle(title, userId, startIndex, countPerpage);
    }

    /**
     * 获取当前用户最近发布的前6篇文章，用于首页显示
     *
     * @param userId
     * @return
     */

    public List<Article> getTop6ArticleListByUser(int userId) {
        return repository.queryTopSixArticleByUser(userId);
    }

    /**
     * 获取模糊查询符合条件的文章数量
     *
     * @param title
     * @param userId
     * @return
     */
    public int getCountByTitle(String title, int userId) {
        return repository.queryCountByTitle(title, userId);
    }

}
