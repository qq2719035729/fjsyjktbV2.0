package com.tools;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse;

public class In implements  Runnable{
    private static Logger logger = LogManager.getLogger(In.class.getName());
    /* public static void main(String[] args) {
         String redisKey= "pushplus:canSend";
         String token= "推送加"; //您的token
         String title= "标题";  //消息的标题
         String content= "内容";  //消息的内容,包含文字、换行和图片
         String url = "https://www.pushplus.plus/send?title="+ title +"&content="+ content +"&token=" + token;

         //服务器发送Get请求，接收响应内容
         String response = HttpUtil.get(url);
         System.out.println(response);
     }*/
    String token;
    String title;
    String content;
    String url;

    public In() {
    }

    public In(String token, String title, String content, String url) {
        this.token = token;
        this.title = title;
        this.content = content;
        this.url = url;
    }

    public In(String token, String title, String content) {
        this.token = token;
        this.title = title;
        this.content = content;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void goTo() {
        url = "https://www.pushplus.plus/send?title=" + getTitle() + "&content=" + getContent() + "&token=" + getToken();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(response.body());
    }

    @Override
    public void run() {
        goTo();
    }
}
