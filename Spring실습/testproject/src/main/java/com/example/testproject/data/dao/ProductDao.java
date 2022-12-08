package com.example.testproject.data.dao;

import com.example.testproject.data.entity.ProductEntity;

public interface ProductDao {
    ProductEntity saveProduct(ProductEntity productEntity);

    ProductEntity getProduct(String ProductId);

}
