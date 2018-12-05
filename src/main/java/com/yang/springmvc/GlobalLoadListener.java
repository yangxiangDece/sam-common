package com.yang.springmvc;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 1、从WebApplicationContext中可以获得ServletContext的引用，整个web应用上下文对象将作为属性放置到ServletContext中，以便web应用环境可以访问Spring应用上下文。
 * 2、Spring可以通过WebApplicationContextUtils.getWebApplicationContext(servletContext);获取WebApplicationContext实例
 * 3、在非web环境下，Bean只有 singleton 和 prototype 两种作用域，WebApplicationContext为Bean添加了三个新的作用域：request、session、global session
 * 4、由于web应用比一般的应用拥有更多的特性，因此WebApplicationContext扩展了ApplicationContext。WebApplicationContext定义了一个常量ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE，
 *      在web应用上下文启动的时候，WebApplicationContext实例即以此为键放置在ServletContext的属性列表中。
 * 5、ConfigurableWebApplicationContext扩展了WebApplicationContext，它允许通过配置的方式实例化WebApplicationContext，同时定义了两个重要的方法：
 *      a、setServletContext(@Nullable ServletContext servletContext);为Spring设置web应用上下文，以便二者融合。
 *      b、setConfigLocation(String configLocation);设置Spring配置文件地址，一般情况下，配置文件地址是相对于web根目录的地址，如/WEB-IFN/smart-dao.xml，但是用户可以
 *          使用带资源类型前缀的地址，比如：classpath:com/smart/beans.xml等
 * 6、WebApplicationContext的初始化，它需要ServletContext实例，所以必须在拥有web容器的前提下才能完成启动工作。在web.xml中配置自启动的Servlet或定义web容器监听器（ServletContextListener），
 *      借助二者中的任何一个，就可以完成启动Spring Web应用上下文的工作
 *      Spring提供了用于启动WebApplicationContext的Web容器监听器：
 *          org.springframework.web.context.ContextLoaderListener，通过Web容器上下文参数contextConfigLocation获取Spring配置文件的地址：
 *          <context-param>
 *              <param-name>contextConfigLocation</param-name>
 *              <param-value>classpath:spring-mvc.xml</param-value>
 *          </context-param>
 *      如果不使用xml配置，使用@Configuration的Java类提供配置信息，则web.xml需要按一下配置方式配置：
 *          <context-param>
 *              <param-name>contextClass</param-name>
 *              <param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
 *          </context-param>
 *          <context-param>
 *              <param-name>contextConfigLocation</param-name>
 *              <param-value>com.yang.springMvcConfig</param-value>//这个配置类必须用@Congiuration注解标注
 *          </context-param>
 *          ContextLoaderListener如果发现配置了contextClass上下文参数，就会使用参数所指定的WebApplicationContext实现类(AnnotationConfigWebApplicationContext)初始化容器，
 *          该实现类会根据contextConfigLocation上下文指定的标注了@Congiuration的配置类所提供的Spring配置信息初始化容器
 *
 */
public class GlobalLoadListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        // 设置根目录别名
        servletContext.setAttribute("ctx", servletContext.getContextPath());
        //静态资源加载到内存中
//        servletContext.setAttribute("advertisementtype", service.loadAdvertisementType());//广告类型
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
//        webApplicationContext.getBean()
    }

    //获取Spring托管的Bean
    private Object getBean(ServletContext servletContext, String beanName) {
        return WebApplicationContextUtils.getWebApplicationContext(
                servletContext).getBean(beanName);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
