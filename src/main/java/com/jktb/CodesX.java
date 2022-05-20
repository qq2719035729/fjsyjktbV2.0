package com.jktb;

import com.cs.Ycpc;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class CodesX {
    //String s12;
    public static String string;
    String ocrResult;
    //StringBuilder result=new StringBuilder();
    StringBuffer result = new StringBuffer();
    private static Logger logger = LogManager.getLogger(CodesX.class.getName());


    public CodesX() {
    }

    public void FixGo() {
        try {
            Jktianbhtml.ces();
            ITesseract instance = new Tesseract();
            instance.setDatapath("D:/tessdata");
            instance.setLanguage("eng");

            // 读入大图
            File urlfile = new File("int1/int/GetLoginVCode.jpeg");
            InputStream inStream = new FileInputStream(urlfile);
            BufferedImage bimg = ImageIO.read(inStream);
            ocrResult = instance.doOCR(urlfile);
            ocrResult = ocrResult.replaceAll("\r|\n| ", "");
            result.append(ocrResult);


            /**
             * 用来处理图片的缓冲流
             */
            File dir1 = new File("img1/");
            if (!dir1.exists()) {// 判断目录是否存在
                dir1.mkdir();
            }
            ImageIO.write(bimg, "png", new File("img1/temp.png"));
            System.out.println("完成读取");
            inStream.close();
            System.out.println(result.toString());

        } catch (Exception e) {
            logger.error(Ycpc.getStackTrace(e));
        }
    }
}
