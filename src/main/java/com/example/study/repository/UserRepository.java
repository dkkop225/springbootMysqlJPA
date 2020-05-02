package com.example.study.repository;

import com.example.study.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//레파지토리라는 것을 명시해주는 어노테이 //따로 쿼리문을 작성하지 않아도 기본적인 crud  해줌
@Repository
// JpaRepository 상속받아서 사용해야한다<T,ID> T:어떤 타입의 오브젝트가 들어갈껀지 선언(여기선 User 테이블이므로 User객체), ID : 기본키 타입
public interface UserRepository extends JpaRepository<User,Long> {

    // findFirstBy : 가장 최근 것이 리턴된다
    User findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);

    // findBy + 무엇으로 찾을건지
    // select * from user where account = ?
  //  Optional<User> findByAccount(String account);

    //Optional<User> findByEmail(String email);

    // account와 email로 찾는다
    // select * from user where account = ? and email = ?
   // Optional<User> findByAccountAndEmail(String account,String email);
    // 뒤에 변수명이 달라도 알아서 매핑해준
}
