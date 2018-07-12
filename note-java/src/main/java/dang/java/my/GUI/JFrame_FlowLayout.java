package com.dang.java.my.GUI;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class JFrame_FlowLayout extends JFrame {
    JButton btn[] = new JButton[5];
    String str[] = {"1", "2", "3", "4", "5"};

    public JFrame_FlowLayout() {
        // TODO 自动生成的构造函数存根
        for (int i = 0; i < str.length; i++) {
            btn[i] = new JButton(str[i]);
        }
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        //默认BorderLayout

        this.setTitle("框架  JFrame flow layout");
        this.setSize(700, 500);
        this.setLocation(300, 100);//Location位置， 初始化位置
        for (int i = 0; i < str.length; i++) {
            this.add(btn[i]);
        }
        this.setResizable(false);//可变尺寸的；可调整大小的
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);//关闭能回收内存
        this.setVisible(true);//Visible 明显的，可见的
    }

    public static void main(String[] args) {
        JFrame_FlowLayout flow = new JFrame_FlowLayout();
    }
}
