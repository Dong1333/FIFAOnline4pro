package com.example.fifaonline4pro.service;

import com.example.fifaonline4pro.domain.FifaUser;
import com.example.fifaonline4pro.dto.UserTearHistoryDTO;


import java.util.List;

/**
 * @version 1.0
 * @Author seodong-geun
 * @since 2023/05/08
 */
public interface FifaUserService {
    FifaUser findUserByNickname(String nickname); // 닉네임으로 유저 정보 조회

    FifaUser findUserByAccessId(String accessId); // 유저 고유 식별자로 유저 정보 조회

    List<UserTearHistoryDTO> getUserTearHistoryList(String accessId); // 유저 경기별 역대 최고 등급 조회

    // 유저 매치 기록 조회
    List<String> getUserMatchHistory(String accessId, int matchType, int offset, int limit);
}
