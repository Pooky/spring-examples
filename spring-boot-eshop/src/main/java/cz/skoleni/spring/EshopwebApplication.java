package cz.skoleni.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.*;
import org.springframework.scheduling.annotation.*;

@EnableCaching
@EnableAsync
@SpringBootApplication
public class EshopwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshopwebApplication.class, args);
	}
}
