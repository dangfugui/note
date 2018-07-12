package com.dang.note.proxy.service.impl;

import com.dang.note.proxy.service.UserService;

public class UserServiceImpl implements UserService {

    public Object getUser() {
        return new String("user");
    }
}
