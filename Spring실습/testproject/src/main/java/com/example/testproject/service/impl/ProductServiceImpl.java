package com.example.testproject.service.impl;

import com.example.testproject.data.dto.ProductDto;
import com.example.testproject.data.entity.ProductEntity;
import com.example.testproject.data.handler.ProductDataHandler;
import com.example.testproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductServiceImpl implements ProductService {

    ProductDataHandler productDataHandler;
    //Handler = 추가적인 처리가 필요할 때 옵션 사항.

    @Autowired
    ProductServiceImpl(ProductDataHandler productDataHandler){
        this.productDataHandler = productDataHandler;
    }

    @Override
    public ProductDto saveProduct(String productId, String productName, int productPrice, int productStock) {
        ProductEntity productEntity = null;//productDatahandler.saveProductEntity()

        ProductDto productDto = new ProductDto(productEntity.getProductID(),
                productEntity.getProductName(),
                productEntity.getProductPrice(),
                productEntity.getProductStock());

        return productDto;
    }
    //Client->Controller->Service 의 계층간 이동은 DTO를 사용
    //Serivce->DB의 계층 이동은 Entity 사용
    //DTO를 Entity로 바꾸는것은 어느 계층에서 작업을 해줘야 할까?
    //이론적으로는 Serivce 에서 변한 해주는게 맞으나 case-by-case

    @Override
    public ProductDto getProduct(String productId) {
        return null;
    }
}
