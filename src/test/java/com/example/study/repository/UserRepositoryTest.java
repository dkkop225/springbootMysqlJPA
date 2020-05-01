package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import org.junit.Assert.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {


    // 스프링의 가장 큰 장점이자 디자인패턴, DI 직접 객체를 만들지 않고 스프링이 객체를 관리하고 의존성을 주입하겠다
    // di의 기본핵심은 싱글턴 :
    @Autowired
    private UserRepository userRepository;

    // 테스트이므로 반드시 테스트 어노테이션 붙여야 한
    @Test
    public void create(){
        User user = new User();

        user.setAccount("TestUser03");
        user.setEmail("TestUser03@gmail.com");
        user.setPhoneNumber("010-1111-3333");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser03");

        // 데이터를 저장하고 리턴해줌
        User newUser = userRepository.save(user);

        System.out.println("newUser:"+newUser);
        //(id=1, account=TestUser01, email=TestUser01@gmail.com, phoneNumber=010-1111-1111, createdAt=2020-05-01T21:16:50.130, createdBy=admin, updatedAt=null, updatedBy=null)
        // User 에서 @Data 어노테이션에 toString 내장되어 있어서 깔끔하게 나어는 것이다
        //User(id=2, account=TestUser012, email=TestUser02@gmail.com, phoneNumber=010-1111-1111, createdAt=2020-05-01T21:19:24.625, createdBy=TestUser02, updatedAt=null, updatedBy=null)
    }

    @Test
    @Transactional
    public void read(){
        //어떤 아이디를 가진 것을 가져오겟다
        Optional<User> user = userRepository.findByAccount("TestUser03");

        //ifPresent =>  값이 있으면
        user.ifPresent(selectUser -> {
//            System.out.println("User:"+selectUser);
//            System.out.println("email:"+selectUser.getEmail());
            selectUser.getOrderDetailList().stream().forEach(detail->{
                Item item = detail.getItem();
                System.out.println(item);
            });
        });


    }

    @Test
    @Transactional // 테스트를 실행하나 실제로 db에서 삭제하진 않음 롤백해줌
    public void update(){
        //업데이트를 위해선 먼저 셀렉트를 해야한다
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser -> {
            // 여기서 setId를 지정해 버리면 그 아이디가 변한
            selectUser.setAccount("PPPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });
    }

    @Test
    @Transactional // 테스트를 실행하나 실제로 db에서 삭제하진 않음 롤백해줌
    public void delete(){
    // 삭제도 마찬가지로 먼저 셀렉트를 해야한다
        Optional<User> user = userRepository.findById(3L);


        Assert.assertTrue(user.isPresent()); // 반드시 true여야 한다
        user.ifPresent(selectUser -> {
            // 딜리트는 return값이 없다 void
            userRepository.delete(selectUser);
        });

//
//
//        if(deleteUser.isPresent()){
//            System.out.println("데이터 존재:"+deleteUser.get());
//        } else {
//            System.out.println(("데이터 삭제되어 없음"));
//        }
        Optional<User> deleteUser = userRepository.findById(2L);
        Assert.assertFalse(deleteUser.isPresent()); // 반드시 false여야 한다
    }
}
