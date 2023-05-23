package com.example.fifaonline4pro.dto.match;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @version 1.0
 * @Author seodong-geun
 * @since 2023/05/23
 *
 * 유저의 매치 기록 중 슛 관련 자세한(시간, 슈팅한 선수 등) 데이터 정보를 전달하는 DTO 객체
 *
 *
 * 조부모 : MatchDTO
 * 부모 : MatchInfoDTO
 * 자식 : (없음)
 * (DTO) 값 개수 : 1개 이상~ (슛 할 때마다 데이터)
 *
 */
@Getter
@Setter
@NoArgsConstructor

public class ShootDetailDTO {
    private Integer goalTime;           // 슛 시간
    private Double x;                   // 슛 x좌표 (전체 경기장 기준)
    private Double y;                   // 슛 y좌표 (전체 경기장 기준)
    private Integer type;               // 슛 종류 (1: normal, 2: finesse, 3: header, 4: lob, 5: flare, 6: low, 7: volley, 8: free-kick, 9: penalty, 10: KNUCKLE)
    private Integer result;             // 슛 결과 (1: ontarget, 2: offtarget, 3: goal)
    private Integer spId;               // 슈팅 선수 고유 식별자 (/metadata/spid API 참고)
    private Integer spGrade;            // 슈팅 선수 강화 등급
    private Integer spLevel;            // 슈팅 선수 레벨
    private Boolean spIdType;           // 슈팅 선수 임대 여부 (임대선수 : true, 비임대선수 : false)
    private Boolean assist;             // 어시스트 받은 골 여부 (받음 : true, 안받음 : false)
    private Integer assistSpId;         // 어시스트 선수 고유 식별자 (/metadata/spid API 참고)
    private Double assistX;             // 어시스트 x좌표
    private Double assistY;             // 어시스트 y좌표
    private Boolean hitPost;            // 골포스트 맞춤 여부 (맞춤 : true, 못 맞춤 : false)
    private Boolean inPenalty;          // 페널티박스 안에서 넣은 슛 여부 (안 : true, 밖 : false)
}