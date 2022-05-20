package com.cs;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class JDBC1 {
    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/fjsdxy?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    //MySQL账号密码
    static final String USER = "root";
    static final String PASS = "hx020730";
    int i;

    public JDBC1() {
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public JDBC1(int i){
        this.i=i;
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps=null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            //连接数据库
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // 执行查询
            //实例化Statement对象
            stmt = conn.createStatement();
            String sql;
            sql="UPDATE user SET sfxf=? WHERE id=?";//向login表里修改数据
            //注：几个问号几个ps.setString，上面的语句中有两个?,所以下面有两个ps.setString
            ps=conn.prepareStatement(sql);//修改数据预处理
            ps.setString(1,0+"");//第1个问号的值"222222"
            ps.setString(2, getI()+"");//第2个问号的值"123"
            ps.executeUpdate();//执行修改数据
            // 完成后关闭
            ps.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }

    }
}
