package com.dang.note.cglib;

public class UserMapper {

    public User getUserById(Integer id) {
        User user = new User();
        user.setId(id);
        user.setUserName("userName:" + id);
        user.setAge(id);
        return user;
    }
}
