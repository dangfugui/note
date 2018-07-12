package com.dang.java.my.util;

import java.util.Stack;

/*
Stack 有时也可以称为“后入先出”（LIFO）集合。换言之，我们在堆栈里最后“压入”的东西将是以后第
一个“弹出”的。和其他所有Java 集合一样，我们压入和弹出的都是“对象”，所以必须对自己弹出的东西
进行“造型”。
一种很少见的做法是拒绝使用Vector 作为一个Stack 的基本构成元素，而是从 Vector里“继承”一个
Stack。这样一来，它就拥有了一个Vector 的所有特征及行为，另外加上一些额外的Stack 行为。很难判断
出设计者到底是明确想这样做，还是属于一种固有的设计。
下面是一个简单的堆栈示例，它能读入数组的每一行，同时将其作为字串压入堆栈。u
*/
public class Stack1 {
    public static void main(String[] args) {
        Stack stack = new Stack();
        for (int a = 0; a < 10; a++) {
            stack.push("item" + a);
        }
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }
}
