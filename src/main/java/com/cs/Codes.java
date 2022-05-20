package com.cs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import org.apache.commons.logging.LogFactory;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Codes {
    //String s12;
    public static String string;
    String ocrResult;
    //StringBuilder result=new StringBuilder();
    StringBuffer result = new StringBuffer();
    private static Logger logger = LogManager.getLogger(Codes.class.getName());


    public Codes()  {
    }

    public void FixGo() {
        try {
            Example.ces();
            HtmlImage image1 = (HtmlImage) Example.page.getElementById("code-box");
            ITesseract instance = new Tesseract();
            instance.setDatapath("D:/tessdata");
            instance.setLanguage("eng");

            // 读入大图
            File urlfile = new File("int/int/GetLoginVCode.jpeg");
            //InputStream inStream = urlfile.openStream();
            InputStream inStream = new FileInputStream(urlfile);
            BufferedImage bimg = ImageIO.read(inStream);

            // 分割成1*4(4)个小图
            int rows = 1;
            int cols = 4;
            int chunks = rows * cols;

            // 计算每个小图的宽度和高度
            int chunkWidth = bimg.getWidth() / cols + 1;
            int chunkHeight = bimg.getHeight() / rows;

            int count = 0;
            BufferedImage imgs[] = new BufferedImage[chunks];

            for (int x = 0; x < rows; x++) {
                for (int y = 0; y < cols; y++) {
                    // 设置小图的大小和类型
                    imgs[count] = new BufferedImage(chunkWidth, chunkHeight, bimg.getType());

                    // 写入图像内容
                    Graphics2D gr = imgs[count++].createGraphics();
                    gr.drawImage(bimg, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x,
                            chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);
                    gr.dispose();
                }
            }

            int[] rgb = new int[3];
            /**
             * 用来处理图片的缓冲流
             */
            BufferedImage bi = null;
            bi = imgs[3];
            int width = bi.getWidth();
            int height = bi.getHeight();
            int minx = bi.getMinX();
            int miny = bi.getMinY();
            for (int i = minx; i < width; i++) {
                for (int j = miny; j < height; j++) {
                    /**
                     * 得到指定像素（i,j)上的RGB值，
                     */
                    int pixel = bi.getRGB(i, j);
                    /**
                     * 分别进行位操作得到 r g b上的值
                     */
                    rgb[0] = (pixel & 0xff0000) >> 16;
                    rgb[1] = (pixel & 0xff00) >> 8;
                    rgb[2] = (pixel & 0xff);
                    /**
                     * 进行换色操作，我这里是要把蓝底/红底换成白底，那么就判断图片中rgb值是否在蓝色范围的像素
                     */

                    if ((rgb[0] == 255 && rgb[1] == 255 && rgb[2] == 255) || (rgb[0] == 0 && rgb[1] == 0 && rgb[2] == 0)) {
                        /**
                         * 这里是判断通过，则把该像素换成白色
                         */
                        bi.setRGB(i, j, 0xffffff);
                    }
                }
            }
            imgs[3] = bi;
            // 输出小图
            for (int i = 0; i < imgs.length; i++) {
                File dir1 = new File("img/");
                if (!dir1.exists()) {// 判断目录是否存在
                    dir1.mkdir();
                }

                ImageIO.write(imgs[i], "png", new File("img/temp" + i + ".png"));

                ocrResult = instance.doOCR(imgs[i]);
                ocrResult = ocrResult.replaceAll("\r|\n| ", "");
                result.append(ocrResult);
            }
            for (int i = 0; i < result.length(); i++) {
                System.out.print(result.charAt(i));
                if (result.charAt(i) >= 'a' && result.charAt(i) <= 'z' || result.charAt(i) >= 'A' && result.charAt(i) <= 'Z') {
                    System.out.print("成功 ");
                } else {
                    result.deleteCharAt(i);
                    System.out.print("失败 ");
                }
            }

            ImageIO.write(bimg, "png", new File("img/temp.png"));
            System.out.println("完成分割！");
            inStream.close();
        } catch (Exception e) {
            logger.error(Ycpc.getStackTrace(e));
        }
    }
}
