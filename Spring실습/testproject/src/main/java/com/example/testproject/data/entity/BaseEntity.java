package com.example.testproject.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
//객체의 입장에서 공통 속성을 공유할 때 사용(중복 제거)
//부모 클래스에 선언 후 ,상속받아서 사용하고 싶을때 사용
//이 클래스는 엔티티가 아니라서 테이블과 매핑되지 않고 단순히 자식에게 정보만 제공
//JPA 에서 @Entity 는 @Entity 나 @MappedSupeclass 만 상속가능.
@EntityListeners(AuditingEntityListener.class)
//JPA 의 Auditing 은 각 엔티티별로 누가 언제 접근했는지 기록해 감시 하는것임.
//기능 사용을 위해서 @EnableJpaAuditing 사용.
//EntityListener 은 Entity에서 이벤트 발생 시마다 특정 로직 실행시키는(전.후 콜백) 어노테이션
//AuditingEntityListener 이 콜백 리스너로 지정됨
//AuditingEntityListener 은 대상 개체에 업데이트 이벤트가 일어나는것을 auditing
public class BaseEntity {


    //테이블에서 공통적으로 사용될 컬럼인 생성된 시간 및 수정된 시간을 필드로 가진 클래스.
    @CreatedDate
    @Column(updatable = false)
    //CreatedDate 로 생성된 시간 정보 저장
    private LocalDateTime createdAt;

  /*
  @CreatedBy
  @Column(updatable = false)
  private String createdBy;
  */

    @LastModifiedDate
    //수정된 시간 정보 저장
    private LocalDateTime updatedAt;

  /*
  @LastModifiedBy
  private String updatedBy;
  */


}