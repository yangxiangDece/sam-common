package com.yang.common.httputil;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.exception.HttpProcessException;

import java.util.Map;

/**
 * <p>Description:Http请求工具类</p>
 *
 * @author Yang Xiang
 * @date 2021/2/23 14:17
 */
public class HttpClientUtils {

    private static String EN_CODING = "utf-8";

    public static <T> String sendPost(String url, Map<String, Object> paramMap) throws HttpProcessException {
        HttpConfig config = HttpConfig.custom().url(url).encoding(EN_CODING);
        if (paramMap != null && !paramMap.isEmpty())
            config.map(paramMap);

        return HttpClientUtil.post(config);
    }

    public static <T> String sendGet(String url, Map<String, Object> paramMap) throws HttpProcessException {
        HttpConfig config = HttpConfig.custom().url(url).encoding(EN_CODING);
        if (paramMap != null && !paramMap.isEmpty())
            config.map(paramMap);
        return HttpClientUtil.get(config);
    }

}