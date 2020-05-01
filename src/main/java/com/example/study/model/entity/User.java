package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity //== table
//@Table(name="user") 테이블명과 같으므로 선언 안해도됨
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//어떤식으로 관리할껀지 전략 설정
        private Long id;
        //@Column(name= "account") 컬럼명과 같으면 안해줘도 됨
        private String account;
        private String email;
        private String phoneNumber;
        private LocalDateTime createdAt;
        private String createdBy;
        private LocalDateTime updatedAt;
        private String updatedBy;

        //1:N
    // 매칭시킬 변수 이름과 동일해야 한다 OrderDetail에 user와 매칭 시키겠다는 뜻
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
        private List<OrderDetail> orderDetailList;

}
