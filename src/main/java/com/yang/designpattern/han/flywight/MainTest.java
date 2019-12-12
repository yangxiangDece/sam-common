package com.yang.designpattern.han.flywight;

/**
 * 享元模式
 */
public class MainTest {

    public static void main(String[] args) {
        WebSiteFactory webSiteFactory = new WebSiteFactory();

        WebSite news = webSiteFactory.getWebSite("新闻");
        news.use();

        WebSite boke = webSiteFactory.getWebSite("博客");
        boke.use();

        WebSite boke1 = webSiteFactory.getWebSite("博客");
        boke1.use();

        System.out.println(webSiteFactory.getWebSiteCount());
    }
}
