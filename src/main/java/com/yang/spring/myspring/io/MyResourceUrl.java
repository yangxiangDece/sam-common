package com.yang.spring.myspring.io;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * <p>Title:</p>
 * <p>Description:资源URL</p>
 * <p>Company:成都瑞智创家科技有限公司</p>
 *
 * @author Yang Xiang
 * @date 2019/5/14 12:02
 */
public class MyResourceUrl implements MyResource {

    /**
     * 类库URL
     */
    private final URL url;

    public MyResourceUrl(URL url) {
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws Exception {
        URLConnection urlConnection = this.url.openConnection();
        urlConnection.connect();
        return urlConnection.getInputStream();
    }

    public URL getUrl() {
        return url;
    }
}
