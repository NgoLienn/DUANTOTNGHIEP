package com.poly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync(proxyTargetClass = true)
public class FastFoodStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastFoodStoreApplication.class, args);
	}

}
