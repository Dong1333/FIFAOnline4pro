package com.example.fifaonline4pro.dto.match;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @version 1.0
 * @Author seodong-geun
 * @since 2023/05/23
 * 유저의 매치 기록 중 수비 데이터 정보를 전달하는 DTO 객체
 *
 * 조부모 : MatchDTO
 * 부모 : MatchInfoDTO
 * 자식 : (없음)
 * (DTO) 값 개수 : 1개 (수비 데이터)
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class DefenceDTO {
    private Integer blockTry;           // 블락 시도 수
    private Integer blockSuccess;       // 블락 성공 수
    private Integer tackleTry;          // 태클 시도 수
    private Integer tackleSuccess;      // 태클 성공 수
}