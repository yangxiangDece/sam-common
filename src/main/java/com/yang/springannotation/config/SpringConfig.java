package com.yang.springannotation.config;

import com.yang.springannotation.bean.Car;
import com.yang.springannotation.bean.Color;
import com.yang.springannotation.bean.ColorFactoryBean;
import com.yang.springannotation.bean.Person;
import com.yang.springannotation.condition.LinuxCondition;
import com.yang.springannotation.condition.MyImportBeanDefinitionRegistrar;
import com.yang.springannotation.condition.MyImportSelector;
import com.yang.springannotation.condition.WindowsCondition;
import com.yang.springannotation.filter.MyTypeFilter;
import com.yang.springannotation.service.BookServiceImpl;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * 当使用includeFilters时，useDefaultFilters必须为false，因为useDefaultFilters为true表示他使用默认过滤规则
 * FilterType.ANNOTATION：注解类型
 * FilterType.CUSTOM：指定类型
 * FilterType.ASSIGNABLE_TYPE：自定义过规则
 * @Conditional 可以放在类、方法上
 *      类：只有当满足条件，这个配置的类里的所有bean注册才能生效
 *
 *
 */
//@Conditional(value = {LinuxCondition.class}) //在windows环境下，这个配置类里面的一个bean都没有被注册到IOC容器中
@Configuration
/*@ComponentScan(value = "com.yang.springannotation",excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class, Service.class})})*/
@ComponentScan(value = "com.yang.springannotation",includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class, Service.class}),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = BookServiceImpl.class),
        @ComponentScan.Filter(type = FilterType.CUSTOM,classes = MyTypeFilter.class)},useDefaultFilters = false)
//直接导入组件，可以使用applicationContext.getBean("com.yang.springannotation.bean.Color")获取bean，bean的id默认是bean的全类名
@Import(value = {Color.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class SpringConfig {

/*    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Scope(value = WebApplicationContext.SCOPE_REQUEST)
    @Scope(value = WebApplicationContext.SCOPE_APPLICATION)
    @Scope(value = WebApplicationContext.SCOPE_SESSION)*/
    @Bean(value = "person")
    //容器启动的时候不会加载bean，只有当使用bean的时候才回去加载
    @Lazy
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Person person(){
        return new Person();
    }

    @Bean("bill")
    @Conditional(value = {WindowsCondition.class})
    public Person person01(){
        return new Person("bill Gates",62);
    }

    @Bean("linus")
    @Conditional(value = {LinuxCondition.class})
    public Person person02(){
        return new Person("linus",48);
    }

    @Bean
    public ColorFactoryBean colorFactoryBean(){
        return new ColorFactoryBean();
    }

    @Bean(initMethod = "initMethod",destroyMethod = "destroyMethod")
    public Car car(){
        return new Car();
    }
}
