package com.dang.java.my.GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

public class Frame_Jpanel extends JFrame {
    JPanel panel1, panel2;            //面板
    JLabel lable;                    //标签
    JTextField textfield;            //文本框
    JPasswordField passwordfield;    //密码框
    JButton btn[] = new JButton[10];    //按钮
    JCheckBox checkbox;                //复选框
    JRadioButton radipbutton;        //单选框
    ButtonGroup buttongroup;        //按钮组  默认组里的按钮只能选一个
    JList list;                        //new JList(String[]);
    JComboBox combobox;//.
    JScrollPane scrollpane;//滚动条  .add(list);.setVisibleRowcount(默认滚显示几个);
    JSplitPane splitpane;//拆分

    public Frame_Jpanel() {
        // TODO 自动生成的构造函数存根
        inpanel();

        this.setLayout(new BorderLayout());

        this.add(panel1, BorderLayout.WEST);
        this.add(panel2, BorderLayout.EAST);

        this.setTitle("Panel");
        this.setSize(700, 500);
        this.setLocation(300, 100);//Location位置， 初始化位置
        this.setResizable(true);//能变大小
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        Frame_Jpanel panel = new Frame_Jpanel();

    }

    private void inpanel() {
        // TODO 自动生成的方法存根
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel1.setLayout(new GridLayout(5, 2));
        for (int i = 0; i < btn.length; i++) {
            btn[i] = new JButton("btn" + i);
            panel1.add(btn[i]);
        }
        JButton bt = new JButton("panel2");
        panel2.add(bt);
    }
}
