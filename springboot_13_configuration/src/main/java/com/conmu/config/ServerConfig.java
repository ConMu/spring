package com.conmu.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Component
@Data
//用于第三方bean的属性配置
@ConfigurationProperties(prefix = "servers")
//开启对当前bean的属性注入校验
@Validated
public class ServerConfig {
    private String ipAddress;
    @Max(value = 8888,message = "最大值不能超过8888")
//    @Min(value = 202,message = "sss")
    private int port;
    private long timeout;
    //Java定义时间的类
    @DurationUnit(ChronoUnit.HOURS)
    private Duration serverTimeout;
    //配置存储空间的
//    @DataSizeUnit(DataUnit.MEGABYTES)
    private DataSize dataSize;
}
