package com.dang.java.设计模式.结构型模式;

// 代理模式就是多一个代理类出来，替原对象进行一些操作
public class 代理模式 {
    public static void main(String[] args) {
        Sourceable1 source = new Proxy();
        source.method();
    }
}

class Proxy implements Sourceable1 {
    private Source2 source;

    public Proxy() {
        super();
        this.source = new Source2();
    }

    @Override
    public void method() {
        before();
        source.method();
        atfer();
    }

    private void atfer() {
        System.out.println("after proxy!");
    }

    private void before() {
        System.out.println("before proxy!");
    }
}

interface Sourceable1 {
    public void method();
}

class Source2 implements Sourceable1 {
    @Override
    public void method() {
        System.out.println("the original method!");
    }
}
//代理模式的应用场景：
// 如果已有的方法在使用的时候需要对原有的方法进行改进，此时有两种办法：
// 1、修改原有的方法来适应。这样违反了“对扩展开放，对修改关闭”的原则。
// 2、就是采用一个代理类调用原有的方法，且对产生的结果进行控制。这种方法就是代理模式。
// 使用代理模式，可以将功能划分的更加清晰，有助于后期维护！