package com.example.fifaonline4pro.serivce;

import com.example.fifaonline4pro.dto.match.MatchDTO;
import com.example.fifaonline4pro.service.FifaUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.ToString;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import javax.servlet.http.HttpServletRequest;
import java.util.List;



/**
 * @version 1.0
 * @Author seodong-geun
 * @since 2023/05/18
 */

@SpringBootTest
@Slf4j
@ToString
public class FifaUserServiceTest {

        @Autowired
        private FifaUserService fifaUserService;
        @Autowired
        private HttpServletRequest request;

        @Test
        @DisplayName("유저의 매칭 기록을 조회")
        public void testGetUserMatchHistory() {
            // given
            String accessId = "3640c1f174814c953bad41f9";
            int matchType = 50;
            int offset = 1;// 번째 부터
            int limit = 50;// 까지 기록 추출
            fifaUserService.setAccessIdToSession(request, accessId); // 세션에 accessId 저장

            // when
            List<String> matches = fifaUserService.findUserMatchHistory(matchType, offset, limit);

            // then
            log.info("--------(matches)------");
            for (String matchId : matches) {

                System.out.println(matchId);
            }
        }

        @Test
        @DisplayName("유저의 매칭 아이디로 경기 상세기록 조회")
        public void testGetMatchInfo() throws JsonProcessingException {
            // given
            String matchId = "646c9362f49851157eb8a9a1";

            // when
            MatchDTO matchDTO = fifaUserService.findMatchInfo(matchId);

            // then [ObjectMapper 클래스를 사용하여 'matchDTO' 객체를 JSON 문자열로 변환]
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonOutput = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(matchDTO);
            log.info("matchDTO:\n" + jsonOutput);
        }

}
