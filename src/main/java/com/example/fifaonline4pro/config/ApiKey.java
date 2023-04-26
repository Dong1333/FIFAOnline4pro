package com.example.fifaonline4pro.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @Author seodong-geun
 * @since 2023/04/25
 * [API 인증 키 값을 저장하는 클래스]
 * application.properties 파일에서 "api.key" 값을 읽어와 key 필드에 주입
 */
@Getter // getter 생성
@Component // Spring Context에 Bean으로 등록
public class ApiKey {
    // 인증키 값을 저장하는 불변성 필드
    private final String key;

    // @Value : properties 파일 등에서 값을 읽어와 해당 필드에 주입
    @Autowired // 없어도 이상없지만 api.key가 이상이 있으면 컴파일 단계에서 확인
    public ApiKey(@Value("${api.key}") String key) {
        this.key = key;
    }
}
