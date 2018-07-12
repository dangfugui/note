package com.dang.note.proxy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.dang.note.proxy.service.UserService;
import com.dang.note.proxy.service.impl.UserServiceImpl;

import sun.misc.ProxyGenerator;

public class JavaProxy {

    public static <T> T getProxy(final T target) {
        return (T) Proxy.newProxyInstance(JavaProxy.class.getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    // UserService target = new UserServiceImpl();

                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("执行前");
                        Object result = method.invoke(target, args);
                        System.out.println("执行后");
                        return result;
                    }
                });

    }

    public static void buildProxyClass(String name, Class[] classArg) throws IOException {
        byte[] bytes = ProxyGenerator.generateProxyClass(name, classArg);
        String fileName = System.getProperty("user.dir") + "/note-proxy/target/" + name + ".class";
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }

    // javaProxy  的缺点是必须要有接口 ！！
    public static void main(String[] args) throws IOException {
        UserService userService = JavaProxy.getProxy(new UserServiceImpl());
        Object res = userService.getUser();
        System.out.println(res);

        JavaProxy.buildProxyClass("UserService$java Proxy", new Class[] {UserService.class});

    }
}
