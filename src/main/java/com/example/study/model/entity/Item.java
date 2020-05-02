package com.example.study.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"item","partner"})
//엔티티 리스너로 AuditingEntityListener 사용하겠다 명
@EntityListeners(AuditingEntityListener.class)
@Builder // 빌더패턴 척용 , 파라미터 추가되었거나 특정 파라미터만 넘기고 싶을때 사용
@Accessors(chain = true)// 체이닝 된 객체로 수정 및 생성 가능
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private String name;
    private String title;
    private Integer price;

    private String content;
    private String brandName;
    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;

    @CreatedDate
    private LocalDateTime createdAt;

    //@CreateBy , @LastModifiedBy 모두 LoginUserAuditorAware에서 리턴해주는 AdminServer 받아온다
    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

    //private Long partnerId;
    //Item N : 1 Partner

    @ManyToOne
    private Partner partner;

    // Item  1 : N OrderDetail
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderDetail> orderDetailList;

    // 1: N
    //fetchType
    // LAZY = 지연로딩 , EAGER = 즉시로딩딩
    // LAZY = select * from item where id = ? 메소드를 따로 호출하지 않는 이상 연관관계 테이블에 대해서는 셀렉트 하지 않는다
    // EAGER  = select  // 즉시 모든 것을 다 로딩하겠다, 연관관계가 있는 테이블에 따라서 모두 조인해서 셀렉트 한다
    // item_id = order_detail.item_id
    // user_id = order_detail.user_id
    // where item.id = ?
    // OneToOne 등 한건만 있는 것에는 EAGER 추천
    // order detail에 tem과 매핑
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
//    private List<OrderDetail> orderDetailList;
}
