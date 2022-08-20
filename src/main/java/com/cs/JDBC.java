package com.cs;

import com.tools.In;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import  java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class JDBC {
    private static Logger logger = LogManager.getLogger(JDBC.class.getName());
    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/fjsdxy?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    //MySQL账号密码
    static final String USER = "账号";
    static final String PASS = "密码";
    int i=0;
    static long day=0;
    public ArrayList<Integer> id = new ArrayList<Integer>();
    public ArrayList<String> user1 = new ArrayList<String>();
    public ArrayList<String> pass1 = new ArrayList<String>();
    public ArrayList<String> token = new ArrayList<String>();
    public ArrayList<Timestamp> arr1=new ArrayList<Timestamp>();
    public ArrayList<Timestamp> arr2=new ArrayList<Timestamp>();
    public ArrayList<Integer> sfxf = new ArrayList<Integer>();

    Date date=new Date();
    long mis=0;

    public JDBC(){
        jdbc();
    }
    public void  jdbc(){
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            //System.out.println("连接数据库...");
            logger.info("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            //System.out.println(" 实例化Statement对象...");
            logger.info("  实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM user";
            ResultSet rs = stmt.executeQuery(sql);
            //System.out.println(" 正在寻找内容...");
            logger.info(" 正在寻找内容...");

            // 展开结果集数据库
            while(rs.next()){

                // 通过字段检索
                //数据库表的字段名称
                // 输出数据
                /*System.out.print("  ID: " + rs.getInt("id"));
                System.out.print("  账号: " + rs.getString("user"));
                System.out.print("  密码: " + rs.getString("pass"));
                System.out.print("  创建时间: " + rs.getTimestamp("date"));
                System.out.print("  到期时间: " + rs.getTimestamp("end"));*/
                logger.info("  ID: " + rs.getInt("id"));
                logger.info("  账号: " + rs.getString("user"));
                logger.info("  密码: " + rs.getString("pass"));
                logger.info("  创建时间: " + rs.getTimestamp("date"));
                logger.info("  到期时间: " + rs.getTimestamp("end"));
                switch (rs.getInt("sfxf")){
                    case 0:
                        //System.out.print(" 当前状态: 到期");
                        logger.info(" 当前状态: 到期");
                        break;
                    case 1:
                       // System.out.print(" 当前状态: 未到期");
                        logger.info(" 当前状态: 未到期");
                        break;

                }
                System.out.print("\n");
                if(rs.getInt("sfxf")==1){
                    id.add(rs.getInt("id"));
                    user1.add(rs.getString("user"));
                    pass1.add(rs.getString("pass"));
                    token.add(rs.getString("token"));
                    arr1.add(rs.getTimestamp("date"));
                    arr2.add(rs.getTimestamp("end"));

                  /*System.out.print("  ID: " + id.get(i));
                    System.out.print("  账号: " + user1.get(i));
                    System.out.print("  密码: " + pass1.get(i));
                    System.out.print("  创建时间: " + arr1.get(i));
                    System.out.print("  到期时间: " + arr2.get(i));*/
                    mis=arr2.get(i).getTime()-date.getTime();
                    day=mis/1000/60/60/24;
                    dqtx(day);
                    if(mis<=0){
                        new JDBC1(id.get(i));
                        new Thread(new In(token.get(i),"过期提示","您的体温填报已过期，请及时联系管理员2719035729，联系时请说明来意")).start();
                    }
                    i++;
                }else{
                    new Thread(new In(rs.getString("token"),"过期提示","您的体温填报已经过期，若未到期，请联系管理员2719035729")).start();
                    continue;
                }
            }
            i=0;

            // 完成后关闭
            rs.close();
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
        //System.out.println(" 查找完成!");
        logger.info(" 查找完成!");
    }
    public void dqtx(long day){
        if(day<=7&&day>5){
           new Thread( new In(token.get(i),"过期提示","您的体温填报还剩下"+this.day+"天，请及时联系管理员2719035729续费,请备注")).start();
        }else if(day<=5&&day>3){
            new Thread( new In(token.get(i),"过期提示","您的体温填报还剩下"+this.day+"天，请及时联系管理员2719035729续费,请备注")).start();
        }else if(day<=3&&day>=1){
            new Thread( new In(token.get(i),"过期提示","您的体温填报还剩下"+this.day+"天，请及时联系管理员2719035729续费,请备注")).start();
        }
    }
}
