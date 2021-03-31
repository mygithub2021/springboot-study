package com.newtouch.work.controller;

import com.newtouch.work.bean.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class HelloController {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @ResponseBody
    @GetMapping("/test")
    public List<Employee> test(){
        List<Employee> employees = jdbcTemplate.query("SELECT * FROM EMPLOYEE",
                new BeanPropertyRowMapper<>(Employee.class));
        return employees;
    }
}
