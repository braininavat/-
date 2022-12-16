package com.example.testproject.test;

import org.junit.jupiter.api.*;

public class TestLifeCycle {


    @BeforeAll
    static void beforeAll() {
        System.out.println("## BeforeAll Annotation 호출 ##");
        System.out.println();
    }

    @AfterAll
    static void afterAll() {
        System.out.println("## afterAll Annotation 호출 ##");
        System.out.println();
    }

    @BeforeEach
    //Each는 각 테스트 수행시마다 실행된다
    void beforeEach() {
        System.out.println("## beforeEach Annotation 호출 ##");
        System.out.println();
    }

    @AfterEach
    void afterEach() {
        System.out.println("## afterEach Annotation 호출 ##");
        System.out.println();
    }

    @Test
    void test1() {
        System.out.println("## test1 시작 ##");
        System.out.println();
    }

    @Test
    @DisplayName("Test Case 2!!!")
    void test2() {
        System.out.println("## test2 시작 ##");
        System.out.println();
    }

    @Test
    @Disabled
    //disabled 어노테이션을 붙이면 테스트를 실행하지 않음
    void test3() {
        System.out.println("## test3 시작 ##");
        System.out.println();
    }



}
