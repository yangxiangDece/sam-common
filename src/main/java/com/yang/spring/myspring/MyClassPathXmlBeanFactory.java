package com.yang.spring.myspring;

import com.yang.spring.myspring.factory.MyAutowireBeanFactory;
import com.yang.spring.myspring.factory.MyBeanFactory;
import com.yang.spring.myspring.xml.MyXmlBeanDefinitionReader;

import java.util.Map;

/**
 * <p>Title:MyClassPathXmlBeanFactory</p>
 * <p>Description:bean工厂实现类</p>
 * <p>Company:成都瑞智创家科技有限公司</p>
 *
 * @author Yang Xiang
 * @date 2019/5/14 17:02
 */
public class MyClassPathXmlBeanFactory implements MyBeanFactory {

    private MyXmlBeanDefinitionReader beanDefinitionReader;

    private MyBeanFactory beanFactory;

    private String configs;

    public MyClassPathXmlBeanFactory(String location) {
        this.configs = location;
        refresh();
    }

    private void refresh() {
        // 创建一个XML解析器，携带一个资源加载器
        beanDefinitionReader = new MyXmlBeanDefinitionReader();
        try {
            // 解析该文件
            beanDefinitionReader.readerXML(configs);

            // 创建一个自动注入bean工厂
            beanFactory = new MyAutowireBeanFactory();

            // 循环xml中的所有bean
            for (Map.Entry<String, MyBeanDefinition> beanDefinitionEntry : beanDefinitionReader.getRegistry().entrySet()) {
                // 将XML容器中的bean注册到bean工厂
                beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }

    @Override
    public void registerBeanDefinition(String name, MyBeanDefinition beanDefinition) throws Exception {
        beanFactory.registerBeanDefinition(name, beanDefinition);
    }
}
