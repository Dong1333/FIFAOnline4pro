package com.example.fifaonline4pro.dto.match;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @version 1.0
 * @Author seodong-geun
 * @since 2023/05/23
 *
 * 유저 경기 중 슛, 골 관련 데이터 정보를 전달하는 DTO 객체
 *
 * 조부모 : MatchDTO
 * 부모 : MatchInfoDTO
 * 자식 : (없음)
 * (DTO) 값 개수 : 1개 (슛 데이터)
 */
@Getter
@Setter
@NoArgsConstructor
public class ShootDTO {
    private Integer shootTotal;         // 총 슛 수
    private Integer effectiveShootTotal;    // 총 유효슛 수
    private Integer shootOutScore;      // 승부차기 슛 수
    private Integer goalTotal;          // 총 골 수 (실제 골 수: goalInPenalty+goalOutPenalty+goalPenaltyKick)
    private Integer goalTotalDisplay;   // 게임 종료 후 유저에게 노출되는 골 수
    private Integer ownGoal;            // 자책 골 수
    private Integer shootHeading;       // 헤딩 슛 수
    private Integer goalHeading;        // 헤딩 골 수
    private Integer shootFreekick;      // 프리킥 슛 수
    private Integer goalFreekick;       // 프리킥 골 수
    private Integer shootInPenalty;     // 인패널티 슛 수
    private Integer goalInPenalty;      // 인패널티 골 수
    private Integer shootOutPenalty;    // 아웃패널티 슛 수
    private Integer goalOutPenalty;     // 아웃패널티 골 수
    private Integer shootPenaltyKick;   // 패널티킥 슛 수
    private Integer goalPenaltyKick;    // 패널티킥 골 수
}