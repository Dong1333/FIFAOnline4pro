package com.example.fifaonline4pro.dto.match;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @version 1.0
 * @Author seodong-geun
 * @since 2023/05/23
 * 유저 경기 중 선수별 경기 데이터를 전달하는 DTO 객체
 *
 * 부모 : PlayerDTO
 * 자식 : (없음)
 * (DTO) 값 개수 : 18개 (선발, 후보)
 *
 *
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class StatusDTO {
    private Integer shoot;              // 슛 수
    private Integer effectiveShoot;     // 유효 슛 수
    private Integer assist;             // 어시스트 수
    private Integer goal;               // 득점 수
    private Integer dribble;            // 드리블 거리(야드)
    private Integer intercept;          // 인터셉트 수
    private Integer defending;          // 디펜딩 수
    private Integer passTry;            // 패스 시도 수
    private Integer passSuccess;        // 패스 성공 수
    private Integer dribbleTry;         // 드리블 시도 수
    private Integer dribbleSuccess;     // 드리블 성공 수
    private Integer ballPossesionTry;   // 볼 소유 시도 수
    private Integer ballPossesionSuccess;   // 볼 소유 성공 수
    private Integer aerialTry;          // 공중볼 경합 시도 수
    private Integer aerialSuccess;      // 공중볼 경합 성공 수
    private Integer blockTry;           // 블락 시도 수
    private Integer block;              // 블락 성공 수
    private Integer tackleTry;          // 태클 시도 수
    private Integer tackle;             // 태클 성공 수
    private Integer yellowCards;        // 옐로카드 수
    private Integer redCards;           // 레드카드 수
    private Float spRating;             // 선수 평점
}