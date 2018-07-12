package com.dang.java.my.GUI;

import javax.swing.JButton;
import javax.swing.JFrame;

public class DJFrame extends JFrame {
    public static void main(String[] args) {
        JFrame mframe = new JFrame();//框架 frame
        mframe.setTitle("框架 frame");
        mframe.setSize(720, 500);
        mframe.setLocation(300, 100);//Location位置， 初始化位置
        JButton mbtn1 = new JButton("按钮JButton");
        mframe.add(mbtn1);
        mframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭能回收内存
        mframe.setVisible(true);    //Visible 明显的，可见的
    }
}
