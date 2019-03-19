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

    @Ignore
    @Test
    public void testQueryArticleCategoryByUserId(){
        User user = new User();
        user.setUserName("Ber1122");
        user.setUserId(1);
        List<ArticleCategory> categoryList = repository.queryArticleCategoryByUserId(user);
        for (ArticleCategory category:categoryList) {
            System.out.println(category.getCategoryName());
        }
        assertEquals(2,categoryList.size());
    }

    @Test
    @Ignore
    public void testDeleteCategory(){
        ArticleCategory category = new ArticleCategory();
        category.setCategoryId(1);
        int row = repository.deleteCategory(category);
        assertEquals(1,row);
    }

    @Test
    @Ignore
    public void testInsertCategory(){
        ArticleCategory category = new ArticleCategory();
        User u = new User();
        u.setUserId(1);
        category.setUser(u);
        category.setCategoryName("编译原理");

        int row = repository.insertArticleCategory(category);
        assertEquals(1,row);
    }

    @Test
    public void testQueryCategoryByCategoryId(){
        ArticleCategory temp = new ArticleCategory();
        temp.setCategoryId(1);
        ArticleCategory category = repository.queryCategoryByCategoryId(temp);
        System.out.println(category.getCategoryName());
    }
}
