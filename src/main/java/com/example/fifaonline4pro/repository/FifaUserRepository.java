package com.example.fifaonline4pro.repository;

import com.example.fifaonline4pro.config.ApiKey;
import com.example.fifaonline4pro.domain.FifaUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

/**
 * @Author seodong-geun
 * @version 1.0
 * @since 2023/04/25
 */

@Repository // 스프링 빈으로 등록(데이터 접근 계층을 의미)
@RequiredArgsConstructor // final 선언 필드 생성자 생성
public class FifaUserRepository {

    private final RestTemplate restTemplate; // RestTemplate 스프링 빈 주입
    private final ApiKey apiKey; // API key 스프링 빈 주입

    //  API의 엔드포인트(endpoint) URL을 저장하기 위한 상수
    private static final String API_URL = "https://api.nexon.co.kr/fifaonline4/v1.0/users?nickname=";

    // 유저 정보 조회 메소드 Userinfo
    // nickname으로 API에서 유저 정보를 가져온 후, FifaUser 객체로 변환 후 반환
    public FifaUser findByUserinfo(String nickname) {
        // 요청 url (open API URL + 작성한 유저이름)
        String url = API_URL + nickname;

        HttpHeaders headers = new HttpHeaders(); // HTTP 요청의 헤더 정보를 담아서 보낼 수 있는 객체 생성
        headers.set("Authorization", apiKey.getKey()); // Authorization 정보를 담아서 API에 인증을 하기위해 api key값 헤더객체에 저장
        HttpEntity<String> entity = new HttpEntity<>("Userinfo", headers); // HTTP 요청의 본문(헤더도 함께 가능) 정보를 담아서 보낼 수 있는 객체 생성

        // HTTP 요청을 보내고 응답을 받아오는 RestTemplate 객체의 exchange() 메소드를 호출
        // GET 방식으로 요청을 보내고, 응답으로 받은 JSON 데이터를 FifaUser 클래스로 매핑하여 반환
        ResponseEntity<FifaUser> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, FifaUser.class);

        return responseEntity.getBody(); // 최종적으로 반환된 FifaUser 객체의 Body 값을 반환
    }
}
