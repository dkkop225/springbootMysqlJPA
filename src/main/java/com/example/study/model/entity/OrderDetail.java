package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // 카멜케이스를 스네이크 케이스로 자동으로 연결해줌 db에 order_detail 이랑 자동 연결
//롬복을 쓰면 투스트링을 자동으로 만들어주는데 오더디테일과 유저가 상호참조 하므로 계속 투스트링 타면서 오버플로우 나므로
@ToString(exclude = {"user","item"}) //를 해줘서 서로 상호참조를 풀어준다
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderAt;

    //N : 1
    @ManyToOne
    // 객체 이름을 적어줘야 한다 하이버네이트가 알아서 id 찾아감
    private User user;


    //N : 1
    @ManyToOne
    private Item item;

}
