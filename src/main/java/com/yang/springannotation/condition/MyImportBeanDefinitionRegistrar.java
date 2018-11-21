package com.yang.springannotation.condition;

import com.yang.springannotation.bean.RainBow;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 自定义逻辑返回需要导入的组件
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     * 把所有需要添加到容器中的bean
     * @param importingClassMetadata    当前类的注解信息
     * @param registry                  BeanDefinition注册类
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean isColor = registry.containsBeanDefinition("com.yang.springannotation.bean.Color");
        //如果容器中有Color这个bean，那么就注册rainRow 这个bean到容器中
        if(isColor){
            BeanDefinition beanDefinition = new RootBeanDefinition(RainBow.class);
            registry.registerBeanDefinition("rainRow", beanDefinition);
        }
    }
}
