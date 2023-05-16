package com.example.fifaonline4pro.controller;

import com.example.fifaonline4pro.domain.FifaUser;
import com.example.fifaonline4pro.dto.UserMatchHistoryDTO;
import com.example.fifaonline4pro.service.FifaUserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @version 1.1
 * @Author seodong-geun
 * @since 2023/04/25
 */
@RestController // 이 클래스가 REST 컨트롤러임을 표시
@RequestMapping("/{users}")// 모든 메소드가 "/users"라는 엔드포인트에 매핑
@Log4j2
@RequiredArgsConstructor // final 선언 필드 생성자 생성
public class FifaUserController {

    // FifaUserRepository 타입의 인스턴스 변수 선언
    private final FifaUserServiceImpl fifaUserServiceImpl;

    // '닉네임'으로 '유저 정보' 조회 후 Model타입 반환
    @GetMapping("/{nickname}")
    public ModelAndView getUserByNickname(@PathVariable("nickname") String nickname, Model model) {
        FifaUser nickNameUser = fifaUserServiceImpl.findUserByNickname(nickname); // 닉네임으로 유저 정보 가져오기
        String accessId = nickNameUser.getAccessId(); // 가져온 유저정보에서 accessId(고유 식별자)만 추출

        // getUserByAccessId 컨트롤러 메소드를 재요청하여 '유저 고유 식별자'로 얻을 수 있는 정보들을 모두 얻어온다.
        FifaUser accessIdUser = getUserByAccessId(accessId);

        if (accessIdUser == null) { // '유저 고유 식별자'로 가져온  FifaUser 객체가 null일 경우(존재하지 않을 경우)
            return new ModelAndView("error"); // error.html View를 반환
        }

        // 유저의 경기별 티어 정보 가져오기. > 배열 : 공식, 감독...
        List<UserMatchHistoryDTO> userMatchHistoryDTOList = fifaUserServiceImpl.getUserTearHistoryList(accessId);

        model.addAttribute("nickNameUser", nickNameUser); // Model 객체에 (닉네임으로 가져온) 유저 정보를 추가
        model.addAttribute("accessIdUser", accessIdUser); // Model 객체에 (고유 식별자로 가져온) 유저 정보를 추가
        model.addAttribute("userMatchHistory", userMatchHistoryDTOList); // Model 객체에 유저 티어 기록 배열 데이터 추가
        return new ModelAndView("userinfo"); // userinfo.html View를 반환
    }

    // '유저 고유 식별자'로 요청하는 메소드
    @GetMapping("/userinfo={accessId}")
    public FifaUser getUserByAccessId(@PathVariable("accessId") String accessId) {
        // FifaUserRepository의 findUserByAccessId 메소드를 호출하여 FifaUser 객체를 가져온다.
        FifaUser accessIdUser = fifaUserServiceImpl.findUserByAccessId(accessId);

        if (accessIdUser == null) { // '유저 고유 식별자'로 가져온 FifaUser 객체가 null일 경우(존재하지 않을 경우)
            return null; // null 반환
        }
        return accessIdUser; // '유저 고유 식별자'로 가져온 FifaUser 객체 반환
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
