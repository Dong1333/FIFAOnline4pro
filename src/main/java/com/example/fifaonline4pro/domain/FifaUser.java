package com.example.fifaonline4pro.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @version 1.0
 * @Author seodong-geun
 * @since 2023/04/24
 */
@Data // 모든 필드에 대한 getter/setter, toString(), equals(), hashCode() 등의 메서드 자동생성
@JsonIgnoreProperties(ignoreUnknown = true) // 직렬화 및 역직렬화 시에 특정 필드를 무시(id, name, level 외 다른 값이 와도 무시)
public class FifaUser {

    @JsonProperty("accessId") // JSON으로부터 'accessId' 값을 읽어와 FifaUser 클래스의 accessId 필드에 매핑.
    private String accessId;

    @JsonProperty("nickname") // JSON으로부터 'nickname' 값을 읽어와 FifaUser 클래스의 nickname 필드에 매핑.
    private String nickname;

    @JsonProperty("level") // JSON으로부터 'level' 값을 읽어와 FifaUser 클래스의 level 필드에 매핑.
    private int level;

    // getters and setters
}
