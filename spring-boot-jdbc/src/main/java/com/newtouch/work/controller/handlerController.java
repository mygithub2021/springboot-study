package com.newtouch.work.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class handlerController {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @ResponseBody
    @GetMapping("/query")
    public Map<String,Object> map(){
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * FROM users");
        return list.get(0);
    }

}
