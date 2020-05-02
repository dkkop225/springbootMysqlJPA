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
    public void create() {
//        User user = new User();
//
//        user.setAccount("TestUser03");
//        user.setEmail("TestUser03@gmail.com");
//        user.setPhoneNumber("010-1111-3333");
//        user.setCreatedAt(LocalDateTime.now());
//        user.setCreatedBy("TestUser03");
//
//        // 데이터를 저장하고 리턴해줌
//        User newUser = userRepository.save(user);
//
//        System.out.println("newUser:"+newUser);
//        //(id=1, account=TestUser01, email=TestUser01@gmail.com, phoneNumber=010-1111-1111, createdAt=2020-05-01T21:16:50.130, createdBy=admin, updatedAt=null, updatedBy=null)
//        // User 에서 @Data 어노테이션에 toString 내장되어 있어서 깔끔하게 나어는 것이다
//        //User(id=2, account=TestUser012, email=TestUser02@gmail.com, phoneNumber=010-1111-1111, createdAt=2020-05-01T21:19:24.625, createdBy=TestUser02, updatedAt=null, updatedBy=null)
//
        String account = "Test01";
        String password = "Test01";
        String status = "REGISTERED";
        String email = "Test01@gmail.com";
        String phoneNumber = "010-1111-3333";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
//        user.setCreatedAt(createdAt);
//        user.setCreatedBy(createdBy);

        //빌더패턴 적용 , account, password, status, email 값만 있는 객체 생성
        User u = User.builder().account(account).password(password).status(status).email(email).build();

        User newUser = userRepository.save(user);
        Assert.assertNotNull(newUser);


    }

    @Test
    @Transactional
    public void read(){
//        //어떤 아이디를 가진 것을 가져오겟다
//        Optional<User> user = userRepository.findByAccount("TestUser03");
//
//        //ifPresent =>  값이 있으면
//        user.ifPresent(selectUser -> {
////            System.out.println("User:"+selectUser);
////            System.out.println("email:"+selectUser.getEmail());
//            selectUser.getOrderDetailList().stream().forEach(detail->{
//                Item item = detail.getItem();
//                System.out.println(item);
//            });
//        });
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");

        //user.setEmail(email).setPhoneNumber(phonenumber).setStatus(status);
        // 이미 생성되어 있는 객체에서 체이닝 방식으로 업데이트 하는 방법
        //User u = new User().setAccount().setEmail();  체이닝 방법은 이처럼 선언하면서도 사용 가능하다
        if(user != null) {

            user.getOrderGroupList().stream().forEach(orderGroup -> {
                System.out.println("------------주문묶음----------------------");
                System.out.println("수령인:"+orderGroup.getRevName());
                System.out.println("수령지:"+orderGroup.getRevAddress());
                System.out.println("총금액:"+orderGroup.getTotalPrice());
                System.out.println("총수량:"+orderGroup.getTotalQuantity());

                System.out.println("------------주문상세----------------------");
                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println("파트너사이름 :"+  orderDetail.getItem().getPartner().getName());
                    System.out.println("파트너사 카테고리 :"+  orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("주문상품 :"+  orderDetail.getItem().getName());
                    System.out.println("고객센터번호 :"+  orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("주문상태:"+orderDetail.getStatus());
                    System.out.println("도착예정:"+orderDetail.getArrivalDate());



                });
            });
        }
        Assert.assertNotNull(user);

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
