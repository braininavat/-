package com.example.testproject.controller;

import com.example.testproject.common.Constants;
import com.example.testproject.data.dto.ProductDto;
import com.example.testproject.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.testproject.common.exception.CustomException;
import com.example.testproject.common.Constants.ExceptionClass;

@RestController
@RequestMapping("/api/v1/product-api")
public class ProductController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productservice;

    @Autowired
    public ProductController(ProductService productService){
        this.productservice = productService;
    }

    @GetMapping(value = "/product/{productID}")
    public ProductDto getProduct(@PathVariable String productID){

        long startTime = System.currentTimeMillis();
        LOGGER.info("[getProduct] perform {} of API.", "getProduct");

        ProductDto productDto = productservice.getProduct(productID);

        return productDto;
    }

    @PostMapping(value = "/product/exception")
    public void exceptionTest() throws CustomException {
        throw new CustomException(ExceptionClass.PRODUCT, HttpStatus.BAD_GATEWAY, "접근이 금지되었습니다.");
    }


}