package com.dang.note.cglib;

import com.dang.note.auto.MockMethod;

public class TestCglib {

    public static void main(String[] args) {
        MockMethod cglibProxy = new MockMethod();

        UserService userService = new UserService();
        userService.setBeanName("111111");
        userService = (UserService) cglibProxy.createInstance(userService);
        System.out.println(userService.getBeanName());
        userService.setBeanName("5555");
        System.out.println(userService.getBeanName());
    }
}
