package com.yang.spring;

import com.yang.spring.service.UserService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


/**
 *
 *   BeanFactoryPostProcessor 构造器 invoke...
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

/**
 * BeanFactory：采用延迟加载，第一次getBean时才会初始化Bean
 * ApplicationContext：容器启动初始化上下文就会实例化所有单例的bean，是对BeanFactory扩展，提供了更多的功能
 *      1、MessageSource, 提供国际化的消息访问
        2、资源访问，如URL和文件
        3、事件传播特性，即支持aop特性
        4、载入多个（有继承关系）上下文 ，使得每一个上下文都专注于一个特定的层次，比如应用的web层
 * ClassPathXmlApplicationContext：从类路径下加载配置文件
 * FileSystemXmlApplicationContext：从文件系统中加载配置文件
 * ConfigurableApplicationContext扩展于ApplicationContext，新增加了两个主要方法：refresh()和close()，让ApplicationContext具有启动、刷新和关闭上下文的功能。
 *
 *
 */
public class SpringTest {

    public static void main(String[] args) throws Exception {

//        UserService userService=new UserServiceImpl();
//        UserService userServiceProxy = (UserService) new MyProxy().getInstance(userService);
//        userServiceProxy.add(1, 2);

//        Resource resource = new ClassPathResource("spring-context.xml");
//        BeanFactory beanFactory=new XmlBeanFactory(resource);

//        ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-context.xml");
//        UserService userService = (UserService) ctx.getBean("userService");
//        int add = userService.add(12, 112);
//        System.out.println(add);

        System.out.println("开始初始化spring容器");
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-context-life.xml");
        System.out.println("spring容器初始化完成");
        applicationContext.getBean("student");
        System.out.println("关闭spring容器");
        ((ClassPathXmlApplicationContext)applicationContext).registerShutdownHook();
    }
}
