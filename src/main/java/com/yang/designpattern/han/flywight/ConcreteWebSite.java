package com.yang.designpattern.han.flywight;

public class ConcreteWebSite extends WebSite {

    private String type;

    public ConcreteWebSite(String type) {
        this.type = type;
    }

    @Override
    protected void use() {
        System.out.println("网站的发布形式：" + type);
    }

    @Override
    protected void use(TestUser user) {
        System.out.println("网站的发布形式：" + type + ",使用者" + user.getName());
    }
}
