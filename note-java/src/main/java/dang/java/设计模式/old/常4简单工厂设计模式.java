package com.dang.java.设计模式.old;

interface Work {
    public void working();
}

/*
 简单工厂模式是由一个工厂对象决定创建出哪一种产品类的实例 。
简单工厂模式是工厂模式家族中最简单使用的模式

耦合：使用者直接使用了一个具体的类，表示当前类 依赖该具体的类
这样的当这类个具体类发生变化，使用者将会受到影响

通过工厂模式  转化依赖//
 */
public class 常4简单工厂设计模式 {
    public static void main(String[] args) {
        Work work = Factory.getWork(Factory.phone);
        work.working();
        Work work1 = Factory.getWork(Factory.tv);
        work1.working();
    }
}

class Factory {
    public static final int phone = 1;
    public static final int tv = 2;

    public static Work getWork(int product) {
        switch (product) {
            case phone:
                return new TCLPhone();
            case tv:
                return new TCLTV();
            default:
                break;
        }
        return null;
    }

}

class TCLPhone implements Work {
    public void working() {
        System.out.println("TCLPhone->working()手机开始工作");
    }
}

class TCLTV implements Work {
    public void working() {
        System.out.println("TCLTV->working()电视开始工作");
    }
}
