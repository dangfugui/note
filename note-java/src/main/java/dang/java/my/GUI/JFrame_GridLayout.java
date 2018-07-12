package com.dang.java.my.GUI;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class JFrame_GridLayout extends JFrame {
    public JFrame_GridLayout() {
        // TODO 自动生成的构造函数存根
        this.setLayout(new GridLayout(3, 3, 15, 20));
        this.setTitle("GridLayout");
        this.setSize(700, 500);
        this.setLocation(300, 100);//Location位置， 初始化位置
        for (int i = 0; i < 9; i++) {
            JButton btn = new JButton("btn" + i);
            this.add(btn);
        }
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame_GridLayout grid = new JFrame_GridLayout();
    }
}
