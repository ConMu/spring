package com.conmu;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//@SpringBootApplication
//@EnableAspectJAutoProxy  // 该注解启用支持处理标有AspectJ的 @Aspect 批注的组件。它与@Configuration批注一起使用
//public class Springboot18AopApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(Springboot18AopApplication.class, args);
//	}
//
//}

@ComponentScan("com.conmu.service")
@Configuration
@EnableAspectJAutoProxy
public class Springboot18AopApplication {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Springboot18AopApplication.class);
		HelloService helloService = context.getBean(HelloService.class);
		helloService.sayHi("hi-1");
		System.out.println("\n");
		helloService.anotherSayHi("hi-2");
	}
}