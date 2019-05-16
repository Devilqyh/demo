package com.example.demo.controller;

import com.example.demo.annotation.AspectMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 默认返回 json 格式的数据
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        Book book = new Book();
//        book.
        return "Hello Spring Boot ";
    }

    @RequestMapping("/find")
    @AspectMethod(value = "测试", description = "执行在AspectDemo1 之后", returnValue = "返回值是me")
    public String findMe() {
        return "findMe";
    }

    @RequestMapping("/test")
    @AspectMethod(value = "me", description = "测试", returnValue = "test")
    public String test() {
        return "test";
    }
}
