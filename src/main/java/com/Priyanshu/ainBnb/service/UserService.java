package com.Priyanshu.ainBnb.service;

import com.Priyanshu.ainBnb.entity.User;

public interface UserService {

    User getUserById(Long id);

    User getUserByEmail(String email);

    User saveUser(User user);
}
