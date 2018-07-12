package com.dang.java.my.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by duang on 2017/3/13.
 */
public class MysqlTools {
    private static Connection connection;

    private MysqlTools() throws Exception {
        connection = getConnection();
    }

    private static Connection getConnection() throws Exception {
        String url =
                "jdbc:mysql://172.0.0.1:3306/test?useUnicode=true&characterEncoding=utf-8&autoreconnect=true"
                        + "&allowMultiQueries=true";
        String username = "root";
        String password = "dang";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    public static Statement getStatement() throws SQLException {
        Statement statement = connection.createStatement();
        return statement;
    }

    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
        String username = "root";
        String password = "dang";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        //statement.executeQuery("INSERT DELETE UPDATE ");
        ResultSet resultSet = statement.executeQuery("select * FROM students;");
        while (resultSet.next()) {
            int id = resultSet.getInt("sid");
            System.out.println(id);
        }
        resultSet.close();
        statement.close();
    }

    @Override
    protected void finalize() throws Throwable {
        connection.close();
        super.finalize();
    }
}
