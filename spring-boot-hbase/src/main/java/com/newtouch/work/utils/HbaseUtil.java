package com.newtouch.work.utils;

import com.newtouch.work.config.HBaseConfig;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @program: HbaseUtil
 * @description: hbase
 * @author: yepengfei
 * @create: 2021/3/19、10:09
 * @Version 1.0
 **/
public class HbaseUtil {
    private RestTemplate restTemplate = new RestTemplate();
    public  String getBody(String str, HBaseConfig config) throws IOException {
        //拼接处理访问hbase的路径
        String url = str;
        //请求头信息
        HttpHeaders headers = HttpRequestUtil.getHeader(config.userName,config.password_dev);

        HttpEntity<String> requestEntity = new HttpEntity<>(null,headers);
        String body = restTemplate.exchange(url, HttpMethod.GET,requestEntity,String.class).getBody();
        return body;



    }
}
