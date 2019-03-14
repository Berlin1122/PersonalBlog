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
public class ArticleCategoryRepositoryTest {

    @Resource
    private ArticleCategoryRepository repository;

    @Test
    @Ignore
    public void testQueryAll(){
        List<ArticleCategory> categoryList = repository.queryAllArticleCategory();
        for (ArticleCategory category:categoryList) {
            System.out.println(category.getCategoryName());
        }
        assertEquals(2,categoryList.size());
    }
    @Test
    @Ignore
    public void testQueryByCategoryName(){
        ArticleCategory articleCategory = new ArticleCategory();
        articleCategory.setCategoryName("JavaWeb");
        ArticleCategory articleCategoryT = repository.queryCategoryByCategoryName(articleCategory);
        System.out.println(articleCategoryT.getCategoryName());
    }

}
