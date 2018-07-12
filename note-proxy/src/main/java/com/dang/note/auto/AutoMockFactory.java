package com.dang.note.auto;

import java.lang.reflect.Field;

public class AutoMockFactory {

    public static <T> T proxy(T obj) {
        String useClassName = Thread.currentThread().getStackTrace()[2].getClassName();
        String path = System.getProperty("user.dir") + "/src/test/java/"
                + useClassName.replaceAll("\\.", "/") + ".methods";
        Class<?> clazz = obj.getClass();
        // 如果有父类  获取父类属性
        while (clazz != Object.class) {
            // 获取实体类的所有属性，返回Field数组
            Field[] fields = clazz.getDeclaredFields();
            // 遍历所有属性
            for (Field field : fields) {
                try {
                    field.setAccessible(true);              // 设置为可访问的
                    Object fieldValue = field.get(obj);     // 获得该属性对应的对象
                    MockMethod mockMethod = new MockMethod();
                    mockMethod.setPath(path);
                    if (field.getType().equals(String.class)) {
                        continue;
                    }
                    Object proxy = mockMethod.createInstance(fieldValue);
                    field.set(obj, proxy);      // 获得该属性为代理对象
                } catch (IllegalArgumentException e) {
                    // class 为 static  不为改属性做代理
                } catch (IllegalAccessException e) {
                    System.err.println(e);
                }
            }
            clazz = clazz.getSuperclass();
        }
        return obj;
    }

}
