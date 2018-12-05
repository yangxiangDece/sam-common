package com.yang.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 *
     [MyBeanDefinitionRegistryPostProcessor] postProcessBeanDefinitionRegistry invoke...bean的数量：6
     [MyBeanDefinitionRegistryPostProcessor] postProcessBeanFactory invoke...bean的数量：7
     BeanFactoryPostProcessor 构造器 invoke...
     [BeanFactoryPostProcessor] postProcessBeanFactory invoke...
     BeanPostProcessor 构造器 invoke...
     InstantiationAwareBeanPostProcessorAdapter 构造器 invoke ...
     [InstantiationAwareBeanPostProcessorAdapter] 调用了 postProcessBeforeInstantiation()...
     【构造器】调用Student的构造器实例化...
     [InstantiationAwareBeanPostProcessorAdapter] 调用了 postProcessAfterInstantiation()...
     [InstantiationAwareBeanPostProcessorAdapter] 调用了 postProcessProperties()...
     【属性注入】name
     【属性注入】address
     【属性注入】phone
     [BeanNameAware] 调用了 setBeanName() ... ：Student{name='张三', address='成都软件园', phone=12456, beanFactory=null, beanName='student'}
     [BeanFactoryAware] 调用 setBeanFactory()... ：Student{name='张三', address='成都软件园', phone=12456, beanFactory=org.springframework.beans.factory.support.DefaultListableBeanFactory@598067a5: defining beans [student,com.yang.spring.processor.MyBeanPostProcessor#0,com.yang.spring.processor.MyBeanFactoryPostProcessor#0,com.yang.spring.processor.MyInstantiationAwareBeanPostProcessor#0,com.yang.spring.processor.MyApplicationContext#0,com.yang.spring.processor.MyBeanDefinitionRegistryPostProcessor#0,BeanDefinitionRegistryPostProcessor--Blue]; root of factory hierarchy, beanName='student'}
     [ApplicationContextAware] student setApplicationContext
     [BeanPostProcessor] postProcessBeforeInitialization bean:student
     [InitializingBean] 调用 afterPropertiesSet()... ：Student{name='张三', address='成都软件园', phone=12456, beanFactory=org.springframework.beans.factory.support.DefaultListableBeanFactory@598067a5: defining beans [student,com.yang.spring.processor.MyBeanPostProcessor#0,com.yang.spring.processor.MyBeanFactoryPostProcessor#0,com.yang.spring.processor.MyInstantiationAwareBeanPostProcessor#0,com.yang.spring.processor.MyApplicationContext#0,com.yang.spring.processor.MyBeanDefinitionRegistryPostProcessor#0,BeanDefinitionRegistryPostProcessor--Blue]; root of factory hierarchy, beanName='student'}
     调用了 bean 的 init-method ....
     [BeanPostProcessor] postProcessAfterInitialization bean:student
     [InstantiationAwareBeanPostProcessorAdapter] 调用了 postProcessBeforeInstantiation()...
     [ApplicationContextAware] 构造器 invoke...
     [InstantiationAwareBeanPostProcessorAdapter] 调用了 postProcessAfterInstantiation()...
     [InstantiationAwareBeanPostProcessorAdapter] 调用了 postProcessProperties()...
     [ApplicationContextAware] setApplicationContext invoke ...
     [BeanPostProcessor] postProcessBeforeInitialization bean:com.yang.spring.processor.MyApplicationContext#0
     [BeanPostProcessor] postProcessAfterInitialization bean:com.yang.spring.processor.MyApplicationContext#0
     [InstantiationAwareBeanPostProcessorAdapter] 调用了 postProcessBeforeInstantiation()...
     [InstantiationAwareBeanPostProcessorAdapter] 调用了 postProcessAfterInstantiation()...
     [InstantiationAwareBeanPostProcessorAdapter] 调用了 postProcessProperties()...
     [BeanPostProcessor] postProcessBeforeInitialization bean:BeanDefinitionRegistryPostProcessor--Blue
     [BeanPostProcessor] postProcessAfterInitialization bean:BeanDefinitionRegistryPostProcessor--Blue
     spring容器初始化完成
     关闭spring容器
     [DisposableBean] 调用了 destroy()...
     调用了 bean 的 destroy-method ...
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
 * ConfigurableApplicationContext扩展于ApplicationContext，新增加了两个主要方法：refresh()和close()，让ApplicationContext具有启动、刷新和关闭上下文的功能，
 *      在应用容器关闭的情况下调用refresh()方法即可启动应用上下文，在已经启动的情况下调用refresh()方法则可以清除缓存并重新装载配置信息。
 *
 * BeanFactory Bean 生命周期：
 *      1、当调用者通过getBean(beanName)向容器请求某一个Bean时，如果容器注册了org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor接口，
 *          则在实例化Bean之前，将调用接口的postProcessBeforeInstantiation()方法
 *      2、根据配置情况调用Bean构造函数或工厂方法实例化Bean
 *      3、如果容器注册了InstantiationAwareBeanPostProcessor接口，那么在实例化Bean之后，调用该接口的postProcessAfterInstantiation()方法，
 *          可在这里对已经实例化的对象进行一些“梳妆打扮”
 *      4、如果Bean配置了属性信息，那么容器在这一步将配置信息设置到Bean对应的属性中，
 *          不过在设置每一个属性之前将先调用InstantiationAwareBeanPostProcessor接口的postProcessProperties()方法
 *      5、调用Bean的属性设置方法设置属性值
 *      6、如果Bean实现了BeanNameAware接口，则将调用setBeanName()方法，将该配置文件中该Bean对应的名称设置到Bean中
 *      7、如果Bean实现了BeanFactoryAware接口，则将调用setBeanFactory()方法，将BeanFactory容器实例设置到Bean中
 *
 *      如果Bean实现了ApplicationContextAware接口，则将调用setApplicationContext()方法，将ApplicationContext容器实例设置到Bean中
 *
 *      8、如果BeanFactory装配了BeanPostProcessor后置处理器，则将调用BeanPostProcessor的postProcessBeforeInitialization(Object bean, String beanName)方法
 *          对Bean进行加工操作，其中，入参bean是当前正在处理的Bean，而beanName是当前Bean的配置名，返回的对象为加工后的Bean。用户可以使用该方法对某些Bean进行特殊处理，
 *          甚至改变Bean的行为。BeanPostProcessor在Spring框架中占有重要地位，为容器提供对Bean进行后续加工处理的切入点，Spring容器所提供的各种神奇功能（如果AOP、动态代理）
 *          都通过BeanPostProcessor来实现
 *      9、如果Bean实现了InitializingBean接口，则将调用接口的afterPropertiesSet()方法
 *      10、如果在<bean/>中通过init-method属性定义了初始化方法，则将执行这个方法。
 *      11、BeanPostProcessor后置处理器定义了两个方法：其一是postProcessBeforeInitialization()，在第8步中已经调用了，
 *          其二是postProcessAfterInstantiation(Object bean, String beanName)，这个方法就是此时调用，容器再次获得对Bean进行加工处理的机会
 *      12、如果在<bean/>中指定Bean的作用范围为scope="prototype"，则将Bean返回给调用者，调用者负责Bean后续的生命周期的管理，Spring不再管理这个Bean的生命周期
 *          如果Bean的作用范围是scope="singleton"，则将Bean放入Spring IOC容器的缓冲池中，并将Bean的引用返回给调用者，Spring继续着这些Bean进行后续的生命周期管理
 *      13、对于scope="singleton"的Bean（默认情况下），当容器关闭时，将触发Spring对Bean后续生命周期的管理工作。如果Bean实现了DisposableBean接口，则将调用接口的destroy()方法
 *          可以在此编写释放资源、记录日志等操作
 *      14、对于scope="singleton"的Bean，如果通过<bean/>的destroy-method属性指定了Bean的销毁方法，那么Spring将执行Bean的这个方法，完成Bean资源的释放操作。
 *
 *      可以将这些方法大致分为4类：
 *          1、Bean自身的方法：如调用Bean构造函数实例化Bean、调用Setter设置Bean的属性值及通过<bean/>的init-method和destroy-method所指定的方法
 *          2、Bean级生命周期接口方法：如BeanNameAware、BeanFactoryAware、InitializingBean和DisposableBean，这些接口方法由Bean类直接实现
 *          3、容器级生命周期接口方法：如InstantiationAwareBeanPostProcessor和BeanPostProcessor接口，一般它们的实现类为后置处理器，后置处理器一般不由Bean本身实现，
 *              它们独立于Bean，实现类似以容器附加装置的形式注册到Spring容器中，并通过接口反射为Spring容器扫描识别。当Spring容器创建任何Bean的时候，这些后置处理器都会发生作用，
 *              所以这些后置处理器影响是全局性的，用户可以通过合理的编写后置处理器，让其仅对感兴趣的Bean进行加工处理
 *          4、工厂后处理接口方法：包括AspectJWeavingEnabler、CustomAutowireConfigurer、ConfigurationClassPostProcessor等方法，工厂后处理器也是容器级的，
 *              在应用上下文装配配置文件后立即调用
 *      Bean级生命周期接口和容器级生命周期接口是个性和共性辩证统一思想的体现，前者解决Bean个性化处理的问题，而后者解决容器中某些Bean共性化处理的问题
 *      Spring容器中可以注册多个后置处理器，可以同时实现Ordered接口，容器将按特定的顺序依次调用这些后处理器
 *      InstantiationAwareBeanPostProcessor是BeanPostProcessor的子类，Spring为其提供了一个适配器类InstantiationAwareBeanPostProcessorAdapter，一般情况下我们继承这个类即可
 *
 *      一般来说开发中不推荐使用InitializingBean和DisposableBean接口，因为这和Spring框架的耦合度太高，一般使用init-method和destroy-method或者@PostConstruct和@PreDestroy都能达到一样的效果
 *      Spring通过InitDestroyAnnotationBeanPostProcessor后置处理器来处理标注了@PostConstruct、@PreDestroy的Bean进行处理，在Bean的初始化后和销毁前执行相应的逻辑
 *
 * ApplicationContext Bean 生命周期：
 *      1、ApplicationContext的生命周期和BeanFactory类似，不同的是，如果Bean实现了ApplicationContextAware接口，
 *          则会调用该接口的setApplicationContext(ApplicationContext applicationContext)方法
 *      2、如果配置文件中声明了工厂后处理器接口BeanFactoryPostProcessor的实现类，则应用上下文在装载配置文件之后，初始化Bean实例之前将调用这些BeanFactoryPostProcessor对配置
 *          信息进行加工处理
 *
 *  ApplicationContext和BeanFactory最大的不同之处在于：前者会利用Java反射机制自动识别出配置文件中定义的BeanPostProcessor、InstantiationAwareBeanPostProcessor
 *      和BeanFactoryPostProcessor，并自动将他们注册到应用上下文中；而后者需要在代码中通过手工调用addBeanPostProcessor()方法进行注册
 *
 *  Spring内部工作机制：
 *      prepareRefresh();
 *      //1、初始化BeanFactory
 *      ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();
 *      prepareBeanFactory(beanFactory);
 *      postProcessBeanFactory(beanFactory);
 *      //2、调用工厂后处理器
 *      invokeBeanFactoryPostProcessors(beanFactory);
 *      //3、调用Bean后处理器
 *      registerBeanPostProcessors(beanFactory);
 *      //4、初始化消息源
 *      initMessageSource();
 *      //5、初始化应用上下文事件广播器
 *      initApplicationEventMulticaster();
 *      //6、初始化其他特殊的Bean：由具体子类实现
 *      onRefresh();
 *      //7、注册事件监听器
 *      registerListeners();
 *      //8、初始化所有单例实例的Bean，使用懒加载模式的Bean除外
 *      finishBeanFactoryInitialization(beanFactory);
 *      //9、完成属性并发布容器刷新事件
 *      finishRefresh();
 *
 *      1、初始化BeanFactory：根据配置文件实例化BeanFactory，在obtainFreshBeanFactory()方法中，首先调用refreshBeanFactory()方法，然后调用getBeanFactory()方法获取
 *          BeanFactory，这两个方法都是由具体的实现类实现的。在这一步里，Spring将配置文件的信息装入容器的Bean定义注册表（BeanDefinitionRegistry）中，此时Bean尚未初始化
 *      2、调用工厂后处理器：根据反射机制从BeanDefinitionRegistry中找出所有实现了BeanFactoryPostProcessor接口的Bean，并调用其postProcessBeanFactory()方法
 *      3、注册Bean后置处理器：根据反射机制从BeanDefinitionRegistry中找出所有实现了BeanPostProcessor接口的Bean，并将它们注册到容器Bean后处理器的注册表中
 *      4、初始化消息源：初始化容器的国际化消息资源
 *      5、初始化应用上下文事件广播器
 *      6、初始化其他特殊的Bean：这是一个钩子方法，子类可以借助这个方法执行一些特殊的操作，比如AbstractRefreshableWebApplicationContext就是用该方法执行初始化的ThemeSource操作
 *      7、注册事件监听器
 *      8、初始化所有单实例的Bean，使用懒加载模式的Bean除外：初始化Bean后，将它们放入Spring IOC容器的缓冲池中
 *      9、发布上下文刷新事件：创建上下文刷新事件，事件广播器负责将这些事件广播到每个注册的事件监听器中
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

//        ClassPathXmlApplicationContext classPathXmlApplicationContext=new ClassPathXmlApplicationContext();
//        classPathXmlApplicationContext.setConfigLocation("spring-context-life.xml");
//        classPathXmlApplicationContext.refresh();


        //XmlBeanFactory 已被废弃，可以使用下面这种方式
//        Resource resource = new ClassPathResource("spring-context.xml");
//        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();
//        XmlBeanDefinitionReader reader=new XmlBeanDefinitionReader(beanFactory);
//        reader.loadBeanDefinitions(resource);
//
//        UserService userService = (UserService) beanFactory.getBean("userService");
//        System.out.println(userService);
    }
}
