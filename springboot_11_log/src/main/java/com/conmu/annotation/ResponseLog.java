package com.conmu.annotation;

import java.lang.annotation.*;

/**
 * 记录请求的响应内容
 *
 * @author wangbin16
 * @date 2022/8/9 15:45
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseLog {
    boolean needRecord();    // true表示需要记录响应内容；false表示不需要记录响应内容
}