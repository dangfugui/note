package com.dang.java.设计模式.old;

/*
 单例模式：保证一个类仅有一个实例，并提供一个访问它的全局访问点
1.构造函数私有化	
2.声明一个本类对象	
3.给外部提供一个静态方法获取实例
实现方法： 饿汉式  懒汉式

通常在工具类中使用
当一个类中没有属性 （属性都是常量）e//
该类作为工具类使用非常频繁
*/
public class 常1单例设计模式 {

}

//饿汉式
class Single {
    private static Single single = new Single();

    private Single() {
    }//构造方法私有化   为了防止在本类外部实例化

    public static Single getInstance() {
        return single;
    }
}

//懒汉式
class Single2 {
    private static Single2 single;

    private Single2() {
    }//构造方法私有化  为了防止在本类外部实例化

    public static Single2 getInstance() {
        if (single == null) {
            single = new Single2(); // 建议第一种  这种线程不安全
        }
        return single;
    }
}

//  这种单例最好
class Single3 {
    private Single3 single3;

    private Single3() {
    }

    public static Single3 getInstance() {
        return Single_3.getSingle();
    }

    private static class Single_3 {
        private static Single3 single3 = new Single3();

        public static Single3 getSingle() {
            return single3;
        }
    }
}
