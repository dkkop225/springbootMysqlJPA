package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    private String content;

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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderDetail> orderDetailList;
}
