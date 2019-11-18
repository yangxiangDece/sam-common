package com.yang.springmvc;

/**
 * HandlerMapping的作用主要是根据request请求匹配/映射上能够处理当前request的handler
 * --核心实现：RequestMappingHandlerMapping
 * HandlerAdapter的作用在于将request中的各个属性，如request param适配为handler能够处理的形式 - 参数绑定、数据校验、内容协商…几乎所有的web层问题都在在这里完成的。
 * --核销实现：RequestMappingHandlerAdapter
 * <p>
 * DispatcherServlet#doDispatch分发流程中
 * 从Chain里拿出Handler（注意是Object类型）然后找到属于它的适配器
 * HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler());
 * ...
 * mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
 * HandlerAdapter对于执行流程的通用性起到了非常重要的作用，它能把任何一个处理器（Object）都适配成一个HandlerAdapter，从而可以做统一的流程处理，
 * 这也是为何DispatcherServlet它能作为其它web处理框架的分发器的原因（因为它没有耦合具体的处理器，你完全可以自己去实现）。
 */
public class SpringMVC {

}
