package com.dang.java.my.GUI;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class JFrame_BorderLayout extends JFrame {//框架 frame
    JButton btn_east, btn_west, btn_south, btn_north, btn_center, btn;

    public JFrame_BorderLayout() {//border边界
        // TODO 自动生成的构造函数存根
        this.setTitle("框架  JFrame");
        this.setSize(700, 500);
        this.setLocation(300, 100);//Location位置， 初始化位置
        btn_east = new JButton("east");
        btn_west = new JButton("west");
        btn_south = new JButton("south");
        btn_north = new JButton("north");
        btn_center = new JButton("north");
        btn_center = new JButton("center");
        btn = new JButton("btn");
        this.add(btn, BorderLayout.EAST);
        this.add(btn_east, BorderLayout.EAST);
        this.add(btn_west, BorderLayout.WEST);
        this.add(btn_south, BorderLayout.SOUTH);
        this.add(btn_north, BorderLayout.NORTH);
        this.add(btn_center, BorderLayout.CENTER);

        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);//关闭能回收内存
        this.setVisible(true);//Visible 明显的，可见的
    }

    //东east西west南south北north中center
    public static void main(String[] args) {
        JFrame_BorderLayout dframe = new JFrame_BorderLayout();
    }
}
