package com.dang.java.设计模式.创建型模式;

// http://blog.csdn.net/zhangerqing/article/details/8194653
public class 工厂模式 {
    public static void main(String[] args) {
        SendFactory1 factory = new SendFactory1();
        Sender sender = factory.produce("sms");
        sender.Send();
    }
}

class SendFactory1 {     // 普通工厂模式
    public Sender produce(String type) {
        if ("mail".equals(type)) {
            return new MailSender();
        } else if ("sms".equals(type)) {
            return new SmsSender();
        } else {
            System.out.println("请输入正确的类型!");
            return null;
        }
    }
}

class SendFactory2 { // 多个工厂方法模式

    public Sender produceMail() {
        return new MailSender();
    }

    public Sender produceSms() {
        return new SmsSender();
    }
}

class SendFactory3 {        // 静态工厂方法模式

    public static Sender produceMail() {
        return new MailSender();
    }

    public static Sender produceSms() {
        return new SmsSender();
    }
}

// 两个工厂类   多一个 Provider接口  每一个Sender  一个工厂
//public class SendSmsFactory implements Provider{
//
//    @Override
//    public Sender produce() {
//        return new SmsSender();
//    }
//}
interface Sender {
    public void Send();
}

class MailSender implements Sender {
    @Override
    public void Send() {
        System.out.println("this is mailsender!");
    }
}

class SmsSender implements Sender {
    @Override
    public void Send() {
        System.out.println("this is sms sender!");
    }
}
