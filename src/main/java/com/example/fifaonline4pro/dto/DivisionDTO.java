package com.example.fifaonline4pro.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @version 1.0
 * @Author seodong-geun
 * @since 2023/05/15
 * 티어 정보를 담는 데이터 전송 객체(DTO)
 */
@Getter
@Setter
public class DivisionDTO {
    private int divisionId; // 티어 식별 아이디
    private String divisionName; // 티어 이름
}
