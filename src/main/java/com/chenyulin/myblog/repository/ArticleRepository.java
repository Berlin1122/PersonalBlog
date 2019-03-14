package com.chenyulin.myblog.repository;

import com.chenyulin.myblog.bean.Article;
import org.apache.ibatis.annotations.Insert;

public interface ArticleRepository {
    @Insert("INSERT INTO tb_article(content,title,status,create_time,last_edit_time,priority,user_id,category_id" +
            ",brief_intro) VALUES(#{content},#{title},#{status},#{createTime},#{lastEditTime},#{priority}" +
            ",#{user.userId},#{category.categoryId},#{briefIntro})")
    int insertArticle(Article article);

}
