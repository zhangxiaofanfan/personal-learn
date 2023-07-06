package com.zhangxiaofanfan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringBootMainApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMainApp.class, args);
    }

    @GetMapping("/hello/world")
    public String helloWorld() {
        return "Hello World, Learn Spring!";
    }
}
