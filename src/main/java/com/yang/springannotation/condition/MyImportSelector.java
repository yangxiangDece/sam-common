package com.yang.springannotation.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 自定义逻辑返回需要导入的组件
 */
public class MyImportSelector implements ImportSelector {

    /**
     * 返回值就是注册到容器中的组件全类名
     *
     * @param importingClassMetadata 当前类的注解信息
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        return new String[]{"com.yang.springannotation.bean.Blue", "com.yang.springannotation.bean.Red"};
    }
}
