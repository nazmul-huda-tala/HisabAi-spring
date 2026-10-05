package com.example.HisabAIEntity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HisabAiEntityApplication {

	public static void main(String[] args) {
		SpringApplication.run(HisabAiEntityApplication.class, args);
	}

}
