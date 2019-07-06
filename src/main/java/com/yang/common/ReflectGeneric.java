package com.yang.common;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>Title:ReflectGeneric</p>
 * <p>Description:反射泛型类型工具类 (例如：ObjectClass<String,Integer>，String为索引0，Integer为索引1)</p>
 * <p>Company:成都瑞智创家科技有限公司</p>
 *
 * @author Yang Xiang
 * @date 2019/7/6 15:40
 */
public class ReflectGeneric {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectGeneric.class);

    // 默认ID
    public static final String DEFAULT_ID = "id";

    private static final String RESOURCE_REGEX = ".*.java*";

    /**
     * 不需要验证的字段名称
     */
    private static final String[] NOT_VERIFY_ENTITY_ARRAY = {"serialVersionUID"};

    /**
     * 获得源文件的泛型类型，取第一个参数的泛型类型
     *
     * @param resource
     * @return
     */
    public static Class getResourceGenericType(String resource) {
        Class<?> clazz = getEntityClazz(resource);
        return getClassGenericType(clazz);
    }

    /**
     * 根据加载源文件获取该类
     *
     * @param resource 类路径地址
     * @return
     */
    public static Class<?> getEntityClazz(String resource) {
        Class<?> clazz = null;
        try {
            if (StringUtils.isBlank(resource)) {
                return null;
            }

            if (!resource.matches(RESOURCE_REGEX)) {
                String temp = resource.substring(0, resource.indexOf(".java"));
                resource = temp.replaceAll("/", ".");
            }

            clazz = Class.forName(resource);
        } catch (ClassNotFoundException e) {
            LOGGER.error("get entity class path is error!", e);
        }
        return clazz;
    }

    /**
     * 获得参数化类型的泛型类型，取第一个参数的泛型类型，（默认去的第一个）
     *
     * @param clazz 参数化类型
     * @return 泛型类型
     */
    public static Class getClassGenericType(final Class clazz) {
        return getClassGenericType(clazz, 0);
    }

    /**
     * 根据参数索引获得参数化类型的泛型类型，（通过索引取得）
     *
     * @param clazz 参数化类型
     * @param index 参数索引
     * @return 泛型类型
     */
    public static Class getClassGenericType(final Class clazz, final int index) {

        Type genType = clazz.getGenericSuperclass();

        if (genType == null) {
            genType = clazz.getGenericInterfaces()[index];
        }

        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class<?>) params[index];
    }

    /**
     * 检查实体字段是否通过验证
     *
     * @param targetValue 需要验证的值
     * @return 包含为true
     */
    public static boolean isVerifyEntity(String targetValue) {
        return Arrays.asList(NOT_VERIFY_ENTITY_ARRAY).contains(targetValue);
    }

    /**
     * 获取数据库的真实列名
     *
     * @param field
     * @param name
     * @return
     */
    public static String getSQLColumn(Field field, String name) {

        if (field == null || StringUtils.isBlank(name) || field.isAnnotationPresent(Transient.class)) {
            return null;
        }

        if (field.isAnnotationPresent(Column.class)) {
            Column column = field.getAnnotation(Column.class);
            String columnName = column.name();
            if (StringUtils.isNotBlank(columnName)) {
                return column.name();
            }
        }

        return ReflectGeneric.formatName(name);
    }

    /**
     * 获取数据库的真实列名
     *
     * @param clazz 类
     * @param name  属性名
     * @return
     */
    public static String getSQLColumn(Class<?> clazz, String name) {
        return getSQLColumn(getField(clazz, name), name);
    }

    /**
     * 获取类中对应Field
     *
     * @param clazz 类
     * @param name  属性名
     * @return
     */
    public static Field getField(Class<?> clazz, String name) {
        Field field = null;
        if (clazz != null && StringUtils.isNotBlank(name)) {
            Class<?> tempClass = clazz;
            while (tempClass != null) {
                try {
                    field = tempClass.getDeclaredField(name);
                    if (field != null) {
                        break;
                    }
                } catch (NoSuchFieldException | SecurityException e) {

                }

                tempClass = tempClass.getSuperclass(); // 得到父类,然后赋给自己
            }
        }

        return field;
    }

    /**
     * 按默认规则格式化字符串 例如：userId -->user_id
     *
     * @param fieldName 格式化的属性名
     * @return
     */
    public static String formatName(String fieldName) {
        if (StringUtils.isBlank(fieldName)) {
            return null;
        }

        int index = 0;
        StringBuilder builder = new StringBuilder();
        for (Integer temp : getUpperIndex(fieldName)) {
            if (index != temp) {
                builder.append("_").append(fieldName.substring(index, temp));
                index = temp;
            }
        }
        builder.append("_").append(fieldName.substring(index, fieldName.length()));
        return builder.deleteCharAt(0).toString().toLowerCase();
    }

    /**
     * 获取传入类型包含的字段
     *
     * @param clazz
     * @return
     */
    public static Field[] getClassFileds(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<>();
        Class<?> tempClass = clazz;
        while (tempClass != null) {
            fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            tempClass = tempClass.getSuperclass(); // 得到父类,然后赋给自己
        }

        return fieldList.toArray(new Field[]{});
    }

    /**
     * 拆分大写
     *
     * @param str
     * @return
     */
    private static List<Integer> getUpperIndex(String str) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < str.length(); i++) {
            char temp = str.charAt(i);
            if (String.valueOf(temp).matches("[A-Z]")) {
                list.add(i);
            }
        }
        return list;
    }
}
