package com.dang.java.设计模式.old;

interface PowerA {
    public void connect();//连接
}

interface PowerB {
    public void insert();
}

/*
适配器模式（Adapter）：将一个类的接口转换成客户希望的另一个接口，//
适配器模式使得原本由接口不兼容而不能一起工作的那些类可以一起工作
  */
public class 常6适配器模式 {
    public static void main(String[] args) {
        PowerAImpl a = new PowerAImpl();
        input(a);
        PowerBImpl b = new PowerBImpl();
        //input(b);
        PowerAdapter adapter = new PowerAdapter(b);
        input(adapter);
    }

    private static void input(PowerA a) {
        a.connect();
    }
}

//适配器   可以适配电源A适配器
class PowerAdapter implements PowerA {
    private PowerB b;

    public PowerAdapter(PowerB b) {
        this.b = b;
    }

    public void connect() {
        b.insert();
    }

}

class PowerAImpl implements PowerA {
    public void connect() {
        System.out.println("PowerAImpl->connect()电源A开始工作");
    }

}

class PowerBImpl implements PowerB {
    public void insert() {
        System.out.println("PowerBImpl->insert()电源B开始工作");
    }

}
