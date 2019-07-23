package com.chenyulin.myblog.repository;

import com.chenyulin.myblog.bean.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

@MapperScan("com.chenyulin.myblog.repository")
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Resource
    private UserRepository repository;

    @Ignore
    @Test
    public void testInsertUser() {
        User user = new User();
        user.setUserName("test");
        user.setPwd("test");

        int row = repository.insertUser(user);
        assertEquals(1, row);
    }

    @Test
    @Ignore
    public void testQueryUserByName() {
        User user = new User();
        user.setUserName("Ber1122");
        User dbUser = repository.queryUserByUserName("Ber1122");
        System.out.println(dbUser.getUserName());
    }

}
