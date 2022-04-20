package com.conmu.controller;

import com.conmu.utils.BaseClass;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//lombok创建，日志方式三
@Slf4j
//Rest模式
@RestController
@RequestMapping("/books")

public class BookController{
    //创建记录日志的对象,方法一
//    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @GetMapping
    public String getById() {
        System.out.println("springboot is running...");
        log.info("info...");
        log.warn("warn...");
        log.debug("debug...");
        log.error("error...");

        return "springboot is running...";
    }
}
