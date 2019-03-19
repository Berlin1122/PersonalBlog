package com.chenyulin.myblog.repository;

import com.chenyulin.myblog.bean.Article;
import com.chenyulin.myblog.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ArticleRepository {
    @Insert("INSERT INTO tb_article(content,title,status,create_time,last_edit_time,priority,user_id,category_id" +
            ",brief_intro) VALUES(#{content},#{title},#{status},#{createTime},#{lastEditTime},#{priority}" +
            ",#{user.userId},#{category.categoryId},#{briefIntro})")
    int insertArticle(Article article);

    @Select("SELECT COUNT(1) FROM tb_article WHERE user_id = #{userId}")
    int queryArticleCountByUser(final User user);

    @Select("SELECT article_id,title,last_edit_time,category_id,brief_intro FROM tb_article " +
            "WHERE user_id = #{user.userId} LIMIT #{rowIndex},#{pageSize}")
    @Results(id = "articleResult",value = {
            @Result(id=true,column = "article_id",property = "articleId"),
            @Result(column = "last_edit_time",property = "lastEditTime"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "user_id",property = "user.userId"),
            @Result(column = "category_id",property = "category.categoryId"),
            @Result(column = "brief_intro",property = "briefIntro"),
    })
    List<Article> queryArticleByPage(@Param("user")User user,@Param("rowIndex")int rowIndex
            ,@Param("pageSize")int pageSize);

    @Delete("DELETE FROM tb_article WHERE article_id = #{articleId}")
    int deleteArticleById(Article article);

    @Select("SELECT content,title FROM tb_article WHERE article_id=#{articleId}")
    Article queryArticleById(Article article);

    @Select("SELECT title,brief_intro,article_id FROM tb_article WHERE category_id=#{article.category.categoryId} " +
            "LIMIT #{rowIndex},#{pageSize}")
    @ResultMap("articleResult")
    List<Article> queryArticleByCategoryId(@Param("article") Article article,@Param("rowIndex")int rowIndex
            ,@Param("pageSize")int pageSize);

    @Select("SELECT COUNT(1) FROM tb_article WHERE category_id=#{category.categoryId}")
    int queryCountByCategoryId(Article article);
}
