package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
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
        private LocalDateTime createAt;
        private String createBy;
        private LocalDateTime updateAt;
        private String updateBy;


}
