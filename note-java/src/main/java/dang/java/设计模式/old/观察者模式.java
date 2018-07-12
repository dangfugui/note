package com.dang.java.设计模式.old;

import java.util.ArrayList;
import java.util.List;

interface Observer {//观察者接口

    public void update(String message);
}

interface Subject1 {//抽象的被观察者

    /**
     * 增加订阅者
     *
     * @param observer
     */
    public void attach(Observer observer);

    /**
     * 删除订阅者
     *
     * @param observer
     */
    public void detach(Observer observer);

    /**
     * 通知订阅者更新消息
     */
    public void notify(String message);
}

/**
 * Created by duang on 2017/3/13.
 */
public class 观察者模式 {
    public static void main(String[] args) {
        SubscriptionSubject mSubscriptionSubject = new SubscriptionSubject();
        //创建微信用户
        WeixinUser user1 = new WeixinUser("杨影枫");
        WeixinUser user2 = new WeixinUser("月眉儿");
        WeixinUser user3 = new WeixinUser("紫轩");
        //订阅公众号
        mSubscriptionSubject.attach(user1);
        mSubscriptionSubject.attach(user2);
        mSubscriptionSubject.attach(user3);
        //公众号更新发出消息给订阅的微信用户
        mSubscriptionSubject.notify("刘望舒的专栏更新了");
    }
}

class WeixinUser implements Observer {
    // 微信用户名
    private String name;

    public WeixinUser(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + "-" + message);
    }
}

//被观察者
class SubscriptionSubject implements Subject1 {
    //储存订阅公众号的微信用户
    private List<Observer> weixinUserlist = new ArrayList<Observer>();

    @Override
    public void attach(Observer observer) {
        weixinUserlist.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        weixinUserlist.remove(observer);
    }

    @Override
    public void notify(String message) {
        for (Observer observer : weixinUserlist) {
            observer.update(message);
        }
    }
}