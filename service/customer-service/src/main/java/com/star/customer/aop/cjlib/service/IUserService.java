package com.star.customer.aop.cjlib.service;

import com.star.customer.aop.User;

import java.util.List;

/**
 * @author Caolp
 */
public interface IUserService {
    /**
     * find user list.
     *
     * @return user list
     */
    List<User> findUserList();

    /**
     * add user
     */
    void addUser();
}
