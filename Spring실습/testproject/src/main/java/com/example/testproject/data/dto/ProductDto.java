package com.example.testproject.data.dto;


import com.example.testproject.data.entity.ProductEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductDto {

    //데이터 검증은 여러 계층에 걸쳐서 이루어지게 되는데, 이 과정으로 인해 문제점이 발생할 수 있다.
    //코드의 중복, 검증 로직 불일치로 인한 오류 등
    //Spring Boot 의 Hibernate Validator 을 임포트하면 멤버에 어노테이션을 사용해서 제약조건 설정 가능
    //제약 조건에 대한 유효성 검사는 validator 를 이용해서 이루어진다.
    //제약 조건이 동작하기 위해서는 @Valid 어노테이션 필요
    @NotNull
    private String productID;

    @NotNull
    private String productName;

    @NotNull
    @Min(value = 500)
    @Max(value = 100000000)
    private int productPrice;

    @NotNull
    @Min(value = 0)
    @Max(value = 999)
    private int productStock;

    public ProductEntity toEntity(){
        return ProductEntity.builder()
                .productID(productID)
                .productName(productName)
                .productPrice(productPrice)
                .productStock(productStock)
                .build();
    }
}
