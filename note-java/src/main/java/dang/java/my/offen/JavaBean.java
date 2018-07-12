package com.dang.java.my.offen;

/**
 * Created by duang on 2017/3/12.
 */
public class JavaBean {
    private String name = "";
    private int age;

    public JavaBean() {
    }

    public JavaBean(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "JavaBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
