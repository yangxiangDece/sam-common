package com.yang.spring.myspring.xml;

import com.yang.spring.myspring.MyAbstractBeanDefinitionReader;
import com.yang.spring.myspring.io.MyResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * <p>Title:MyXmlBeanDefinitionReader</p>
 * <p>Description:解析XML文件</p>
 * <p>Company:成都瑞智创家科技有限公司</p>
 * @author Yang Xiang
 * @date 2019/5/14 12:12
 */
public class MyXmlBeanDefinitionReader extends MyAbstractBeanDefinitionReader {

    /**
     * 构造器器必须有一个资源加载器， 默认插件创建一个map容器
     *
     * @param resourceLoader
     */
    public MyXmlBeanDefinitionReader(MyResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    public void readerXML(String location) throws Exception {
        // 创建一个资源加载器
        MyResourceLoader resourceLoader = new MyResourceLoader();
        // 从资源加载器中获取输入流
        InputStream inputStream = resourceLoader.getResource(location).getInputStream();
        // 获取文档建造者工厂实例
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // 通过文档建造者工厂实例创建文档建造者
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        // 文档建造者解析输入流 返回文档对象
        Document doc = documentBuilder.parse(inputStream);
        // 根据给定的文档对象进行解析，并注册到bean定义的容器中
        registerBeanDefinitions(doc);
        // 关闭输入流
        inputStream.close();
    }

    /**
     * 根据给定的文档对象进行解析，并注册到bean定义的容器中
     * @param doc
     */
    private void registerBeanDefinitions(Document doc) {
        // 读取文档的根元素
        Element root = doc.getDocumentElement();
        // 解析元素的根节点及所有子节点并添加到容器中

    }
}
