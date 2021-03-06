package com.example.study.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.apache.tomcat.jni.Local;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // 카멜케이스를 스네이크 케이스로 자동으로 연결해줌 db에 order_detail 이랑 자동 연결
//롬복을 쓰면 투스트링을 자동으로 만들어주는데 오더디테일과 유저가 상호참조 하므로 계속 투스트링 타면서 오버플로우 나므로
//@ToString(exclude = {"user","item"}) //를 해줘서 서로 상호참조를 풀어준다
@ToString(exclude = {"orderGroup","item"})
//엔티티 리스너로 AuditingEntityListener 사용하겠다 명
@EntityListeners(AuditingEntityListener.class)
@Builder // 빌더패턴 척용 , 파라미터 추가되었거나 특정 파라미터만 넘기고 싶을때 사용
@Accessors(chain = true)// 체이닝 된 객체로 수정 및 생성 가능
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private LocalDateTime arrivalDate;
    private Integer quantity;
    private BigDecimal totalPrice;
    @CreatedDate
    private LocalDateTime createdAt;

    //@CreateBy , @LastModifiedBy 모두 LoginUserAuditorAware에서 리턴해주는 AdminServer 받아온다
    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

    // OrderDetail N : 1 Item
    @ManyToOne
    private Item item;

    //OrderDetail N : 1 OrderGroup
    @ManyToOne
    private OrderGroup orderGroup;
    //private LocalDateTime orderAt;

//    //N : 1
//    @ManyToOne
//    // 객체 이름을 적어줘야 한다 하이버네이트가 알아서 id 찾아감
//    private User user;
//
//
//    //N : 1
//    @ManyToOne
//    private Item item;

}
