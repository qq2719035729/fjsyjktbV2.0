package com.jktb;
import com.cs.Example;
import com.tools.In;
import com.cs.Ycpc;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;

public class Jktianbhtml implements Runnable{
    public static String token;
    public static String str;
    public static int id;
    public static String user;
    public static String pass;
    public static JDBC2 jdbc=new JDBC2();
    static  WebClient client;
    static HtmlPage page;
    static HtmlPage page2;
    static int flag = 1;
    static boolean fl = false;
    static boolean fl1 = false;
    static ArrayList<Integer> id1 = new ArrayList<>();
    static ArrayList<String> user1 = new ArrayList<String>();
    static ArrayList<String> pass1 = new ArrayList<String>();
    static ArrayList<String> token1 = new ArrayList<String>();
    private static Logger logger = LogManager.getLogger(Jktianbhtml.class.getName());
    Codes2 s1;
    HtmlInput input1;
    HtmlInput input2;
    HtmlInput input3;
    HtmlInput btn;
    int cuowupaoc=0;



    public Jktianbhtml() {
        // TODO 自动生成的构造函数存根

    }

    public Jktianbhtml(String user, String pass) {
        // TODO 自动生成的构造函数存根
        this.id = id;
        this.user=user;
        this.pass = pass;
    }
    public Jktianbhtml(int id,String user, String pass) {
        // TODO 自动生成的构造函数存根
        this.id = id;
        this.user=user;
        this.pass = pass;
    }
    public Jktianbhtml(int id,String user,  String pass, String token) {
        // TODO 自动生成的构造函数存根
        this.id = id;
        this.user=user;
        this.pass = pass;
        this.token=token;

    }

    public void zidiaoyon() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < user1.size(); i++) {
                    System.out.println("当前运行编号" + i);
                    while (true) {
                        if (fl1 == false) {
                            new Thread(new Jktianbhtml(id1.get(i),user1.get(i), pass1.get(i), token1.get(i))).start();
                            fl1 = true;
                            break;
                        }
                        try {
                            Thread.sleep(15000);
                        } catch (InterruptedException e) {
                            logger.error(Ycpc.getStackTrace(e));
                        }
                    }
                }
            }
        }).start();
    }
    public void start(){
        for(int i=0;i<jdbc.user1.size();i++){
            id1.add(jdbc.id.get(i));
            user1.add(jdbc.user1.get(i));
            pass1.add(jdbc.pass1.get(i));
            token1.add(jdbc.token.get(i));
        }
        logger.info("健康填报部分==========================");
        logger.info("健康填报名单总数： "+user1.size());
        System.out.println(123);
        System.out.println("数据库信息导入");
    }
    @Override
    public void run() {
        ces();
        while (true){
            /**
             *页面访问部分
             */
            client = new WebClient(BrowserVersion.FIREFOX_ESR);
            client.getOptions().setJavaScriptEnabled(true);
            client.getOptions().setCssEnabled(true);

            try {
                page = client.getPage("http://xg.fjsdxy.com/SPCP/Web/");
                page.save(new File("int1/int"));
                s1 = new Codes2();
                s1.FixGo();
            } catch (Exception e) {
                // TODO 自动生成的 catch 块
                deleteFile( new File("int1"));
                if (flag == 1) {
                    // System.out.println("文件删除成功！");
                    logger.info("文件删除成功！");
                }
                logger.error(Ycpc.getStackTrace(e));
                continue;
            }
            /**
             *页面元素部分
             */
            input1 = (HtmlInput) page.getHtmlElementById("StudentId");
            input2 = (HtmlInput) page.getHtmlElementById("Name");
            input3 = (HtmlInput) page.getHtmlElementById("codeInput");
            btn = (HtmlInput) page.getHtmlElementById("Submit");
            /**
             *页面设置内容部分
             */
            input1.setDefaultValue(user);
            input2.setDefaultValue(pass);
            input3.setDefaultValue(s1.result.toString());

            /**
             * 页面点击部分
             */
            try {
                page2 = btn.click();
                str = page2.asNormalizedText();
            } catch (Exception e1) {
                // TODO 自动生成的 catch 块
                logger.error(Ycpc.getStackTrace(e1));
                continue;
            }

            /**
             *页面输入的内容输出部分
             */
            System.out.println(input1.getDefaultValue());
            logger.info(input1.getDefaultValue());
            logger.info(input2.getDefaultValue());
            System.out.println(input2.getDefaultValue());
            System.out.println(input3.getDefaultValue());
            System.out.println(str);
            /**
             *
             * 文件夹删除部分
             *
             * */
            File file = new File("int1/");// 输入要删除文件目录的绝对路径
            deleteFile(file);
            if (flag == 1) {
                //System.out.println("文件删除成功！");
                logger.info("文件删除成功！");
            }
            /**
             *
             *判断是否在选择页面 flase为是 true为否
             *
             * */
            if (str.contains("学生健康情况填报")) {
                fl = false;
            } else {
                fl = true;
            }
            if (!fl) {
                cuowupaoc=0;
                break;
            } else {

                /*System.out.println("循环");
                System.out.println();*/
                logger.info("循环！");
                if(cuowupaoc>100){
                    cuowupaoc=0;
                    fl=false;
                    logger.info("循环达到"+cuowupaoc+"次，跳过！");
                    if(str.contains("账号有误，请仔细核对！")){
                        logger.info(str);
                        new Thread(new In(this.token,"警告","账号有误，请仔细核对！")).start();
                    }
                }else {
                    cuowupaoc++;
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    // TODO 自动生成的 catch 块
                    logger.error(Ycpc.getStackTrace(e));
                }
            }
        }
        /**
         *
         * 页面登陆成功，进入健康填报
         *
         * */
        if (!fl) {
            LoginOkOne ok = new LoginOkOne(id-1);
            ok.Select();
            fl1 = false;
            In in=  new In(token,"健康填报信息",ok.str2);
            new Thread(in).start();
        }
        client.close();
    }
    /**
     * 删除目录方法
     */
    public static void deleteFile(File file) {
        // 判断文件不为null或文件目录存在
        if (file == null || !file.exists()) {
            flag = 0;
            // System.out.println("文件删除失败,请检查文件路径是否正确");
            logger.error("文件删除失败,请检查文件路径是否正确");
            return;
        }
        // 取得这个目录下的所有子文件对象
        File[] files = file.listFiles();
        // 遍历该目录下的文件对象
        for (File f : files) {
            // 打印文件名
            String name = file.getName();
            //System.out.println(name);
            // 判断子目录是否存在子目录,如果是文件则删除
            if (f.isDirectory()) {
                deleteFile(f);
            } else {
                f.delete();
            }
        }
        // 删除空文件夹 for循环已经把上一层节点的目录清空。
        file.delete();
    }

    public static void  ces(){
        System.getProperties().put("org.apache.commons.logging.simplelog.defaultlog","fatal");
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.http").setLevel(Level.OFF);

    }
}
