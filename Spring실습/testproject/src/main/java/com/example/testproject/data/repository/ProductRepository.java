package com.example.testproject.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.testproject.data.entity.ProductEntity;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends JpaRepository<ProductEntity,String>
{
    //JpaRepository<엔티티,PK 타입값>
    //JPA Repository 상속시 @Repository 붙이지 않아도 됨
}
