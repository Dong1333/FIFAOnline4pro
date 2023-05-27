package com.example.fifaonline4pro.service;

import com.example.fifaonline4pro.config.ApiKey;
import com.example.fifaonline4pro.domain.FifaUser;
import com.example.fifaonline4pro.dto.match.MatchDTO;
import com.example.fifaonline4pro.dto.tear.DivisionDTO;
import com.example.fifaonline4pro.dto.tear.MatchTypeDTO;

import com.example.fifaonline4pro.dto.tear.UserTearHistoryDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.*;


/**
 * @Author seodong-geun
 * @version 1.0
 * @since 2023/04/25
 */

@Service // 스프링 빈으로 등록(데이터 접근 계층을 의미)
@RequiredArgsConstructor // final 선언 필드 생성자 생성
@Slf4j
public class FifaUserServiceImpl implements FifaUserService{


    private final ApiKey apiKey; // API key 스프링 빈 주입
    private final RestTemplate restTemplate; //  HTTP 통신을 간편하게 처리하기 위한 객체 생성
    private final FifaMetadataMatcherService fifaMetadataMatcherService;
    private final FifaMetadataService  fifaMetadataService;
    private final HttpServletRequest request; // accessId가 저장되는 requset 멤버 변수(재할당 불가)

    HttpHeaders headers = new HttpHeaders(); // (재사용)HTTP 요청의 헤더 정보를 담아서 보낼 수 있는 객체 생성

    // 컨트롤러에서 유저 accessId 값 받아 세션으로 저장
    public void setAccessIdToSession(HttpServletRequest request, String accessId) {
        HttpSession session = request.getSession();
        session.setAttribute("accessId", accessId);
    }

    // accessId 가져오기
    public String getAccessIdToSession() {
        HttpSession session = request.getSession();
        String accessId = (String) session.getAttribute("accessId");
        return accessId;
    }


    // 유저 정보 조회 메소드 findUserByNickname
    // nickname으로 넥슨 open API에서 유저 정보를 가져온 후, FifaUser 객체로 변환 후 반환
    public FifaUser findUserByNickname(String nickname) {
        // 넥슨에 요청할 url (open API URL + 작성한 유저이름)
        String url = "https://api.nexon.co.kr/fifaonline4/v1.0/users?nickname=" + nickname;

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
    public FifaUser findUserByAccessId() {
        String accessId = getAccessIdToSession(); // 유저 accessId 가져오기
        // 넥슨에 요청할 url (open API URL + 작성한 유저 이름을 통해 얻어온 accessId)
        String url = "https://api.nexon.co.kr/fifaonline4/v1.0/users/" + accessId;

        log.info("-------accessId---------");
        log.info(accessId);

        headers.set("Authorization", apiKey.getKey()); // Authorization 정보를 담아서 API에 인증을 하기위해 api key값을 헤더 객체에 저장
        HttpEntity<String> entity = new HttpEntity<>("Userinfo", headers); // HTTP 요청의 본문(헤더도 함께 가능) 정보를 담아서 보낼 수 있는 객체 생성

        // HTTP 요청을 보내고 응답을 받아오는 RestTemplate 객체의 exchange() 메소드를 호출
        // GET 방식으로 요청을 보내고, 응답으로 받은 JSON 데이터를 FifaUser 클래스로 매핑하여 반환
        // (url, 메소드, HTTP 요청 본문 데이터, HTTP 응답 본문 타입)
        ResponseEntity<FifaUser> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, FifaUser.class);

        return responseEntity.getBody(); // 최종적으로 반환된 FifaUser 객체의 Body 값을 반환
    }

