package com.example.testproject.controller;

import com.example.testproject.data.dto.ProductDto;
import com.example.testproject.service.impl.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(ProductController.class)
@AutoConfigureWebMvc
// MockMvc를 Builder 없이 주입받을 수 있게 해주는 어노테이션
// @EnableJpaRepositories 와 충돌 발생 : Data 를 직접 관리하겠다는 선언임.
// @WebMVcTest 가 JPA 를 끄지만 @EnableJPARepositories 는 살아있어서 계속 JPA 빈을 찾기 때문에 예외가 발생한다.
public class ProductControllerTest
{
    //Test 시 경로 맞춰줘야됨
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductServiceImpl productService;
    //ProductController 는 ProductService Bean 객체를 사용중
    //따라서 Mock 형태의 객체 생성해줌

    @Test
    @DisplayName("Product Data 가져오는 TEST")
    void getProductTest() throws Exception{

        //given은 Mock 객체가 해야하는 행위를 정의하는 메소드이다
        given(productService.getProduct("12345")).willReturn(
                new ProductDto("15211", "tape", 1000,500));


        String productID = "12345";

        //perform 메소드로 Rest api 테스트가능(여기선 get)
        //andExpect 메소드로 기대한 값이 나오는지 체크한다.
        mockMvc.perform(
                get("/api/v1/product-api/product/" + productID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productID").exists())
                .andExpect(jsonPath("$.productName").exists())
                .andExpect(jsonPath("$.productPrice").exists())
                .andExpect(jsonPath("$.productStock").exists())
                .andDo(print());
        //$. 으로 Json 의 키값이 존재하는지 확인가능
        
        //verify 메소드로 해당 객체의 메소드가 실행되었는지 체크한다.
        verify(productService).getProduct("12345");
    }

    @Test
    @DisplayName("Product DATA 생성 TEST")
    void createProductTest() throws Exception {
        //Mock 객체에서는 Return을 줄 수 없기 때문에 아래와 같이 가정 사항을 만들어줌
        given(productService.saveProduct("15211", "tape", 1000, 500)).willReturn(
                new ProductDto("15211", "tape", 1000, 500));

        ProductDto productDto = ProductDto.builder().productID("15211").productName("tape")
                .productPrice(1000).productStock(500).build();

        //Gson은 json 을 쉽게 다루기 위한 라이브러리
        Gson gson = new Gson();
        String content = gson.toJson(productDto);

        // 아래 코드로도 json 형태 변경 작업 가능
        String json = new ObjectMapper().writeValueAsString(productDto);

        //.content 로 어떤 바디값을 넘겨줄건지, contentType 으로 어떤 타입인지도 알려줘야함
        mockMvc.perform(
                        post("/api/v1/product-api/product")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productID").exists())
                .andExpect(jsonPath("$.productName").exists())
                .andExpect(jsonPath("$.productPrice").exists())
                .andExpect(jsonPath("$.productStock").exists())
                .andDo(print());

        verify(productService).saveProduct("15211", "tape", 1000, 500);
    }

}
