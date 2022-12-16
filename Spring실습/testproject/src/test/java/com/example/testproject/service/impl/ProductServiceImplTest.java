package com.example.testproject.service.impl;

import com.example.testproject.data.dto.ProductDto;
import com.example.testproject.data.entity.ProductEntity;
import com.example.testproject.data.handler.impl.ProductDataHandlerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.verify;

//@SpringBootTest(classes = {ProductDataHandlerImpl.class, ProductServiceImpl.class})
@ExtendWith(SpringExtension.class)
@Import({ProductDataHandlerImpl.class, ProductServiceImpl.class})
public class ProductServiceImplTest {

    @MockBean
    ProductDataHandlerImpl productDataHandler;

    //웹 MVC 테스트가 아니라서 주입을 받아줘야함
    @Autowired
    ProductServiceImpl productService;

    @Test
    public void getProductTest() {

        //given의 다른방식 구현
        //when 으로 호출할 때, thenReturn 을 하는가?
        Mockito.when(productDataHandler.getProductEntity("123"))
                .thenReturn(new ProductEntity("123", "pen", 2000, 3000));

        ProductDto productDto = productService.getProduct("123");

        Assertions.assertEquals(productDto.getProductID(), "123");
        Assertions.assertEquals(productDto.getProductName(), "pen");
        Assertions.assertEquals(productDto.getProductPrice(), 2000);
        Assertions.assertEquals(productDto.getProductStock(), 3000);

        verify(productDataHandler).getProductEntity("123");
    }

    @Test
    public void saveProductTest() {
        //given
        Mockito.when(productDataHandler.saveProductEntity("123", "pen", 2000, 3000))
                .thenReturn(new ProductEntity("123", "pen", 2000, 3000));

        ProductDto productDto = productService.saveProduct("123", "pen", 2000, 3000);

        Assertions.assertEquals(productDto.getProductID(), "123");
        Assertions.assertEquals(productDto.getProductName(), "pen");
        Assertions.assertEquals(productDto.getProductPrice(), 2000);
        Assertions.assertEquals(productDto.getProductStock(), 3000);

        verify(productDataHandler).saveProductEntity("123", "pen", 2000, 3000);
    }
}