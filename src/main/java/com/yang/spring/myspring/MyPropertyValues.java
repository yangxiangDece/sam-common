package com.yang.spring.myspring;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title:MyPropertyValues </p>
 * <p>Description:属性集合</p>
 * <p>Company:成都瑞智创家科技有限公司</p>
 * @author Yang Xiang
 * @date 2019/5/14 11:50
 */
public class MyPropertyValues {

    /**
     * 属性集合
     */
    private final List<MyPropertyValue> propertyValueList = new ArrayList<>();

    /**
     * 默认构造器
     */
    public MyPropertyValues() {

    }

    /**
     * 添加进属性集合
     */
    public void addPropertyValue(MyPropertyValue pv) {
        this.propertyValueList.add(pv);
    }

    /**
     * 获取属性集合
     */
    public List<MyPropertyValue> getPropertyValueList() {
        return propertyValueList;
    }
}
