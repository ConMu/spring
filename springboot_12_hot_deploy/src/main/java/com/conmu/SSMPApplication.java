package com.conmu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SSMPApplication {

	public static void main(String[] args) {
		// 级别更高的配置，使得热部署关闭
		System.setProperty("spring.devtools.restart.enabled","false");
		SpringApplication.run(SSMPApplication.class, args);
	}

}
