package com.yang.spring;

import org.springframework.beans.factory.*;

public class Student implements BeanFactoryAware,InitializingBean,BeanNameAware,DisposableBean {

    private String name;
    private String address;
    private Integer phone;

    private BeanFactory beanFactory;
    private String beanName;

    public Student() {
        System.out.println("【构造器】调用Student的构造器实例化...");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("【属性注入】name");
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        System.out.println("【属性注入】address");
        this.address = address;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        System.out.println("【属性注入】phone");
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                ", beanFactory=" + beanFactory +
                ", beanName='" + beanName + '\'' +
                '}';
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
        System.out.println("[BeanFactoryAware] 调用 setBeanFactory()... ："+toString());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("[InitializingBean] 调用 afterPropertiesSet()... ："+toString());
    }

    public void beanInit(){
        System.out.println("调用了 bean 的 init-method ....");
    }

    public void beanDestroy(){
        System.out.println("调用了 bean 的 destroy-method ...");
    }

    @Override
    public void setBeanName(String name) {
        this.beanName=name;
        System.out.println("[BeanNameAware] 调用了 setBeanName() ... ："+toString());
    }

    @Override
    public void destroy() {
        System.out.println("[DiposibleBean] 调用了 destroy()... ");
    }
}
