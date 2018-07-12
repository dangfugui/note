package com.dang.java.设计模式.old;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

interface Subject2 {
    public void shopping();
}

public class 常7动态代理模式 {
    public static void main(String[] args) {
        Subject2 s = new SuperMan2();
        Proxy2 p = new Proxy2(s);
        //动态创建一个代理对象（类加载器）//
        Subject2 subject = (Subject2) java.lang.reflect.Proxy.newProxyInstance
                (s.getClass().getClassLoader(), s.getClass().getInterfaces(), p);
        subject.shopping();
    }
}

class SuperMan2 implements Subject2 {
    public void shopping() {
        System.out.println("哥有钱，给媳妇买东西");
    }

}

/**
 * 代理人
 */
class Proxy2 implements InvocationHandler {
    private Object target;

    public Proxy2(Subject2 s) {
        this.target = s; //接收被代理人句柄
    }

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("代理人做的事情" + method.getName());
        method.invoke(target, args);
        System.out.println("代购之后的事情");
        return null;
    }

}

//继承和聚合
