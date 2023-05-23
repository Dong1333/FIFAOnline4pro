package com.example.fifaonline4pro.dto.match;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @version 1.0
 * @Author seodong-geun
 * @since 2023/05/23
 *
 * 유저 경기 중 출전(후보 포함) 선수 데이터 정보를 전달하는 DTO 객체
 *
 * 조부모 : MatchDTO
 * 부모 : MatchInfoDTO
 * 자식 : StatusDTO
 * (DTO) 값 개수 : 18개 (선발, 후보)
 */
@Getter
@Setter
@NoArgsConstructor
public class PlayerDTO {
    private Integer spId;               // 선수 고유 식별자 (/metadata/spid API 참고)
    private Integer spPosition;         // 선수 포지션 (/metadata/spposition API 참고)
    private Integer spGrade;            // 선수 강화 등급
    private StatusDTO status;           // 선수 경기 스탯
}
