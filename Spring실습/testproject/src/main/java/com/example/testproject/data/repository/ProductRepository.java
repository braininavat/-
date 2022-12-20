package com.example.testproject.data.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.testproject.data.entity.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity,String>
{
    //JpaRepository<엔티티,PK 타입값>
    //JPA Repository 상속시 @Repository 붙이지 않아도 됨

    //쿼리 메소드 : 시그니처만 만들면 자동 생성됨
    List<ProductEntity> findByProductName(String name);
    List<ProductEntity> queryByProductName(String name);
    //메소드 이름에 ProductEntity는 생략 가능하다(findProductEntityByProductName)


    boolean existsByProductName(String name);

    long countByProductName(String name);

    void deleteByProductName(String name);
    long removeByProductName(String name);

    void deleteByProductID(String id);
    void removeByProductID(String id);

    List<ProductEntity> findFirst5ByProductName(String name);

    List<ProductEntity> findTop3ByProductName(String name);

    ProductEntity findByProductIDIs(String id);
    ProductEntity findByProductIDEquals(String id);

    List<ProductEntity> findByProductIDNot(String id);
    List<ProductEntity> findByProductIDIsNot(String id);

    List<ProductEntity> findByProductStockIsNull();
    List<ProductEntity> findByProductStockIsNotNull();

    List<ProductEntity> findTopByProductIDAndProductName(String id, String name);

    List<ProductEntity> findByProductPriceGreaterThan(Integer price);

    List<ProductEntity> findByProductNameContaining(String name);

    //정렬 및 페이징

    List<ProductEntity> findByProductNameContainingOrderByProductStockAsc(String name);
    List<ProductEntity> findByProductNameContainingOrderByProductStockDesc(String name);

    List<ProductEntity> findByProductNameContainingOrderByProductPriceAscProductStockDesc(String name);

    List<ProductEntity> findByProductNameContaining(String name, Sort sort);


    //페이징
    List<ProductEntity> findByProductPriceGreaterThan(Integer price, Pageable pageable);

}
