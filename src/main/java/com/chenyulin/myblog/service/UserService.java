package com.chenyulin.myblog.service;

import com.chenyulin.myblog.bean.User;
import com.chenyulin.myblog.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserRepository repository;

    public User getUserByUserName(String userName) {
        return repository.queryUserByUserName(userName);
    }


}