    // 유저 경기별 역대 최고 등급 조회
    public List<UserTearHistoryDTO> findUserTearHistoryList() {
        String accessId = getAccessIdToSession(); // 유저 accessId 가져오기

        log.info("-------accessId---------");
        log.info(accessId); // 로그 찍기

        String url = "https://api.nexon.co.kr/fifaonline4/v1.0/users/" + accessId + "/maxdivision";
        headers.set("Authorization", apiKey.getKey()); // Authorization 정보를 담아서 API에 인증을 하기위해 api key값을 헤더 객체에 저장
        HttpEntity<String> entity = new HttpEntity<>("UserMacthHistory", headers); // HTTP 요청의 본문(헤더도 함께 가능) 정보를 담아서 보낼 수 있는 객체 생성
        List<UserTearHistoryDTO> userTearHistoryDTOList = new ArrayList<>();

        // HTTP 요청을 보내고 응답을 받아오는 RestTemplate 객체의 exchange() 메소드를 호출
        // 유저 경기별 역대 최고 등급 조회 결과 > [{"matchType":50,"division":800,"achievementDate":"2023-05-12T01:03:34"},{"matchType":52,"division":1100,"achievementDate":"2021-03-01T18:42:03"}]
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        try {
            // responseEntity의 body 데이터를 JSON 배열로 변환.
            JSONArray jsonArray = new JSONArray(responseEntity.getBody());

            // 피파의 메타 데이터 가져오기(경기 종류, 티어 종류)
            List<MatchTypeDTO> fifaMatchTypeDTOList = fifaMetadataService.getMatchTypeDTOList();
            List<DivisionDTO> fifaDivisionDTOList = fifaMetadataService.getDivisionDTOList();

            // 유저 티어 기록 제이슨 '배열' 데이터를 하나씩 JSONObject 객체로 변환
            // > {"division":800,"matchType":50,"achievementDate":"2023-05-12T01:03:34"}
            // > {"division":1100,"matchType":52,"achievementDate":"2021-03-01T18:42:03"}
            for (int i = 0; i < jsonArray.length(); i++) {
                // 현재 요소에 대한 JSON 객체를 가져옵니다.
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // 제이슨 객체로 변한 조회 결과 객체에서 값 가져와서 저장하기
                int userMatchTypeId = jsonObject.getInt("matchType");
                int userDivisionId = jsonObject.getInt("division");
                String achievementDate = jsonObject.getString("achievementDate");

                // 유저의 매치 기록을 담아 전달할 DTO 객체 생성
                UserTearHistoryDTO userMatchHistoryDTO = new UserTearHistoryDTO();

                // 유저의 기록(경기 종류, 최고 티어, 달성 날짜) 저장
                // 유저의 경기 기록들을 하나씩 피파의 메타데이터와 매칭 경기 : 공식, 감독 / 티어 : 챌린저 1, 프로 1
                userMatchHistoryDTO.setMatchType(fifaMetadataMatcherService.getMatchTypeResult(userMatchTypeId, fifaMatchTypeDTOList));
                userMatchHistoryDTO.setDivision(fifaMetadataMatcherService.getDivisionResult(userDivisionId, fifaDivisionDTOList));
                userMatchHistoryDTO.setAchievementDate(achievementDate);  // 기록 달성 날짜 저장

                // DTO를 경기 기록 리스트에 하나씩(공식, 감독) 저장
                userTearHistoryDTOList.add(userMatchHistoryDTO);
            }
        } catch (JSONException e) { // JSON 데이터 관련 처리를 하는 도중 에러가 나오면
            e.printStackTrace(); // 예외가 발생한 위치와 예외 스택 트레이스(디버깅하는 데 매우 유용)을 출력
        }
        return userTearHistoryDTOList;
    }

    // 유저 매치 기록중 매칭 ID만 가져오기
    public List<String> findUserMatchHistory(int matchType, int offset, int limit) {
        String accessId = getAccessIdToSession(); // 유저 accessId 가져오기

        log.info("------(findUserMatchHistory accessId)-----");
        log.info(accessId);
        String url = "https://api.nexon.co.kr/fifaonline4/v1.0/users/" + accessId + "/matches?matchtype=" + matchType + "&offset=" + offset + "&limit=" + limit;

        // HTTP 요청을 위한 헤더 설정
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", apiKey.getKey());

        // 요청 URI 객체 생성 (요청 url + 값 지정하기)
        URI uri = URI.create(url.replace("{accessid}", accessId)
                .replace("{matchtype}", String.valueOf(matchType))
                .replace("{offset}", String.valueOf(offset))
                .replace("{limit}", String.valueOf(limit)));

        // HTTP 요청(GET) 객체 생성
        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, uri);

        // API 호출 및 결과 수신 (exchange = 요청에 대한 응답을 반환)
        ResponseEntity<String[]> responseEntity = restTemplate.exchange(requestEntity, String[].class);
        String[] response = responseEntity.getBody();

        // 결과 반환
        return Arrays.asList(response);
    }

    // 유저 매칭 ID로 매칭 상세정보 가져오기
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
