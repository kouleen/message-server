package com.kouleen.message.service.infrastructure.utils;

import com.kouleen.message.service.infrastructure.core.CommonException;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.List;

/**
 * @author zhangqing
 * @since 2023/8/4 9:38
 */
public class HttpUtils {

    private static RestTemplate restTemplate = getRestTemplate("utf-8");

    public static RestTemplate getRestTemplate(String charset){
        try {
            return getInstance(charset);
        }catch (Exception exception){
            exception.printStackTrace();
            throw new CommonException(exception);
        }
    }

    public static RestTemplate getInstance(String charset) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        //忽略证书
        SSLContextBuilder sslContextBuilder = SSLContexts.custom();
        SSLContextBuilder contextBuilder = sslContextBuilder.loadTrustMaterial(null, acceptingTrustStrategy);
        SSLContext sslContext = contextBuilder.build();

        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext);

        RegistryBuilder<ConnectionSocketFactory> connectionSocketFactoryRegistryBuilder = RegistryBuilder.create();
        Registry<ConnectionSocketFactory> socketFactoryRegistry = connectionSocketFactoryRegistryBuilder
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", sslConnectionSocketFactory).build();

        HttpClientBuilder clientBuilder = HttpClients.custom();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        //连接池的最大连接数，0代表不限；如果取0，需要考虑连接泄露导致系统崩溃的后果
        connectionManager.setMaxTotal(1000);
        //每个路由的最大连接数,如果只调用一个地址,可以将其设置为最大连接数
        connectionManager.setDefaultMaxPerRoute(300);

        clientBuilder.setConnectionManager(connectionManager);

        CloseableHttpClient httpClient = clientBuilder.build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        requestFactory.setConnectionRequestTimeout(3000);
        requestFactory.setConnectTimeout(3000);
        requestFactory.setReadTimeout(30000);

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        List<HttpMessageConverter<?>> httpMessageConverters = restTemplate.getMessageConverters();
        for (HttpMessageConverter<?> httpMessageConverter : httpMessageConverters) {
            if (httpMessageConverter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) httpMessageConverter).setDefaultCharset(Charset.forName(charset));
                break;
            }
        }
        return restTemplate;
    }

    public static String getByHttpClientWithChrome(String url){
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("user-agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.67 Safari/537.36");
            HttpEntity<String> requestEntity = new HttpEntity<>(null, httpHeaders);
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
            if(responseEntity.getStatusCode() == HttpStatus.OK){
                return responseEntity.getBody();
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return null;
    }
}
