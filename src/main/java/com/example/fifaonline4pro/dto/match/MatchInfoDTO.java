package com.example.fifaonline4pro.dto.match;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @version 1.0
 * @Author seodong-geun
 * @since 2023/05/23
 * 유저의 매치 기록 정보 모두를 담고 있는 (상위) 정보 DTO 객체 * 최상위 아님
 * 비고 : 각종 데이터와 더 자세한 데이터를 담고있는 DTO 클래스(패스, 슛 등)를 가지고 있다.
 *
 * 조부모 : (없음)
 * 부모 : MatchDTO
 * 자식 : PassDTO, DefenceDTO, PlayerDTO, ShootDetailDTO, ShootDTO
 * (DTO) 값 개수 : 2개 (본인, 상대)
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class MatchInfoDTO {
    private String accessId;            // 유저 고유 식별자
    private String nickname;            // 유저 닉네임
    private MatchDetailDTO matchDetail; // 매치 결과 상세 정보
    private ShootDTO shoot;             // 슈팅 정보
    private List<ShootDetailDTO> shootDetail;   // 슈팅 별 상세정보 리스트
    private PassDTO pass;               // 패스 정보
    private DefenceDTO defence;         // 수비 정보
    private List<PlayerDTO> player;     // 경기 사용 선수 정보
}
