package com.cs;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.jktb.Jktianbhtml;
import com.tools.GuanBo;
import com.tools.In;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Example implements Runnable {

    public static String token;
    public static String str;
    public static int id;
    public static String user;
    public static String pass;
    static ArrayList<String> user1 = new ArrayList<String>();
    static ArrayList<String> pass1 = new ArrayList<String>();
    static ArrayList<String> token1 = new ArrayList<String>();
    static ArrayList<Integer> id1=new ArrayList<>();
    static  WebClient client;
    static HtmlPage page;
    static HtmlPage page2;
    static int flag = 1;
    static boolean fl = false;
    static boolean fl1 = false;
    static JDBC jdbc = new JDBC();
    private static Logger logger = LogManager.getLogger(Example.class.getName());
    int cuowupaoc=0;
    Codes s1;
    HtmlInput input1;
    HtmlInput input2;
    HtmlInput input3;
    HtmlInput btn;



    public Example() {
        // TODO 自动生成的构造函数存根
    }

    public Example(int id,String user, String pass) {
        // TODO 自动生成的构造函数存根
        this.id=id;
        this.user = user;
        this.pass = pass;
}

    public Example(int id,String user, String pass, String token) {
        // TODO 自动生成的构造函数存根
        this.id=id;
        this.user = user;
        this.pass = pass;
        this.token=token;

    }

    public static String getStr() {
        return str;
    }

    public static void setStr(String str) {
        Example.str = str;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Example.id = id;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        Example.user = user;
    }

    public static String getPass() {
        return pass;
    }

    public static void setPass(String pass) {
        Example.pass = pass;
    }

    public static void main(String[] args) {
        ces();
        //Example example = new Example("1326200033", "301017");
        //Example example = new Example("1326200145","050637");
        //new Thread(example).start();
        /*
         * if(fl=true) { new Thread(example).start(); }
         */
        /**
         * 
         * 将数据库的相关信息存入数列中
         * 
         * **/

        for(int i=0;i<jdbc.user1.size();i++){
            ces();
            id1.add(jdbc.id.get(i));
            user1.add(jdbc.user1.get(i));
            pass1.add(jdbc.pass1.get(i));
            token1.add(jdbc.token.get(i));
        }
        logger.info("名单总数： "+user1.size());
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Scanner scanner = new Scanner(System.in);
                    String ing = scanner.next();
                    if (ing.equals("stop")) {
                        System.exit(0);
                    } else if (ing.equals("Go")) {
                        sdqd();
                    } else if(ing.equals("is")){
                        new GuanBo();
                    }else if (ing.equals("jktb")){
                       Jktianbhtml jktianbhtml=new Jktianbhtml();
                        jktianbhtml.start();
                        jktianbhtml.zidiaoyon();
                    }

                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        /**
         *
         *提示信息
         *
         * */
        /*System.out.println("等待响应");
        System.out.println("输入stop可以结束进程");*/
        logger.info("等待响应");
        logger.info("输入stop可以结束进程");
        /**
         *
         *定时执行shi=小时，fen=分钟
         *
         **/
        cronJob(7, 01);
        cronJob(12, 01);
        cronJob(18, 01);
        cronJob1(23,59);
        cronJob2(8,01);


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

    /**
     * 时间字符串分割
     */
    // 获取应该在多少秒后
    public static long getTaskTime(int shi, int fen) {
        DateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        // 当前时分秒字符串切成数组
        String[] sArr = sdf.format(new Date()).split(":");
        // 从数组取值换算成 秒计数值
        long currentMiao = (Integer.parseInt(sArr[0]) * 60 * 60) + (Integer.parseInt(sArr[1]) * 60)
                + Integer.parseInt(sArr[2]);
        // 设定的执行时间换算成 秒计数值
        long runTime = (shi * 60 * 60 + fen * 60);

        if (currentMiao <= runTime) {
            return runTime - currentMiao;
        } else {
            return currentMiao + (24 * 60 * 60) - (currentMiao - runTime);
        }
    }

    /**
     * 定时任务执行方法
     */
    // 定时任务
    public static void cronJob(int shi, int fen) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            // 把run方法里的内容换成要运行的代码
            public void run() {
				/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println("当前的系统时间为：" + sdf.format(new Date()));*/

                //Example example = new Example("1326200033", "301017");
                //Example example = new Example("1326200145","050637");
                //new Thread(example).start();

				comm();

				//System.out.println(shi + ":" + fen + "填报成功");
                logger.info(shi + ":" + fen + "填报成功");
            }
        }, getTaskTime(shi, fen) * 1000, 24 * 60 * 60 * 1000);
    }
    /**
     * 定时任务重启
     */
    public static void cronJob1(int shi, int fen) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            // 把run方法里的内容换成要运行的代码
            public void run() {
				/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println("当前的系统时间为：" + sdf.format(new Date()));*/

                //Example example = new Example("1326200033", "301017");
                //Example example = new Example("1326200145","050637");
                //new Thread(example).start();
                //System.out.println(shi + ":" + fen + "重启成功");
               logger.info (shi + ":" + fen + "重启成功");
                System.exit(0);
            }
        }, getTaskTime(shi, fen) * 1000, 24 * 60 * 60 * 1000);
    }
    /**
     * 定时任务健康填报
     */
    public static void cronJob2(int shi, int fen) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            // 把run方法里的内容换成要运行的代码
            public void run() {
                Jktianbhtml jktianbhtml=new Jktianbhtml();
                jktianbhtml.start();
                jktianbhtml.zidiaoyon();
            }
        }, getTaskTime(shi, fen) * 1000, 24 * 60 * 60 * 1000);
    }

    public static void sdqd() {
		comm();
	}

	/**
	 *
	 * 遍历运行
	 *
	 * */
	public static void comm() {
			new Thread(new Runnable() {
				@Override
				public void run() {
                    for (int i = 0; i < user1.size(); i++) {
                        System.out.println("当前运行编号" + i);
                        while (true) {
                            if (fl1 == false) {
                                new Thread(new Example(id1.get(i)-1,user1.get(i), pass1.get(i), token1.get(i))).start();
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

	public static void  ces(){
        System.getProperties().put("org.apache.commons.logging.simplelog.defaultlog","fatal");
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.http").setLevel(Level.OFF);

    }

    @Override
    public void run() {
        // TODO 自动生成的方法存根

        while (true) {
            /**
             *页面访问部分
             */
            client = new WebClient(BrowserVersion.FIREFOX_ESR);
            client.getOptions().setJavaScriptEnabled(true);
            client.getOptions().setCssEnabled(true);

            try {
                page = client.getPage("http://xg.fjsdxy.com/SPCP/Web/");
                page.save(new File("int/int"));
                s1 = new Codes();
                s1.FixGo();
            } catch (Exception e) {
                    // TODO 自动生成的 catch 块
                    deleteFile( new File("int/"));
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
            File file = new File("int/");// 输入要删除文件目录的绝对路径
            deleteFile(file);
            if (flag == 1) {
                //System.out.println("文件删除成功！");
                logger.info("文件删除成功！");
            }
            /**
             *
             *验证页面部分
             *
             * */
            if (str.contains("体温监测管理")) {
                fl = false;
            } else {
                fl = true;
            }
            if (fl == false) {
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
         * 页面登陆成功，填体温页面
         *
         * */
        if (fl == false) {
                LoginOk ok = new LoginOk();
                ok.Select();
                fl1 = false;
                In in=  new In(token,ok.str1,ok.str2);
                new Thread(in).start();

        }
        client.close();
    }
}
