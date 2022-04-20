package com.conmu.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseClass {

    private final Class clazz;
    //日志对象方法二,采用方法继承，log以静态变量存储
    public static Logger log;
    public BaseClass() {
        clazz = this.getClass();
        log = LoggerFactory.getLogger(clazz);
    }
}
