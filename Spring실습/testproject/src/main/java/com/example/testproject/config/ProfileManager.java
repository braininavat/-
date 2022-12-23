package com.example.testproject.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
//@Component 사용시 Bean Configuration 에 Bean 을 따로 등록하지 않아도 사용가능하다.
//직접 작성한 Class 를 Bean 으로 등록. @ComponentScan 에 의해 스캔될 때,
//패키지 내에서 @Component 어노테이션이 적용된 클래스를 식별하고, 그런 클래스들의
//빈을 생성해 ApplicationContext 에 등록한다.
//타입 기반의 자동 주입 어노테이션 @Conponent의 구체화가 @Controller, @Service, @Repository
public class ProfileManager {
    //단순히 environment 의 정보를 출력해주는 객체
    private final Logger LOGGER = LoggerFactory.getLogger(ProfileManager.class);
    private final Environment environment;

    @Autowired
    public ProfileManager(Environment environment){
        this.environment = environment;
    }

    public void printActiveProfiles(){
        LOGGER.info("[printActiveProfiles] active profiles size : {}",environment.getActiveProfiles().length);
        for (String profile: environment.getActiveProfiles()){
            LOGGER.info("[printActiveProfiles] profile : {}", profile);
        }
    }



}
