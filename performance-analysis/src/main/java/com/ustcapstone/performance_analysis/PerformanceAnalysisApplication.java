package com.ustcapstone.performance_analysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PerformanceAnalysisApplication {

	public static void main(String[] args) {
		SpringApplication.run(PerformanceAnalysisApplication.class, args);
	}

}
