package com.yang.spring.myspring.bean;

public class RoleBean {

    private String name;

    private ResourceBean resourceBean;

    public void print() {
        resourceBean.print();
    }

    public ResourceBean getResourceBean() {
        return resourceBean;
    }

    public void setResourceBean(ResourceBean resourceBean) {
        this.resourceBean = resourceBean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
