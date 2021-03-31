package com.newtouch.work.test;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * @program: Configration
 * @description: 配置文件获取DB2连接信息
 * @author: yepengfei
 * @create: 2021/3/19、16:29
 * @Version 1.0
 **/
public class Configration {
    private Properties properties ;
    private FileInputStream fileInputStream ;
    private String value;

    public Configration() {
        properties = new Properties();
    }

    public Configration(String filePath) {
        this();
        try {
            fileInputStream = new FileInputStream(filePath);
            properties.load(fileInputStream);
            fileInputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getValue(String key) {
        if(properties.containsKey(key)){
            value = properties.getProperty(key);
        }
        return value;
    }


}
