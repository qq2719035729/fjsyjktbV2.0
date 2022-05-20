package com.cs;

import java.io.IOException;
import java.util.Random;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginOk {

	Example example=new Example();
	static String string1="";
	static String string2="";
	static  String str1="";
	static  String str2="";
	static HtmlPage page;
	boolean Jr=false;
	private static Logger logger = LogManager.getLogger(LoginOk.class.getName());
	public LoginOk() {
		// TODO 自动生成的构造函数存根
		Example.ces();
	}

	public void Select()  {
		try{
			DomElement htmlPage1=(DomElement) example.page2.getElementById("platfrom1").getFirstElementChild();
			page= htmlPage1.click();
			string1=page.asNormalizedText();
			if (string1.contains("福建水利电力职业技术学院信息提示页")) {
				Jr=false;
			}else {
				Jr=true;
			}
			/*System.out.println("分割线=============");
			System.out.println(string1);
			System.out.println();*/
			logger.info("分割线=============");
			logger.info(string1);
			jrOk();
		}catch (Exception e) {
			logger.error(Ycpc.getStackTrace(e));
			//System.out.println("二级网页出现异常");
			logger.error("二级网页出现异常");
			Select();
		}
	}
	public void jrOk()  {
		try {
			Random r = new Random(System.currentTimeMillis());
			int sjs=r.nextInt(9)+1;
			//System.out.println("即将填报温度36."+sjs);
			logger.info("即将填报温度36."+sjs);
			if (Jr==true) {

				HtmlSelect select3= (HtmlSelect) page.getElementById("Temper1");
				HtmlSelect select4=(HtmlSelect) page.getElementById("Temper2");
				DomElement element=page.getElementById("SaveBtnDiv").getFirstElementChild().getLastElementChild();
				select3.getOptionByValue("36").setSelected(true);
				select4.getOptionByValue(sjs+"").setSelected(true);
				HtmlPage page1=element.click();
				string2=page1.asNormalizedText();
				/*System.out.println();
				System.out.println("================");
				System.out.println();
				System.out.println(string2);
				System.out.println();*/
				logger.info("================");
				logger.info(string2);
				str1=string2.substring(0,17);
				str2=string2.substring(18,string2.length()-3);
			}else {
				//System.out.println("提示");
				logger.info("提示");
				string2=string1;
				str1=string2.substring(0,17);
				str2=string2.substring(18,string2.length()-3);
			}
		}catch (Exception e){
			logger.error(Ycpc.getStackTrace(e));
			//System.out.println("三级网页出现异常");
			logger.error("三级网页出现异常");
			jrOk();
		}
		
	}
}
