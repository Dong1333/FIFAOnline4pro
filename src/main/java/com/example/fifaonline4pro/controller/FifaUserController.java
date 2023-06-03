package com.example.fifaonline4pro.controller;

import com.example.fifaonline4pro.domain.FifaUser;

import com.example.fifaonline4pro.dto.match.MatchDTO;
import com.example.fifaonline4pro.dto.tear.UserTearHistoryDTO;
import com.example.fifaonline4pro.service.match.FifaMatchService;
import com.example.fifaonline4pro.service.user.FifaUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.1
 * @Author seodong-geun
 * @since 2023/04/25
 */
@RestController // 이 클래스가 REST 컨트롤러임을 표시
@RequestMapping("/user")// 모든 메소드가 "/users"라는 엔드포인트에 매핑
@Log4j2
@RequiredArgsConstructor // final 선언 필드 생성자 생성
public class FifaUserController {

    // FifaUserRepository 타입의 인스턴스 변수 선언
    private final FifaUserService fifaUserService;
    private final FifaMatchService fifaMatchService;

    // '닉네임'으로 '유저 정보' 조회 후 Model타입 반환
    @GetMapping("/{nickname}")
    public ModelAndView getUserByNickname(@PathVariable("nickname") String nickname, HttpServletRequest request, Model model) {
        FifaUser userInfo = fifaUserService.findUserByNickname(nickname); // 닉네임으로 유저 정보 가져오기
        String accessId = userInfo.getAccessId(); // 가져온 유저정보에서 accessId(고유 식별자)만 추출

        fifaUserService.setAccessIdToSession(request, accessId); // 세션에 accessId 저장

        // '유저 고유 식별자'로 얻을 수 있는 정보들을 모두 얻어온다.
        FifaUser userInfoByAccessId = fifaUserService.findUserByAccessId();
        // 유저의 경기별 티어 정보 가져오기. > 배열 데이터(공식, 감독)
        List<UserTearHistoryDTO> userTearHistoryDTOList = fifaUserService.findUserTearHistoryList();

        if (userInfoByAccessId == null) { // '유저 고유 식별자'로 가져온  FifaUser 객체가 null일 경우(존재하지 않을 경우)
            return new ModelAndView("error"); // error.html View를 반환
        }

        model.addAttribute("userInfoByAccessId", userInfoByAccessId); // Model 객체에 (고유 식별자로 가져온) 유저 정보를 추가
        model.addAttribute("userTearHistoryDTOList", userTearHistoryDTOList); // Model 객체에 유저 티어 기록 배열 데이터 추가
        return new ModelAndView("userinfo"); // userinfo.html View를 반환
    }

    // 유저 매칭 기록 조회(MVC)
    @GetMapping("/getUserMatchHistory")
    public ModelAndView getUserMatchHistory(@RequestParam("matchType") int matchType,
                                            @RequestParam("offset") int offset,
                                            @RequestParam("limit") int limit,
                                            Model model) {
        // 매칭 상세기록들을 담을 MatchDTO 리스트 선언
        List<MatchDTO> matchDTOs = new ArrayList<>();
        // 매칭 ID 조회(경기 타입, 시작, 끝)
        List<String> matchIds = fifaUserService.findUserMatchHistory(matchType, offset, limit);
        // 조회한 매칭 ID별 상세기록 값 matchDTOs에 저장
        for (String matchId : matchIds) {
            MatchDTO matchDTO = fifaMatchService.findMatchInfo(matchId);
            matchDTOs.add(matchDTO);
        }
        model.addAttribute("matches", matchDTOs);

        return new ModelAndView("matchHistory");// 타임리프 템플릿 이름
    }


    // 유저 매칭 기록 조회(Ajax)
    @GetMapping("/loadMoreMatches")
    public ResponseEntity<List<MatchDTO>> loadMoreMatches(@RequestParam("matchType") int matchType,
                                                              @RequestParam("offset") int offset,
                                                              @RequestParam("limit") int limit) {
        // 매칭 상세기록들을 담을 MatchDTO 리스트 선언
        List<MatchDTO> matchDTOs = new ArrayList<>();
        // 매칭 ID 조회(경기 타입, 시작, 끝)
        List<String> matchIds = fifaUserService.findUserMatchHistory(matchType, offset, limit);
        // 조회한 매칭 ID별 상세기록 값 matchDTOs에 저장
        for (String matchId : matchIds) {
            MatchDTO matchDTO = fifaMatchService.findMatchInfo(matchId);
            matchDTOs.add(matchDTO);
        }

        // MatchDTO 객체를 JSON으로 변환하여 HTTP 응답
        return ResponseEntity.ok(matchDTOs);
    }



    //   Ajax 방식 '닉네임'으로 '유저 정보' 조회 후 JSON 형태 반환
    //     HTTP GET방식에 "/users/{nickname}" 요청
    //    @GetMapping("/{nickname}")
    //    public ResponseEntity<FifaUser> getUserInfo(@PathVariable("nickname") String nickname) { // nickname이라는 경로 변수를 선언
    //        // FifaUserRepository의 findByUserinfo 메소드를 호출하여 FifaUser 객체를 가져온다.
    //        FifaUser user = fifaUserService.findByUserinfo(nickname);
    //
    //        if (user == null) { // 가져온 FifaUser 객체가 null일 경우(존재하지 않을 경우)
    //            return ResponseEntity.notFound().build(); // HTTP 404 Not Found 응답 반환
    //        }
    //        // 만약 유저가 존재한다면 HTTP 200 OK 응답과 함께 가져온 FifaUser 객체 반환
    //        return ResponseEntity.ok(user);
    //    }
}
