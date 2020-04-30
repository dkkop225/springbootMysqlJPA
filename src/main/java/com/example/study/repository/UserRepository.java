package com.example.study.repository;

import com.example.study.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//레파지토리라는 것을 명시해주는 어노테이 //따로 쿼리문을 작성하지 않아도 기본적인 crud  해줌
@Repository
// JpaRepository 상속받아서 사용해야한다<T,ID> T:어떤 타입의 오브젝트가 들어갈껀지 선언(여기선 User 테이블이므로 User객체), ID : 기본키 타입
public interface UserRepository extends JpaRepository<User,Long> {
}
