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
    public void testQueryUserById(){
        User login = new User();
        login.setUserId(1);
        login.setUserName("ccc");
        User user = service.getUserByUserName(login);
        System.out.println("*****"+user.getPwd());
        System.out.println("*****"+user.getUserId());
        System.out.println("*****"+user.getUserName());
    }

    @Test
    public void testQueryUserByName(){
        User login = new User();
        login.setUserName("Ber1122");
        User user = service.getUserByUserName(login);
        System.out.println("*****"+user.getPwd());
        System.out.println("*****"+user.getUserId());
        System.out.println("*****"+user.getUserName());
    }


}
