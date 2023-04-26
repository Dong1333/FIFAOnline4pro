package com.example.fifaonline4pro.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @version 1.0
 * @Author seodong-geun
 * @since 2023/04/25
 */
@Configuration // 스프링의 설정 클래스를 의미
public class AppConfig {

    // RestTemplate: (스프링에서 제공)HTTP 요청을 쉽게 처리할 수 있는 클래스.
    // HTTP 요청을 보낼 때 필요한 설정
    @Bean // 스프링 컨테이너에 의해 관리되는 Bean 객체.
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
