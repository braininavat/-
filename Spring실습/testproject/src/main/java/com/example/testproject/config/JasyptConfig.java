package com.example.testproject.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//Jasypt 는 암호화 관련 라이브러리로, 쓰레드-안전한 단방향 및 양방향 암호화 지원
@Configuration
public class JasyptConfig {

    @Bean(name = "jasyptStringEncryptor")
    public StringEncryptor stringEncryptor(){
        String password = "testproject";
        //실무에서 password 는 외부에서 받아 설정해줘야 한다.
        //@Value("${jasypt.encryptor.password}") 어노테이션을 사용하여
        //java -jar 로 어플리케이션 실행 시 전달(--jayspt.encryptor.password=testproject)
        //아니면 환경 변수로 설정하는 방법, IntelliJ에서 VM option 적용할수도 있다.
        //AWS EC2를 사용한다면, AWS secret manager 로 관리하는 것도 방법
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(password);
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");//반복 해싱 횟수
        config.setPoolSize("1");// 1개의 인스턴스 풀
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        //암호화 시도시마다 값 달라짐
        config.setStringOutputType("base64"); //인코딩방식
        encryptor.setConfig(config);

        return encryptor;
    }
}
