package com.yang.spring.myspring.bean;

public class UserBean {

    private String name;

    private RoleBean roleBean;

    public void print() {
        roleBean.print();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleBean getRoleBean() {
        return roleBean;
    }

    public void setRoleBean(RoleBean roleBean) {
        this.roleBean = roleBean;
    }
}
