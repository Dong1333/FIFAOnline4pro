package com.example.fifaonline4pro.service;

import com.example.fifaonline4pro.dto.DivisionDTO;
import com.example.fifaonline4pro.dto.MatchTypeDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @Author seodong-geun
 * @since 2023/05/15
 * 유저의 데이터와 피파의 메타데이터를 매치 하여
 * 유저의 데이터가 어떤 데이터를 의미하는지 매치해주는 구현 클래스
 */
@Service
@Log4j2
public class FifaMetadataMatcherServiceImpl implements  FifaMetadataMatcherService {

    // 유저의 최고티어 '경기종류'를 반환
    public String getMatchTypeResult(int userMatchTypeId, List<MatchTypeDTO> fifaMatchTypeDTOList) {
        // 경기 종류 DTO 목록에서 피파 메타데이터 경기 종류와 일치하는 값을 찾아서 매칭된 경기 종류 이름을 반환한다.
        for (MatchTypeDTO matchTypeDTO : fifaMatchTypeDTOList) {
            if (matchTypeDTO.getMatchType() == userMatchTypeId) {
                return matchTypeDTO.getDesc(); // 만약
            }
        }
        return null;
    }

    // 유저의 경기 종류별 '최고 티어'를 반환
    public String getDivisionResult(int userDivisionId, List<DivisionDTO> fifaDivisionDTOList) {
        // '유저 경기별 티어 DTO 목록'에서 '피파 메타데이터 티어 종류'와 일치하는 값을 찾아서 매칭된 티어 이름을 반환한다.
        for (DivisionDTO divisionDTO : fifaDivisionDTOList) {
            if (divisionDTO.getDivisionId() == userDivisionId) {
                return divisionDTO.getDivisionName();
            }
        }
        return null;
    }
}
