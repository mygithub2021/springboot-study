package com.newtouch.work.controller;

import com.newtouch.work.common.JsonResult;
import com.newtouch.work.config.HBaseConfig;
import com.newtouch.work.utils.HbaseUtil;
import com.newtouch.work.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.pattern.PathPattern;

import java.io.IOException;

/**
 * @program: HbaseController
 * @description: Hbase
 * @author: yepengfei
 * @create: 2021/3/19、9:43
 * @Version 1.0
 **/
@RestController
@RequestMapping("hbase")
public class HbaseController {

    @Autowired
    private HBaseConfig config;

    @GetMapping("/getHbaseData")
    public ResponseEntity<JsonResult> getHbaseData() throws IOException {
        // hbase 表结构路径
        String sql = "/rs:mid_ipe/*?startrow=";
        new  HbaseUtil().getBody(sql,config);
        return ResponseEntity.ok(ResultUtil.success(""));
    }

}
