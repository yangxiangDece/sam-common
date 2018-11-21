package com.yang.springannotation.bean;

import org.springframework.beans.factory.FactoryBean;

public class ColorFactoryBean implements FactoryBean<Color> {

    @Override
    public Color getObject() throws Exception {
        return new Color();
    }

    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    /**
     * true：单实例，Spring会将这个bean保存起来，每次获取到的都是同一个bean
     * false：多实例
     * @return
     */
    @Override
    public boolean isSingleton() {
        return true;
    }
}
