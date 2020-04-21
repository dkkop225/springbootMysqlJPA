package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
//컨트롤러로 활용하겠다는 지시자 어노테이션
@RequestMapping("/api") //localhost:8080/api
//이곳으로 들어온 api 주소를 매핑하기 위하여
public class GetController {

    @RequestMapping(method= RequestMethod.GET, path="/getMethod") //localhost:8080/api/getMethod
    public String getRequest(){

        return "Hi GetMethod";

    }

    //getMapping은 RequestMapping과 다르게 메소드를 지정하지 않아도 되고 주소만 설정해 주면 된다
    @GetMapping("/getParameter") //localhost:8080/api/getparameter?id=1234&password=abcd
    //password 란 이름으로 들어올 것이라고 명시해줌
    public String getParameter(@RequestParam String id, @RequestParam(name="password") String pwd){
        String password = "bbb";

        System.out.println("id:"+id);
        System.out.println("password:"+password);

        return id+pwd;
    }

    //localhost:8080/api/multiParameter?account=abcd&email=aaa@gmail.com&page=14
    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam){
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

    return  searchParam;
    }

}
