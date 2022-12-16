package com.example.testproject.controller;

import com.example.testproject.common.Constants;
import com.example.testproject.data.dto.ProductDto;
import com.example.testproject.service.ProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.testproject.common.exception.CustomException;
import com.example.testproject.common.Constants.ExceptionClass;

@RestController
@RequestMapping("/api/v1/product-api")
public class ProductController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping(value = "/product/{productID}")
    public ProductDto getProduct(@PathVariable String productID){

        long startTime = System.currentTimeMillis();
        LOGGER.info("[getProduct] perform {} of API.", "getProduct");

        ProductDto productDto = productService.getProduct(productID);

        return productDto;
    }
    @PostMapping(value = "/product")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {

        LOGGER.info("[createProduct] perform {} of API.", "createProduct");

        // Validation Code Example
        if (productDto.getProductID().equals("") || productDto.getProductID().isEmpty()) {
            LOGGER.error("[createProduct] failed Response :: productId is Empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(productDto);
        }

        String productID = productDto.getProductID();
        String productName = productDto.getProductName();
        int productPrice = productDto.getProductPrice();
        int productStock = productDto.getProductStock();

        ProductDto response = productService
                .saveProduct(productID, productName, productPrice, productStock);

        LOGGER.info(
                "[createProduct] Response >> productId : {}, productName : {}, productPrice : {}, productStock : {}",
                response.getProductID(), response.getProductName(), response.getProductPrice(),
                response.getProductStock());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(value = "/product/exception")
    public void exceptionTest() throws CustomException {
        throw new CustomException(ExceptionClass.PRODUCT, HttpStatus.BAD_GATEWAY, "접근이 금지되었습니다.");
    }


}