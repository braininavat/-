package com.example.testproject.data.dao.impl;

import com.example.testproject.data.dao.ProductDAO;
import com.example.testproject.data.entity.ProductEntity;
import com.example.testproject.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDAOImpl implements ProductDAO {

    ProductRepository productRepository;


    //Autowired - 객체를 주입받기 위한 어노테이션
    //Spring 은 싱글턴 기반, 사용할 때마다 만들어진 객체에 의존성을 주입받게 된다.
    @Autowired
    public ProductDAOImpl(ProductRepository productRepository){

        this.productRepository = productRepository;
    }

    @Override
    public ProductEntity saveProduct(ProductEntity productEntity) {
        productRepository.save(productEntity);
        return productEntity;
    }

    @Override
    public ProductEntity getProduct(String productId) {
        ProductEntity productEntity = productRepository.getById(productId);
        return productEntity;
    }
}
