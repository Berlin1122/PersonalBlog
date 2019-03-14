package com.chenyulin.myblog.repository;

import com.chenyulin.myblog.bean.Article;
import com.chenyulin.myblog.bean.ArticleCategory;
import com.chenyulin.myblog.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@MapperScan("com.chenyulin.myblog.repository")
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleRepositoryTest {
    @Resource
    ArticleRepository repository;
    @Test
    public void testInsertArticle(){
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
        assertEquals(1,row);
    }

}
