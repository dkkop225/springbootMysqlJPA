package com.example.study.model.entity;

import com.example.study.model.enumclass.OrderType;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.criterion.Order;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString(exclude = {"user","orderDetailList"})
//엔티티 리스너로 AuditingEntityListener 사용하겠다 명
@EntityListeners(AuditingEntityListener.class)
@Builder // 빌더패턴 척용 , 파라미터 추가되었거나 특정 파라미터만 넘기고 싶을때 사용
@Accessors(chain = true)// 체이닝 된 객체로 수정 및 생성 가능
public class OrderGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;

    @Enumerated(EnumType.STRING)
    private OrderType orderType; // 주문형태 - 일괄 / 개별

    private String revAddress;
    private String revName;
    private String paymentType;  // 카드 / 현금
    private BigDecimal totalPrice;
    private Integer totalQuantity;
    private LocalDateTime orderAt;
    private LocalDateTime arrivalDate;
    @CreatedDate
    private LocalDateTime createdAt;

    //@CreateBy , @LastModifiedBy 모두 LoginUserAuditorAware에서 리턴해주는 AdminServer 받아온다
    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

    //OrderGroup N : 1 User
    @ManyToOne
    private User user;

    //OrderGroup 1 : N OrderDetail
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderGroup")
    private List<OrderDetail> orderDetailList;
}
