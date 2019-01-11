package com.imap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:integration.xml"})
@ComponentScan(basePackages = {"com.imap.controller", "com.imap.endpoint"})
public class SpringIntegrationApplication {

	public static void main(String[] args) {	
		SpringApplication.run(SpringIntegrationApplication.class, args);
	}
}
