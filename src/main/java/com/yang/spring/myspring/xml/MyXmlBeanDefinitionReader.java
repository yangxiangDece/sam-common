package com.yang.spring.myspring.xml;

import com.yang.spring.myspring.MyAbstractBeanDefinitionReader;
import com.yang.spring.myspring.MyBeanDefinition;
import com.yang.spring.myspring.MyBeanReference;
import com.yang.spring.myspring.MyPropertyValue;
import com.yang.spring.myspring.io.MyResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * <p>Title:MyXmlBeanDefinitionReader</p>
 * <p>Description:解析XML文件</p>
 * <p>Company:成都瑞智创家科技有限公司</p>
 *
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

    public MyXmlBeanDefinitionReader() {
        this(new MyResourceLoader());
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
     *
     * @param doc
     */
    private void registerBeanDefinitions(Document doc) {
        // 读取文档的根元素
        Element root = doc.getDocumentElement();
        // 解析元素的根节点及所有子节点并添加到容器中
        parseBeanDefinitions(root);
    }

    /**
     * 解析元素的根节点及根节点下的所有子节点并添加进注册容器
     *
     * @param root
     */
    private void parseBeanDefinitions(Element root) {
        NodeList childNodes = root.getChildNodes();
        for (int i = 0, len = childNodes.getLength(); i < len; i++) {
            Node node = childNodes.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                processBeanDefinitions(ele);
            }
        }
    }

    /**
     * 解析给给定的节点，包括name，class，property， name， value，ref
     *
     * @param ele
     */
    private void processBeanDefinitions(Element ele) {
        String name = ele.getAttribute("name");
        String className = ele.getAttribute("class");
        MyBeanDefinition beanDefinition = new MyBeanDefinition();
        // 设置bean 定义对象的 全限定类名
        beanDefinition.setClassName(className);
        // 向 bean 注入配置文件中的成员变量
        addPropertyValues(ele, beanDefinition);
        // 向注册容器 添加bean名称和bean定义
        getRegistry().put(name, beanDefinition);
    }

    /**
     * 向 bean 注入配置文件中的成员变量
     *
     * @param ele
     * @param beanDefinition
     */
    private void addPropertyValues(Element ele, MyBeanDefinition beanDefinition) {
        // 获取给定元素的 property 属性集合
        NodeList propertyNode = ele.getElementsByTagName("property");
        for (int i = 0; i < propertyNode.getLength(); i++) {
            Node node = propertyNode.item(i);
            if (node instanceof Element) {
                Element propertyEle = (Element) node;
                String name = propertyEle.getAttribute("name");
                String value = propertyEle.getAttribute("value");
                if (value != null && value.length() > 0) {
                    beanDefinition.getPropertyValues().addPropertyValue(new MyPropertyValue(name, value));
                } else {
                    String ref = propertyEle.getAttribute("ref");
                    if (ref == null || ref.length() == 0) {
                        // 如果属性ref为空，则抛出异常
                        throw new IllegalArgumentException(
                                "Configuration problem: <property> element for property '"
                                        + name + "' must specify a ref or value");
                    }
                    // 如果不为空，表示有ref引用 则创建建一个 “bean的引用” 实例，构造参数为名称，实例暂时为空
                    MyBeanReference beanReference = new MyBeanReference(name, ref);
                    // 向给定的 “bean定义” 中添加属性
                    beanDefinition.getPropertyValues().addPropertyValue(new MyPropertyValue(name, beanReference));
                }
            }
        }
    }
}
