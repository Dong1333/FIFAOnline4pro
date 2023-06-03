package com.example.fifaonline4pro.service.match;

import com.example.fifaonline4pro.dto.match.MatchDTO;

/**
 * @version 1.0
 * @Author seodong-geun
 * @since 2023/06/01
 */
public interface FifaMatchService {

    // 유저 매칭 ID로 '매칭 상세 정보' 가져오기
    MatchDTO findMatchInfo(String matchId); // 유저 매치 상세 기록 조회
}
