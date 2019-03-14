package com.chenyulin.myblog.service;

import com.chenyulin.myblog.bean.User;
import com.chenyulin.myblog.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserRepository repository;

    public int addUser(User user){
        return repository.insertUser(user);
    }

    public User getUserByUserName(User user){
        return repository.queryUserByUserName(user);
    }


}
