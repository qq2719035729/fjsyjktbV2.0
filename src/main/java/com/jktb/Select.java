package com.jktb;

import com.cs.JDBC1;
import com.tools.In;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class Select {
    private static Logger logger = LogManager.getLogger(Select.class.getName());
    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/fjsdxy?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    //MySQL账号密码
    static final String USER = "root";
    static final String PASS = "hx020730";
     ArrayList<Integer> id = new ArrayList<Integer>();
     ArrayList<Integer> province = new ArrayList<>();
     ArrayList<Integer> city = new ArrayList<>();
     ArrayList<Integer> county = new ArrayList<>();
     ArrayList<String> come_where = new ArrayList<>();
     ArrayList<String> move_tel = new ArrayList<>();
    int i = 0;
    static long day = 0;
    Date date = new Date();
    long mis = 0;
    String table="address1";



    public Select() {
        jdbc();
    }

    public Select(String table) {
        this.table = table;
        jdbc();
    }
    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public void jdbc() {
        String sql = "SELECT * FROM "+table;
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            //System.out.println("连接数据库...");
            logger.info("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            logger.info(" 正在寻找"+table+"表的内容...");

            // 展开结果集数据库
            while (rs.next()) {

                // 通过字段检索
                //数据库表的字段名称
                // 输出数据
                System.out.println(" 即将选择 ： ");
                System.out.print("  手机号码: " + rs.getString("MoveTel"));
                System.out.print("  省份: " + rs.getString("Province"));
                System.out.print("  市区: " + rs.getString("City"));
                System.out.print("  县区: " + rs.getString("County"));
                System.out.print("  详细地址: " + rs.getString("ComeWhere"));
                System.out.print("\n");

                id.add(rs.getInt("id"));
                province.add(rs.getInt("Value1"));
                city.add(rs.getInt("Value2"));
                county.add(rs.getInt("Value3"));
                come_where.add(rs.getString("ComeWhere"));
                move_tel.add(rs.getString("MoveTel"));
            }

            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println(" 查找完成!");
       // logger.info(" 查找完成!");
    }
}
