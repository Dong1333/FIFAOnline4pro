package com.example.fifaonline4pro.serivce;

import com.example.fifaonline4pro.service.FifaUserService;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        @Test
        public void testGetUserMatchHistory() {
            // given
            String accessId = "3640c1f174814c953bad41f9";
            int matchType = 50;
            int offset = 0;
            int limit = 10;

            // when
            List<String> matches = fifaUserService.getUserMatchHistory(matchType, offset, limit);

            // then
            for (String matchId : matches) {
                System.out.println(matchId);
            }
        }
}
