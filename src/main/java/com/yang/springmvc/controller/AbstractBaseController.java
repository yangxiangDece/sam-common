package com.yang.springmvc.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 如果在当前的 Handler 中找不到 @ExceptionHandler 标记的方法来处理当前方法出现的异常，
 * 则将去 @ControllerAdvice 标记的类中查找 @ExceptionHandler 标记的方法来处理异常。
 */
@ControllerAdvice
public abstract class AbstractBaseController {

    /**
     * 1、在 @ExceptionHandler 方法的入参中可以加入 Exception 类型的参数，该参数即对应发生的异常对象，
     * 2、在 @ExceptionHandler 方法的入参中不能传入map，若希望把异常信息传到页面上，需要使用 ModelAndView 作为返回值
     *
     * @param e
     */
    @ExceptionHandler({Exception.class})
    public void handlerException(Exception e) {
        System.out.println("出异常了..."+e);
    }
}
