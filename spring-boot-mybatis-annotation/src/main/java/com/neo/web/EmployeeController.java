package com.neo.web;

import com.neo.mapper.db2.EmployeeMapper;
import com.neo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/db2")
public class EmployeeController {

//    @Autowired
//    JdbcTemplate jdbcTemplate;

    @Autowired
    EmployeeMapper employeeMapper;

//    @ResponseBody
//    @GetMapping("/test")
//    public List<Employee> test(){
//        List<Employee> employees = jdbcTemplate.query("SELECT * FROM EMPLOYEE",
//                new BeanPropertyRowMapper<>(Employee.class));
//        System.out.println(employees);
//        return employees;
//    }

    @ResponseBody
    @GetMapping("/getAll")
    public List<Employee> getAll(){
        List<Employee> employees = employeeMapper.getAll();
        System.out.println(" getAll "+employees);
        return employees;
    }
}
