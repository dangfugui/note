package com.dang.java.设计模式.old;

/*
 模板方法模式：——抽象类应用
 定义一个操作中的算法的骨架，而将一些可变的实现延迟到之类中。
 模板方法模式使得之类可以不改变一个算法的结构即可重新定义改算法的某些定义的步骤//
 */
public class 常2模板方法模式_抽象类应用 {
    public static void main(String[] args) {
        Games g = new Game1();
        g.play();
        Games g2 = new Game2();
        g2.play();

    }
}

//抽象类
abstract class Games {
    //算法骨架
    public void play() {
        System.out.println("父类Games->play()");
        if (result()) {
            System.out.println("恭喜你赢了");
        } else {
            System.out.println("你输了");
        }
    }

    //抽象方法 需要子类实现  可变的
    public abstract boolean result();
}

class Game1 extends Games {
    public boolean result() {
        return true;
    }
}

class Game2 extends Games {
    public boolean result() {
        return false;
    }
}

