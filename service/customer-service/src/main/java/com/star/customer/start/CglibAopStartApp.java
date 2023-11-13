package com.star.customer.start;

import com.star.customer.aop.cjlib.proxy.UserLogProxy;
import com.star.customer.aop.cjlib.service.UserServiceImpl;


/**
 * @author Caolp
 */
public class CglibAopStartApp {
    public static void main(String[] args) {
        UserServiceImpl userService = (UserServiceImpl )new UserLogProxy().userLogProxy(new UserServiceImpl());
        userService.findUserList();
        userService.addUser();
    }
}
