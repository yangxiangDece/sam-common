package com.yang.spring.myspring.io;

import java.net.URL;

/**
 * <p>Title:</p>
 * <p>Description:资源加载器</p>
 * <p>Company:成都瑞智创家科技有限公司</p>
 *
 * @author Yang Xiang
 * @date 2019/5/14 12:02
 */
public class MyResourceLoader {

    /**
     * 给定一个位置， 使用类加器的资源加载URL，并创建一个“资源URL”对象，便于获取输入流
     *
     * @param location
     * @return
     */
    public MyResourceUrl getResource(String location) {
        URL url = this.getClass().getClassLoader().getResource(location);
        return new MyResourceUrl(url);
    }
}
