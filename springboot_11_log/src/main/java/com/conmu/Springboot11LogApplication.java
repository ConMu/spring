package com.conmu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class Springboot11LogApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot11LogApplication.class, args);
	}

}
