package com.dang.java.my.offen;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class 反射 {
    public static void main(String[] args) throws Exception {
        JavaBean javaBean = new JavaBean("dang", 24);
        Class<?> clazz = Class.forName("com.dang.java.my.offen.JavaBean");
        Object obj = clazz.newInstance();
        Constructor<?> constructor = clazz.getConstructor(String.class, int.class);
        obj = constructor.newInstance("dang", 24);
        System.out.println(clazz);
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);//设置访问属性为true
            String str = field.get(obj).toString();//只能是public的成员变量
            System.out.println("field:" + field.getName() + ">>value:" + str);
        }
        for (Method method : clazz.getMethods()) {
            System.out.println("method:" + method.getName());
        }
        Method setNameMethod = clazz.getMethod("setName", String.class);
        if (setNameMethod != null) {
            setNameMethod.invoke(obj, "dangdang");
        }
        Method getNameMethod = clazz.getMethod("getName");
        if (getNameMethod != null) {
            String getNameStr = (String) getNameMethod.invoke(obj);
            System.out.println("getNameMethod:" + getNameStr);
        }
        System.out.println(obj.toString());
    }

}
