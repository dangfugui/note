package com.dang.java.设计模式.创建型模式;

public class 单例模式 {
}

class Singleton {

    /* 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载 */
    private static Singleton instance = null;

    /* 私有构造方法，防止被实例化 */
    private Singleton() {
    }

    /* 获取实例 */
    public static Singleton getInstance() {     //  @@推荐
        return SingletonFactory.instance;
    }

    /* 静态工程方法，创建实例 */
    public static Singleton getInstance1() {
        if (instance == null) {
            instance = new Singleton(); // !!! 不安全
        }
        return instance;
    }

    public static synchronized Singleton getInstance2() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public static Singleton getInstance3() {
        if (instance == null) {
            synchronized(instance) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static Singleton getInstance4() {    // 因为我们只需要在创建类的时候进行同步，所以只要将创建和getInstance()分开，单独为创建加synchronized关键字
        if (instance == null) {
            syncInit();
        }
        return instance;
    }

    private static synchronized void syncInit() {
        if (instance == null) {
            instance = new Singleton();
        }
    }

    /* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */
    public Object readResolve() {
        return instance;
    }

    private static class SingletonFactory {
        private static Singleton instance = new Singleton();
    }

}
