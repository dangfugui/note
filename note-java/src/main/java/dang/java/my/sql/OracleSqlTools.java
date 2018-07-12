package com.dang.java.my.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleSqlTools {
    /*数据库连接，oracle代表链接的是oracle数据库；
       thin:@MyDbComputerNameOrIP代表的是数据库所在的IP地址（可以保留thin:） ；
       1521代表链接数据库的端口号；ORCL代表的是数据库名*/
    Statement stmt = null; //创建状态
    ResultSet rs = null;//查询返回的结果集
    private Connection conn = null;
    private String UserName = "scott";// 数据库用户登陆名 ( 也有说是 schema 名字的 )u
    private String Password = "tiger";// 密码
    private String url = "jdbc:oracle:thin:@192.168.255.10:1521:ORCL";

    public OracleSqlTools() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }//需要下载oracle JDBC的
        try {
            conn = DriverManager.getConnection(url, UserName, Password);
            stmt = conn.createStatement(); // 创建状态
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }

    }

    public Statement getStatement() {
        return stmt;
    }

    /**
     * 增删改查
     *
     * @param sql 执行的SQL语句
     *
     * @return 影响的行数
     */
    public int update(String sql) {
        try {
            return stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 执行查询语句
     *
     * @param sql 查询SQL语句
     *
     * @return 查询到的结果集
     */
    public ResultSet query(String sql) {
        try {
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return rs;
        /*while(rs.next()){
            long id=rs.getLong("id");
			String name=rs.getString("name");
			System.out.println(id+"_"+name);
		}*/
    }

    /**
     * 释放资源
     */
    public void close() {
        //释放资源
        try {
            rs.close();
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        try {
            stmt.close();
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }

}
