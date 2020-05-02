package com.example.study.component;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

//로그인사용자 감시할목적으로

//컴포넌트임을 명시하는 어노테이션
@Component
//AuditorAware 상
public class LoginUserAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
      //  return Optional.empty();
        return Optional.of("AdminServer");
    }
}
