package com.newtouch.work.utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.wiring.BeanWiringInfo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;

/**
 * @program: blog-back
 * @description: http接口请求解析util
 * @author: YePengFei
 * @create: 2020-12-08 13:40
 **/
public class HttpRequestUtil {
        private HttpRequestUtil(){}
        // 直接调用接口得到数据并解析（get）
        private void httpGetRequest(String urlstr) {
            try {
                String str = urlstr.substring(urlstr.lastIndexOf("/") + 1);
                URL url = new URL(urlstr);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //设置请求格式
                conn.setRequestProperty("Content-type", "application/json");
                //设置编码语言
                conn.setRequestProperty("Accept-Charset", "UTF-8");
                //请求方式,默认是GET
//                conn.setRequestMethod("POST");
                //连接主机的超时时间（单位：毫秒）
                //conn.setConnectTimeout(30000);
                conn.setUseCaches(false);
                //设置是否输出,post请求参数要放在 http正文内,需设为true,默认是false
                conn.setDoOutput(false);
                //设置是否读入,默认是true
                conn.setDoInput(true);
                conn.connect();
//                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
//                String messages = "";
//                out.writeBytes(messages);
//                out.flush();
//                out.close();
                //读取响应
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String lines;
                StringBuffer resultStr = new StringBuffer("");
                if ((lines = reader.readLine()) != null) {
                    lines = new String(lines.getBytes(), "utf-8");
                    resultStr.append(lines);
                }
                JSONObject object = JSON.parseObject(String.valueOf(resultStr));
                JSONArray array = (JSONArray) object.get("result");
//                JSONObject object1 = (JSONObject) object.get("result");
                java.util.List<Object> list = new ArrayList<>();
//                JSONArray array = (JSONArray) object1.get("PRODUVCT");
                for (int i = 0; i < array.size(); i++) {
                    System.out.println(array.get(i));
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
     // post 携带参数访问链接，得到数据并解析
    private void httpPostRequest(String urlstr,Object param) {
        try {
            String str = urlstr.substring(urlstr.lastIndexOf("/") + 1);
            URL url = new URL(urlstr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置请求格式
            conn.setRequestProperty("Content-type", "application/json");
            //设置编码语言
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            //请求方式,默认是GET
            conn.setRequestMethod("POST");
            //连接主机的超时时间（单位：毫秒）
            //conn.setConnectTimeout(30000);
            conn.setUseCaches(false);
            //设置是否输出,post请求参数要放在 http正文内,需设为true,默认是false
            conn.setDoOutput(true);
            //设置是否读入,默认是true
            conn.setDoInput(true);
            conn.connect();
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            Object o = JSONObject.toJSON(param);
            Object jsonObject = JSONObject.parse(o.toString());
            out.write(jsonObject.toString().getBytes());
            out.flush();
            out.close();
            //读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String lines;
            StringBuffer resultStr = new StringBuffer("");
            if ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                resultStr.append(lines);
            }
            JSONObject object = JSON.parseObject(String.valueOf(resultStr));

            JSONObject object1 = (JSONObject) object.get("result");
            java.util.List<Object> list = new ArrayList<>();
            JSONArray array = (JSONArray) object1.get("recommendArticles");
            for (int i = 0; i < array.size(); i++) {
                System.out.println(array.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    // 请求头
    public static HttpHeaders getHeader(String userName,String passWord)throws IOException{
            // 请求头信息
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Accept","text/xml");
        String token = userName.concat(passWord);
        // 登录权限（用户名和密码BASE64加密）
        String Authorization = new String(Base64.getEncoder().encode(token.getBytes(StandardCharsets.UTF_8)));
        headers.add("Authorization",Authorization);
        return headers;
    }

    public static void main(String[] args) {
        // get 请求
//      new HttpRequestUtil().httpGetRequest("http://8.131.95.61:8080/blog-back/app/queryRecommendArticles");
        // post 请求,参数为对象。如果参数为字符串可以这样写：String message = "channel="+channel+";userCode="+userCode;

        new HttpRequestUtil().httpPostRequest("http://8.131.95.61:8080/blog-back/app/findRecommendArticlePage","");

    }
}
