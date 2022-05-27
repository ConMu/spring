package com.conmu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy  // 该注解启用支持处理标有AspectJ的 @Aspect 批注的组件。它与@Configuration批注一起使用
public class Springboot18AopApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot18AopApplication.class, args);
	}

}
