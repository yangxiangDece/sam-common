package com.yang.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *
 *      BeanFactoryPostProcessor 构造器 invoke...
        [BeanFactoryPostProcessor] postProcessBeanFactory invoke...

        BeanPostProcessor 构造器 invoke...
        InstantiationAwareBeanPostProcessorAdapter 构造器 invoke ...
        【构造器】调用Student的构造器实例化...

        [InstantiationAwareBeanPostProcessorAdapter] 调用了 postProcessProperties()...
        【属性注入】name
        【属性注入】address
        【属性注入】phone
        [BeanNameAware] 调用了 setBeanName() ... ：Student{name='张三', address='成都软件园', phone=12456, beanFactory=null, beanName='student'}
        [BeanFactoryAware] 调用 setBeanFactory()... ：Student{name='张三', address='成都软件园', phone=12456, beanFactory=org.springframework.beans.factory.support.DefaultListableBeanFactory@685cb137: defining beans [student,com.yang.spring.processor.MyBeanPostProcessor#0,com.yang.spring.processor.MyBeanFactoryPostProcessor#0,com.yang.spring.processor.MyInstantiationAwareBeanPostProcessor#0,com.yang.spring.processor.MyApplicationContext#0]; root of factory hierarchy, beanName='student'}
        [ApplicationContextAware] student setApplicationContext
        [BeanPostProcessor] postProcessBeforeInitialization bean:student
        [InstantiationAwareBeanPostProcessorAdapter] 调用了 postProcessBeforeInitialization()...
        [InitializingBean] 调用 afterPropertiesSet()... ：Student{name='张三', address='成都软件园', phone=12456, beanFactory=org.springframework.beans.factory.support.DefaultListableBeanFactory@685cb137: defining beans [student,com.yang.spring.processor.MyBeanPostProcessor#0,com.yang.spring.processor.MyBeanFactoryPostProcessor#0,com.yang.spring.processor.MyInstantiationAwareBeanPostProcessor#0,com.yang.spring.processor.MyApplicationContext#0]; root of factory hierarchy, beanName='student'}
        调用了 bean 的 init-method ....
        [BeanPostProcessor] postProcessAfterInitialization bean:student
        [InstantiationAwareBeanPostProcessorAdapter] 调用了 postProcessAfterInitialization()...

        [ApplicationContextAware] 构造器 invoke...
        [InstantiationAwareBeanPostProcessorAdapter] 调用了 postProcessProperties()...
        [ApplicationContextAware] setApplicationContext invoke ...
        [BeanPostProcessor] postProcessBeforeInitialization bean:com.yang.spring.processor.MyApplicationContext#0
        [InstantiationAwareBeanPostProcessorAdapter] 调用了 postProcessBeforeInitialization()...
        [BeanPostProcessor] postProcessAfterInitialization bean:com.yang.spring.processor.MyApplicationContext#0
        [InstantiationAwareBeanPostProcessorAdapter] 调用了 postProcessAfterInitialization()...
        spring容器初始化完成
        关闭spring容器
        [DisposableBean] 调用了 destroy()...
        调用了 bean 的 destroy-method ...
 *
 *
 *
 *
 *
 * 这Spring框架中，一旦把一个bean纳入到Spring IoC容器之中，这个bean的生命周期就会交由容器进行管理，一般担当管理者角色的是BeanFactory或ApplicationContext。
 * 认识一下Bean的生命周期活动，对更好的利用它有很大的帮助下面以BeanFactory为例，说明一个Bean的生命周期活动：
 *
 * 1、Bean的建立：由BeanFactory读取Bean定义文件，并生成各个实例。
 * 2、Setter注入：执行Bean的属性依赖注入。
 * 3、BeanNameAware的setBeanName()：如果Bean类实现了org.springframework.beans.factory.BeanNameAware接口，则执行其setBeanName()方法。
 * 4、BeanFactoryAware的setBeanFactory() 或者 ApplicationContextAware的setApplicationContext() ：
 *      如果Bean类实现了org.springframework.beans.factory.BeanFactoryAware接口，则执行其setBeanFactory()方法。
 *      如果Bean类实现了org.springframework.context.ApplicationContextAware接口，则执行其setApplicationContext()方法。
 * 5、BeanPostProcessors的processBeforeInitialization()：容器中如果有实现org.springframework.beans.factory.BeanPostProcessors接口的实例，则任何Bean在初始化之前都会执行这个实例的processBeforeInitialization()方法。
 * 6、InitializingBean的afterPropertiesSet()：如果Bean类实现了org.springframework.beans.factory.InitializingBean接口，则执行其afterPropertiesSet()方法。
 * 7、Bean定义文件中定义的init-method方法
 * 8、BeanPostProcessors的processAfterInitialization()：容器中如果有实现org.springframework.beans.factory.BeanPostProcessors接口的实例，则任何Bean在初始化之前都会执行这个实例的processAfterInitialization()方法。
 * 9、DisposableBean的destroy()：在容器关闭时，如果Bean类实现了org.springframework.beans.factory.DisposableBean接口，则执行它的destroy()方法。
 * 10、Bean定义文件中定义destroy-method
 *
 * 如果使用ApplicationContext来维护一个Bean的生命周期，则基本上与上边的流程相同，只不过在执行BeanNameAware的setBeanName()后，
 * 若有Bean类实现了org.springframework.context.ApplicationContextAware接口，则执行其setApplicationContext()方法，
 * 然后再进行BeanPostProcessors的processBeforeInitialization()
 *
 */
public class Student implements BeanFactoryAware,InitializingBean,BeanNameAware,DisposableBean,ApplicationContextAware {

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
        System.out.println("[DisposableBean] 调用了 destroy()... ");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("[ApplicationContextAware] student setApplicationContext");
    }
}
