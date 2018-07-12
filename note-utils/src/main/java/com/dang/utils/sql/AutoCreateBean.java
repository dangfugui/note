package com.dang.utils.sql;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dang.utils.string.StringUtils;
import com.mysql.jdbc.JDBC42ResultSet;

/**
 * 根据数据库表结构 自动生成java Bean
 *
 * @author DLHT 2016年3月4日下午5:00:28 AutoCreateClass.java DLHT
 */
public class AutoCreateBean {

    public static void main(String[] args) throws Exception {
        AutoCreateBean auto = new AutoCreateBean("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/networkflow",
                "root", "");
        List<String> list = auto.TBlist();
        auto.GenEntity(list, "com/bean");

    }

    private final String DRIVER;    // mysql 驱动类
    private final String USER;      // 数据库登录用户名
    private final String PASSWORD;  // 数据库登录
    private final String URL;       // 数据库连接地址

    private String tablename;
    private String[] colnames; // 列名数组
    private String[] colTypes; // 列名类型数组
    private String[] comment;    // 列名备注
    private boolean f_util = false; // 是否需要导入包java.util.*
    private boolean f_sql = false; // 是否需要导入包java.sql.*
    private boolean mapUnderscoreToCamelCase = true;

    public AutoCreateBean(String driver, String url, String user, String password) {
        this.DRIVER = driver;
        this.URL = url;
        this.USER = user;
        this.PASSWORD = password;
    }

    /**
     * 获取指定数据库中包含的表 TBlist
     *
     * @return 返回所有表名(将表名放到一个集合中)
     *
     * @throws Exception
     * @time 2016年3月4日下午5:54:52
     * @packageName com.util
     */
    public List<String> TBlist() throws Exception {
        // 访问数据库 采用 JDBC方式
        Class.forName(DRIVER);

        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

        DatabaseMetaData md = con.getMetaData();

        List<String> list = null;

        ResultSet rs = md.getTables(null, null, null, null);
        if (rs != null) {
            list = new ArrayList<String>();
        }
        while (rs.next()) {
            //            System.out.println("|表" + (i++) + ":" + rs.getString("TABLE_NAME"));
            String tableName = rs.getString("TABLE_NAME");
            list.add(tableName);
        }
        rs = null;
        md = null;
        con = null;
        return list;
    }

    public void GenEntity(List<String> TBlist, String packageName) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSetMetaData rsmd = null;

        // 访问数据库 采用 JDBC方式
        Class.forName(DRIVER);  // 1加载驱动程序
        conn = DriverManager.getConnection(URL, USER, PASSWORD);    // 2获得数据库的连接
        Statement statement = conn.createStatement();     // 3通过数据库的连接操作数据库，实现增删改查
        // 4 预编译的,对于批量处理可以大大提高效率. 也叫JDBC存储过程
        // select * from information_schema.columns where table_schema = 'peixun' and table_name = 'peixun_course';
        pstmt = conn.prepareStatement("select * from information_schema.columns where table_name = ?");
        for (int k = 0; k < TBlist.size(); k++) {
            tablename = TBlist.get(k);
            pstmt.setString(1, tablename);
            ResultSet resultSet = pstmt.executeQuery();
            int size = (int) ((JDBC42ResultSet) resultSet).getUpdateCount();
            // 共有多少列
            colnames = new String[size];
            colTypes = new String[size];
            comment = new String[size];
            //while (resultSet.next()){
            for (int i = 0; i < size; i++) {
                resultSet.next();
                if (mapUnderscoreToCamelCase) {
                    colnames[i] = StringUtils.lineToHump(resultSet.getString("COLUMN_NAME").toLowerCase());
                } else {
                    colnames[i] = resultSet.getString("COLUMN_NAME").toLowerCase();
                }

                colTypes[i] = resultSet.getString("DATA_TYPE").toLowerCase();
                if (colTypes[i].equalsIgnoreCase("datetime")) {
                    f_util = true;
                }
                if (colTypes[i].equalsIgnoreCase("image")
                        || colTypes[i].equalsIgnoreCase("text")) {
                    f_sql = true;
                }
                comment[i] = resultSet.getString("COLUMN_COMMENT");
            }
            markerBean(initcap(StringUtils.lineToHump(tablename)), parse(), packageName);
        }
        pstmt = null;
        rsmd = null;
        conn = null;
    }

    /**
     * 解析处理(生成实体类主体代码)
     */
    private String parse() {
        StringBuffer sb = new StringBuffer();
        sb.append("import java.io.Serializable;\r\n");
        if (f_util) {
            sb.append("import java.util.Date;\r\n");
        }
        if (f_sql) {
            sb.append("import java.sql.*;\r\n\r\n\r\n");
        }
        sb.append("public class " + initcap(StringUtils.lineToHump(tablename)) + " implements Serializable {\r\n");
        sb.append("\r\nprivate static final long serialVersionUID = 666L;\r\n");
        processAllAttrs(sb);
        processAllMethod(sb);
        sb.append("}\r\n");

        return sb.toString();

    }

    /**
     * 创建java 文件 将生成的属性 get/set 方法 保存到 文件中 markerBean
     *
     * @param className 类名称
     * @param content   类内容 包括属性 getset 方法
     *
     * @time 2015年9月29日下午4:15:22
     * @packageName fanshe
     */
    public void markerBean(String className, String content, String packageName) {
        String folder = System.getProperty("user.dir") + "/src/" + packageName + "/";

        File file = new File(folder);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = folder + className + ".java";

        try {
            File newdao = new File(fileName);
            FileWriter fw = new FileWriter(newdao);
            fw.write("package\t" + packageName.replace("/", ".") + ";\r\n");
            fw.write(content);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成所有的方法
     *
     * @param sb
     */
    private void processAllMethod(StringBuffer sb) {
        for (int i = 0; i < colnames.length; i++) {
            sb.append("\tpublic void set" + initcap(colnames[i]) + "("
                    + sqlType2JavaType(colTypes[i]) + " " + colnames[i]
                    + "){\r\n");
            sb.append("\t\tthis." + colnames[i] + " = " + colnames[i] + ";\r\n");
            sb.append("\t}\r\n");

            sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get"
                    + initcap(colnames[i]) + "(){\r\n");
            sb.append("\t\treturn " + colnames[i] + ";\r\n");
            sb.append("\t}\r\n");
        }
    }

    /**
     * 解析输出属性
     *
     * @return
     */
    private void processAllAttrs(StringBuffer sb) {
        for (int i = 0; i < colnames.length; i++) {
            if (StringUtils.isEmpty(comment[i])) {
                sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " " + colnames[i] + ";\r\n");
            } else {
                sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " " + colnames[i] + ";    //" + comment[i]
                        + "\r\n");
            }

        }
    }

    /**
     * 把输入字符串的首字母改成大写
     *
     * @param str
     *
     * @return
     */
    private String initcap(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    private String sqlType2JavaType(String sqlType) {
        if (sqlType.equalsIgnoreCase("bit")) {
            return "Boolean";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("int")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "Long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("decimal")
                || sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real")) {
            return "Double";
        } else if (sqlType.equalsIgnoreCase("money")
                || sqlType.equalsIgnoreCase("smallmoney")) {
            return "Double";
        } else if (sqlType.equalsIgnoreCase("varchar")
                || sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar")
                || sqlType.equalsIgnoreCase("nchar")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime")
                || sqlType.equalsIgnoreCase("date")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "Blob";
        } else if (sqlType.equalsIgnoreCase("text")) {
            return "Clob";
        }
        return null;
    }

}