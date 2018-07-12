package com.dang.note.cglib;

public class UserService extends UserServiceSuper {

    private UserMapper userMapper = new UserMapper();
    private String beanName = "";

    public User getById(Integer id) {
        return userMapper.getUserById(id);
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
