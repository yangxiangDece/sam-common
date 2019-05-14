package com.yang.spring;

import java.lang.reflect.Method;

public class Test {

    public static void main(String[] args) throws Exception {
//        Class clazz = ClassLoader.getSystemClassLoader().loadClass("com.yang.spring.Person");
        Class clazz = Class.forName("com.yang.spring.Person");
        Person person = (Person) clazz.newInstance();
        Method method = clazz.getMethod("setName", String.class);
        method.invoke(person, "马云");
        System.out.println(person);
        System.out.println(clazz.getClassLoader());
        System.out.println(clazz.getClassLoader().getParent());
        System.out.println(clazz.getClassLoader().getParent().getParent());

        System.out.println(Integer.class.getClassLoader());

        System.out.println(Thread.currentThread().getContextClassLoader());
    }
}

class Person {

    private String name;

    static {
        System.out.println("执行了静态方法...");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}