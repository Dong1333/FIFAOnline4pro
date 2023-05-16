package com.example.fifaonline4pro.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @version 1.0
 * @Author seodong-geun
 * @since 2023/05/11
 * 경기 종류와 종류를 표현하는 번호 정보를 담아 전달하는 DTO
 */
@Getter
@Setter
public class MatchTypeDTO {
    private int matchType; // 경기 종류 번호 > ex) 50
    private String desc; // 경기 종류 이름 > ex) 공식경기
}
