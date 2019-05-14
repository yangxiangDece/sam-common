package com.yang.spring.myspring.test;

import com.yang.spring.myspring.MyClassPathXmlBeanFactory;
import com.yang.spring.myspring.bean.UserBean;
import com.yang.spring.myspring.factory.MyBeanFactory;

public class XmlBeanDefinitionReaderTest {

    public static void main(String[] args) throws Exception {

        MyBeanFactory beanFactory = new MyClassPathXmlBeanFactory("my-spring.xml");
        UserBean userBean = (UserBean) beanFactory.getBean("userBean");
        userBean.print();
    }
}
