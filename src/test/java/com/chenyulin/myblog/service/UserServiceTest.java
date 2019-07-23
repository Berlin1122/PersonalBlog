package com.chenyulin.myblog.service;

import com.chenyulin.myblog.bean.User;
import com.chenyulin.myblog.repository.UserRepository;
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
public class UserServiceTest {

    @Resource
    private UserService service;

    @Ignore
    @Test
    public void testQueryUserById() {
        User user = service.getUserByUserName("ccc");
        System.out.println("*****" + user.getPwd());
        System.out.println("*****" + user.getUserId());
        System.out.println("*****" + user.getUserName());
    }

    @Test
    @Ignore
    public void testQueryUserByName() {

        User user = service.getUserByUserName("Ber1122");
        System.out.println("*****" + user.getPwd());
        System.out.println("*****" + user.getUserId());
        System.out.println("*****" + user.getUserName());
    }


}
