package com.tools;

import com.cs.JDBC;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class GuanBo implements Runnable{
    JDBC jdbc=new JDBC();
    String str;
    String string1;
    private static Logger logger = LogManager.getLogger(GuanBo.class.getName());
    public GuanBo() {
    new Thread(this).start();
    }
    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
    @Override
    public void run() {
        Scanner scanner=new Scanner(System.in);
        //System.out.println("请输入要发布的内容");
        logger.info("请输入要发布的内容");
        String string=scanner.next();
        setStr(string);
        for (int i=0;i<jdbc.token.size();i++){
            string1=jdbc.token.get(i);
            string1.replaceAll("\r\n|\r|\n", "");
            new Thread(new In(string1,"通知",getStr())).start();
        }
        //System.out.println("信息发布成功");
        logger.info("信息发布成功");

    }
}
