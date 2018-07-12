package com.dang.java.my.vince;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Cat {
    public String name;
    public int age;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Cat() {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) throws Exception {
        //获取一个Class类对象的三种方式
        Cat cat = new Cat("mimi", 3);
        Class catClass1 = cat.getClass();
        Class catClass2 = Cat.class;
        Class catClass3 = Class.forName("com.dang.java.my.vince.Cat");
        //通过类信息创建对象
        Cat cat2 = (Cat) catClass2.newInstance();//调用无参构造方法u
        //获取构造方法
        Constructor[] cs = catClass2.getConstructors();
        for (int a = 0; a < cs.length; a++) {
            System.out.println(cs[a].getName());
        }
        //使用有参构造方法
        Constructor c = catClass2.getConstructor(String.class, int.class);
        Cat cat3 = (Cat) c.newInstance("catname1", 2);
        cat2.name = "dang";
        System.out.println(cat2.name);
        //得到类信息
        System.out.println("包名：" + catClass2.getPackage().getName());
        System.out.println("类名：" + catClass2.getName());
        Method[] method = catClass2.getMethods();//获得所有方法
        //getDeclaredMethods//获取本类中的方法 包括私有方法
        //.getFields//获得属性
        //getDeclaredFields//获取本类中所有包括私有的属
        for (int i = 0; i < method.length; i++) {
            System.out.println("方法：" + method[i].getName());
            System.out.println("方法类型：" + Modifier.toString(method[i].getModifiers()));
        }
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
}
