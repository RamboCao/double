package com.star.customer.aop.cjlib.service;

import com.star.customer.aop.User;

import java.util.Collections;
import java.util.List;

/**
 * @author Caolp
 */
public class UserServiceImpl implements IUserService{
    @Override
    public List<User> findUserList() {
        return Collections.singletonList(new User("pdai", 18));
    }

    @Override
    public void addUser() {
        // do something
    }
}
