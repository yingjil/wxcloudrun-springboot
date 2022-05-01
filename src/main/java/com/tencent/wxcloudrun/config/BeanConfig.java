package com.tencent.wxcloudrun.config;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.protocol.HttpContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;


@Configuration
public class BeanConfig {
    @Bean
    RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        HttpClient httpClient = HttpClientBuilder.create()
                /**
                 * 重定向策略
                 */
                .setRedirectStrategy(new LaxRedirectStrategy())
                /**
                 * 重试策略
                 */
                .setRetryHandler(new HttpRequestRetryHandler() {
                    public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                        if (executionCount >= 3) {
                            return false;
                        }
                        if (exception instanceof InterruptedIOException) {
                            return false;
                        }
                        if (exception instanceof UnknownHostException) {
                            return false;
                        }
                        if (exception instanceof ConnectTimeoutException) {
                            return false;
                        }
                        if (exception instanceof SSLException) {
                            return false;
                        }

                        HttpClientContext clientContext = HttpClientContext.adapt(context);
                        HttpRequest request = clientContext.getRequest();
                        boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
                        if (idempotent) {
                            return true;
                        }
                        return false;
                    }
                })
                .build();
        factory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(factory);
        return restTemplate;
    }
}
