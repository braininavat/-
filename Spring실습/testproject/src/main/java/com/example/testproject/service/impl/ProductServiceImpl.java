package com.example.testproject.service.impl;

import com.example.testproject.data.dto.ProductDto;
import com.example.testproject.data.entity.ProductEntity;
import com.example.testproject.data.handler.ProductDataHandler;
import com.example.testproject.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    ProductDataHandler productDataHandler;
    //Handler = 추가적인 처리가 필요할 때 옵션 사항.

    @Autowired
    ProductServiceImpl(ProductDataHandler productDataHandler){
        this.productDataHandler = productDataHandler;
    }

    @Override
    public ProductDto saveProduct(String productID, String productName, int productPrice, int productStock) {

       ProductEntity product = productDataHandler.saveProductEntity(productID,productName,productPrice,productStock);

        ProductDto productDto = new ProductDto(product.getProductID(),
                product.getProductName(),
                product.getProductPrice(),
                product.getProductStock());

        return productDto;
    }
    //Client->Controller->Service 의 계층간 이동은 DTO를 사용
    //Serivce->DB의 계층 이동은 Entity 사용
    //DTO를 Entity로 바꾸는것은 어느 계층에서 작업을 해줘야 할까?
    //이론적으로는 Serivce 에서 변한 해주는게 맞으나 case-by-case

    @Override
    public ProductDto getProduct(String productId) {
        LOGGER.info("[getProduct] productDataHandler 로 상품 정보 조회 요청");
        ProductEntity product = productDataHandler.getProductEntity(productId);

        LOGGER.info("[getProduct] Entity 객체를 DTO 객체로 변환 작업. productId : {}",
                product.getProductID());
        ProductDto productDto = new ProductDto(product.getProductID(),
                product.getProductName(), product.getProductPrice(),
                product.getProductStock());

        return productDto;
    }
}
