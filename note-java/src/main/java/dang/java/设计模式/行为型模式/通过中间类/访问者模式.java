package com.dang.java.设计模式.行为型模式.通过中间类;

public class 访问者模式 {    // Visitor
    public static void main(String[] args) {

        Visitor visitor = new MyVisitor();
        Subject sub = new MySubject();
        sub.accept(visitor);
    }
}

interface Visitor { // 存放要访问的对象
    public void visit(Subject sub);
}

class MyVisitor implements Visitor {

    @Override
    public void visit(Subject sub) {
        System.out.println("visit the subject：" + sub.getSubject());
    }
}

interface Subject {     // Subject类，accept方法，接受将要访问它的对象，getSubject()获取将要被访问的属性，
    public void accept(Visitor visitor);

    public String getSubject();
}

class MySubject implements Subject {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getSubject() {
        return "love";
    }
}