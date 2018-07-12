package com.dang.java.my.GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Test implements ActionListener {
    JFrame f;//主要的框架
    JPanel da;//用来分层
    JPanel g1;//上方的面板
    JScrollPane g2;//下面有滚动条的面板
    JLabel b1;//四个标签
    JLabel b2;
    JLabel b3;
    JLabel b4;
    JTextField text;//用来输入名字的文本框
    JPanel bu;//用来放两个单选框
    JRadioButton button1;//两个单选框
    JRadioButton button2;
    JPanel bo;//用来放三个复选框
    JCheckBox box1;//三个复选框
    JCheckBox box2;
    JCheckBox box3;
    JComboBox xia;//一个下拉框
    String a[];
    JButton ok;//确定按钮
    JTextArea o;

    public Test() {
        //实现声明
        JFrame f = new JFrame("试一下看怎么样");
        JPanel g1 = new JPanel();
        JScrollPane g2 = new JScrollPane();
        JLabel b1 = new JLabel("姓名：");
        JLabel b2 = new JLabel("性别：");
        JLabel b3 = new JLabel("爱好：");
        JLabel b4 = new JLabel("籍贯");
        JTextField text = new JTextField();
        JPanel bu = new JPanel();
        JRadioButton button1 = new JRadioButton("男");
        JRadioButton button2 = new JRadioButton("女");
        bu.add(button1);
        bu.add(button2);
        JPanel bo = new JPanel();
        JCheckBox box1 = new JCheckBox("体育");
        JCheckBox box2 = new JCheckBox("音乐");
        JCheckBox box3 = new JCheckBox("美术");
        bo.add(box1);
        bo.add(box2);
        bo.add(box3);
        String a[] = {"ok?", "0000"};
        JComboBox xia = new JComboBox(a);
        g1.setLayout(new GridLayout(4, 2));
        g1.add(b1);
        g1.add(text);
        g1.add(b2);
        g1.add(bu);
        g1.add(b3);
        g1.add(bo);
        g1.add(b4);
        g1.add(xia);
        JTextArea o = new JTextArea();
        g2.add(o);
        JButton ok = new JButton("确定");
        //g2.add(ok);
        JPanel da = new JPanel();
        da.setLayout(new GridLayout(3, 1));
        da.add(g1);
        da.add(g2);
        da.add(ok);
        ok.addActionListener(this);
        //设标题框架
        f.add(da);
        f.setLocation(500, 500);
        f.setSize(600, 600);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String args[]) {
        Test r = new Test();
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(text.getText());
    }
}
