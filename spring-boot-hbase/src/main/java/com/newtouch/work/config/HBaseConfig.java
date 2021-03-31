package com.newtouch.work.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @program: HBaseConfig
 * @description: hbase配置类
 * @author: yepengfei
 * @create: 2021/3/19、10:14
 * @Version 1.0
 **/
@Configuration
@Data
public class HBaseConfig {


    @Value("${HBase.userName}")
    public String userName;

    @Value("${HBase.password}")
    public String passWord;

    @Value("${HBase.host_dav}")
    public String host;

    @Value("${HBase.password_dev}")
    public String password_dev;


}
