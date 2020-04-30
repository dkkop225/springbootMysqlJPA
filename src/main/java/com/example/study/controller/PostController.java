package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {

    //HTML form 태그나 ajax 비동기 검색 등 검색 파라미터 많을 때 사용
    // json, xml , multipart-form / text-pain 등을 받을수 있음
    //기본적으로 json default
    // 다른 형태로 받을 땐 produces에 다른 형태 지정해주면됨
    //@PostMapping(value = "/postMethod",produces={"application-json")
    @PostMapping(value = "/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam){
        return searchParam;
    }

}
