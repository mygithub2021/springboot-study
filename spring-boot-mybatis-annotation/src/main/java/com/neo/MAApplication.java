package com.neo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.neo.mapper")
@ServletComponentScan
public class MAApplication {

	public static void main(String[] args) {
		SpringApplication.run(MAApplication.class, args);
	}
}
