package com.example.fifaonline4pro.controller;

import com.example.fifaonline4pro.domain.FifaUser;
import com.example.fifaonline4pro.repository.FifaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @Author seodong-geun
 * @since 2023/04/25
 */
@RestController // 이 클래스가 REST 컨트롤러임을 표시
@RequestMapping("/users") // 모든 메소드가 "/users"라는 엔드포인트에 매핑
@RequiredArgsConstructor // final 선언 필드 생성자 생성
public class FifaUserController {

    // FifaUserRepository 타입의 인스턴스 변수 선언
    private final FifaUserRepository fifaUserRepository;


    // '닉네임'으로 '유저 정보' 조회
    // HTTP GET방식에 "/users/{nickname}" 요청
    @GetMapping("/{nickname}")
    public ResponseEntity<FifaUser> getUserInfo(@PathVariable("nickname") String nickname) { // nickname이라는 경로 변수를 선언
        // FifaUserRepository의 findByUserinfo 메소드를 호출하여 FifaUser 객체를 가져온다.
        FifaUser user = fifaUserRepository.findByUserinfo(nickname);

        if (user == null) { // 가져온 FifaUser 객체가 null일 경우(존재하지 않을 경우)
            return ResponseEntity.notFound().build(); // HTTP 404 Not Found 응답 반환
        }
        // 만약 유저가 존재한다면 HTTP 200 OK 응답과 함께 가져온 FifaUser 객체 반환
        return ResponseEntity.ok(user);
    }
}
