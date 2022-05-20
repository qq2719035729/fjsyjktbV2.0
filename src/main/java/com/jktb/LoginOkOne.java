package com.jktb;


import com.cs.LoginOk;
import com.cs.Ycpc;
import com.gargoylesoftware.htmlunit.html.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class LoginOkOne {
    int id;
    static String string1 = "";
    static String string2 = "";
    static String str1 = "";
    static String str2 = "";
    static HtmlPage page;
    boolean log = false;
    private static Logger logger = LogManager.getLogger(LoginOk.class.getName());
    public Jktianbhtml jktianbhtml = new Jktianbhtml();
    public Select select =new Select();
    public Select select1 =new Select("address2");

    public LoginOkOne() {
    }
    public LoginOkOne(int id) {
    this.id=id;
    }

    public void Select() {
        try {
            DomElement htmlPage1 = (DomElement) jktianbhtml.page2.getElementById("platfrom2").getFirstElementChild();
            page = htmlPage1.click();
            string1 = page.asNormalizedText();
            if (string1.contains("福建水利电力职业技术学院信息提示页")) {
                log=false;
            }else {
                log=true;
            }
            jrOk();
        } catch (Exception e) {
            logger.error(Ycpc.getStackTrace(e));
            logger.error("二级网页出现异常");
            Select();
        }
    }

    public void jrOk() {
        try {
            if (log == true) {
                /**手机号码部分*/
                HtmlInput moveTel=(HtmlInput)page.getElementById("MoveTel");//手机号码
                /**当前所在地址部分*/
                HtmlSelect province= page.getElementByName("Province");//省份
                HtmlSelect city= page.getElementByName("City");//城市
                HtmlSelect county= page.getElementByName("County");//县区
                HtmlInput comewhere= page.getElementByName("ComeWhere");//详细街道
                /**家庭住址部分*/
                HtmlSelect fa_province= page.getElementByName("FaProvince");//省份
                HtmlSelect fa_city= page.getElementByName("FaCity");//城市
                HtmlSelect fa_county= page.getElementByName("FaCounty");//县区
                HtmlInput fa_come_where= page.getElementByName("FaComeWhere");//详细街道
                /**单选框部分*/
                //是否有发热咳嗽症状：
                HtmlRadioButtonInput radio1=(HtmlRadioButtonInput) page.getElementById("71a16876-3d52-4510-8c96-09b232a0161b");
                //是否疑似感染:
                HtmlRadioButtonInput radio2=(HtmlRadioButtonInput) page.getElementById("994c60eb-6f68-48bd-8bda-49a8a7ea812c");
                //是否确诊新型肺炎：
                HtmlRadioButtonInput radio3=(HtmlRadioButtonInput) page.getElementById("083d90f5-5fa2-4a6d-a231-fe315b5104a3");
                //今天体温是否小于37.3℃：
                HtmlRadioButtonInput radio4=(HtmlRadioButtonInput) page.getElementById("386c9585-5833-4c90-bf32-97d9419bf39c");
                //最近14天本人及共同居住人员是否与确诊、疑似病人或回国人员有过密切接触：
                HtmlRadioButtonInput radio5=(HtmlRadioButtonInput) page.getElementById("a48da759-c31e-4939-bb67-de7652913f15");
                //最近14天本人及共同居住人员健康码是否异常或是否有前往中高风险地区：
                HtmlRadioButtonInput radio6=(HtmlRadioButtonInput) page.getElementById("66bad42c-df31-4e44-8d0e-4b0c2a98f6e2");
                //本人目前所处地区是否为中高风险地区：
                HtmlRadioButtonInput radio7=(HtmlRadioButtonInput) page.getElementById("521f310c-d7c3-47ce-a79c-856930784ddd");
                //本人健康码是否为绿色：
                HtmlRadioButtonInput radio8=(HtmlRadioButtonInput) page.getElementById("fe9a5cc1-6d58-4f75-b069-7c71b19d51ec");
                /**确定已阅读*/
                HtmlInput checkboxOk=(HtmlInput) page.getElementById("ckCLS");
                /**提交按钮*/
                List<HtmlElement> btn= page.getElementById("SaveBtnDiv").getByXPath("//button");
                /**提交与选中部分*/
                moveTel.setDefaultValue(select.move_tel.get(this.id));
                /*现在所在地址*/
                //判断是否被选中，如果被选中则取消选中
                if(province.getOptionByValue(select.province.get(this.id)+"").isSelected()){
                    province.getOptionByValue(select.province.get(this.id)+"").setSelected(false);
                }
                province.getOptionByValue(select.province.get(this.id)+"").setSelected(true);
                //判断是否被选中，如果被选中则取消选中
                if(city.getOptionByValue(select.city.get(this.id)+"").isSelected()){
                    city.getOptionByValue(select.city.get(this.id)+"").setSelected(false);
                }
                city.getOptionByValue(select.city.get(this.id)+"").setSelected(true);
                //判断是否被选中，如果被选中则取消选中
                if(county.getOptionByValue(select.county.get(this.id)+"").isSelected()){
                    county.getOptionByValue(select.county.get(this.id)+"").setSelected(false);
                }
                county.getOptionByValue(select.county.get(this.id)+"").setSelected(true);
                comewhere.setDefaultValue(select.come_where.get(this.id));
                /*家庭住址*/
                fa_province.getOptionByValue(select1.province.get(this.id)+"").setSelected(true);
                fa_city.getOptionByValue(select1.city.get(this.id)+"").setSelected(true);
                fa_county.getOptionByValue(select1.county.get(this.id)+"").setSelected(true);
                fa_come_where.setDefaultValue(select1.come_where.get(this.id));
                /*单选框选中*/
                radio1.setChecked(true);
                radio2.setChecked(true);
                radio3.setChecked(true);
                radio4.setChecked(true);
                radio5.setChecked(true);
                radio6.setChecked(true);
                radio7.setChecked(true);
                radio8.setChecked(true);
                /*提交*/
                checkboxOk.click();
                System.out.println(page.asNormalizedText());
                System.out.println(btn.get(0).asXml());
                HtmlPage page1= btn.get(0).click();
                System.out.println("执行完毕");
                string2=page1.asNormalizedText();
                System.out.println(string2);
                logger.info("================");
                logger.info(string2);
                str1=string2.substring(0,17);
                str2=string2.substring(18,string2.length()-3);
            } else {
                logger.info("提示");
                string2 = string1;
                str1 = string2.substring(0, 17);
                str2 = string2.substring(18, string2.length() - 3);
                System.out.println(string2);
            }
        } catch (Exception e) {
            logger.error("三级网页出现异常");
            logger.error(Ycpc.getStackTrace(e));
            e.printStackTrace();
            //System.out.println(Ycpc.getStackTrace(e));
        }
    }
}

