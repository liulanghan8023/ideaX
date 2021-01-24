package com.tramp.idea.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Http工具类
 */
public final class HttpUtils {


    /**
     * http客户端
     */
    private static final CloseableHttpClient HTTP_CLIENT = HttpClients.createDefault();

    /**
     * 请求超时时间设置(10秒)
     */
    private static final int TIMEOUT = 10 * 1000;

    /**
     * 私有构造方法
     */
    private HttpUtils() {
        throw new UnsupportedOperationException();
    }


    private static RequestConfig getDefaultConfig() {
        return RequestConfig.custom().setSocketTimeout(TIMEOUT).setConnectTimeout(TIMEOUT).build();
    }


}
