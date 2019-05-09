package com.chenyulin.myblog.repository;

import com.chenyulin.myblog.bean.ArticleCategory;
import com.chenyulin.myblog.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ArticleCategoryRepository {

    @Select("SELECT category_id,category_name,user_id FROM tb_article_category " +
            "WHERE user_id=#{userId}")
    @Results(id = "articleCategoryResult", value = {
            @Result(id = true, column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "user_id", property = "user.userId")
    })
    List<ArticleCategory> queryArticleCategoryByUserId(User user);

    @Delete("DELETE FROM tb_article_category WHERE category_id=#{categoryId}")
    @ResultMap("articleCategoryResult")
    int deleteCategory(ArticleCategory category);

    @Insert("INSERT INTO tb_article_category (category_name,user_id) VALUES (#{categoryName},#{user.userId})")
    @ResultMap("articleCategoryResult")
    int insertArticleCategory(ArticleCategory category);

    @Select("SELECT category_name FROM tb_article_category WHERE category_id=#{categoryId}")
    @ResultMap("articleCategoryResult")
    ArticleCategory queryCategoryByCategoryId(ArticleCategory category);

}
