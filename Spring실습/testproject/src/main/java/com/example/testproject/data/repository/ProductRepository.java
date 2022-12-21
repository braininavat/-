package com.example.testproject.data.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.testproject.data.entity.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    
    
    //JPQL 로 쿼리 작성, SQL 과 문법 비슷
    //차이점은 JPQL은 엔티티 객체를 대상으로 쿼리 수행

    @Query("SELECT p FROM ProductEntity p WHERE p.productPrice > 2000")
    List<ProductEntity> findByPriceBasis();

    @Query(value = "SELECT * FROM ProductEntity p WHERE p.productPrice > 2000", nativeQuery = true)
    List<ProductEntity> findByPriceBasisNativeQuery();

    @Query("SELECT p FROM ProductEntity p WHERE p.productPrice > ?1")
    List<ProductEntity> findByPriceWithParameter(Integer price);

    @Query("SELECT p FROM ProductEntity p WHERE p.productPrice > :price")
    List<ProductEntity> findByPriceWithParameterNaming(Integer price);

    @Query("SELECT p FROM ProductEntity p WHERE p.productPrice > :pri")
    List<ProductEntity> findByPriceWithParameterNaming2(@Param("pri") Integer price);

    @Query(value = "SELECT * FROM ProductEntity WHERE productPrice > :price",
            countQuery = "SELECT count(*) FROM ProductEntity WHERE productPrice > ?1",
            nativeQuery = true)
    List<ProductEntity> findByPriceWithParameterPaging(Integer price, Pageable pageable);




}
