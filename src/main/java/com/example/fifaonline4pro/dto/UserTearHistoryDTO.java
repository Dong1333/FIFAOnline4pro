package com.example.fifaonline4pro.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @version 1.0
 * @Author seodong-geun
 * @since 2023/05/11
 * 유저의 경기별 최고티어 정보를 담아 전달하는 DTO 객체
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class UserTearHistoryDTO {
        private String matchType; // 경기 종류
        private String division; // 최고 티어
        private String achievementDate; // 달성 날짜
}

