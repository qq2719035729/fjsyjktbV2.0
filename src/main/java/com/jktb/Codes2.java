package com.jktb;

import com.cs.Example;
import com.cs.Ycpc;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Codes2 {
    //String s12;
    public static String string;
    String ocrResult;
    //StringBuilder result=new StringBuilder();
    StringBuffer result = new StringBuffer();
    private static Logger logger = LogManager.getLogger(Codes2.class.getName());


    public Codes2()  {
    }

    public void FixGo() {
        try {
            Jktianbhtml.ces();
            HtmlImage image1 = (HtmlImage) Jktianbhtml.page.getElementById("code-box");
            ITesseract instance = new Tesseract();
            instance.setDatapath("D:/tessdata");
            instance.setLanguage("eng");

            // 读入大图
            File urlfile = new File("int1/int/GetLoginVCode.jpeg");
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
                File dir1 = new File("img1/");
                if (!dir1.exists()) {// 判断目录是否存在
                    dir1.mkdir();
                }

                ImageIO.write(imgs[i], "png", new File("img1/temp" + i + ".png"));

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

            ImageIO.write(bimg, "png", new File("img1/temp.png"));
            System.out.println("完成分割！");
            inStream.close();
        } catch (Exception e) {
            logger.error(Ycpc.getStackTrace(e));
        }
    }
}
