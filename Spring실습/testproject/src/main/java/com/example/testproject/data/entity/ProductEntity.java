package com.example.testproject.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity // Entity 객체는 DB의 테이블과 매칭됨
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product")//테이블 자동 생성
public class ProductEntity {

    @Id 
     // PK 설정하는 어노테이션, 반드시 jakarta.persistence.id 를 import 할것
     // springframework.data.annotation 쓰면 에러남
    String productID;

    String productName;

    Integer productPrice;

    Integer productStock;

}
