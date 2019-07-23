package com.chenyulin.myblog.repository;

import com.chenyulin.myblog.bean.Article;
import com.chenyulin.myblog.bean.ArticleCategory;
import com.chenyulin.myblog.bean.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@MapperScan("com.chenyulin.myblog.repository")
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleRepositoryTest {
    @Resource
    ArticleRepository repository;

    @Test
    @Ignore
    public void testInsertArticle() {
        Article article = new Article();
        User u = new User();
        u.setUserName("test");
        u.setUserId(3);
        ArticleCategory category = new ArticleCategory();
        category.setCategoryName("JavaWeb");
        category.setCategoryId(2);

        article.setUser(u);
        article.setCategory(category);
        article.setBriefIntro("test");
        article.setContent("testtse");
        article.setCreateTime(new Date());
        article.setLastEditTime(new Date());
        article.setStatus(1);
        article.setTitle("test");

        int row = repository.insertArticle(article);
        assertEquals(1, row);
    }

    @Test
    @Ignore
    public void testQueryArticleCount() {
        int row = repository.queryArticleCountByUser(1);
        System.out.println(row);
    }

    @Test
    @Ignore
    public void testQueryArticleByPage() {
        List<Article> list = repository.queryArticleByPage(1, 0, 4);
        System.out.println("listSize" + list.size());
        for (Article temp : list) {
            System.out.println(temp.getTitle());
        }
    }

    @Test
    @Ignore
    public void testDeleteArticle() {
        int row = repository.deleteArticleById(2);
        assertEquals(1, row);
    }

    @Test
    @Ignore
    public void testQueryArticleById() {
        Article testArticle = repository.queryArticleById(4);
        System.out.println(testArticle.getTitle());
        System.out.println(testArticle.getContent());
    }

    @Test
    @Ignore
    public void testQueryArticleByCategoryId() {

        List<Article> articleList = repository.queryArticleByCategoryId(2, 0, 3);
        System.out.println(articleList.size());

    }

    @Test
    @Ignore
    public void testQueryArticleByTitle() {
//        List<Article> articleList = repository.queryArticleByTitle(article,u,0,3);
        int count = repository.queryCountByTitle("测试", 1);
//        for (Article a:articleList) {
//            System.out.println(a.getTitle());
//        }
//        assertEquals(3,articleList.size());
        System.out.println("符合标题的文章数量：" + count);
    }

    @Test
    @Ignore
    public void queryTopSixArticle() {

        List<Article> articleList = repository.queryTopSixArticleByUser(1);
        System.out.println(articleList.size());

    }

}
