package com.example.testproject.data.repository;

import com.example.testproject.data.entity.ProductEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.util.List;

@SpringBootTest
public class ProductRepositoryTest {


    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void GenerateDAta() {
        int count = 1;
        productRepository.save(getProduct(Integer.toString(count), count++, 2000, 3000));
        productRepository.save(getProduct(Integer.toString(count), count++, 2000, 9000));
        productRepository.save(getProduct(Integer.toString(--count), count += 2, 1500, 3300));
        productRepository.save(getProduct(Integer.toString(count), count++, 300, 1200));
        productRepository.save(getProduct(Integer.toString(count), count++, 2000, 98));
        productRepository.save(getProduct(Integer.toString(count), count++, 5000, 58));
        productRepository.save(getProduct(Integer.toString(count), count++, 1000, 46));
        productRepository.save(getProduct(Integer.toString(count), count++, 1000, 10));
        productRepository.save(getProduct(Integer.toString(count), count++, 1700, 50));
        productRepository.save(getProduct(Integer.toString(count), count++, 8000, 100));
    }

    private ProductEntity getProduct(String id, int nameNumber, int price, int stock) {
        return new ProductEntity(id, "상품" + nameNumber, price, stock);
    }

    @Test
    void findTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("------Test Data------");
        for (ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("------Test Data------");

        List<ProductEntity> foundEntities = productRepository.findByProductName("상품4");

        for (ProductEntity productEntity : foundEntities) {
            System.out.println(productEntity.toString());
        }

        List<ProductEntity> queryEntities = productRepository.queryByProductName("상품4");

        for (ProductEntity productEntity : queryEntities) {
            System.out.println(productEntity.toString());
        }
    }

    @Test
    void existsTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("--------Test Data--------");
        for (ProductEntity product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("----------Test Data-------");

        System.out.println(productRepository.existsByProductName("상품4"));
        System.out.println(productRepository.existsByProductName("상품2"));
    }

    @Test
    @Transactional
        //Transactional 을 붙여야 동작. 쉽게 rollback 가능.
    void deleteTest() {
        System.out.println("before : " + productRepository.count());

        productRepository.deleteByProductID("1");
        productRepository.removeByProductID("9");

        System.out.println("After : " + productRepository.count());
    }

    @Test
    void topTest() {
        productRepository.save(getProduct("109", 123, 1500, 5000));
        productRepository.save(getProduct("101", 123, 2500, 5000));
        productRepository.save(getProduct("102", 123, 3500, 5000));
        productRepository.save(getProduct("103", 123, 4500, 5000));
        productRepository.save(getProduct("104", 123, 1000, 5000));
        productRepository.save(getProduct("105", 123, 2000, 5000));
        productRepository.save(getProduct("106", 123, 3000, 5000));
        productRepository.save(getProduct("107", 123, 4000, 5000));
        //상품123 에 대해서만 조회
        List<ProductEntity> foundEntities = productRepository.findFirst5ByProductName("상품123");
        for (ProductEntity product : foundEntities) {
            System.out.println(product.toString());
        }

        List<ProductEntity> foundEntities2 = productRepository.findTop3ByProductName("상품123");
        for (ProductEntity product : foundEntities2) {
            System.out.println(product.toString());
        }
    }

    @Test
    void isEqualsTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("--------Test Data--------");
        for (ProductEntity product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("---------Test Data----");

        System.out.println(productRepository.findByProductIDIs("1"));
        System.out.println(productRepository.findByProductIDEquals("1"));
    }

    @Test
    void NotTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("--------Test Data--------");
        for (ProductEntity product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("---------Test Data----");

        List<ProductEntity> foundEntity = productRepository.findByProductIDNot("1");
        for (ProductEntity product : foundEntity) {
            System.out.println(product.toString());
        }
        System.out.println(productRepository.findByProductIDEquals("1"));
    }


    //정렬, 페이징 테스트코드
    @Test
    void orderByTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<ProductEntity> foundProducts = productRepository.findByProductNameContainingOrderByProductStockAsc("상품");
        for (ProductEntity product : foundProducts) {
            System.out.println(product);
        }

        foundProducts = productRepository.findByProductNameContainingOrderByProductStockDesc("상품");
        for (ProductEntity product : foundProducts) {
            System.out.println(product);
        }

        foundProducts = productRepository.findByProductNameContainingOrderByProductPriceAscProductStockDesc(
                "상품");
        for (ProductEntity product : foundProducts) {
            System.out.println(product);
        }
    }

    //파라미터를 통해 정렬하는 코드를 테스트한다
    @Test
    void orderByWithParameterTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<ProductEntity> foundProducts = productRepository.findByProductNameContaining(
                "상품", Sort.by(Order.asc("productPrice")));
        for (ProductEntity product : foundProducts) {
            System.out.println(product);
        }

        foundProducts = productRepository.findByProductNameContaining("상품",
                Sort.by(Order.asc("productPrice"), Order.asc("productStock")));
        for (ProductEntity product : foundProducts) {
            System.out.println(product);
        }
    }

    @Test
    void pagingTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        //PageRequest.of (페이지 번호, 크기)
        //2개씩 묶은 페이지 중 0번째 페이지를 가져오겠다.(0부터시작)
        List<ProductEntity> foundProducts = productRepository.findByProductPriceGreaterThan(200,
                PageRequest.of(0, 2));
        for (ProductEntity product : foundProducts) {
            System.out.println(product);
        }

        foundProducts = productRepository.findByProductPriceGreaterThan(200, PageRequest.of(4, 2));
        for (ProductEntity product : foundProducts) {
            System.out.println(product);
        }
    }

    //@Query Test

    @Test
    public void queryTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<ProductEntity> foundProducts = productRepository.findByPriceBasis();
        for (ProductEntity product : foundProducts) {
            System.out.println(product);
        }
    }

    @Test
    public void nativeQueryTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<ProductEntity> foundProducts = productRepository.findByPriceBasisNativeQuery();
        for (ProductEntity product : foundProducts) {
            System.out.println(product);
        }
    }

    @Test
    public void parameterQueryTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<ProductEntity> foundProducts = productRepository.findByPriceWithParameter(2000);
        for (ProductEntity product : foundProducts) {
            System.out.println(product);
        }
    }

    @Test
    public void parameterNamingQueryTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<ProductEntity> foundProducts = productRepository.findByPriceWithParameterNaming(2000);
        for (ProductEntity product : foundProducts) {
            System.out.println(product);
        }
    }

    @Test
    public void parameterNamingQueryTest2() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<ProductEntity> foundProducts = productRepository.findByPriceWithParameterNaming2(2000);
        for (ProductEntity product : foundProducts) {
            System.out.println(product);
        }
    }

    @Test
    public void nativeQueryPagingTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<ProductEntity> foundProducts = productRepository.findByPriceWithParameterPaging(2000,
                PageRequest.of(2, 2));
        for (ProductEntity product : foundProducts) {
            System.out.println(product);
        }
    }

}

