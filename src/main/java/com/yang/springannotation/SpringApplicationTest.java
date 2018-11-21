package com.yang.springannotation;

import com.yang.springannotation.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApplicationTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);

        String[] names = applicationContext.getBeanDefinitionNames();
        for (String name:names) {
            System.out.println(name);
        }

        //切换操作系统环境，在运行参数上加上：-Dos.name=Linux
        String osName = applicationContext.getEnvironment().getProperty("os.name");
        System.out.println("os name："+osName);

//        System.out.println(applicationContext.getBean("person"));
//        System.out.println(applicationContext.getBean("com.yang.springannotation.bean.Color"));
        System.out.println("获取colorFactoryBean，但是默认拿到的却是Color对象："+applicationContext.getBean("colorFactoryBean"));
        System.out.println("bean的id前面加上&，就可获取到FactoryBean工厂bean本身："+applicationContext.getBean("&colorFactoryBean"));
//        System.out.println("init,destroy方法的调用:"+applicationContext.getBean("car"));
    }
}
