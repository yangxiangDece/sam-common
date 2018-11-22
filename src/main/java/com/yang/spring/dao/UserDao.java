package com.yang.spring.dao;

import com.yang.spring.bean.User;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 *  <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.yang.spring.dao"/>
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
    </bean>

 * 这里的@MapperScan就是上面所讲的Mapper扫描器中所需要的配置，会自动生成代理对象。
 * 注意，接口中的方法名称要和对应的MyBatis映射文件中的语句的id值一样，因为生成的动态代理，会根据这个匹配相应的Sql语句执行。
 * 另外就是方法的参数和返回值也需要注意。
 * 接口中的方法如何定义，对应的MyBatis映射文件就应该进行相应的定义。
 * 最后，标注中的userDao是用来作为Spring的Bean的id(或name)进行使用的，方便我们在Service层进行注入使用。
 */
@MapperScan
public interface UserDao {

    void insert(User user);

    List<User> getUsers();
}
