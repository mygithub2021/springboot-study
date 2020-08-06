package com.newtouch.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class HelloController {
   /* 未传参*/
    @RequestMapping("/hello")
    public String hello(){
       return "Hello World!!!";
    }
    /*传参*/
    @RequestMapping(value = "/helloName",method = POST)
    public  String helloName(String name){
        return "-----------------Hello World!!" + name;
    }


}
