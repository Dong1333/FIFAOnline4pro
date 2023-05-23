package com.example.fifaonline4pro.dto.match;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @version 1.0
 * @Author seodong-geun
 * @since 2023/05/23
 * 유저의 매치 기록 데이터 정보를 전달하는 (최상위)  DTO 객체
 * matchInfo는 유저 별 경기 관련한 매우 상세한 정보들이 담겨져 있다
 *
 * 자식 : MatchInfoDTO
 * (DTO) 값 개수 : 1개 (경기 데이터)
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class MatchDTO {
    private String matchId;             // 매치 고유 식별자
    private String matchDate;           // 매치 일자 (ex. 2019-05-13T18:03:10)
    private Integer matchType;          // 매치 종류 (/metadata/matchtype API 참고)
    private List<MatchInfoDTO> matchInfo;   // 매치 참여 플레이어별 매치 내용 상세 리스트
}
