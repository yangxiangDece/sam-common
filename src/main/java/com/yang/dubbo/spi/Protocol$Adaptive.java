package com.yang.dubbo.spi;

import org.apache.dubbo.common.extension.ExtensionLoader;

/**
 * @see org.apache.dubbo.common.extension.Adaptive
 * <p>
 * 当注解 @Adaptive 放到方法上时，会为这个方法生成代理，如下
 * 其中 @Adaptive 的 value 有值是 会从url中获取value的参数值 来获取具体的扩展类
 * 即：String extName = url.getParameter("proxy", "javassist"); 未获取到则使用SPI注解中value的默认扩展类
 * ProxyFactory extension = ExtensionLoader.getExtensionLoader(ProxyFactory.class).getExtension(extName);
 * <p>
 * <p>
 * 区别：方法上的@Adaptive 的 value 有值 则：String extName = url.getParameter("proxy", "javassist");
 * 区别：方法上的@Adaptive 的 value 无值 则：String extName = (url.getProtocol() == null ? "dubbo" : url.getProtocol());
 * <p>
 * <p>
 * Adaptive({Constants.DISPATCHER_KEY, "dispather", "channel.handler"})
 * 当value有多个值时：String extName = url.getParameter("dispatcher", url.getParameter("dispather", url.getParameter("channel.handler", "all")));
 * 从后向前查找匹配参数
 */
public class Protocol$Adaptive implements org.apache.dubbo.rpc.Protocol {

    public void destroy() {
        throw new UnsupportedOperationException("The method public abstract void org.apache.dubbo.rpc.Protocol.destroy() of interface org.apache.dubbo.rpc.Protocol is not adaptive method!");
    }

    public int getDefaultPort() {
        throw new UnsupportedOperationException("The method public abstract int org.apache.dubbo.rpc.Protocol.getDefaultPort() of interface org.apache.dubbo.rpc.Protocol is not adaptive method!");
    }

    public org.apache.dubbo.rpc.Exporter export(org.apache.dubbo.rpc.Invoker arg0) throws org.apache.dubbo.rpc.RpcException {
        if (arg0 == null) throw new IllegalArgumentException("org.apache.dubbo.rpc.Invoker argument == null");
        if (arg0.getUrl() == null)
            throw new IllegalArgumentException("org.apache.dubbo.rpc.Invoker argument getUrl() == null");
        org.apache.dubbo.common.URL url = arg0.getUrl();
        String extName = (url.getProtocol() == null ? "dubbo" : url.getProtocol());
        if (extName == null)
            throw new IllegalStateException("Failed to get extension (org.apache.dubbo.rpc.Protocol) name from url (" + url.toString() + ") use keys([protocol])");
        org.apache.dubbo.rpc.Protocol extension = ExtensionLoader.getExtensionLoader(org.apache.dubbo.rpc.Protocol.class).getExtension(extName);
        return extension.export(arg0);
    }

    public org.apache.dubbo.rpc.Invoker refer(java.lang.Class arg0, org.apache.dubbo.common.URL arg1) throws org.apache.dubbo.rpc.RpcException {
        if (arg1 == null) throw new IllegalArgumentException("url == null");
        org.apache.dubbo.common.URL url = arg1;
        String extName = (url.getProtocol() == null ? "dubbo" : url.getProtocol());
        if (extName == null)
            throw new IllegalStateException("Failed to get extension (org.apache.dubbo.rpc.Protocol) name from url (" + url.toString() + ") use keys([protocol])");
        org.apache.dubbo.rpc.Protocol extension = ExtensionLoader.getExtensionLoader(org.apache.dubbo.rpc.Protocol.class).getExtension(extName);
        return extension.refer(arg0, arg1);
    }
}
