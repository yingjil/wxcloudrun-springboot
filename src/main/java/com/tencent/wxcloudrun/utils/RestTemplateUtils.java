package com.tencent.wxcloudrun.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class RestTemplateUtils {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * get请求获取json数据，并转换成对象
     * @param url
     * @param respClazz
     * @param paramMap
     * @param <T>
     * @return
     */
    public <T> T getForObject(String url, Class<T> respClazz,Map<String, ?> paramMap) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class, paramMap);
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            return null;
        }
        return JSON.parseObject(responseEntity.getBody(), respClazz);
    }

}
