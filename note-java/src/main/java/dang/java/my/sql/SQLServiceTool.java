package com.dang.java.my.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServiceTool {
    private String username = "sa";
    private String password = "root";

    public SQLServiceTool()
            throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        //装载驱动程序 u
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
        //连接字符串
        String url = "jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=mydata";
        //建立连接
        Connection conn = DriverManager.getConnection(url, username, password);
        System.out.println("ok");

    }

    public static void main(String[] args)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        new SQLServiceTool();
    }
}
