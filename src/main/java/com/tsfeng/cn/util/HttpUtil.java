package com.tsfeng.cn.util;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @author tsfeng
 * @version 创建时间 2019/4/19 14:36
 */
public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
//
    /**
     * 重试次数
     */
    private static final int RETRY_COUNT = 3;
    private static final int DEFAULT_TIMEOUT = 60000;
    /**
     * 最大连接数
     */
    private static final int MAX_TOTAL = 200;
    /**
     * 每个路由基础的最大连接数
     */
    private static final int DEFAULT_MAXPERROUTE = 200;

    private CloseableHttpClient httpClient;
    private RequestConfig requestConfig;
    private HttpHost proxy;

    private volatile static HttpUtil httpUtil;

    public static HttpUtil getInstance() {
        HttpUtil result = httpUtil;
        //先检查实例是否存在，如果不存在才进入下面的同步块
        if (result == null) {
            //同步块，线程安全的创建实例
            synchronized (HttpUtil.class) {
                result = httpUtil;
                //再次检查实例是否存在，如果不存在才真正的创建实例
                if (result == null) {
                    result = httpUtil = new HttpUtil();
                }
            }
        }
        return result;
    }

    private HttpUtil() {
        initRequestConfig();
        initHttpClient();
    }

    private void initRequestConfig() {
        logger.info("initRequestConfig");
        //配置请求的超时设置。单位都是毫秒
        requestConfig = RequestConfig.custom()
                //从连接池中获取连接的超时时间，超过该时间未拿到可用连接，会抛出org.apache.http.conn.ConnectionPoolTimeoutException: Timeout waiting for connection from pool
                .setConnectionRequestTimeout(DEFAULT_TIMEOUT)
                //连接上服务器(握手成功)的时间，超出该时间抛出connect timeout
                .setConnectTimeout(DEFAULT_TIMEOUT)
                //服务器返回数据(response)的时间，超过该时间抛出read timeout
                .setSocketTimeout(DEFAULT_TIMEOUT)
                //Invalid cookie header
                .setCookieSpec(CookieSpecs.STANDARD_STRICT)
                .setRedirectsEnabled(false)
                .setExpectContinueEnabled(true)
                .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
                .build();
    }

    private void initHttpClient() {
        logger.info("initHttpClient");
        //请求重试处理
        HttpRequestRetryHandler httpRequestRetryHandler = (exception, executionCount, context) -> {
            if (executionCount >= RETRY_COUNT) {
                // 重试次数
                return false;
            }
            if (exception instanceof NoHttpResponseException) {
                // 如果服务器丢掉了连接，那么就重试
                return true;
            }
            if (exception instanceof SSLHandshakeException) {
                // 不要重试SSL握手异常
                return false;
            }
            if (exception instanceof InterruptedIOException) {
                // 超时
                return false;
            }
            if (exception instanceof UnknownHostException) {
                // 目标服务器不可达
                return false;
            }
            if (exception instanceof SSLException) {
                // ssl握手异常
                return false;
            }

            HttpClientContext clientContext = HttpClientContext.adapt(context);
            HttpRequest request = clientContext.getRequest();
            return !(request instanceof HttpEntityEnclosingRequest);
        };

        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();

        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);
        connManager.setDefaultSocketConfig(SocketConfig.custom().setSoKeepAlive(true).build());
        // 最大连接数
        connManager.setMaxTotal(MAX_TOTAL);
        // 每个路由基础的连接数
        connManager.setDefaultMaxPerRoute(DEFAULT_MAXPERROUTE);

        httpClient = HttpClients.custom()
                .setConnectionManager(connManager)
                .setRetryHandler(httpRequestRetryHandler)
                .build();
        logger.info("init success");
    }


    private String getResult(HttpRequestBase request) {
        request.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            logger.info(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String sendGetRequest(String url) {
        HttpGet httpGet = new HttpGet(url);
        return getResult(httpGet);
    }


    public String sendPostRequest(String url) {
        HttpPost httpPost = new HttpPost(url);
        return getResult(httpPost);

    }

    public void setProxy(String host, int port) {
        proxy = new HttpHost(host, port);
    }

    public void enableFidder() {
        proxy = new HttpHost("127.0.0.1", 8888);
    }

    public void disableProxy() {
        proxy = null;
    }

    public static void main(String[] args) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        httpUtil.sendGetRequest("https://zhuanlan.zhihu.com/p/33370207");
    }
}
