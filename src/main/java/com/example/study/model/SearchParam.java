package com.example.study.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
//모든 매개변수를 가진 생성자 생성(AllArgsConstructor)
public class SearchParam {

    private  String account;
    private String email;
    private int page;


}
