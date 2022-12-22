package com.example.testproject.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JasyptConfigTest {

    @Test
    void encryptTest(){
        //보안을 위해서 실무에선 id,password 를 코드 어디에도 노출하지 않는다.
        String url = "jdbc:postgresql://localhost:5432/docker_db";
        String id = "testuser";
        String password = "1234";

        System.out.println(jasyptEncoding(url));
        System.out.println(jasyptEncoding(id));
        System.out.println(jasyptEncoding(password));
    }

    public String jasyptEncoding(String value){
        String key = "testproject";
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword(key);
        return pbeEnc.encrypt(value);
    }
}
