package com.yang.spring.myspring.io;

import java.io.InputStream;

/**
 * <p>Title:</p>
 * <p>Description:资源定义</p>
 * <p>Company:成都瑞智创家科技有限公司</p>
 *
 * @author Yang Xiang
 * @date 2019/5/14 12:01
 */
public interface MyResource {

    /**
     * 获取输入流
     *
     * @return
     * @throws Exception
     */
    InputStream getInputStream() throws Exception;
}
