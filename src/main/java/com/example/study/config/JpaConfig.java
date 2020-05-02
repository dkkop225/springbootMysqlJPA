package com.example.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//JPA 설정파일이란걸 알려주는 어노테이션
@Configuration
@EnableJpaAuditing //JPA 감시를 활성화 시키겠
public class JpaConfig {

}
