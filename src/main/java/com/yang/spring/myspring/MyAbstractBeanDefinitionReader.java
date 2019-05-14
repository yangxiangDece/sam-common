package com.yang.spring.myspring;

import com.yang.spring.myspring.io.MyResourceLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title:</p>
 * <p>Description:抽象的bean定义读取类</p>
 * <p>Company:成都瑞智创家科技有限公司</p>
 * @author Yang Xiang
 * @date 2019/5/14 11:59
 */
public abstract class MyAbstractBeanDefinitionReader implements MyBeanDefinitionReader {

    /**
     * 注册bean定义的容器
     */
    private Map<String, MyBeanDefinition> registry;

    /**
     * 资源加载器
     */
    private MyResourceLoader resourceLoader;

    /**
     * 构造器器必须有一个资源加载器， 默认插件创建一个map容器
     * @param resourceLoader
     */
    public MyAbstractBeanDefinitionReader(MyResourceLoader resourceLoader) {
        this.registry = new HashMap<>();
        this.resourceLoader = resourceLoader;
    }

    /**
     * 获取容器
     */
    public Map<String, MyBeanDefinition> getRegistry() {
        return registry;
    }

    /**
     * 获取资源加载器
     */
    public MyResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
