package com.yang.springmvc.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstInterceptor implements HandlerInterceptor {

    /**
     * 此方法在目标方法之前被调用
     *  若返回值为true，则继续执行后续的拦截器和目标方法
     *  若返回值位false，则会再调用后续的拦截器和目标方法
     *
     *  原理：
     *      在DispatcherServlet中的doDispatch方法中，有：
     *          if (!mappedHandler.applyPreHandle(processedRequest, response)) { return; }
     *          这个applyPreHandle方法的内部是会循环调用注册的所有interceptor的preHandle方法，如果有一个拦截器返回false，那么就返回false结束。
     *
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("[FirstInterceptor] preHandle invoke...");
        return true;
    }

    /**
     * 调用目标方法之后，渲染视图之前调用
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        System.out.println("[FirstInterceptor] postHandle invoke...");
    }

    /**
     * 渲染视图之后调用
     * @param request
     * @param response
     * @param handler
     * @param ex
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("[FirstInterceptor] afterCompletion invoke...");
    }
}
