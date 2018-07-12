package com.dang.java.设计模式.old;

interface Subject {//主题

    public void shopping();
}

/*
代理模式（proxy）：为其他对象提供一种代理 以控制对这个对象的访问
代理模式 说白了就是“真实对象”的代表，在访问对象时引入一定程度的间接性，
因为这种间接性可以附加多种途径//
 */
public class 常5静态代理模式 {
    public static void main(String[] args) {
        // TODO 自动生成的方法存根
        SuperMan man = new SuperMan();//被代理人
        Proxy proxy = new Proxy(man);//代理人
        proxy.shopping();
    }
}

//被代理人
class SuperMan implements Subject {
    private String name;

    public void shopping() {
        //在买之前要做大量的评估工作 /
        System.out.println("为媳妇购买进口化妆品");
    }
}

//代理人
class Proxy implements Subject {
    private Subject target;//被代理人

    public Proxy(Subject target) {
        this.target = target;
    }

    public void shopping() {
        // 代理要做的事情---
        System.out.println("Proxy->shopping（）代理人开始进行评估");
        target.shopping();
        System.out.println("Proxy->shopping（）代理后的评估调查");
    }

}
