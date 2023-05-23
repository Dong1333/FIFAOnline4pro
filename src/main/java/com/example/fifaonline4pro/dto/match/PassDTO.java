package com.example.fifaonline4pro.dto.match;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @version 1.0
 * @Author seodong-geun
 * @since 2023/05/23
 *
 * 유저의 매치 기록 중 패스 데이터 정보를 전달하는 DTO 객체
 *
 * 조부모 : MatchDTO
 * 부모 : MatchInfoDTO
 * 자식 : (없음)
 * (DTO) 값 개수 : 1개 (패스 데이터)
 */
@Getter
@Setter
@NoArgsConstructor
public class PassDTO {
    private Integer passTry;            // 패스 시도 수
    private Integer passSuccess;        // 패스 성공 수
    private Integer shortPassTry;       // 숏 패스 시도 수
    private Integer shortPassSuccess;   // 숏 패스 성공 수
    private Integer longPassTry;        // 롱 패스 시도 수
    private Integer longPassSuccess;    // 롱 패스 성공 수
    private Integer bouncingLobPassTry; // 바운싱 롭 패스 시도 수
    private Integer bouncingLobPassSuccess; // 바운싱 롭 패스 성공 수
    private Integer drivenGroundPassTry;    // 드리븐 땅볼 패스 시도 수
    private Integer drivenGroundPassSuccess;    // 드리븐 땅볼 패스 성공 수
    private Integer throughPassTry;     // 스루 패스 시도 수
    private Integer throughPassSuccess; // 스루 패스 성공 수
    private Integer lobbedThroughPassTry;    // 로빙 스루 패스 시도 수
    private Integer lobbedThroughPassSuccess;    // 로빙 스루 패스 성공 수
}