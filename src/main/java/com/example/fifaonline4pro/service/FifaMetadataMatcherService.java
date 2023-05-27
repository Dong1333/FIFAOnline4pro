package com.example.fifaonline4pro.service;

import com.example.fifaonline4pro.dto.tear.DivisionDTO;
import com.example.fifaonline4pro.dto.tear.MatchTypeDTO;

import java.util.List;

/**
 * @version 1.0
 * @Author seodong-geun
 * @since 2023/05/15
 * 유저의 데이터와 피파의 메타데이터를 매치 하여
 * 유저의 데이터가 어떤 데이터를 의미하는지 매치해주는 인터페이스 클래스
 */
public interface FifaMetadataMatcherService {

    // 유저의 최고 티어가 기록된 경기 종류를 반환 ex) 공식경기, 감독모드
    String getMatchTypeResult(int userMatchTypeId, List<MatchTypeDTO> matchTypeDTOList);
    // 유저의 최고 티어를 반환 ex) 챌린지1, 슈퍼챔피언스
    String getDivisionResult(int userDivisionId, List<DivisionDTO> divisionDTOList);
}
