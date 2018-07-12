package com.dang.java.设计模式.old;

interface Flyable {
    public void fly();
}

/*
 策略模式（Strategy pattern）：
 定义一系列的算法，将每一种算法封装起来并可以相互替换使用，策略模式让算法独立于使用它的客户应用而独立变化
 OO设计原则：
 1.面向接口编程（面向抽象编程）
 2.封装变化
 3.多用组合，少用继承
 */
public class 常3策略设计模式_接口应用 {
    public static void main(String[] args) {
        Duck black = new BlackDuck("黑鸭子");
        black.setFly(new FlyImpl());
        black.fly();
        Duck mode = new ModeDuck("模型鸭");
        mode.setFly(new NotFlyImpl());
        mode.fly();
    }
}

//会飞
class FlyImpl implements Flyable {
    public void fly() {
        System.out.println("FlyImpl->fly()我会飞");
    }
}

//不会飞
class NotFlyImpl implements Flyable {
    public void fly() {
        System.out.println("NotFlyImpl->fly()我不会飞");
    }
}

class Duck {
    private String name;
    private Flyable fly;

    public Duck(String name) {
        this.name = name;
    }

    public void setFly(Flyable fly) {
        this.fly = fly;
    }

    public void fly() {
        System.out.println("Duck->fly()我是:" + name);
        fly.fly();//飞 这个行为集成到这个类中
    }
}

//真黑鸭子
class BlackDuck extends Duck {
    public BlackDuck(String name) {
        super(name);
    }

}

//模型鸭子
class ModeDuck extends Duck {
    public ModeDuck(String name) {
        super(name);
    }
}//
