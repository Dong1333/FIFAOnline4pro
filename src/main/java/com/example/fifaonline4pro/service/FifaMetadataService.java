package com.example.fifaonline4pro.service;

import com.example.fifaonline4pro.dto.DivisionDTO;
import com.example.fifaonline4pro.dto.MatchTypeDTO;


import java.util.List;

/**
 * @version 1.0
 * @Author seodong-geun
 * @since 2023/05/15
 * 피파의 메타 데이터를 가져오는 인터페이스 클래스
 * 이 클래스가 있어야 피파의 데이터가 변경되어도 내가 확인과 수정을 안해도 된다
 * ex) 프로 1이라는 티어 명칭 변경 > 프로패셔널 1, 브론즈 1,2,3 티어 추가
 */

public interface FifaMetadataService {

    List<MatchTypeDTO> getMatchTypeDTOList(); // 피파의 모든 경기 종류 리스트 가져오기
    List<DivisionDTO> getDivisionDTOList(); // 피파의 모든 티어 종류 리스트 가져오기
}
