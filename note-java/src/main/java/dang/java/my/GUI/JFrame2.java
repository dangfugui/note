package com.dang.java.my.GUI;

import javax.swing.JButton;
import javax.swing.JFrame;

public class JFrame2 extends JFrame {//框架 frame
    JButton btn1 = null;

    public JFrame2() {
        // TODO 自动生成的构造函数存根
        this.setTitle("框架  JFrame");
        this.setSize(700, 500);
        this.setLocation(300, 100);//Location位置， 初始化位置
        btn1 = new JButton("JButton");
        this.add(btn1);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);//关闭能回收内存
        this.setVisible(true);//Visible 明显的，可见的
    }

    public static void main(String[] args) {
        JFrame2 dframe = new JFrame2();
    }
}
