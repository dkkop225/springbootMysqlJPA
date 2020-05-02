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
@Entity //== table
//@Table(name="user") 테이블명과 같으므로 선언 안해도됨
@ToString(exclude = {"orderGroup"})
//엔티티 리스너로 AuditingEntityListener 사용하겠다 명
@EntityListeners(AuditingEntityListener.class)
@Builder // 빌더패턴 척용 , 파라미터 추가되었거나 특정 파라미터만 넘기고 싶을때 사용
@Accessors(chain = true)// 체이닝 된 객체로 수정 및 생성 가능
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//어떤식으로 관리할껀지 전략 설정
        private Long id;
        //@Column(name= "account") 컬럼명과 같으면 안해줘도 됨
        private String account;
        private String password;
        private String status;
        private String email;
        private String phoneNumber;
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

        // User 1 : N OrderGroup
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
        private List<OrderGroup> orderGroupList;

        //1:N
    // 매칭시킬 변수 이름과 동일해야 한다 OrderDetail에 user와 매칭 시키겠다는 뜻
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//        private List<OrderDetail> orderDetailList;

}
