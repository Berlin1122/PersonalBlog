package com.chenyulin.myblog.repository;

import com.chenyulin.myblog.bean.ArticleCategory;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArticleCategoryRepository {

    @Select("SELECT category_id,category_name FROM tb_article_category")
    @Results(id = "articleCategoryResult",value = {
            @Result(id=true,column = "category_id",property = "categoryId"),
            @Result(column = "category_name",property = "categoryName"),
    })
    List<ArticleCategory> queryAllArticleCategory();

    @Select("SELECT category_name,category_id FROM tb_article_category WHERE category_name=#{categoryName}")
    @ResultMap("articleCategoryResult")
    ArticleCategory queryCategoryByCategoryName(final ArticleCategory category);

}
