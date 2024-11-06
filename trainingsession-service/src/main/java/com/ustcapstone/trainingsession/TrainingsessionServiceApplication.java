package com.ustcapstone.trainingsession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableFeignClients
public class TrainingsessionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingsessionServiceApplication.class, args);
	}

}
