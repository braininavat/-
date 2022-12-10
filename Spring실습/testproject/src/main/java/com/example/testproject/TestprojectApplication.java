package com.example.testproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.testproject.data.repository")
//Repository를 인식 못하면 @EnableJpaRepositories 추가해줌
public class TestprojectApplication {

	public static void main(String[] args) {

		SpringApplication.run(TestprojectApplication.class, args);
	}

}
