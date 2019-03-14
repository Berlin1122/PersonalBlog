package com.chenyulin.myblog.repository;

import com.chenyulin.myblog.bean.User;
import org.apache.ibatis.annotations.*;

public interface UserRepository {

    @Insert("insert into tb_user(user_name,pwd) values(#{userName},#{pwd})")
    public int insertUser(User user);

    @Select("select user_id,user_name,pwd from tb_user where user_name=#{userName}")
    @Results(id="userResult",value = {
            @Result(id=true,column = "user_id",property = "userId"),
            @Result(column = "user_name",property = "userName"),
            @Result(column = "pwd",property = "pwd")
    })
    public User queryUserByUserName(final User user);


    @Select("select * from tb_user where user_id=#{userId}")
    @ResultMap("userResult")
    public User queryUserById(final User user);

}
