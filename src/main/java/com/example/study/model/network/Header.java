package com.example.study.model.network;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header<T>{
    //api 통신 시간
   // @JsonProperty("transaction_time") // json형태가 만들어질 떄 해당 이름으로 만들어짐 스네이크 케이스로
    // 일일히 이렇게 하기 귀찮으니까 application.properties에 spring.jackson.property-naming-strategy=SNAKE_CASE로 설정해줌
    private LocalDateTime transactionTime;
    //api 응답 코드
    private String resultCode;
    //api 부가설명
    private String description;

    // 계속 바뀌는 데이터 부분
    // 어떤 타입이 올지 확실하지 않으므로 제네릭으로
    private T data;

    // OK
    public static <T> Header<T> OK(){
        return  (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .build();
    }


    //DATA OK
    public static <T> Header<T> OK(T data){
        return  (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build();
    }

    //ERROR
    public static <T> Header<T> ERROR(String description){
        return  (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description(description)
                .build();
    }

}
