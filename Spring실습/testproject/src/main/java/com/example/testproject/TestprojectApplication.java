package com.example.testproject;

import com.example.testproject.config.ProfileManager;
import com.example.testproject.config.env.EnvConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//Repository를 인식 못하면 @EnableJpaRepositories 추가해줌
@EnableJpaAuditing
//JPA Auditing 관련 빈 사용시 필요
public class TestprojectApplication {

	private final Logger LOGGER = LoggerFactory.getLogger(TestprojectApplication.class);

	@Autowired
	public TestprojectApplication(EnvConfiguration envConfiguration, ProfileManager profileManager){
		LOGGER.info(envConfiguration.getMessage());
		profileManager.printActiveProfiles();
	}

	public static void main(String[] args) {

		SpringApplication.run(TestprojectApplication.class, args);
	}

}
