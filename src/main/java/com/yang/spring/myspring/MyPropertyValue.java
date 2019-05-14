package com.yang.spring.myspring;

/**
 * <p>Title:MyPropertyValue </p>
 * <p>Description:属性定义</p>
 * <p>Company:成都瑞智创家科技有限公司</p>
 * @author Yang Xiang
 * @date 2019/5/14 11:48
 */
public class MyPropertyValue {

    private final String name;

    private final Object value;

    public MyPropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
