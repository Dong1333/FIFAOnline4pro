package com.example.fifaonline4pro.service.user;

import com.example.fifaonline4pro.domain.FifaUser;
import com.example.fifaonline4pro.dto.tear.UserTearHistoryDTO;
import com.example.fifaonline4pro.dto.trade.TradeDTO;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @version 1.0
 * @Author seodong-geun
 * @since 2023/05/08
 */
public interface FifaUserService {
    // 세션 getter/setter
    void setAccessIdToSession(HttpServletRequest request, String accessId); // (유저 고유 식별자)세션 저장
    String getAccessIdToSession(); // (유저 고유 식별자)세션 부러오기

    // ----- 유저 관련 메소드 -----
    FifaUser findUserByNickname(String nickname); // 닉네임으로 유저 정보 조회
    FifaUser findUserByAccessId(); // 유저 고유 식별자로 유저 정보 조회
    List<UserTearHistoryDTO> findUserTearHistoryList(); // 유저 경기별 역대 최고 등급 조회
    List<String> findUserMatchHistory(int matchType, int offset, int limit); // 유저 매치 ID 조회
    List<TradeDTO> findTradeHistory(String tradetype, int offset, int limit); // 유저 이적시장 내역(구매, 판매) 조회
}
