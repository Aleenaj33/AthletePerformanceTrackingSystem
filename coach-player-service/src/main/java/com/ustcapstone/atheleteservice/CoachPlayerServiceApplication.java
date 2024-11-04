package com.ustcapstone.atheleteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CoachPlayerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoachPlayerServiceApplication.class, args);
	}

}
