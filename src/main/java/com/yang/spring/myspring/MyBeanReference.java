package com.yang.spring.myspring;

/**
 * <p>Title:</p>
 * <p>Description:bean的引用</p>
 * <p>Company:成都瑞智创家科技有限公司</p>
 *
 * @author Yang Xiang
 * @date 2019/5/14 11:20
 */
public class MyBeanReference {

    /**
     * bean的名称
     */
    private String name;

    /**
     * bean 对象
     */
    private Object bean;

    /**
     * 引用的bean的名称
     */
    private String refName;

    /**
     * 构造器 必须包含一个bean名字
     */
    public MyBeanReference(String name, String refName) {
        this.name = name;
        this.refName = refName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public String getRefName() {
        return refName;
    }

    public void setRefName(String refName) {
        this.refName = refName;
    }
}
