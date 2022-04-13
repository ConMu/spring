package com.example.controller;

import com.example.MyDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Rest模式
@RestController
@RequestMapping("/books")

public class BookController {
    //读取yaml数据中单一数据
    @Value("${country}")
    private String country1;

    @Value("${user1.name}")
    private String name1;

    @Value("${baseDir}")
    private String baseDir1;

    @Value("${tmpDir}")
    private String tmpDir1;

    //使用自动装配将所有数据封装到一个对象的Environment中
    @Autowired
    private Environment environment;

    @Autowired
    private MyDataSource myDataSource;

    @GetMapping
    public String getById() {
        System.out.println("springboot is running...");
        System.out.println("country1===>" + country1);
        System.out.println("name===>" + name1);
        System.out.println(baseDir1);
        System.out.println(tmpDir1);
        System.out.println(environment.getProperty("baseDir"));
        System.out.println(environment.getProperty("enterprise.name"));
        System.out.println(myDataSource);
        return "springboot is running...";
    }
}
