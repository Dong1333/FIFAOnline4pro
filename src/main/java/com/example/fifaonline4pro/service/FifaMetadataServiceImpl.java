package com.example.fifaonline4pro.service;

import com.example.fifaonline4pro.dto.tear.DivisionDTO;
import com.example.fifaonline4pro.dto.tear.MatchTypeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author seodong-geun
 * @since 2023/05/15
 * 피파의 메타 데이터를 가져오는 구현 클래스
 */
@Service
@RequiredArgsConstructor // final 선언 필드 생성자 생성
@Slf4j
public class FifaMetadataServiceImpl implements FifaMetadataService{
    RestTemplate restTemplate = new RestTemplate();

    // 피파의 모든 경기 종류 리스트를 가져와 DTO 리스트로 반환
    public List<MatchTypeDTO> getMatchTypeDTOList() {
        String url = "https://static.api.nexon.co.kr/fifaonline4/latest/matchtype.json";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        List<MatchTypeDTO> matchTypeDTOList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(responseEntity.getBody()); // 받아온 responseEntity의 body의 Json데이터 추출

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i); // JSON 배열의 i 번째 데이터 JSON 객체로 반환

                // 경기 종류를 저장하는 DTO 생성 후 값 저장 (id, 경기 종류) > : {"matchtype":30,"desc":"리그 친선"}...
                MatchTypeDTO matchTypeDTO = new MatchTypeDTO();
                matchTypeDTO.setMatchType(jsonObject.getInt("matchtype"));
                matchTypeDTO.setDesc(jsonObject.getString("desc"));

                // 객체를 리스트에 추가
                matchTypeDTOList.add(matchTypeDTO);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return matchTypeDTOList;
    }

    // 피파의 모든 티어 종류 리스트를 가져와 DTO 리스트로 반환
    public List<DivisionDTO> getDivisionDTOList() {
        String url = "https://static.api.nexon.co.kr/fifaonline4/latest/division.json";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        List<DivisionDTO> divisionDTOList = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(responseEntity.getBody()); // 받아온 responseEntity의 body의 Json데이터 추출

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i); // JSON 배열의 i 번째 데이터 JSON 객체로 반환

                // 티어 정보 저장하는 DTO 생성 후 값 저장 (id, 티어 이름) > {"divisionName":"슈퍼챔피언스","divisionId":800}]...
                DivisionDTO divisionDTO = new DivisionDTO();
                divisionDTO.setDivisionId(jsonObject.getInt("divisionId"));
                divisionDTO.setDivisionName(jsonObject.getString("divisionName"));

                // 객체를 리스트에 추가
                divisionDTOList.add(divisionDTO);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return divisionDTOList;
    }
}
