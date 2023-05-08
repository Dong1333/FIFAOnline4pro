package com.example.fifaonline4pro.service;

import com.example.fifaonline4pro.config.ApiKey;
import com.example.fifaonline4pro.domain.FifaUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * @Author seodong-geun
 * @version 1.0
 * @since 2023/04/25
 */

@Service // 스프링 빈으로 등록(데이터 접근 계층을 의미)
@RequiredArgsConstructor // final 선언 필드 생성자 생성
@Slf4j
public class FifaUserServiceImpl implements FifaUserService{

    private final RestTemplate restTemplate; // RestTemplate 스프링 빈 주입
    private final ApiKey apiKey; // API key 스프링 빈 주입

    // 유저 정보 조회 메소드 findUserByNickname
    // nickname으로 넥슨 open API에서 유저 정보를 가져온 후, FifaUser 객체로 변환 후 반환
    public FifaUser findUserByNickname(String nickname) {
        // 넥슨에 요청할 url (open API URL + 작성한 유저이름)
        String url = "https://api.nexon.co.kr/fifaonline4/v1.0/users?nickname=" + nickname;

        HttpHeaders headers = new HttpHeaders(); // HTTP 요청의 헤더 정보를 담아서 보낼 수 있는 객체 생성
        headers.set("Authorization", apiKey.getKey()); // Authorization 정보를 담아서 API에 인증을 하기위해 api key값을 헤더 객체에 저장
        HttpEntity<String> entity = new HttpEntity<>("Userinfo", headers); // HTTP 요청의 본문(헤더도 함께 가능) 정보를 담아서 보낼 수 있는 객체 생성

        // HTTP 요청을 보내고 응답을 받아오는 RestTemplate 객체의 exchange() 메소드를 호출
        // GET 방식으로 요청을 보내고, 응답으로 받은 JSON 데이터를 FifaUser 클래스로 매핑하여 반환
        // (url, 메소드, HTTP 요청 본문 데이터, HTTP 응답 본문 타입)
        ResponseEntity<FifaUser> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, FifaUser.class);

        return responseEntity.getBody(); // 최종적으로 반환된 FifaUser 객체의 Body 값을 반환
    }

    // 유저 정보 조회 메소드 findUserByAccessId
    // accessId으로 넥슨 open API에서 유저 정보를 가져온 후, FifaUser 객체로 변환 후 반환
    public FifaUser findUserByAccessId(String accessId) {
        // 넥슨에 요청할 url (open API URL + 작성한 유저 이름을 통해 얻어온 accessId)
        String url = "https://api.nexon.co.kr/fifaonline4/v1.0/users/" + accessId;

        HttpHeaders headers = new HttpHeaders(); // HTTP 요청의 헤더 정보를 담아서 보낼 수 있는 객체 생성
        headers.set("Authorization", apiKey.getKey()); // Authorization 정보를 담아서 API에 인증을 하기위해 api key값을 헤더 객체에 저장
        HttpEntity<String> entity = new HttpEntity<>("Userinfo", headers); // HTTP 요청의 본문(헤더도 함께 가능) 정보를 담아서 보낼 수 있는 객체 생성

        // HTTP 요청을 보내고 응답을 받아오는 RestTemplate 객체의 exchange() 메소드를 호출
        // GET 방식으로 요청을 보내고, 응답으로 받은 JSON 데이터를 FifaUser 클래스로 매핑하여 반환
        // (url, 메소드, HTTP 요청 본문 데이터, HTTP 응답 본문 타입)
        ResponseEntity<FifaUser> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, FifaUser.class);

        return responseEntity.getBody(); // 최종적으로 반환된 FifaUser 객체의 Body 값을 반환
    }
}
