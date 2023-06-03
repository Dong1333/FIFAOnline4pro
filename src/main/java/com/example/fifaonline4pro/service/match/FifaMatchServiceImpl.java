package com.example.fifaonline4pro.service.match;

import com.example.fifaonline4pro.config.ApiKey;
import com.example.fifaonline4pro.dto.match.MatchDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * @version 1.0
 * @Author seodong-geun
 * @since 2023/06/01
 */

@Service // 스프링 빈으로 등록(데이터 접근 계층을 의미)
@RequiredArgsConstructor // final 선언 필드 생성자 생성
@Slf4j
public class FifaMatchServiceImpl implements FifaMatchService{

    private final ApiKey apiKey; // API key 스프링 빈 주입
    private final RestTemplate restTemplate; //  HTTP 통신을 간편하게 처리하기 위한 객체 생성

    HttpHeaders headers = new HttpHeaders(); // (재사용)HTTP 요청의 헤더 정보를 담아서 보낼 수 있는 객체 생성

    // 유저 매칭 ID로 '매칭 상세 정보' 가져오기
    public MatchDTO findMatchInfo(String matchId) {
        String url = "https://api.nexon.co.kr/fifaonline4/v1.0/matches/" + matchId;

        // HTTP 요청을 위한 헤더 설정
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", apiKey.getKey());

        // HTTP 요청(GET) 객체 생성
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // API 호출 및 결과 수신
        ResponseEntity<MatchDTO> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, MatchDTO.class);
        return responseEntity.getBody();
    }
}
