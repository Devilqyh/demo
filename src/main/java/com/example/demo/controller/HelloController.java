package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 默认返回 json 格式的数据
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        Book book =new Book();
//        book.
        return "Hello Spring Boot ";
    }
}
